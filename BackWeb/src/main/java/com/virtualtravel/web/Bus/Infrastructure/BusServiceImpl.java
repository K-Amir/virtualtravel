package com.virtualtravel.web.Bus.Infrastructure;

import com.virtualtravel.web.Bus.Domain.BusEntity;
import com.virtualtravel.web.Bus.Domain.BusService;
import com.virtualtravel.web.Bus.Infrastructure.Jpa.BusJpaRepository;
import com.virtualtravel.web.ErrorHandling.NoSeatsAvailableException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class BusServiceImpl implements BusService {
    @PersistenceContext
    private EntityManager entityManager;
    private BusJpaRepository busRepo;

    @Override
    public void createBus(BusEntity busEntity) {
        busRepo.saveAndFlush(busEntity);
    }

    @Override
    public BusEntity findBusById(int id) {
        return busRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Bus with id: " + id +" not found :("));
    }

    @Override
    public List<BusEntity> findAllBuses() {
        return busRepo.findAll();
    }

    @Override
    public void deleteBusById(int id) {
       try {
           busRepo.deleteById(id);
       }catch (Exception e){
           throw new EntityNotFoundException("Could not delete bus with id: " + id +" , sorry :(");
       }
    }

    @Override
    public void findBusToBookAndDecreaseSeat(Date date, String city, String hour) {
        BusEntity busEntity = busRepo.findBusEntityByDateIsAndCityIsAndHourIs(date, city, hour);

        if(busEntity == null){
            throw new EntityNotFoundException("There's no bus for the specified city, date and hour ");
        }
        if (busEntity.getAvailableSeats() <= 0) {
            throw new NoSeatsAvailableException("The book seats are already full");
        }

        busEntity.setAvailableSeats(busEntity.getAvailableSeats()-1);

        busRepo.saveAndFlush(busEntity);
    }

    @Override
    public void syncBusAvailableSeats(BusEntity busEntity) {
        BusEntity busEntityToUpdate = findBusById(busEntity.getId());
        busEntityToUpdate.setAvailableSeats(busEntity.getAvailableSeats());
        busRepo.saveAndFlush(busEntityToUpdate);
    }

    @Override
    public List<BusEntity> getAvailableBookings(String city, HashMap<String, Object> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BusEntity> query = cb.createQuery(BusEntity.class);
        Root<BusEntity> root = query.from(BusEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        params.forEach((key,value)->{
            switch (key){
                case "lowerDate":
                    try {
                        predicates.add(cb.greaterThanOrEqualTo(root.get("date"), (Date)new SimpleDateFormat("ddMMyyyy").parse((String) value)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "upperDate":
                    try {
                        predicates.add(cb.lessThan(root.get("date"), (Date)new SimpleDateFormat("ddMMyyyy").parse((String) value)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "lowerHour":
                    predicates.add(cb.greaterThanOrEqualTo(root.get("hour"), (String)value));
                    break;
              case "upperHour":
                    predicates.add(cb.lessThan(root.get("hour"), (String)value));
                    break;

            }
        });

        predicates.add(cb.equal(root.get("city"), city));
        predicates.add(cb.greaterThan(root.get("availableSeats"), 0));

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager
                .createQuery(query)
                .getResultList();

    }
}

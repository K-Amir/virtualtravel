package com.virtualtravel.web.Bookings.Infrastructure;

import com.virtualtravel.rabbitmq.RabbitMQProducer;
import com.virtualtravel.web.Bookings.Application.Dto.BookingFormInputDto;
import com.virtualtravel.web.Bookings.Domain.BookingRegistryEntity;
import com.virtualtravel.web.Bookings.Domain.BookingRegistryService;
import com.virtualtravel.web.Bookings.Infrastructure.Jpa.BookingRegistryJpaRepository;
import com.virtualtravel.web.Bus.Domain.BusEntity;
import com.virtualtravel.web.Bus.Domain.BusService;
import lombok.AllArgsConstructor;
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
public class BookingRegistryServiceImpl implements BookingRegistryService {
    private BookingRegistryJpaRepository bookingRegistryRepo;
    private BusService busService;
    private RabbitMQProducer rabbitMQProducer;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createBookingRegistry(BookingRegistryEntity bookingRegistryEntity) {
        bookingRegistryRepo.save(bookingRegistryEntity);
    }

    @Override
    public void registerBooking(BookingFormInputDto bookingFormInputDto) {
        BookingRegistryEntity bookingRegistryEntity = BookingRegistryEntity.builder()
                .city(bookingFormInputDto.getCity())
                .email(bookingFormInputDto.getEmail())
                .createdAt(new Date())
                .isAccepted(true)
                .hour(bookingFormInputDto.getHour())
                .date(bookingFormInputDto.getDate())
                .build();

        try {
            busService.findBusToBookAndDecreaseSeat(bookingFormInputDto.getDate(), bookingFormInputDto.getCity(), bookingFormInputDto.getHour());
            createBookingRegistry(bookingRegistryEntity);
            rabbitMQProducer.send(bookingFormInputDto, "bus", "register-booking");


        } catch (EntityNotFoundException ex) {
            bookingRegistryEntity.setAccepted(false);
            createBookingRegistry(bookingRegistryEntity);
            throw new EntityNotFoundException("There's no bus for the specified city, date and hour ");

        }


    }

    @Override
    public List<BookingRegistryEntity> getBookingsHistory(String city, HashMap<String, Object> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookingRegistryEntity> query = cb.createQuery(BookingRegistryEntity.class);
        Root<BookingRegistryEntity> root = query.from(BookingRegistryEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        params.forEach((key, value) -> {
            switch (key) {
                case "lowerDate":
                    try {
                        predicates.add(cb.greaterThanOrEqualTo(root.get("date"), (Date) new SimpleDateFormat("ddMMyyyy").parse((String) value)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "upperDate":
                    try {
                        predicates.add(cb.lessThan(root.get("date"), (Date) new SimpleDateFormat("ddMMyyyy").parse((String) value)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "lowerHour":
                    predicates.add(cb.greaterThanOrEqualTo(root.get("hour"), (String) value));
                    break;
                case "upperHour":
                    predicates.add(cb.lessThan(root.get("hour"), (String) value));
                    break;

            }
        });
        predicates.add(cb.equal(root.get("city"), city));


        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager
                .createQuery(query)
                .getResultList();

    }
}

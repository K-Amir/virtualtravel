package com.virtualtravel.empresa.Bus.Infrastructure;


import com.virtualtravel.empresa.Bus.Domain.BusEntity;
import com.virtualtravel.empresa.Bus.Domain.BusService;
import com.virtualtravel.empresa.Bus.Infrastructure.Jpa.BusJpaRepository;
import com.virtualtravel.empresa.ErrorHandling.NoSeatsAvailableException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public record BusServiceImpl(BusJpaRepository busRepo) implements BusService {
    @Override
    public void createBus(BusEntity busEntity) {
        busRepo.saveAndFlush(busEntity);
    }

    @Override
    public BusEntity findBusById(int id) {
        return busRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Bus with id: " + id + " not found :("));
    }

    @Override
    public List<BusEntity> findAllBuses() {
        return busRepo.findAll();
    }

    @Override
    public void deleteBusById(int id) {
        try {
            busRepo.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Could not delete bus with id: " + id + " , sorry :(");
        }
    }

    @Override
    public BusEntity findBusToBook(Date date, String city, String hour) {
        BusEntity busEntity = busRepo.findBusEntityByDateIsAndCityIsAndHourIs(date, city, hour);
        if (busEntity == null) {
            throw new EntityNotFoundException("There's no bus for the specified city, date and hour ");
        }
        if (busEntity.getAvailableSeats() <= 0) {
            throw new NoSeatsAvailableException("The book seats are already full");
        }
        return busEntity;
    }

    @Override
    public void decreaseAvailableSeats(BusEntity busEntity) {
        busEntity.setAvailableSeats(busEntity.getAvailableSeats() - 1);
        busRepo.saveAndFlush(busEntity);
    }
}

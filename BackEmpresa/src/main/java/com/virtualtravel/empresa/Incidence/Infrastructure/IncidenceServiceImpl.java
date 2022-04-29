package com.virtualtravel.empresa.Incidence.Infrastructure;

import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Booking.Domain.BookingService;
import com.virtualtravel.empresa.Bus.Domain.BusEntity;
import com.virtualtravel.empresa.Bus.Domain.BusService;
import com.virtualtravel.empresa.Incidence.Domain.IncidenceEntity;
import com.virtualtravel.empresa.Incidence.Domain.IncidenceService;
import com.virtualtravel.empresa.Incidence.Infrastructure.Jpa.IncidenceJpaRepository;
import com.virtualtravel.empresa.Mail.Domain.MailService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public record IncidenceServiceImpl(
        IncidenceJpaRepository incidenceRepo,
        BusService busService,
        MailService mailService
) implements IncidenceService {
    @Override
    public void save(IncidenceEntity incidenceEntity, int busId) {

        BusEntity busEntity = busService().findBusById(busId);

        incidenceEntity.setBusEntity(busEntity);

        incidenceRepo.save(incidenceEntity);
    }



    @Override
    public void deleteById(int id) {
        try {
            incidenceRepo.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Could not delete incidence with id: " + id + " , sorry :(");
        }
    }

    @Override
    public IncidenceEntity findById(int id) {
        return incidenceRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Incidence with id: " + id + " not found :("));
    }

    @Override
    public List<IncidenceEntity> findAll() {
        return incidenceRepo.findAll();
    }

    @Override
    public IncidenceEntity findIncidenceByBusId(int id) {
        return incidenceRepo.findIncidenceByBusEntity_Id(id);
    }


}

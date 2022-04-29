package com.virtualtravel.empresa.Booking.Infrastructure;

import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Booking.Domain.BookingService;
import com.virtualtravel.empresa.Booking.Infrastructure.Jpa.BookingJpaRepository;
import com.virtualtravel.empresa.Bus.Domain.BusEntity;
import com.virtualtravel.empresa.Bus.Domain.BusService;
import com.virtualtravel.empresa.ErrorHandling.BusIncidenceExpcetion;
import com.virtualtravel.empresa.Incidence.Domain.IncidenceEntity;
import com.virtualtravel.empresa.Incidence.Domain.IncidenceService;
import com.virtualtravel.empresa.Mail.Domain.MailService;
import com.virtualtravel.rabbitmq.RabbitMQProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public record BookingServiceImpl(
        BookingJpaRepository bookingRepo,
        BusService busService,
        MailService mailService,
        IncidenceService incidenceService,
        RabbitMQProducer rabbitMQProducer
) implements BookingService {
    @Override
    public BookingEntity createBooking(BookingEntity bookingEntity) {

        BusEntity busEntity = busService.findBusToBook(bookingEntity.getDate(), bookingEntity.getCity(), bookingEntity.getHour());

        bookingEntity.setBusEntity(busEntity);

        BookingEntity bookingEntitySaved = bookingRepo.saveAndFlush(bookingEntity);

        IncidenceEntity incidence = incidenceService.findIncidenceByBusId(busEntity.getId());

        if (busHasIncidences(incidence)) {
            mailService.sendSuccessEmail(bookingEntity);
            throw new BusIncidenceExpcetion("Bus has an incidence:  " + incidence.getReason());
        }

        mailService.sendSuccessEmail(bookingEntitySaved);
        busService.decreaseAvailableSeats(busEntity);

        return bookingEntitySaved;
    }

    @Override
    public BookingEntity findBookingById(int id) {
        return bookingRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking with id: " + id + " not found :("));
    }

    @Override
    public void deleteBookingById(int id) {
        try {
            bookingRepo.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Could not delete booking with id: " + id + " , sorry :(");
        }
    }

    @Override
    public void registerBookingFromWebAndCheckAvailability(BookingEntity bookingEntity) {
        BusEntity busEntity = busService.findBusToBook(bookingEntity.getDate(), bookingEntity.getCity(), bookingEntity.getHour());

        bookingEntity.setBusEntity(busEntity);

        bookingRepo.saveAndFlush(bookingEntity);

        IncidenceEntity incidence = incidenceService.findIncidenceByBusId(busEntity.getId());


        if (busHasNoIncidences(incidence)) {
            busService.decreaseAvailableSeats(busEntity);
            mailService.sendSuccessEmail(bookingEntity);

        }

        if (busHasIncidences(incidence)) {
            mailService.sendCancellationEmail(bookingEntity, incidence.getReason());

            rabbitMQProducer.send(busEntity, "bus-sync", "bus.sync");
        }


    }

    @Override
    public List<BookingEntity> findBookingEntitiesByBusEntity_Id(int busId) {
        return bookingRepo.findBookingEntitiesByBusEntity_Id(busId);
    }

    @Override
    public BookingEntity findBookingEntityByEmailIsAndCityIsAndDateIsAndHourIs(String email, String city, Date date, String hour) {
        return bookingRepo.findBookingEntityByEmailIsAndCityIsAndDateIsAndHourIs(email,city,date,hour);
    }

    private boolean busHasNoIncidences(IncidenceEntity incidence) {
        return incidence == null;
    }

    private boolean busHasIncidences(IncidenceEntity incidence) {
        return incidence != null;
    }


}

package com.virtualtravel.empresa.Booking.Infrastructure.Jpa;

import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Booking.Domain.BookingService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, Integer> {
    List<BookingEntity> findBookingEntitiesByBusEntity_Id(int busId);

    BookingEntity findBookingEntityByEmailIsAndCityIsAndDateIsAndHourIs(String email, String city, Date date, String hour);
}

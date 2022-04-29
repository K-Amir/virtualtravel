package com.virtualtravel.empresa.Booking.Domain;

import com.virtualtravel.empresa.Booking.Application.Dto.BookingFormInputDto;

import java.util.Date;
import java.util.List;

public interface BookingService {

    BookingEntity createBooking(BookingEntity bookingEntity);

    BookingEntity findBookingById(int id);

    void deleteBookingById(int id);

    void registerBookingFromWebAndCheckAvailability(BookingEntity bookingEntity);

    List<BookingEntity> findBookingEntitiesByBusEntity_Id(int busId);

    BookingEntity findBookingEntityByEmailIsAndCityIsAndDateIsAndHourIs(String email, String city, Date date, String hour);
}

package com.virtualtravel.web.Bookings.Domain;

import com.virtualtravel.web.Bookings.Application.Dto.BookingFormInputDto;

import java.util.HashMap;
import java.util.List;

public interface BookingRegistryService {

    void createBookingRegistry(BookingRegistryEntity bookingRegistryEntity);

    void registerBooking(BookingFormInputDto bookingFormInputDto);

    List<BookingRegistryEntity> getBookingsHistory(String city, HashMap<String, Object> params);
}

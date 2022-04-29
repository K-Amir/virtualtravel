package com.virtualtravel.web.Bookings.Application;


import com.virtualtravel.web.Bookings.Application.Dto.AvailableBookingOutputDto;
import com.virtualtravel.web.Bookings.Application.Dto.BookingFormInputDto;
import com.virtualtravel.web.Bookings.Application.Dto.BookingOutputDto;
import com.virtualtravel.web.Bookings.Domain.BookingMapper;
import com.virtualtravel.web.Bookings.Domain.BookingRegistryEntity;
import com.virtualtravel.web.Bookings.Domain.BookingRegistryService;
import com.virtualtravel.web.Bookings.Domain.CompanyFeignClient;
import com.virtualtravel.web.Bus.Application.Dto.BusOutputDto;
import com.virtualtravel.web.Bus.Domain.BusEntity;
import com.virtualtravel.web.Bus.Domain.BusMapper;
import com.virtualtravel.web.Bus.Domain.BusService;
import com.virtualtravel.web.ErrorHandling.RequiredQueryParamException;
import com.virtualtravel.web.ErrorHandling.TokenNotValidException;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v0/bookings")
public record BookingController(BookingRegistryService bookingRegistryService, BusService busService,
                                CompanyFeignClient companyFeignClient) {

    @PostMapping()
    public ResponseEntity<BookingOutputDto> registerBooking(@RequestBody BookingFormInputDto bookingFormInputDto) {
        bookingRegistryService.registerBooking(bookingFormInputDto);
        return ResponseEntity.ok(BookingMapper.MAP.bookingInToOutDto(bookingFormInputDto));
    }


    @GetMapping("/available/{city}")
    public ResponseEntity<?> findAvailableBookings(@PathVariable String city, @RequestParam HashMap<String, Object> params) {
        if (!params.containsKey("lowerDate")) {
            throw new RequiredQueryParamException("lowerDate param is required");
        }
        List<BusEntity> availableBookingsList = busService.getAvailableBookings(city, params);


        List<AvailableBookingOutputDto> output = new ArrayList<>();
        availableBookingsList.forEach(x -> output.add(BusMapper.MAP.inputListToAvailableList(x)));

        return ResponseEntity.ok(output);
    }


    //TODO: Require Auth
    @GetMapping("/booked/{city}")
    public ResponseEntity<?> findBookingsHistory(
            @PathVariable String city,
            @RequestParam HashMap<String, Object> params,
            @RequestHeader("Authorization") String auth) {
        ResponseEntity<Boolean> response = companyFeignClient.checkAuth(auth);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new TokenNotValidException("Provided token is not valid");
        }

        if (!params.containsKey("lowerDate")) {
            throw new RequiredQueryParamException("lowerDate param is required");
        }

        List<BookingRegistryEntity> bookingRegistryEntityList =
                bookingRegistryService.getBookingsHistory(city, params);

        List<BookingOutputDto> output = BookingMapper.MAP.bookingListToOutDto(bookingRegistryEntityList);

        return ResponseEntity.ok(output);
    }
}

package com.virtualtravel.empresa.Booking.Application;


import com.virtualtravel.empresa.Auth.Infrastructure.JwtUtil;
import com.virtualtravel.empresa.Booking.Application.Dto.BookingFormInputDto;
import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Booking.Domain.BookingMapper;
import com.virtualtravel.empresa.Booking.Domain.BookingService;
import com.virtualtravel.empresa.ErrorHandling.SuccessDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("empresa/v0/bookings")
public record BookingController(BookingService bookingService, JwtUtil jwtUtil) {
    @PostMapping
    public ResponseEntity<SuccessDto> createBooking(@RequestBody BookingFormInputDto bookingFormInputDto, @RequestHeader("Authorization") String auth){
        jwtUtil.getSubject(auth);
        BookingEntity bookingEntity = BookingMapper.MAP.bookingInDtoToEntity(bookingFormInputDto);
        bookingService.createBooking(bookingEntity);
        return SuccessDto.send("Your booking has been registered successfully");
    }
}

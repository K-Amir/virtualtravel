package com.virtualtravel.empresa.BookingListener;


import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Booking.Domain.BookingMapper;
import com.virtualtravel.empresa.Booking.Domain.BookingService;
import com.virtualtravel.empresa.Booking.Application.Dto.BookingFormInputDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public record BookingListener(BookingService bookingService) {
    @RabbitListener(queues = "booking")
    public void receiveMessage(BookingFormInputDto bookingFormInputDto) {
        BookingEntity bookingEntity =  BookingMapper.MAP.bookingInDtoToEntity(bookingFormInputDto);
        bookingService.registerBookingFromWebAndCheckAvailability(bookingEntity);
    }


}

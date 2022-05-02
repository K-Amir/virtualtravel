package com.virtualtravel.empresa.RabbitMQListener;


import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Booking.Domain.BookingMapper;
import com.virtualtravel.empresa.Booking.Domain.BookingService;
import com.virtualtravel.empresa.Booking.Application.Dto.BookingFormInputDto;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public record RabbitMQListener(BookingService bookingService) {
    @RabbitListener(
            bindings = @QueueBinding(
                    value =  @Queue(value = "bookings", durable = "true"),
                    exchange = @Exchange(value = "bus",ignoreDeclarationExceptions = "true"),
                    key = "register-booking"
            )
    )
    public void receiveMessage(BookingFormInputDto bookingFormInputDto) {
        BookingEntity bookingEntity =  BookingMapper.MAP.bookingInDtoToEntity(bookingFormInputDto);
        bookingService.registerBookingFromWebAndCheckAvailability(bookingEntity);
    }


}

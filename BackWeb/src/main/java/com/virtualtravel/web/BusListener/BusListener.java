package com.virtualtravel.web.BusListener;


import com.virtualtravel.web.Bus.Domain.BusEntity;
import com.virtualtravel.web.Bus.Domain.BusService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public record BusListener(BusService busService) {

    @RabbitListener(queues = "synchronize")
    public void receiveMessage(BusEntity busEntity) {
        busService.syncBusAvailableSeats(busEntity);
    }
}

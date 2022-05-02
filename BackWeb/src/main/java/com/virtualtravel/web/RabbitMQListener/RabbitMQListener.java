package com.virtualtravel.web.RabbitMQListener;


import com.virtualtravel.web.Bus.Domain.BusEntity;
import com.virtualtravel.web.Bus.Domain.BusService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public record RabbitMQListener(BusService busService) {


    @RabbitListener(
            bindings = @QueueBinding(
                    value =  @Queue(value = "update-seats", durable = "true"),
                    exchange = @Exchange(value = "bus",ignoreDeclarationExceptions = "true"),
                    key = "sync.seats"
            )
    )
    public void syncBusSeats(BusEntity busEntity) {
        busService.syncBusAvailableSeats(busEntity);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value =  @Queue(value = "create-bus", durable = "true"),
                    exchange = @Exchange(value = "bus",ignoreDeclarationExceptions = "true"),
                    key = "bus.create"
            )
    )
    public void createBusSync(BusEntity busEntity) {
        busService.createBus(busEntity);
    }


    @RabbitListener(
            bindings = @QueueBinding(
                    value =  @Queue(value = "delete-bus", durable = "true"),
                    exchange = @Exchange(value = "bus",ignoreDeclarationExceptions = "true"),
                    key = "bus.delete"
            )
    )
    public void deleteBusSync(int id) {
       busService.deleteBusById(id);
    }





}

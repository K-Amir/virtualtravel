package com.virtualtravel.empresa.Bus.Domain;


import com.virtualtravel.empresa.ErrorHandling.SuccessDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("backweb")
@LoadBalancerClient(name = "backweb")
public interface WebBusClient {
    @PostMapping(path = "api/v1/web/buses")
    SuccessDto saveBusToBackweb(BusEntity busInputDto);

    @DeleteMapping(path = "api/v1/web/buses/{id}")
    SuccessDto deleteBusById(@PathVariable("id") int id);
}

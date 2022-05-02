package com.virtualtravel.web.Bookings.Domain;


import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("backempresa")
@LoadBalancerClient(name = "backempresa")
public interface CompanyFeignClient {
    @GetMapping("empresa/v0/auth/token/{token}")
    ResponseEntity<Boolean> checkAuth(@PathVariable("token") String token);
}

package com.virtualtravel.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HealthCheck {
    @GetMapping
    public String isHealthy(){
        return "healhy";
    }
}

package com.virtualtravel.empresa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
        scanBasePackages = {
                "com.virtualtravel.empresa",
                "com.virtualtravel.rabbitmq"
        }
)
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.virtualtravel.empresa"
)
@EnableScheduling //enabled to sync eveyr 5 seconds the num of seats
public class EmpresaApplication {
  public static void main(String[] args) {
      SpringApplication.run(EmpresaApplication.class, args);
  }


}

package com.virtualtravel.empresa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

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
public class EmpresaApplication {
  public static void main(String[] args) {
      SpringApplication.run(EmpresaApplication.class, args);
  }


}

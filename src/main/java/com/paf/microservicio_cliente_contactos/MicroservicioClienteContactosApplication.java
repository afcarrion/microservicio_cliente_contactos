package com.paf.microservicio_cliente_contactos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = {"controller"})
@EntityScan(basePackages = {"model"})
@SpringBootApplication
public class MicroservicioClienteContactosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioClienteContactosApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

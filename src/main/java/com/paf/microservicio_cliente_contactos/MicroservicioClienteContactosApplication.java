package com.paf.microservicio_cliente_contactos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@ComponentScan(basePackages = {"controller","service"})
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

    @Bean
    public Executor executor(){
        return new ThreadPoolTaskExecutor();
    }
}

package com.lm.lmliving.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LmlivingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LmlivingServiceApplication.class,args);
    }
}

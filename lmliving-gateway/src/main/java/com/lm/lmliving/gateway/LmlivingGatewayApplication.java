package com.lm.lmliving.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LmlivingGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LmlivingGatewayApplication.class,args);
    }
}

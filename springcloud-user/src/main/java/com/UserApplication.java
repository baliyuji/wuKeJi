package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //Eureka Client
public class UserApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UserApplication.class).web(true).run(args);
    }
}

package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication //spring-boot 启动注解
@EnableEurekaServer // spring-cloud 服务注解
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);

//         new SpringApplicationBuilder(EurekaApplication.class).web(true).run(args);
    }
}

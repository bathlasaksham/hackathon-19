package com.airbus.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.airbus.hackathon"})
@EntityScan(basePackages = {"com.airbus.hackathon"})
@EnableScheduling
public class HackathonApplication {

    public static void main(String[] args) {

        @SuppressWarnings("unused")
        ApplicationContext ctx = SpringApplication.run(HackathonApplication.class, args);

    }

}

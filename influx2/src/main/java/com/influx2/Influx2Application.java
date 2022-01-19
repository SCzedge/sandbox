package com.influx2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class Influx2Application {

    public static void main(String[] args) {
        SpringApplication.run(Influx2Application.class, args);
    }

}

package com.springbatch.schedulers;

import com.springbatch.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SimpleScheduler {
    private final SimpleService simpleService;

    @Autowired
    public SimpleScheduler(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @Scheduled(fixedDelay = 5 * 1000L)
    public void simpleJobExecute() {
        System.out.println("START");
        try {
            simpleService.doSimpleJob(LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
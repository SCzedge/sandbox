package com.springbatch.controller;

import com.springbatch.service.SimpleService;
import org.springframework.batch.core.JobExecution;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class SimpleController {
    private final SimpleService simpleService;

    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @RequestMapping("")
    public ResponseEntity<?> doSimpleJob(@RequestParam String requestedDate) {
        try{

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime date = LocalDateTime.from(LocalDate.parse(requestedDate,formatter));

            JobExecution je = simpleService.doSimpleJob(date);
            return ResponseEntity.ok(je);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}

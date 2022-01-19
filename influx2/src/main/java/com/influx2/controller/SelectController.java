package com.influx2.controller;

import com.influx2.model.RawData;
import com.influx2.service.RawDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SelectController {
    private final RawDataService rawDataService;

    public SelectController(RawDataService rawDataService) {
        this.rawDataService = rawDataService;
    }

    private final DateTimeFormatter dateTimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/")
    public ResponseEntity<?> test() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/select")
    public ResponseEntity<?> select() {
        try {
            rawDataService.select();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/select/range")
    public ResponseEntity<?> rangeSelect() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/select/param")
    public ResponseEntity<?> paramSelect() {
        return ResponseEntity.ok().build();
    }

}

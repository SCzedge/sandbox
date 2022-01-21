package com.influx.controller;

import com.google.gson.Gson;
import com.influx.service.SelectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class SelectController {
    private final SelectService selectService;

    public SelectController(SelectService selectService) {
        this.selectService = selectService;
    }

    private final Gson gson = new Gson();
    private final DateTimeFormatter dateTimeFormatPattern =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/select/")
    public ResponseEntity<?> select() {
        try {
            return ResponseEntity.ok(selectService.select());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/select/param")
    public ResponseEntity<?> selectParam() {
        try {
            return ResponseEntity.ok(selectService.selectParam());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/select/range")
    public ResponseEntity<?> selectRange() {
        try {
            return ResponseEntity.ok(selectService.selectRange());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

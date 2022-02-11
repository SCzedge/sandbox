package com.rawdatamover.controller;

import com.rawdatamover.service.SelectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelectController {
    private final SelectService selectService;

    public SelectController(SelectService selectService) {
        this.selectService = selectService;
    }

    public ResponseEntity<?> selectPrimary() {


        return ResponseEntity.ok().build();
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

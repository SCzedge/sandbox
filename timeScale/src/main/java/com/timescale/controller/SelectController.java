package com.timescale.controller;

import com.timescale.service.SelectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelectController {
    private final SelectService selectService;

    public SelectController(SelectService selectService) {
        this.selectService = selectService;
    }

    public ResponseEntity<?> test() {


        return ResponseEntity.ok().build();
    }
}

package com.influx.controller;

import com.google.gson.Gson;
import com.influx.model.RawData;
import com.influx.service.RawDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class RawDataController {
    private final RawDataService rawDataService;

    public RawDataController(RawDataService rawDataService) {
        this.rawDataService = rawDataService;
    }

    @GetMapping("/insert")
    public ResponseEntity<?> test() {
        try {
            Gson gson = new Gson();
            DateTimeFormatter dateTimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            RawData rawData = new RawData();
//            rawData.setUnit(LocalDateTime.now());
            rawData.setUnit(LocalDateTime.now().format(dateTimeFormatPattern));
            rawData.setPointId("1");
            rawData.setValue(0.3);
            rawData.setRmk("for the horde - crush the vicious alliance");


            log.info("rawData : {}", gson.toJson(rawData));
            rawDataService.rawData(rawData);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/select")
    public ResponseEntity<?> select() {
        try {
            return ResponseEntity.ok(rawDataService.select());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

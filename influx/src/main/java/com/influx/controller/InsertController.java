package com.influx.controller;

import com.google.gson.Gson;
import com.influx.model.RawData;
import com.influx.service.RawDataService;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.annotation.Measurement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class InsertController {
    private final RawDataService rawDataService;

    public InsertController(RawDataService rawDataService) {
        this.rawDataService = rawDataService;
    }

    private final Gson gson = new Gson();
    private final DateTimeFormatter dateTimeFormatPattern =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @GetMapping("/test")
    public ResponseEntity<?> test() {
        try {
            RawData rawData = new RawData();
            String name = rawData.getClass().getAnnotation(Measurement.class).name();
            return ResponseEntity.ok(name);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/insert")
    public ResponseEntity<?> insert() {
        try {
            RawData rawData = new RawData();
//            rawData.setUnit(LocalDateTime.now());
            rawData.setUnit(LocalDateTime.now().format(dateTimeFormatPattern));
            rawData.setPointId("1");
            rawData.setValue(0.1);
            rawData.setRmk("for the horde");

            log.info("rawData : {}", gson.toJson(rawData));
            rawDataService.insert(rawData);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/insert/batch")
    public ResponseEntity<?> batchInsert() {
        try {
            List<RawData> rawDataList = new ArrayList<>();

            RawData rawData1 = new RawData();
            rawData1.setUnit(LocalDateTime.now().format(dateTimeFormatPattern));
            rawData1.setPointId("2");
            rawData1.setValue(0.2);
            rawData1.setRmk("for the horde");
            rawDataList.add(rawData1);

            RawData rawData2 = new RawData();
            rawData2.setUnit(LocalDateTime.now().format(dateTimeFormatPattern));
            rawData2.setPointId("3");
            rawData2.setValue(0.3);
            rawData2.setRmk("for the horde");
            rawDataList.add(rawData2);

            rawDataService.batchInsert(rawDataList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

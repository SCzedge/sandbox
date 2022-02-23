package com.tsdb.controller;

import com.google.gson.Gson;
import com.tsdb.model.RawData;
import com.tsdb.service.InfluxService;
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
public class InfluxController {

    private final InfluxService influxService;

    public InfluxController(InfluxService influxService) {
        this.influxService = influxService;
    }

    private final Gson gson = new Gson();
    private final DateTimeFormatter dateTimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
            rawData.setMeasuredDate(LocalDateTime.now().format(dateTimeFormatPattern));
            rawData.setPointId("1");
            rawData.setValue(0.1);

            log.info(" RawData : {}", gson.toJson(rawData));
            influxService.insert(rawData);

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
            rawData1.setMeasuredDate(LocalDateTime.now().format(dateTimeFormatPattern));
            rawData1.setPointId("2");
            rawData1.setValue(0.2);
            rawDataList.add(rawData1);

            RawData rawData2 = new RawData();
            rawData2.setMeasuredDate(LocalDateTime.now().format(dateTimeFormatPattern));
            rawData2.setPointId("3");
            rawData2.setValue(0.3);
            rawDataList.add(rawData2);

            influxService.batchInsert(rawDataList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

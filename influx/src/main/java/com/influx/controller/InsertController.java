package com.influx.controller;

import com.google.gson.Gson;
import com.influx.model.Sample;
import com.influx.service.InsertService;
import com.influx.service.RawDataService;
import com.influx.service.SelectService;
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

    private final InsertService insertService;

    public InsertController(InsertService insertService) {
        this.insertService = insertService;
    }

    private final Gson gson = new Gson();
    private final DateTimeFormatter dateTimeFormatPattern =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        try {
            Sample sample = new Sample();
            String name = sample.getClass().getAnnotation(Measurement.class).name();
            return ResponseEntity.ok(name);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/insert")
    public ResponseEntity<?> insert() {
        try {
            Sample sample = new Sample();
            sample.setDatetime(LocalDateTime.now().format(dateTimeFormatPattern));
            sample.setUnit("1");
            sample.setValue(0.1);
            sample.setRmk("for the horde");

            log.info("sample : {}", gson.toJson(sample));
            insertService.insert(sample);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/insert/batch")
    public ResponseEntity<?> batchInsert() {
        try {
            List<Sample> sampleList = new ArrayList<>();

            Sample sample1 = new Sample();
            sample1.setDatetime(LocalDateTime.now().format(dateTimeFormatPattern));
            sample1.setUnit("2");
            sample1.setValue(0.2);
            sample1.setRmk("for the horde");
            sampleList.add(sample1);

            Sample sample2 = new Sample();
            sample2.setDatetime(LocalDateTime.now().format(dateTimeFormatPattern));
            sample2.setUnit("3");
            sample2.setValue(0.3);
            sample2.setRmk("for the horde");
            sampleList.add(sample2);

            insertService.batchInsert(sampleList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

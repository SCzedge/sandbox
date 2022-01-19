package com.influx2.controller;

import com.google.gson.Gson;
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
public class InsertController {
    private final RawDataService rawDataService;

    public InsertController(RawDataService rawDataService) {
        this.rawDataService = rawDataService;
    }

    private final Gson gson = new Gson();
    private final DateTimeFormatter dateTimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/insert")
    public ResponseEntity<?> insert() {
        RawData rawData = new RawData();
        rawData.setUnit(LocalDateTime.now().format(dateTimeFormatPattern));
        rawData.setPointId("1");
        rawData.setValue("0.1");
        try {
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
            rawData1.setPointId("1");
            rawData1.setValue("0.1");
            RawData rawData2 = new RawData();
            rawData2.setUnit(LocalDateTime.now().format(dateTimeFormatPattern));
            rawData2.setPointId("2");
            rawData2.setValue("0.2");
            RawData rawData3 = new RawData();
            rawData3.setUnit(LocalDateTime.now().format(dateTimeFormatPattern));
            rawData3.setPointId("3");
            rawData3.setValue("0.3");


            rawDataList.add(rawData1);
            rawDataList.add(rawData2);
            rawDataList.add(rawData3);

            rawDataService.batchInsert(rawDataList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}

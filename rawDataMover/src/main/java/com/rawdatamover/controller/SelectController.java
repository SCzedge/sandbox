package com.rawdatamover.controller;

import com.rawdatamover.model.RawData;
import com.rawdatamover.model.SelectResult;
import com.rawdatamover.service.SelectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SelectController {
    private final SelectService selectService;

    public SelectController(SelectService selectService) {
        this.selectService = selectService;
    }


    @GetMapping("/select/all")
    public ResponseEntity<?> selectAll() {
        try {
            SelectResult mariaResult = new SelectResult();
            Long mariaStart = System.currentTimeMillis();
            List<RawData> mariaResultList = selectService.selectMaria();
            Long mariaEnd = System.currentTimeMillis();

            SelectResult postResult = new SelectResult();
            Long postStart = System.currentTimeMillis();
            List<RawData> postResultList = selectService.selectPost();
            Long postEnd = System.currentTimeMillis();

            SelectResult influxResult = new SelectResult();
            Long influxStrt = System.currentTimeMillis();
            List<RawData> influxResultList = selectService.selectInflux();
            Long influxEnd = System.currentTimeMillis();

            mariaResult.setType("mariadb");
            mariaResult.setRunningTime(mariaEnd - mariaStart);
            mariaResult.setResultSize(mariaResultList.size());
            postResult.setType("postgreSql");
            postResult.setRunningTime(postEnd - postStart);
            postResult.setResultSize(postResultList.size());
            influxResult.setType("influx");
            influxResult.setRunningTime(influxEnd - influxStrt);
            influxResult.setResultSize(influxResultList.size());


            return ResponseEntity.ok(new ArrayList(Arrays.asList(mariaResult,postResult,influxResult)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

package com.tsdb.controller;

import com.tsdb.service.InfluxService;
import com.tsdb.service.MoverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class MoverController {
    private final MoverService moverService;
    private final InfluxService influxService;

    public static final DateTimeFormatter dateTimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public MoverController(MoverService moverService, InfluxService influxService) {
        this.moverService = moverService;
        this.influxService = influxService;
    }

    @GetMapping("/con-test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(moverService.test());
    }


    @GetMapping("/data-move/primary2secondary")
    public ResponseEntity<?> primary2secondary(String startDate, String endDate) {
        try {
            LocalDateTime strDt = LocalDateTime.parse(startDate, dateTimeFormatPattern).withMinute(0).withSecond(0);
            LocalDateTime endDt = LocalDateTime.parse(endDate, dateTimeFormatPattern).withMinute(0).withSecond(0);

            if (endDt.isBefore(strDt)) {
                return ResponseEntity.badRequest().build();
            }

            int result = 0;
            while (strDt.isBefore(endDt) || strDt.isEqual(endDt)) {
                String requestedDate = strDt.format(dateTimeFormatPattern);
                result += moverService.primary2secondary(requestedDate);
                strDt = strDt.plusMinutes(10);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/data-move/primary2inf")
    public ResponseEntity<?> primary2inf(String startDate, String endDate) {
        try {
            LocalDateTime strDt = LocalDateTime.parse(startDate, dateTimeFormatPattern).withMinute(0).withSecond(0);
            LocalDateTime endDt = LocalDateTime.parse(endDate, dateTimeFormatPattern).withMinute(0).withSecond(0);

            if (endDt.isBefore(strDt)) {
                return ResponseEntity.badRequest().build();
            }
            int result = 0;
            while (strDt.isBefore(endDt) || strDt.isEqual(endDt)) {
                String requestedDate = strDt.format(dateTimeFormatPattern);
                result += influxService.primary2inf(requestedDate);
                strDt = strDt.plusHours(1);
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/data-move/primary2third")
    public ResponseEntity<?> primary2third(String startDate, String endDate) {
        try {
            LocalDateTime strDt = LocalDateTime.parse(startDate, dateTimeFormatPattern).withMinute(0).withSecond(0);
            LocalDateTime endDt = LocalDateTime.parse(endDate, dateTimeFormatPattern).withMinute(0).withSecond(0);

            if (endDt.isBefore(strDt)) {
                return ResponseEntity.badRequest().build();
            }
            int result = 0;
            while (strDt.isBefore(endDt) || strDt.isEqual(endDt)) {
                String requestedDate = strDt.format(dateTimeFormatPattern);
                result += moverService.primary2third(requestedDate);
                strDt = strDt.plusMinutes(1);
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

package com.influx.service;

import com.influx.model.Sample;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
public class InsertService {
    private final InfluxDBTemplate influxDBTemplate;

    public InsertService(InfluxDBTemplate influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }

    public void insert(Sample sample) {
        //Query query = BoundParameterQuery.QueryBuilder.newQuery("").forDatabase("").create();
        Timestamp.valueOf(sample.getUnit()).getTime();
        influxDBTemplate.write(Point.measurementByPOJO(Sample.class).addFieldsFromPOJO(sample).time(Timestamp.valueOf(sample.getUnit()).getTime(), TimeUnit.MILLISECONDS).build());
    }

    public void batchInsert(List<Sample> sampleList) {
        List<Point> pointList = new ArrayList<>();
        sampleList.forEach((sample -> {
            pointList.add(Point.measurementByPOJO(Sample.class).addFieldsFromPOJO(sample).build());
        }));
        influxDBTemplate.write(pointList);
    }
}

package com.influx.service;

import com.influx.model.RawData;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.annotation.Measurement;
import org.influxdb.dto.BoundParameterQuery;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.influxdb.InfluxDBTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
public class RawDataService {

    private final InfluxDBTemplate influxDBTemplate;

    public RawDataService(InfluxDBTemplate influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }

    public List<RawData> select() {
        Query query = BoundParameterQuery.QueryBuilder.newQuery("SELECT * FROM \"Raw-Data\" LIMIT 1000")
                .forDatabase("mybucket")
                .create();

        QueryResult queryResult = influxDBTemplate.query(query);
//        return influxDBTemplate.query(query);

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, RawData.class);
    }

    public List<RawData> selectParam() {
        Query query = BoundParameterQuery.QueryBuilder.newQuery("SELECT * FROM \"Raw-Data\" where pointId = \"{pointId}\" LIMIT 1000")
                .bind("{pointId}", "1")
                .forDatabase("mybucket")
                .create();


        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, RawData.class);
    }

    public List<RawData> selectRange() {

        Query query = BoundParameterQuery.QueryBuilder.newQuery("SELECT * FROM \"Raw-Data\" where time >= '2022-01-03 00:00:00' LIMIT 1000")
                .forDatabase("mybucket")
                .create();
        QueryResult queryResult = influxDBTemplate.query(query);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

        return resultMapper.toPOJO(queryResult, RawData.class);
    }


    public void insert(RawData rawData) {
        //Query query = BoundParameterQuery.QueryBuilder.newQuery("").forDatabase("").create();
        Timestamp.valueOf(rawData.getUnit()).getTime();
        influxDBTemplate.write(Point.measurementByPOJO(RawData.class).addFieldsFromPOJO(rawData).time(Timestamp.valueOf(rawData.getUnit()).getTime(), TimeUnit.MILLISECONDS).build());
    }

    public void batchInsert(List<RawData> rawDataList) {
        List<Point> pointList = new ArrayList<>();
        rawDataList.forEach((rawData -> {
            pointList.add(Point.measurementByPOJO(RawData.class).addFieldsFromPOJO(rawData).build());
        }));
        influxDBTemplate.write(pointList);
    }
}

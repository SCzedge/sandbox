package com.tsdb.service;

import com.tsdb.mapper.MoverMapper;
import com.tsdb.model.RawData;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.BoundParameterQuery;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class InfluxService {
    private final InfluxDBTemplate influxDBTemplate;
    private final MoverMapper moverMapper;

    public InfluxService(InfluxDBTemplate influxDBTemplate, MoverMapper moverMapper) {
        this.influxDBTemplate = influxDBTemplate;
        this.moverMapper = moverMapper;
    }

    public List<RawData> selectInflux() {
        Query query = BoundParameterQuery
                .QueryBuilder
                .newQuery("SELECT * " +
                        "FROM sample " +
                        "where time >= '2022-01-27 09:34:15'" +
                        "and time <= '2022-02-09 08:27:47'")
                .forDatabase("mybucket")
                .create();
        QueryResult queryResult = influxDBTemplate.query(query);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, RawData.class);
    }


    public List<RawData> selectParam() {
        Query query = BoundParameterQuery.QueryBuilder.newQuery("SELECT * FROM sample where pointId = \"{pointId}\" LIMIT 1000")
                .bind("{pointId}", "1")
                .forDatabase("mybucket")
                .create();


        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, RawData.class);
    }



    public void insert(RawData rawData) {
        //Query query = BoundParameterQuery.QueryBuilder.newQuery("").forDatabase("").create();
        influxDBTemplate.write(
                Point.measurementByPOJO(RawData.class)
                        .addFieldsFromPOJO(rawData)
                        .time(Timestamp.valueOf(rawData.getMeasuredDate()).getTime(), TimeUnit.MILLISECONDS)
                        .build()
        );
    }

    public void batchInsert(List<RawData> rawDataList) {
        List<Point> pointList = new ArrayList<>();
        rawDataList.forEach((rawData -> {
            pointList.add(
                    Point.measurementByPOJO(RawData.class)
                            .addFieldsFromPOJO(rawData)
                            .build()
            );
        }));
        influxDBTemplate.write(pointList);
    }

    public int primary2inf(String requestedDate) {
        try {
            List<RawData> rawDataList = moverMapper.readPrimaryRawData(requestedDate);

            if (rawDataList == null || rawDataList.isEmpty()) {
                throw new NullPointerException(requestedDate);
            }

            log.info(requestedDate);

            List<Point> pointList = new ArrayList<>();
            rawDataList.forEach((rawData -> {
                pointList.add(
                        Point.measurementByPOJO(RawData.class)
                                .addFieldsFromPOJO(rawData)
                                .time(Timestamp.valueOf(rawData.getMeasuredDate()).getTime(), TimeUnit.MILLISECONDS)
                                .build()
                );
            }));
            influxDBTemplate.write(pointList);
        } catch (NullPointerException e) {
            log.info("NPE : {}", e.getMessage());
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}

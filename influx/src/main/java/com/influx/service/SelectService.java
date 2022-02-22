package com.influx.service;

import com.influx.model.Sample;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.BoundParameterQuery;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SelectService {

    private final InfluxDBTemplate influxDBTemplate;

    public SelectService(InfluxDBTemplate influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }

    public List<Sample> select() {
        Query query = BoundParameterQuery.QueryBuilder.newQuery("SELECT * FROM sample LIMIT 1000")
                .forDatabase("mybucket")
                .create();

        QueryResult queryResult = influxDBTemplate.query(query);
//        return influxDBTemplate.query(query);

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Sample.class);
    }

    public List<Sample> selectParam() {
        Query query = BoundParameterQuery.QueryBuilder.newQuery("SELECT * FROM sample where pointId = \"{pointId}\" LIMIT 1000")
                .bind("{pointId}", "1")
                .forDatabase("mybucket")
                .create();


        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Sample.class);
    }

    public List<Sample> selectRange() {

        Query query = BoundParameterQuery.QueryBuilder.newQuery("SELECT * FROM sample where time >= '2022-01-03 00:00:00' LIMIT 1000")
                .forDatabase("mybucket")
                .create();
        QueryResult queryResult = influxDBTemplate.query(query);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

        return resultMapper.toPOJO(queryResult, Sample.class);
    }
}

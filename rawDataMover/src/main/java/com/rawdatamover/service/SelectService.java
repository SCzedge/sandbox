package com.rawdatamover.service;

import com.google.gson.Gson;
import com.rawdatamover.mapper.MoverMapper;
import com.rawdatamover.mapper.SelectMapper;
import com.rawdatamover.model.RawData;
import com.rawdatamover.model.RawDataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.BoundParameterQuery;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SelectService {
    private final InfluxDBTemplate influxDBTemplate;
    private final SelectMapper selectMapper;

    public SelectService(InfluxDBTemplate influxDBTemplate, SelectMapper selectMapper) {
        this.influxDBTemplate = influxDBTemplate;
        this.selectMapper = selectMapper;
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

    public List<RawData> selectMaria() {
        return selectMapper.readMaria();
    }

    public List<RawData> selectPost() {
        return selectMapper.readPost();
    }


}

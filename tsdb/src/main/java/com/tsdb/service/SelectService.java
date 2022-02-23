package com.tsdb.service;

import com.tsdb.mapper.SelectMapper;
import com.tsdb.model.RawData;
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
    private final SelectMapper selectMapper;

    public SelectService(InfluxDBTemplate influxDBTemplate, SelectMapper selectMapper) {
        this.influxDBTemplate = influxDBTemplate;
        this.selectMapper = selectMapper;
    }

    public List<RawData> selectMaria() {
        return selectMapper.readMaria();
    }

    public List<RawData> selectPost() {
        return selectMapper.readPost();
    }


}

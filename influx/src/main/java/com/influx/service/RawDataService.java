package com.influx.service;

import com.influx.model.RawData;
import org.influxdb.dto.BoundParameterQuery;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.stereotype.Service;
import org.springframework.data.influxdb.InfluxDBTemplate;
@Service
public class RawDataService {

    private final InfluxDBTemplate influxDBTemplate;

    public RawDataService(InfluxDBTemplate influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }

    public QueryResult select(){
        Query query = BoundParameterQuery.QueryBuilder.newQuery("SELECT * FROM \"Raw-Data\" LIMIT 1000")
                .forDatabase("mybucket")
                .create();
        //QueryResult queryResult = influxDBTemplate.query(query);
        return influxDBTemplate.query(query);
    }



    public void rawData(RawData rawData){
        //Query query = BoundParameterQuery.QueryBuilder.newQuery("").forDatabase("").create();
        influxDBTemplate.write(Point.measurementByPOJO(RawData.class).addFieldsFromPOJO(rawData).build());
    };
}

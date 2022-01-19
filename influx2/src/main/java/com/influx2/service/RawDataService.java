package com.influx2.service;

import com.google.gson.Gson;
import com.influx2.model.RawData;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RawDataService {

    private final WriteApiBlocking writeApiBlockingApi;
    private final QueryApi influxQueryApi;

    public RawDataService(WriteApiBlocking writeApiBlockingApi, QueryApi influxQueryApi) {
        this.writeApiBlockingApi = writeApiBlockingApi;
        this.influxQueryApi = influxQueryApi;
    }

    Gson gson = new Gson();

    public List<RawData> select() throws Exception {
        String flux = "from(bucket:\"mybucket\") " +
                "|> range(start: 0)";

        List<FluxTable> tables = influxQueryApi.query(flux);
        for (FluxTable fluxTable : tables) {
            List<FluxRecord> records = fluxTable.getRecords();

            for (FluxRecord fluxRecord : records) {
                log.info("==========================");
                log.info("measurement : {}", fluxRecord.getMeasurement());
                log.info(fluxRecord.getTime().toString());
                log.info("pointId : {}", fluxRecord.getValueByKey("pointId"));
                log.info("unit : {}", fluxRecord.getValueByKey("unit"));
                log.info(fluxRecord.getField() + " : " + fluxRecord.getValueByKey("_value").toString());
            }
        }
        return null;
    }

    public List<RawData> rangeSelect() throws Exception {
        return null;
    }

    public List<RawData> paramSelect() throws Exception {
        return null;
    }

    public void insert(RawData rawData) throws Exception {
        Point point = new Point("Raw-Data");
        point.addTag("unit", rawData.getUnit());
        point.addTag("pointId", rawData.getPointId());
        point.addField("value", rawData.getValue());
        writeApiBlockingApi.writePoint(point);
    }

    public void batchInsert(List<RawData> rawDataList) throws Exception {
        writeApiBlockingApi.writePoints(pojo2Point(rawDataList,RawData.class));
    }





    public List<Point> pojo2Point(List<?> objectList, Class<?> type) throws Exception {
        List<Point> pointList = new ArrayList<>();

        String measurement = type.getAnnotation(Measurement.class).name();
        Field[] fields = type.getDeclaredFields();
        Map<String, Map<String, Object>> fieldProp = getFieldMap(fields);

        for (Object obcject : objectList) {
            log.info("===============");
            Point point = new Point(measurement);

            for (Field field : fields) {
                field.setAccessible(true);
                Map<String, Object> prop = fieldProp.get(field.getName());
                if ((boolean) prop.get("tag")) {//tag
                    point.addTag(prop.get("name").toString(), field.get(obcject).toString());
//                    log.info("TAG_TRUE infname : {}, value : {}",prop.get("name").toString(),field.get(obcject).toString());
                } else {
//                    log.info("TAG_FALSE infname : {}, value : {}",prop.get("name").toString(),field.get(obcject).toString());
                    point.addField(prop.get("name").toString(), field.get(obcject).toString());
                }
            }
            pointList.add(point);
        }
        return pointList;
    }

    public Map<String, Map<String, Object>> getFieldMap(Field[] fields) {
        Map<String, Map<String, Object>> fieldMap = new HashMap<>();

        for (Field field : fields) {

            Map<String, Object> columnMap = new HashMap<>();

            Column column = field.getAnnotation(Column.class);
            columnMap.put("tag", column.tag());
            columnMap.put("name", column.name());
            columnMap.put("timestamp", column.timestamp());
            columnMap.put("value", "");
            fieldMap.put(field.getName(), columnMap);
        }
        return fieldMap;
    }

}

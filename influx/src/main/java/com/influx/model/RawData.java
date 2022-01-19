package com.influx.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.LocalDateTime;

@Data
@Measurement(name = "Raw-Data")
public class RawData {

    @TimeColumn
    @Column(name = "unit",tag = true)
    String unit;

    @Column(name = "pointId", tag = true)
    String pointId;

   @Column(name = "value")
   Double value;

    @Column(name = "rmk")
    String rmk;
}

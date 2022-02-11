package com.influx.model;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;


@Data
@Measurement(name = "sample")
public class Sample {

    @TimeColumn
    @Column(name = "datetime",tag = true)
    String datetime;

    @Column(name = "unit", tag = true)
    String unit;

   @Column(name = "value")
   Double value;

    @Column(name = "rmk")
    String rmk;
}

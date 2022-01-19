package com.influx2.model;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;

@Data
@Measurement(name = "MeasuredRaw")
public class RawData {

    @Column(name = "unit1", tag = true)
    String unit;

    @Column(name = "pointId", tag = true)
    String pointId;

    @Column(name = "value")
    String value;
}

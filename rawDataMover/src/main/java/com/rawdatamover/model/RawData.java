package com.rawdatamover.model;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Data
@Measurement(name = "sample")
public class RawData {
    @Column(name = "pointId", tag = true)
    String pointId;
    @Column(name = "value")
    double value;
    @Column(name = "measuredDate", tag=true)
    String measuredDate;
    @Column(name = "kafka_topic")
    String kafka_topic;
    @Column(name = "kafka_offset")
    String kafka_offset;
    @Column(name = "createdAt")
    String createdAt;
}

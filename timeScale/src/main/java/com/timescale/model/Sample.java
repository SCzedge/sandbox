package com.timescale.model;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class Sample {

    Timestamp datetime;

    String unit;

    Double value;

    String rmk;
}

package com.tsdb.model;

import lombok.Data;

import java.util.List;

@Data
public class RawDataWrapper {
    List<RawData> rawDataList;
}

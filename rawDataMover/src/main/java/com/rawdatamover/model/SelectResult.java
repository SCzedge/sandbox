package com.rawdatamover.model;

import lombok.Data;

@Data
public class SelectResult {
    String type;
    Long runningTime;
    int resultSize;
}

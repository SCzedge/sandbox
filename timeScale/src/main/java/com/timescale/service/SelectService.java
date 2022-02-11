package com.timescale.service;

import com.timescale.mapper.SelectMapper;
import org.springframework.stereotype.Service;

@Service
public class SelectService {
    private final SelectMapper selectMapper;

    public SelectService(SelectMapper selectMapper) {
        this.selectMapper = selectMapper;
    }
}

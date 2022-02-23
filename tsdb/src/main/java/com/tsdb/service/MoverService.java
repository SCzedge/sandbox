package com.tsdb.service;

import com.google.gson.Gson;
import com.tsdb.mapper.MoverMapper;
import com.tsdb.model.RawData;
import com.tsdb.model.RawDataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MoverService {

    private final MoverMapper moverMapper;


    Gson gson = new Gson();

    public MoverService(MoverMapper moverMapper) {
        this.moverMapper = moverMapper;
    }

    public int test() {
        try {
            return moverMapper.test();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int primary2secondary(String requestedDate) {
        try {
            List<RawData> rawDataList = moverMapper.readPrimaryRawData(requestedDate);

            if (rawDataList == null || rawDataList.isEmpty()) {
                throw new NullPointerException(requestedDate);
            }
            log.info(requestedDate);
            RawDataWrapper rawDataWrapper = new RawDataWrapper();
            rawDataWrapper.setRawDataList(rawDataList);
            return moverMapper.writeSecondaryRawData(rawDataWrapper);
        } catch (NullPointerException e) {
            log.error("NPE :: {}", e.getMessage());
            return 0;
        } catch (Exception e) {
            log.error("{}::{}.{}({})", e.getMessage(), this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber());
            return 0;
        }
    }



    public int primary2third(String requestedDate) {
        List<RawData> rawDataList = new ArrayList<>();
        try {
            rawDataList = moverMapper.read1minPrimaryRawData(requestedDate);
            if (rawDataList == null || rawDataList.isEmpty()) {
                throw new NullPointerException(requestedDate);
            }

            log.info(requestedDate);

            RawDataWrapper rawDataWrapper = new RawDataWrapper();
            rawDataWrapper.setRawDataList(rawDataList);
            return moverMapper.writeThirdRawData(rawDataWrapper);

        } catch (DataAccessResourceFailureException e) {
//            log.error("DARFE : {}", e.getMessage());
            log.error("DARFE : {}",requestedDate);
            log.error("rawDataList size : {}", rawDataList != null ? rawDataList.size() : 0);
            return 0;
        } catch (PSQLException e) {
            log.error("PSQLE : {}", e.getMessage());
            return 0;
        } catch (NullPointerException e) {
            log.error("NPE : {}", e.getMessage());
            return 0;
        } catch (Exception e) {
            log.error("{}::{}.{}({})", e.getMessage(), this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber());
            return 0;
        }
    }
}

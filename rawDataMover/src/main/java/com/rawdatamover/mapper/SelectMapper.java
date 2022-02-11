package com.rawdatamover.mapper;

import com.google.gson.Gson;
import com.rawdatamover.model.RawData;
import com.rawdatamover.model.RawDataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class SelectMapper {

    private final SqlSession primarySqlSessionTemplate;
    private final SqlSession secondarySqlSessionTemplate;
    private final SqlSession thirdSqlSessionTemplate;

    private final static String nameSpace = "com.rawdatamover.mapper.MoverMapper";

    Gson gson = new Gson();

    public SelectMapper(SqlSession primarySqlSessionTemplate, SqlSession secondarySqlSessionTemplate, SqlSession thirdSqlSessionTemplate) {
        this.primarySqlSessionTemplate = primarySqlSessionTemplate;
        this.secondarySqlSessionTemplate = secondarySqlSessionTemplate;
        this.thirdSqlSessionTemplate = thirdSqlSessionTemplate;
    }

    public void test() throws Exception{
        log.info(primarySqlSessionTemplate.selectOne(nameSpace + ".primaryTest"));
        log.info(secondarySqlSessionTemplate.selectOne(nameSpace + ".secondaryTest"));
        log.info(thirdSqlSessionTemplate.selectOne(nameSpace + ".thirdTest"));
    }

    public List<RawData> readPrimaryRawData(String requestedDate)throws Exception {
        return primarySqlSessionTemplate.selectList(nameSpace + ".readPrimaryRawData", requestedDate);
    }

}

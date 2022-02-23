package com.tsdb.mapper;

import com.google.gson.Gson;
import com.tsdb.model.RawData;
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

    private final static String nameSpace = "com.tsdb.mapper.SelectMapper";

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

    public List<RawData> readMaria(){
        return secondarySqlSessionTemplate.selectList(nameSpace+".readMaria","");
    }

    public List<RawData> readPost(){
        return thirdSqlSessionTemplate.selectList(nameSpace+".readPost","");
    }

}

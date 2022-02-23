package com.tsdb.mapper;

import com.google.gson.Gson;
import com.tsdb.model.RawData;
import com.tsdb.model.RawDataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class MoverMapper {

    private final SqlSession primarySqlSessionTemplate;
    private final SqlSession secondarySqlSessionTemplate;
    private final SqlSession thirdSqlSessionTemplate;

    private final static String nameSpace = "com.tsdb.mapper.MoverMapper";

    Gson gson = new Gson();

    public MoverMapper(SqlSession primarySqlSessionTemplate, SqlSession secondarySqlSessionTemplate, SqlSession thirdSqlSessionTemplate) {
        this.primarySqlSessionTemplate = primarySqlSessionTemplate;
        this.secondarySqlSessionTemplate = secondarySqlSessionTemplate;
        this.thirdSqlSessionTemplate = thirdSqlSessionTemplate;
    }

    public int test() throws Exception {
        int result = 0;
        result += Integer.parseInt(primarySqlSessionTemplate.selectOne(nameSpace + ".primaryTest").toString());
        result += Integer.parseInt(secondarySqlSessionTemplate.selectOne(nameSpace + ".secondaryTest").toString());
        result += Integer.parseInt(thirdSqlSessionTemplate.selectOne(nameSpace + ".thirdTest").toString());
        return result;
    }

    public List<RawData> readPrimaryRawData(String requestedDate) throws Exception {
        return primarySqlSessionTemplate.selectList(nameSpace + ".readPrimaryRawData", requestedDate);
    }

    public int writeSecondaryRawData(RawDataWrapper rawDataWrapper) throws Exception {
        return secondarySqlSessionTemplate.insert(nameSpace + ".writeSecondaryRawData", rawDataWrapper);
    }

    public List<RawData> read1minPrimaryRawData(String requestedDate) throws Exception {
        return primarySqlSessionTemplate.selectList(nameSpace + ".read1minPrimaryRawData", requestedDate);
    }

    public int writeThirdRawData(RawDataWrapper rawDataWrapper) throws Exception {
        return thirdSqlSessionTemplate.insert(nameSpace + ".writeThirdRawData", rawDataWrapper);
    }
}

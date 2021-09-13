package com.springbatch.tasklet;

import com.springbatch.model.Data;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class JdbcPagingTasklet {

    private final int CHUNK_SIZE = 100;

    private final DataSource dataSource;

    @Autowired
    public JdbcPagingTasklet(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcPagingItemReader<Data> jdbcPagingItemReader() throws Exception {
        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("faccode", 11000);
        return new JdbcPagingItemReaderBuilder<Data>()
                .pageSize(CHUNK_SIZE)
                .fetchSize(CHUNK_SIZE)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Data.class))
                .queryProvider(sumQueryProvider())
                .parameterValues(parameterValues)
                .name("jdbcPaginItemReader")
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Data> jdbcPagingItemWriter() {
        return new JdbcBatchItemWriterBuilder<Data>()
                .dataSource(dataSource)
                .sql("insert into tbl_sum(type,val) values(:type,:val)")
                .beanMapped()
                .build()
                ;
    }


    @Bean
    public PagingQueryProvider sumQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("select idx, type, sum(val) val, :faccode");
        queryProvider.setFromClause("tbl_data");
//        queryProvider.setWhereClause("");
        queryProvider.setGroupClause("group by type");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("idx", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);
        return queryProvider.getObject();
    }
}

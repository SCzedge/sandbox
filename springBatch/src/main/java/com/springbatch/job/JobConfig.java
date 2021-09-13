package com.springbatch.job;

import com.springbatch.model.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class JobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    private final int CHUNK_SIZE = 100;

    @Autowired
    public JobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep1((null)))
                .next(simpleStep2(null))
                .build();
    }

    @Bean
    @JobScope
    public Step simpleStep1(@Value("#{jobParameters[requestedDate]}") String requestedDate) {
        return stepBuilderFactory.get("simpleStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> step1");
                    log.info(">>>>> requestedDate = {}", requestedDate);
                    return RepeatStatus.FINISHED;
                })
                .build();

    }

    @Bean
    @JobScope
    public Step simpleStep2(@Value("#{jobParameters[requestedDate]}") String requestedDate) {
        return stepBuilderFactory.get("simpleStep2")
                .tasklet(simpleTasklet(requestedDate))
                .build();
    }

    @Bean
    @StepScope
    public Tasklet simpleTasklet(@Value("#{jobParameters[requestedDate]}") String requestedDate) {
        return (contribution, chunkContext) -> {
            log.info(">>>>> tasklet");
            log.info(">>>>> requestedDate = {}", requestedDate);
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Job jdbcPagingItemRederJob() throws Exception {
        return jobBuilderFactory.get("jdbcPagingItemRederJob")
                .start(jdbcPagingItemRederStep())
                .build();
    }

    @Bean
    public Step jdbcPagingItemRederStep() throws Exception {
        return stepBuilderFactory.get("jdbcPagingItemRederStep")
                .<Data, Data>chunk(CHUNK_SIZE)
                .reader(jdbcPagingItemReader())
                .writer(jdbcPagingItemWriter())
                .build();
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
    public ItemWriter<Data> jdbcPagingItemWriter() {
        return null;
    }


    @Bean
    public PagingQueryProvider sumQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("select type, sum(val)");
        queryProvider.setFromClause("tbl_data");
//        queryProvider.setWhereClause("");
        queryProvider.setGroupClause("group by type");

//        Map<String, Order> sortKeys = new HashMap<>(1);
//        sortKeys.put("id", Order.ASCENDING);

        return queryProvider.getObject();
    }
}
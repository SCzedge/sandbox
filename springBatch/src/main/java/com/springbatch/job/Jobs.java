package com.springbatch.job;

import com.springbatch.step.JdbcPagingStep;
import com.springbatch.step.SimpleStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Jobs {
    private final JobBuilderFactory jobBuilderFactory;
    private final SimpleStep simpleStep;
    private final JdbcPagingStep jdbcPagingStep;

    @Autowired
    public Jobs(JobBuilderFactory jobBuilderFactory, SimpleStep simpleStep, JdbcPagingStep jdbcPagingStep) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.simpleStep = simpleStep;
        this.jdbcPagingStep = jdbcPagingStep;
    }

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep.simpleStep1((null)))
                .next(simpleStep.simpleStep2(null))
                .build();
    }

    @Bean
    public Job jdbcPagingItemRederJob() throws Exception {
        return jobBuilderFactory.get("jdbcPagingItemReaderJob")
                .start(jdbcPagingStep.jdbcPagingItemReaderStep())
                .build();
    }
}
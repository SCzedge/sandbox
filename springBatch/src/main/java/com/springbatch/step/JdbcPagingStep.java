package com.springbatch.step;

import com.springbatch.model.Data;
import com.springbatch.tasklet.JdbcPagingTasklet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcPagingStep {
    private final int CHUNK_SIZE = 100;

    private final StepBuilderFactory stepBuilderFactory;
    private final JdbcPagingTasklet tasklet;

    @Autowired
    public JdbcPagingStep(StepBuilderFactory stepBuilderFactory, JdbcPagingTasklet jdbcPagingTasklet) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.tasklet = jdbcPagingTasklet;
    }

    @Bean
    public Step jdbcPagingItemReaderStep() throws Exception {
        return stepBuilderFactory.get("jdbcPagingItemRederStep")
                .<Data, Data>chunk(CHUNK_SIZE)
                .reader(tasklet.jdbcPagingItemReader())
                .writer(tasklet.jdbcPagingItemWriter())
                .build();
    }

}

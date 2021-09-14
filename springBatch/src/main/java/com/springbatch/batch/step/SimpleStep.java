package com.springbatch.batch.step;

import com.springbatch.batch.tasklet.SimpleTasklet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SimpleStep {
    private final StepBuilderFactory stepBuilderFactory;
    private final SimpleTasklet simpleTasklet;

    @Autowired
    public SimpleStep(StepBuilderFactory stepBuilderFactory, SimpleTasklet simpleTasklet) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.simpleTasklet = simpleTasklet;
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
                .tasklet(simpleTasklet.tasklet(requestedDate))
                .build();
    }

}

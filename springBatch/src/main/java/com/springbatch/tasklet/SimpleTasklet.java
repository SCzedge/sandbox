package com.springbatch.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SimpleTasklet {

    @Bean
    @StepScope
    public Tasklet tasklet(@Value("#{jobParameters[requestedDate]}") String requestedDate) {
        return (contribution, chunkContext) -> {
            log.info(">>>>> tasklet");
            log.info(">>>>> requestedDate = {}", requestedDate);
            return RepeatStatus.FINISHED;
        };
    }
}

package com.springbatch.service;

import com.springbatch.batch.job.Jobs;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SimpleService {

    private final JobLauncher jobLauncher;
    private final Jobs job;

    @Autowired
    public SimpleService(JobLauncher jobLauncher, Jobs job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }


    public JobExecution doSimpleJob(LocalDateTime requestedDate)throws Exception{

        if(requestedDate == null){
            requestedDate = LocalDateTime.now();
        }

        JobExecution je =  jobLauncher.run(
                job.simpleJob(),
                new JobParametersBuilder()
                        .addString("requestedDate",requestedDate.toString() )
                        .toJobParameters()
        );
        return je;
    }
}

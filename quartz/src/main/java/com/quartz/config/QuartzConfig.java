package com.quartz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;

public class QuartzConfig {
    @Autowired
    private TriggersListener triggersListener;

    @Autowired
    private JobsListener jobsListener;

    @Autowired
    private QuartzProperties quartzProperties;
    AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
}

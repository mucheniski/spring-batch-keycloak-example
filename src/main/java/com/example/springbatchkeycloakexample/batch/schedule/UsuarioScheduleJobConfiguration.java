package com.example.springbatchkeycloakexample.batch.schedule;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioScheduleJobConfiguration {

    @Bean
    public JobDetail quartzJobDetail() {
        return JobBuilder.newJob(UsuarioBatchScheduleJob.class).storeDurably().build();
    }

    @Bean
    public Trigger jobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(5)
                .withRepeatCount(4);
        return TriggerBuilder
                .newTrigger()
                .forJob(quartzJobDetail())
                .withSchedule(scheduleBuilder)
                .build();
    }

}

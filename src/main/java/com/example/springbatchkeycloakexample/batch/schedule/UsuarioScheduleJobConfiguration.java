package com.example.springbatchkeycloakexample.batch.schedule;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class UsuarioScheduleJobConfiguration {

    @Bean
    public JobDetail quartzJobDetail() {
        return JobBuilder
                .newJob(UsuarioBatchScheduleJob.class)
                .storeDurably()
                .build();
    }

    /**
     *
     * O gatilho de execução configurado foi a cada 5 segundos,
     * no máximo 4 execuções repetidas.
     * Esse intervalo poderia ser customizado para qualquer periodicidade desejada,
     * o SimpleScheduleBuilder do Quartz possui vários métodos para essa finalidade.
     * https://www.quartz-scheduler.org/api/2.1.7/org/quartz/SimpleScheduleBuilder.html
     */
    @Bean
    public Trigger jobTrigger() throws ParseException {

        Date dataInicioAgendamento = convertUTCToDate("30/03/2023 13:50:00");

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(5) // intervalo em Segundos
//                .withIntervalInHours(24) // intervalo em horas
//                .withRepeatCount(4); // Quantidade de repetições
                .repeatForever();

        return TriggerBuilder
                .newTrigger()
                .startAt(dataInicioAgendamento)
                .forJob(quartzJobDetail())
                .withSchedule(scheduleBuilder)
                .build();
    }

    private static Date convertUTCToDate(String value) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3"));

        return dateFormat.parse(value);
    }

}

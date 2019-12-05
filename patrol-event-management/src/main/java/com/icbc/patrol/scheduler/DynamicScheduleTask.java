package com.icbc.patrol.scheduler;

import com.icbc.patrol.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/29 14:30
 * @description : 动态定时任务
 */
//@Component
//@Configuration
//@EnableScheduling
public class DynamicScheduleTask implements SchedulingConfigurer {

    /**
     * 执行定时任务
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                // 1、添加任务内容
                ()-> System.out.println("执行动态定时任务："+ LocalDateTime.now().toLocalTime()),
                // 2、设置执行周期
                triggerContext -> {
                    String cronExpression = "0/5 * * * * ?";
                    return new CronTrigger(cronExpression).nextExecutionTime(triggerContext);
                });
    }


}

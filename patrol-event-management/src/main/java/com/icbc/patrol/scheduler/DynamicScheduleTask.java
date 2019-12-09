package com.icbc.patrol.scheduler;

import com.icbc.patrol.mapper.CronMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

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

    //    @Mapper
//    public interface CronMapper {
//        @Select("select cron from cron limit 1")
//        String getCron();
//    }
//
    @Autowired
    private CronMapper cronMapper;


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
//                    String cronExpression = "0/5 * * * * ?";
                    String cronExpression = cronMapper.getCron();
                    if (StringUtils.isEmpty(cronExpression)) {
                        // TODO
                    }
                    return new CronTrigger(cronExpression).nextExecutionTime(triggerContext);
                });
    }


}

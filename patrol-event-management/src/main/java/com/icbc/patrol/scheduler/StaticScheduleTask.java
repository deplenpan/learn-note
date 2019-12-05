package com.icbc.patrol.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/29 14:08
 * @description : 静态定时任务
 */
//@Component
//@Configuration
//@EnableScheduling
public class StaticScheduleTask {

    /**
     * 每隔5秒执行一次任务
     */
//    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTask(){
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }

}

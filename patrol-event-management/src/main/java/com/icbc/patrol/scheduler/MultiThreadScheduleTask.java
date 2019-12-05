package com.icbc.patrol.scheduler;

import com.alibaba.fastjson.JSON;
import com.icbc.common.util.HttpClientUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/29 14:46
 * @description : 通过注解@EnableScheduling设定多线程定时任务
 */
//@Component
//@EnableScheduling
//@EnableAsync
public class MultiThreadScheduleTask {

//    @Async
//    @Scheduled(fixedDelay = 1000)
    public void schedulingInsertEvent() {
        String postUrl = "http://localhost:10000/patrol/event/add";
//        String postUrl = "http://192.168.1.7:10000/patrol/event/add";
//        String postUrl = "http://192.168.0.103:10000/patrol/event/add";
//        String jsonData = "{\"eventNum\":\"201909181438002\",\"equipmentId\":\"0101\",\"location\":\"6号楼西3-24\",\"problem\":\"电池内阻过低设备故障告警\",\"cause\":\"停止工作\",\"solution\":\"待解决\",\"isCompleted\":\"1\"}";
        Map<String, Object> map = new HashMap<>();
        map.put("eventNum", getEventNum());
        map.put("equipmentId", getEquipmentId());
        map.put("location", getLocation());
        map.put("model", getModel());
        map.put("roomNum", getRoomNum());
        map.put("problem", getProblem());
        map.put("cause", getCause());
        map.put("isCompleted", getStatus());
        map.put("isDeleted", getIsDeleted());
        map.put("version", getVersion());
        String jsonData = JSON.toJSONString(map);
        System.out.println("jsonData = " + jsonData);
        HttpClientUtil.doPostJson(postUrl, jsonData);
    }

    public Integer getIsDeleted() {
        int[] isDeleted = new int[]{0, 1};
        return isDeleted[1];
    }

    public String getVersion() {
        return "V1.0";
    }

    public String getStatus() {
        String[] status = new String[]{"0", "1"};
        return status[1];
    }

    public String getLocation() {
        String[] locations = new String[]{"6号楼西3-24", "5号楼东2-24", "4号楼西1-24", "3号楼西3-22", "2号楼西4-21", "1号楼西1-20"};
        return locations[new Random().nextInt(locations.length)];
    }

    public String getEventNum() {
        int eventNum = new Random().nextInt(10000);
        return String.valueOf(eventNum);
    }

    public String getRoomNum() {
        int roomNum = new Random().nextInt(1000);
        return String.valueOf(roomNum);
    }

    public String getModel() {
        String[] models = new String[]{"模型A", "模型B", "模型C", "模型D", "模型E", "模型F"};
        return models[new Random().nextInt(models.length)];
    }

    public String getEquipmentId() {
        int equipmentId = new Random().nextInt(10000);
        return String.valueOf(equipmentId);
    }

    public String getSolution() {
        String[] solutions = new String[]{"已解决", "未解决"};
        return solutions[new Random().nextInt(solutions.length)];
    }


    public String getCause() {
        return "停止工作";
    }

    public String getProblem() {
        String[] causes = new String[]{"电流过大告警", "电压过大告警", "设备温度过高报警", "电池故障告警"};
        return causes[new Random().nextInt(causes.length)];
    }

//    @Async
//    @Scheduled(fixedDelay = 10000)
//    public void schedulingQuery(){
//        System.out.println("第二个定时任务开始："+LocalDateTime.now().toLocalTime()+"\r\n线程："+Thread.currentThread().getName());
//        System.out.println();
//    }

}

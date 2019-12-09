package com.icbc.patrol.service;

import com.icbc.patrol.model.Event;

import java.util.List;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/24 14:29
 */
public interface EventService {


    /**
     * 根据事件单编号查询事件单
     *
     * @param eventNum
     * @return event data
     */
    Event queryByEventNum(String eventNum);

    /**
     * 根据指定时间段查询事件数据
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 事件集合
     */
    List<Event> queryPeriodEventList(String startTime, String endTime);

    /**
     * 新增事件单
     *
     * @param event event object
     * @return insert result
     */
    int addEvent(Event event);

    /**
     * 修改事件单
     *
     * @param event event object
     * @return update result
     */
    int updateByEventNum(Event event);

    /**
     * 根据事件单编号删除事件单
     *
     * @param eventNum 事件单编号
     * @return delete result
     */
    int deleteByEventNum(String eventNum);

    int logicDeleteByEventNum(Event event);
}

package com.icbc.patrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icbc.patrol.model.Event;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/24 14:29
 */
public interface EventMapper extends BaseMapper<Event> {

    /**
     * 根据事件单编号查询事件单
     *
     * @param eventNum
     * @return event data
     */
    Event queryByEventNum(@Param("eventNum") String eventNum);


    /**
     * 根据指定时间段查询事件数据
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 事件集合
     */
    List<Event> queryPeriodEventList(@Param("startTime") String startTime,@Param("endTime") String endTime);

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
    int deleteByEventNum(@Param("eventNum") String eventNum);


}

package com.icbc.patrol.service.impl;

import com.icbc.patrol.mapper.EventMapper;
import com.icbc.patrol.model.Event;
import com.icbc.patrol.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/24 14:29
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;


    @Override
    public int deleteByEventNum(String eventNum) {
        return eventMapper.deleteByEventNum(eventNum);
    }

    @Override
    public Event queryByEventNum(String eventNum) {
        return eventMapper.queryByEventNum(eventNum);
    }

    @Override
    public int addEvent(Event event) {
        return eventMapper.addEvent(event);
    }

    @Override
    public int updateByEventNum(Event event) {
        return eventMapper.updateByEventNum(event);
    }

    @Override
    public List<Event> queryPeriodEventList(String startTime, String endTime) {
        return eventMapper.queryPeriodEventList(startTime, endTime);
    }
}

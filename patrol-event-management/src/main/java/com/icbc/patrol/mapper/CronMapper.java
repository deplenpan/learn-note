package com.icbc.patrol.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/12/9 13:58
 */
public interface CronMapper {

    @Select("select cron from cron limit 1")
    String getCron();

}

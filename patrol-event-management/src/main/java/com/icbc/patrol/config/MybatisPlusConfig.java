package com.icbc.patrol.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/15 15:25
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.icbc.patrol.mapper")
public class MybatisPlusConfig {
}

package com.icbc.patrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/15 14:44
 */
//@ServletComponentScan("com.icbc.patrol.filter")
@SpringBootApplication
public class PatrolApplication{

    public static void main(String[] args) {
        SpringApplication.run(PatrolApplication.class, args);
    }

}

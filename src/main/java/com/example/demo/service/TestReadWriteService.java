package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxi
 * @Date: 2019/3/15 13:40
 * @Description:
 */
@Service
public class TestReadWriteService {

    private static Logger logger = LoggerFactory.getLogger(TestReadWriteService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public int update(String name, int id) throws InterruptedException {

        Map<String,Object> map = jdbcTemplate.queryForMap("select * from test where id = ?",new Object[]{id});

        logger.info(Thread.currentThread().getName()+" map:"+map.get("id")+"|"+map.get("name"));

        int i = jdbcTemplate.update("update test set `name` = ? where id = ?",new Object[]{name,id});

        logger.info(Thread.currentThread().getName()+" result:"+i);

        Map<String,Object> map1 = jdbcTemplate.queryForMap("select * from test where id = ?",new Object[]{id});

        logger.info(Thread.currentThread().getName()+" map:"+map1.get("id")+"|"+map1.get("name"));

        return i;
    }

}

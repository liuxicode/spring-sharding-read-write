package com.example.demo.Controller;

import com.example.demo.service.TestReadWriteService;
import io.shardingjdbc.core.api.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxi
 * @Date: 2019/3/15 11:11
 * @Description:
 */
@Controller
@RequestMapping("testReadWrite")
public class TestReadWriteController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("findAll")
    @ResponseBody
    public List<Map<String,Object>> findAll(){

        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from test");

        return list;
    }

    @RequestMapping("add")
    @ResponseBody
    public int add(@RequestParam("name") String name){

        return jdbcTemplate.update("insert into test(`name`) values(?)",new Object[]{name});
    }

    @RequestMapping("findMaster")
    @ResponseBody
    public List<Map<String,Object>> findMaster(){

        // 强制路由主库
        HintManager.getInstance().setMasterRouteOnly();

        return jdbcTemplate.queryForList("select * from test");
    }

    @Autowired
    private TestReadWriteService testReadWriteService;

    @RequestMapping("update")
    @ResponseBody
    public int update(@RequestParam("id") int id,
                      @RequestParam("name") String name) throws InterruptedException {

        // 强制路由主库
        //HintManager.getInstance().setMasterRouteOnly();

        return testReadWriteService.update(name,id);
    }
}

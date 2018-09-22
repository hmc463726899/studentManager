package com.stu.user.dao;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */
@Component
public interface AFLDao {

    List<Map<String,Object>>getStuAFL(int stuId, int begin, int rows);

    int getStuAFLCount(int stuId);

    void addStuAFL(String stuId,String reason,String start_time,String end_time);
}

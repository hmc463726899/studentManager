package com.pim.admin.dao;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */

/**
 * Created by chenkexuan on 2017/4/27.
 */
@Component
public interface AFLManagerDao {


    List<Map<String,Object>> getAllStuAFL(String keyword, int begin, int rows);

    int getTotalAFL(String keyword);

    void delStuAFL(int stuId,int aflId);

    void approveStuAFL(int stuId,int aflId);




}

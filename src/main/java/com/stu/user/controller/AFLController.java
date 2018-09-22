package com.stu.user.controller;

import com.stu.user.service.AFLService;
import com.stu.user.service.RewardPunishService;
import com.stu.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * edited and modified by dengxionghui on 2018/6/21
 */

@Controller
@RequestMapping(value = "/stu/askforleave",method = {RequestMethod.POST,RequestMethod.GET})
public class AFLController {
    @Autowired
    private AFLService aflService;

    /**
     * 查找学生请假记录
     * @param stuId
     * @param page
     * @param rows
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getStuAFL.do")
    public void getStuAFL(int stuId, int page, int rows, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = aflService.getStuAFL(stuId,page,rows);
        ResponseUtil.returnJson(result,response);
    }

    @RequestMapping("/addStuAFL.do")
    public void addStuAFL(String stuId,String reason,String start_time,String end_time,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result=aflService.addStuAFL(stuId,reason,start_time,end_time);
        ResponseUtil.returnJson(result,response);
    }
}
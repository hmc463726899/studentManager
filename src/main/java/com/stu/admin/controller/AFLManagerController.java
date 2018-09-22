package com.stu.admin.controller;

import com.stu.admin.dao.AFLManagerDao;
import com.stu.admin.service.AFLManagerService;
import com.stu.admin.service.CourseManagerService;
import com.stu.user.service.AFLService;
import com.stu.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */


@Controller
@RequestMapping(value = "/stu/aflManager/",method = {RequestMethod.POST,RequestMethod.GET})
public class AFLManagerController {
    @Autowired
    private AFLManagerService aflManagerService;

    /**
     * 获取全部请假信息
     * @param keywords
     * @param page
     * @param rows
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getAllStuAFL.do")
    public void getAllStuAFL(String keywords,int page,int rows ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = aflManagerService.getAllStuAFL(keywords,page,rows);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 删除请假记录
     * @param stuId
     * @param request
     * @param response
     * @throws IOException
     *
     **/

    @RequestMapping("/delStuAFL.do")
    public void delCourse(int stuId,int aflId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = aflManagerService.delStuAFL(stuId,aflId);
        result.put("msg","删除操作成功");
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 批准学生请假
     * @param stuId
     * @param aflId
     * @param request
     * @param response
     * @throws IOException
     */

    @RequestMapping("/approveStuAFL.do")
    public void addStuGrade(int stuId,int aflId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = aflManagerService.approveStuAFL(stuId,aflId);
        result.put("msg","批准操作成功");
        ResponseUtil.returnJson(result,response);
    }
}

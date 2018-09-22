package com.stu.user.controller;

import com.stu.user.service.StuCourseService;
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
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */
@Controller
@RequestMapping(value = "/stu/stuCourse", method = {RequestMethod.POST,RequestMethod.GET})
public class StuCourseController {
    @Autowired
    private StuCourseService stuCourseService;

    /**
     * 获取的全部课程
     * @param stuId
     * @param keywords
     * @param page
     * @param rows
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getAllCourse.do")
    public void getAllCourse(Integer stuId,String keywords,Integer page,Integer rows,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = stuCourseService.getAllCourse(stuId,keywords,page,rows);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 获取选课开通信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getMsg.do")
    public void getMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = stuCourseService.getMsg();
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 学生选课
     * @param stuId
     * @param courseId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getCourse.do")
    public void getCourse(Integer stuId,Integer courseId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = stuCourseService.getCourse(stuId,courseId);
        ResponseUtil.returnJson(result,response);
    }
}

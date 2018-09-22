package com.stu.admin.controller;

import com.stu.admin.service.CourseManagerService;
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
@RequestMapping(value = "/stu/courseManager/",method = {RequestMethod.POST,RequestMethod.GET})
public class CourseManagerController {
    @Autowired
    private CourseManagerService courseManagerService;

    /**
     * 获取全部课程信息
     * @param keywords
     * @param page
     * @param rows
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getAllcourse.do")
    public void getAllcourse(String keywords,int page,int rows ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = courseManagerService.getAllcourse(keywords,page,rows);
        ResponseUtil.returnJson(result,response);
    }


    /**
     * 获取课程分类下拉列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getCombox.do")
    public void getCombox(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Map<String,Object>> combox = new ArrayList<Map<String,Object>>();
        combox = courseManagerService.getCombox();
        ResponseUtil.returnJson(combox,response);
    }

    /**
     * 添加课程
     * @param name
     * @param type
     * @param score
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addCourse.do")
    public void addCourse(String name,String type,String score,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = courseManagerService.addCourse(name,type,score);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 通过课程id查找选择该课程的学生
     * @param id
     * @param page
     * @param rows
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getStuByCourse.do")
    public void getStuByCourse(int id,int page,int rows,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = courseManagerService.getStuByCourse(id,page,rows);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 添加课程类型
     * @param typeName
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addCourseType.do")
    public void addCourseType(String typeName,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = courseManagerService.addCourseType(typeName);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 删除课程
     * @param id
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/delCourse.do")
    public void delCourse(int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = courseManagerService.delCourse(id);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 获取学生下拉列表
     * @param courseId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getStudentCombox.do")
    public void getStudentCombox(int courseId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Map<String,Object>> combox = new ArrayList<Map<String,Object>>();
        combox = courseManagerService.getStudentCombox(courseId);
        ResponseUtil.returnJson(combox,response);
    }

    /**
     * 学生选课
     * @param courseId
     * @param stuId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addStuToCourse.do")
    public void addStuToCourse(int courseId,int stuId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = courseManagerService.addStuToCourse(courseId,stuId);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 获得选课学生datagrid
     * @param keywords
     * @param courseId
     * @param page
     * @param rows
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getStuGradeGrid.do")
    public void getStuGradeGrid(String keywords,int courseId,int page,int rows,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = courseManagerService.getStuGradeGrid(keywords,courseId,page,rows);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 录入学生成绩
     * @param stuId
     * @param courseId
     * @param score
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addStuGrade.do")
    public void addStuGrade(int stuId,int courseId,String score,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = courseManagerService.addStuGrade(stuId,courseId,score);
        ResponseUtil.returnJson(result,response);
    }
}

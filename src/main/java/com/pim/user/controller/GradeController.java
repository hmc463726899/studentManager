package com.pim.user.controller;

import com.pim.user.service.GradeService;
import com.pim.util.ResponseUtil;
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
@RequestMapping(value = "/stu/grade",method = {RequestMethod.POST,RequestMethod.GET})
public class GradeController {
    @Autowired
    private GradeService gradeService;

    /**
     * 获得学生成绩
     * @param stuId
     * @param page
     * @param rows
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getGrade.do")
    public void getGrade(int stuId, int page, int rows, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = gradeService.getGrade(stuId,page,rows);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 计算学生绩点
     * @param stuId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getJD.do")
    public void getJD(int stuId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = gradeService.getJD(stuId);
        ResponseUtil.returnJson(result,response);
    }
}

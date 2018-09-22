package com.stu.user.controller;

import com.stu.user.service.StudentService;
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
@RequestMapping(value = "/stu/student",method = {RequestMethod.POST,RequestMethod.GET})
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 获取学生信息
     * @param stuId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getStuMsg.do")
    public void getStuMsg(int stuId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = studentService.getStuMsg(stuId);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 修改学生信息
     * @param stuId
     * @param idcard
     * @param sex
     * @param phone
     * @param qq
     * @param email
     * @param address
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/updateMsg.do")
    public void updateMsg(int stuId,String idcard,String sex,String phone,String qq,String email,String address, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = studentService.updateMsg(stuId,idcard,sex,phone,qq,email,address);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 修改密码
     * @param stuId
     * @param username
     * @param password
     * @param repassword
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/resetAccount.do")
    public void resetAccount(int stuId,String username,String password,String repassword, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        request.getSession().removeAttribute("defaultPass");
        result = studentService.resetAccount(stuId,username,password,repassword);
        ResponseUtil.returnJson(result,response);
    }
}

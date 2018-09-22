package com.stu.user.controller;

import com.stu.user.service.UserService;
import com.stu.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
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
@RequestMapping(value = "/stu/user",method = {RequestMethod.POST,RequestMethod.GET})
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登陆
     * @param role
     * @param username
     * @param password
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/userLogin.do")
    public void userLogin(String role , String username, String password , HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = userService.userLogin(request,role,username,password);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 用户注销
     */
    @RequestMapping("/userLogout.do")
    public void userLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.UserLogout(request,response);
    }

    /**
     * 管理员注销
     */
    @RequestMapping("/adminLogout.do")
    public void adminLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.AdminLogout(request,response);
    }

    /**
     * 管理员密码修改
     */
    @RequestMapping("/updateAdminPassword.do")
    public void updateAdminPassword(String adminId,String newPassword,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        userService.updateAdminPassword(adminId,newPassword);
        result.put("msg","密码修改成功! 请使用新密码登陆!");
        result.put("code",true);
        ResponseUtil.returnJson(result,response);
    }

}

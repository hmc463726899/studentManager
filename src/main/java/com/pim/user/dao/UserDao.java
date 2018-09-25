package com.pim.user.dao;

import org.springframework.stereotype.Component;


import java.util.Map;

/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */
@Component
public interface UserDao {

    Map<String,Object> adminLogin(String username, String password);

    String getAdminName(String username); //根据登录名返回用户名

    Map<String,Object> stuLogin(String username);

    int studentLogin(String username, String password);

    Map<String,Object> studentLogin1(String username, String password);

    String getUsername(int stuId);

    int UserSignUp( String username, String password, String ID, String phone, String gender);

    void updateAdminPassword(String adminId,String newPassword);
}

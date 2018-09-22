package com.stu.user.controller;


import com.stu.user.service.DormitoryService;
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
 * edited and modified by dengxionghui on 2018/6/21
 */

@Controller
@RequestMapping(value = "/stu/dormitory",method = {RequestMethod.POST,RequestMethod.GET})
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;


    /**
     * 获取学生的住宿信息
     * @param stuId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getStuDormitoryInfo.do")
    public void getStuDormitoryInfo(int stuId , HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = dormitoryService.getStuDormitoryInfo(stuId);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 获取宿舍园区列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getdormZoneCombox.do",method = {RequestMethod.POST,RequestMethod.GET})
    public void getStudentCombox(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Map<String,Object>> data = new ArrayList<>();
        data = dormitoryService.getdormZoneCombox();
        ResponseUtil.returnJson(data, response);
    }


    /**
     * 更新学生的住宿信息
     * @param stuId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/updateStuDormitoryInfo.do")
    public void updateStuDormitoryInfo(String stuId, String zone_id, String building, String room , HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = dormitoryService.updateStuDormitoryInfo(stuId,zone_id,building,room);
        result.put("msg","更新信息成功!");
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 添加住宿信息
     * @param stuId
     * @param zone_id
     * @param building
     * @param room
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addStuDormitoryInfo.do")
    public void addStuDormitoryInfo(String stuId, String zone_id, String building, String room , HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = dormitoryService.addStuDormitoryInfo(stuId,zone_id,building,room);
        result.put("msg","添加信息成功!");
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 删除学生住宿信息
     * @param stuId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/delStuDormitoryInfo.do")
    public void addStuDormitoryInfo(String stuId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = dormitoryService.delStuDormitoryInfo(stuId);
        result.put("msg","删除信息成功!");
        ResponseUtil.returnJson(result,response);
    }


    /**
     * 获取全部住宿信息
     * @param keywords
     * @param page
     * @param rows
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getAllStuDormitory.do")
    public void getAllStuAFL(String keywords,int page,int rows ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = dormitoryService.getAllStuDormitory(keywords,page,rows);
        ResponseUtil.returnJson(result,response);
    }

    /**
     * 添加园区
     * @param zoneName
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addDormiZone.do")
    public void addDormiZone(String zoneName,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = dormitoryService.addDormiZone(zoneName);
        ResponseUtil.returnJson(result,response);
    }

    @RequestMapping("/delDormiZone.do")
    public void delDormiZone(String zoneId,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String,Object>();
        result = dormitoryService.delDormiZone(zoneId);
        ResponseUtil.returnJson(result,response);
    }
}
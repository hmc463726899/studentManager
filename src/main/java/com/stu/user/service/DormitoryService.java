package com.stu.user.service;

import com.stu.user.dao.DormitoryDao;
import com.stu.user.dao.GradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DormitoryService {
    @Autowired
    private DormitoryDao dormitoryDao;

    /**
     * 获取学生住宿信息 按id
     * @param stuId
     * @return
     */
    public Map<String,Object>getStuDormitoryInfo(int stuId)
    {
        Map<String,Object> data = new HashMap<String,Object>();
        Map<String,Object> result = new HashMap<String,Object>();
        data=dormitoryDao.getStuDormitoryInfo(stuId);
        result.put("data",data);
        return result;
    }

    /**
     * 获取宿舍园区列表
     * @return
     */
    public List<Map<String,Object>> getdormZoneCombox() {

        List<Map<String,Object>> dormZoneCombox = new ArrayList<>();
        dormZoneCombox = dormitoryDao.getdormZoneCombox();

        return dormZoneCombox;
    }

    /**
     * 更新学生住宿信息
     * @param stuId
     * @param zone_id
     * @param building
     * @param room
     * @return
     */
    public Map<String,Object>updateStuDormitoryInfo(String stuId,String zone_id,String building,String room)
    {
        Map<String,Object> result = new HashMap<String,Object>();
        dormitoryDao.updateStuDormitoryInfo(stuId,zone_id,building,room);
        result.put("code",true);
        return result;
    }

    /**
     * 添加学生住宿信息
     * @param stuId
     * @param zone_id
     * @param building
     * @param room
     * @return
     */
    public Map<String,Object>addStuDormitoryInfo(String stuId,String zone_id,String building,String room)
    {
        Map<String,Object> result = new HashMap<String,Object>();
        dormitoryDao.addStuDormitoryInfo(stuId,zone_id,building,room);
        result.put("code",true);
        return result;
    }

    /**
     * 删除学生住宿信息
     * @param stuId
     * @return
     */
    public Map<String,Object>delStuDormitoryInfo(String stuId)
    {
        Map<String,Object> result = new HashMap<String,Object>();
        dormitoryDao.delStuDormitoryInfo(stuId);
        result.put("code",true);
        return result;
    }

    /**
     * 获取所有学生住宿信息分页、关键词
     * @param keywords
     * @param page
     * @param rows
     * @return
     */
    public Map<String,Object> getAllStuDormitory(String keywords, int page, int rows) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<>();
        if(keywords==null){
            keywords="";
        }
        int begin = (page-1)*rows;
        int total = dormitoryDao.getTotalStuDormitory(keywords);
        data = dormitoryDao.getAllStuDormitory(keywords,begin,rows);
        result.put("total",total);
        result.put("rows",data);
        return result;
    }

    /**
     * 添加园区
     * @param zoneName
     * @return
     */
    public Map<String,Object> addDormiZone(String zoneName) {
        Map<String,Object> result = new HashMap<>();
        dormitoryDao.addDormiZone(zoneName);
        result.put("code",true);
        result.put("msg","添加园区成功!");
        return result;
    }

    /**
     * 删除园区
     * @param zoneId
     * @return
     */
    public Map<String,Object> delDormiZone(String zoneId) {
        Map<String,Object> result = new HashMap<>();
        dormitoryDao.delDormiZone(zoneId);
        result.put("code",true);
        result.put("msg","删除园区成功!");
        return result;
    }
}


package com.pim.user.dao;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */
@Component
public interface DormitoryDao {

    int getTotalStuDormitory(String keyword);

    List<Map<String,Object>> getAllStuDormitory(String keyword, int begin, int rows);

    Map<String, Object> getStuDormitoryInfo(int stuId);

    List<Map<String, Object>> getdormZoneCombox();

    void updateStuDormitoryInfo(String stuId, String zone_id, String building, String room);

    void addStuDormitoryInfo(String stuId, String zone_id, String building, String room);

    void delStuDormitoryInfo(String stuId);

    void addDormiZone(String zoneName);

    void delDormiZone(String zoneId);
}



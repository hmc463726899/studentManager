package com.stu.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */

@Component
public interface StuManagerDao {

	int getCountStu(String keywords);

	List<Map<String,Object>> getAllStu(String keywords, int begin, int rows);

	void addStudent(String addName, String addIdcard, String addSex);

	void delStudent(String id);

	void delMoreStudent(List<String> idList);

	Map<String,Object> getStudent(String id);

	void updateStudent(String updateName, String updateIdcard, String updateSex, String updatePhone, String updateQQ, String updateEmail, String updateAddress,String id);

	void delStudentInCourse(String id);

	int getSwitch(String name);

	void setSwitch(String name, String num);

	List<Map<String,Object>> getstudentCombox();

	List<Map<String,Object>> getStudentReward(int id);

	List<Map<String,Object>> getStudentPunish(int id);

	void addreward(int id, String content,String time);

	List<Map<String,Object>> getRewardCombobox(int id);

	void delReword(int id);

	void addPunish(int id, String content,String time);

	List<Map<String,Object>> getPunishCombobox(int id);

	void delPunish(int id);

	int getStuAllQualityWithKey(int id,String keywords);

	int getStuAllQuality(int id);

	List<Map<String,Object>> getQualityData(int id,int begin, int rows,String keywords);

	float getCountQuality(int id);

	void delQuality(int id);

	void addQuality(int id, String name, float score,String time);

	int checkStuDuplicate(String stuName,String stuIdCard);
}

package com.stu.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stu.admin.dao.StuManagerDao;

/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */
@Service
@Transactional
public class StuManagerService {
	@Autowired
	private StuManagerDao stuManagerDao;


    /**
     * 获取开关状态
     * @return
     */
    public Map<String,Object> getSwitch(String name) {
        Map<String,Object> result = new HashMap<>();
        int data = stuManagerDao.getSwitch(name);
        result.put("code",true);
        result.put("data",data);
        return result;
    }

    /**
     * 修改switch开关状态
     * @param name
     * @param state
     * @return
     */
    public Map<String,Object> setSwitch(String name, boolean state) {
        Map<String,Object> result = new HashMap<>();
        String num = "0";
        if(state==true){
            num="1";
        }
        stuManagerDao.setSwitch(name,num);
        result.put("code",true);
        return result;
    }

	/**
	 * 获取全部学生信息
	 * @return
	 */
	public Map<String, Object> getAllstu(String keywords,int page,int rows) {
		Map<String,Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		if(keywords==null || keywords=="null"){
			keywords = "";
		}
		int begin = (page-1)*rows;
		int total = stuManagerDao.getCountStu(keywords);
		data = stuManagerDao.getAllStu(keywords,begin,rows);
		result.put("total",total);
		result.put("rows",data);
		return result;
	}

	/**
	 * 添加学生
	 * @param addName
	 * @param addIdcard
	 * @param addSex
	 * @return
	 */
	public Map<String,Object> addStudent(String addName,String addIdcard, String addSex) {
		Map<String,Object> result = new HashMap<String,Object>();
		if(addName==null){
			result.put("code",false);
			result.put("msg","学生姓名不能为空!");
			return result;
		}
		if(addIdcard==null){
			addIdcard = "";
		}
		stuManagerDao.addStudent(addName,addIdcard,addSex);
		result.put("code",true);
		result.put("msg","添加成功!");
		return result;
	}

	/**
	 * 删除学生信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> delStudent(String id) {
		Map<String,Object> result = new HashMap<String,Object>();
		//删除学生表中数据
		stuManagerDao.delStudent(id);
		//删除学生选课记录
		stuManagerDao.delStudentInCourse(id);
		result.put("code",true);
		result.put("msg","删除成功!");
		return result;
	}

	/**
	 * 多选删除
	 * @param ids
	 * @return
	 */
	public Map<String,Object> delMoreStudent(String[] ids) {
		Map<String,Object> result = new HashMap<String,Object>();
		if(ids==null){
			result.put("code", false);
			result.put("msg", "没有选择行!");
			return result;
		}
		List<String> idList = new ArrayList<>();
		for(String str:ids){
			idList.add(str);
		}
		stuManagerDao.delMoreStudent(idList);
		result.put("code", true);
		result.put("msg", "删除成功!");
		return result;
	}

    /**
     * 根据学生ID查找学生
     * @param id
     * @return
     */
    public Map<String,Object> getStudent(String id) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> data = new HashMap<String,Object>();

        if(id!=null && id!="null" && id!=""){
            data = stuManagerDao.getStudent(id);
            result.put("code", true);
            result.put("data", data);
            return result;
        }
        result.put("code", false);
        result.put("msg", "查找失败");
        return result;
    }

    /**
     * 修改学生信息
     * @param updateName
     * @param updateIdcard
     * @param updateSex
     * @param updatePhone
     * @param updateQQ
     * @param updateEmail
     * @param updateAddress
     * @return
     */
    public Map<String,Object> updateStudent(String updateName, String updateIdcard, String updateSex, String updatePhone, String updateQQ, String updateEmail, String updateAddress,String id) {
        Map<String,Object> result = new HashMap<String,Object>();
        if(updateName==null){
            updateName="";
        }
        if(updateIdcard==null){
            updateIdcard="";
        }
        if(updateSex==null){
            updateSex="";
        }
        if(updatePhone==null){
            updatePhone="";
        }
        if(updateQQ==null){
            updateQQ="";
        }
        if(updateEmail==null){
            updateEmail="";
        }
        if(updateAddress==null){
            updateAddress="";
        }
        stuManagerDao.updateStudent(updateName,updateIdcard,updateSex,updatePhone,updateQQ,updateEmail,updateAddress,id);
        result.put("code",true);
        result.put("msg","修改成功");
        return result;
    }

	/**
	 * 获取学生下拉列表
	 * @return
	 */
	public List<Map<String,Object>> getStudentCombox() {
		List<Map<String,Object>> studentCombox = new ArrayList<>();
		studentCombox = stuManagerDao.getstudentCombox();
		return studentCombox;
	}

	/**
	 * 通过学生id查找学生奖惩信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> getStudentReward(int id) {
		Map<String,Object> result = new HashMap<>();
		List<Map<String,Object>> reward = new ArrayList<>();
		List<Map<String,Object>> punish = new ArrayList<>();
		//查找学生奖励信息
		reward = stuManagerDao.getStudentReward(id);
		//查找学生惩罚信息
		punish = stuManagerDao.getStudentPunish(id);
		result.put("reward",reward);
		result.put("punish",punish);
		return result;
	}

	/**
	 * 添加奖励信息
	 * @param id
	 * @param content
	 * @return
	 */
	public Map<String,Object> addreward(int id, String content,String time) {
		Map<String,Object> result = new HashMap<>();
		if(content==null ||"".equals(content.trim()) || content==""){
			result.put("code",false);
			result.put("msg","奖励内容不能为空!");
			return result;
		}
		stuManagerDao.addreward(id,content,time);
		result.put("code",true);
		result.put("msg","添加成功!");
		return result;
	}

	/**
	 * 获得奖励下拉列表
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getRewardCombobox(int id) {
		List<Map<String,Object>> rewardCombox = new ArrayList<>();
		rewardCombox = stuManagerDao.getRewardCombobox(id);
		return rewardCombox;
	}

	/**
	 * 删除奖励信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> delReword(int id) {
		Map<String,Object> result = new HashMap<>();
		if(id == 0){
			result.put("code",false);
			result.put("msg","删除失败！没找到对应奖励");
			return result;
		}
		stuManagerDao.delReword(id);
		result.put("code",true);
		result.put("msg","删除成功！");
		return result;
	}

	/**
	 * 新增惩罚
	 * @param id
	 * @param content
	 * @return
	 */
	public Map<String,Object> addPunish(int id, String content,String time) {
		Map<String,Object> result = new HashMap<>();
		if(content==""|content==null||"".equals(content.trim())){
			result.put("code",false);
			result.put("msg","惩罚内容不能为空!");
			return result;
		}
		stuManagerDao.addPunish(id,content,time);
		result.put("code",true);
		result.put("msg","添加成功!");
		return result;
	}

	/**
	 * 查找惩罚下拉列表
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getPunishCombobox(int id) {
		List<Map<String,Object>> punishCombox = new ArrayList<>();
		punishCombox = stuManagerDao.getPunishCombobox(id);
		return punishCombox;
	}

	public Map<String,Object> delPunish(int id) {
		Map<String,Object> result = new HashMap<>();
		if(id == 0){
			result.put("code",false);
			result.put("msg","删除失败！没找到对应惩罚");
			return result;
		}
		stuManagerDao.delPunish(id);
		result.put("code",true);
		result.put("msg","删除成功！");
		return result;
	}

	/**
	 * 获得学生素质拓展列表
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	public Map<String,Object> getAllQuality(int id, int page, int rows,String keywords) {
		Map<String,Object> result = new HashMap<>();
		List<Map<String,Object>> data = new ArrayList<>();
		if(keywords==null){
		    keywords = "";
        }
		//计算总数
		int total = stuManagerDao.getStuAllQualityWithKey(id,keywords);
		int begin = (page-1)*rows;
		//分页查询详细记录
		data = stuManagerDao.getQualityData(id,begin,rows,keywords);
		result.put("total",total);
		result.put("rows",data);
		return result;
	}

	/**
	 * 计算素质拓展分总分
	 * @param id
	 * @return
	 */
	public Map<String,Object> getCountQuality(int id) {
		Map<String,Object> result = new HashMap<>();
		//查询总活动个数，如果等于零，则设置count为0
		float count = 0;
		int total = stuManagerDao.getStuAllQuality(id);
		if(total==0){
			result.put("count",count);
			return result;
		}
		//得到总分
		count = stuManagerDao.getCountQuality(id);
		result.put("count",count);
		return result;
	}

    /**
     * 删除素质拓展活动
     * @param id
     * @return
     */
    public Map<String,Object> delQuality(int id) {
        Map<String,Object> result = new HashMap<>();
        stuManagerDao.delQuality(id);
        result.put("code",true);
        result.put("msg","删除成功！");
        return result;
    }

    /**
     * 添加学生素质拓展活动
     * @param id
     * @param name
     * @param score
     * @return
     */
    public Map<String,Object> addQulity(int id, String name, float score,String time) {
        Map<String,Object> result = new HashMap<>();
        if(name=="null" || "".equals(name.trim())){
            result.put("code",false);
            result.put("msg","添加失败，活动名称不能为空！");
        }
        stuManagerDao.addQuality(id,name,score,time);
        result.put("code",true);
        result.put("msg","添加成功！");
        return result;
    }

	/**
	 * 检查是否有重复学生
	 * @param stuName
	 * @param stuIdCard
	 * @return
	 */
	public int checkStuDuplicate(String stuName,String stuIdCard)
	{
		int result=stuManagerDao.checkStuDuplicate(stuName,stuIdCard);
		return result;
	}
}

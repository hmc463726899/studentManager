package com.stu.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stu.admin.service.StuManagerService;
import com.stu.util.ResponseUtil;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */

@Controller
@RequestMapping(value = "/stu/stuManager" ,method = {RequestMethod.POST,RequestMethod.GET})
public class StuManagerController {
	@Autowired
	private StuManagerService stuManagerService;

	/**
	 *
	 * 获取管理员的登录状态
	 * @param
	 * @param
	 * @param
	 */
	@RequestMapping("/getAdminLoginStatus.do")

	public void getAdminLoginStatus(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Map<String,Object> result = new HashMap<String,Object>();

		if(request.getSession().getAttribute("adminDisplayName")!=null)
		{
			result.put("code",true);
		}
		else
		{
			result.put("code",false);
		}
		ResponseUtil.returnJson(result,response);
	}



	/**
	 * 获取switch开关状态
	 * @param name
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getSwitch.do")
	public void getSwitch(String name,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = new HashMap<String,Object>();
		result = stuManagerService.getSwitch(name);
		ResponseUtil.returnJson(result,response);
	}

	/**
	 * 修改switch开关状态
	 * @param name
	 * @param state
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/setSwitch.do")
	public void getSwitch(String name,boolean state,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = new HashMap<String,Object>();
		result = stuManagerService.setSwitch(name,state);
		ResponseUtil.returnJson(result,response);
	}

	/**
	 * 获取全部学生信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getAllStu.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getAllStu(String keywords,int page,int rows ,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.getAllstu(keywords,page,rows);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 添加学生
	 * @param name
	 * @param idcard
	 * @param sex
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addStudent.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void addStudent(String name,String idcard,String sex ,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.addStudent(name,idcard,sex);
		ResponseUtil.returnJson(data, response);
	}


	/**
	 * 删除学生信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/delStudent.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void delStudent(String id ,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.delStudent(id);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 多选删除
	 * @param ids
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/delMoreStudent.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void delMoreStudent(String[] ids,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.delMoreStudent(ids);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 根据学生ID查找学生
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getStudent.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getStudent(String id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.getStudent(id);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 修改学生信息
	 * @param id
	 * @param name
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
	@RequestMapping(value = "/updateStudent.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void updateStudent(String id,String name,String idcard,String sex,String phone,String qq,String email,String address,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.updateStudent(name,idcard,sex,phone,qq,email,address,id);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 获取学生下拉列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getStudentCombox.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getStudentCombox(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Map<String,Object>> data = new ArrayList<>();
		data = stuManagerService.getStudentCombox();
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 通过学生id查找学生奖惩信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getStudentReward.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getStudentReward(int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.getStudentReward(id);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 添加奖励信息
	 * @param id
	 * @param content
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addreward.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void addreward(int id,String content,String time,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.addreward(id,content,time);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 查找奖励下拉列表
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getRewardCombobox.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getRewardCombobox(int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Map<String,Object>> data = new ArrayList<>();
		data = stuManagerService.getRewardCombobox(id);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 删除奖励信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/delReword.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void delReword(int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.delReword(id);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 新增惩罚
	 * @param id
	 * @param content
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addPunish.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void addPunish(int id,String content,String time,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.addPunish(id,content,time);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 查找惩罚下拉列表
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getPunishCombobox.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getPunishCombobox(int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Map<String,Object>> data = new ArrayList<>();
		data = stuManagerService.getPunishCombobox(id);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 删除惩罚
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/delPunish.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void delPunish(int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.delPunish(id);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 获得学生素质拓展列表
	 * @param id
	 * @param page
	 * @param rows
	 * @param keywords
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getAllQuality.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getAllQuality(int id,int page,int rows,String keywords,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.getAllQuality(id,page,rows,keywords);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 计算学生素质拓展分总分
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCountQuality.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getCountQuality(int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.getCountQuality(id);
		ResponseUtil.returnJson(data, response);
	}

    /**
     * 删除素质拓展活动
     * @param id
     * @param request
     * @param response
     * @throws IOException
     */
	@RequestMapping(value = "/delQuality.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void delQuality(int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.delQuality(id);
		ResponseUtil.returnJson(data, response);
	}

    /**
     * 添加学生素质拓展活动
     * @param id
     * @param name
     * @param score
     * @param request
     * @param response
     * @throws IOException
     */
	@RequestMapping(value = "/addQulity.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void addQulity(int id,String name,float score,String time,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.addQulity(id,name,score,time);
		ResponseUtil.returnJson(data, response);
	}

	@RequestMapping(value = "/checkStuDuplicate.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void checkStuDuplicate(String stuName,String stuIdCard,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();

		if(stuManagerService.checkStuDuplicate(stuName,stuIdCard)!=0)
		{
			result.put("code",false);
			result.put("msg","该学生已经存在!");
		}
		else
		{
			result.put("code",true);
		}
		ResponseUtil.returnJson(result, response);
	}
}

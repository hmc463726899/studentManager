package com.pim.admin.service;

import com.pim.admin.dao.CourseManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenkexuan on 2017/4/27.
 */

/**
 * edited and modified by dengxionghui on 2018/6/21
 */
@Service
@Transactional
public class CourseManagerService {
    @Autowired
    private CourseManagerDao courseManagerDao;

    /**
     * 获取所有课程信息分页、关键词
     * @param keywords
     * @param page
     * @param rows
     * @return
     */
    public Map<String,Object> getAllcourse(String keywords, int page, int rows) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<>();
        if(keywords==null){
            keywords="";
        }
        int begin = (page-1)*rows;
        int total = courseManagerDao.getTotal(keywords);
        data = courseManagerDao.getAllcourse(keywords,begin,rows);
        result.put("total",total);
        result.put("rows",data);
        return result;
    }

    /**
     * 获取课程分类下拉列表
     * @return
     */
    public List<Map<String,Object>> getCombox() {
        List<Map<String,Object>> combox = new ArrayList<Map<String,Object>>();
        combox= courseManagerDao.getCombox();
        return combox;
    }

    /**
     * 添加课程
     * @param name
     * @param type
     * @param score
     * @return
     */
    public Map<String,Object> addCourse(String name, String type, String score) {
        Map<String,Object> result = new HashMap<>();
        courseManagerDao.addCourse(name,type,score);
        result.put("code",true);
        return result;
    }

    /**
     * 通过课程id查找选择该课程的学生
     * @param id
     * @return
     */
    public Map<String,Object> getStuByCourse(int id,int page,int rows) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<>();
        int total = courseManagerDao.getTotalByCourseId(id);
        int begin = (page-1)*rows;
        data = courseManagerDao.getStuByCourse(id,begin,rows);
        result.put("total",total);
        result.put("rows",data);
        return result;
    }

    /**
     * 添加课程类型
     * @param typeName
     * @return
     */
    public Map<String,Object> addCourseType(String typeName) {
        Map<String,Object> result = new HashMap<>();
        courseManagerDao.addCourseType(typeName);
        result.put("code",true);
        return result;
    }

    /**
     * 删除课程---删除课程课，连带删除选课表中的信息
     * @param id
     * @return
     */
    public Map<String,Object> delCourse(int id) {
        Map<String,Object> result = new HashMap<>();
        //删除课程
        courseManagerDao.delCourse(id);
        //删除已选此课程的学生选课信息
        courseManagerDao.delCourseAndStu(id);
        result.put("code",true);
        return result;
    }

    /**
     * 获取学生下拉列表
     * @return
     */
    public List<Map<String,Object>> getStudentCombox(int courseId) {
        List<Map<String,Object>> combox = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        //查找该课程所有已选择学生，例如：已选择2
        List<Integer> stus = courseManagerDao.getAllStuByCourse(courseId);
        //查找所有的学生，例如：全部学生有1,2,3
        data= courseManagerDao.getStudentCombox();
        //下拉列表中过滤已选择该课程的学生
        for(int i=0;i<stus.size();i++){
            for(int j=0;j<data.size();j++){
                if(stus.get(i)==(Integer) data.get(j).get("id")){
                    //把已选择选择的学生从列表中删除
                    data.remove(j);
                    //后面不可能还有，直接跳出循环，开始下一次遍历
                    break;
                }
            }
        }
        combox=data;
        return combox;
    }

    /**
     * 学生选课
     * @param courseId
     * @param stuId
     * @return
     */
    public Map<String,Object> addStuToCourse(int courseId, int stuId) {
        Map<String,Object> result = new HashMap<>();
        courseManagerDao.addStuToCourse(courseId,stuId);
        result.put("code",true);
        result.put("msg","添加成功！");
        return result;
    }

    /**
     * 获得选课学生datagrid
     * @param keywords
     * @param courseId
     * @param page
     * @param rows
     * @return
     */
    public Map<String,Object> getStuGradeGrid(String keywords, int courseId, int page, int rows) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        int total = courseManagerDao.getStuGradeCount(keywords,courseId);
        int begin = (page-1)*rows;
        data = courseManagerDao.getStuGradeGrid(keywords,courseId,begin,rows);
        result.put("total",total);
        result.put("rows",data);
        return result;
    }

    /**
     * 录入学生成绩
     * @param stuId
     * @param courseId
     * @param score
     * @return
     */
    public Map<String,Object> addStuGrade(int stuId, int courseId, String score) {
        Map<String,Object> result = new HashMap<>();
        if(score==null){
            result.put("code",false);
            result.put("msg","分数不能为空！");
            return result;
        }
        courseManagerDao.addStuGrade(stuId,courseId,score);
        result.put("code",true);
        result.put("msg","录入成功！");
        return result;
    }
}

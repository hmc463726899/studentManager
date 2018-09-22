package com.stu.user.service;

import com.stu.user.dao.StuCourseDao;
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
public class StuCourseService {
    @Autowired
    private StuCourseDao courseDao;

    /**
     * 获得全部课程
     * @param stuId
     * @param keywords
     * @param page
     * @param rows
     * @return
     */
    public Map<String,Object> getAllCourse(Integer stuId, String keywords, Integer page, Integer rows) {
        Map<String,Object> result = new HashMap<String,Object>();
        List<Map<String,Object>> data = new ArrayList<>();
        //查找课程总数
        int total = courseDao.getTotalCourse();
        //课程分页起始
        int begin = (page-1)*rows;
        //查找所有课程
        data = courseDao.getAllCourse(keywords,begin,rows);
        //查找该学生已选择课程
        List<Integer> stuCourse = courseDao.getStuCourse(stuId);
        //标志已选择的课程
        for(int i=0;i<stuCourse.size();i++){
            for(int j=0;j<data.size();j++){
                if(stuCourse.get(i)==data.get(j).get("id")){
                    //如果判断学生已选择该门课程，则map添加("flag",1)
                    data.get(j).put("flag",1);
                    break;
                }
            }
        }
        result.put("total",total);
        result.put("rows",data);
        return result;
    }

    /**
     * 获取选课信息
     * @return
     */
    public Map<String,Object> getMsg() {
        Map<String,Object> result = new HashMap<String,Object>();
        int code = courseDao.getCanGetCourse();
        if(code==0){
            result.put("msg","选课暂未开通");
        }else{
            result.put("msg","选课已开通");
        }
        return result;
    }

    /**
     * 学生选课
     * @param stuId
     * @param courseId
     * @return
     */
    public Map<String,Object> getCourse(int stuId, int courseId) {
        Map<String,Object> result = new HashMap<String,Object>();
        //查看学生选课是否开通
        int flag = courseDao.getCanGetCourse();
        if(flag==0){
            //没开通选课
            result.put("code",false);
            result.put("msg","选课暂未开通,请等待管理员开通后再试！");
            return result;
        }else {
            //开通了选课
            courseDao.getCourse(stuId,courseId);
            result.put("code",true);
            result.put("msg","选课成功！");
            return result;
        }
    }
}

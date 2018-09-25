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
/**
 * Created by chenkexuan on 2017/5/1.
 */
@Component
public interface StuCourseDao {
    int getTotalCourse();

    List<Map<String,Object>> getAllCourse(String keywords, int begin, int rows);

    List<Integer> getStuCourse(int stuId);

    int getCanGetCourse();

    void getCourse(int stuId, int courseId);
}

package com.pim.user.service;

import com.pim.user.dao.GradeDao;
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
public class GradeService {
    @Autowired
    private GradeDao gradeDao;

    /**
     * 获得学生成绩
     * @param stuId
     * @param page
     * @param rows
     * @return
     */
    public Map<String,Object> getGrade(int stuId, int page, int rows) {
        Map<String,Object> result = new HashMap<String,Object>();
        List<Map<String,Object>> data = new ArrayList<>();
        int begin = (page-1)*rows;
        int total = gradeDao.getTotalGrade(stuId);
        data = gradeDao.getGrade(stuId,begin,rows);
        result.put("total",total);
        result.put("rows",data);
        return result;
    }

    /**
     * 计算学生绩点
     * @param stuId
     * @return
     */
    public Map<String,Object> getJD(int stuId) {
        Map<String,Object> result = new HashMap<String,Object>();
        //绩点公式：每一科绩点乘以每一科学分加起来。除总学分

        //得到学生所有课程信息
        List<Map<String,Object>> data = new ArrayList<>();
        data = gradeDao.getAllGrade(stuId);
        if(data.size()==0){
            result.put("code",true);
            result.put("msg","暂时没有成绩!");
            return result;
        }
        int sum = 0;//每一科绩点乘以每一科学分加起来
        int countScore = 0;//总学分
        for(int i=0;i<data.size();i++){
            if(data.get(i).get("score")!=null) {
                int courseScore = Integer.parseInt((String) data.get(i).get("courseScore"));
                int grade = (Integer) data.get(i).get("score");
                double score = getCourseScore(grade);
                sum += courseScore * score;
                countScore += Integer.parseInt((String) data.get(i).get("courseScore"));
            }
        }
        double s = (double)sum/(double)countScore;
        result.put("score",s);
        result.put("code",true);
        return result;
    }

    /**
     * 计算每科绩点
     * @param score
     * @return
     */
    public double getCourseScore(int score){
        if(score<60){
            return 0;
        }
        int ge = score%10;//得到分数个位数
        int shi = score/10;//得到分数十位数
        int base=0;
        if(shi==6){
            base=1;
        }else if(shi==7){
            base=2;
        }else if(shi==8){
            base=3;
        }else if(shi==9){
            base=4;
        }
        double stuScore = base+ge*0.1;
        return stuScore;
    }
}



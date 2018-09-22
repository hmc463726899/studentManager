package com.stu.user.dao;

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
public interface RewardPunishDao {

    List<Map<String,Object>>getStuReward(int stuId, int begin, int rows);

    int getStuRewardCount(int stuId);

    List<Map<String,Object>> getStuPunish(int stuId,int begin,int rows);

    int getStuPunishCount(int stuId);
}

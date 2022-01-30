package com.cqnu.dlzf.service;

import com.cqnu.dlzf.bean.ChartRecord;

import java.util.List;

public interface ChartRecordService {

    /**
     * 插入一次聊天记录
     */
    void chartOne(ChartRecord chartRecord);

    /**
     * 查询用户和某一好友的聊天记录
     * @param userId
     */
    List<ChartRecord> selectMyChartWithFriend(String userId,String friendId);
}

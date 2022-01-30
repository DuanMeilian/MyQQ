package com.cqnu.dlzf.service.impl;

import com.cqnu.dlzf.bean.ChartRecord;
import com.cqnu.dlzf.dao.ChartRecordMapper;
import com.cqnu.dlzf.service.ChartRecordService;
import com.cqnu.dlzf.utils.MyBatisUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ChartRecordServiceImpl implements ChartRecordService {

    @Override
    public void chartOne(ChartRecord chartRecord) {
        // 开启批量操作
        SqlSession sqlSession = MyBatisUtils.getSqlSession(ExecutorType.BATCH);
        ChartRecordMapper chartRecordMapper = sqlSession.getMapper(ChartRecordMapper.class);
        chartRecordMapper.insertChartRecord(chartRecord);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<ChartRecord> selectMyChartWithFriend(String userId, String friendId) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        ChartRecordMapper chartRecordMapper = sqlSession.getMapper(ChartRecordMapper.class);
        List<ChartRecord> chartRecords = chartRecordMapper.selectChartRecordsByUserId(userId,friendId);
        sqlSession.close();
        return chartRecords;
    }
}

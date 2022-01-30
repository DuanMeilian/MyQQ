package com.cqnu.dlzf.dao;

import com.cqnu.dlzf.bean.ChartRecord;
import com.cqnu.dlzf.utils.MyBatisUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TestChartRecordDao {

    @Test
    public void insertChartRecordTest(){
        ChartRecord chartRecord = new ChartRecord();
        chartRecord.setRecordId(MyBatisUtils.getUUID());
        chartRecord.setSenderId("98b8363894c140c3bd1c1f24df8c5862");
        chartRecord.setreceiverId("5212081dd32a45cb8d02989d02f15146");
        chartRecord.setContent("测试");
        chartRecord.setTime(new Date(System.currentTimeMillis()));

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        ChartRecordMapper chartRecordMapper = sqlSession.getMapper(ChartRecordMapper.class);
        chartRecordMapper.insertChartRecord(chartRecord);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectChartRecordsByUserIdTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        ChartRecordMapper chartRecordMapper = sqlSession.getMapper(ChartRecordMapper.class);

        Page<Object> page = PageHelper.startPage(1, 1);
        List<ChartRecord> chartRecords = chartRecordMapper.selectChartRecordsByUserId("98b8363894c140c3bd1c1f24df8c5862", "5212081dd32a45cb8d02989d02f15146");
        for (ChartRecord chartRecord : chartRecords) {
            System.out.println(chartRecord);
        }
        sqlSession.close();
    }
}

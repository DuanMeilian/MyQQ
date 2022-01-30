package com.cqnu.dlzf.dao;

import com.cqnu.dlzf.bean.ChartRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChartRecordMapper {

    /**
     * 新增一条聊天记录
     * @param chartRecord
     */
    void insertChartRecord(ChartRecord chartRecord);

    /**
     * 查询消息记录（userId既可以是发送方，也可以接收方）
     * @param userId
     * @return
     */
    List<ChartRecord> selectChartRecordsByUserId(
            @Param("userId") String userId,
            @Param("friendId") String friendId);
}

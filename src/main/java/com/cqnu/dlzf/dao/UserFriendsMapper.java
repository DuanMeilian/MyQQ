package com.cqnu.dlzf.dao;

import com.cqnu.dlzf.bean.ChartRecord;
import com.cqnu.dlzf.bean.UserFriends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFriendsMapper {
    /**
     * 根据用户Id查询其所有的好友
     * @param userId
     * @return
     */
    List<UserFriends> selectFriendsByUserId(String userId);

    /**
     * 更新最新聊天记录
     * @param chartRecord
     */
    void updateNewestUserFriend(ChartRecord chartRecord);

    /**
     * 添加好友
     * @param userFriend
     */
    int insertUserFriend(
            @Param("userFriend") UserFriends userFriend,
            @Param("secondId") String secondId
    );
}

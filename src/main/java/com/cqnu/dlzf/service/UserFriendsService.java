package com.cqnu.dlzf.service;

import com.cqnu.dlzf.bean.ChartRecord;
import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.bean.UserFriends;

import java.util.List;

public interface UserFriendsService {

    /**
     * 通过用户Id查询其所有好友
     * @return
     */
    List<UserFriends> getUserFriendsByUserId(String userId);

    /**
     * 更新为最新聊天状态
     * @param chartRecord
     */
    void updateNewest(ChartRecord chartRecord);

    /**
     * 添加好友
     * @param userFriend
     */
    int addFriend(UserFriends userFriend);

    /**
     * 查询两者是否为好友
     * @param userId
     * @param friendId
     * @return
     */
    boolean isFriend(String userId, String friendId);
}

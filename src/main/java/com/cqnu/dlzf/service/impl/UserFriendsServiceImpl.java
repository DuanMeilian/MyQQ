package com.cqnu.dlzf.service.impl;

import com.cqnu.dlzf.bean.ChartRecord;
import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.bean.UserFriends;
import com.cqnu.dlzf.dao.UserFriendsMapper;
import com.cqnu.dlzf.service.UserFriendsService;
import com.cqnu.dlzf.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserFriendsServiceImpl implements UserFriendsService {

    @Override
    public List<UserFriends> getUserFriendsByUserId(String userId) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserFriendsMapper userFriendsMapper = sqlSession.getMapper(UserFriendsMapper.class);
        List<UserFriends> userFriends = userFriendsMapper.selectFriendsByUserId(userId);
        sqlSession.close();
        return userFriends;
    }

    @Override
    public void updateNewest(ChartRecord chartRecord) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserFriendsMapper userFriendsMapper = sqlSession.getMapper(UserFriendsMapper.class);
        userFriendsMapper.updateNewestUserFriend(chartRecord);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int addFriend(UserFriends userFriend) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserFriendsMapper userFriendsMapper = sqlSession.getMapper(UserFriendsMapper.class);
        int rows = userFriendsMapper.insertUserFriend(userFriend,MyBatisUtils.getUUID());
        sqlSession.commit();
        sqlSession.close();
        return rows;
    }

    @Override
    public boolean isFriend(String userId, String friendId) {
        List<UserFriends> userFriends = getUserFriendsByUserId(userId);
        for (UserFriends userFriend : userFriends) {
            User friend = userFriend.getFriend();
            if (friendId != null && friendId.equals(friend.getUserId())) return true;
        }
        return false;
    }
}

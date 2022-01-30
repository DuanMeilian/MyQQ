package com.cqnu.dlzf.dao;

import com.cqnu.dlzf.bean.ChartRecord;
import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.bean.UserFriends;
import com.cqnu.dlzf.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestUserFriendsDao {

    @Test
    public void updateNewestUserFriendTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserFriendsMapper userFriendsMapper = sqlSession.getMapper(UserFriendsMapper.class);

        ChartRecord chartRecord = new ChartRecord();
        chartRecord.setSenderId("98b8363894c140c3bd1c1f24df8c5862");
        chartRecord.setreceiverId("5212081dd32a45cb8d02989d02f15146");
        chartRecord.setContent("测试");
        chartRecord.setTime(new Date(System.currentTimeMillis()));
        userFriendsMapper.updateNewestUserFriend(chartRecord);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectFriendByUserIdTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserFriendsMapper userFriendsMapper = sqlSession.getMapper(UserFriendsMapper.class);
        List<UserFriends> userFriends = userFriendsMapper.selectFriendsByUserId("98b8363894c140c3bd1c1f24df8c5862");
        for (UserFriends userFriend : userFriends) {
            System.out.println("userId:" + userFriend.getUser().getUserId());

            User friend = userFriend.getFriend();
            System.out.println(friend);
            System.out.println("最新聊天内容：" + userFriend.getLatestChartContent());

            Date time = userFriend.getLatestChartTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(sdf);
            String format = sdf.format(time);
            System.out.println("最新聊天时间：" + format);
        }
        sqlSession.close();
    }

    @Test
    public void insertUserFriendTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserFriendsMapper userFriendsMapper = sqlSession.getMapper(UserFriendsMapper.class);
        UserFriends userFriend = new UserFriends();
        userFriend.setFriendListId(MyBatisUtils.getUUID());
        userFriend.setUser(new User("5212081dd32a45cb8d02989d02f15146"));
        userFriend.setFriend(new User("f16dec92f2534c3594e22a19087028b2"));
        userFriend.setLatestChartTime(new Date(System.currentTimeMillis()));
        userFriend.setLatestChartContent("我们已经是好友了，现在开始对话吧！");

        userFriendsMapper.insertUserFriend(userFriend, MyBatisUtils.getUUID());
        sqlSession.commit();
        sqlSession.close();
    }
}

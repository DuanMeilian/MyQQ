package com.cqnu.dlzf.dao;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.utils.InternetUtils;
import com.cqnu.dlzf.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestUserDao {

    @Test
    public void selectUsersByInfoTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectUsersByInfo("打开了家门咱迎春风");
        users.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void updateByInfoTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User updateUser = new User();
        updateUser.setPort(9000 + 1);
        updateUser.setIp(InternetUtils.getLocalIp());
        updateUser.setUserId("98b8363894c140c3bd1c1f24df8c5862");
        updateUser.setState(1);

        userMapper.updateByInfo(updateUser);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateStateByUsernameTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User updateUser = new User();
        updateUser.setUsername("10000");
        updateUser.setState(0);
        userMapper.updateByInfo(updateUser);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectStateByUsernameTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Integer state = userMapper.selectStateByUsername("10000");
        System.out.println(state);
        sqlSession.close();
    }

    @Test
    public void selectUserByUsernameAndPassTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserByUsernameAndPass("10000", "123");
        System.out.println(user);
        sqlSession.close();
    }

    /*
    初始化第一个用户
     */
    @Test
    public void insertNewUserTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUserId(MyBatisUtils.getUUID());
        user.setUsername("10000");
        user.setPassword("123");
        user.setNickname("admin");

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getMaxUsernameFromUsersTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String maxUsername = userMapper.getMaxUsernameFromUsers();
        System.out.println(maxUsername);
        sqlSession.close();
    }
}

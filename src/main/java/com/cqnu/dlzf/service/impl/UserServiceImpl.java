package com.cqnu.dlzf.service.impl;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.dao.UserMapper;
import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.utils.InternetUtils;
import com.cqnu.dlzf.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public String register(User user){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 设置用户Id
        user.setUserId(MyBatisUtils.getUUID());

        // 给予用户账号（数据库中默认有账号最大值10000）
        String maxUsernameFromUsers = userMapper.getMaxUsernameFromUsers();
        BigDecimal nextUsername = new BigDecimal(maxUsernameFromUsers).add(BigDecimal.ONE);
        user.setUsername(String.valueOf(nextUsername));

        int row = userMapper.insertUser(user);

        sqlSession.commit();
        sqlSession.close();

        /*
            row == 1：注册成功，返回用户账号
            row ！= 1：注册失败，返回null
         */
        return row == 1 ? String.valueOf(nextUsername) : null;
    }

    @Override
    public User login(String username, String password) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserByUsernameAndPass(username, password);

        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    @Override
    public void loginSucceed(String userId, int port) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User updateUser = new User();
        updateUser.setPort(port);
        updateUser.setIp(InternetUtils.getLocalIp());
        updateUser.setUserId(userId);
        updateUser.setState(1);
        userMapper.updateByInfo(updateUser);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void offline(String username) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User updateUser = new User();
        updateUser.setUsername(username);
        updateUser.setState(0);
        userMapper.updateByInfo(updateUser);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateUserSign(User loginUser) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateByInfo(loginUser);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateUser(User loginUser) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateByInfo(loginUser);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public User getUserById(String userId) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectInfoByUserId(userId);
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> searchBy(String content) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectUsersByInfo(content);
        users.forEach(System.out::println);
        sqlSession.close();
        return users;
    }
}

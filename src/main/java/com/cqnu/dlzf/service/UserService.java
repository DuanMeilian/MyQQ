package com.cqnu.dlzf.service;

import com.cqnu.dlzf.bean.User;

import java.util.List;

public interface UserService {
    /**
     * 注册新用户
     * @param user
     * @return 新用户账号
     */
    String register(User user);

    /**
     * 登录用户
     * PS：一定要先查询，再修改状态
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 用户离线
     * @param username
     */
    void offline(String username);

    /**
     * 修改用户签名
     * @param loginUser
     */
    void updateUserSign(User loginUser);

    /**
     * 修改用户信息
     * @param loginUser
     */
    void updateUser(User loginUser);

    /**
     * 查询用户名和头像等信息
     * @param userId
     * @return
     */
    User getUserById(String userId);


    /**
     * 登录成功的操作
     * 1.修改状态为1
     * 2.保存ip和端口
     * @param userId
     */
    void loginSucceed(String userId, int port);

    /**
     * 根据搜索内容，检索用户（非模糊查询）
     * @param content
     * @return
     */
    List<User> searchBy(String content);
}

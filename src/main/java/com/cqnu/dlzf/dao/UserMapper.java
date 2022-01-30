package com.cqnu.dlzf.dao;

import com.cqnu.dlzf.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 添加新用户
     * @param user
     */
    int insertUser(User user);

    /**
     * 获取账号数为最大值的用户账号名
     * @return
     */
    String getMaxUsernameFromUsers();

    /**
     * 根据用户名和密码查询用户
     */
    User selectUserByUsernameAndPass(
            @Param("username") String username,
            @Param("password") String password);

    /**
     * 根据用户名查询上线状态
     * @return
     */
    Integer selectStateByUsername(String username);

    /**
     * 更新用户信息，比如：
     * 1.根据用户名更新其上线状态
     * 2.根据用户名更新其签名
     * @param user
     */
    void updateByInfo(User user);

    /**
     * 根据用户Id查询用户信息
     * @param userId
     * @return
     */
    User selectInfoByUserId(String userId);

    /**
     * 根据搜索内容查询出匹配的用户
     * @param content
     * @return
     */
    List<User> selectUsersByInfo(String content);
}

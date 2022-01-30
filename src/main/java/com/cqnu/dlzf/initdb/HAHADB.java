package com.cqnu.dlzf.initdb;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class HAHADB {

    public static void main(String[] args) {
        try {
            HAHADB.init();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void init() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

        Statement stat = conn.createStatement();

        stat.executeUpdate("drop database if exists qq");
        stat.executeUpdate("create database qq");
        stat.executeUpdate("use qq");
        stat.executeUpdate("DROP TABLE IF EXISTS `t_user`");
        stat.executeUpdate("CREATE TABLE `t_user`  (\n" +
                "  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,\n" +
                "  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\n" +
                "  `port` int(6) NULL DEFAULT NULL,\n" +
                "  `username` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,\n" +
                "  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,\n" +
                "  `nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,\n" +
                "  `state` int(1) NOT NULL DEFAULT 0 COMMENT '1：在线；0：离线',\n" +
                "  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'C:\\\\head_uploads\\\\default.png',\n" +
                "  `sign` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',\n" +
                "  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',\n" +
                "  `birthday` date NULL DEFAULT NULL,\n" +
                "  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',\n" +
                "  `company` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',\n" +
                "  `blood_type` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',\n" +
                "  `job` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',\n" +
                "  `location` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',\n" +
                "  `hometown` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',\n" +
                "  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',\n" +
                "  PRIMARY KEY (`user_id`) USING BTREE\n" +
                ")");
        stat.executeUpdate("DROP TABLE IF EXISTS `t_user_friends`");
        stat.executeUpdate("CREATE TABLE `t_user_friends`  (\n" +
                "  `friend_list_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,\n" +
                "  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\n" +
                "  `friend_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\n" +
                "  `latest_chart_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '我们已经是好友了，现在开始对话吧！' COMMENT '最新聊天记录',\n" +
                "  `latest_chart_time` datetime(0) NULL DEFAULT NULL COMMENT '最新聊天时间（最初为添加好友的时候）',\n" +
                "  PRIMARY KEY (`friend_list_id`) USING BTREE,\n" +
                "  INDEX `friend_id`(`friend_id`) USING BTREE,\n" +
                "  CONSTRAINT `friend_id` FOREIGN KEY (`friend_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ")");
        stat.executeUpdate("DROP TABLE IF EXISTS `t_chart_record`");
        stat.executeUpdate("CREATE TABLE `t_chart_record`  (\n" +
                "  `record_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,\n" +
                "  `sender_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\n" +
                "  `receiver_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\n" +
                "  `content` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,\n" +
                "  `time` datetime(0) NULL DEFAULT NULL,\n" +
                "  PRIMARY KEY (`record_id`) USING BTREE,\n" +
                "  INDEX `recevier_id`(`receiver_id`) USING BTREE,\n" +
                "  INDEX `sender_id`(`sender_id`) USING BTREE,\n" +
                "  CONSTRAINT `recevier_id` FOREIGN KEY (`receiver_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "  CONSTRAINT `sender_id` FOREIGN KEY (`sender_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ")");

        stat.executeUpdate("INSERT INTO `t_user` VALUES ('2261a5230ce7402fbc39fe450a3d3f45', NULL, NULL, '10007', '132', '123', 0, 'C:\\\\head_uploads\\\\default.png', '', '', NULL, '', '', '', '', '', '', '')");
        stat.executeUpdate("INSERT INTO `t_user` VALUES ('5212081dd32a45cb8d02989d02f15146', '', NULL, '10001', '123', '今天是个好日子，嘿嘿', 0, 'C:\\\\head_uploads\\\\aea02d12b2e4493582af20d6c2db7b13.png', '咚咚咚 咚咚~', '女', '2001-04-26', '', '', 'O型', '', '', '', '');");
        stat.executeUpdate("INSERT INTO `t_user` VALUES ('5d7d0a437eb546a989adf47299695b7a', NULL, NULL, '10006', '123', '123', 0, 'C:\\\\head_uploads\\\\default.png', '', '', NULL, '', '', '', '', '', '', '')");
        stat.executeUpdate("INSERT INTO `t_user` VALUES ('6bc2d5ea9c454aa9ad52cb6c10dc5547', NULL, NULL, '10008', '123', '123', 0, 'C:\\\\head_uploads\\\\default.png', '', '', NULL, '', '', '', '', '', '', '')");
        stat.executeUpdate("INSERT INTO `t_user` VALUES ('8f2ae4534cb841ac8393cc54423b7806', '', NULL, '10004', '123', '123', 0, 'C:\\\\head_uploads\\\\aea02d12b2e4493582af20d6c2db7b13.png', '哈哈', '', '1905-04-02', '3123123', '', 'A型', '', '', '', '')");
        stat.executeUpdate("INSERT INTO `t_user` VALUES ('8f3383d33341438da3a31eaf3212b3f3', '', NULL, '10003', '123', 'Laurus', 0, 'C:\\\\head_uploads\\\\40dd03af10ce4f189fd8b4b8c0c6d34e.png', '编辑个性签名', '女', '2001-04-14', '', '', '', '', '', '', '')");
        stat.executeUpdate("INSERT INTO `t_user` VALUES ('98b8363894c140c3bd1c1f24df8c5862', '', NULL, '10000', '123', '家猫', 0, 'C:\\\\head_uploads\\\\3d9617cf8af74c57a80b0c986c99bc09.png', '修', '女', '2001-06-04', '13032346976', '无', '其它血型', '', '', '', '')");
        stat.executeUpdate("INSERT INTO `t_user` VALUES ('bfa4b1d1ed174e118c5668d64e1077c6', NULL, NULL, '10005', '123', '123', 0, 'C:\\\\head_uploads\\\\default.png', '', '', NULL, '', '', '', '', '', '', '')");
        stat.executeUpdate("INSERT INTO `t_user` VALUES ('f16dec92f2534c3594e22a19087028b2', '', NULL, '10002', '123', '无为', 0, 'C:\\\\head_uploads\\\\156b032d54644f4ea60b45940181a7e1.png', '一期一祈', '女', '2001-04-21', '', '', '', '', '', '', '')");

        stat.executeUpdate("INSERT INTO `t_user_friends` VALUES ('45d9570fee1242e48de145050d9b57da', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '10', '2022-01-06 17:14:00')");
        stat.executeUpdate("INSERT INTO `t_user_friends` VALUES ('53e2f25a259849b2be058cab0f5a3350', '8f3383d33341438da3a31eaf3212b3f3', '98b8363894c140c3bd1c1f24df8c5862', '我们已经是好友了，现在开始对话吧！', '2022-01-06 17:15:11')");
        stat.executeUpdate("INSERT INTO `t_user_friends` VALUES ('62d99cf1db5547c6b644db019d4ee79a', 'f16dec92f2534c3594e22a19087028b2', '98b8363894c140c3bd1c1f24df8c5862', '我们已经是好友了，现在开始对话吧！', '2022-01-06 17:15:07')");
        stat.executeUpdate("INSERT INTO `t_user_friends` VALUES ('ad0290bb28524aa0abc312af5eb4abb3', '98b8363894c140c3bd1c1f24df8c5862', '8f3383d33341438da3a31eaf3212b3f3', '我们已经是好友了，现在开始对话吧！', '2022-01-06 17:15:11')");
        stat.executeUpdate("INSERT INTO `t_user_friends` VALUES ('cb3dfd15e5d84d1192f34c6d5ecaeae3', '98b8363894c140c3bd1c1f24df8c5862', 'f16dec92f2534c3594e22a19087028b2', '我们已经是好友了，现在开始对话吧！', '2022-01-06 17:15:07')");
        stat.executeUpdate("INSERT INTO `t_user_friends` VALUES ('e1047b4e2fae4bccb91f371cbc57d322', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '10', '2022-01-06 17:14:00')");

        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('073aa0831db6497fb38726b38fe9225e', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '7', '2022-01-06 17:08:14')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('0a64fc936e9e44e5b513bbcd386c5b5f', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '9', '2022-01-06 17:13:55')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('18ff3435ceca4e2ea09e9fcaa1640cb9', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '5', '2022-01-06 17:08:01')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('1af25667b73b4c08bc1c9a379620cbc3', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '8', '2022-01-06 17:13:51')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('210a761973684106bc8fa114d053e7a2', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '6', '2022-01-06 17:08:05')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('3416df7c124848eab803bcad6e2c1c6c', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '5', '2022-01-06 17:07:58')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('4e066b74197f4bc9b9d3adfc57561c5e', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '1', '2022-01-06 17:07:17')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('4edfbe90a64544b4a2b19110a80abe80', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '10', '2022-01-06 17:13:58')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('646e854158654a0390e871d4f59db43e', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '9', '2022-01-06 17:13:54')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('744eb1f04f86416f9e7e1c49a3004460', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '2', '2022-01-06 17:07:24')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('80e70252bd344ca58a66651f80f562e2', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '3', '2022-01-06 17:07:34')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('85732b11fb734e9e8794be5b328c5cdf', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '7', '2022-01-06 17:08:16')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('98254fae5fb74ee389eb788794836dbd', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '1', '2022-01-06 17:07:21')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('ad0290bb28524aa0abc312af5eb4abb3', '98b8363894c140c3bd1c1f24df8c5862', '8f3383d33341438da3a31eaf3212b3f3', '我们已经是好友了，现在开始对话吧！', '2022-01-06 17:15:11')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('af0b1611c5cd4018bc008cee31310eee', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '4', '2022-01-06 17:07:54')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('cb3dfd15e5d84d1192f34c6d5ecaeae3', '98b8363894c140c3bd1c1f24df8c5862', 'f16dec92f2534c3594e22a19087028b2', '我们已经是好友了，现在开始对话吧！', '2022-01-06 17:15:07')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('e1047b4e2fae4bccb91f371cbc57d322', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '我们已经是好友了，现在开始对话吧！', '2022-01-06 17:06:29')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('ecd42bcec1a74638ae63497629646a05', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '2', '2022-01-06 17:07:26')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('eeac6906490f4b8fad57526d4b6ebb84', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '6', '2022-01-06 17:08:03')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('f012015d7bf3402f886e33af27d1b513', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '4', '2022-01-06 17:07:45')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('f4713f270acd4fca88b5016785f32a02', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '3', '2022-01-06 17:07:29')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('f5539eae7c374f69a00bc45db3bb6dbe', '98b8363894c140c3bd1c1f24df8c5862', '5212081dd32a45cb8d02989d02f15146', '10', '2022-01-06 17:14:00')");
        stat.executeUpdate("INSERT INTO `t_chart_record` VALUES ('f89ee8f46aec41ef913f347d6e775f91', '5212081dd32a45cb8d02989d02f15146', '98b8363894c140c3bd1c1f24df8c5862', '8', '2022-01-06 17:13:14')");
    }
}
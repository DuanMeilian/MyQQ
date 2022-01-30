package com.cqnu.dlzf.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class MyBatisUtils {

    // 判断是否没有网络，无法连接（SqlSession为null）
    private static boolean connectionOK = true;

    private static SqlSessionFactory factory;

    private MyBatisUtils(){}

    static{
        String config = "mybatis.xml";
        try(InputStream is = Resources.getResourceAsStream(config)){
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定ExecutorType的SqlSession对象
     * @return
     */
    public static SqlSession getSqlSession(ExecutorType type){
        SqlSession session = null;
        if(factory != null){
            session = factory.openSession(type);
        }
        return session;
    }

    /**
     * 获取SqlSession对象
     * @return
     */
    public static SqlSession getSqlSession(){
        SqlSession session = null;
        if(factory != null){
            session = factory.openSession();
        }
        return session;
    }

    /**
     * 获取UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}

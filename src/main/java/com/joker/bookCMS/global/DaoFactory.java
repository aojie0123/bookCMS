package com.joker.bookCMS.global;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private SqlSessionFactory sessionFactory;

    private DaoFactory() {
        SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
        try {
            sessionFactory = sessionFactoryBuilder.build(Resources.getResourceAsReader("/mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public <T> T getDao(Class<T> tClass) {
        return sessionFactory.openSession(true).getMapper(tClass);
    }
}

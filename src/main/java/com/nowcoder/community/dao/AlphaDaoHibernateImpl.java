package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

import java.lang.annotation.Annotation;

// 运行程序会自动扫描这个bean，装到容器
@Repository("alphaHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao {


    @Override
    public String select() {
        return "Hibernate ";
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}

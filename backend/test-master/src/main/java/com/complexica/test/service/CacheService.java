package com.complexica.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CacheService {
    private HibernateJpaSessionFactoryBean sessionFactory;

    @Autowired
    public CacheService(HibernateJpaSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("rawtypes")
    public void evict(Class entityClass, Serializable id) {
        sessionFactory.getObject().getCache().evictEntity(entityClass, id);
    }
}

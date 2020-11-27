package com.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.io.Serializable;

@Component
public class CacheService {

    @Autowired
    EntityManager entityManager;

    @SuppressWarnings("rawtypes")
    public void evict(Class entityClass, Serializable id) {
        entityManager.getEntityManagerFactory().getCache().evict(entityClass, id);
    }
}

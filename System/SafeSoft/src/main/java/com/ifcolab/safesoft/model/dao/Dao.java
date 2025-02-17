package com.ifcolab.safesoft.model.dao;

import com.ifcolab.safesoft.factory.DatabaseJPA;
import com.ifcolab.safesoft.model.interfaces.IDao;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class Dao<T> implements IDao<T> {
    protected EntityManager entityManager;
    protected String jpql;
    protected Query qry;
    
    @Override
    public void save(T obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }
    
    @Override
    public void update(T obj) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }
    
    
}

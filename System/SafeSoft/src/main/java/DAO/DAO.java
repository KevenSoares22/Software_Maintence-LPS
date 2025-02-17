package DAO;

import Factory.DatabaseJPA;
import Interfaces.IDao;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public abstract class DAO<T> implements IDao<T> {
    protected EntityManager entityManager;
    protected String jpql;
    protected Query qry;

    public DAO() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
    }

    @Override
    public void save(T obj) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    @Override
    public void update(T obj) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    public boolean delete(int id) {
        try {
            T entity = find(id);
            if (entity == null) {
                return false;
            }
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(entity);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().rollback();
            }
            return false;
        } finally {
            this.entityManager.close();
        }
    }

    public T find(int id) {
        return this.entityManager.find(getEntityClass(), id);
    }

    public List<T> findAll() {
        return this.entityManager.createQuery("SELECT e FROM " + getEntityClass().getSimpleName() + " e", getEntityClass())
                .getResultList();
    }

    protected abstract Class<T> getEntityClass();
}

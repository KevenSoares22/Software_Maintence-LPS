package DAO;

import Factory.DatabaseJPA;
import javax.persistence.EntityManager;
import java.util.List;

public abstract class DAO<T> {

    protected EntityManager entityManager;

    public DAO() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
    }

    public boolean insert(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            T entity = find(id);
            if (entity == null) {
                return false;
            }
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        }
    }

    public T find(int id) {
        return entityManager.find(getEntityClass(), id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + getEntityClass().getSimpleName() + " e", getEntityClass())
                .getResultList();
    }

    // Método abstrato para obter a classe da entidade
    protected abstract Class<T> getEntityClass();
}

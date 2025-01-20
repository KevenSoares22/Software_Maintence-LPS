package DAO;

import Factory.DatabaseJPA;
import Model.Entities.Technician;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UserDAO extends DAO<User> {

    private EntityManager entityManager;

    public UserDAO() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = entityManager.find(User.class, id);
            if (user == null) {
                transaction.rollback();
                return false;
            }
            entityManager.remove(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    @Override
    public User find(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    public User findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public User findByCPF(String cpf) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.cpf = :cpf", User.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }

    public List<User> findByName(String name) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.name LIKE :name", User.class)
                .setParameter("name", name + "%")
                .getResultList();
    }
}

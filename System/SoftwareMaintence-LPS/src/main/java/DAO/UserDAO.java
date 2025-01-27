package DAO;

import Factory.DatabaseJPA;
import Model.Entities.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserDAO {

    private EntityManager entityManager;

    public UserDAO() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
    }

    // Método que busca o usuário pelo email e senha
    public User findByEmailAndPassword(String email, String password) {
        try {
            return entityManager.createQuery("SELECT u FROM JrUsers u WHERE u.email = :email AND u.password = :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT u FROM JrUsers u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}

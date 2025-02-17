package DAO;

import Factory.DatabaseJPA;
import Model.Entities.Client;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ClientDAO extends DAO<Client> {

    private EntityManager entityManager;

    public ClientDAO() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Client client = entityManager.find(Client.class, id);
            if (client == null) {
                transaction.rollback();
                return false;
            }
            entityManager.remove(client);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Client find(int id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public List<Client> findAll() {
        return entityManager.createQuery("SELECT c FROM Client c", Client.class)
                .getResultList();
    }

    @Override
    public Class<Client> getEntityClass() {
        return Client.class;
    }

    public Client findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT c FROM Client c WHERE c.email = :email", Client.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Client> findByName(String name) {
        return entityManager.createQuery("SELECT c FROM Client c WHERE c.name LIKE :name", Client.class)
                .setParameter("name", name + "%")
                .getResultList();
    }

    public Client findByCpf(String cpf) {
        try {
            return entityManager.createQuery("SELECT c FROM Client c WHERE c.cpf = :cpf", Client.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}

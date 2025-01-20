package DAO;

import Factory.DatabaseJPA;
import Model.Entities.ServiceAppointment;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ServiceAppointmentDAO extends DAO<ServiceAppointment> {

    private EntityManager entityManager;

    public ServiceAppointmentDAO() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ServiceAppointment serviceAppointment = entityManager.find(ServiceAppointment.class, id);
            if (serviceAppointment == null) {
                transaction.rollback();
                return false;
            }
            entityManager.remove(serviceAppointment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    @Override
    public ServiceAppointment find(int id) {
        return entityManager.find(ServiceAppointment.class, id);
    }

    @Override
    public List<ServiceAppointment> findAll() {
        return entityManager.createQuery("SELECT sa FROM ServiceAppointment sa", ServiceAppointment.class)
                .getResultList();
    }

    public ServiceAppointment findByClientId(int clientId) {
        return entityManager.createQuery("SELECT sa FROM ServiceAppointment sa WHERE sa.clientId = :clientId", ServiceAppointment.class)
                .setParameter("clientId", clientId)
                .getSingleResult();
    }
}

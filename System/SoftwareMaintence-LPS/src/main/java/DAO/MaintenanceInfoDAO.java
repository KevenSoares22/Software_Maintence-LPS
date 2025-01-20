package DAO;

import Factory.DatabaseJPA;
import Model.Entities.MaintenanceInfo;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class MaintenanceInfoDAO extends DAO<MaintenanceInfo> {

    private EntityManager entityManager;

    public MaintenanceInfoDAO() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            MaintenanceInfo maintenanceInfo = entityManager.find(MaintenanceInfo.class, id);
            if (maintenanceInfo == null) {
                transaction.rollback();
                return false;
            }
            entityManager.remove(maintenanceInfo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    @Override
    public MaintenanceInfo find(int id) {
        return entityManager.find(MaintenanceInfo.class, id);
    }

    @Override
    public List<MaintenanceInfo> findAll() {
        return entityManager.createQuery("SELECT mi FROM MaintenanceInfo mi", MaintenanceInfo.class)
                .getResultList();
    }

    public MaintenanceInfo findByClientId(int clientId) {
        return entityManager.createQuery("SELECT mi FROM MaintenanceInfo mi WHERE mi.clientId = :clientId", MaintenanceInfo.class)
                .setParameter("clientId", clientId)
                .getSingleResult();
    }
}

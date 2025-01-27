package DAO;

import Factory.DatabaseJPA;
import Model.Entities.MaintenceInfo;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class MaintenanceInfoDAO extends DAO<MaintenceInfo> {

    private EntityManager entityManager;

    public MaintenanceInfoDAO() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            MaintenceInfo maintenceInfo = entityManager.find(MaintenceInfo.class, id);
            if (maintenceInfo == null) {
                transaction.rollback();
                return false;
            }
            entityManager.remove(maintenceInfo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    @Override
    public MaintenceInfo find(int id) {
        return entityManager.find(MaintenceInfo.class, id);
    }

    @Override
    public List<MaintenceInfo> findAll() {
        return entityManager.createQuery("SELECT mi FROM MaintenanceInfo mi", MaintenceInfo.class)
                .getResultList();
    }

    @Override
    public Class<MaintenceInfo> getEntityClass() {
        return MaintenceInfo.class;
    }

    public MaintenceInfo findByClientId(int clientId) {
        return entityManager.createQuery("SELECT mi FROM MaintenanceInfo mi WHERE mi.clientId = :clientId", MaintenceInfo.class)
                .setParameter("clientId", clientId)
                .getSingleResult();
    }
}

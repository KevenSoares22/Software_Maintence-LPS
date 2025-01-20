package DAO;

import Factory.DatabaseJPA;
import Model.Entities.StockPlace;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class StockPlaceDAO extends DAO<StockPlace> {

    private EntityManager entityManager;

    public StockPlaceDAO() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            StockPlace stockPlace = entityManager.find(StockPlace.class, id);
            if (stockPlace == null) {
                transaction.rollback();
                return false;
            }
            entityManager.remove(stockPlace);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    @Override
    public StockPlace find(int id) {
        return entityManager.find(StockPlace.class, id);
    }

    @Override
    public List<StockPlace> findAll() {
        return entityManager.createQuery("SELECT sp FROM StockPlace sp", StockPlace.class)
                .getResultList();
    }

    public StockPlace findByFilial(String filial) {
        return entityManager.createQuery("SELECT sp FROM StockPlace sp WHERE sp.filial = :filial", StockPlace.class)
                .setParameter("filial", filial)
                .getSingleResult();
    }
}

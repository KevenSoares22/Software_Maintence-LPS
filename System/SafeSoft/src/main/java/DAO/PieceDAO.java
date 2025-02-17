package DAO;

import Factory.DatabaseJPA;
import Model.Entities.Piece;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PieceDAO extends DAO<Piece> {

    private EntityManager entityManager;

    public PieceDAO() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Piece piece = entityManager.find(Piece.class, id);
            if (piece == null) {
                transaction.rollback();
                return false;
            }
            entityManager.remove(piece);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Piece find(int id) {
        return entityManager.find(Piece.class, id);
    }

    @Override
    public List<Piece> findAll() {
        return entityManager.createQuery("SELECT p FROM Piece p", Piece.class)
                .getResultList();
    }

    @Override
    public Class<Piece> getEntityClass() {
        return Piece.class;
    }

    public Piece findByName(String name) {
        return entityManager.createQuery("SELECT p FROM Piece p WHERE p.nome = :name", Piece.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}

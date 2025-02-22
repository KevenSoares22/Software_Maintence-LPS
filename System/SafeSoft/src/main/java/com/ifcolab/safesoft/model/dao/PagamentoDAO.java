package com.ifcolab.safesoft.model.dao;

import com.ifcolab.safesoft.factory.DatabaseJPA;
import com.ifcolab.safesoft.model.Pagamento;
import java.util.List;
import javax.persistence.TypedQuery;

public class PagamentoDAO extends Dao<Pagamento> {
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        
        Pagamento pagamento = this.entityManager.find(Pagamento.class, id);
        if (pagamento == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }
        
        this.entityManager.remove(pagamento);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Pagamento find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Pagamento pagamento = this.entityManager.find(Pagamento.class, id);
        this.entityManager.close();
        return pagamento;
    }
    
    @Override
    public List<Pagamento> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT p FROM Pagamento p";
        TypedQuery<Pagamento> query = this.entityManager.createQuery(jpql, Pagamento.class);
        List<Pagamento> pagamentos = query.getResultList();
        this.entityManager.close();
        return pagamentos;
    }
    
    public List<Pagamento> buscarPorServico(int servicoId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            jpql = "SELECT p FROM Pagamento p WHERE p.servico.id = :servicoId";
            TypedQuery<Pagamento> query = this.entityManager.createQuery(jpql, Pagamento.class);
            query.setParameter("servicoId", servicoId);
            return query.getResultList();
        } finally {
            this.entityManager.close();
        }
    }
}

package com.ifcolab.safesoft.model.dao;

import com.ifcolab.safesoft.factory.DatabaseJPA;
import com.ifcolab.safesoft.model.Servico;

import java.util.List;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ServicoDAO extends Dao<Servico> {
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        
        Servico servico = this.entityManager.find(Servico.class, id);
        if (servico == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }
        
        this.entityManager.remove(servico);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Servico find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Servico servico = this.entityManager.find(Servico.class, id);
        this.entityManager.close();
        return servico;
    }
    
    @Override
    public List<Servico> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT DISTINCT s FROM Servico s " +
               "LEFT JOIN FETCH s.itens " +
               "LEFT JOIN FETCH s.cliente " +
               "LEFT JOIN FETCH s.tecnico " +
               "LEFT JOIN FETCH s.Suporte " +
               "ORDER BY s.dataHora DESC";
        TypedQuery<Servico> query = this.entityManager.createQuery(jpql, Servico.class);
        List<Servico> lst = query.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Servico> buscarServicosNoPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            jpql = "SELECT s FROM Servico s WHERE s.dataHora BETWEEN :inicio AND :fim";
            qry = this.entityManager.createQuery(jpql, Servico.class);
            qry.setParameter("inicio", inicio);
            qry.setParameter("fim", fim);
            return qry.getResultList();
        } finally {
            this.entityManager.close();
        }
    }
    
    public List<Servico> buscarPorData(LocalDate data) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            LocalDateTime inicioDia = data.atStartOfDay();
            LocalDateTime fimDia = data.atTime(23, 59, 59);
            
            jpql = "SELECT DISTINCT s FROM Servico s " +
                   "LEFT JOIN FETCH s.itens " +
                   "LEFT JOIN FETCH s.cliente " +
                   "LEFT JOIN FETCH s.tecnico " +
                   "LEFT JOIN FETCH s.Suporte " +
                   "WHERE s.dataHora BETWEEN :inicio AND :fim " +
                   "ORDER BY s.dataHora DESC";
            
            TypedQuery<Servico> query = this.entityManager.createQuery(jpql, Servico.class);
            query.setParameter("inicio", inicioDia);
            query.setParameter("fim", fimDia);
            
            return query.getResultList();
        } finally {
            this.entityManager.close();
        }
    }
    
    public List<Servico> buscarServicosPorCliente(int idCliente) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        try {
            jpql = "SELECT DISTINCT s FROM Servico s " +
                   "LEFT JOIN FETCH s.itens " +
                   "LEFT JOIN FETCH s.cliente " +
                   "LEFT JOIN FETCH s.tecnico " +
                   "LEFT JOIN FETCH s.Suporte " +
                   "WHERE s.cliente.id = :idCliente " +
                   "ORDER BY s.dataHora DESC";
            
            TypedQuery<Servico> query = this.entityManager.createQuery(jpql, Servico.class);
            query.setParameter("idCliente", idCliente);
            
            return query.getResultList();
        } finally {
            this.entityManager.close();
        }
    }
}

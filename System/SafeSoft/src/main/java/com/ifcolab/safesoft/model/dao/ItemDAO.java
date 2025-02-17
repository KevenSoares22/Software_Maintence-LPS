package com.ifcolab.safesoft.model.dao;

import com.ifcolab.safesoft.factory.DatabaseJPA;
import com.ifcolab.safesoft.model.Item;
import java.time.LocalDateTime;
import java.util.List;

public class ItemDAO extends Dao<Item> {
    
    public ItemDAO() {
    }
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        
        Item item = this.entityManager.find(Item.class, id);
        if (item == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }
        
        this.entityManager.remove(item);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Item find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Item i = this.entityManager.find(Item.class, id);
        this.entityManager.close();
        return i;
    }
    
    @Override
    public List<Item> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT i FROM Item i";
        qry = this.entityManager.createQuery(jpql, Item.class);
        List<Item> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Item> filterByDescricao(String descricao) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT i FROM Item i WHERE i.descricao LIKE :descricao";
        qry = this.entityManager.createQuery(jpql, Item.class);
        qry.setParameter("descricao", "%" + descricao + "%");
        List<Item> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Item> findByCliente(int itemId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT i FROM Item i WHERE i.item.id = :itemId";
        qry = this.entityManager.createQuery(jpql, Item.class);
        qry.setParameter("itemId", itemId);
        List<Item> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Item> findBySuporte(int SuporteId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT i FROM Item i WHERE i.Suporte.id = :SuporteId";
        qry = this.entityManager.createQuery(jpql, Item.class);
        qry.setParameter("SuporteId", SuporteId);
        List<Item> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Item> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT i FROM Item i WHERE i.dataHora BETWEEN :inicio AND :fim";
        qry = this.entityManager.createQuery(jpql, Item.class);
        qry.setParameter("inicio", inicio);
        qry.setParameter("fim", fim);
        List<Item> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Item> findByTecnico(int tecnicoId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT i FROM Item i WHERE i.tecnico.id = :tecnicoId";
        qry = this.entityManager.createQuery(jpql, Item.class);
        qry.setParameter("tecnicoId", tecnicoId);
        List<Item> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Item> findByEquipe(int tecnicoId, int SuporteId) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT i FROM Item i WHERE i.tecnico.id = :tecnicoId AND i.Suporte.id = :SuporteId";
        qry = this.entityManager.createQuery(jpql, Item.class);
        qry.setParameter("tecnicoId", tecnicoId);
        qry.setParameter("SuporteId", SuporteId);
        List<Item> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Item> findByClienteEPeriodo(int itemId, LocalDateTime inicio, LocalDateTime fim) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT i FROM Item i WHERE i.item.id = :itemId AND i.dataHora BETWEEN :inicio AND :fim";
        qry = this.entityManager.createQuery(jpql, Item.class);
        qry.setParameter("itemId", itemId);
        qry.setParameter("inicio", inicio);
        qry.setParameter("fim", fim);
        List<Item> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
}

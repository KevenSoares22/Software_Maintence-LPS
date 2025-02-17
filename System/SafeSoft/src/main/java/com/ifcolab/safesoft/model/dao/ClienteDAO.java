package com.ifcolab.safesoft.model.dao;

import com.ifcolab.safesoft.factory.DatabaseJPA;
import com.ifcolab.safesoft.model.Cliente;

import java.util.List;

public class ClienteDAO extends Dao<Cliente> {
    
    public ClienteDAO(){
    }
    
    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();
        
        Cliente cliente = this.entityManager.find(Cliente.class, id);
        if (cliente == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }
        
        this.entityManager.remove(cliente);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }
    
    @Override
    public Cliente find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Cliente p = this.entityManager.find(Cliente.class, id);
        this.entityManager.close();
        return p;
    }
    
    @Override
    public List<Cliente> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Cliente c";
        qry = this.entityManager.createQuery(jpql, Cliente.class);
        List<Cliente> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public List<Cliente> filterByName(String nome) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome";
        qry = this.entityManager.createQuery(jpql, Cliente.class);
        qry.setParameter("nome", nome + "%");
        List<Cliente> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }
    
    public Cliente findByCPF(String cpf) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Cliente c WHERE c.cpf = :cpf";
        qry = this.entityManager.createQuery(jpql, Cliente.class);
        qry.setParameter("cpf", cpf);
        List<Cliente> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
    
    public Cliente findByEmail(String email) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Cliente c WHERE c.email = :email";
        qry = this.entityManager.createQuery(jpql, Cliente.class);
        qry.setParameter("email", email);
        List<Cliente> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
}

package com.ifcolab.safesoft.model.dao;

import com.ifcolab.safesoft.factory.DatabaseJPA;
import com.ifcolab.safesoft.model.Suporte;
import java.util.List;

public class SuporteDAO extends Dao<Suporte> {

    public SuporteDAO() {
    }

    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();

        Suporte Suporte = this.entityManager.find(Suporte.class, id);
        if (Suporte == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }

        this.entityManager.remove(Suporte);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }

    @Override
    public Suporte find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Suporte c = this.entityManager.find(Suporte.class, id);
        this.entityManager.close();
        return c;
    }

    @Override
    public List<Suporte> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Suporte t";
        qry = this.entityManager.createQuery(jpql, Suporte.class);
        List<Suporte> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }

    public List<Suporte> filterByName(String nome) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Suporte t WHERE c.name LIKE :nome";
        qry = this.entityManager.createQuery(jpql, Suporte.class);
        qry.setParameter("nome", nome + "%");
        List<Suporte> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }

    public Suporte findByCPF(String cpf) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT c FROM Suporte t WHERE c.cpf = :cpf";
        qry = this.entityManager.createQuery(jpql, Suporte.class);
        qry.setParameter("cpf", cpf);
        List<Suporte> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }

    public Suporte findByEmail(String email) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT t FROM Suporte t WHERE t.email = :email";
        qry = this.entityManager.createQuery(jpql, Suporte.class);
        qry.setParameter("email", email);
        List<Suporte> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
}

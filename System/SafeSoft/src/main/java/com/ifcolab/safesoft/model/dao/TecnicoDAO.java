package com.ifcolab.safesoft.model.dao;

import com.ifcolab.safesoft.factory.DatabaseJPA;
import com.ifcolab.safesoft.model.Tecnico;
import java.util.List;

public class TecnicoDAO extends Dao<Tecnico> {

    public TecnicoDAO() {
    }

    @Override
    public boolean delete(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        this.entityManager.getTransaction().begin();

        Tecnico tecnico = this.entityManager.find(Tecnico.class, id);
        if (tecnico == null) {
            this.entityManager.getTransaction().rollback();
            return false;
        }

        this.entityManager.remove(tecnico);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return true;
    }

    @Override
    public Tecnico find(int id) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        Tecnico m = this.entityManager.find(Tecnico.class, id);
        this.entityManager.close();
        return m;
    }

    @Override
    public List<Tecnico> findAll() {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT m FROM Tecnico m";
        qry = this.entityManager.createQuery(jpql, Tecnico.class);
        List<Tecnico> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }

    public List<Tecnico> filterByName(String nome) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT m FROM Tecnico m WHERE m.name LIKE :nome";
        qry = this.entityManager.createQuery(jpql, Tecnico.class);
        qry.setParameter("nome", nome + "%");
        List<Tecnico> lst = qry.getResultList();
        this.entityManager.close();
        return lst;
    }

    public Tecnico findByCPF(String cpf) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT m FROM Tecnico m WHERE m.cpf = :cpf";
        qry = this.entityManager.createQuery(jpql, Tecnico.class);
        qry.setParameter("cpf", cpf);
        List<Tecnico> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }

    public Tecnico findByEmail(String email) {
        this.entityManager = DatabaseJPA.getInstance().getEntityManager();
        jpql = "SELECT m FROM Tecnico m WHERE m.email = :email";
        qry = this.entityManager.createQuery(jpql, Tecnico.class);
        qry.setParameter("email", email);
        List<Tecnico> lst = qry.getResultList();
        this.entityManager.close();
        return !lst.isEmpty() ? lst.get(0) : null;
    }
}

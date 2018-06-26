package com.company.Persistence;

import com.company.Modelo.Pais;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alumno on 29/05/2018.
 */
@Repository
public class PaisDao extends AbstractDao<Pais> {

    @Autowired
    public PaisDao (SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Pais> getAll() {


        Session session = this.sessionFactory.openSession();
        List<Pais> Listpaises = session.createQuery("FROM country").list();
        session.close();
        return Listpaises;
    }

    @Override
    public Pais getById(int id) {
        Session session = this.sessionFactory.openSession();
        Pais pais = (Pais) session.get(Pais.class, id);
        session.close();
        return pais;
    }

    @Override
    public void save(Pais value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(value);
        t.commit();
        session.close();
    }

    public Pais get(String iso) {
        Session session = this.sessionFactory.openSession();
        Pais pais = (Pais) session.createQuery("FROM Pais WHERE Iso = :u").setParameter("u", iso).uniqueResult();
        session.close();
        return pais;
    }


    }


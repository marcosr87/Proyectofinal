package com.company.Persistence;

import com.company.Modelo.Estado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alumno on 28/05/2018.
 */
@Repository
public class EstadoDao extends AbstractDao<Estado> {

    @Autowired
    public EstadoDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Estado> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Estado> Liststates = session.createQuery("FROM state").list();
        session.close();
        return Liststates;
    }

    @Override
    public Estado getById(int id) {
        Session session = this.sessionFactory.openSession();
        Estado states = (Estado) session.get(Estado.class, id);
        session.close();
        return states;
    }

    @Override
   public void save(Estado value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(value);
        t.commit();
        session.close();
    }

    public Estado get(String iata) {
        Session session = this.sessionFactory.openSession();
        Estado estado = (Estado) session.createQuery("FROM Estado WHERE Iata_code = :u").setParameter("u", iata).uniqueResult();
        session.close();
        return estado;
    }

}

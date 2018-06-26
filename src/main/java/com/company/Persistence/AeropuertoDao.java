package com.company.Persistence;

import com.company.Modelo.Aeropuertos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by alumno on 27/05/2018.
 */
@Repository
public class AeropuertoDao extends AbstractDao<Aeropuertos> {

    @Autowired
    public AeropuertoDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
   public List<Aeropuertos> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Aeropuertos> Listairports = session.createQuery("FROM Aeropuertos").list();
        session.close();
        return Listairports;
    }

    @Override
    public Aeropuertos getById(int id) {
        Session session = this.sessionFactory.openSession();
        Aeropuertos aeropuerto = (Aeropuertos) session.get(Aeropuertos.class, id);
        session.close();
        return aeropuerto;
    }

    @Override
    public void save(Aeropuertos value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(value);

        t.commit();
        session.close();
    }

    public void update(Aeropuertos value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.update(value);
        t.commit();
        session.close();
    }


    public void delete (Aeropuertos aero)

    {
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.delete(aero);
        t.commit();
        session.close();

    }

    public Aeropuertos get(String iata) {
        Session session = this.sessionFactory.openSession();
        Aeropuertos aeropueto = (Aeropuertos) session.createQuery("FROM Aeropuertos WHERE IATA = :u").setParameter("u", iata).uniqueResult();
        session.close();
        return aeropueto;
    }
}

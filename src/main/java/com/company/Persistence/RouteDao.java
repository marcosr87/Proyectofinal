package com.company.Persistence;

import com.company.Modelo.Ruta;
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
public class RouteDao extends AbstractDao<Ruta> {
    @Autowired
    public RouteDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Ruta> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Ruta> Listroad = session.createQuery("FROM Ruta").list();
        session.close();
        return Listroad;
    }

    @Override
    public Ruta getById(int id) {
        Session session = this.sessionFactory.openSession();
        Ruta road = (Ruta) session.get(Ruta.class, id);
        session.close();
        return road;
    }

    @Override
   public void save(Ruta value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(value);
        t.commit();
        session.close();
    }

    public void delete (Ruta ruta)

    {
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.delete(ruta);
        t.commit();
        session.close();

    }


    }



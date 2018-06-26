package com.company.Persistence;

import com.company.Modelo.Precio;
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
public class PrecioDao extends AbstractDao<Precio> {

    @Autowired
    public PrecioDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Precio> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Precio> Listprices = session.createQuery("FROM airports").list();
        session.close();
        return Listprices;
    }

    @Override
    public Precio getById(int id) {
        Session session = this.sessionFactory.openSession();
        Precio prices = (Precio) session.get(Precio.class, id);
        session.close();
        return prices;
    }

    @Override
   public void save(Precio value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(value);
        t.commit();
        session.close();
    }

    public void delete(Precio value)
    {
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.delete(value);
        t.commit();
        session.close();

    }
}

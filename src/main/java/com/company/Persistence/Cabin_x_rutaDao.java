package com.company.Persistence;

import com.company.Modelo.Cabin_x_ruta;
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
public class Cabin_x_rutaDao extends AbstractDao<Cabin_x_ruta> {
    @Autowired
    public Cabin_x_rutaDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Cabin_x_ruta> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Cabin_x_ruta> Listcabinrut = session.createQuery("FROM Cabin_x_ruta").list();
        session.close();
        return Listcabinrut;
    }

    @Override
    public Cabin_x_ruta getById(int id) {
        Session session = this.sessionFactory.openSession();
        Cabin_x_ruta cabins = (Cabin_x_ruta) session.get(Cabin_x_ruta.class, id);
        session.close();
        return cabins;
    }

    @Override
    public void save(Cabin_x_ruta value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(value);
        t.commit();
        session.close();
    }

    public void delete(Cabin_x_ruta value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.delete(value);
        t.commit();
        session.close();
    }

}

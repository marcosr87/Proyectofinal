package com.company.Persistence;

import com.company.Modelo.Cabina;
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
public class CabinaDao extends AbstractDao<Cabina> {

    @Autowired
    public CabinaDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Cabina> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Cabina> Listcabinas = session.createQuery("FROM Cabina").list();
        session.close();
        return Listcabinas;
    }

    @Override
    public Cabina getById(int id) {
        Session session = this.sessionFactory.openSession();
        Cabina cabin = (Cabina) session.get(Cabina.class, id);
        session.close();
        return cabin;
    }

    @Override
    public void save(Cabina value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(value);
        t.commit();
        session.close();
    }

    public Cabina get(String nombre) {
        Session session = this.sessionFactory.openSession();
        Cabina cabina = (Cabina) session.createQuery("FROM Cabina where name = :nombre").setParameter("nombre", nombre).list();
        session.close();
        return cabina;

    }

    public  void  delete (Cabina cab)
    {
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.delete(cab);
        t.commit();
        session.close();

    }

    public void update(Cabina value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.update(value);
        t.commit();
        session.close();
    }
}

package com.company.Persistence;

import com.company.Modelo.Ciudad;
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
public class CiudadDao extends AbstractDao<Ciudad> {

    @Autowired
    public CiudadDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Ciudad> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Ciudad> Listcitys = session.createQuery("FROM city").list();
        session.close();
        return Listcitys;
    }

    @Override
    public Ciudad getById(int id) {
        Session session = this.sessionFactory.openSession();
        Ciudad city = (Ciudad) session.get(Ciudad.class, id);
        session.close();
        return city;
    }

    @Override
    public void save(Ciudad value) {

        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(value);
        t.commit();
        session.close();
    }

    public Ciudad get(String iata) {
        Session session = this.sessionFactory.openSession();
        Ciudad ciudad = (Ciudad) session.createQuery("FROM Ciudad WHERE Iata_code = :u").setParameter("u", iata).uniqueResult();
        session.close();
        return ciudad;
    }


}

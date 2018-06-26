package com.company.Persistence;

import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by alumno on 27/05/2018.
 */
public abstract class AbstractDao<K> {

    protected SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    abstract List<K> getAll();
    abstract K getById(int id);
    abstract void save(K value);



}
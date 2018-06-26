package com.company.Persistence;

import com.company.Modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alumno on 15/06/2018.
 */
@Repository
public class UsuarioDao extends  AbstractDao<Usuario> {


    @Autowired
    public UsuarioDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    List<Usuario> getAll() {
        return null;
    }

    @Override
    Usuario getById(int id) {
        return null;
    }

    @Override
    void save(Usuario value) {

    }


    public Usuario get(String nombreUsuario, String password) {

        Session session = this.sessionFactory.openSession();
        List<Usuario> list = session.createQuery("FROM Usuario where nombreUsuario = :u and password = :p").setParameter("u", nombreUsuario).setParameter("p", password).list();
        session.close();
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

}


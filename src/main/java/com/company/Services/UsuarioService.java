package com.company.Services;

import com.company.Modelo.Usuario;
import com.company.Persistence.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alumno on 15/06/2018.
 */

@Service
public class UsuarioService {

    UsuarioDao usuarioDao;

    @Autowired
    public UsuarioService(UsuarioDao dao) {
        this.usuarioDao = dao;
    }

    public Usuario login(String nombreUsuario, String password) {
        return usuarioDao.get(nombreUsuario, password);
    }
}

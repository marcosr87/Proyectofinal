package com.company.Util;

import com.company.Modelo.Usuario;
import org.joda.time.DateTime;

/**
 * Created by alumno on 14/06/2018.
 */
public class AuthenticationData {

    private Usuario usuario;
    private DateTime lastAction;

    public DateTime getLastAction() {
        return lastAction;
    }

    public void setLastAction(DateTime lastAction) {
        this.lastAction = lastAction;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

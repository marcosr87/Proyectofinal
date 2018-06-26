package com.company.Util;

import com.company.Modelo.Usuario;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by alumno on 14/06/2018.
 */
@Service
public class SessionData {

    static final java.util.logging.Logger LOGGER = Logger.getLogger(String.valueOf(SessionData.class));
    private HashMap<String, AuthenticationData> sessionData;

    @org.springframework.beans.factory.annotation.Value("${session.expiration}")
    private int expirationTime;


    public SessionData() {
        this.sessionData = new HashMap<String, AuthenticationData>();
    }

    public String addSession(Usuario usuario) {
        String sessionId = UUID.randomUUID().toString();
        AuthenticationData aData = new AuthenticationData();
        aData.setUsuario(usuario);
        aData.setLastAction(new DateTime());
        this.sessionData.put(sessionId, aData);
        return sessionId;
    }


    public void removeSession(String sessionId) {
        sessionData.remove(sessionId);
    }

    public AuthenticationData getSession(String sessionId) {
        AuthenticationData aData = this.sessionData.get(sessionId);
        if (aData != null) {
            return aData;
        } else {
            return null;
        }
    }

    @Scheduled(fixedRate = 5000)
    public void checkSessions() {
        System.out.println("Checking sessions");
        Set<String> sessionsId = this.sessionData.keySet();
        for (String sessionId : sessionsId) {
            AuthenticationData aData = this.sessionData.get(sessionId);
            if (aData.getLastAction().plusSeconds(expirationTime).
                    isBefore(System.currentTimeMillis())) {
                System.out.println("Deleting sessionId = " + sessionId);
                this.sessionData.remove(sessionId);
            }
        }
    }
}

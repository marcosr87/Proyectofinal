package com.company.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alumno on 15/06/2018.
 */
public class LoginResponseWrapper {

    @JsonProperty
    private String sessionId;


    public LoginResponseWrapper() {

    }

    public LoginResponseWrapper(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}

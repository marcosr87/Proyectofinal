package com.company.Util;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;

import org.hibernate.cfg.Configuration;

/**
 * Created by alumno on 28/05/2018.
 */

@org.springframework.context.annotation.Configuration
public class HibernateUtil {

    private static final SessionFactory
            SESSION_FACTORY = buildSessionFactory();

    public HibernateUtil() {
    }

    @Bean
    private  static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static  SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static  void shutdown() {
        getSessionFactory().close();
    }


}

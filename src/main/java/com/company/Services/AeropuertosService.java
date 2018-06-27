package com.company.Services;

import com.company.Modelo.Aeropuertos;
import com.company.Persistence.AeropuertoDao;
import com.company.Util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;


/**
 * Created by alumno on 27/05/2018.
 */
@Service
public class AeropuertosService {

    @Autowired
    AeropuertoDao aeropuertoDao;

    public  AeropuertosService(){
        this.aeropuertoDao = new AeropuertoDao(HibernateUtil.getSessionFactory());
    }

    public List<Aeropuertos> getAll() {
        return aeropuertoDao.getAll();
    }


    public Aeropuertos getAeropuertos(int id) {
        return aeropuertoDao.getById(id);
    }

    public void save(Aeropuertos a)  throws ParseException {


        this.aeropuertoDao.save(a);


         }

         public void update ( Aeropuertos a)  throws ParseException {

        this.aeropuertoDao.update(a);
         }


    public void delete (Aeropuertos aero)
    {this.aeropuertoDao.delete(aero);}

    public Aeropuertos get(String surname) {
        return aeropuertoDao.get(surname);
    }

}

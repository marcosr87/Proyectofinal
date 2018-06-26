package com.company.Services;

import com.company.DTO.PaisDTO;
import com.company.Modelo.Pais;
import com.company.Persistence.PaisDao;
import com.company.Util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by alumno on 27/05/2018.
 */
@Service
public class PaisService  {

    @Autowired
    PaisDao paisDao;

    public  PaisService(){
        this.paisDao = new PaisDao(HibernateUtil.getSessionFactory());
    }

    public List<Pais> getAll() {
        return paisDao.getAll();
    }


    public Pais getPaises(int id) {
        return paisDao.getById(id);
    }

    public void save (PaisDTO paisDTO)  throws ParseException {
        Pais p = new Pais(paisDTO);
        this.paisDao.save(p);

    }



    public Pais get(String surname) {
        return paisDao.get(surname);
    }

}

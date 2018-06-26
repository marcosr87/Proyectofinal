package com.company.Services;

import com.company.DTO.CiudadDTO;
import com.company.Modelo.Ciudad;
import com.company.Modelo.Estado;
import com.company.Persistence.CiudadDao;
import com.company.Util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alumno on 27/05/2018.
 */
@Service
public class CiudadService {

    @Autowired
    CiudadDao ciudadDao;

    public CiudadService(){this.ciudadDao = new CiudadDao(HibernateUtil.getSessionFactory());}

    public List<Ciudad> getAll() {return ciudadDao.getAll();}

    public Ciudad getCiudades( int id){ return ciudadDao.getById(id); }

    public void save(CiudadDTO dto, Estado estado)
    {
        Ciudad c = new Ciudad(dto,estado);

        ciudadDao.save(c);
    }
    public Ciudad get(String surname) {
        return ciudadDao.get(surname);
    }
}

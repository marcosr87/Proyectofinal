package com.company.Services;

import com.company.DTO.EstadoDTO;
import com.company.Modelo.Estado;
import com.company.Modelo.Pais;
import com.company.Persistence.EstadoDao;
import com.company.Util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alumno on 27/05/2018.
 */
@Service
public class EstadoService {

    @Autowired
    EstadoDao estadoDao;

    public  EstadoService(){this.estadoDao = new EstadoDao(HibernateUtil.getSessionFactory()); }

    public List<Estado> getAll(){ return estadoDao.getAll();}

    public Estado getEstados(int id){ return estadoDao.getById(id);}

    public void save(EstadoDTO dto, Pais pais)
    {
        Estado e = new Estado(dto,pais);

        this.estadoDao.save(e);
    }

    public Estado get(String surname) {
        return estadoDao.get(surname);
    }

}

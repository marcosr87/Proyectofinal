package com.company.Services;

import com.company.DTO.PrecioDTO;
import com.company.Modelo.Cabin_x_ruta;
import com.company.Modelo.Precio;
import com.company.Persistence.PrecioDao;
import com.company.Util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alumno on 27/05/2018.
 */
@Service
public class PrecioService {

    @Autowired
    PrecioDao precioDao;

    public PrecioService(){this.precioDao = new PrecioDao(HibernateUtil.getSessionFactory());}

    public List<Precio> getAll(){return precioDao.getAll();}

    public Precio getprecios(int id){return precioDao.getById(id);}

    public void save(Cabin_x_ruta c, PrecioDTO p)

    {
        Precio precio = new Precio(c,p);
        precioDao.save(precio);
    }

    public void delete (Precio value)
    {
        this.precioDao.delete(value);
    }
}

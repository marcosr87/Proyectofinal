package com.company.Services;

import com.company.Modelo.Cabin_x_ruta;
import com.company.Modelo.Cabina;
import com.company.Modelo.Ruta;
import com.company.Persistence.Cabin_x_rutaDao;
import com.company.Util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alumno on 27/05/2018.
 */
@Service
public class Cabin_x_rutaService {

    @Autowired
    Cabin_x_rutaDao cabin_x_rutaDao;

    public Cabin_x_rutaService (){this.cabin_x_rutaDao = new Cabin_x_rutaDao(HibernateUtil.getSessionFactory());}

    public List<Cabin_x_ruta> getAll(){return cabin_x_rutaDao.getAll();}

    public Cabin_x_ruta getById(int id){return cabin_x_rutaDao.getById(id);}

    public void save (Cabina c, Ruta r)
    {
        Cabin_x_ruta cxr = new Cabin_x_ruta(c , r);

        this.cabin_x_rutaDao.save(cxr);
    }

    public void delete (Cabin_x_ruta value)
    {
        this.cabin_x_rutaDao.delete(value);
    }
}

package com.company.Services;

import com.company.Modelo.Aeropuertos;
import com.company.Modelo.Ruta;
import com.company.Persistence.RouteDao;
import com.company.Util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alumno on 27/05/2018.
 */
@Service
public class RutaService {

    @Autowired
    RouteDao routeDao;

    public RutaService(){this.routeDao = new RouteDao(HibernateUtil.getSessionFactory());}

    public List<Ruta> getAll(){ return routeDao.getAll(); }

    public Ruta getRutaService (int id){return routeDao.getById(id);}

    public void save(Aeropuertos origen, Aeropuertos destino)
    {
        Ruta r = new Ruta(origen,destino);
        routeDao.save(r);
    }
    public void delete (Ruta road)
    {this.routeDao.delete(road);}


}

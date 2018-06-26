package com.company.Services;

import com.company.DTO.CabinaDTO;
import com.company.Modelo.Cabina;
import com.company.Persistence.CabinaDao;
import com.company.Util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by alumno on 27/05/2018.
 */
@Service
public class CabinaService {

    @Autowired
    CabinaDao cabinaDao;

    public CabinaService(){this.cabinaDao = new CabinaDao (HibernateUtil.getSessionFactory());}

    public List<Cabina> getAll(){return cabinaDao.getAll();}

    public Cabina getById(int id){ return cabinaDao.getById(id); }

    public void save (CabinaDTO dto)
    {
        Cabina c = new Cabina(dto);

        this.cabinaDao.save(c);
    }

    public Cabina get(String name) {
        return cabinaDao.get(name);
    }

    public void update ( Cabina a)  throws ParseException {

        this.cabinaDao.update(a);


    }


    public void delete (Cabina value)
    {
        this.cabinaDao.delete(value);
    }

}

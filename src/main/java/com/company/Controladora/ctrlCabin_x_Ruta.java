package com.company.Controladora;

import com.company.DTO.CabinaxrutaDTO;
import com.company.Exceptions.RestBadRequestException;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Exceptions.RestNotFoundException;
import com.company.Exceptions.RestValidationErrorException;
import com.company.Modelo.Cabin_x_ruta;
import com.company.Modelo.Cabina;
import com.company.Modelo.Ruta;
import com.company.Services.Cabin_x_rutaService;
import com.company.Services.CabinaService;
import com.company.Services.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alumno on 24/06/2018.
 */
@RestController
@RequestMapping(value = "/Cabinaxruta", produces = MediaType.APPLICATION_JSON_VALUE)
public class ctrlCabin_x_Ruta {
    @Autowired
    RutaService rutaService;

    @Autowired
    CabinaService cabinaService;

    @Autowired
    Cabin_x_rutaService cabin_x_rutaService;

    /// POST
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity post(@RequestBody CabinaxrutaDTO dto) {
        Ruta route =rutaService.getRutaService(dto.getIdRuta());
        Cabina cabin = cabinaService.getById(dto.getIdcabina());

        try {
             cabin_x_rutaService.save(cabin,route);
        } catch (ConstraintViolationException e) {
            throw new RestValidationErrorException(e.getConstraintViolations());
        } catch (Exception e) {
            throw new RestInternalServerErrorException(e.getMessage());
        }
        return new ResponseEntity<>( HttpStatus.CREATED);
        //return new ResponseEntity(new PriceDTO(price), HttpStatus.CREATED);
    }

    /// DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity delete(@PathVariable("id") int id) {
        if (id > 0) {
            Cabin_x_ruta cabin_x_ruta = cabin_x_rutaService.getById(id);
            if (cabin_x_ruta != null) {
                try {
                    cabin_x_rutaService.delete(cabin_x_ruta);
                } catch (Exception e) {
                    throw new RestInternalServerErrorException(e.getMessage());
                }
                return new ResponseEntity(HttpStatus.OK);
            } else {
                throw new RestNotFoundException(String.format("Cabixruta %s not found", id));
            }
        } else {
            throw new RestBadRequestException("id invalido");
        }
    }

    /// GET ALL
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<CabinaxrutaDTO>> get() {
        List<CabinaxrutaDTO> list = new LinkedList<>();
        try {
            List<Cabin_x_ruta> auxc = cabin_x_rutaService.getAll();
            for (Cabin_x_ruta cabina : auxc) {
                    list.add(new CabinaxrutaDTO(cabina.getCabina(),cabina.getRuta()));
                }

        } catch (Exception e) {
            throw new RestInternalServerErrorException(e.getMessage());
        }
        if (list.size() > 0) {
            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }


}

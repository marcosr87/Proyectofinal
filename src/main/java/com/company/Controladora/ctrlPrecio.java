package com.company.Controladora;

import com.company.DTO.PrecioDTO;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Exceptions.RestValidationErrorException;
import com.company.Modelo.Cabin_x_ruta;
import com.company.Services.Cabin_x_rutaService;
import com.company.Services.PrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

/**
 * Created by alumno on 16/05/2018.
 */
@RestController
@RequestMapping(value = "/Precio", produces = MediaType.APPLICATION_JSON_VALUE)

public class ctrlPrecio {
    @Autowired
    PrecioService precioService;

    @Autowired
    Cabin_x_rutaService cabin_x_rutaService;

    /// POST
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity post(@RequestBody PrecioDTO dto) {

        Cabin_x_ruta route = cabin_x_rutaService.getById(dto.getId_cabin_x_ruta());
                try {
             precioService.save(route,dto);
        } catch (ConstraintViolationException e) {
            throw new RestValidationErrorException(e.getConstraintViolations());
        } catch (Exception e) {
            throw new RestInternalServerErrorException(e.getMessage());
        }
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

}

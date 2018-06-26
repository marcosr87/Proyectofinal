package com.company.Controladora;

import com.company.DTO.CiudadDTO;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Exceptions.RestValidationErrorException;
import com.company.Modelo.Estado;
import com.company.Services.CiudadService;
import com.company.Services.EstadoService;
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
@RequestMapping(value = "/Ciudad", produces = MediaType.APPLICATION_JSON_VALUE)

public class ctrlCiudad {
    @Autowired
    private EstadoService estadoService;
    @Autowired
    private CiudadService ciudadService;

    //POST
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<CiudadDTO> post(@RequestBody CiudadDTO dto) {
        Estado estado = estadoService.get(dto.getIataCodeState());
        //  System.out.println("PaIS" + pais.getName());
        try {
            ciudadService.save(dto,estado); // Persisito el Country

        } catch (ConstraintViolationException e) { // Error de validacion
            throw new RestValidationErrorException(e.getConstraintViolations());
        } catch (Exception e) {
            throw new RestInternalServerErrorException(e.getMessage());
        }
        return new ResponseEntity<>( HttpStatus.CREATED);

    }

}

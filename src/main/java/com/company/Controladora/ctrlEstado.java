package com.company.Controladora;

import com.company.DTO.EstadoDTO;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Exceptions.RestValidationErrorException;
import com.company.Modelo.Pais;
import com.company.Services.EstadoService;
import com.company.Services.PaisService;
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
@RequestMapping(value = "/Estado", produces = MediaType.APPLICATION_JSON_VALUE)

public class ctrlEstado {
    @Autowired
    private EstadoService estadoService;
    @Autowired
    private PaisService paisService;



    //POST
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<EstadoDTO> post(@RequestBody EstadoDTO dto) {
        Pais pais = paisService.get(dto.getIso());
      //  System.out.println("PaIS" + pais.getName());
        try {
            estadoService.save(dto,pais); // Persisito el Country

        } catch (ConstraintViolationException e) { // Error de validacion
            throw new RestValidationErrorException(e.getConstraintViolations());
        } catch (Exception e) {
            throw new RestInternalServerErrorException(e.getMessage());
        }
        return new ResponseEntity<>( HttpStatus.CREATED);

    }

}

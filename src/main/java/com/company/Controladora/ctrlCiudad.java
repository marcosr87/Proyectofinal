package com.company.Controladora;

import com.company.DTO.CiudadDTO;
import com.company.Exceptions.RestBadRequestException;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Exceptions.RestNotFoundException;
import com.company.Exceptions.RestValidationErrorException;
import com.company.Modelo.Ciudad;
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

    @RequestMapping(value = "/{iataCode}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity getByIataCode(@PathVariable("iataCode") String iataCode) {
        if (!iataCode.equalsIgnoreCase("")) {
            iataCode = iataCode.toLowerCase();
            Ciudad city;
            try {
                city = ciudadService.get(iataCode);
            } catch (Exception e) {
                throw new RestInternalServerErrorException(e.getMessage());
            }
            if (city != null) {
                return new ResponseEntity(new CiudadDTO(city.getIata_code(),city.getName(),city.getEstado().getIata_code()), HttpStatus.OK);
            } else {
                throw new RestNotFoundException(String.format("City %s not found", iataCode));
            }
        } else {
            throw new RestBadRequestException("iataCode no puede ser vacio");
        }
    }

}

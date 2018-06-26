package com.company.Controladora;

import com.company.DTO.PaisDTO;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Exceptions.RestValidationErrorException;
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
@RequestMapping(value = "/Pais", produces = MediaType.APPLICATION_JSON_VALUE)

public class ctrlPais {

    @Autowired
    private PaisService paisService;

    //POST
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<PaisDTO> post(@RequestBody PaisDTO dto) {
       // if (paisService.getByISO(dto.getIsoCode()) == null) { // Si no hay un Country con el isoCode que viene en el POST

            System.out.println(dto.getIsoCode()+"isocode");
        System.out.println(dto.getName()+":name");
            try {
                 paisService.save(dto); // Persisito el Country

            } catch (ConstraintViolationException e) { // Error de validacion
                throw new RestValidationErrorException(e.getConstraintViolations());
            } catch (Exception e) {
                throw new RestInternalServerErrorException(e.getMessage());
            }
            return new ResponseEntity<>( HttpStatus.CREATED);

    }
}

package com.company.Controladora;

import com.company.DTO.PaisDTO;
import com.company.Exceptions.RestBadRequestException;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Exceptions.RestNotFoundException;
import com.company.Exceptions.RestValidationErrorException;
import com.company.Modelo.Pais;
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
    @RequestMapping(value = "/{isoCode}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<PaisDTO> getByIsoCode(@PathVariable("isoCode") String isoCode) {
        if (!isoCode.isEmpty()) { // Si el isoCode no esta vacio
            isoCode = isoCode.toLowerCase();
            Pais country; // Defino una var country
            try { // Intento traerlo por isoCode
                country = paisService.get(isoCode);
            } catch (Exception e) {
                throw new RestInternalServerErrorException(e.getMessage());
            }
            if (country != null) { // Si lo encuentro
                return new ResponseEntity<>(new PaisDTO(country.getIso(),country.getName()), HttpStatus.OK); // Lo retorno
            } else { // Si no lo encuentro
                throw new RestNotFoundException(String.format("Country %s not found", isoCode));
            }
        } else { // El isoCode esta vacio
            throw new RestBadRequestException("isoCode no puede ser vacio");
        }
    }

}

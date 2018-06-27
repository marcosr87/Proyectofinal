package com.company.Controladora;

import com.company.DTO.AeropuertosDTO;
import com.company.Exceptions.RestBadRequestException;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Exceptions.RestNotFoundException;
import com.company.Exceptions.RestValidationErrorException;
import com.company.Modelo.Aeropuertos;
import com.company.Modelo.Ciudad;
import com.company.Services.AeropuertosService;
import com.company.Services.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alumno on 16/05/2018.
 */

@RestController
@RequestMapping(value = "/Aeropuertos", produces = MediaType.APPLICATION_JSON_VALUE)

public class ctrlAeropuerto {
@Autowired
private AeropuertosService service;

@Autowired
private CiudadService ciudadService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<AeropuertosDTO> post(@RequestBody AeropuertosDTO dto) {
        Ciudad ciudad = ciudadService.get(dto.getCity_iataCode());
        Aeropuertos a = new Aeropuertos(dto,ciudad);
        //  System.out.println("PaIS" + pais.getName());
        try {
            service.save(a); // Persisito el Country

        } catch (ConstraintViolationException e) { // Error de validacion
            throw new RestValidationErrorException(e.getConstraintViolations());
        } catch (Exception e) {
            throw new RestInternalServerErrorException(e.getMessage());
        }
        return new ResponseEntity<>( HttpStatus.CREATED);

    }

    /// GET ALL
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<AeropuertosDTO>> get() {
        List<AeropuertosDTO> list = new LinkedList<>();
        try {
            List<Aeropuertos> aux = service.getAll();
            for (Aeropuertos airport : aux) {
                list.add(new AeropuertosDTO(airport));
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

    /// PUT
    @RequestMapping(value = "/{iataCode}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity put(@PathVariable("iataCode") String iataCode, @RequestBody AeropuertosDTO dto) {
        if (!iataCode.isEmpty()) {
            iataCode = iataCode.toLowerCase();
            Aeropuertos airport = service.get(iataCode);
            if (airport != null) {
               airport.setName(dto.getName());
                airport.setLatitude(dto.getLatitude());
                airport.setLongitud(dto.getLongitude());
                Ciudad city;
                city = ciudadService.get(dto.getCity_iataCode().toLowerCase());
                if (city == null) {
                    throw new RestNotFoundException("City no encontrado");
                }
                try {
                    service.update(airport);
                } catch (ConstraintViolationException e) {
                    throw new RestValidationErrorException(e.getConstraintViolations());
                } catch (Exception e) {
                    throw new RestInternalServerErrorException(e.getMessage());
                }
                return new ResponseEntity(HttpStatus.OK);
            } else {
                throw new RestNotFoundException(String.format("Airport %s not found", iataCode));
            }
        } else {
            throw new RestBadRequestException("iataCode no puede estar vacio");
        }
    }
  //PATCH
    @RequestMapping(value = "/{iataCode}", method = RequestMethod.PATCH)
    public @ResponseBody
    ResponseEntity patch(@PathVariable("iataCode") String iataCode, @RequestBody AeropuertosDTO dto) {
        if (!iataCode.isEmpty()) {
            iataCode = iataCode.toLowerCase();
            Aeropuertos airport = service.get(iataCode);
            if (airport != null) {
                if (dto.getName() != null) {
                    airport.setName(dto.getName());
                }
                if (dto.getLatitude() != null) {
                    airport.setLatitude(dto.getLatitude());
                }
                if (dto.getLongitude() != null) {
                    airport.setLongitud(dto.getLongitude());
                }

                if (dto.getCity_iataCode() != null) {
                    Ciudad city;
                    city = ciudadService.get(dto.getCity_iataCode());
                    if (city == null) {
                        throw new RestNotFoundException("City no encontrado");
                    }
                }
                try {
                    service.update(airport);
                } catch (ConstraintViolationException e) {
                    throw new RestValidationErrorException(e.getConstraintViolations());
                } catch (Exception e) {
                    throw new RestInternalServerErrorException(e.getMessage());
                }
                return new ResponseEntity(HttpStatus.OK);
            } else {
                throw new RestNotFoundException(String.format("Airport %s not found", iataCode));
            }
        } else {
            throw new RestBadRequestException("iataCode no puede estar vacio");
        }
    }

    /// DELETE
    @RequestMapping(value = "/{iataCode}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity delete(@PathVariable("iataCode") String iataCode) {
        if (!iataCode.isEmpty()) {
            iataCode = iataCode.toLowerCase();
            Aeropuertos airport = service.get(iataCode);
            if (airport != null) {
                try {
                    service.delete(airport);
                } catch (Exception e) {
                    throw new RestInternalServerErrorException(e.getMessage());
                }
                return new ResponseEntity(HttpStatus.OK);
            } else {
                throw new RestNotFoundException(String.format("Airport %s not found", iataCode));
            }
        } else {
            throw new RestBadRequestException("iataCode no puede estar vacio");
        }
    }



}






package com.company.Controladora;

import com.company.DTO.RutaDTO;
import com.company.Exceptions.RestBadRequestException;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Exceptions.RestNotFoundException;
import com.company.Exceptions.RestValidationErrorException;
import com.company.Modelo.Aeropuertos;
import com.company.Modelo.Ruta;
import com.company.Services.AeropuertosService;
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
 * Created by alumno on 16/05/2018.
 */
@RestController
@RequestMapping(value = "/Ruta", produces = MediaType.APPLICATION_JSON_VALUE)
public class ctrlRuta {
    @Autowired
    RutaService rutaService;

    @Autowired
    AeropuertosService aeropuertosService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<RutaDTO> post(@RequestBody RutaDTO dto) {
        Aeropuertos origen = aeropuertosService.get(dto.getOrigin());
        Aeropuertos destino = aeropuertosService.get(dto.getDestination());
        //  System.out.println("PaIS" + pais.getName());
        try {
            rutaService.save(origen, destino); // Persisito el Country

        } catch (ConstraintViolationException e) { // Error de validacion
            throw new RestValidationErrorException(e.getConstraintViolations());
        } catch (Exception e) {
            throw new RestInternalServerErrorException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /// GET ALL
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<List<RutaDTO>> get() {
        List<RutaDTO> list = new LinkedList<>();
        try {
            List<Ruta> aux = rutaService.getAll();
           for (Ruta route : aux) {
            list.add(new RutaDTO(route.getAeropuertosorigin(), route.getAeropuertosdestine(),route));
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

    /// DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity delete(@PathVariable("id") int id) {
        if (id > 0) {
            Ruta route = rutaService.getRutaService(id);
            if (route != null) {
                try {
                    rutaService.delete(route);
                } catch (Exception e) {
                    throw new RestInternalServerErrorException(e.getMessage());
                }
                return new ResponseEntity(HttpStatus.OK);
            } else {
                throw new RestNotFoundException(String.format("Route %s not found", id));
            }
        } else {
            throw new RestBadRequestException("Invalid id");
        }
    }


}





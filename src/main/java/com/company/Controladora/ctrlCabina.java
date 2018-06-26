package com.company.Controladora;

import com.company.DTO.CabinaDTO;
import com.company.Exceptions.RestBadRequestException;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Exceptions.RestNotFoundException;
import com.company.Exceptions.RestValidationErrorException;
import com.company.Modelo.Cabina;
import com.company.Services.CabinaService;
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
@RequestMapping(value = "/Cabina", produces = MediaType.APPLICATION_JSON_VALUE)
public class ctrlCabina {
    @Autowired
    private CabinaService cabinaService;

    //POST
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<CabinaDTO> post(@RequestBody CabinaDTO dto) {

        try {
            cabinaService.save(dto); // Persisito el Country

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
    ResponseEntity get() {
        List<CabinaDTO> list = new LinkedList<>();
        try {
            List<Cabina> aux = cabinaService.getAll();
            for (Cabina cabin : aux) {
                list.add(new CabinaDTO(cabin));
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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity put(@PathVariable("id") int id, @RequestBody CabinaDTO dto) {
        if (id > 0) {
            Cabina cabin = cabinaService.getById(id);
            if (cabin != null) {
                cabin.setName(dto.getName());
                try {
                    cabinaService.update(cabin);
                } catch (ConstraintViolationException e) {
                    throw new RestValidationErrorException(e.getConstraintViolations());
                } catch (Exception e) {
                    throw new RestInternalServerErrorException(e.getMessage());
                }
                return new ResponseEntity(HttpStatus.OK);
            } else {
                throw new RestNotFoundException(String.format("Cabin %s not found", id));
            }
        } else {
            throw new RestBadRequestException("id invalido");
        }
    }

}

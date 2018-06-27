package com.company.ProyectoFinal;

import com.company.Controladora.ctrlAeropuerto;
import com.company.DTO.AeropuertosDTO;
import com.company.Modelo.Aeropuertos;
import com.company.Modelo.Ciudad;
import com.company.Modelo.Estado;
import com.company.Modelo.Pais;
import com.company.Services.AeropuertosService;
import com.company.Services.CiudadService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by alumno on 26/06/2018.
 */
public class ctrlAeropuertoTest {

    @Mock
    private CiudadService cityService;

    @InjectMocks
    private ctrlAeropuerto controller;

    @Mock
    private AeropuertosService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Pais per = new Pais(1,"PERU","pe");
    private Pais arg = new Pais(2,"ARGENTINA","ar");
    private Estado bsas = new Estado(1,"arg","BUENOS AIRES",arg);
    private Estado lima = new Estado(2,"per","LIMA",per);
    private Ciudad mdq = new Ciudad(1, "mdq", "Mar del Plata", bsas);
    //private Ciudad bue = new Ciudad(2, "BUE", "Buenos Aires", bsas);
    private Ciudad lim = new Ciudad(2,"lim","LIMA",lima);

    /// GET ALL
    @Test
    public void verifyGetAllEmpty() {
        List<Aeropuertos> list = new ArrayList<>();
        when(service.getAll()).thenReturn(list);
        ResponseEntity response = controller.get();
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
    @Test
    public void GetAll() {
        List<Aeropuertos> list = new ArrayList<>();
        list.add(new Aeropuertos(1, "MDQ", "Astor Piazzolla", "-23.5", "14.3", mdq));
        list.add(new Aeropuertos(2, "LIM", "Jorge Chavezzz", "-45.5", "19.3", lim));

        List<AeropuertosDTO> listdto = new ArrayList<>();
        listdto.add(new AeropuertosDTO(new Aeropuertos(1, "MDQ", "Astor Piazzolla", "-23.5", "14.3", mdq)));
        listdto.add(new AeropuertosDTO(new Aeropuertos(2, "LIM", "Jorge Chavezzz", "-45.5", "19.3", lim)));

        when(service.getAll()).thenReturn(list);
        ResponseEntity response = controller.get();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((List<AeropuertosDTO>) response.getBody()).size(), listdto.size());
    }

    /// POST
    @Test
    public void verifyPost() {
        AeropuertosDTO dto = new AeropuertosDTO("mdq", "Astor Piazzolla","-23.5" , "14.3", "mdq");
        Aeropuertos airport = new Aeropuertos(dto, mdq);
        when(cityService.get(any(String.class))).thenReturn(mdq);
        ResponseEntity response = controller.post(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());


    }
/*
    @Test
    public void verifyPostThrowsRestInternalServerErrorException() throws ParseException {
        when(service.save((Aeropuertos.class))).thenThrow(Exception.class);
        when(cityService.get(any(String.class))).thenReturn(mdq);
        try {
            controller.post(new AeropuertosDTO("mdq", "Astor Piazzolla","-23.5" , "14.3", "mdq"));
            Assert.fail();
        } catch (Exception e) {
            assertTrue(e instanceof RestInternalServerErrorException);
        }
    }

*/

}

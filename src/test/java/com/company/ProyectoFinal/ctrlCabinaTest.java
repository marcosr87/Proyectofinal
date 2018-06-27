package com.company.ProyectoFinal;

import com.company.Controladora.ctrlCabina;
import com.company.DTO.CabinaDTO;
import com.company.Exceptions.RestInternalServerErrorException;
import com.company.Modelo.Cabina;
import com.company.Services.CabinaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by alumno on 27/06/2018.
 */
public class ctrlCabinaTest {
    @InjectMocks
    private ctrlCabina controller;

    @Mock
    private CabinaService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // GET ALL
    @Test
    public void GetAll() {
        List<Cabina> list = new ArrayList<>();
        list.add(new Cabina(1, "ECONOMICAAA"));
        list.add(new Cabina(2,"PLUS"));
        list.add(new Cabina(3, "BUSINESS"));
        list.add(new Cabina(4, "PRIMERA"));
        List<CabinaDTO> lista = new ArrayList<>();
        lista.add(new CabinaDTO(new Cabina(1, "ECONOMICAAA")));
        lista.add(new CabinaDTO(new Cabina(2,"PLUS")));
        lista.add(new CabinaDTO(new Cabina(3,"BUSINESS")));
        lista.add(new CabinaDTO(new Cabina(4,"PRIMERA")));

        when(service.getAll()).thenReturn(list);
        ResponseEntity response = controller.get();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((List<CabinaDTO>) response.getBody()).size(), list.size());
    }
    @Test
    public void verifyGetAllEmpty() {
        List<Cabina> list = new ArrayList<>();
        when(service.getAll()).thenReturn(list);
        ResponseEntity response = controller.get();
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void verifyGetAllThrowsRestInternalServerErrorException() {
        when(service.getAll()).thenThrow(Exception.class);
        try {
            controller.get();
            Assert.fail();
        } catch (Exception e) {
            assertTrue(e instanceof RestInternalServerErrorException);
        }
    }

}

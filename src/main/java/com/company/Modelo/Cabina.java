package com.company.Modelo;

import com.company.DTO.CabinaDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by alumno on 16/05/2018.
 */
@Getter
@Setter
@Entity
@Table (name = "cabin")
@NoArgsConstructor

public class Cabina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id_cabin", nullable = false)
    private int Id_cabin;
    @NotEmpty(message = "Nombre Requerido")
    @Column (name = "Name", columnDefinition = "varchar(30)", nullable = false)
    private String name;

    public Cabina(CabinaDTO dto)
    {
        this.name = dto.getName();

    }

    public  Cabina (int id_cabin, @NotNull String nombre)
    {
        this.Id_cabin = id_cabin;
        this.name = nombre;
    }
}

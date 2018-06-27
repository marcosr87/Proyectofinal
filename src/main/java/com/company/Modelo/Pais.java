package com.company.Modelo;

import com.company.DTO.PaisDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alumno on 16/05/2018.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (name = "country", uniqueConstraints = {@UniqueConstraint(columnNames = {"Iso"})})
public class Pais {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "Id_Country" , nullable = false)
    private int Id_Country;
    @NotEmpty(message = "Nombre Requerido")
    @Column(name = "Name", columnDefinition = "varchar(30)", nullable = false)
    private String name;
    @NotEmpty ( message = "Iso requerido" )
    @Column(name = "Iso", columnDefinition = "varchar(3)", unique = true, nullable = false)
    private String Iso;

    @OneToMany( mappedBy = "pais")
    private List<Estado> ListaEstado;

    public Pais(int id, @NotNull String name, @NotNull String Iso) {

        this.Id_Country = id;
        this.name = name;
        this.Iso = Iso.toLowerCase();

    }

    public Pais(PaisDTO dto) {
        this.Iso = dto.getIsoCode().toLowerCase();
        this.name = dto.getName();
    }

}

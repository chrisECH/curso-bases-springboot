package com.cursojava.demospring.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "USUARIOS")
@ToString @EqualsAndHashCode
public class Usuarios {

    @Id
    @Getter @Setter @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter @Column(name = "NOMBRE")
    private String nombre;

    @Getter @Setter @Column(name = "APELLIDOS")
    private String apellidos;

    @Getter @Setter @Column(name = "EMAIL")
    private String email;

    @Getter @Setter @Column(name = "TELEFONO")
    private String telefono;

    @Getter @Setter @Column(name = "PASSWORD")
    private String password;

}

package com.umg.simulador_mundial.model;

import jakarta.persistence.*;

// La etiqueta @Entity le dice a Spring Boot: "¡Oye! Convierte esta clase en una tabla de PostgreSQL"
@Entity
@Table(name = "equipos")
public class Equipo {

    // @Id le dice que esta es la Llave Primaria (Primary Key)
    // @GeneratedValue hace que el ID sea auto-incrementable (1, 2, 3...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre; // Ejemplo: "Guatemala", "Brasil"

    @Column(nullable = false, length = 3)
    private String abreviatura; // Ejemplo: "GUA", "BRA" (Esto servirá mucho para el UI del partido)

    private String entrenador;


    // CONSTRUCTORES (Concepto clave de POO para tu rúbrica)
    public Equipo() {
    }

    public Equipo(String nombre, String abreviatura, String entrenador) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.entrenador = entrenador;
    }

    private String grupo; 


    // GETTERS Y SETTERS (Encapsulamiento, otro punto de tu rúbrica)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getAbreviatura() { return abreviatura; }
    public void setAbreviatura(String abreviatura) { this.abreviatura = abreviatura; }

    public String getEntrenador() { return entrenador; }
    public void setEntrenador(String entrenador) { this.entrenador = entrenador; }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
}
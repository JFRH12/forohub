package com.aluracursos.forohub.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria.

    @NotBlank
    private String username; // Nombre de usuario (obligatorio).

    @NotBlank
    private String password; // Contraseña del usuario (obligatoria).

    // Getters y setters (se generan automáticamente con Lombok o manualmente si prefieres).
}

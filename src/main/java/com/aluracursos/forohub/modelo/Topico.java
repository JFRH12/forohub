package com.aluracursos.forohub.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria, se genera automáticamente.

    @NotBlank
    private String titulo; // El título del tópico (obligatorio).

    @NotBlank
    private String mensaje; // El mensaje del tópico (obligatorio).

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now(); // Fecha de creación del tópico.

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.ABIERTO; // Estado del tópico (ABIERTO o CERRADO).

    @NotBlank
    private String autor; // Nombre del autor del tópico.

    @NotBlank
    private String curso; // Nombre del curso al que pertenece el tópico.

    // Getters y setters (se generan automáticamente con Lombok o manualmente si prefieres).
}

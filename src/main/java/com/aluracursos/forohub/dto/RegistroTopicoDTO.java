package com.aluracursos.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroTopicoDTO {

    @NotBlank
    private String titulo; // Título del tópico (obligatorio).

    @NotBlank
    private String mensaje; // Mensaje del tópico (obligatorio).

    @NotBlank
    private String autor; // Nombre del autor (obligatorio).

    @NotBlank
    private String curso; // Nombre del curso (obligatorio).

    }

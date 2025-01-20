package com.aluracursos.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public class ActualizarTopicoDTO {

    @NotBlank(message = "El título no puede estar vacío.")
    private String titulo;

    @NotBlank(message = "El mensaje no puede estar vacío.")
    private String mensaje;

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

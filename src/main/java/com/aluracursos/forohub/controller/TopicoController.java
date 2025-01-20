package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.dto.ActualizarTopicoDTO;
import com.aluracursos.forohub.dto.RegistroTopicoDTO;
import com.aluracursos.forohub.modelo.Topico;
import com.aluracursos.forohub.repositorio.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository; // Inyección de dependencia para acceder a la base de datos.

    @PostMapping
    public Topico registrar(@RequestBody @Valid RegistroTopicoDTO datos) {
        // Crear un nuevo tópico basado en los datos enviados.
        Topico topico = new Topico();
        topico.setTitulo(datos.getTitulo());
        topico.setMensaje(datos.getMensaje());
        topico.setAutor(datos.getAutor());
        topico.setCurso(datos.getCurso());

        // Guardar el tópico en la base de datos y devolverlo.
        return topicoRepository.save(topico);
    }

    @GetMapping("/{id}")
    public Topico detalle(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado."));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizar(@PathVariable Long id, @RequestBody @Valid ActualizarTopicoDTO datos) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);



        // Validar si el tópico existe
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna un código HTTP 404
        }

        // Actualizar los datos del tópico
        Topico topico = topicoOptional.get();
        topico.setTitulo(datos.getTitulo());
        topico.setMensaje(datos.getMensaje());

        // Guardar los cambios en la base de datos
        topicoRepository.save(topico);

        return ResponseEntity.ok(topico); // Retorna el tópico actualizado
    }



    @GetMapping
    public Page<Topico> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion
    ) {
        // Caso: Filtrar por curso y año.
        if (curso != null && anio != null) {
            return topicoRepository.findByCursoAndAnio(curso, anio, paginacion);
        }

        // Caso: Filtrar solo por curso.
        if (curso != null) {
            return topicoRepository.findByCurso(curso, paginacion);
        }

        // Caso: Listar todos los tópicos.
        return topicoRepository.findAll(paginacion);

        @GetMapping("/{id}")
        public ResponseEntity<Topico> detalle(@PathVariable Long id) {
            Optional<Topico> topicoOptional = topicoRepository.findById(id);

            // Validar si el tópico existe
            if (topicoOptional.isEmpty()) {
                return ResponseEntity.notFound().build(); // Retorna un código HTTP 404
            }

            // Si el tópico existe, retorna su contenido
            return ResponseEntity.ok(topicoOptional.get());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        // Verificar si el tópico existe
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna un código HTTP 404
        }

        // Eliminar el tópico de la base de datos
        topicoRepository.deleteById(id);

        // Responder con un código HTTP 204 (No Content)
        return ResponseEntity.noContent().build();
    }


}

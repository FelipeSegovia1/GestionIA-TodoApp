package com.felipe.todo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tareas")
@Data // Esto genera getters y setters automáticos gracias a Lombok
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private boolean completada;
}
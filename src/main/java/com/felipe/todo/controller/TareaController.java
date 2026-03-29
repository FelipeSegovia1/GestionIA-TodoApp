package com.felipe.todo.controller;

import com.felipe.todo.model.Tarea;
import com.felipe.todo.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
@CrossOrigin(origins = "*") // Permite que otras webs consulten tu API
public class TareaController {

    @Autowired
    private TareaRepository repository;

    // GET /tareas -> Listar todas
    @GetMapping
    public List<Tarea> listar() {
        return repository.findAll();
    }

    // POST /tareas -> Crear una nueva
    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea) {
        return repository.save(tarea);
    }

    // PUT /tareas/{id} -> Marcar como completada o editar
    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable Long id, @RequestBody Tarea datosActualizados) {
        return repository.findById(id).map(tarea -> {
            tarea.setTitulo(datosActualizados.getTitulo());
            tarea.setCompletada(datosActualizados.isCompletada());
            return repository.save(tarea);
        }).orElseThrow();
    }

    // DELETE /tareas/{id} -> Borrar tarea
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

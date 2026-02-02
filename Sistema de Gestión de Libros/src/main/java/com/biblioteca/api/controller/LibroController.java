package com.biblioteca.api.controller;

import com.biblioteca.api.model.Libro;
import com.biblioteca.api.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen
public class LibroController {

    @Autowired
    private LibroService libroService;

    // ==================== OPERACIONES CRUD ====================

    // 1. Obtener todos los libros
    // GET /api/libros
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodos() {
        List<Libro> libros = libroService.obtenerTodos();
        return ResponseEntity.ok(libros);
    }

    // 2. Obtener un libro por ID
    // GET /api/libros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable Long id) {
        Libro libro = libroService.obtenerPorId(id);
        return ResponseEntity.ok(libro);
    }

    // 3. Crear un nuevo libro
    // POST /api/libros
    @PostMapping
    public ResponseEntity<Libro> crear(@Valid @RequestBody Libro libro) {
        Libro nuevoLibro = libroService.guardar(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }

    // 4. Actualizar un libro existente
    // PUT /api/libros/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @Valid @RequestBody Libro libro) {
        Libro libroActualizado = libroService.actualizar(id, libro);
        return ResponseEntity.ok(libroActualizado);
    }

    // 5. Eliminar un libro
    // DELETE /api/libros/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Libro eliminado exitosamente");
        return ResponseEntity.ok(response);
    }

    // ==================== BÚSQUEDAS ====================

    // 6. Buscar libros por título
    // GET /api/libros/buscar/titulo?q=palabra
    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<Libro>> buscarPorTitulo(@RequestParam("q") String titulo) {
        List<Libro> libros = libroService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(libros);
    }

    // 7. Buscar libros por autor
    // GET /api/libros/buscar/autor?q=nombre
    @GetMapping("/buscar/autor")
    public ResponseEntity<List<Libro>> buscarPorAutor(@RequestParam("q") String autor) {
        List<Libro> libros = libroService.buscarPorAutor(autor);
        return ResponseEntity.ok(libros);
    }

    // 8. Buscar libro por ISBN
    // GET /api/libros/buscar/isbn/{isbn}
    @GetMapping("/buscar/isbn/{isbn}")
    public ResponseEntity<Libro> buscarPorIsbn(@PathVariable String isbn) {
        Libro libro = libroService.buscarPorIsbn(isbn);
        return ResponseEntity.ok(libro);
    }

    // 9. Buscar libros por editorial
    // GET /api/libros/buscar/editorial?q=nombre
    @GetMapping("/buscar/editorial")
    public ResponseEntity<List<Libro>> buscarPorEditorial(@RequestParam("q") String editorial) {
        List<Libro> libros = libroService.buscarPorEditorial(editorial);
        return ResponseEntity.ok(libros);
    }

    // 10. Buscar libros por año de publicación
    // GET /api/libros/buscar/anio/{anio}
    @GetMapping("/buscar/anio/{anio}")
    public ResponseEntity<List<Libro>> buscarPorAnio(@PathVariable Integer anio) {
        List<Libro> libros = libroService.buscarPorAnio(anio);
        return ResponseEntity.ok(libros);
    }

    // ==================== ESTADÍSTICAS ====================

    // 11. Obtener total de libros
    // GET /api/libros/estadisticas/total
    @GetMapping("/estadisticas/total")
    public ResponseEntity<Map<String, Long>> obtenerTotal() {
        Map<String, Long> response = new HashMap<>();
        response.put("totalLibros", libroService.contarLibros());
        return ResponseEntity.ok(response);
    }
}

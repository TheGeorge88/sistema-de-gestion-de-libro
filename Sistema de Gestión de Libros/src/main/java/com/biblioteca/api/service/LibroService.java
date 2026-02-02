package com.biblioteca.api.service;

import com.biblioteca.api.exception.DuplicateResourceException;
import com.biblioteca.api.exception.ResourceNotFoundException;
import com.biblioteca.api.model.Libro;
import com.biblioteca.api.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos los libros
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    // Obtener libro por ID
    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", id));
    }

    // Buscar por título
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Buscar por autor
    public List<Libro> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }

    // Buscar por ISBN
    public Libro buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Libro con ISBN " + isbn + " no encontrado"));
    }

    // Buscar por editorial
    public List<Libro> buscarPorEditorial(String editorial) {
        return libroRepository.findByEditorialContainingIgnoreCase(editorial);
    }

    // Buscar por año
    public List<Libro> buscarPorAnio(Integer anio) {
        return libroRepository.findByAnioPublicacion(anio);
    }

    // Crear nuevo libro
    public Libro guardar(Libro libro) {
        // Verificar si ya existe un libro con el mismo ISBN
        if (libroRepository.existsByIsbn(libro.getIsbn())) {
            throw new DuplicateResourceException("ISBN", libro.getIsbn());
        }
        return libroRepository.save(libro);
    }

    // Actualizar libro existente
    public Libro actualizar(Long id, Libro libroActualizado) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", id));

        // Verificar si el nuevo ISBN ya existe en otro libro
        if (!libro.getIsbn().equals(libroActualizado.getIsbn()) &&
                libroRepository.existsByIsbn(libroActualizado.getIsbn())) {
            throw new DuplicateResourceException("ISBN", libroActualizado.getIsbn());
        }

        libro.setTitulo(libroActualizado.getTitulo());
        libro.setAutor(libroActualizado.getAutor());
        libro.setIsbn(libroActualizado.getIsbn());
        libro.setAnioPublicacion(libroActualizado.getAnioPublicacion());
        libro.setEditorial(libroActualizado.getEditorial());

        return libroRepository.save(libro);
    }

    // Eliminar libro
    public void eliminar(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Libro", id);
        }
        libroRepository.deleteById(id);
    }

    // Contar total de libros
    public long contarLibros() {
        return libroRepository.count();
    }
}

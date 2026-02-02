package com.biblioteca.api.repository;

import com.biblioteca.api.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar por título (contiene, ignorando mayúsculas)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Buscar por autor (contiene, ignorando mayúsculas)
    List<Libro> findByAutorContainingIgnoreCase(String autor);

    // Buscar por ISBN exacto
    Optional<Libro> findByIsbn(String isbn);

    // Buscar por editorial
    List<Libro> findByEditorialContainingIgnoreCase(String editorial);

    // Buscar por año de publicación
    List<Libro> findByAnioPublicacion(Integer anio);

    // Verificar si existe un ISBN
    boolean existsByIsbn(String isbn);
}

package com.biblioteca.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(min = 1, max = 255, message = "El autor debe tener entre 1 y 255 caracteres")
    @Column(nullable = false)
    private String autor;

    @NotBlank(message = "El ISBN es obligatorio")
    @Column(nullable = false, unique = true)
    private String isbn;

    @Min(value = 1000, message = "El año debe ser mayor a 1000")
    @Max(value = 2100, message = "El año no puede ser mayor a 2100")
    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    @NotBlank(message = "La editorial es obligatoria")
    @Size(min = 1, max = 255, message = "La editorial debe tener entre 1 y 255 caracteres")
    @Column(nullable = false)
    private String editorial;

    // Constructor vacío
    public Libro() {
    }

    // Constructor con parámetros
    public Libro(String titulo, String autor, String isbn, Integer anioPublicacion, String editorial) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}

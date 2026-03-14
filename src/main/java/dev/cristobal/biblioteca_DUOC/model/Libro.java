package dev.cristobal.biblioteca_DUOC.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private int id;
    private String titulo;
    private String isbn;
    private String editorial;
    private String autor;
    private int fechaPublicacion;


}

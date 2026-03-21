package dev.cristobal.biblioteca_DUOC.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dev.cristobal.biblioteca_DUOC.model.Libro;
import dev.cristobal.biblioteca_DUOC.service.LibroService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/libro")
public class LibroController {
    @Autowired
    private LibroService libroService;
    @GetMapping
    public List<Libro> getLibros() {
        return libroService.getLibros();
    }

    @PostMapping
    public Libro agregarLibro(@RequestBody Libro libro) {
        return libroService.saveLibro(libro);
    }

    @GetMapping("{id}")
    public Libro buscarLibro(@PathVariable int id) {
        return libroService.getLibroId(id);
    }

    @PutMapping("{id}")
    public Libro actualizarLibro(@PathVariable int id, @RequestBody Libro libro) {
        return libroService.updateLibro(libro);
    }

    @DeleteMapping("{id}")
    public String eliminarLibro(@PathVariable int id) {
        return libroService.deleteLibro(id);
    }

    @GetMapping("/total")
    public int totalLibrosV2() {
        return libroService.totalLibrosV2();
    }

    @GetMapping("/masAntiguo")
    public Libro masAntiguo() {
        return libroService.masAntiguo();
    }

    @GetMapping("/masNuevo")
    public Libro masNuevo() {
        return libroService.masNuevo();
    }
    @GetMapping("/buscarPorAutor/{nombreAutor}")
    public List<Libro> buscarPorAutor(@PathVariable String nombreAutor) {
        return libroService.buscarPorAutor(nombreAutor);
    }
    @GetMapping("/ordenarPorFecha")
    public List<Libro>  ordenarPorFecha() {
        return libroService.ordenarPorFecha();
    }

    @GetMapping("/buscarPorIsbn/{isbn}")
    public Libro buscarPorIsbn(@PathVariable String isbn) {
        return libroService.buscarPorIsbn(isbn);
    }

    @GetMapping("/buscarPorFecha/{fechaPublicacion}")
    public List<Libro> libroPorFecha(@PathVariable int fechaPublicacion) {
        return libroService.libroPorFecha(fechaPublicacion);
    }
}

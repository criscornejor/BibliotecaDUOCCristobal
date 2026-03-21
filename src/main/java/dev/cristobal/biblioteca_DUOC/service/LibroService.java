package dev.cristobal.biblioteca_DUOC.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.cristobal.biblioteca_DUOC.repository.LibroRepository;
import dev.cristobal.biblioteca_DUOC.model.Libro;
import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> getLibros() {
        return libroRepository.obtenerLibros();
    }

    public Libro saveLibro(Libro libro){
        return libroRepository.guardar(libro);
    }
    public Libro getLibroId(int id) {
        return libroRepository.buscarPorId(id);
    }

    public Libro updateLibro(Libro libro) {
        return libroRepository.actualizar(libro);
    }

    public String deleteLibro(int id) {
        libroRepository.eliminar(id);
        return "Libro eliminado";
    }

    public int totalLibrosV1() {
        return libroRepository.obtenerLibros().size();
    }

    public int totalLibrosV2() {
        return libroRepository.totalLibros();
    }

    public Libro masAntiguo() {
        return libroRepository.masAntiguo();
    }

    public Libro masNuevo() {
        return libroRepository.masNuevo();
    }

    public List<Libro> buscarPorAutor(String nombreAutor) {
        return libroRepository.buscarPorAutor(nombreAutor);
    }

    public List<Libro> ordenarPorFecha() {
        return libroRepository.ordenarPorFecha();
    }

    public Libro buscarPorIsbn(String isbn) {
        return libroRepository.buscarPorIsbn(isbn);
    }

    public List<Libro> libroPorFecha(int fechaPublicacion) {
        return libroRepository.librosPorFecha(fechaPublicacion);
    }
}

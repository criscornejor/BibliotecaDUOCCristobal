package dev.cristobal.biblioteca_DUOC.repository;


import dev.cristobal.biblioteca_DUOC.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRepository {
    private List<Libro> listaLibros = new ArrayList<>();

    public List<Libro> obtenerLibros() {
        return listaLibros;
    }

    public Libro buscarPorId(int id) {
        for (Libro libro : listaLibros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }
    public Libro buscarPorIsbn(String isbn) {
        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public Libro guardar(Libro lib) {
        listaLibros.add(lib);
        return lib;
    }

    public Libro actualizar(Libro lib) {
        int id = 0;
        int idPosition = 0;

        for (int i = 0; i < listaLibros.size(); i++){
            if(listaLibros.get(i).getId() == lib.getId()) {
                id = listaLibros.get(i).getId();
                idPosition = i;
            }
        }

        Libro libro1 = new Libro();
        libro1.setId(id);
        libro1.setTitulo(lib.getTitulo());
        libro1.setAutor(lib.getAutor());
        libro1.setFechaPublicacion(lib.getFechaPublicacion());
        libro1.setEditorial(lib.getEditorial());
        libro1.setIsbn(lib.getIsbn());

        listaLibros.set(idPosition, libro1);
        return libro1;
    }

    public void eliminar(int id) {
        Libro lib = buscarPorId(id);
        if(lib != null) {
            listaLibros.remove(lib);
        }
    }

    public void eliminarV2(int id) {
        int idPosition = 0;
        for (int i = 0; i < listaLibros.size(); i++) {
            idPosition = i;
            break;
        }
    }
}
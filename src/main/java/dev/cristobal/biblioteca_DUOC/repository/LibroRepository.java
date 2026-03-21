package dev.cristobal.biblioteca_DUOC.repository;


import dev.cristobal.biblioteca_DUOC.model.Libro;
import dev.cristobal.biblioteca_DUOC.model.Prestamo;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRepository {
    private List<Libro> listaLibros = new ArrayList<>();
    private List<Prestamo> listaPrestamos = new ArrayList<>();

    public LibroRepository() {
        listaLibros.add(new Libro(1, "9789569646638", "Fuego y Sangre", "Penguin Random House Grupo Editorial", 2018, "George R. R. Martin"));
        listaLibros.add(new Libro(2, "9789563494150", "Quique Hache: El Mall Embrujado y Otras Historias", "Sm Ediciones", 2014, "Sergio Gomez"));
        listaLibros.add(new Libro(3, "9781484256251", "Spring Boot Persistence Best Practices", "Apress", 2020, "Anghel Leonard"));
        listaLibros.add(new Libro(4, "9789566075752", "Harry Potter y la piedra filosofal", "Salamandra", 2024, "J. K. Rowling"));
        listaLibros.add(new Libro(5, "9780439139601", "Harry Potter y el prisionero de Azkaban", "Scholastic", 1999, "J. K. Rowling"));
        listaLibros.add(new Libro(6, "9780439136365", "Harry Potter y el cáliz de fuego", "Scholastic", 2000, "J. K. Rowling"));
        listaLibros.add(new Libro(7, "9780321127426", "Effective Java", "AddisonWesley", 2008, "Joshua Bloch"));
        listaLibros.add(new Libro(8, "9780134685991", "Clean Architecture", "Prentice Hall", 2017, "Robert C. Martin"));
        listaLibros.add(new Libro(9, "9780201633610", "Design Patterns", "AddisonWesley", 1994, "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides"));
        listaLibros.add(new Libro(10, "9780132350884", "Clean Code", "Prentice Hall", 2008, "Robert C. Martin"));
    }

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
    //Quiero dejar en claro que aúnque estoy avanzando y creo estár haciendolo bien, no tengo idea
    //si esto vaya a funcionar TnT.

    public List<Prestamo> obtenerTotalPrestamo() {
        return listaPrestamos;
    }

    public Prestamo guardarPrestamo(Prestamo prestamo) {
        listaPrestamos.add(prestamo);
        return prestamo;
    }

    public Prestamo buscarPrestamoPorId(int id_prestamo) {
        for (Prestamo prestamo : listaPrestamos) {
            if (prestamo.getId_prestamo() == id_prestamo) {
                return prestamo;
            }
        }
        return null;
    }

    public Prestamo actualizarPrestamo(Prestamo prestamo) {
        int id_prestamo = 0;
        int idPosition = 0;

        for (int i = 0; i<listaPrestamos.size(); i++) {
            if(listaPrestamos.get(i).getId_prestamo() == prestamo.getId_prestamo()) {
                id_prestamo = listaPrestamos.get(i).getId_prestamo();
                idPosition = i;
            }
        }
        Prestamo prestamo1 = new Prestamo();
        prestamo1.setId_prestamo(id_prestamo);
        prestamo1.setId_libro(listaPrestamos.get(idPosition).getId_libro());
        prestamo1.setRun_solicitante(prestamo.getRun_solicitante());
        prestamo1.setFecha_solicitud(prestamo.getFecha_solicitud());
        prestamo1.setFecha_entrega(prestamo.getFecha_entrega());
        prestamo1.setCantidad_dias(prestamo.getCantidad_dias());
        prestamo1.setCantidad_multas(prestamo.getCantidad_multas());

        listaPrestamos.set(idPosition, prestamo1);
        return prestamo1;
    }

    public void borrarPrestamo(int id) {
        Prestamo prestamo = buscarPrestamoPorId(id);
        if(prestamo != null) {
            listaPrestamos.remove(prestamo);
        }
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

    public int totalLibros() {
        return listaLibros.size();
    }

    public Libro masAntiguo() {
        Libro masAntiguo = listaLibros.get(0);

        for (Libro libroActual : listaLibros) {
            if (libroActual.getFechaPublicacion() < masAntiguo.getFechaPublicacion()) {
                masAntiguo = libroActual;
            }
        }
        return masAntiguo;
        //Llevo como 20 min. solo para hacer esto, ayuda tuve que verme un video sobre
        //for e if en Java, se me había olvidado como se usaban D:
    }
    public Libro masNuevo() {
        Libro masNuevo = listaLibros.get(0);

        for (Libro libroActual : listaLibros) {
            if (libroActual.getFechaPublicacion() > masNuevo.getFechaPublicacion()) {
                masNuevo = libroActual;
            }
        }
        return masNuevo;
        //esto era copiar y pegar lo anterior xddddd, bueno obviamente dando vuelta la logica y
        //todo eso, pero era practicamente copiar y pegar.
    }

    public List<Libro> buscarPorAutor(String nombreAutor) {
        List<Libro> librosDelAutor = new ArrayList<>(); // Lista para guardar los resultados

        for (Libro libroActual : listaLibros) {
            if (libroActual.getAutor().equalsIgnoreCase(nombreAutor)) {
                librosDelAutor.add(libroActual);
            }
        }

        return librosDelAutor;

        //Esto de las listas me tiene mal, pero es entretenido, aunque sigo sin saber bien que hace el
        // equalsIgnoreCase(), solo se que hace la pega y ya.
    }

    public List<Libro> ordenarPorFecha() {
        listaLibros.sort(Comparator.comparingInt(Libro::getFechaPublicacion));
        //Estaba viendo como manejar esta situación y me encontré con "Comparator", tenía pensado anidar
        //un for dentro de otro for, pero quería ver si había otra forma de hacerlo y me encontre
        //con Comparator, 10/10.
        return listaLibros;
    }

    public List<Libro> librosPorFecha(int fechaPublicacion) {
        List<Libro> libroPorFecha = new ArrayList<>();

        for (Libro libroActual : listaLibros) {
            if (libroActual.getFechaPublicacion() == fechaPublicacion) {
                libroPorFecha.add(libroActual);
            }
        }
        return libroPorFecha;
    }



}
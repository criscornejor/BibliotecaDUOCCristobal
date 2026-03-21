package dev.cristobal.biblioteca_DUOC.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    private int id_prestamo;
    private int id_libro;
    private String run_solicitante;
    private Date fecha_solicitud;
    private Date fecha_entrega = null;
    private int cantidad_dias;
    private int cantidad_multas = 0;
}

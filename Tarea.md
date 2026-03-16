Informe de Desarrollo: API REST con Spring Boot (CRUD)

Estudiante: Cristobal Cornejo
Fecha: 16 de Marzo de 2026

1. URL del Repositorio GitHub
[ENLACE AL REPOSITORIO GITHUB AQUÍ]

2. Explicación del Código

El proyecto consiste en una API RESTful desarrollada utilizando Java y el framework Spring Boot. La aplicación gestiona una entidad básica (por ejemplo, Usuario) y expone diferentes endpoints (rutas) web para realizar las operaciones fundamentales de un CRUD (Crear, Leer, Actualizar y Eliminar).

Para cumplir con el Bonus Track, se implementó un patrón de diseño DTO (Data Transfer Object) creando una clase llamada RespuestaAPI. 

codigo: El código de estado HTTP (ej. 200, 201, 400, 404).

mensaje: Un mensaje descriptivo de la acción realizada o del error ocurrido.

data: (Opcional) La información solicitada en caso de peticiones GET.

Esto permite que cualquier cliente (como el frontend u otras aplicaciones) pueda interpretar fácilmente si la petición fue exitosa o si ocurrió un error de validación.

3. Cómo subir el proyecto a GitHub

Para versionar este código y subirlo al repositorio de GitHub, se deben ejecutar los siguientes pasos:

Inicializar el repositorio local de Git:

Se puede hacer ya sea en la terminal con "git init" o creandolo desde Intellij


Agregar todos los archivos del proyecto al "Staging Area":

git add . o hacerlo desde la ventana "Commit" de Intellij seleccionando los archivos que quieras subir o seleccionarlos todos de una sola vez.
 

Crear el primer commit con un mensaje descriptivo:

git commit -m "Primer commit: Implementación de CRUD con respuestas personalizadas" o desde la ventana "Commit" hacer un commit normal o un commit con pull


Renombrar la rama principal a 'main' (si es necesario):

git branch -M main


Vincular el repositorio local con el repositorio remoto de GitHub:

git remote add origin [https://github.com/tu-usuario/tu-repositorio.git](https://github.com/tu-usuario/tu-repositorio.git), Intellij tiene la opción de hacerlo graficamente, vas a la parte superior de la ventana, apretas el boton que dice "master" y apretas push, colocas el link del repositorio dentro de la ventana seleccionando el branch "main" o simplemente creando una nueva.


Subir los cambios al repositorio remoto:

git push -u origin main o apretar el boton "push" una vez puesto el link en el paso anterior.


4. Implementación del CRUD y Bonus Track

A continuación se muestra el código implementado en Spring Boot para cumplir con el CRUD y la respuesta estructurada mediante objetos.

A. Objeto de Respuesta (RespuestaAPI.java)

Esta clase se encarga de estandarizar todas las respuestas de nuestro servicio web.

package com.ejemplo.demo.dto;

public class RespuestaAPI {
private int codigo;
private String mensaje;
private Object data;

    // Constructor para mensajes de éxito o error simple (Bonus Track)
    public RespuestaAPI(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    // Constructor que incluye datos adicionales (ej. para consultas)
    public RespuestaAPI(int codigo, String mensaje, Object data) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.data = data;
    }

    // Getters y Setters
    // ayuda, son como las 3 de la mañana y tengo sueño
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}


B. Controlador REST CRUD (UsuarioController.java)

Este controlador maneja las peticiones HTTP y devuelve las respuestas estructuradas.

package com.ejemplo.demo.controller;

import com.ejemplo.demo.dto.RespuestaAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    // Simulador de base de datos en memoria para el ejemplo
    private List<String> usuarios = new ArrayList<>();

    // 1. CREATE - POST
    @PostMapping
    public ResponseEntity<RespuestaAPI> crearUsuario(@RequestBody String nombreUsuario) {
        // Validación: Si el usuario viene vacío o es nulo (Simulando Bad Request - 400)
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            RespuestaAPI error = new RespuestaAPI(400, "Error: El nombre de usuario no puede estar vacío");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        usuarios.add(nombreUsuario);
        // Respuesta exitosa de creación (Created - 201)
        RespuestaAPI exito = new RespuestaAPI(201, "Usuario '" + nombreUsuario + "' creado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(exito);
    }

    // 2. READ - GET
    @GetMapping
    public ResponseEntity<RespuestaAPI> obtenerUsuarios() {
        // Retorna la lista de usuarios (OK - 200)
        RespuestaAPI respuesta = new RespuestaAPI(200, "Lista de usuarios obtenida correctamente", usuarios);
        return ResponseEntity.ok(respuesta);
    }

    // 3. UPDATE - PUT
    @PutMapping("/{id}")
    public ResponseEntity<RespuestaAPI> actualizarUsuario(@PathVariable int id, @RequestBody String nuevoNombre) {
        // Simulando que el ID no existe (Not Found - 404)
        if (id < 0 || id >= usuarios.size()) {
            RespuestaAPI error = new RespuestaAPI(404, "Error: El usuario con ID " + id + " no fue encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        usuarios.set(id, nuevoNombre);
        // Respuesta de actualización exitosa (OK - 200)
        RespuestaAPI exito = new RespuestaAPI(200, "Usuario actualizado correctamente a '" + nuevoNombre + "'");
        return ResponseEntity.ok(exito);
    }

    // 4. DELETE - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaAPI> eliminarUsuario(@PathVariable int id) {
        if (id < 0 || id >= usuarios.size()) {
            RespuestaAPI error = new RespuestaAPI(404, "Error: No se puede eliminar, el usuario con ID " + id + " no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        String eliminado = usuarios.remove(id);
        // Respuesta de eliminación exitosa (OK - 200)
        RespuestaAPI exito = new RespuestaAPI(200, "Usuario '" + eliminado + "' eliminado del sistema");
        return ResponseEntity.ok(exito);
    }
}

Con esto no solo obtiene la respuesta en codigo el usuario, también logra saber el significado de ese codigo sin tener que visitar la página de los gatitos http.
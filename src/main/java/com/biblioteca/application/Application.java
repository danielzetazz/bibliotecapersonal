package com.biblioteca.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import controladorBiblioteca.controladorBiblio;
import servicioBiblioteca.libro;
import servicioBiblioteca.tipoEstado;

import java.time.LocalDate;
import java.util.List;


/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "bibliotecapersonal")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
        controladorBiblio controlador = new controladorBiblio();

        System.out.println("📚 Listar todos los libros:");
        List<libro> libros = controlador.obtenerTodosLosLibros();
        for (libro l : libros) {
            System.out.println(l.getTitulo() + " - " + l.getAutor() + " (" + l.getFechaPublicacion() + ")");
        }

        System.out.println("\n➕ Agregar un nuevo libro:");
        libro nuevoLibro = new libro(
                "El Principito",
                "Antoine de Saint-Exupéry",
                "Ficción",
                tipoEstado.NO_LEIDO,
                LocalDate.of(1943, 4, 6)
        );
        controlador.agregarLibro(nuevoLibro);

        System.out.println("\n📚 Libros después de agregar:");
        libros = controlador.obtenerTodosLosLibros();
        for (libro l : libros) {
            System.out.println(l.getTitulo() + " - " + l.getAutor() + " (" + l.getFechaPublicacion() + ")");
        }

        System.out.println("\n✏️ Editar libro 'El Principito':");
        libro libroEditado = new libro(
                "1984",
                "George Orwell",
                "Ficción Distópica",
                tipoEstado.LEIDO,
                LocalDate.of(1950, 1, 1)
        );
        boolean editado = controlador.editarLibro("El Principito", libroEditado);
        System.out.println(editado ? "✅ Editado con éxito." : "❌ No encontrado.");

        System.out.println("\n❌ Eliminar libro 'Sapiens: De animales a dioses':");
        boolean eliminado = controlador.eliminarLibro("Sapiens: De animales a dioses");
        System.out.println(eliminado ? "✅ Eliminado con éxito." : "❌ No encontrado.");

        System.out.println("\n📚 Libros finales:");
        libros = controlador.obtenerTodosLosLibros();
        for (libro l : libros) {
            System.out.println(l.getTitulo() + " - " + l.getAutor() + " (" + l.getFechaPublicacion() + ")");
        }


    }
}

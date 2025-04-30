package controladorBiblioteca;

import bibliotecaDAO.biblioDAO;
import servicioBiblioteca.libro;

import java.util.List;
import java.util.stream.Collectors;

public class controladorBiblio {

    private biblioDAO dao;
    private List<libro> libros;

    public controladorBiblio() {
        dao = biblioDAO.getInstance();
        libros = dao.cargarLibros();
    }

    public List<libro> obtenerTodosLosLibros() {
        return libros;
    }

    public void agregarLibro(libro nuevoLibro) {
        dao.anadirLibro(nuevoLibro);
        libros = dao.cargarLibros(); // Vuelves a sincronizar la lista actualizada
    }

    public boolean editarLibro(String tituloOriginal, libro libroEditado) {
        int resultado = dao.editarLibro(tituloOriginal, libroEditado);
        if (resultado >= 0) {
            libros.set(resultado, libroEditado);
            return true;
        }
        return false;
    }


    public boolean eliminarLibro(String titulo) {
        int resultado = dao.eliminarLibro(titulo);
        if (resultado >= 0) {
            libros.remove(resultado);
            return true;
        }
        return false;
    }

    public List<libro> filtrarPorCategoria(String categoria) {
        return libros.stream()
                .filter(libro -> libro.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public List<libro> filtrarPorEstado(String estado) {
        return libros.stream()
                .filter(libros -> libros.getEstado().toString().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }
}
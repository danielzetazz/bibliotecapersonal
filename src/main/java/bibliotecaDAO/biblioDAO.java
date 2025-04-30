package bibliotecaDAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import servicioBiblioteca.libro;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class biblioDAO {

    private static biblioDAO dao = null;
    private List<libro> libros;

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {
                @Override
                public void write(JsonWriter out, LocalDate value) throws IOException {
                    if (value == null) {
                        out.nullValue();
                    } else {
                        out.value(value.toString());
                    }                }

                @Override
                public LocalDate read(JsonReader in) throws IOException {
                    String fecha = in.nextString();
                    return (fecha == null || fecha.isEmpty()) ? null : LocalDate.parse(fecha);
                }
            })
            .setPrettyPrinting()
            .create();

    private final String archivoLectura = "libros.json"; // Este se genera/usa fuera de resources

    private biblioDAO() {
        File archivo = new File(archivoLectura);

        if (archivo.exists()) {
            // Leer desde archivo externo (editable)
            try (Reader reader = new FileReader(archivo)) {
                Type tipoLista = new TypeToken<List<libro>>() {}.getType();
                libros = gson.fromJson(reader, tipoLista);
                if (libros == null) libros = new ArrayList<>();
            } catch (IOException e) {
                libros = new ArrayList<>();
                e.printStackTrace();
            }
        } else {
            // Intentar cargar desde resources (solo lectura)
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("libros.json")) {
                if (input != null) {
                    Reader reader = new InputStreamReader(input);
                    Type tipoLista = new TypeToken<List<libro>>() {}.getType();
                    libros = gson.fromJson(reader, tipoLista);
                }
                else {
                    System.err.println("⚠ No se encontró libros.json en resources.");
                }
                if (libros == null) libros = new ArrayList<>();
            } catch (Exception e) {
                libros = new ArrayList<>();
                e.printStackTrace();
            }
        }
    }

    public static biblioDAO getInstance() {
        if (dao == null) dao = new biblioDAO();
        return dao;
    }

    public List<libro> cargarLibros() {
        return libros;
    }

    public void guardarLibros() {
        try (FileWriter writer = new FileWriter(archivoLectura)) {
            gson.toJson(libros, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void anadirLibro(libro nuevoLibro) {
        libros.add(nuevoLibro);
        guardarLibros();
    }

    public int editarLibro(String tituloOriginal, libro libroEditado) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equalsIgnoreCase(tituloOriginal)) {
                libros.set(i, libroEditado);
                guardarLibros();
                return i;
            }
        }
        return -1;
    }

    public int eliminarLibro(String titulo) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                libros.remove(i);
                guardarLibros();
                return i;
            }
        }
        return -1;
    }
}

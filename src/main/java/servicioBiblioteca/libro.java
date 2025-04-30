package servicioBiblioteca;

import java.time.LocalDate;

public class libro {

    private String titulo, autor, categoria;
    private tipoEstado estado;
    private LocalDate fechaPublicacion;

    public libro(String titulo, String autor, String categoria, tipoEstado estado, LocalDate fechaPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.estado = estado;
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", categoria='" + categoria + '\'' +
                ", estado=" + estado +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public tipoEstado getEstado() {
        return estado;
    }

    public void setEstado(tipoEstado estado) {
        this.estado = estado;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}

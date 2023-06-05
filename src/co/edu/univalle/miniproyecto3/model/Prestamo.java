/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.model;

/**
 *
 * @author Sebastián
 */
public class Prestamo {
    private int id;
    private Usuario usuario;
    private Recurso recurso;
    private static int consecutivo = 0;
    private String[] estados = {"Abierto", "P. Cerrado", "Cerrado"};
    private String estado;
    private String fechaRealizacion;
    private String fechaDevolucion;

    public Prestamo() {
    }
    
    public Prestamo(Usuario usuario, Recurso recurso, String fechaRealizacion) {
        this.recurso = recurso;
        this.usuario = usuario;
        this.id = consecutivo++;
        this.estado = estados[0];
        this.fechaRealizacion = fechaRealizacion;
        this.fechaDevolucion = "";
    }

    public int getId() {
        return id;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public String[] getEstados() {
        return estados;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaRealizacion() {
        return fechaRealizacion;
    }

//    public void setFechaRealizacion(String fechaRealizacion) {
//        this.fechaRealizacion = fechaRealizacion;
//    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    @Override
    public String toString() {
        return id + ", " + usuario.getNombre() + 
                ", " + usuario.getRol() +
//                ", " + recurso.getIsbn() +
                ", " + recurso.getNombre() + 
//                ", " + recurso.getAutores().get(0);
                ", " + estado +
                ", " + fechaRealizacion +
                ", " + fechaDevolucion;
    }
}

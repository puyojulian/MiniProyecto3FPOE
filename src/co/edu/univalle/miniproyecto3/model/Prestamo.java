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

    public Prestamo() {
    }
    
    public Prestamo(Usuario usuario, Recurso recurso) {
        this.recurso = recurso;
        this.usuario = usuario;
        this.id = consecutivo++;
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
    
    @Override
    public String toString() {
        return id + ", " + usuario.toString() + ", " + recurso.toString();
    }
}

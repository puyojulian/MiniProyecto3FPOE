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
    private Usuario usuario;
    private Recurso recurso;
    
    
    public Prestamo(Usuario usuario, Recurso recurso) {
        this.recurso = recurso;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Recurso getRecurso() {
        return recurso;
    }
    
    @Override
    public String toString() {
        return " Usuario: " + usuario + " Recurso: " + recurso;
    }
}

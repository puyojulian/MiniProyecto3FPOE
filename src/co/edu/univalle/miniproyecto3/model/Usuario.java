/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.model;

/**
 *
 * @author julia
 */
public class Usuario {
    
    private int id;
    private String nombre;
    private boolean estadoActivo;
    private String rol;
    private static int consecutivo = 0;

    public Usuario() {
    }

    public Usuario(String nombre, String rol) {
        this.id = consecutivo++;
        this.nombre = nombre;
        this.estadoActivo = true;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
    }

    public String isRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
//        return " id=" + id + ", nombre=" + nombre + ", estadoActivo=" + estadoActivo + ", rol=" + rol;
//        return " id: " + id + ", nombre: " + nombre + ", estado activo: " + estadoActivo + ", rol: " + rol;
        return id + ", " + nombre + ", " + estadoActivo + ", " + rol;
    }
    
    
    
}

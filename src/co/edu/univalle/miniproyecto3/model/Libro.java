/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.model;

/**
 *
 * @author julia
 */
public class Libro {
    private int isbn;
    private String nombre;
    private boolean disponible;

    public Libro() {
    }

    public Libro(int isbn, String nombre, boolean disponible) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.disponible = disponible;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}

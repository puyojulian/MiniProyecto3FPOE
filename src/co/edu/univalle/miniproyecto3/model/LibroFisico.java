/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.model;

/**
 *
 * @author julia
 */
public class LibroFisico extends Libro {
    
    private int numeroEjemplar;

    public LibroFisico() {
    }

    public LibroFisico(int numeroEjemplar, int isbn, String nombre, boolean disponible) {
        super(isbn, nombre, disponible);
        this.numeroEjemplar = numeroEjemplar;
    }

    public int getNumeroEjemplar() {
        return numeroEjemplar;
    }

    public void setNumeroEjemplar(int numeroEjemplar) {
        this.numeroEjemplar = numeroEjemplar;
    }   
}

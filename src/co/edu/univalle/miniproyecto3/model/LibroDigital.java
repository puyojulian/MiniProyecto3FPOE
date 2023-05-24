/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.model;

/**
 *
 * @author julia
 */
public class LibroDigital extends Libro {
    
    private String url;

    public LibroDigital() {
    }

    public LibroDigital(String url, int isbn, String nombre, boolean disponible) {
        super(isbn, nombre, disponible);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }  
}

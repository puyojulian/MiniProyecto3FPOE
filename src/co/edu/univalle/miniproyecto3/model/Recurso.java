/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.model;

import java.util.ArrayList;

/**
 *
 * @author julia
 */
public class Recurso {
    private int codigoRecurso;
    private String isbn;
    private String nombre;
    private ArrayList<String> autores;
    private ArrayList<String> generosLiterarios;
    private ArrayList<String> areasConocimiento;
    private boolean disponible;
    private static int consecutivo = 0;

//    public Recurso(String nombre, String autor) {
//        this.codigoRecurso = consecutivo++;
//        this.isbn = 0;
//        this.nombre = "";
//        autores = new ArrayList();
//        autores.add(autor);
//        generosLiterarios = new ArrayList();
//        areasConocimiento = new ArrayList();
//    }

    public Recurso(String isbn, String nombre, String autor, String generoLiterario, String areaConocimiento) {
        this.codigoRecurso = consecutivo++;
        this.isbn = isbn;
        this.nombre = nombre;
        autores = new ArrayList();
        autores.add(autor);
        generosLiterarios = new ArrayList();
        generosLiterarios.add(generoLiterario);
        areasConocimiento = new ArrayList();
        areasConocimiento.add(areaConocimiento);
        this.disponible = true;
    }
    
    public int getCodigoRecurso() {
        return codigoRecurso;
    }

    public void setCodigoRecurso(int codigoRecurso) {
        this.codigoRecurso = codigoRecurso;
    }

    public ArrayList getAutores() {
        return autores;
    }

    public void setAutores(ArrayList autor) {
        this.autores = autor;
    }

    public ArrayList getGenerosLiterario() {
        return generosLiterarios;
    }

    public void setGenerosLiterario(ArrayList generosLiterarios) {
        this.generosLiterarios = generosLiterarios;
    }

    public ArrayList getAreasConocimiento() {
        return areasConocimiento;
    }

    public void setAreasConocimiento(ArrayList areasConocimiento) {
        this.areasConocimiento = areasConocimiento;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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
    
    @Override
    public String toString() {
//        return " Cod: " + codigoRecurso + " ISBN: " + isbn + " Nombre: " + nombre + " Autor: " + autores.get(0) + " Género: " + generosLiterarios.get(0) + " Área: " + areasConocimiento.get(0);
        return codigoRecurso + ", " + isbn + ", " + nombre + ", " + autores.get(0) + ", " + generosLiterarios.get(0) + ", " + areasConocimiento.get(0);

    }
}

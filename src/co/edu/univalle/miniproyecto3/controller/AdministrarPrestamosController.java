/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.repository.PrestamoDAO;
import co.edu.univalle.miniproyecto3.vista.AdministrarPrestamos;

/**
 *
 * @author User
 */
public class AdministrarPrestamosController {
    private AdministrarPrestamos administrarPrestamos;
    private PrestamoDAO prestamoDAO;

    public AdministrarPrestamosController(AdministrarPrestamos administrarPrestamos, PrestamoDAO prestamoDAO) {
        this.administrarPrestamos = administrarPrestamos;
        this.prestamoDAO = prestamoDAO;
    }   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.repository.UsuarioDAO;
import co.edu.univalle.miniproyecto3.vista.AgregarUsuario;

/**
 *
 * @author julia
 */
public class AgregarUsuarioController {
    private UsuarioDAO usuarioDAO;
    private AgregarUsuario agregarUsuario;
    
    public AgregarUsuarioController(AgregarUsuario agregarUsuario, UsuarioDAO usuarioDAO) {
        this.agregarUsuario = agregarUsuario;
        this.usuarioDAO = usuarioDAO;
    }
}

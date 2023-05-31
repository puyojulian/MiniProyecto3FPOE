/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Usuario;
import co.edu.univalle.miniproyecto3.repository.UsuarioDAO;
import co.edu.univalle.miniproyecto3.vista.EditarUsuario;

/**
 *
 * @author julia
 */
public class EditarUsuarioController {
    private int llaveTemporal;
    private Usuario usuarioTemporal;
    private UsuarioDAO usuarioDAO;
    private EditarUsuario vistaEditarUsuario;
    
    
    public EditarUsuarioController(EditarUsuario vista, UsuarioDAO dao) {
        this.vistaEditarUsuario = vista;
        this.usuarioDAO = dao;
    }
    
    public void abrirVista(int llave) {
        llaveTemporal = llave;
        Usuario usuario = usuarioDAO.getUsuario(llave);
        mostrarItems(usuario);
    }
    
    public void mostrarItems(Usuario usuario) {
        vistaEditarUsuario.getTxtNombre().setText(usuario.getNombre());
        vistaEditarUsuario.getTxtTipo().setText(usuario.getRol());
        vistaEditarUsuario.getTxtCodigo().setText(usuario.getId()+"");
    }
    
    
    
}

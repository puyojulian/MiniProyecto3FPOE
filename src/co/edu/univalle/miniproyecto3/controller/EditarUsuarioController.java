/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Usuario;
import co.edu.univalle.miniproyecto3.repository.UsuarioDAO;
import co.edu.univalle.miniproyecto3.vista.EditarUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author julia
 */
public class EditarUsuarioController {
    
    private int llaveTemporal;
    private Usuario usuarioTemporal;
    private UsuarioDAO usuarioDAO;
    private EditarUsuario editarUsuario;
    
    
    public EditarUsuarioController(EditarUsuario vista, UsuarioDAO dao) {
        this.editarUsuario = vista;
        this.usuarioDAO = dao;
        llaveTemporal = 0;
        
        ActionHandler manejadoraDeEventos = new ActionHandler();
        
        editarUsuario.addBtnCancelar(manejadoraDeEventos);
        editarUsuario.addBtnEditar(manejadoraDeEventos);
        editarUsuario.addComboBox(manejadoraDeEventos);
    }
    
    public void abrirVista(int llave) {
        llaveTemporal = llave;
        usuarioTemporal = usuarioDAO.getUsuario(llave);
        mostrarItems(usuarioTemporal);
    }
    
    public void mostrarItems(Usuario usuario) {
        editarUsuario.getTxtNombre().setText(usuario.getNombre());
        editarUsuario.getTxtTipo().setText(usuario.getRol());
        editarUsuario.getTxtCodigo().setText(usuario.getId()+"");
        if(usuarioTemporal.isEstadoActivo())
            editarUsuario.getJCombo().setSelectedIndex(0);
        else {
            editarUsuario.getJCombo().setSelectedIndex(1);
        }
    }
    
    class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == editarUsuario.getBtnCancelar()) {
                editarUsuario.getTxtCodigo().setText("");
                editarUsuario.getTxtNombre().setText("");
                editarUsuario.getTxtTipo().setText("");
                editarUsuario.setVisible(false);
            }
            else if(e.getSource() == editarUsuario.getBtnEditar()) {
                usuarioTemporal.setNombre(editarUsuario.getTxtNombre().getText());
                usuarioTemporal.setRol(editarUsuario.getTxtTipo().getText());
                if(editarUsuario.getJCombo().getSelectedIndex() == 0) {
                    usuarioTemporal.setEstadoActivo(true);
                }
                else if (editarUsuario.getJCombo().getSelectedIndex() == 1) {
                    usuarioTemporal.setEstadoActivo(false);
                }
            }
        }
        
    }
    
}

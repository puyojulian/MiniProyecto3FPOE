/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Usuario;
import co.edu.univalle.miniproyecto3.repository.UsuarioDAO;
import co.edu.univalle.miniproyecto3.vista.AgregarUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

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
        
        HandlerActions listener = new HandlerActions();
        
        agregarUsuario.addBtnAgregar(listener);
        agregarUsuario.addBtnVolver(listener);
        
//        Actualizar();
    }
    
    public void crearUsuario(String nombre, String rol) {
        usuarioDAO.addUsuario(new Usuario(nombre, rol));
    }
       
    class HandlerActions implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == agregarUsuario.getBtnAgregar()){
                String strNombre = agregarUsuario.getTxtNombre().getText();
                String strRol = agregarUsuario.getTxtTipoUsuario().getText();
                
                if (strNombre.isEmpty() || strRol.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    
                    crearUsuario(strNombre, strRol);
                    
                    strNombre = "";
                    strRol = "";
                    
                    JOptionPane.showMessageDialog(null, "Nuevo usuario registrado satifactoriamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    
                    agregarUsuario.setTxtNombre(strNombre);
                    agregarUsuario.setTxtTipoUsuario(strRol);
                }
            }
            if (e.getSource() == agregarUsuario.getBtnVolver()){
                agregarUsuario.dispose();
            }
        }
    }
}

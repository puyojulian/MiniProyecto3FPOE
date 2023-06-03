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
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author julia
 */
public class EditarUsuarioController {
    
    private Usuario usuarioTemporal;
    private UsuarioDAO usuarioDAO;
    private EditarUsuario editarUsuario;
    
    
    public EditarUsuarioController(EditarUsuario vista, UsuarioDAO dao) {
        this.editarUsuario = vista;
        this.usuarioDAO = dao;
        
        ActionHandler manejadoraDeEventos = new ActionHandler();
        
        editarUsuario.addBtnCancelar(manejadoraDeEventos);
        editarUsuario.addBtnEditar(manejadoraDeEventos);
        editarUsuario.addComboBox(manejadoraDeEventos);
    }
    
    public void abrirVista(int llave) {
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
    
    public void mensajeTemporal(String mensaje, String titulo, int milisegundos) {
        JOptionPane msg = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE);
        final JDialog dlg = msg.createDialog(titulo);
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            dlg.setVisible(false);
          }
        }).start();
        dlg.setVisible(true);
    }
    
    class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == editarUsuario.getBtnCancelar()) {
                editarUsuario.getTxtCodigo().setText("");
                editarUsuario.getTxtNombre().setText("");
                editarUsuario.getTxtTipo().setText("");
                editarUsuario.dispose();
            }
            else if(e.getSource() == editarUsuario.getBtnEditar()) {
                if ("".equals(editarUsuario.getTxtNombre().getText()) || "".equals(editarUsuario.getTxtTipo().getText())) {
                    mensajeTemporal("Debe editar los campos de texto con un valor", "Error", 1150);
                    mostrarItems(usuarioTemporal);
                } else {
                    usuarioTemporal.setNombre(editarUsuario.getTxtNombre().getText());
                    usuarioTemporal.setRol(editarUsuario.getTxtTipo().getText());
                    if(editarUsuario.getJCombo().getSelectedIndex() == 0) {
                        usuarioTemporal.setEstadoActivo(true);
                    }
                    else if (editarUsuario.getJCombo().getSelectedIndex() == 1) {
                        usuarioTemporal.setEstadoActivo(false);
                    }
                    mensajeTemporal("Usuario editado satisfactoriamente", "Aviso", 1150);
                    editarUsuario.dispose();
                }
            }
        }
        
    }
}

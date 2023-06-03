package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Usuario;
import co.edu.univalle.miniproyecto3.repository.UsuarioDAO;
import co.edu.univalle.miniproyecto3.vista.AgregarUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class AgregarUsuarioController {
    private UsuarioDAO usuarioDAO;
    private AgregarUsuario agregarUsuario;

    public AgregarUsuarioController(AgregarUsuario agregarUsuario, UsuarioDAO usuarioDAO) {

        this.agregarUsuario = agregarUsuario;
        this.usuarioDAO = usuarioDAO;
        
        HandlerActions listener = new HandlerActions();
        
        agregarUsuario.addBtnAgregar(listener);
        agregarUsuario.addBtnVolver(listener);
    }
    
    public void crearUsuario(String nombre, String rol) {
        usuarioDAO.addUsuario(new Usuario(nombre, rol));
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
                       
    class HandlerActions implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == agregarUsuario.getBtnAgregar()){
                String strNombre = agregarUsuario.getTxtNombre().getText();
                String strRol = agregarUsuario.getTxtTipoUsuario().getText();
                
                if (strNombre.isEmpty() || strRol.isEmpty()) {
                    mensajeTemporal("Todos los campos deben ser completados", "Error", 1150);
                } else {
                    crearUsuario(strNombre, strRol);
                    
                    strNombre = "";
                    strRol = "";
                    
                    agregarUsuario.setTxtNombre(strNombre);
                    agregarUsuario.setTxtTipoUsuario(strRol);
                    
                    mensajeTemporal("Usuario agregado satisfactoriamente.", "Aviso", 1000);
                }
            }
            if (e.getSource() == agregarUsuario.getBtnVolver()){
                agregarUsuario.dispose();
            }
        }
    }
}

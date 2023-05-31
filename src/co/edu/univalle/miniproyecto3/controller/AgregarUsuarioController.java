/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Usuario;
import co.edu.univalle.miniproyecto3.repository.UsuarioDAO;
import co.edu.univalle.miniproyecto3.vista.AgregarUsuario;
import co.edu.univalle.miniproyecto3.vista.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author julia
 */
public class AgregarUsuarioController {
    private UsuarioDAO usuarioDAO;
    private AgregarUsuario agregarUsuario;
    private MenuPrincipal menuPrincipal;
    private Map mapaUsuarios;
    private List<Map.Entry<Integer, Usuario>> listaMapUsuarios;
    private DefaultListModel<String> modeloLista;
    
    public AgregarUsuarioController(AgregarUsuario agregarUsuario, UsuarioDAO usuarioDAO, MenuPrincipal menuPrincipal, Map mapaUsuarios, List<Map.Entry<Integer, Usuario>> listaMapUsuarios, DefaultListModel modeloLista) {
        listaMapUsuarios = new ArrayList<>();
        this.modeloLista = modeloLista;
        this.listaMapUsuarios = listaMapUsuarios;
        this.agregarUsuario = agregarUsuario;
        this.usuarioDAO = usuarioDAO;
        this.menuPrincipal = menuPrincipal;
        this.mapaUsuarios = mapaUsuarios;
        
        HandlerActions listener = new HandlerActions();
        
        agregarUsuario.addBtnAgregar(listener);
        agregarUsuario.addBtnVolver(listener);
        
        Actualizar();
    }
    
    public void crearUsuario(String nombre, String rol) {
        usuarioDAO.addUsuario(new Usuario(nombre, rol));
    }
    
    public DefaultListModel Actualizar() {
        if(mapaUsuarios.size() > 0) {
            Set<Map.Entry<Integer, Usuario>> entrySetMapa = mapaUsuarios.entrySet();

            listaMapUsuarios = new ArrayList<>(entrySetMapa);
            
            modeloLista.clear();

            for (Map.Entry<Integer, Usuario> entry : listaMapUsuarios){
                Integer key = entry.getKey();
                Usuario value = entry.getValue();
                String item = "" + value;
                modeloLista.addElement(item);
            }
        }
        return modeloLista;
    }

    public Map getMapaUsuarios() {
        return mapaUsuarios;
    }

    public List<Map.Entry<Integer, Usuario>> getListaMapUsuarios() {
        return listaMapUsuarios;
    }

    public DefaultListModel<String> getModeloLista() {
        return modeloLista;
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
                    menuPrincipal.getJList().setModel(Actualizar());
                }
            }
            if (e.getSource() == agregarUsuario.getBtnVolver()){
                agregarUsuario.dispose();
            }
        }
    }
}

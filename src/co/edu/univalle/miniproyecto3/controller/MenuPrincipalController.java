package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Recurso;
import co.edu.univalle.miniproyecto3.model.Usuario;
import co.edu.univalle.miniproyecto3.repository.UsuarioDAO;
import co.edu.univalle.miniproyecto3.vista.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JRadioButton;

public class MenuPrincipalController {
    
    private MenuPrincipal menuPrincipal;
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    private Map mapaUsuarios;
    private List<Map.Entry<Integer, Usuario>> listaMap;
    private DefaultListModel<String> lista;
    private JList<String> jList;
    
    public MenuPrincipalController(MenuPrincipal menuPrincipal) {
        this.usuarioDAO = new UsuarioDAO();
        this.menuPrincipal = menuPrincipal;
        
        HandlerActions listener = new HandlerActions();
        menuPrincipal.addBtnRecursos(listener);
        menuPrincipal.addBtnUsuarios(listener);
        menuPrincipal.addBtnBuscar(listener);
        menuPrincipal.addBtnBusqueda(listener);
        menuPrincipal.addBtnAgregar(listener);
        menuPrincipal.addBtnActualizar(listener);
        menuPrincipal.addBtnEliminar(listener);
        usuariosActuales();
        mapaUsuarios = usuarioDAO.getUsuarios();
//        lista = new ArrayList<>(mapaUsuarios.entrySet());
    }

    private void usuariosActuales(){
        usuarioDAO.addUsuario(new Usuario("Sebastian", "Estudiante"));
        usuarioDAO.addUsuario(new Usuario("Carlos", "Estudiante"));
        usuarioDAO.addUsuario(new Usuario("Julian", "Docente"));
    }

    class HandlerActions implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((menuPrincipal.getBtnUsuarios()).isSelected()) {
                
                Set<Map.Entry<Integer, Usuario>> entrySet = mapaUsuarios.entrySet();
//                
//                lista = new ArrayList<>(mapaUsuarios.entrySet());
//                Map.Entry<Integer, Usuario>[] array = lista.toArray(new Map.Entry[0]);
//                menuPrincipal.setjList(array);
                lista = new DefaultListModel<>();
                listaMap = new ArrayList<>(mapaUsuarios.entrySet());

                for (Map.Entry<Integer, Usuario> entry : entrySet){
                    Integer key = entry.getKey();
                    Usuario value = entry.getValue();
                    String item = key + ", Usuario: " + value;
                    lista.addElement(item);
                    
                    menuPrincipal.setjList(lista);
                }
                
                if (e.getSource() == menuPrincipal.getBtnEliminar()) {
                jList = menuPrincipal.getjList();
                int index = jList.getSelectedIndex();

//                Map.Entry<Integer, Usuario> Entry = listaMap.get(index);
//                usuarioDAO.deleteUsuario((Entry.getKey()));
//
//                listaMap.remove(index);
//                lista.remove(index); 
//                menuPrincipal.setjList(lista);
                
                System.out.println(index);
                
                for (Map.Entry<Integer, Usuario> Lista : listaMap) {
                       System.out.println("Lista: " + listaMap);
                    }
        
                Set<Map.Entry<Integer, Usuario>> Set = mapaUsuarios.entrySet();
        
                for (Map.Entry<Integer, Usuario> entry : Set) {
                    Integer key = entry.getKey();
                    Usuario value = entry.getValue();
                    System.out.println("Key: " + key + ", Value: " + value);
                    }                
                }
            }
        }
    }
}
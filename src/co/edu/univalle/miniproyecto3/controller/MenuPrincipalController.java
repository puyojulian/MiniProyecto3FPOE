package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Recurso;
import co.edu.univalle.miniproyecto3.model.Usuario;
import co.edu.univalle.miniproyecto3.repository.RecursoDAO;
import co.edu.univalle.miniproyecto3.repository.UsuarioDAO;
import co.edu.univalle.miniproyecto3.repository.RecursoDAO;
import co.edu.univalle.miniproyecto3.vista.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class MenuPrincipalController {
    
    private MenuPrincipal menuPrincipal;
    private UsuarioDAO usuarioDAO;
    private RecursoDAO recursoDAO;
    private Map mapaUsuarios;
    private Map mapaRecursos;
    private List<Map.Entry<Integer, Usuario>> listaMapUsuarios;
    private List<Map.Entry<String, Recurso>> listaMapRecursos;
    private DefaultListModel<String> modeloLista;
    private JList<String> jLista;
    private int index;
    
    public MenuPrincipalController(MenuPrincipal menuPrincipal) {
        this.usuarioDAO = new UsuarioDAO();
        this.recursoDAO = new RecursoDAO();
        this.menuPrincipal = menuPrincipal;
        this.jLista = menuPrincipal.getjList();
        
        HandlerActions listener = new HandlerActions();
        
        menuPrincipal.addBtnRecursos(listener);
        menuPrincipal.addBtnUsuarios(listener);
        menuPrincipal.addBtnPrestamos(listener);
        menuPrincipal.addBtnBuscar(listener);
        menuPrincipal.addBtnBusqueda(listener);
        menuPrincipal.addBtnAgregar(listener);
        menuPrincipal.addBtnActualizar(listener);
        menuPrincipal.addBtnEliminar(listener);
        
        usuariosActuales();
        recursosActuales();
        mapaUsuarios = usuarioDAO.getUsuarios();
        mapaRecursos = recursoDAO.getRecursos();
    }

    private void usuariosActuales(){
        usuarioDAO.addUsuario(new Usuario("Sebastian", "Estudiante"));
        usuarioDAO.addUsuario(new Usuario("Carlos", "Estudiante"));
        usuarioDAO.addUsuario(new Usuario("Julian", "Docente"));
    }
    
    private void recursosActuales(){
        recursoDAO.addRecurso(new Recurso("9780141182561", "To Kill a Mockingbird", "Harper Lee", "Fiction", "Literature"));
        recursoDAO.addRecurso(new Recurso("9780061120084", "1984", "George Orwell", "Fiction", "Science Fiction"));
        recursoDAO.addRecurso(new Recurso("9780141439518", "Pride and Prejudice", "Jane Austen", "Fiction", "Classic Literature"));
        recursoDAO.addRecurso(new Recurso("9780060256654", "Where the Wild Things Are", "Maurice Sendak", "Children's Fiction", "Picture Books"));
        recursoDAO.addRecurso(new Recurso("9780743273565", "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", "Classic Literature"));
        recursoDAO.addRecurso(new Recurso("9780307474278", "The Hunger Games", "Suzanne Collins", "Fiction", "Young Adult"));
        recursoDAO.addRecurso(new Recurso("9780143105954", "The Catcher in the Rye", "J.D. Salinger", "Fiction", "Literature"));
        recursoDAO.addRecurso(new Recurso("9780064404990", "Bridge to Terabithia", "Katherine Paterson", "Children's Fiction", "Fantasy"));
        recursoDAO.addRecurso(new Recurso("9780060935467", "Freakonomics: A Rogue Economist Explores the Hidden Side of Everything", "Steven D. Levitt and Stephen J. Dubner", "Non-Fiction", "Economics"));
    }
        
    class HandlerActions implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == menuPrincipal.getBtnUsuarios()) {
                menuPrincipal.getBtnRecursos().setSelected(false);
                menuPrincipal.getBtnPrestamos().setSelected(false);
                menuPrincipal.getBtnRecursos().setEnabled(true);
                menuPrincipal.getBtnPrestamos().setEnabled(true);
                menuPrincipal.getBtnUsuarios().setEnabled(false);
                
                if(mapaUsuarios.size()>0) {
                    Set<Map.Entry<Integer, Usuario>> entrySetMapa = mapaUsuarios.entrySet();

                    modeloLista = new DefaultListModel<>();

                    listaMapUsuarios = new ArrayList<>(mapaUsuarios.entrySet());

                    for (Map.Entry<Integer, Usuario> entry : entrySetMapa){
                        Integer key = entry.getKey();
                        Usuario value = entry.getValue();
                        String item = key + ", Usuario: " + value;
                        modeloLista.addElement(item);
                    }
                    jLista.setModel(modeloLista);
                }
                else {
                    modeloLista.clear();
                    jLista.setModel(modeloLista);
                }
            }
            else if(e.getSource() == menuPrincipal.getBtnRecursos()) {
                menuPrincipal.getBtnUsuarios().setSelected(false);
                menuPrincipal.getBtnPrestamos().setSelected(false);
                menuPrincipal.getBtnUsuarios().setEnabled(true);
                menuPrincipal.getBtnPrestamos().setEnabled(true);
                menuPrincipal.getBtnRecursos().setEnabled(false);
                
                if(mapaRecursos.size()>0) {
                    Set<Map.Entry<String, Recurso>> entrySetMapa = mapaRecursos.entrySet();

                    modeloLista = new DefaultListModel<>();

                    listaMapRecursos = new ArrayList<>(mapaRecursos.entrySet());

                    for (Map.Entry<String, Recurso> entry : entrySetMapa){
                        String key = entry.getKey();
                        Recurso value = entry.getValue();
                        String item = key + ", Recurso: " + value;
                        modeloLista.addElement(item);
                    }
                    jLista.setModel(modeloLista);
                }
                else {
                    modeloLista.clear();
                    jLista.setModel(modeloLista);
                }
                
            }
            else if(e.getSource() == menuPrincipal.getBtnPrestamos()) {
                menuPrincipal.getBtnRecursos().setSelected(false);
                menuPrincipal.getBtnUsuarios().setSelected(false);
                menuPrincipal.getBtnRecursos().setEnabled(true);
                menuPrincipal.getBtnUsuarios().setEnabled(true);
                menuPrincipal.getBtnPrestamos().setEnabled(false);
            }
            if (e.getSource() == menuPrincipal.getBtnEliminar()) {
                if (menuPrincipal.getBtnUsuarios().isSelected()) {
                    index = menuPrincipal.getjListIndex();
                    if(index != -1) {
                        Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(index);

                        usuarioDAO.deleteUsuario((entry.getKey()));
                        listaMapUsuarios.remove(index);
                        modeloLista.remove(index); 

                        jLista.setModel(modeloLista);
                    }    
                }
                else if(menuPrincipal.getBtnRecursos().isSelected()) {
                    index = menuPrincipal.getjListIndex();
                    if(index != -1) {
                        Map.Entry<String, Recurso> entry = listaMapRecursos.get(index);

                        recursoDAO.deleteRecurso((entry.getKey()));
                        listaMapRecursos.remove(index);
                        modeloLista.remove(index); 

                        jLista.setModel(modeloLista);
                    }
                }
                else if(menuPrincipal.getBtnPrestamos().isSelected()) {
//                index = menuPrincipal.getjListIndex();
//                Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(index);
//                
//                usuarioDAO.deleteUsuario((entry.getKey()));
//                listaMapUsuarios.remove(index);
//                modeloLista.remove(index); 
//
//                jLista.setModel(modeloLista);
                }
            }
        }
    }
}
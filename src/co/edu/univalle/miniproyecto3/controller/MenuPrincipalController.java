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
    private List<Map.Entry<Integer, Usuario>> listaMap;
    private DefaultListModel<String> lista;
    private JList<String> jList;
    private int index;
    
    public MenuPrincipalController(MenuPrincipal menuPrincipal) {
        this.usuarioDAO = new UsuarioDAO();
        this.recursoDAO = new RecursoDAO();
        this.menuPrincipal = menuPrincipal;
        this.jList = menuPrincipal.getjList();
        
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
        mapaRecursos = recursoDAO.getRecursos();
    }
        
    class HandlerActions implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((menuPrincipal.getBtnUsuarios()).isSelected()) {
                
                Set<Map.Entry<Integer, Usuario>> entrySet = mapaUsuarios.entrySet();

                lista = new DefaultListModel<>();
                listaMap = new ArrayList<>(mapaUsuarios.entrySet());

                for (Map.Entry<Integer, Usuario> entry : entrySet){
                    Integer key = entry.getKey();
                    Usuario value = entry.getValue();
                    String item = key + ", Usuario: " + value;
                    lista.addElement(item);
                    
                    menuPrincipal.setjList(lista);
                    menuPrincipal.clearSelection();
                }
            }
            if (e.getSource() == menuPrincipal.getBtnEliminar()) {
                index = menuPrincipal.getjListIndex();
                Map.Entry<Integer, Usuario> entry = listaMap.get(index);
                usuarioDAO.deleteUsuario((entry.getKey()));

                listaMap.remove(index);
                lista.remove(index); 
                menuPrincipal.setjList(lista);             
            }
        }
    }
}
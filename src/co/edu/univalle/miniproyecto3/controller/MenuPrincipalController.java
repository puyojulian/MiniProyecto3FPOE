package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Prestamo;
import co.edu.univalle.miniproyecto3.model.Recurso;
import co.edu.univalle.miniproyecto3.model.Usuario;
import co.edu.univalle.miniproyecto3.repository.PrestamoDAO;
import co.edu.univalle.miniproyecto3.repository.UsuarioDAO;
import co.edu.univalle.miniproyecto3.repository.RecursoDAO;
import co.edu.univalle.miniproyecto3.vista.AdministrarPrestamos;
import co.edu.univalle.miniproyecto3.vista.AgregarRecurso;
import co.edu.univalle.miniproyecto3.vista.AgregarUsuario;
import co.edu.univalle.miniproyecto3.vista.EditarRecurso;
import co.edu.univalle.miniproyecto3.vista.EditarUsuario;
import co.edu.univalle.miniproyecto3.vista.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class MenuPrincipalController {
    
    private MenuPrincipal menuPrincipal;
    private AgregarRecurso agregarRecurso;
    private AgregarUsuario agregarUsuario;
    private EditarUsuario editarUsuario;
    private EditarRecurso editarRecurso;
    private AdministrarPrestamos administrarPrestamos;
    private AgregarRecursoController agregarRecursoController;
    private AgregarUsuarioController agregarUsuarioController;
    private EditarUsuarioController editarUsuarioController;
    private EditarRecursoController editarRecursoController;
    private UsuarioDAO usuarioDAO;
    private RecursoDAO recursoDAO;
    private PrestamoDAO prestamoDAO;
    private Map mapaUsuarios;
    private Map mapaRecursos;
    private Map mapaPrestamos;
    private javax.swing.JList<String> jLista;
    private List<Map.Entry<Integer, Usuario>> listaMapUsuarios;
    private List<Map.Entry<String, Recurso>> listaMapRecursos;
    private List<Map.Entry<Integer, Prestamo>> listaMapPrestamos;
    private String strBusqueda1;
    private String strBusqueda2;
    private DefaultListModel<String> modeloLista;
    private DefaultListModel<String> modeloListaResultado;
    private int index;
    
    public MenuPrincipalController(MenuPrincipal menuPrincipal) {
        this.usuarioDAO = new UsuarioDAO();
        this.recursoDAO = new RecursoDAO();
        this.prestamoDAO = new PrestamoDAO();
        this.menuPrincipal = menuPrincipal;
        this.jLista = menuPrincipal.getJList();
        this.modeloLista = new DefaultListModel<>();
        this.modeloListaResultado = new DefaultListModel<>();
        
        HandlerActions listener = new HandlerActions();
        
        agregarRecurso = new AgregarRecurso();
        agregarRecurso.addBtnAgregar(listener);
        agregarRecursoController = new AgregarRecursoController(agregarRecurso, recursoDAO);
        agregarUsuario = new AgregarUsuario();
        agregarUsuario.addBtnAgregar(listener);
        agregarUsuarioController = new AgregarUsuarioController(agregarUsuario, usuarioDAO);
        editarUsuario = new EditarUsuario();
        editarUsuario.addBtnEditar(listener);
        editarUsuarioController = new EditarUsuarioController(editarUsuario, usuarioDAO);
        editarRecurso = new EditarRecurso();
        editarRecurso.addBtnEditar(listener);
        editarRecursoController = new EditarRecursoController(editarRecurso, recursoDAO);
        administrarPrestamos = new AdministrarPrestamos();
        
        menuPrincipal.addBtnRecursos(listener);
        menuPrincipal.addBtnUsuarios(listener);
        menuPrincipal.addBtnPrestamos(listener);
        menuPrincipal.addBtnBuscar(listener);
        menuPrincipal.addBtnBusqueda(listener);
        menuPrincipal.addBtnAgregar(listener);
        menuPrincipal.addBtnActualizar(listener);
        menuPrincipal.addBtnEliminar(listener);
        menuPrincipal.addBtnRadioBusqueda1(listener);
        menuPrincipal.addBtnRadioBusqueda2(listener);
        menuPrincipal.addBtnPopConfirmar(listener);
        
        
        usuariosActuales();
        recursosActuales();
        mapaUsuarios = usuarioDAO.getUsuarios();
        mapaRecursos = recursoDAO.getRecursos();
        mapaPrestamos = prestamoDAO.getPrestamo();
        prestamosActuales();
        
//        agregarUsuarioController = new AgregarUsuarioController(agregarUsuario, usuarioDAO, menuPrincipal, mapaUsuarios, listaMapUsuarios, modeloLista);
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
    
    private void prestamosActuales() {
        Collection<Usuario> usuarios = mapaUsuarios.values();
        Set<Map.Entry<String, Recurso>> entrySetMapaR = mapaRecursos.entrySet();

        int count = 1;

        for (Map.Entry<String, Recurso> entryR : entrySetMapaR) {
            Recurso valueR = entryR.getValue();

            if (count > usuarios.size()) {
                break;
            }

            Usuario valueU = usuarios.toArray(new Usuario[0])[count - 1];
            if (valueR.isDisponible() == true){
                valueR.setDisponible(false);
                prestamoDAO.addPrestamo(new Prestamo(valueU, valueR));
            }

            count++;
        }
    }
    
    public void actualizarJListaUsuarios() {
        if(mapaUsuarios.size() > 0) {
            Set<Map.Entry<Integer, Usuario>> entrySetMapa = mapaUsuarios.entrySet();

            modeloLista.clear();

            listaMapUsuarios = new ArrayList<>(mapaUsuarios.entrySet());

            for (Map.Entry<Integer, Usuario> entry : entrySetMapa){
//                        Integer key = entry.getKey();
                Usuario value = entry.getValue();
//                        String item = key + ", Usuario: " + value;
                String item = "" + value;
                modeloLista.addElement(item);
            }
            menuPrincipal.getJList().setModel(modeloLista);
        }
        else {
            modeloLista.clear();
            menuPrincipal.getJList().setModel(modeloLista);
        }
    }
    
    public void actualizarJListaRecursos() {
        if(mapaRecursos.size() > 0) {
            Set<Map.Entry<String, Recurso>> entrySetMapa = mapaRecursos.entrySet();

            modeloLista.clear();

            listaMapRecursos = new ArrayList<>(mapaRecursos.entrySet());

            for (Map.Entry<String, Recurso> entry : entrySetMapa){
//                        String key = entry.getKey();
                Recurso value = entry.getValue();
//                        String item = key + ", Recurso: " + value;
                String item = "" + value;   
                modeloLista.addElement(item);
            }
            menuPrincipal.getJList().setModel(modeloLista);
        }
        else {
            modeloLista.clear();
            menuPrincipal.getJList().setModel(modeloLista);
        }
    }
    
    public void actualizarJListaPrestamos() {
        if(mapaPrestamos.size() > 0) {
            Set<Map.Entry<Integer, Prestamo>> entrySetMapa = mapaPrestamos.entrySet();

            modeloLista.clear();

            listaMapPrestamos = new ArrayList<>(mapaPrestamos.entrySet());

            for (Map.Entry<Integer, Prestamo> entry : entrySetMapa){
//                        Integer key = entry.getKey();
                Prestamo value = entry.getValue();
//                        String item = key + ", Prestamo: " + value;
                String item = "" + value;
                modeloLista.addElement(item);
            }
            jLista.setModel(modeloLista);
        }
        else {
            modeloLista.clear();
            jLista.setModel(modeloLista);
        }
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
                
                menuPrincipal.getBtnRadioBusqueda1().setText("Nombre y Código");
                menuPrincipal.getBtnRadioBusqueda2().setText("Nombre y Rol");
                
                actualizarJListaUsuarios();
//                if(mapaUsuarios.size() > 0) {
//                    Set<Map.Entry<Integer, Usuario>> entrySetMapa = mapaUsuarios.entrySet();
//
//                    modeloLista.clear();
//
//                    listaMapUsuarios = new ArrayList<>(mapaUsuarios.entrySet());
//
//                    for (Map.Entry<Integer, Usuario> entry : entrySetMapa){
////                        Integer key = entry.getKey();
//                        Usuario value = entry.getValue();
////                        String item = key + ", Usuario: " + value;
//                        String item = "" + value;
//                        modeloLista.addElement(item);
//                    }
//                    menuPrincipal.getJList().setModel(modeloLista);
//                }
//                else {
//                    modeloLista.clear();
//                    menuPrincipal.getJList().setModel(modeloLista);
//                }
            }
            else if(e.getSource() == menuPrincipal.getBtnRecursos()) {
                menuPrincipal.getBtnUsuarios().setSelected(false);
                menuPrincipal.getBtnPrestamos().setSelected(false);
                menuPrincipal.getBtnUsuarios().setEnabled(true);
                menuPrincipal.getBtnPrestamos().setEnabled(true);
                menuPrincipal.getBtnRecursos().setEnabled(false);
                
                actualizarJListaRecursos();
                
                menuPrincipal.getBtnRadioBusqueda1().setText("Título y Autor");
                menuPrincipal.getBtnRadioBusqueda2().setText("Título y Género");
                
//                if(mapaRecursos.size() > 0) {
//                    Set<Map.Entry<String, Recurso>> entrySetMapa = mapaRecursos.entrySet();
//
//                    modeloLista.clear();
//
//                    listaMapRecursos = new ArrayList<>(mapaRecursos.entrySet());
//
//                    for (Map.Entry<String, Recurso> entry : entrySetMapa){
////                        String key = entry.getKey();
//                        Recurso value = entry.getValue();
////                        String item = key + ", Recurso: " + value;
//                        String item = "" + value;   
//                        modeloLista.addElement(item);
//                    }
//                    menuPrincipal.getJList().setModel(modeloLista);
//                }
//                else {
//                    modeloLista.clear();
//                    menuPrincipal.getJList().setModel(modeloLista);
//                }
            }
            else if(e.getSource() == menuPrincipal.getBtnPrestamos()) {
                menuPrincipal.getBtnRecursos().setSelected(false);
                menuPrincipal.getBtnUsuarios().setSelected(false);
                menuPrincipal.getBtnRecursos().setEnabled(true);
                menuPrincipal.getBtnUsuarios().setEnabled(true);
                menuPrincipal.getBtnPrestamos().setEnabled(false);
                
                actualizarJListaPrestamos();
                
//                if(mapaPrestamos.size() > 0) {
//                    Set<Map.Entry<Integer, Prestamo>> entrySetMapa = mapaPrestamos.entrySet();
//
//                    modeloLista.clear();
//
//                    listaMapPrestamos = new ArrayList<>(mapaPrestamos.entrySet());
//
//                    for (Map.Entry<Integer, Prestamo> entry : entrySetMapa){
////                        Integer key = entry.getKey();
//                        Prestamo value = entry.getValue();
////                        String item = key + ", Prestamo: " + value;
//                        String item = "" + value;
//                        modeloLista.addElement(item);
//                    }
//                    jLista.setModel(modeloLista);
//                }
//                else {
//                    modeloLista.clear();
//                    jLista.setModel(modeloLista);
//                }
                
                menuPrincipal.getBtnRadioBusqueda1().setText("Nombre y Fecha");
                menuPrincipal.getBtnRadioBusqueda2().setText("Título y Autor");

            }
            else if (e.getSource() == menuPrincipal.getBtnEliminar()) { // ELIMINAR
                if (menuPrincipal.getBtnUsuarios().isSelected()) {
                    index = menuPrincipal.getJListIndex();
//                    mapaUsuarios = agregarUsuarioController.getMapaUsuarios();
//                    listaMapUsuarios = agregarUsuarioController.getListaMapUsuarios();
//                    modeloLista = agregarUsuarioController.getModeloLista();
                    
//                    System.out.println(listaMapUsuarios.size());
//                    System.out.println(modeloLista.size());
                    
                    if(index != -1) {
                        Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(index);

                        usuarioDAO.deleteUsuario((entry.getKey()));
                        listaMapUsuarios.remove(index);
                        modeloLista.remove(index); 

                        menuPrincipal.getJList().setModel(modeloLista);
                    }    
                }
                else if(menuPrincipal.getBtnRecursos().isSelected()) {
                    index = menuPrincipal.getJListIndex();
                    if(index != -1) {
                        Map.Entry<String, Recurso> entry = listaMapRecursos.get(index);

                        recursoDAO.deleteRecurso((entry.getKey()));
                        listaMapRecursos.remove(index);
                        modeloLista.remove(index); 

                        menuPrincipal.getJList().setModel(modeloLista);
                    }
                }
                else if(menuPrincipal.getBtnPrestamos().isSelected()) {
                    index = menuPrincipal.getJListIndex();
                    Map.Entry<Integer, Prestamo> entry = listaMapPrestamos.get(index);
                    
                    prestamoDAO.deletePrestamo((entry.getKey()));
                    listaMapPrestamos.remove(index);
                    modeloLista.remove(index); 

                    menuPrincipal.getJList().setModel(modeloLista);
                }
            }
            else if (e.getSource() == menuPrincipal.getBtnBusqueda()) { // Obtener JPanel de Busqueda Avanzada.
                if(menuPrincipal.getJpBusquedaAvanzada().isVisible()) {
                    menuPrincipal.getJpBusquedaAvanzada().setVisible(false);
                }
                else {
                    menuPrincipal.getJpBusquedaAvanzada().setVisible(true);
                }
            }
            else if (e.getSource() == menuPrincipal.getBtnAgregar()) { // AGREGAR
                if (menuPrincipal.getBtnUsuarios().isSelected()) {
                    agregarUsuario.setVisible(true);
                }
                else if(menuPrincipal.getBtnRecursos().isSelected()) {
                    agregarRecurso.setVisible(true);
                }
                else if(menuPrincipal.getBtnPrestamos().isSelected()) {
                    administrarPrestamos.setVisible(true);
                }
            }
            else if (e.getSource() == menuPrincipal.getBtnActualizar()) { // ACTUALIZAR
                if (menuPrincipal.getBtnUsuarios().isSelected()) {
                    index = menuPrincipal.getJListIndex();
                    if(index != -1) {
                        Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(index);
                        editarUsuario.setVisible(true);
                        editarUsuarioController.abrirVista(entry.getKey());
                    } 
                }
                else if(menuPrincipal.getBtnRecursos().isSelected()) {
                    index = menuPrincipal.getJListIndex();
                    if(index != -1) {
                        Map.Entry<String, Recurso> entry = listaMapRecursos.get(index);
                        editarRecurso.setVisible(true);
                        editarRecursoController.abrirVista(entry.getKey());
                    }
                }
                else if(menuPrincipal.getBtnPrestamos().isSelected()) {
                    administrarPrestamos.setVisible(true);
                }
            }
            else if (e.getSource() == menuPrincipal.getBtnDetalles()) { // DETALLES 
                if (menuPrincipal.getBtnUsuarios().isSelected()) {
                    
                }
                else if(menuPrincipal.getBtnRecursos().isSelected()) {

                }
                else if(menuPrincipal.getBtnPrestamos().isSelected()) {

                }
            }
            else if(e.getSource() == menuPrincipal.getBtnBuscar()) { //BUSQUEDA PRINCIPAL
                modeloListaResultado.clear();
                strBusqueda1 = menuPrincipal.getTxtBuscar().getText();
                if(!"".equals(strBusqueda1)) {
                    String strBusqueda1Clean = strBusqueda1.replaceAll("\\s", "");
                    for (int i = 0; i < modeloLista.size(); i++) {
                        String filaLista = modeloLista.getElementAt(i);
                        StringTokenizer listaTemporal =  new StringTokenizer(filaLista,",");
                        while (listaTemporal.hasMoreTokens()) {
                            String token = listaTemporal.nextToken();
                            String tokenClean = token.replaceAll("\\s", "");
                            if(strBusqueda1Clean.equalsIgnoreCase(tokenClean)) {
                                modeloListaResultado.addElement(filaLista);
                                break;
                            }
                            else {
                                StringTokenizer elementoTemporal = new StringTokenizer(token," ");
                                while (elementoTemporal.hasMoreTokens()) {
                                    if(strBusqueda1.equalsIgnoreCase(elementoTemporal.nextToken())) {
                                        modeloListaResultado.addElement(filaLista);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    menuPrincipal.getJList().setModel(modeloListaResultado);
                }
                else {
                    menuPrincipal.getJList().setModel(modeloLista);
                }
            }
            else if(e.getSource() == menuPrincipal.getBtnPopConfirmar()) {
                modeloListaResultado.clear();
                strBusqueda1 = menuPrincipal.getTxtBusqueda1().getText();
                strBusqueda2 = menuPrincipal.getTxtBusqueda2().getText();
                if(!"".equals(strBusqueda1)) {
                    String strBusqueda1Clean = strBusqueda1.replaceAll("\\s", "");
                    String strBusqueda2Clean = strBusqueda2.replaceAll("\\s", "");
                    for (int i = 0; i < modeloLista.size(); i++) {
                        String filaLista = modeloLista.getElementAt(i);
                        StringTokenizer listaTemporal =  new StringTokenizer(filaLista,",");
                        while (listaTemporal.hasMoreTokens()) {
                            String token = listaTemporal.nextToken();
                            String tokenClean = token.replaceAll("\\s", "");
                            if(strBusqueda1Clean.equalsIgnoreCase(tokenClean) || strBusqueda2Clean.equalsIgnoreCase(tokenClean)) {
                                modeloListaResultado.addElement(filaLista);
                                break;
                            }
                            else {
                                StringTokenizer elementoTemporal = new StringTokenizer(token," ");
                                while (elementoTemporal.hasMoreTokens()) {
                                    String subToken = elementoTemporal.nextToken();
                                    if(strBusqueda1.equalsIgnoreCase(subToken) || strBusqueda2.equalsIgnoreCase(subToken)) {
                                        modeloListaResultado.addElement(filaLista);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    menuPrincipal.getJList().setModel(modeloListaResultado);
                }
                else {
                    menuPrincipal.getJList().setModel(modeloLista);
                }
                menuPrincipal.getJpBusquedaAvanzada().setVisible(false);
            }    
            else if(e.getSource() == agregarUsuario.getBtnAgregar()) {
                try {
                    Thread.sleep(200);
                    mapaUsuarios = usuarioDAO.getUsuarios();
                    actualizarJListaUsuarios();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(e.getSource() == editarUsuario.getBtnEditar()) {
                try {
                    Thread.sleep(200);
                    mapaUsuarios = usuarioDAO.getUsuarios();
                    actualizarJListaUsuarios();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(e.getSource() == agregarRecurso.getBotonAgregar()) {
                try {
                    Thread.sleep(200);
                    mapaRecursos = recursoDAO.getRecursos();
                    actualizarJListaRecursos();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(e.getSource() == editarRecurso.getBtnEditar()) {
                try {
                    Thread.sleep(200);
                    mapaRecursos = recursoDAO.getRecursos();
                    actualizarJListaRecursos();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }
}
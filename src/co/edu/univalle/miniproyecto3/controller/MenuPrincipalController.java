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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

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
    private AdministrarPrestamosController administrarPrestamosController;
    private UsuarioDAO usuarioDAO;
    private RecursoDAO recursoDAO;
    private PrestamoDAO prestamoDAO;
    private Map mapaUsuarios;
    private Map mapaRecursos;
    private Map mapaPrestamos;
//    private javax.swing.JList<String> jLista;
    private javax.swing.JTable jTable;
    private List<Map.Entry<Integer, Usuario>> listaMapUsuarios;
    private List<Map.Entry<String, Recurso>> listaMapRecursos;
    private List<Map.Entry<Integer, Prestamo>> listaMapPrestamos;
    private String strBusqueda1;
    private String strBusqueda2;
    private DefaultTableModel modeloTabla;
//    private DefaultListModel<String> modeloLista;
    private DefaultTableModel modeloTablaResultado;
    private DefaultListModel<String> modeloListaResultado;
    private List listaTemporal;
    private int index;
    
    public MenuPrincipalController(MenuPrincipal menuPrincipal) {
        this.usuarioDAO = new UsuarioDAO();
        this.recursoDAO = new RecursoDAO();
        this.prestamoDAO = new PrestamoDAO();
        this.menuPrincipal = menuPrincipal;
//        this.jLista = menuPrincipal.getJList();
        this.jTable = menuPrincipal.getJTable();
//        this.modeloLista = new DefaultListModel<>();
        this.modeloListaResultado = new DefaultListModel<>();
        this.modeloTabla = new DefaultTableModel();
        this.modeloTablaResultado = new DefaultTableModel();
        this.listaTemporal = new ArrayList();
        
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
        administrarPrestamos.addBtnConfirmar(listener);
        administrarPrestamosController = new AdministrarPrestamosController(administrarPrestamos, prestamoDAO, recursoDAO, usuarioDAO);
        
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
        mapaPrestamos = prestamoDAO.getPrestamos();
        prestamosActuales();
        
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

            listaTemporal.clear();
            modeloTabla.setRowCount(0);
//            modeloLista.clear();

            listaMapUsuarios = new ArrayList<>(mapaUsuarios.entrySet());
            
            establecerIdentificadoresColumnas();

            for (Map.Entry<Integer, Usuario> entry : entrySetMapa){
                Usuario value = entry.getValue();
                String item = "" + value;
                StringTokenizer tokenizer = new StringTokenizer(item,",");
                while(tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    listaTemporal.add(token);
                }
                
//                modeloLista.addElement(item);
                modeloTabla.addRow(listaTemporal.toArray());
                listaTemporal.clear();
            }
//            menuPrincipal.getJList().setModel(modeloLista);
            jTable.setModel(modeloTabla);
        }
        else {
//            modeloLista.clear();
            modeloTabla.setRowCount(0);
//            menuPrincipal.getJList().setModel(modeloLista);
            jTable.setModel(modeloTabla);
        }
    }
    
    public void actualizarJListaRecursos() {
        if(mapaRecursos.size() > 0) {
            Set<Map.Entry<String, Recurso>> entrySetMapa = mapaRecursos.entrySet();

            listaTemporal.clear();
            modeloTabla.setRowCount(0);
//            modeloLista.clear();

            listaMapRecursos = new ArrayList<>(mapaRecursos.entrySet());
            
            establecerIdentificadoresColumnas();

            for (Map.Entry<String, Recurso> entry : entrySetMapa){
                Recurso value = entry.getValue();
                String item = "" + value;
                StringTokenizer tokenizer = new StringTokenizer(item,",");
                while(tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    listaTemporal.add(token);
                }
                
//                modeloLista.addElement(item);
                modeloTabla.addRow(listaTemporal.toArray());
                listaTemporal.clear();
            }
//            menuPrincipal.getJList().setModel(modeloLista);
            jTable.setModel(modeloTabla);
        }
        else {
//            modeloLista.clear();
            modeloTabla.setRowCount(0);
//            menuPrincipal.getJList().setModel(modeloLista);
            jTable.setModel(modeloTabla);
        }
    }
    
    public void actualizarJListaPrestamos() {
        if(mapaPrestamos.size() > 0) {
            Set<Map.Entry<Integer, Prestamo>> entrySetMapa = mapaPrestamos.entrySet();

            listaTemporal.clear();
            modeloTabla.setRowCount(0);
//            modeloLista.clear();

            listaMapPrestamos = new ArrayList<>(mapaPrestamos.entrySet());
            
            establecerIdentificadoresColumnas();

            for (Map.Entry<Integer, Prestamo> entry : entrySetMapa){
                Prestamo value = entry.getValue();
                String item = "" + value;
                StringTokenizer tokenizer = new StringTokenizer(item,",");
                while(tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    listaTemporal.add(token);
                }
                
//                modeloLista.addElement(item);
                modeloTabla.addRow(listaTemporal.toArray());
                listaTemporal.clear();
            }
//            jLista.setModel(modeloLista);
            jTable.setModel(modeloTabla);
        }
        else {
//            modeloLista.clear();
            modeloTabla.setRowCount(0);
//            jLista.setModel(modeloLista);
            jTable.setModel(modeloTabla);
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
    
    public void establecerIdentificadoresColumnas() {
        if(menuPrincipal.getBtnUsuarios().isSelected()) {
            String[] atributosTablaUsuarios = {"ID", "NOMBRE", "ESTADO ACTIVO", "ROL"};
            modeloTabla.setColumnIdentifiers(atributosTablaUsuarios);
        }
        else if(menuPrincipal.getBtnRecursos().isSelected()){
            String[] atributosTablaRecursos = {"CODIGO", "ISBN", "TÍTULO", "AUTOR", "GÉNERO", "ÁREA", "DISPONIBLE"};
            modeloTabla.setColumnIdentifiers(atributosTablaRecursos);
        }
        else if(menuPrincipal.getBtnPrestamos().isSelected()){
            String[] atributosTablaRPrestamos = {"ID RECURSO", "ID USUARIO", "NOMBRE", "ESTADO ACTIVO", "ROL", "CODIGO", "ISBN", "TÍTULO", "AUTOR", "GÉNERO", "ÁREA", "DISPONIBLE"};
            modeloTabla.setColumnIdentifiers(atributosTablaRPrestamos);
        }
    }
    
    public void establecerIdentificadoresColumnasResultado() {
        if(menuPrincipal.getBtnUsuarios().isSelected()) {
            String[] atributosTablaUsuarios = {"ID", "NOMBRE", "ESTADO ACTIVO", "ROL"};
            modeloTablaResultado.setColumnIdentifiers(atributosTablaUsuarios);
        }
        else if(menuPrincipal.getBtnRecursos().isSelected()){
            String[] atributosTablaRecursos = {"CODIGO", "ISBN", "TÍTULO", "AUTOR", "GÉNERO", "ÁREA", "DISPONIBLE"};
            modeloTablaResultado.setColumnIdentifiers(atributosTablaRecursos);
        }
        else if(menuPrincipal.getBtnPrestamos().isSelected()){
            String[] atributosTablaRPrestamos = {"ID RECURSO", "ID USUARIO", "NOMBRE", "ESTADO ACTIVO", "ROL", "CODIGO", "ISBN", "TÍTULO", "AUTOR", "GÉNERO", "ÁREA", "DISPONIBLE"};
            modeloTablaResultado.setColumnIdentifiers(atributosTablaRPrestamos);
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
                
                menuPrincipal.getBtnActualizar().setText("EDITAR");
                
                menuPrincipal.getBtnRadioBusqueda1().setText("Nombre y Código");
                menuPrincipal.getBtnRadioBusqueda2().setText("Nombre y Rol");
                menuPrincipal.getBtnAgregar().setEnabled(true);
                menuPrincipal.getBtnEliminar().setEnabled(true);
                
                actualizarJListaUsuarios();
            }
            else if(e.getSource() == menuPrincipal.getBtnRecursos()) {
                menuPrincipal.getBtnUsuarios().setSelected(false);
                menuPrincipal.getBtnPrestamos().setSelected(false);
                menuPrincipal.getBtnUsuarios().setEnabled(true);
                menuPrincipal.getBtnPrestamos().setEnabled(true);
                menuPrincipal.getBtnRecursos().setEnabled(false);
                
                menuPrincipal.getBtnActualizar().setText("EDITAR");
                
                actualizarJListaRecursos();
                
                menuPrincipal.getBtnRadioBusqueda1().setText("Título y Autor");
                menuPrincipal.getBtnRadioBusqueda2().setText("Título y Género");
                menuPrincipal.getBtnAgregar().setEnabled(true);
                menuPrincipal.getBtnEliminar().setEnabled(true);
            }
            else if(e.getSource() == menuPrincipal.getBtnPrestamos()) {
                menuPrincipal.getBtnRecursos().setSelected(false);
                menuPrincipal.getBtnUsuarios().setSelected(false);
                menuPrincipal.getBtnRecursos().setEnabled(true);
                menuPrincipal.getBtnUsuarios().setEnabled(true);
                menuPrincipal.getBtnPrestamos().setEnabled(false);
                
                menuPrincipal.getBtnActualizar().setText("ADMINISTRAR");
                
                menuPrincipal.getBtnRadioBusqueda1().setText("Nombre y Fecha");
                menuPrincipal.getBtnRadioBusqueda2().setText("Título y Autor");
                menuPrincipal.getBtnAgregar().setEnabled(false);
                menuPrincipal.getBtnEliminar().setEnabled(false);
                
                actualizarJListaPrestamos();
            }
            else if (e.getSource() == menuPrincipal.getBtnEliminar()) { // ELIMINAR
                if (menuPrincipal.getBtnUsuarios().isSelected()) {
//                    index = menuPrincipal.getJListIndex();
                    index = jTable.getSelectedRow();
                    System.out.println(index);
                    
                    if(index != -1) {
                        Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(index);

                        usuarioDAO.deleteUsuario((entry.getKey()));
                        listaMapUsuarios.remove(index);
//                        modeloLista.remove(index);
                        modeloTabla.removeRow(index);

//                        menuPrincipal.getJList().setModel(modeloLista);
                        jTable.setModel(modeloTabla);    
                    } else {
                        mensajeTemporal("Elija el usuario que desea eliminar", "Error", 1150);
                    }
                }
                else if(menuPrincipal.getBtnRecursos().isSelected()) {
//                    index = menuPrincipal.getJListIndex();
                    index = jTable.getSelectedRow();
                    System.out.println(index);
                    if(index != -1) {
                        Map.Entry<String, Recurso> entry = listaMapRecursos.get(index);

                        recursoDAO.deleteRecurso((entry.getKey()));
                        listaMapRecursos.remove(index);
//                        modeloLista.remove(index); 
                        modeloTabla.removeRow(index);

//                        menuPrincipal.getJList().setModel(modeloLista);
                        jTable.setModel(modeloTabla);
                    } else {
                        mensajeTemporal("Elija el recurso que desea eliminar", "Error", 1150);
                    }
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
            }
            else if (e.getSource() == menuPrincipal.getBtnActualizar()) { // ACTUALIZAR
                if (menuPrincipal.getBtnUsuarios().isSelected()) {
//                    index = menuPrincipal.getJListIndex();
                    index = jTable.getSelectedRow();
                    if(index != -1) {
                        Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(index);
                        editarUsuario.setVisible(true);
                        editarUsuarioController.abrirVista(entry.getKey());
                    } else {
                        mensajeTemporal("Elija el usuario que desea editar", "Error", 1150);
                    }
                }
                else if(menuPrincipal.getBtnRecursos().isSelected()) {
//                    index = menuPrincipal.getJListIndex();
                    index = jTable.getSelectedRow();
                    System.out.println("");
                    if(index != -1) {
                        Map.Entry<String, Recurso> entry = listaMapRecursos.get(index);
                        editarRecurso.setVisible(true);
                        editarRecursoController.abrirVista(entry.getKey());
                    } else {
                        mensajeTemporal("Elija el recurso que desea editar", "Error", 1150);
                    }
                }
                else if(menuPrincipal.getBtnPrestamos().isSelected()) {
                    administrarPrestamos.setVisible(true);
                    administrarPrestamosController.abrirVista();
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
                modeloTablaResultado.setRowCount(0);
                establecerIdentificadoresColumnasResultado();
                strBusqueda1 = menuPrincipal.getTxtBuscar().getText();
                if(!"".equals(strBusqueda1)) {
                    String strBusqueda1Clean = strBusqueda1.replaceAll("\\s", "");
                    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                        for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                            if(strBusqueda1Clean.equalsIgnoreCase((String)modeloTabla.getValueAt(i,j))) {
                                Object[] rowData = new Object[modeloTabla.getColumnCount()];
                                for (int columnIndex = 0; columnIndex < modeloTabla.getColumnCount(); columnIndex++) {
                                    rowData[columnIndex] = modeloTabla.getValueAt(i, columnIndex);
                                }
                                modeloTablaResultado.addRow(rowData);
                                break;
                            }
                            else {
                                StringTokenizer elementoTemporal = new StringTokenizer((String)modeloTabla.getValueAt(i,j)," ");
                                while (elementoTemporal.hasMoreTokens()) {
                                    String subToken = elementoTemporal.nextToken();
                                    if(strBusqueda1.equalsIgnoreCase(subToken)) {
                                        Object[] rowData = new Object[modeloTabla.getColumnCount()];
                                        for (int columnIndex = 0; columnIndex < modeloTabla.getColumnCount(); columnIndex++) {
                                            rowData[columnIndex] = modeloTabla.getValueAt(i, columnIndex);
                                        }
                                        modeloTablaResultado.addRow(rowData);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    jTable.setModel(modeloTablaResultado);
                }
                else {
                    jTable.setModel(modeloTabla); //CAMBIAR
                }
            }
            else if(e.getSource() == menuPrincipal.getBtnPopConfirmar()) {
                modeloTablaResultado.setRowCount(0);
                establecerIdentificadoresColumnasResultado();
                strBusqueda1 = menuPrincipal.getTxtBuscar().getText();
                strBusqueda2 = menuPrincipal.getTxtBusqueda2().getText();
                if(!"".equals(strBusqueda1)) {
                    String strBusqueda1Clean = strBusqueda1.replaceAll("\\s", "");
                    String strBusqueda2Clean = strBusqueda2.replaceAll("\\s", "");
                    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                        for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                            String rawString = (String)modeloTabla.getValueAt(i,j);
                            if(strBusqueda1Clean.equalsIgnoreCase(rawString.replaceAll("\\s", "")) || strBusqueda2Clean.equalsIgnoreCase(rawString.replaceAll("\\s", ""))) { // rawString.replaceAll("\\s", "") es string de solo caracteres.
                                Object[] rowData = new Object[modeloTabla.getColumnCount()];
                                for (int columnIndex = 0; columnIndex < modeloTabla.getColumnCount(); columnIndex++) {
                                    rowData[columnIndex] = modeloTabla.getValueAt(i, columnIndex);
                                }
                                modeloTablaResultado.addRow(rowData);
                                break;
                            }
                            else {
                                StringTokenizer elementoTemporal = new StringTokenizer((String)modeloTabla.getValueAt(i,j)," ");
                                while (elementoTemporal.hasMoreTokens()) {
                                    String subToken = elementoTemporal.nextToken();
                                    if(strBusqueda1.equalsIgnoreCase(subToken) || strBusqueda2Clean.equalsIgnoreCase(subToken)) {
                                        Object[] rowData = new Object[modeloTabla.getColumnCount()];
                                        for (int columnIndex = 0; columnIndex < modeloTabla.getColumnCount(); columnIndex++) {
                                            rowData[columnIndex] = modeloTabla.getValueAt(i, columnIndex);
                                        }
                                        modeloTablaResultado.addRow(rowData);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    jTable.setModel(modeloTablaResultado);
                }
                else {
                    jTable.setModel(modeloTabla); //CAMBIAR
                }
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
            else if(e.getSource() == administrarPrestamos.getBtnConfirmar()) {
                try {
                    Thread.sleep(200);
                    mapaPrestamos = prestamoDAO.getPrestamos();
                    actualizarJListaPrestamos();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }
}
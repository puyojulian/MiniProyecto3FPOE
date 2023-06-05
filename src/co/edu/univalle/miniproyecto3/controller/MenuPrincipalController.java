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
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
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
    private javax.swing.JTable jTable;
    private List<Map.Entry<Integer, Usuario>> listaMapUsuarios;
    private List<Map.Entry<String, Recurso>> listaMapRecursos;
    private List<Map.Entry<Integer, Prestamo>> listaMapPrestamos;
    private String strBusqueda1;
    private String strBusqueda2;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloTablaResultado;
    private DefaultListModel<String> modeloListaResultado;
    private List listaTemporal;
    private List listaParametros;
    private List listaFechas;
    private int index;
    private Object[] rowData1;
    private Object[] rowData2;
    private List keyList;
    private String fechaHoyFormateada;
    
    public MenuPrincipalController(MenuPrincipal menuPrincipal) throws IOException {
        this.usuarioDAO = new UsuarioDAO();
        this.recursoDAO = new RecursoDAO();
        this.prestamoDAO = new PrestamoDAO();
        this.menuPrincipal = menuPrincipal;
        this.jTable = menuPrincipal.getJTable();
        this.modeloListaResultado = new DefaultListModel<>();
        this.modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.modeloTablaResultado = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.listaTemporal = new ArrayList();
        this.listaParametros = new ArrayList();
        this.listaFechas = new ArrayList();
        this.keyList = new ArrayList();
        
        HandlerActions listener = new HandlerActions();
        KeyEventHandler keyListener = new KeyEventHandler();
        
        menuPrincipal.getTxtBusqueda1().addKeyListener(keyListener);
        menuPrincipal.getTxtBusqueda2().addKeyListener(keyListener);
        menuPrincipal.getBtnPopConfirmar().addKeyListener(keyListener);
        menuPrincipal.getTxtBuscar().addKeyListener(keyListener);
        menuPrincipal.getBtnBuscar().addKeyListener(keyListener);
        
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
        menuPrincipal.addBtnPopConfirmar(listener);
        menuPrincipal.addBtnPopCerrar(listener);
        
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fechaHoyFormateada = fechaHoy.format(formateador);
        System.out.println(fechaHoyFormateada);
        
        usuariosActuales();
        recursosActuales();
        mapaUsuarios = usuarioDAO.getUsuarios();
        mapaRecursos = recursoDAO.getRecursos();
        mapaPrestamos = prestamoDAO.getPrestamos();
        prestamosActuales();
        
        menuPrincipal.getBtnRecursos().setSelected(true);
        actualizarJListaRecursos();
    }

    private void usuariosActuales(){
        FileReader frUsuarios = null;
        listaParametros.clear();
        BufferedReader brUsuarios = null;
        try {
            File archivo = new File("usuarios.txt");
            frUsuarios = new FileReader(archivo);
            brUsuarios = new BufferedReader(frUsuarios);
            String linea;
            while((linea = brUsuarios.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(linea,",");
                while(tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    listaParametros.add(token);
                }
                if(listaParametros.size() == 2) {
                    usuarioDAO.addUsuario(new Usuario((String)listaParametros.get(0), (String)listaParametros.get(1)));
                }
                listaParametros.clear();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (brUsuarios != null) {
                    brUsuarios.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        usuarioDAO.addUsuario(new Usuario("Sebastian", "Estudiante"));
//        usuarioDAO.addUsuario(new Usuario("Carlos", "Estudiante"));
//        usuarioDAO.addUsuario(new Usuario("Julian", "Docente"));
    }
    
    private void recursosActuales(){
        FileReader frRecursos = null;
        listaParametros.clear();
        BufferedReader brRecursos = null;
        try {
            File archivo = new File("recursos.txt");
            frRecursos = new FileReader(archivo);
            brRecursos = new BufferedReader(frRecursos);
            String linea;
            while((linea = brRecursos.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(linea,",");
                while(tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    listaParametros.add(token);
                }
                if(listaParametros.size() == 5) {
                    recursoDAO.addRecurso(new Recurso((String)listaParametros.get(0), 
                            (String)listaParametros.get(1), 
                            (String)listaParametros.get(2), 
                            (String)listaParametros.get(3), 
                            (String)listaParametros.get(4)));
                }
                listaParametros.clear();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (brRecursos != null) {
                    brRecursos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
//        recursoDAO.addRecurso(new Recurso("9780141182561", "To Kill a Mockingbird", "Harper Lee", "Fiction", "Literature"));
//        recursoDAO.addRecurso(new Recurso("9780061120084", "1984", "George Orwell", "Fiction", "Science Fiction"));
//        recursoDAO.addRecurso(new Recurso("9780141439518", "Pride and Prejudice", "Jane Austen", "Fiction", "Classic Literature"));
//        recursoDAO.addRecurso(new Recurso("9780060256654", "Where the Wild Things Are", "Maurice Sendak", "Children's Fiction", "Picture Books"));
//        recursoDAO.addRecurso(new Recurso("9780743273565", "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", "Classic Literature"));
//        recursoDAO.addRecurso(new Recurso("9780307474278", "The Hunger Games", "Suzanne Collins", "Fiction", "Young Adult"));
//        recursoDAO.addRecurso(new Recurso("9780143105954", "The Catcher in the Rye", "J.D. Salinger", "Fiction", "Literature"));
//        recursoDAO.addRecurso(new Recurso("9780064404990", "Bridge to Terabithia", "Katherine Paterson", "Children's Fiction", "Fantasy"));
//        recursoDAO.addRecurso(new Recurso("9780060935467", "Freakonomics: A Rogue Economist Explores the Hidden Side of Everything", "Steven D. Levitt and Stephen J. Dubner", "Non-Fiction", "Economics"));
    }
    
    public void prestamosActuales() {
        Collection<Usuario> usuarios = mapaUsuarios.values();
        Set<Map.Entry<String, Recurso>> entrySetMapaR = mapaRecursos.entrySet();
        
        FileReader frFechas = null;
        listaFechas.clear();
        BufferedReader brFechas = null;
        try {
            File archivo = new File("fechas.txt");
            frFechas = new FileReader(archivo);
            brFechas = new BufferedReader(frFechas);
            String linea;
            while((linea = brFechas.readLine()) != null) {
                listaFechas.add(linea.replaceAll("\\s", ""));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (brFechas != null) {
                    brFechas.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int count = 1;

        for (Map.Entry<String, Recurso> entryR : entrySetMapaR) {
            Recurso valueR = entryR.getValue();

            if (count > 40) { // Número de usuarios con préstamo inicial. No debe sobrepasar la cantidad de fechas de préstamo iniciales.
                break;
            }

            Usuario valueU = usuarios.toArray(new Usuario[0])[count - 1];
            if (valueR.isDisponible() == true){
                valueR.setDisponible(false);
                System.out.println(fechaHoyFormateada);
                prestamoDAO.addPrestamo(new Prestamo(valueU, valueR, (String) listaFechas.get(count - 1)));
                System.out.println(fechaHoyFormateada);
            }

            count++;
        }
    }
    
    public void actualizarJListaUsuarios() {
        if(mapaUsuarios.size() > 0) {
            Set<Map.Entry<Integer, Usuario>> entrySetMapa = mapaUsuarios.entrySet();

            listaTemporal.clear();
            modeloTabla.setRowCount(0);

            listaMapUsuarios = new ArrayList<>(mapaUsuarios.entrySet());
            
            establecerIdentificadoresColumnas(modeloTabla);

            for (Map.Entry<Integer, Usuario> entry : entrySetMapa){
                Usuario value = entry.getValue();
                String item = "" + value;
                StringTokenizer tokenizer = new StringTokenizer(item,",");
                while(tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    listaTemporal.add(token);
                }
                
                modeloTabla.addRow(listaTemporal.toArray());
                listaTemporal.clear();
            }
            jTable.setModel(modeloTabla);
        }
        else {
            modeloTabla.setRowCount(0);
            jTable.setModel(modeloTabla);
        }
    }
    
    public void actualizarJListaRecursos() {
        if(mapaRecursos.size() > 0) {
            Set<Map.Entry<String, Recurso>> entrySetMapa = mapaRecursos.entrySet();

            listaTemporal.clear();
            modeloTabla.setRowCount(0);

            listaMapRecursos = new ArrayList<>(mapaRecursos.entrySet());
            
            establecerIdentificadoresColumnas(modeloTabla);

            for (Map.Entry<String, Recurso> entry : entrySetMapa){
                Recurso value = entry.getValue();
                String item = "" + value;
                StringTokenizer tokenizer = new StringTokenizer(item,",");
                while(tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    listaTemporal.add(token);
                }
                
                modeloTabla.addRow(listaTemporal.toArray());
                listaTemporal.clear();
            }
            jTable.setModel(modeloTabla);
        }
        else {
            modeloTabla.setRowCount(0);
            jTable.setModel(modeloTabla);
        }
    }
    
    public void actualizarJListaPrestamos() {
        if(mapaPrestamos.size() > 0) {
            Set<Map.Entry<Integer, Prestamo>> entrySetMapa = mapaPrestamos.entrySet();

            listaTemporal.clear();
            modeloTabla.setRowCount(0);

            listaMapPrestamos = new ArrayList<>(mapaPrestamos.entrySet());
            
            establecerIdentificadoresColumnas(modeloTabla);

            for (Map.Entry<Integer, Prestamo> entry : entrySetMapa){
                Prestamo value = entry.getValue();
                String item = "" + value;
                StringTokenizer tokenizer = new StringTokenizer(item,",");
                while(tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    listaTemporal.add(token);
                }
                
                modeloTabla.addRow(listaTemporal.toArray());
                listaTemporal.clear();
            }
            jTable.setModel(modeloTabla);
        }
        else {
            modeloTabla.setRowCount(0);
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
    
    public void establecerIdentificadoresColumnas(DefaultTableModel modelo) {
        if(menuPrincipal.getBtnUsuarios().isSelected()) {
            String[] atributosTablaUsuarios = {"ID", "NOMBRE", "ESTADO ACTIVO", "ROL"};
            modelo.setColumnIdentifiers(atributosTablaUsuarios);
        }
        else if(menuPrincipal.getBtnRecursos().isSelected()){
            String[] atributosTablaRecursos = {"CÓDIGO", "ISBN", "TÍTULO", "AUTOR", "GÉNERO", "ÁREA", "DISPONIBLE"};
            modelo.setColumnIdentifiers(atributosTablaRecursos);
        }
        else if(menuPrincipal.getBtnPrestamos().isSelected()){
            String[] atributosTablaRPrestamos = {"CÓDIGO", "USUARIO", "ROL", "TÍTULO", "ESTADO", "FECHA REA.", "FECHA DEV."};
            modelo.setColumnIdentifiers(atributosTablaRPrestamos);
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
                
                menuPrincipal.getBtnAgregar().setEnabled(false);
                menuPrincipal.getBtnEliminar().setEnabled(false);
                
                actualizarJListaPrestamos();
            }
            else if (e.getSource() == menuPrincipal.getBtnEliminar()) { // ELIMINAR
                if (menuPrincipal.getBtnUsuarios().isSelected()) {
                    index = jTable.getSelectedRow();
                    System.out.println(index);
                    
                    if(index != -1) {
                        Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(index);

                        usuarioDAO.deleteUsuario((entry.getKey()));
                        listaMapUsuarios.remove(index);
                        modeloTabla.removeRow(index);

                        jTable.setModel(modeloTabla);    
                    } else {
                        mensajeTemporal("Elija el usuario que desea eliminar", "Error", 1150);
                    }
                }
                else if(menuPrincipal.getBtnRecursos().isSelected()) {
                    index = jTable.getSelectedRow();
                    System.out.println(index);
                    if(index != -1) {
                        Map.Entry<String, Recurso> entry = listaMapRecursos.get(index);

                        recursoDAO.deleteRecurso((entry.getKey()));
                        listaMapRecursos.remove(index);
                        modeloTabla.removeRow(index);

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
                    index = jTable.getSelectedRow();
                    if(index != -1) {
                        if(jTable.getRowCount() < listaMapUsuarios.size()) {
                            editarUsuario.setVisible(true);
                            editarUsuarioController.abrirVista((int) keyList.get(index));
                        } else {
                            Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(index);
                            editarUsuario.setVisible(true);
                            editarUsuarioController.abrirVista(entry.getKey());
                        }
                    } 
                    else {
                        mensajeTemporal("Elija el usuario que desea editar", "Error", 1150);
                    }
                }
                else if(menuPrincipal.getBtnRecursos().isSelected()) {
                    index = jTable.getSelectedRow(); 
                    if(index != -1) {
                        if(jTable.getRowCount() < listaMapRecursos.size()) {
                            editarRecurso.setVisible(true);
                            editarRecursoController.abrirVista((String) keyList.get(index));
                        }
                        else {
                            Map.Entry<String, Recurso> entry = listaMapRecursos.get(index);
                            editarRecurso.setVisible(true);
                            editarRecursoController.abrirVista(entry.getKey());
                        }
                    } 
                    else {
                        mensajeTemporal("Elija el recurso que desea editar", "Error", 1150);
                    }
                }
                else if(menuPrincipal.getBtnPrestamos().isSelected()) {
                    administrarPrestamos.setVisible(true);
                    administrarPrestamosController.abrirVista();
                }
            }
            else if(e.getSource() == menuPrincipal.getBtnBuscar()) { //BUSQUEDA PRINCIPAL
                keyList.clear();
                modeloTablaResultado.setRowCount(0);
                establecerIdentificadoresColumnas(modeloTablaResultado);
                strBusqueda1 = menuPrincipal.getTxtBuscar().getText();
                if(!"".equals(strBusqueda1)) {
                    String strBusqueda1Clean = strBusqueda1.replaceAll("\\s", "");
                    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                        for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                            String rawString = (String)modeloTabla.getValueAt(i,j);   
                            if(strBusqueda1Clean.equalsIgnoreCase(rawString.replaceAll("\\s", "").substring(0,Math.min(rawString.replaceAll("\\s", "").length(), strBusqueda1Clean.length())))) {
                                Object[] rowData = new Object[modeloTabla.getColumnCount()];
                                for (int columnIndex = 0; columnIndex < modeloTabla.getColumnCount(); columnIndex++) {
                                    rowData[columnIndex] = modeloTabla.getValueAt(i, columnIndex);
                                }
                                if(menuPrincipal.getBtnUsuarios().isSelected()) {
                                    Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(i);
                                    keyList.add(entry.getKey());
                                }
                                else if(menuPrincipal.getBtnRecursos().isSelected()) {
                                    Map.Entry<String, Recurso> entry = listaMapRecursos.get(i);
                                    keyList.add(entry.getKey());
                                }
                                else if(menuPrincipal.getBtnPrestamos().isSelected()) {
                                    Map.Entry<Integer, Prestamo> entry = listaMapPrestamos.get(i);
                                    keyList.add(entry.getKey());
                                }
                                modeloTablaResultado.addRow(rowData);
                                break;
                            }
                            else {
                                StringTokenizer elementoTemporal = new StringTokenizer((String)modeloTabla.getValueAt(i,j)," ");
                                while (elementoTemporal.hasMoreTokens()) {
                                    String subToken = elementoTemporal.nextToken();
                                    if(strBusqueda1Clean.equalsIgnoreCase(subToken.substring(0,Math.min(subToken.length(), strBusqueda1Clean.length())))) {
                                        Object[] rowData = new Object[modeloTabla.getColumnCount()];
                                        for (int columnIndex = 0; columnIndex < modeloTabla.getColumnCount(); columnIndex++) {
                                            rowData[columnIndex] = modeloTabla.getValueAt(i, columnIndex);
                                        }
                                        if(menuPrincipal.getBtnUsuarios().isSelected()) {
                                            Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(i);
                                            keyList.add(entry.getKey());
                                        }
                                        else if(menuPrincipal.getBtnRecursos().isSelected()) {
                                            Map.Entry<String, Recurso> entry = listaMapRecursos.get(i);
                                            keyList.add(entry.getKey());
                                        }
                                        else if(menuPrincipal.getBtnPrestamos().isSelected()) {
                                            Map.Entry<Integer, Prestamo> entry = listaMapPrestamos.get(i);
                                            keyList.add(entry.getKey());
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
            else if(e.getSource() == menuPrincipal.getBtnPopConfirmar()) { //BUSQUEDA AVANZADA
                keyList.clear();
                modeloTablaResultado.setRowCount(0);
                establecerIdentificadoresColumnas(modeloTablaResultado);
                strBusqueda1 = menuPrincipal.getTxtBusqueda1().getText();
                strBusqueda2 = menuPrincipal.getTxtBusqueda2().getText();
                if(!"".equals(strBusqueda1) && !"".equals(strBusqueda2)) {
                    String strBusqueda1Clean = strBusqueda1.replaceAll("\\s", "");
                    String strBusqueda2Clean = strBusqueda2.replaceAll("\\s", "");
                    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                        for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                            String rawString = (String)modeloTabla.getValueAt(i,j);
                            if(strBusqueda1Clean.equalsIgnoreCase(rawString.replaceAll("\\s", "").substring(0,Math.min(rawString.replaceAll("\\s", "").length(), strBusqueda1Clean.length())))) { // rawString.replaceAll("\\s", "") es string de solo caracteres.
                                rowData1= new Object[modeloTabla.getColumnCount()];
                                for (int columnIndex = 0; columnIndex < modeloTabla.getColumnCount(); columnIndex++) {
                                    rowData1[columnIndex] = modeloTabla.getValueAt(i, columnIndex);
                                }
                                break;
                            }
                            else {
                                StringTokenizer elementoTemporal = new StringTokenizer((String)modeloTabla.getValueAt(i,j)," ");
                                while (elementoTemporal.hasMoreTokens()) {
                                    String subToken = elementoTemporal.nextToken();
                                    if(strBusqueda1Clean.equalsIgnoreCase(subToken.substring(0,Math.min(subToken.length(), strBusqueda1Clean.length())))) {
                                        rowData1 = new Object[modeloTabla.getColumnCount()];
                                        for (int columnIndex = 0; columnIndex < modeloTabla.getColumnCount(); columnIndex++) {
                                            rowData1[columnIndex] = modeloTabla.getValueAt(i, columnIndex);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                            String rawString = (String)modeloTabla.getValueAt(i,j);
                            if(strBusqueda2Clean.equalsIgnoreCase(rawString.replaceAll("\\s", "").substring(0,Math.min(rawString.replaceAll("\\s", "").length(), strBusqueda2Clean.length())))) { // rawString.replaceAll("\\s", "") es string de solo caracteres.
                                rowData2 = new Object[modeloTabla.getColumnCount()];
                                for (int columnIndex = 0; columnIndex < modeloTabla.getColumnCount(); columnIndex++) {
                                    rowData2[columnIndex] = modeloTabla.getValueAt(i, columnIndex);
                                }
                                if(Arrays.equals(rowData1, rowData2)) {
                                    if(menuPrincipal.getBtnUsuarios().isSelected()) {
                                        Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(i);
                                        keyList.add(entry.getKey());
                                    }
                                    else if(menuPrincipal.getBtnRecursos().isSelected()) {
                                        Map.Entry<String, Recurso> entry = listaMapRecursos.get(i);
                                        keyList.add(entry.getKey());
                                    }
                                    else if(menuPrincipal.getBtnPrestamos().isSelected()) {
                                        Map.Entry<Integer, Prestamo> entry = listaMapPrestamos.get(i);
                                        keyList.add(entry.getKey());
                                    }
                                    modeloTablaResultado.addRow(rowData2);  
                                }
                                break;
                            }
                            else {
                                StringTokenizer elementoTemporal = new StringTokenizer((String)modeloTabla.getValueAt(i,j)," ");
                                while (elementoTemporal.hasMoreTokens()) {
                                    String subToken = elementoTemporal.nextToken();
                                    if(strBusqueda2Clean.equalsIgnoreCase(subToken.substring(0,Math.min(subToken.length(), strBusqueda2Clean.length())))) {
                                        rowData2 = new Object[modeloTabla.getColumnCount()];
                                        for (int columnIndex = 0; columnIndex < modeloTabla.getColumnCount(); columnIndex++) {
                                            rowData2[columnIndex] = modeloTabla.getValueAt(i, columnIndex);
                                        }
                                        if(Arrays.equals(rowData1, rowData2)) {
                                            if(menuPrincipal.getBtnUsuarios().isSelected()) {
                                                Map.Entry<Integer, Usuario> entry = listaMapUsuarios.get(i);
                                                keyList.add(entry.getKey());
                                            }
                                            else if(menuPrincipal.getBtnRecursos().isSelected()) {
                                                Map.Entry<String, Recurso> entry = listaMapRecursos.get(i);
                                                keyList.add(entry.getKey());
                                            }
                                            else if(menuPrincipal.getBtnPrestamos().isSelected()) {
                                                Map.Entry<Integer, Prestamo> entry = listaMapPrestamos.get(i);
                                                keyList.add(entry.getKey());
                                            }
                                            modeloTablaResultado.addRow(rowData2);  
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    jTable.setModel(modeloTablaResultado);
                    menuPrincipal.getJpBusquedaAvanzada().setVisible(false);
                    menuPrincipal.getTxtBusqueda1().setText("");
                    menuPrincipal.getTxtBusqueda2().setText("");
                }
                else {
                    jTable.setModel(modeloTabla);
                    mensajeTemporal("Ningun campo puede estar vacío.", "Error", 1150);
                }
            }
            else if(e.getSource() == menuPrincipal.getBtnPopCerrar()) {
                menuPrincipal.getJpBusquedaAvanzada().setVisible(false);
                menuPrincipal.getTxtBusqueda1().setText("");
                menuPrincipal.getTxtBusqueda2().setText("");
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
    
    class KeyEventHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(menuPrincipal.getTxtBuscar().hasFocus()) {
                    try {
                        Robot robot = new Robot();
                        // Simulate a key press
                        menuPrincipal.getBtnBuscar().requestFocus();
                        robot.keyPress(KeyEvent.VK_SPACE);
                        robot.keyRelease(KeyEvent.VK_SPACE);
                    } catch (AWTException ex) {
                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(menuPrincipal.getTxtBusqueda1().hasFocus() || menuPrincipal.getTxtBusqueda2().hasFocus()) {
                    try {
                        Robot robot = new Robot();
                        // Simulate a key press
                        menuPrincipal.getBtnPopConfirmar().requestFocus();
                        robot.keyPress(KeyEvent.VK_SPACE);
                        robot.keyRelease(KeyEvent.VK_SPACE);
                    } catch (AWTException ex) {
                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // Not used.
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Not used.
        }
    }
}

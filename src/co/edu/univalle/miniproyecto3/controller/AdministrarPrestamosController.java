package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Prestamo;
import co.edu.univalle.miniproyecto3.model.Usuario;
import co.edu.univalle.miniproyecto3.repository.PrestamoDAO;
import co.edu.univalle.miniproyecto3.repository.RecursoDAO;
import co.edu.univalle.miniproyecto3.repository.UsuarioDAO;
import co.edu.univalle.miniproyecto3.vista.AdministrarPrestamos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AdministrarPrestamosController {
    private AdministrarPrestamos administrarPrestamos;
    private PrestamoDAO prestamoDAO;
    private RecursoDAO recursoDAO;
    private UsuarioDAO usuarioDAO;
    private int indexU;
    private int indexR;
    private int tabbedIndex;
    private List listaRecursos;
    private List listaUsuarios;
    private int recursosDisponibles;
    private String fechaHoyFormateada;

    public AdministrarPrestamosController(AdministrarPrestamos vista, PrestamoDAO prestamoDao, RecursoDAO recursoDao, UsuarioDAO usuarioDao) {
        this.administrarPrestamos = vista;
        this.prestamoDAO = prestamoDao;
        this.recursoDAO = recursoDao;
        this.usuarioDAO = usuarioDao;
        listaRecursos = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        
        HandlerActions listener = new HandlerActions();
        
        administrarPrestamos.addBtnConfirmar(listener);
        administrarPrestamos.addBtnVolver(listener);
        administrarPrestamos.addjComboDevolucionRecursos(listener);
        administrarPrestamos.addjComboDevolucionUsuarios(listener);
        administrarPrestamos.addjComboPrestamoRecursos(listener);
        administrarPrestamos.addjComboPrestamoUsuarios(listener);
        administrarPrestamos.addjTabbedPanePrestamo(listener);
        
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fechaHoyFormateada = fechaHoy.format(formateador);
        System.out.println(fechaHoyFormateada);
        
    }
    
    public void abrirVista() {
        indexU = 0;
        indexR = 0;
        tabbedIndex = 0;
        administrarPrestamos.getjTabbedPane1().setSelectedIndex(tabbedIndex);
        mostrarItemsDevolucion();
    }
    
    public void mostrarItemsDevolucion() {
        administrarPrestamos.getjComboDevolucionUsuarios().removeAllItems();
        listaUsuarios.clear();

        if(!prestamoDAO.getPrestamos().isEmpty()) {
            for (Map.Entry<Integer, Usuario> entryUsuario : usuarioDAO.getUsuarios().entrySet()) {
                for (Map.Entry<Integer, Prestamo> entryPrestamo : prestamoDAO.getPrestamos().entrySet()) {
                    if(entryUsuario.getValue().isEstadoActivo() && entryPrestamo.getValue().getEstado().equals(entryPrestamo.getValue().getEstados()[0]) && entryUsuario.getValue().getId() == entryPrestamo.getValue().getUsuario().getId()) {
                        administrarPrestamos.getjComboDevolucionUsuarios().addItem(entryUsuario.getValue().getNombre());
                        listaUsuarios.add(entryUsuario.getValue().getId());
                        break;
                    }
                }
            }
        }
        
        if(!listaUsuarios.isEmpty()) {
            mostrarRecursosDevolucion((int) listaUsuarios.get(0));
        }
        else {
            administrarPrestamos.getjComboDevolucionRecursos().removeAllItems();
        }
    }
    
    public void mostrarRecursosDevolucion(Integer index) {
        administrarPrestamos.getjComboDevolucionRecursos().removeAllItems();
        listaRecursos.clear();
        
        if(!prestamoDAO.getPrestamos().isEmpty()) {
            for (Map.Entry<Integer, Prestamo> entry : prestamoDAO.getPrestamos().entrySet()) {
                if (entry.getValue().getUsuario().getId() == index && !entry.getValue().getRecurso().isDisponible()) {
                    administrarPrestamos.getjComboDevolucionRecursos().addItem(entry.getValue().getRecurso().getNombre());
                    listaRecursos.add(entry.getValue().getRecurso().getCodigoRecurso());
                }
            }
        }
        else {
            administrarPrestamos.getjComboDevolucionRecursos().removeAllItems();
        }
    }
    
    public void mostrarItemsPrestamo() {
        administrarPrestamos.getjComboPrestamoUsuarios().removeAllItems();
        administrarPrestamos.getjComboPrestamoRecursos().removeAllItems();
        listaUsuarios.clear();
        listaRecursos.clear();
        
        if(!usuarioDAO.getUsuarios().isEmpty()) {
            usuarioDAO.getUsuarios().forEach((clave, valor) -> {
                if (valor.isEstadoActivo()){
                    administrarPrestamos.getjComboPrestamoUsuarios().addItem(valor.getNombre());
                    listaUsuarios.add(valor.getId());
                }
            });
        }
        
        recursosDisponibles = 0;
        if(!recursoDAO.getRecursos().isEmpty()) {
            recursoDAO.getRecursos().forEach((clave, valor) -> {
                if (valor.isDisponible()){
                    administrarPrestamos.getjComboPrestamoRecursos().addItem(valor.getNombre());
                    listaRecursos.add(clave);
                    recursosDisponibles++;
                }
            });
        }
    }
    
    public Integer verificarLlaveDevolucion() {
        for (Map.Entry<Integer, Prestamo> entry : prestamoDAO.getPrestamos().entrySet()) {
            if (entry.getValue().getUsuario().getId() == (int) listaUsuarios.get(indexU) && entry.getValue().getRecurso().getCodigoRecurso() == (int) listaRecursos.get(indexR)) {
                return entry.getKey();
            }
        }    
        return -1;
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
    
    class HandlerActions implements ActionListener, ChangeListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == administrarPrestamos.getBtnConfirmar()) {
                if(tabbedIndex == 0){
                    if(!listaUsuarios.isEmpty()) {
                        prestamoDAO.deletePrestamo(verificarLlaveDevolucion());
                        mostrarItemsDevolucion();
                        indexU = 0;
                        indexR = 0;
                        mensajeTemporal("Prestamo cerrado satisfactoriamente", "Aviso",1150);
                    } else {
                        mensajeTemporal("No hay prestamos para eliminar", "Aviso",1150);
                    }
                }
                else if(tabbedIndex == 1){
                    if(recursosDisponibles > 0) {
                        System.out.println(fechaHoyFormateada);
                        prestamoDAO.addPrestamo(new Prestamo(usuarioDAO.getUsuario((int)listaUsuarios.get(indexU)), recursoDAO.getRecurso((String)listaRecursos.get(indexR)), fechaHoyFormateada));
                        mostrarItemsPrestamo();
                        indexU = 0;
                        indexR = 0;
                        mensajeTemporal("Prestamo agregado satisfactoriamente", "Aviso",1150);
                    } else {
                        mensajeTemporal("No hay recursos disponibles para prestar", "Aviso",1150);
                    }
                }
            }
            else if (e.getSource() == administrarPrestamos.getBtnVolver()) {
                administrarPrestamos.setVisible(false);
            }
            else if (e.getSource() == administrarPrestamos.getjComboDevolucionUsuarios()) {
                indexU = administrarPrestamos.getjComboDevolucionUsuarios().getSelectedIndex();
                if(indexU != -1 && !listaUsuarios.isEmpty()) {
                    mostrarRecursosDevolucion((int)listaUsuarios.get(indexU));
                }
            }
            else if (e.getSource() == administrarPrestamos.getjComboDevolucionRecursos()) {
                indexR = administrarPrestamos.getjComboDevolucionRecursos().getSelectedIndex();
            }
            else if (e.getSource() == administrarPrestamos.getjComboPrestamoUsuarios()) {
                indexU = administrarPrestamos.getjComboPrestamoUsuarios().getSelectedIndex();
            }
            else if (e.getSource() == administrarPrestamos.getjComboPrestamoRecursos()) {
                indexR = administrarPrestamos.getjComboPrestamoRecursos().getSelectedIndex();
            }
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == administrarPrestamos.getjTabbedPane1()) {
                if(administrarPrestamos.getjTabbedPane1().getSelectedIndex() == 0){
                    indexU = 0;
                    indexR = 0;
                    tabbedIndex = 0;
                    mostrarItemsDevolucion();
                }
                if(administrarPrestamos.getjTabbedPane1().getSelectedIndex() == 1){
                    indexU = 0;
                    indexR = 0;
                    tabbedIndex = 1;
                    mostrarItemsPrestamo();
                }
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Prestamo;
import co.edu.univalle.miniproyecto3.repository.PrestamoDAO;
import co.edu.univalle.miniproyecto3.repository.RecursoDAO;
import co.edu.univalle.miniproyecto3.vista.AdministrarPrestamos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author User
 */
public class AdministrarPrestamosController {
    private AdministrarPrestamos administrarPrestamos;
    private PrestamoDAO prestamoDAO;
    private RecursoDAO recursoDAO;
    private Prestamo prestamoTemporal;
    private Prestamo prestamo;
    private int indexU = 0;
    private int indexR = 0;
    private int tabbedIndex;
    private List listaRecursos;
    private List listaUsuarios;
//    private List listaPrestamos;

    public AdministrarPrestamosController(AdministrarPrestamos vista, PrestamoDAO prestamoDao, RecursoDAO recursoDao) {
        this.administrarPrestamos = vista;
        this.prestamoDAO = prestamoDao;
        this.recursoDAO = recursoDao;
        listaRecursos = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
//        listaPrestamos = new ArrayList<>();
        
        HandlerActions listener = new HandlerActions();
        
        administrarPrestamos.addBtnConfirmar(listener);
        administrarPrestamos.addBtnVolver(listener);
        administrarPrestamos.addjComboDevolucionRecursos(listener);
        administrarPrestamos.addjComboDevolucionUsuarios(listener);
        administrarPrestamos.addjComboPrestamoRecursos(listener);
        administrarPrestamos.addjComboPrestamoUsuarios(listener);
        administrarPrestamos.addjTabbedPanePrestamo(listener);
    }
    
    public void abrirVista() {
        tabbedIndex = 0;
        administrarPrestamos.getjTabbedPane1().setSelectedIndex(tabbedIndex);
        mostrarItemsDevolucion();
    }
    
    public void mostrarItemsDevolucion() {
        administrarPrestamos.getjComboDevolucionUsuarios().removeAllItems();
        listaUsuarios.clear();

        if(!prestamoDAO.getPrestamos().isEmpty()) {
            for (Map.Entry<Integer, Prestamo> entry : prestamoDAO.getPrestamos().entrySet()) {
                administrarPrestamos.getjComboDevolucionUsuarios().addItem(entry.getValue().getUsuario().getNombre());
                listaUsuarios.add(entry.getValue().getUsuario().getId());
            }
        }
        System.out.println(listaUsuarios);
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
                if (entry.getValue().getUsuario().getId() == index) {
                    administrarPrestamos.getjComboDevolucionRecursos().addItem(entry.getValue().getRecurso().getNombre());
                    listaRecursos.add(entry.getValue().getRecurso().getCodigoRecurso());
                }
            }
        }
        else {
            administrarPrestamos.getjComboDevolucionRecursos().removeAllItems();
        }
    }
    
    public void mostrarItemsPrestamo(Prestamo prestamo) {
//        defaultComboBoxUsuario.removeAllElements();
//        defaultComboBoxRecurso.removeAllElements();
//        defaultComboBoxUsuario.addElement(prestamo.getUsuario().toString());
        
        if(!prestamoDAO.getPrestamos().isEmpty()) {
            recursoDAO.getRecursos().forEach((clave, valor) -> {
                if (valor.isDisponible()){
                    listaRecursos.add(recursoDAO.setLlave(valor));
//                    defaultComboBoxRecurso.addElement(valor.toString());
                }
            });
        }
        
//        administrarPrestamos.getjComboPrestamoUsuarios().setModel(defaultComboBoxUsuario);
//        administrarPrestamos.getjComboPrestamoRecursos().setModel(defaultComboBoxRecurso);
    }
    
    public void cleanFields() {
//        defaultComboBoxUsuario.removeAllElements();
//        defaultComboBoxRecurso.removeAllElements();
        listaUsuarios.clear();
        listaRecursos.clear();
//        listaPrestamos.clear();
//        administrarPrestamos.getjComboDevolucionRecursos().setModel(defaultComboBoxRecurso);
//        administrarPrestamos.getjComboDevolucionUsuarios().setModel(defaultComboBoxUsuario);
//        administrarPrestamos.getjComboPrestamoRecursos().setModel(defaultComboBoxRecurso);
//        administrarPrestamos.getjComboPrestamoUsuarios().setModel(defaultComboBoxUsuario);
    }
    
    public Integer verificarLlaveDevolucion() {
        for (Map.Entry<Integer, Prestamo> entry : prestamoDAO.getPrestamos().entrySet()) {
            if (entry.getValue().getUsuario().getId() == (int) listaUsuarios.get(indexU) && entry.getValue().getRecurso().getCodigoRecurso() == (int) listaRecursos.get(indexR)) {
                return entry.getKey();
            }
        }    
        return -1;
    }
    
    class HandlerActions implements ActionListener, ChangeListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == administrarPrestamos.getBtnConfirmar()) {
                if(tabbedIndex == 0){
                    prestamoDAO.deletePrestamo(verificarLlaveDevolucion());
                    mostrarItemsDevolucion();
                    indexU = 0;
                    indexR = 0;
//                    cleanFields();
                }
                else if(tabbedIndex == 1){
                    int index = administrarPrestamos.getjComboPrestamoRecursos().getSelectedIndex();
                    String llave = listaRecursos.get(index).toString();
                    
                    prestamo = new Prestamo(prestamoTemporal.getUsuario(), recursoDAO.getRecurso(llave) );
                    prestamoDAO.addPrestamo(prestamo);
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
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == administrarPrestamos.getjTabbedPane1()) {
                if(administrarPrestamos.getjTabbedPane1().getSelectedIndex() == 0){
                    tabbedIndex = 0;
                    mostrarItemsDevolucion();
                }
                if(administrarPrestamos.getjTabbedPane1().getSelectedIndex() == 1){
                    tabbedIndex = 1;
                    mostrarItemsPrestamo(prestamoTemporal);
                }
            }
        }
    }
}

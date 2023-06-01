/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Prestamo;
import co.edu.univalle.miniproyecto3.model.Usuario;
import co.edu.univalle.miniproyecto3.repository.PrestamoDAO;
import co.edu.univalle.miniproyecto3.repository.RecursoDAO;
import co.edu.univalle.miniproyecto3.vista.AdministrarPrestamos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.DefaultComboBoxModel;

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
    private int llaveTemporal;
    private DefaultComboBoxModel defaultComboBoxUsuario;
    private DefaultComboBoxModel defaultComboBoxRecurso;
    private javax.swing.JComboBox<String> jComboDevolucionRecursos;
    private List llavesRecursos;
    private List listaUsuarios;

    public AdministrarPrestamosController(AdministrarPrestamos administrarPrestamos, PrestamoDAO prestamoDAO, RecursoDAO recursoDAO) {
        this.administrarPrestamos = administrarPrestamos;
        this.prestamoDAO = prestamoDAO;
        this.recursoDAO = recursoDAO;
        defaultComboBoxUsuario = new DefaultComboBoxModel();
        defaultComboBoxRecurso = new DefaultComboBoxModel();
        jComboDevolucionRecursos = new javax.swing.JComboBox<>();
        llavesRecursos = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        
        HandlerActions listener = new HandlerActions();
        
        administrarPrestamos.addBtnConfirmar(listener);
        administrarPrestamos.addBtnVolver(listener);
        administrarPrestamos.addjComboDevolucionRecursos(listener);
        administrarPrestamos.addjComboDevolucionUsuarios(listener);
        administrarPrestamos.addjComboPrestamoRecursos(listener);
        administrarPrestamos.addjComboPrestamoUsuarios(listener);
        administrarPrestamos.addjTabbedPanePrestamo(listener);
    }
    
//    public void abrirVista(int llave) {
    public void abrirVista() {
//        llaveTemporal = llave;
//        prestamoTemporal = prestamoDAO.getPrestamo(llave);
        mostrarItemsDevolucion();

    }
    
    public void mostrarItemsDevolucion() {
        defaultComboBoxUsuario.removeAllElements();
        
        prestamoDAO.getPrestamos().forEach((clave, valor) -> {
            defaultComboBoxUsuario.addElement(valor.getUsuario().toString());
            
            listaUsuarios.add(valor.getUsuario().getId());
        });
        
        administrarPrestamos.getjComboDevolucionUsuarios().setModel(defaultComboBoxUsuario);
        mostrarRecursosDevolucion(administrarPrestamos.getjComboDevolucionUsuarios().getSelectedIndex()); 
    }
    
    public void mostrarRecursosDevolucion(Integer index) {;
        defaultComboBoxRecurso.removeAllElements();

        prestamoDAO.getPrestamos().forEach((clave, valor) -> {
            if (valor.getUsuario().getId() == (int)listaUsuarios.get(index)) {
               defaultComboBoxRecurso.addElement(valor.getRecurso().toString());
            }
        });
        
        administrarPrestamos.getjComboDevolucionRecursos().setModel(defaultComboBoxRecurso);
    }
    
    public void mostrarItemsPrestamo(Prestamo prestamo) {
        defaultComboBoxUsuario.removeAllElements();
        defaultComboBoxRecurso.removeAllElements();
        defaultComboBoxUsuario.addElement(prestamo.getUsuario().toString());
        
        recursoDAO.getRecursos().forEach((clave, valor) -> {
            if (valor.isDisponible()){
                llavesRecursos.add(recursoDAO.setLlave(valor));
                defaultComboBoxRecurso.addElement(valor.toString());
            }
        });
        
        administrarPrestamos.getjComboPrestamoUsuarios().setModel(defaultComboBoxUsuario);
        administrarPrestamos.getjComboPrestamoRecursos().setModel(defaultComboBoxRecurso);
    }
    
    public void cleanFields() {
        defaultComboBoxUsuario.removeAllElements();
        defaultComboBoxRecurso.removeAllElements();
        administrarPrestamos.getjComboDevolucionRecursos().setModel(defaultComboBoxRecurso);
        administrarPrestamos.getjComboDevolucionUsuarios().setModel(defaultComboBoxUsuario);
        administrarPrestamos.getjComboPrestamoRecursos().setModel(defaultComboBoxRecurso);
        administrarPrestamos.getjComboPrestamoUsuarios().setModel(defaultComboBoxUsuario);
    }
    
    class HandlerActions implements ActionListener, ChangeListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == administrarPrestamos.getBtnConfirmar()) {
                if(administrarPrestamos.getjTabbedPane1().getSelectedIndex() == 0){
                    int index = administrarPrestamos.getjComboPrestamoRecursos().getSelectedIndex();
                    System.out.println(index);
                    prestamoDAO.deletePrestamo(index);
                    
//                    cleanFields();
                }
                if(administrarPrestamos.getjTabbedPane1().getSelectedIndex() == 1){
                    int index = administrarPrestamos.getjComboPrestamoRecursos().getSelectedIndex();
                    String llave = llavesRecursos.get(index).toString();
                    
                    prestamo = new Prestamo(prestamoTemporal.getUsuario(), recursoDAO.getRecurso(llave) );
                    prestamoDAO.addPrestamo(prestamo);
                }
            }
            if (e.getSource() == administrarPrestamos.getBtnVolver()) {
                administrarPrestamos.dispose();
            }
            if (e.getSource() == administrarPrestamos.getjComboDevolucionUsuarios()) {
                mostrarRecursosDevolucion(administrarPrestamos.getjComboDevolucionUsuarios().getSelectedIndex());
            }
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == administrarPrestamos.getjTabbedPane1()) {
                if(administrarPrestamos.getjTabbedPane1().getSelectedIndex() == 0){
                    mostrarItemsDevolucion();
                }
                if(administrarPrestamos.getjTabbedPane1().getSelectedIndex() == 1){
                    mostrarItemsPrestamo(prestamoTemporal);
                }
            }
        }
    
    }
    
}

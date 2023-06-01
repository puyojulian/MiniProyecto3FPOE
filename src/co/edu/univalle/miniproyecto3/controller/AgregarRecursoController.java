/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Recurso;
import co.edu.univalle.miniproyecto3.repository.RecursoDAO;
import co.edu.univalle.miniproyecto3.vista.AgregarRecurso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author julia
 */
public class AgregarRecursoController {
    private RecursoDAO recursoDAO;
    private AgregarRecurso agregarRecurso;
    private Recurso recursoTemporal;

    public AgregarRecursoController(AgregarRecurso vista, RecursoDAO dao) {
        this.recursoDAO = dao;
        this.agregarRecurso = vista;
        
        ActionEventHandler manejadoraDeEventos = new ActionEventHandler();
        DocumentEventHandler manejadorDeEventos = new DocumentEventHandler();
        
        agregarRecurso.addBtnAgregar(manejadoraDeEventos);
        agregarRecurso.addBtnCancelar(manejadoraDeEventos);
        agregarRecurso.addTextArea(manejadorDeEventos);
        agregarRecurso.addTextAutor(manejadorDeEventos);
        agregarRecurso.addTextGenero(manejadorDeEventos);
    }
    
    public void crearRecurso(String isbn, String nombre, String autor, String generoLiterario, String areaConocimiento) {
        recursoTemporal = new Recurso(isbn, nombre, autor, generoLiterario, areaConocimiento);
        recursoDAO.addRecurso(recursoTemporal);
    }
    
    public boolean verificarContenido() {
        boolean hayCamposVacios = agregarRecurso.getTextIsbn().getText().replaceAll("\\s", "").isEmpty() ||
                agregarRecurso.getTextTitulo().getText().replaceAll("\\s", "").isEmpty() ||
                agregarRecurso.getTextAutor1().getText().replaceAll("\\s", "").isEmpty() ||
                agregarRecurso.getTextGenero1().getText().replaceAll("\\s", "").isEmpty() ||
                agregarRecurso.getTextArea1().getText().replaceAll("\\s", "").isEmpty();
        return !hayCamposVacios;
    }
    
    class ActionEventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == agregarRecurso.getBotonAgregar()) {
                if(verificarContenido()) {
                    crearRecurso(agregarRecurso.getTextIsbn().getText(),
                            agregarRecurso.getTextTitulo().getText(),
                            agregarRecurso.getTextAutor1().getText(),
                            agregarRecurso.getTextGenero1().getText(),
                            agregarRecurso.getTextArea1().getText());

                    if(!agregarRecurso.getTextAutor2().getText().isEmpty()) {
                        recursoTemporal.addAutor(agregarRecurso.getTextAutor2().getText());
                    }
                    if(!agregarRecurso.getTextGenero2().getText().isEmpty()) {
                        recursoTemporal.addGenero(agregarRecurso.getTextGenero2().getText());
                    }
                    if(!agregarRecurso.getTextArea2().getText().isEmpty()) {
                        recursoTemporal.addArea(agregarRecurso.getTextArea2().getText());
                    }
                    cleanFields();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Ingrese al menos una entrada por campo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == agregarRecurso.getBotonCancelar()) {
                cleanFields();
                agregarRecurso.setVisible(false);
            }
        }
    }
    
    public void cleanFields() {
        agregarRecurso.getTextAutor1().setText("");
        agregarRecurso.getTextAutor2().setText("");
        agregarRecurso.getTextGenero1().setText("");
        agregarRecurso.getTextGenero2().setText("");
        agregarRecurso.getTextArea1().setText("");
        agregarRecurso.getTextArea2().setText("");
        agregarRecurso.getTextIsbn().setText("");
        agregarRecurso.getTextTitulo().setText("");
    }
        
    class DocumentEventHandler implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            enableTextFields(e);         
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            enableTextFields(e);        
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            enableTextFields(e);
        }
        
        private void enableTextFields(DocumentEvent e) {
            JTextField textField = (JTextField) e.getDocument().getProperty("owner");
            if(textField == agregarRecurso.getTextAutor1()) {
                agregarRecurso.getTextAutor2().setEnabled(!agregarRecurso.getTextAutor1().getText().isEmpty());
            }
            else if(textField == agregarRecurso.getTextGenero1()) {
                agregarRecurso.getTextGenero2().setEnabled(!agregarRecurso.getTextGenero1().getText().isEmpty());
            }
            else if(textField == agregarRecurso.getTextArea1()) {
                agregarRecurso.getTextArea2().setEnabled(!agregarRecurso.getTextArea1().getText().isEmpty());
            }    
        }
    }
    
    
    
}

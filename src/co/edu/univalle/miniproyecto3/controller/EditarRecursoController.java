/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Recurso;
import co.edu.univalle.miniproyecto3.repository.RecursoDAO;
import co.edu.univalle.miniproyecto3.vista.EditarRecurso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.DefaultListModel;

/**
 *
 * @author User
 */
public class EditarRecursoController {
    private RecursoDAO recursoDAO;
    private EditarRecurso editarRecurso;
    private String llaveTemporal;
    private Recurso recursoTemporal;
    private DefaultListModel<String> modeloLista;

    public EditarRecursoController(EditarRecurso vista, RecursoDAO dao) {
        this.recursoDAO = dao;
        this.editarRecurso = vista;
        this.modeloLista = new DefaultListModel<>();
        
        
        ActionHandler listener = new ActionHandler();
        
        editarRecurso.addBtnEditar(listener);
        editarRecurso.addBtnCancelar(listener);
        editarRecurso.addBtnModificar(listener);
        editarRecurso.addBtnRadioAutor(listener);
        editarRecurso.addBtnRadioGenero(listener);
        editarRecurso.addBtnRadioArea(listener);
    }
    
    public void abrirVista(String llave) {
        llaveTemporal = llave;
        recursoTemporal = recursoDAO.getRecurso(llave);
        mostrarItems(recursoTemporal);
    }
    
    public void mostrarItems(Recurso recurso) {
        editarRecurso.getTxtTitulo().setText(recursoTemporal.getNombre());
        editarRecurso.getTxtISBN().setText(recursoTemporal.getIsbn());
        editarRecurso.getBtnRadioAutor().setSelected(true);
        actualizarJListaAutores();
    }
    
    public void actualizarJListaAutores() {
        if(recursoTemporal.getAutores().size() > 0) {
            modeloLista.clear();
            Iterator iterador = recursoTemporal.getAutores().iterator();
            while(iterador.hasNext()) {
                String item = (String) iterador.next(); 
                modeloLista.addElement(item);
            }
            editarRecurso.getJList().setModel(modeloLista);
        }
        else {
            modeloLista.clear();
            editarRecurso.getJList().setModel(modeloLista);
        }
    }
    
    public void actualizarJListaAreas() {
        if(recursoTemporal.getAreasConocimiento().size() > 0) {
            modeloLista.clear();
            Iterator iterador = recursoTemporal.getAreasConocimiento().iterator();
            while(iterador.hasNext()) {
                String item = (String) iterador.next(); 
                modeloLista.addElement(item);
            }
            editarRecurso.getJList().setModel(modeloLista);
        }
        else {
            modeloLista.clear();
            editarRecurso.getJList().setModel(modeloLista);
        }
    }
    
    public void actualizarJListaGeneros() {
        if(recursoTemporal.getGenerosLiterario().size() > 0) {
            modeloLista.clear();
            Iterator iterador = recursoTemporal.getGenerosLiterario().iterator();
            while(iterador.hasNext()) {
                String item = (String) iterador.next(); 
                modeloLista.addElement(item);
            }
            editarRecurso.getJList().setModel(modeloLista);
        }
        else {
            modeloLista.clear();
            editarRecurso.getJList().setModel(modeloLista);
        }
    }
    
    class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == editarRecurso.getBtnEditar()) {
                
            }
            else if(e.getSource() == editarRecurso.getBtnEditar()) {
                
            }
            else if(e.getSource() == editarRecurso.getBtnModificar()) {
                
            }
            else if(e.getSource() == editarRecurso.getBtnRadioAutor()) {
                actualizarJListaAutores();
            }
            else if(e.getSource() == editarRecurso.getBtnRadioGenero()) {
                actualizarJListaGeneros();
            }
            else if(e.getSource() == editarRecurso.getBtnRadioArea()) {
                actualizarJListaAreas();
            }
        }
        
    }
}

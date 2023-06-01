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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
        editarRecurso.getJList().addListSelectionListener(listener);
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
    
    public void limpiarCampos() {
        editarRecurso.getTxtTitulo().setText("");
        editarRecurso.getTxtISBN().setText("");
        editarRecurso.getTxtValorLista().setText("");
        modeloLista.clear();
        editarRecurso.getJList().setModel(modeloLista);
        if(editarRecurso.getBtnRadioAutor().isSelected()) {
            editarRecurso.getBtnRadioAutor().setSelected(false);
        }
        else if(editarRecurso.getBtnRadioGenero().isSelected()) {
            editarRecurso.getBtnRadioGenero().setSelected(false);
        }
        else if(editarRecurso.getBtnRadioArea().isSelected()) {
            editarRecurso.getBtnRadioArea().setSelected(false);
        }
    }
    
    class ActionHandler implements ActionListener, ListSelectionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == editarRecurso.getBtnEditar()) {
                recursoTemporal.setNombre(editarRecurso.getTxtTitulo().getText());
                recursoTemporal.setIsbn(editarRecurso.getTxtISBN().getText());
                limpiarCampos();
                editarRecurso.setVisible(false);
            }
            else if(e.getSource() == editarRecurso.getBtnCancelar()) {
                limpiarCampos();
                editarRecurso.setVisible(false);
            }
            else if(e.getSource() == editarRecurso.getBtnModificar()) {
                if(editarRecurso.getJList().getSelectedIndex() == -1) {
                    if(editarRecurso.getBtnRadioAutor().isSelected()) {
                        recursoTemporal.addAutor(editarRecurso.getTxtValorLista().getText());
                        actualizarJListaAutores();
                    }
                    else if(editarRecurso.getBtnRadioGenero().isSelected()) {
                        recursoTemporal.addGenero(editarRecurso.getTxtValorLista().getText());
                        actualizarJListaGeneros();
                    }
                    else if(editarRecurso.getBtnRadioArea().isSelected()) {
                        recursoTemporal.addArea(editarRecurso.getTxtValorLista().getText());
                        actualizarJListaAreas();
                    }
                }
                else {
                    if(editarRecurso.getBtnRadioAutor().isSelected()) {
                        int index = editarRecurso.getJList().getSelectedIndex();
                        List listaTemporal = recursoTemporal.getAutores();
                        listaTemporal.set(index,editarRecurso.getTxtValorLista().getText());
                        recursoTemporal.setAutores((ArrayList) listaTemporal);
                        actualizarJListaAutores();
                    }
                    else if(editarRecurso.getBtnRadioGenero().isSelected()) {
                        int index = editarRecurso.getJList().getSelectedIndex();
                        List listaTemporal = recursoTemporal.getGenerosLiterario();
                        listaTemporal.set(index,editarRecurso.getTxtValorLista().getText());
                        recursoTemporal.setGenerosLiterario((ArrayList) listaTemporal);
                        actualizarJListaGeneros();
                    }
                    else if(editarRecurso.getBtnRadioArea().isSelected()) {
                        int index = editarRecurso.getJList().getSelectedIndex();
                        List listaTemporal = recursoTemporal.getAreasConocimiento();
                        listaTemporal.set(index,editarRecurso.getTxtValorLista().getText());
                        recursoTemporal.setAreasConocimiento((ArrayList) listaTemporal);
                        actualizarJListaAreas();
                    }
                }
            }
            else if(e.getSource() == editarRecurso.getBtnRadioAutor()) {
                actualizarJListaAutores();
                editarRecurso.getBtnModificar().setText("Agregar");
            }
            else if(e.getSource() == editarRecurso.getBtnRadioGenero()) {
                actualizarJListaGeneros();
                editarRecurso.getBtnModificar().setText("Agregar");
            }
            else if(e.getSource() == editarRecurso.getBtnRadioArea()) {
                actualizarJListaAreas();
                editarRecurso.getBtnModificar().setText("Agregar");
            }
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = editarRecurso.getJList().getSelectedValue();
                if(editarRecurso.getBtnRadioAutor().isSelected()) {
                    editarRecurso.getTxtValorLista().setText(selectedValue);
                    editarRecurso.getBtnModificar().setText("Modificar");
                }
                else if(editarRecurso.getBtnRadioGenero().isSelected()) {
                    editarRecurso.getTxtValorLista().setText(selectedValue);
                    editarRecurso.getBtnModificar().setText("Modificar");
                }
                else if(editarRecurso.getBtnRadioArea().isSelected()) {
                    editarRecurso.getTxtValorLista().setText(selectedValue);
                    editarRecurso.getBtnModificar().setText("Modificar");
                }                
            }
        }
    }
}

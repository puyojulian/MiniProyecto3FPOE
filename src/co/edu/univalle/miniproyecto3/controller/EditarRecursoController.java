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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditarRecursoController {
    private RecursoDAO recursoDAO;
    private EditarRecurso editarRecurso;
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
    
    class ActionHandler implements ActionListener, ListSelectionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == editarRecurso.getBtnEditar()) {
                if ("".equals(editarRecurso.getTxtTitulo().getText().replaceAll("\\s", "")) || "".equals(editarRecurso.getTxtISBN().getText().replaceAll("\\s", ""))) {
                    mensajeTemporal("Debes llenar los campo con un valor", "Error", 1150);
                    mostrarItems(recursoTemporal);
                } else {
                    recursoTemporal.setNombre(editarRecurso.getTxtTitulo().getText());
                    recursoTemporal.setIsbn(editarRecurso.getTxtISBN().getText());
                    limpiarCampos();
                    mensajeTemporal("Recurso editado satisfactoriamente", "Aviso", 1150);
                    editarRecurso.dispose();
                }
            }
            else if(e.getSource() == editarRecurso.getBtnCancelar()) {
                limpiarCampos();
                editarRecurso.dispose();
            }
            else if(e.getSource() == editarRecurso.getBtnModificar()) {
                if(editarRecurso.getJList().getSelectedIndex() == -1) {
                    if (!"".equals(editarRecurso.getTxtValorLista().getText())) {
                        if(editarRecurso.getBtnRadioAutor().isSelected()) {
                            recursoTemporal.addAutor(editarRecurso.getTxtValorLista().getText());
                            actualizarJListaAutores();
                            editarRecurso.getTxtValorLista().setText("");
                            mensajeTemporal("Autor agregado", "Aviso", 1000);
                        }
                        else if(editarRecurso.getBtnRadioGenero().isSelected()) {
                            recursoTemporal.addGenero(editarRecurso.getTxtValorLista().getText());
                            actualizarJListaGeneros();
                            editarRecurso.getTxtValorLista().setText("");
                            mensajeTemporal("Genero agregado", "Aviso", 1000);
                        }
                        else if(editarRecurso.getBtnRadioArea().isSelected()) {
                            recursoTemporal.addArea(editarRecurso.getTxtValorLista().getText());
                            actualizarJListaAreas();
                            editarRecurso.getTxtValorLista().setText("");
                            mensajeTemporal("Area de conocimiento agregada", "Aviso", 1000);
                        }
                    } else {
                        mensajeTemporal("Debe llenar el campo con un valor", "Error", 1000);
                    }
                }
                else {
                    if(editarRecurso.getBtnRadioAutor().isSelected()) {
                        int index = editarRecurso.getJList().getSelectedIndex();
                        List listaTemporal = recursoTemporal.getAutores();
                        listaTemporal.set(index,editarRecurso.getTxtValorLista().getText());
                        recursoTemporal.setAutores((ArrayList) listaTemporal);
                        actualizarJListaAutores();
                        editarRecurso.getBtnModificar().setText("Agregar");
                        mensajeTemporal("Autor modificado", "Aviso", 1000);
                    }
                    else if(editarRecurso.getBtnRadioGenero().isSelected()) {
                        int index = editarRecurso.getJList().getSelectedIndex();
                        List listaTemporal = recursoTemporal.getGenerosLiterario();
                        listaTemporal.set(index,editarRecurso.getTxtValorLista().getText());
                        recursoTemporal.setGenerosLiterario((ArrayList) listaTemporal);
                        actualizarJListaGeneros();
                        editarRecurso.getBtnModificar().setText("Agregar");
                        mensajeTemporal("Genero modificado", "Aviso", 1000);
                    }
                    else if(editarRecurso.getBtnRadioArea().isSelected()) {
                        int index = editarRecurso.getJList().getSelectedIndex();
                        List listaTemporal = recursoTemporal.getAreasConocimiento();
                        listaTemporal.set(index,editarRecurso.getTxtValorLista().getText());
                        recursoTemporal.setAreasConocimiento((ArrayList) listaTemporal);
                        actualizarJListaAreas();
                        editarRecurso.getBtnModificar().setText("Agregar");
                        mensajeTemporal("Area de conocimiento modificada", "Aviso", 1000);
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

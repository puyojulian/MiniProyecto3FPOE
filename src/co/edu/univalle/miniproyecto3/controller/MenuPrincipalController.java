package co.edu.univalle.miniproyecto3.controller;

import co.edu.univalle.miniproyecto3.model.Recurso;
import co.edu.univalle.miniproyecto3.vista.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;

public class MenuPrincipalController {
    
    private MenuPrincipal menuPrincipal;
    private Recurso recurso;
    
    public MenuPrincipalController(MenuPrincipal menuPrincipal, Recurso recurso) {
        this.menuPrincipal = menuPrincipal;
        this.recurso = recurso;
        
        HandlerActions listener = new HandlerActions();
        menuPrincipal.addBtnRecursos(listener);
        menuPrincipal.addBtnUsuarios(listener);
        menuPrincipal.addBtnBuscar(listener);
        menuPrincipal.addBtnBusqueda(listener);
        menuPrincipal.addBtnAgregar(listener);
        menuPrincipal.addBtnActualizar(listener);
        menuPrincipal.addBtnEliminar(listener);
        
    }

    class HandlerActions implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((MenuPrincipal.getBtnRecursos).isSelected()) {
                
            }
        }
    }
    
}

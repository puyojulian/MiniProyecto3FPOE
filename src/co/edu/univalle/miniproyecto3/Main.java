/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package co.edu.univalle.miniproyecto3;


import co.edu.univalle.miniproyecto3.controller.MenuPrincipalController;
import co.edu.univalle.miniproyecto3.vista.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        MenuPrincipalController menuPrincipalController = new MenuPrincipalController(menuPrincipal);
    }
}


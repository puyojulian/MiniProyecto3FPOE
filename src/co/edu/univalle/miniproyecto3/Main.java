/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package co.edu.univalle.miniproyecto3;

///**
// *
// * @author julia
// */
//public class Main {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
//    
//}

import co.edu.univalle.miniproyecto3.controller.MenuPrincipalController;
import co.edu.univalle.miniproyecto3.vista.MenuPrincipal;
//import javax.swing.*;
//import java.awt.*;
//import java.util.*;

public class Main {
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        MenuPrincipalController menuPrincipalController = new MenuPrincipalController(menuPrincipal);
        
        
        // Create a Map
//        Map<Integer, String> map = new HashMap<>();
//        map.put(1, "Apple");
//        map.put(2, "Banana");
//        map.put(3, "Orange");
//
//        // Convert the Map entries to a List
//        java.util.List<Map.Entry<Integer, String>> list = new ArrayList<>(map.entrySet());
//
//        // Create a JList and set the List as the data source
//        JList<Map.Entry<Integer, String>> jList = new JList<>(list.toArray(new Map.Entry[list.size()]));
//
//        // Create a JFrame and add the JList to it
//        JFrame frame = new JFrame("Map to JList Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//        frame.add(new JScrollPane(jList), BorderLayout.CENTER);
//        frame.setSize(200, 200);
//        frame.setVisible(true);
    }
}


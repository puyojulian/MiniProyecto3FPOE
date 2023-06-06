/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.repository;

import co.edu.univalle.miniproyecto3.model.Prestamo;
import co.edu.univalle.miniproyecto3.model.Recurso;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author Sebastián
 */
public class PrestamoDAO implements PrestamoDAOInterface {
    
    LocalDate fechaHoy = LocalDate.now();
    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String fechaHoyFormateada = fechaHoy.format(formateador);
    private List listaLlaves = new ArrayList();
    
    Map <Integer, Prestamo> mapaPrestamos = new HashMap();

    @Override
    public Map<Integer, Prestamo> getPrestamos() {
        return mapaPrestamos;
    }

    @Override
    public Prestamo getPrestamo(Integer llave) {
        return mapaPrestamos.get(llave);
    }

    @Override
    public boolean addPrestamo(Prestamo prestamo) {
        prestamo.getRecurso().setDisponible(false);
        mapaPrestamos.put(prestamo.getId(), prestamo);
        return true;
    }

    @Override
    public boolean updatePrestamo(Integer llave, Prestamo prestamo) {
        mapaPrestamos.put(llave,prestamo);
        return true;
    }

    @Override
    public boolean deletePrestamo(Integer llave) {
        Prestamo prestamo;           
        Recurso recurso;
        
        prestamo = mapaPrestamos.get(llave);
        System.out.println(llave);
        recurso = prestamo.getRecurso();
        
        prestamo.setFechaDevolucion(fechaHoyFormateada);
        prestamo.setEstado(prestamo.getEstados()[1]);
        verificarCerrado(llave);
        recurso.setDisponible(true);
 
//        mapaPrestamos.remove(llave);
        return true;
    }
    
    public void verificarCerrado(Integer llave) {
        listaLlaves.clear();
        boolean verificarPCerrado = false;
        
        Set<Map.Entry<Integer, Prestamo>> entrySetMapa = mapaPrestamos.entrySet();
        
        for (Map.Entry<Integer, Prestamo> entry : entrySetMapa){
            if(entry.getValue().getUsuario().getId() == mapaPrestamos.get(llave).getUsuario().getId() && entry.getValue().getFechaRealizacion().equals(mapaPrestamos.get(llave).getFechaRealizacion())) {
                if(entry.getValue().getEstado().equals(entry.getValue().getEstados()[1])) {
                    verificarPCerrado = true;
                    listaLlaves.add(entry.getKey());
                }
                else {
                    verificarPCerrado = false;
                }
            }
        }
        
        Iterator iterador = listaLlaves.iterator();
        if(verificarPCerrado) {
            while(iterador.hasNext()) {
                int key = (int) iterador.next();
                mapaPrestamos.get(key).setEstado(mapaPrestamos.get(key).getEstados()[2]);
            }
        }
    }
    
}

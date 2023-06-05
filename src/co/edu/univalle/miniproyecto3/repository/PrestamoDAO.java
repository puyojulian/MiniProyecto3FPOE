/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.repository;

import co.edu.univalle.miniproyecto3.model.Prestamo;
import co.edu.univalle.miniproyecto3.model.Recurso;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Sebastián
 */
public class PrestamoDAO implements PrestamoDAOInterface {
    
    LocalDate fechaHoy = LocalDate.now();
    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String fechaHoyFormateada = fechaHoy.format(formateador);
    
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
        recurso = prestamo.getRecurso();
        
        prestamo.setFechaDevolucion(fechaHoyFormateada);
        prestamo.setEstado(prestamo.getEstados()[2]);
        recurso.setDisponible(true);
 
//        mapaPrestamos.remove(llave);
        return true;
    }
    
}

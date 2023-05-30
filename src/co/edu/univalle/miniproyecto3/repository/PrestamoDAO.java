/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.repository;

import co.edu.univalle.miniproyecto3.model.Prestamo;
import co.edu.univalle.miniproyecto3.model.Recurso;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Sebastián
 */
public class PrestamoDAO implements PrestamoDAOInterface {
    
    Map <Integer, Prestamo> mapaPrestamos = new HashMap();

    @Override
    public Map<Integer, Prestamo> getPrestamo() {
        return mapaPrestamos;
    }

    @Override
    public Prestamo getPrestamo(Integer llave) {
        return mapaPrestamos.get(llave);
    }

    @Override
    public boolean addPrestamo(Prestamo prestamo) {
        mapaPrestamos.put(mapaPrestamos.size() + 1, prestamo);
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
        prestamo = new Prestamo();
                    
        Recurso recurso;
        recurso = new Recurso();
        
        prestamo = mapaPrestamos.get(llave);
        recurso = prestamo.getRecurso();
        
        recurso.setDisponible(true);
        
        mapaPrestamos.remove(llave);
        return true;
    }
    
}

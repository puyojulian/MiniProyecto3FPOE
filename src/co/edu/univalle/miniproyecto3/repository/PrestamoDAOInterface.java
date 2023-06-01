/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.univalle.miniproyecto3.repository;

import co.edu.univalle.miniproyecto3.model.Prestamo;
import java.util.Map;

/**
 *
 * @author Sebastián
 */
public interface PrestamoDAOInterface {
    
    public Map<Integer, Prestamo> getPrestamos();
    
    public Prestamo getPrestamo(Integer llave);
    
    public boolean addPrestamo(Prestamo prestamo);
    
    public boolean updatePrestamo(Integer llave, Prestamo prestamo);
    
    public boolean deletePrestamo(Integer llave);
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.univalle.miniproyecto3.repository;

import co.edu.univalle.miniproyecto3.model.Recurso;
import java.util.Map;

/**
 *
 * @author julia
 */
public interface RecursoDAOInterface {
    
    public Map<String, Recurso> getRecursos();
    
    public Recurso getRecurso(String llave);
    
    public boolean addRecurso(Recurso recurso);
    
    public boolean updateRecurso(String llave, Recurso recurso);
    
    public boolean deleteRecurso(String llave);
}
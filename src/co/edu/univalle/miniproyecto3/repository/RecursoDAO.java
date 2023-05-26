/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.repository;

import co.edu.univalle.miniproyecto3.model.Recurso;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author julia
 */
public class RecursoDAO implements RecursoDAOInterface {
    
    Map <String, Recurso> mapaRecursos = new HashMap();

    @Override
    public Map<String, Recurso> getRecursos() {
        return mapaRecursos;
    }

    @Override
    public Recurso getRecurso(String llave) {
        return mapaRecursos.get(llave);
    }

    @Override
    public boolean addRecurso(Recurso recurso) {
        mapaRecursos.put(setLlave(recurso), recurso);
        return true;
    }

    @Override
    public boolean updateRecurso(String llave, Recurso recurso) {
        mapaRecursos.put(llave, recurso);
        return true;
    }

    @Override
    public boolean deleteRecurso(String llave) {
        mapaRecursos.remove(llave);
        return true;
    }
    
    public String setLlave(Recurso recurso) {
        char primeraLetraNombre = recurso.getNombre().charAt(0);
        char ultimaLetraNombre = recurso.getNombre().charAt(recurso.getNombre().length() - 1);
        String codigoRecursoSrt = recurso.getCodigoRecurso() + "";
        String llave = primeraLetraNombre + codigoRecursoSrt + ultimaLetraNombre;
        return llave;
    }
    
}

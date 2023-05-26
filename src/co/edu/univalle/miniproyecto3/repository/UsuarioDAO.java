/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.miniproyecto3.repository;

import co.edu.univalle.miniproyecto3.model.Usuario;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author julia
 */
public class UsuarioDAO implements UsuarioDAOInterface {
    
    Map<Integer,Usuario> mapaUsuarios= new HashMap<>();

    @Override
    public Map<Integer, Usuario> getUsuarios() {
        return mapaUsuarios;
    }

    @Override
    public Usuario getUsuario(int id) {
        return mapaUsuarios.get(id);
    }

    @Override
    public boolean addUsuario(Usuario usuario) {
        mapaUsuarios.put(usuario.getId(),usuario);
        return true;
    }

    @Override
    public boolean updateUsuario(int id, Usuario usuario) {
        mapaUsuarios.put(id, usuario);
        return true;
    }

    @Override
    public boolean deleteUsuario(int id) {
        mapaUsuarios.remove(id);
        return true;
    }
}

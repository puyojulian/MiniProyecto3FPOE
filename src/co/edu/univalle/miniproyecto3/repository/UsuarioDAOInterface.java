package co.edu.univalle.miniproyecto3.repository;

import co.edu.univalle.miniproyecto3.model.Usuario;
import java.util.Map;

public interface UsuarioDAOInterface {
    
    public Map<Integer, Usuario> getUsuarios();
    
    public Usuario getUsuario(int id);
    
    public boolean addUsuario(Usuario usuario);
    
    public boolean updateUsuario(int id, Usuario usuario);
    
    public boolean deleteUsuario(int id);
}

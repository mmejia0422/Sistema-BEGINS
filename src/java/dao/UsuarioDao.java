/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Usuario;

/**
 *
 * @author Mario
 */
public interface UsuarioDao {
    public Usuario findByUsuario(Usuario usuario);
    public Usuario login(Usuario usuario);
    public List<Usuario> findAll();
    public boolean create(Usuario usuario);
    public boolean update(Usuario usuario);
    public boolean delete(Integer id);
    public List<Usuario> selectItems();
    public Usuario findUser(String usuarioSesion);
}

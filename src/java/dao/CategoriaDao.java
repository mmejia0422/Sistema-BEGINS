/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Categoria;

/**
 *
 * @author Mario
 */
public interface CategoriaDao {
    public List<Categoria> findAll();
    public boolean create(Categoria categoria);
    public boolean update(Categoria categoria);
    public boolean delete(Integer id);
    public List<Categoria> selectItems();
}

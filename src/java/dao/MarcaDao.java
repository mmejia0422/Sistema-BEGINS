/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Marca;

/**
 *
 * @author Mario
 */
public interface MarcaDao {
    public List<Marca> findAll();
    public boolean create(Marca marca);
    public boolean update(Marca marca);
    public boolean delete(Integer id);
    public List<Marca> selectItems();
}

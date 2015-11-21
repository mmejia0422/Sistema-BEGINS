/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.CatProducto;

/**
 *
 * @author Mario
 */
public interface CateProductoDao {
    public List<CatProducto> findAll();
    public boolean create(CatProducto catPro);
    public boolean update(CatProducto catPro);
    public boolean delete(Integer id);
    public List<CatProducto> selectItems();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Presentacion;

/**
 *
 * @author Mario
 */
public interface PresentacionDao {
    public List<Presentacion> findAll();
    public boolean create(Presentacion presentacion);
    public boolean update(Presentacion presentacion);
    public boolean delete(Integer id);
    public List<Presentacion> selectItems();
}

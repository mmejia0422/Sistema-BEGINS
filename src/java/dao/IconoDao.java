/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Icono;

/**
 *
 * @author Mario
 */
public interface IconoDao {
    public List<Icono> findAll();
    public boolean create(Icono icono);
    public boolean update(Icono icono);
    public boolean delete(Integer id);
    public List<Icono> selectItems();
}

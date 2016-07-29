/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Menu;

/**
 *
 * @author Mario
 */
public interface MenuDao {
    public List<Menu> findByRolMenu (Integer idMenu);
    public List<Menu> findAll();
    public boolean create(Menu menu);
    public boolean update(Menu menu);
    public boolean delete(Integer id);
}

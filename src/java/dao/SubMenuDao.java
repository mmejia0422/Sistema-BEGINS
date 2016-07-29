/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Submenu;

/**
 *
 * @author Mario
 */
public interface SubMenuDao {
    public List<Submenu> findByMenu(Integer idMenu);
    public boolean create(Submenu subMenu);
    public boolean update(Submenu subMenu);
    public boolean delete(Integer id);
}

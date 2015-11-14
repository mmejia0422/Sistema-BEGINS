/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Pais;

/**
 *
 * @author Mario
 */
public interface PaisDao {
    public List<Pais> findAll();
    public boolean create(Pais pais);
}

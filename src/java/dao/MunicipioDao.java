/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Municipio;

/**
 *
 * @author Mario
 */
public interface MunicipioDao {
    public List<Municipio> findAll();
    public boolean create(Municipio municipio);
    public boolean update(Municipio municipio);
    public boolean delete(Integer id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Empleado;

/**
 *
 * @author Mario
 */
public interface EmpleadoDao {
    public List<Empleado> findAll();
    public boolean create(Empleado empleado);
    public boolean update(Empleado empleado);
    public boolean delete(Integer id);
    public List<Empleado> selectItems();
}

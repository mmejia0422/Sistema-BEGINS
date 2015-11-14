/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Departamento;

/**
 *
 * @author Mario
 */
public interface DepartamentoDao {
    public List<Departamento> findAll();
    public boolean create(Departamento departamento);
    public boolean update(Departamento departamento);
    public boolean delete(Integer id);
}

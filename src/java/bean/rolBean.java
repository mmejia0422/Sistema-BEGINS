/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.RolDao;
import dao.RolDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import model.Rol;

/**
 *
 * @author Mario
 */
@ManagedBean
@RequestScoped
public class rolBean {

  private List<SelectItem> selectOneItemsRol;
    
    public rolBean() {
    }

    /**
     * @return the selectOneItemsRol
     */
    public List<SelectItem> getSelectOneItemsRol() {
        this.selectOneItemsRol = new ArrayList<SelectItem>();
        RolDao rolDao = new RolDaoImpl();
        List<Rol> roles = rolDao.selectItems();
        for (Rol rol :roles){
            SelectItem selectItem = new SelectItem(rol.getIdRol(), rol.getDescripcion());
            this.selectOneItemsRol.add(selectItem);
        }
        return selectOneItemsRol;
    }
}

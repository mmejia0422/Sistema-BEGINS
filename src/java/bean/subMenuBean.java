/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.SubMenuDao;
import dao.SubMenuDaoImpl;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Menu;
import model.Submenu;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Mario
 */
@ManagedBean (name = "subMenuBean")
@RequestScoped
public class subMenuBean implements Serializable{

    private Submenu selectedSubMenu;
    
    public subMenuBean() {
        this.selectedSubMenu = new Submenu();
    }
    
     @PostConstruct
    public void init() {        
    }

    public void btnCreate(ActionEvent actionEvent, Integer id) {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        String msg;
        
        this.selectedSubMenu.getMenu().setIdmenu(id);
        
        if (subMenuDao.create(this.selectedSubMenu)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            
            this.selectedSubMenu = new Submenu();
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdate(ActionEvent actionEvent) {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        String msg;

        if (subMenuDao.update(this.selectedSubMenu)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnDelete(ActionEvent actionEvent) {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        String msg;
        if (subMenuDao.delete(this.selectedSubMenu.getIdsubmenu())) {
            msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public Submenu getSelectedSubMenu() {
        return selectedSubMenu;
    }

    public void setSelectedSubMenu(Submenu selectedSubMenu) {
        this.selectedSubMenu = selectedSubMenu;
    }
}

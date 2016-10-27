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
import javax.faces.application.FacesMessage.Severity;
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
@ManagedBean(name = "subMenuBean")
@RequestScoped
public class subMenuBean implements Serializable {

    private Submenu selectedSubMenu;

    public subMenuBean() {
        this.selectedSubMenu = new Submenu();
    }

    @PostConstruct
    public void init() {
    }

    public void btnCreate(ActionEvent actionEvent) {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        String msg;

        Integer id = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idMenu");
        this.selectedSubMenu.getMenu().setIdmenu(id);

        if (subMenuDao.create(this.selectedSubMenu)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
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

    public void btnDelete(Integer idSubmenu) {
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        String msg;
        if (subMenuDao.delete(idSubmenu)) {
            msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void eliminarMultiplesSm(List<Submenu> eliminarLista) {
        String msg = null;
        Severity tipoMsg = null;
        
      if(eliminarLista.size() > 0){  
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        
        
        try{
        
        for (int i = 0; i < eliminarLista.size(); i++) {
            subMenuDao.delete(eliminarLista.get(i).getIdsubmenu());
            }
        } catch(Exception e){
            msg = "Error al intentar eliminar registro: " + e.toString();
            tipoMsg = FacesMessage.SEVERITY_ERROR;
        }

       if (msg == null){
           msg = "Total de registros eliminados con exito: " + eliminarLista.size();
           tipoMsg = FacesMessage.SEVERITY_INFO;
       }
      }else {
          msg = "Por favor seleccione para eliminar";
          tipoMsg = FacesMessage.SEVERITY_WARN;
      }
       
        FacesMessage message = new FacesMessage(tipoMsg, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Submenu getSelectedSubMenu() {
        return selectedSubMenu;
    }

    public void setSelectedSubMenu(Submenu selectedSubMenu) {
        this.selectedSubMenu = selectedSubMenu;
    }
}

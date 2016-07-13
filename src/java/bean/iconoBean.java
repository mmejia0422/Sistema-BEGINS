/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.IconoDao;
import dao.IconoDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Icono;

/**
 *
 * @author Mario
 */
@ManagedBean (name = "iconoBean")
@RequestScoped
public class iconoBean {

    private List<Icono> iconos;
    private Icono selectedIcono;
    
    public iconoBean() {
        this.iconos = new ArrayList<Icono>();
    }
    
    @PostConstruct
    public void init(){
        this.selectedIcono = new Icono();
    }

    /**
     * @return the iconos
     */
    public List<Icono> getIconos() {
        IconoDao icoDao = new IconoDaoImpl();
        this.iconos = icoDao.findAll();
        return iconos;
    }

    /**
     * @param iconos the iconos to set
     */
    public void setIconos(List<Icono> iconos) {
        this.iconos = iconos;
    }

    public Icono getSelectedIcono() {
        return selectedIcono;
    }

    public void setSelectedIcono(Icono selectedIcono) {
        this.selectedIcono = selectedIcono;
    }
    
     public void btnCreateIcono(ActionEvent actionEvent) {
        IconoDao iconoDao = new IconoDaoImpl();
        String msg;

        if (iconoDao.create(this.selectedIcono)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
     
     
     public void btnUpdateIcono(ActionEvent actionEvent) {
        IconoDao iconoDao = new IconoDaoImpl();
        String msg;

        if (iconoDao.update(this.selectedIcono)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
     
     public void btnDeleteIcono(ActionEvent actionEvent) {
        IconoDao iconoDao = new IconoDaoImpl();
        String msg;
        if (iconoDao.delete(this.selectedIcono.getIdicono())) {
            msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    
}

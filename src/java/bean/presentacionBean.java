/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.PresentacionDao;
import dao.PresentacionDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Presentacion;

/**
 *
 * @author Mario
 */
@ManagedBean (name="presentacionBean")
@RequestScoped
public class presentacionBean {
    
    private List<Presentacion> pres;
    private Presentacion selectedPres;
    private List<SelectItem> selectOneItemsPres;

    /**
     * Creates a new instance of presentacionBean
     */
    public presentacionBean() {
        this.pres = new ArrayList<Presentacion>();
    }
    
    @PostConstruct
    public void init() {
        this.selectedPres = new Presentacion();
    }

    public List<Presentacion> getPres() {
        PresentacionDao preDao = new PresentacionDaoImpl();
        this.pres = preDao.findAll();
        return pres;
    }

    public void setPres(List<Presentacion> pres) {
        this.pres = pres;
    }

    public Presentacion getSelectedPres() {
        return selectedPres;
    }

    public void setSelectedPres(Presentacion selectedPres) {
        this.selectedPres = selectedPres;
    }

    public List<SelectItem> getSelectOneItemsPres() {
        this.selectOneItemsPres = new ArrayList<SelectItem>();
        PresentacionDao preDao = new PresentacionDaoImpl();
        List<Presentacion> us = preDao.selectItems();
        for (Presentacion pre : us) {
            SelectItem selectItem = new SelectItem(pre.getIdPresentacion(), pre.getNombre());
            this.selectOneItemsPres.add(selectItem);
        }
        return selectOneItemsPres;
    }
    
    public void btnCreatePrese(ActionEvent actionEvent) {
        PresentacionDao preDao = new PresentacionDaoImpl();
        String msg;

        if (preDao.create(this.selectedPres)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdatePrese(ActionEvent actionEvent) {
        PresentacionDao preDao = new PresentacionDaoImpl();
        String msg;
        if (preDao.update(this.selectedPres)) {
            msg = "Se modifico correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeletePrese(ActionEvent actionEvent) {
        PresentacionDao preDao = new PresentacionDaoImpl();
        String msg;
        if (preDao.delete(this.selectedPres.getIdPresentacion())) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.MunicipioDao;
import dao.MunicipioDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Municipio;

/**
 *
 * @author Mario
 */
@ManagedBean (name = "municipioBean")
@RequestScoped
public class municipioBean {

    private List<Municipio> municipios;
    private Municipio selectedMunicipio;
    
    /**
     * Creates a new instance of municipioBean
     */
    public municipioBean() {
        this.municipios = new ArrayList<Municipio>();
    }
    
    @PostConstruct
    public void init() {
        this.selectedMunicipio = new Municipio();
    }

    public List<Municipio> getMunicipios() {
        MunicipioDao MmnicipioDao = new MunicipioDaoImpl();
        this.municipios = MmnicipioDao.findAll();
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public Municipio getSelectedMunicipio() {
        return selectedMunicipio;
    }

    public void setSelectedMunicipio(Municipio selectedMunicipio) {
        this.selectedMunicipio = selectedMunicipio;
    }
 
    
     public void btnCreateMunicipio(ActionEvent actionEvent) {
        MunicipioDao municipioDao = new MunicipioDaoImpl();
        String msg;

        if (municipioDao.create(this.selectedMunicipio)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    
    public void btnUpdateMunicipio(ActionEvent actionEvent) {
        MunicipioDao municipioDao = new MunicipioDaoImpl();
        String msg;

        if (municipioDao.update(this.selectedMunicipio)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    
    public void btnDeleteMuni(ActionEvent actionEvent) {
        MunicipioDao municipioDao = new MunicipioDaoImpl();
        String msg;
        if (municipioDao.delete(this.selectedMunicipio.getIdMunicipio())) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.PaisDao;
import dao.PaisDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Pais;

/**
 *
 * @author Mario
 */
@ManagedBean (name = "paisBean")
@RequestScoped
public class paisBean {

    private List<Pais> paises;
    private Pais selectedPais;
    /**
     * Creates a new instance of paisBean
     */
    public paisBean() {
        this.paises = new ArrayList<Pais>();
    }
    
    @PostConstruct
    public void init() {
        this.selectedPais = new Pais();
    }

    public List<Pais> getPaises() {
        PaisDao paisDao = new PaisDaoImpl();
        this.paises = paisDao.findAll();
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public Pais getSelectedPais() {
        return selectedPais;
    }

    public void setSelectedPais(Pais selectedPais) {
        this.selectedPais = selectedPais;
    }
    
     public void btnCreatePais(ActionEvent actionEvent) {
        PaisDao paisDao = new PaisDaoImpl();
        String msg;

        if (paisDao.create(this.selectedPais)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
     
      public void btnUpdatePais(ActionEvent actionEvent) {
        PaisDao paisDao = new PaisDaoImpl();
        String msg;
        if (paisDao.update(this.selectedPais)) {
            msg = "Se modifico correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
      
       public void btnDeletePais(ActionEvent actionEvent) {
        PaisDao paisDao = new PaisDaoImpl();
        String msg;
        if (paisDao.delete(this.selectedPais.getIdpais())) {
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

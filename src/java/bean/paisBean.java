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
    
     public void btnCreateUsuario(ActionEvent actionEvent) {
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
    
}

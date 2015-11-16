/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.DepartamentoDao;
import dao.DepartamentoDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Departamento;

/**
 *
 * @author Mario
 */
@ManagedBean (name = "departamentoBean")
@RequestScoped
public class departamentoBean {
    
    private List<Departamento> departamentos;
    private Departamento selectedDptos;

    /**
     * Creates a new instance of departamentoBean
     */
    public departamentoBean() {
        this.departamentos = new ArrayList<Departamento>();
    }
    
      @PostConstruct
    public void init() {
        this.selectedDptos = new Departamento();
    }

    public List<Departamento> getDepartamentos() {
        DepartamentoDao deptoDao = new DepartamentoDaoImpl();
        this.departamentos = deptoDao.findAll();
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public Departamento getSelectedDptos() {
        return selectedDptos;
    }

    public void setSelectedDptos(Departamento selectedDptos) {
        this.selectedDptos = selectedDptos;
    }
    
    public void btnCreateDepartamento(ActionEvent actionEvent) {
        DepartamentoDao departamentoDao = new DepartamentoDaoImpl();
        String msg;

        if (departamentoDao.create(this.selectedDptos)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    
    public void btnUpdateDepartamento(ActionEvent actionEvent) {
        DepartamentoDao departamentoDao = new DepartamentoDaoImpl();
        String msg;

        if (departamentoDao.update(this.selectedDptos)) {
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

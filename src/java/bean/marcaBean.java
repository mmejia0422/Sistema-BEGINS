/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.MarcaDao;
import dao.MarcaDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Marca;

/**
 *
 * @author Mario
 */
@ManagedBean (name= "marcaBean")
@RequestScoped
public class marcaBean {

    /**
     * Creates a new instance of marcaBean
     */
    private List<Marca> marcas;
    private Marca selectedMarca;
    private List<SelectItem> selectOneItemsMarca;
    
    public marcaBean() {
        this.marcas = new ArrayList<Marca>();
    }
    
    @PostConstruct
    public void init(){
        this.selectedMarca = new Marca();
    }

    public List<Marca> getMarcas() {
        MarcaDao marcaDao = new MarcaDaoImpl();
        this.marcas = marcaDao.findAll();
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public Marca getSelectedMarca() {
        return selectedMarca;
    }

    public void setSelectedMarca(Marca selectedMarca) {
        this.selectedMarca = selectedMarca;
    }

    public List<SelectItem> getSelectOneItemsMarca() {
        this.selectOneItemsMarca = new ArrayList<SelectItem>();
        MarcaDao marcaDao = new MarcaDaoImpl();
        List<Marca> us = marcaDao.selectItems();
        for (Marca marc : us) {
            SelectItem selectItem = new SelectItem(marc.getIdMarca(), marc.getNombre());
            this.selectOneItemsMarca.add(selectItem);
        }
        return selectOneItemsMarca;
    }
    
     public void btnCreateMarca(ActionEvent actionEvent) {
        MarcaDao marcaDao = new MarcaDaoImpl();
        String msg;

        if (marcaDao.create(this.selectedMarca)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateMarca(ActionEvent actionEvent) {
        MarcaDao marcaDao = new MarcaDaoImpl();
        String msg;
        if (marcaDao.update(this.selectedMarca)) {
            msg = "Se modifico correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteMarca(ActionEvent actionEvent) {
        MarcaDao marcaDao = new MarcaDaoImpl();
        String msg;
        //if (usuarioDao.delete(this.selectedUsuario)) {
        if (marcaDao.delete(this.selectedMarca.getIdMarca())) {
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

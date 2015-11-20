/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ProveedorDao;
import dao.ProveedorDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Proveedor;

/**
 *
 * @author Mario
 */
@ManagedBean
@RequestScoped
public class proveedorBean {

    /**
     * Creates a new instance of proveedorBean
     */
    
    private List<Proveedor> proveedor;
    private Proveedor selectedProveedor;
    
    public proveedorBean() {
        this.proveedor = new ArrayList<Proveedor>();
    }
    
     @PostConstruct
    public void init() {
        this.selectedProveedor = new Proveedor();
    }

    public List<Proveedor> getProveedor() {
        ProveedorDao proveedorDao = new ProveedorDaoImpl();
        this.proveedor = proveedorDao.findAll();
        return proveedor;
    }

    public void setProveedor(List<Proveedor> proveedor) {
        this.proveedor = proveedor;
    }

    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }
    
    public void btnCreateProv(ActionEvent actionEvent) {
        ProveedorDao proveedorDao = new ProveedorDaoImpl();
        String msg;

        if (proveedorDao.create(this.selectedProveedor)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateProv(ActionEvent actionEvent) {
        ProveedorDao proveedorDao = new ProveedorDaoImpl();
        String msg;
        if (proveedorDao.update(this.selectedProveedor)) {
            msg = "Se modifico correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteProv(ActionEvent actionEvent) {
        ProveedorDao proveedorDao = new ProveedorDaoImpl();
        String msg;
        //if (usuarioDao.delete(this.selectedUsuario)) {
        if (proveedorDao.delete(this.selectedProveedor.getIdProveedor())) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CateProductoDao;
import dao.CateProductoDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.CatProducto;

/**
 *
 * @author Mario
 */
@ManagedBean (name="cateProductoBean")
@RequestScoped
public class cateProductoBean {

    /**
     * Creates a new instance of cateProductoBean
     */
    
    private List<CatProducto> catProductos;
    private CatProducto selectedCatProducto;
    
    public cateProductoBean() {
        this.catProductos = new ArrayList<CatProducto>();
    }
    
     @PostConstruct
    public void init() {
        this.selectedCatProducto = new CatProducto();
    }

    public List<CatProducto> getCatProductos() {
        CateProductoDao catProDao = new CateProductoDaoImpl();
        this.catProductos = catProDao.findAll();
        return catProductos;
    }

    public void setCatProductos(List<CatProducto> catProductos) {
        this.catProductos = catProductos;
    }

    public CatProducto getSelectedCatProducto() {
        return selectedCatProducto;
    }

    public void setSelectedCatProducto(CatProducto selectedCatProducto) {
        this.selectedCatProducto = selectedCatProducto;
    }
    
    
      public void btnCreateCatPro(ActionEvent actionEvent) {
        CateProductoDao catProDao = new CateProductoDaoImpl();
        String msg;

        if (catProDao.create(this.selectedCatProducto)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateCatPro(ActionEvent actionEvent) {
        CateProductoDao catProDao = new CateProductoDaoImpl();
        String msg;
        if (catProDao.update(this.selectedCatProducto)) {
            msg = "Se modifico correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteCatPro(ActionEvent actionEvent) {
        CateProductoDao catProDao = new CateProductoDaoImpl();
        String msg;
        if (catProDao.delete(this.selectedCatProducto.getIdCatProducto())) {
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

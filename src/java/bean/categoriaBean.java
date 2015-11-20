/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Categoria;

/**
 *
 * @author Mario
 */
@ManagedBean (name ="categoriaBean")
@RequestScoped 
public class categoriaBean {
    
    private List<Categoria> categorias;
    private Categoria selectedCategoria;
    private List<SelectItem> selectOneItemsCategoria;

    /**
     * Creates a new instance of categoriaBean
     */
    public categoriaBean() {
        this.categorias = new ArrayList<Categoria>();
    }
    
    @PostConstruct
    public void init() {
        this.selectedCategoria = new Categoria();
    }

    public List<Categoria> getCategorias() {
         CategoriaDao categoriaDao = new CategoriaDaoImpl();
        this.categorias = categoriaDao.findAll();
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Categoria getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(Categoria selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    public List<SelectItem> getSelectOneItemsCategoria() {
        this.selectOneItemsCategoria = new ArrayList<SelectItem>();
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        List<Categoria> us = categoriaDao.selectItems();
        for (Categoria cate : us) {
            SelectItem selectItem = new SelectItem(cate.getIdCategoria(), cate.getNombre());
            this.selectOneItemsCategoria.add(selectItem);
        }
        return selectOneItemsCategoria;
    }
    
    public void btnCreateCategoria(ActionEvent actionEvent) {
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        String msg;

        if (categoriaDao.create(this.selectedCategoria)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateCategoria(ActionEvent actionEvent) {
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        String msg;
        if (categoriaDao.update(this.selectedCategoria)) {
            msg = "Se modifico correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteCategoria(ActionEvent actionEvent) {
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        String msg;
        //if (usuarioDao.delete(this.selectedUsuario)) {
        if (categoriaDao.delete(this.selectedCategoria.getIdCategoria())) {
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

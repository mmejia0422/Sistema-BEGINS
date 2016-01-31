/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Usuario;

/**
 *
 * @author Mario
 */
@ManagedBean(name = "usuarioBean")
@RequestScoped
public class usuarioBean {

    /**
     * Creates a new instance of usuarioBean
     */
    private List<Usuario> usuarios;
    private Usuario selectedUsuario;
    private List<SelectItem> selectOneItemsUsuario;
    private String usuarioSesion;

    public usuarioBean() {
        this.usuarios = new ArrayList<Usuario>();
        this.selectedUsuario = new Usuario();
    }

    @PostConstruct
    public void init() {
        this.selectedUsuario = new Usuario();
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        this.usuarios = usuarioDao.findAll();
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the selectedUsuario
     */
    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    /**
     * @param selectedUsuario the selectedUsuario to set
     */
    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public void btnCreateUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;

        if (usuarioDao.create(this.selectedUsuario)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        if (usuarioDao.update(this.selectedUsuario)) {
            msg = "Se modifico correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        //if (usuarioDao.delete(this.selectedUsuario)) {
        if (usuarioDao.delete(this.selectedUsuario.getIdUsuario())) {
            msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public List<SelectItem> getSelectOneItemsUsuario() {
        this.selectOneItemsUsuario = new ArrayList<SelectItem>();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        List<Usuario> us = usuarioDao.selectItems();
        for (Usuario usuario : us) {
            SelectItem selectItem = new SelectItem(usuario.getIdUsuario(), usuario.getUsuario());
            this.selectOneItemsUsuario.add(selectItem);
        }
        return selectOneItemsUsuario;
    }

    public String getUsuarioSesion() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        usuarioSesion = us.getUsuario();
        return usuarioSesion;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.RolDao;
import dao.RolDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Rol;

/**
 *
 * @author Mario
 */
@ManagedBean(name = "rolBean")
@RequestScoped
public class rolBean {

    private List<SelectItem> selectOneItemsRol;
    private List<Rol> roles;
    private Rol selectedRol;

    public rolBean() {
        this.roles = new ArrayList<Rol>();
    }

    @PostConstruct
    public void init() {
        this.selectedRol = new Rol();
    }

    /**
     * @return the selectOneItemsRol
     */
    public List<SelectItem> getSelectOneItemsRol() {
        this.selectOneItemsRol = new ArrayList<SelectItem>();
        RolDao rolDao = new RolDaoImpl();
        List<Rol> roles = rolDao.selectItems();
        for (Rol rol : roles) {
            SelectItem selectItem = new SelectItem(rol.getIdRol(), rol.getDescripcion());
            this.selectOneItemsRol.add(selectItem);
        }
        return selectOneItemsRol;
    }

    /**
     * @return the roles
     */
    public List<Rol> getRoles() {
        //put the code similar to:
        RolDao rolDao = new RolDaoImpl();
        this.roles = rolDao.findAll();
        //which is on usuarioBean
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Rol getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

    public void btnCreateUsuario(ActionEvent actionEvent) {
        RolDao rolDao = new RolDaoImpl();
        String msg;

        if (rolDao.create(this.selectedRol)) {
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

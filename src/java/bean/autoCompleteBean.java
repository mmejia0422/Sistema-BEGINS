/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.SubMenuDao;
import dao.SubMenuDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Submenu;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Mario
 */
@ManagedBean(name = "autoCompleteBean")
@ViewScoped

public class autoCompleteBean {

    private List<Submenu> sbMenuTarget;
    private List<Submenu> sbMenuSource;
    private DualListModel<String> pickSbMenu;
    private Boolean mostrar;
    private Integer sessionId;

    public autoCompleteBean() {
    }

    @PostConstruct
    public void init() {
        this.mostrar = false;
        
        this.pickSbMenu = new DualListModel<String>();

        this.sessionId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idMenu");

        if (this.sessionId != null) {
            List<String> sbSource = new ArrayList<String>();
            List<String> sbTarget = new ArrayList<String>();
            SubMenuDao sbMenu = new SubMenuDaoImpl();

            sbMenuTarget = new ArrayList<Submenu>();
            sbMenuSource = new ArrayList<Submenu>();

            this.sbMenuTarget = sbMenu.manageSubMenuTarget(this.sessionId);
            this.sbMenuSource = sbMenu.manageSubMenuSource();

            if (this.sbMenuTarget.size() > 0) {
                for (int i = 0; i <= this.sbMenuTarget.size() - 1; i++) {
                    sbTarget.add(this.sbMenuTarget.get(i).getNombreSubmenu());
                }
            }

            if (this.sbMenuSource.size() > 0) {
                for (int j = 0; j <= this.sbMenuSource.size() - 1; j++) {
                    sbSource.add(this.sbMenuSource.get(j).getNombreSubmenu());
                }
            }

            this.pickSbMenu = new DualListModel<String>(sbSource, sbTarget);
        }
    }

    public void llenarPicklist() {

        this.pickSbMenu = new DualListModel<String>();

        this.sessionId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idMenu");

        if (this.sessionId != null) {
            List<String> sbSource = new ArrayList<String>();
            List<String> sbTarget = new ArrayList<String>();
            SubMenuDao sbMenu = new SubMenuDaoImpl();

            sbMenuTarget = new ArrayList<Submenu>();
            sbMenuSource = new ArrayList<Submenu>();

            this.sbMenuTarget = sbMenu.manageSubMenuTarget(this.sessionId);
            this.sbMenuSource = sbMenu.manageSubMenuSource();

            if (this.sbMenuTarget.size() > 0) {
                for (int i = 0; i <= this.sbMenuTarget.size() - 1; i++) {
                    sbTarget.add(this.sbMenuTarget.get(i).getNombreSubmenu());
                }
            }

            if (this.sbMenuSource.size() > 0) {
                for (int j = 0; j <= this.sbMenuSource.size() - 1; j++) {
                    sbSource.add(this.sbMenuSource.get(j).getNombreSubmenu());
                }
            }

            this.pickSbMenu = new DualListModel<String>(sbSource, sbTarget);
        }
    }

    public DualListModel<String> actualizarPicklist(Integer id) {

        DualListModel<String> updatePL = new DualListModel<String>();

        List<String> sbSource = new ArrayList<String>();
        List<String> sbTarget = new ArrayList<String>();
        SubMenuDao sbMenu = new SubMenuDaoImpl();

        sbMenuTarget = new ArrayList<Submenu>();
        sbMenuSource = new ArrayList<Submenu>();

        this.sbMenuTarget = sbMenu.manageSubMenuTarget(id);
        this.sbMenuSource = sbMenu.manageSubMenuSource();

        if (this.sbMenuTarget.size() > 0) {
            for (int i = 0; i <= this.sbMenuTarget.size() - 1; i++) {
                sbTarget.add(this.sbMenuTarget.get(i).getNombreSubmenu());
            }
        }

        if (this.sbMenuSource.size() > 0) {
            for (int j = 0; j <= this.sbMenuSource.size() - 1; j++) {
                sbSource.add(this.sbMenuSource.get(j).getNombreSubmenu());
            }
        }

        updatePL = new DualListModel<String>(sbSource, sbTarget);

        return updatePL;

    }

    public void mostrarForma() {
        if (this.mostrar != true) {
            this.mostrar = true;
        } else {
            this.mostrar = false;
        }
    }

    public DualListModel<String> getPickSbMenu() {
        return pickSbMenu;
    }

    public void setPickSbMenu(DualListModel<String> pickSbMenu) {
        this.pickSbMenu = pickSbMenu;
    }

    public Boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(Boolean mostrar) {
        this.mostrar = mostrar;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

}

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
import javax.faces.bean.ViewScoped;
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
    
    public autoCompleteBean() {
    }
    
    @PostConstruct
    public void init() {
    this.mostrar = false;
    }

    public DualListModel<String> llenarPicklist(Integer id) {
        this.pickSbMenu = new DualListModel<String>();

        if (id != null) {
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

            this.pickSbMenu = new DualListModel<String>(sbSource, sbTarget);

        }

        return this.pickSbMenu;
    }

    public void mostrarForma() {
        if (this.mostrar != true){
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

}

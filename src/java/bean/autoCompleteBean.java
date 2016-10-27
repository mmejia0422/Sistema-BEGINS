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
import util.MyUtil;

/**
 *
 * @author Mario
 */
@ManagedBean(name = "autoCompleteBean")
@RequestScoped

public class autoCompleteBean {

    private List<Submenu> sbMenuTarget;
    private List<Submenu> sbMenuSource;
    private DualListModel<String> pickSbMenu;
    private Boolean mostrar;
    private Boolean mostrarPL;
    private Boolean mostrarDlte;
    private Integer sessionId;
    private List<Submenu> listaSubMenusDlte;
    private List<Submenu> selectedSubMenu; //lista seleccionada para borrar

    public autoCompleteBean() {
    }

    @PostConstruct
    public void init() {
        this.mostrar = false;
        this.mostrarPL = false;
        this.mostrarDlte = false;
        
        //llenado de variable para datatable
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        this.listaSubMenusDlte = subMenuDao.findAll((Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idMenu"));
        
        //llenado de picklist
        
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
    
    public void llenarDataTable(){
        Integer id = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idMenu");
        SubMenuDao subMenuDao = new SubMenuDaoImpl();
        this.listaSubMenusDlte = subMenuDao.findAll(id);
    }

    public String llenarPicklist(Integer id) {

        this.pickSbMenu = new DualListModel<String>();

        
        this.sessionId = id;
        
        if (this.sessionId != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idMenu", this.sessionId);
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
        
        llenarDataTable();
        
        //forward to another page
        return "admonSubMenu";
    }


    public void mostrarForma() {
        if (this.mostrar != true) {
            this.mostrar = true;
        } else {
            this.mostrar = false;
        }
        
        if(this.mostrarPL = true){
            this.mostrarPL = false;
        }
        
        if(this.mostrarDlte = true){
              this.mostrarDlte = false;
          }
        
    }
    
    public void mostrarPickList() {
          if (this.mostrarPL != true) {
            this.mostrarPL = true;
        } else if (this.mostrarPL = true) {
            this.mostrarPL = false;
        }
          
          if(this.mostrar = true){
              this.mostrar = false;
          }
          
          if(this.mostrarDlte = true){
              this.mostrarDlte = false;
          }
    }
    
    public void mostrarDataDlte(){
         if (this.mostrarDlte != true) {
            this.mostrarDlte = true;
        } else if (this.mostrarDlte = true) {
            this.mostrarDlte = false;
        }
         
         if(this.mostrar = true){
              this.mostrar = false;
          }
         
          if(this.mostrarPL = true){
            this.mostrarPL = false;
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

    public Boolean getMostrarPL() {
        return mostrarPL;
    }

    public void setMostrarPL(Boolean mostrarPL) {
        this.mostrarPL = mostrarPL;
    }

    public List<Submenu> getListaSubMenusDlte() {
        return this.listaSubMenusDlte;
    }

    public void setListaSubMenusDlte(List<Submenu> listaSubMenusDlte) {
        this.listaSubMenusDlte = listaSubMenusDlte;
    }

    public List<Submenu> getSelectedSubMenu() {
        return selectedSubMenu;
    }

    public void setSelectedSubMenu(List<Submenu> selectedSubMenu) {
        this.selectedSubMenu = selectedSubMenu;
    }

    public Boolean getMostrarDlte() {
        return mostrarDlte;
    }

    public void setMostrarDlte(Boolean mostrarDlte) {
        this.mostrarDlte = mostrarDlte;
    }

    
    
}

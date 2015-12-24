/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.MenuDao;
import dao.MenuDaoImpl;
import dao.RolDao;
import dao.RolDaoImpl;
import dao.SubMenuDao;
import dao.SubMenuDaoImpl;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Menu;
import model.Rol;
import model.Submenu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Mario
 */
@ManagedBean
@RequestScoped
public class menuBean {
    
    private MenuModel model;
    private List<Menu> menus;
    private List<Submenu> subMenus;
    //private Integer idRol;
    private String usuarioSesion;
    private List<Rol> idRolUs;
    
    public menuBean() { 
        /* The following code is new to make a dynamic menu bar*/
        
         model = new DynamicMenuModel();
         MenuDao menuDao = new MenuDaoImpl();
         this.usuarioSesion = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
         if (this.usuarioSesion != null) { //esta regresando null no recupera el usuario de sesion
         RolDao rd = new RolDaoImpl();
         this.idRolUs = rd.findResp(this.usuarioSesion);
         if  (this.idRolUs != null){
         this.menus = menuDao.findAll(this.idRolUs.get(0).getIdRol());
         
       for (Menu sm : this.menus){
       DefaultSubMenu firstSubmenu = new DefaultSubMenu(sm.getNombre());
       
       //SubMenuDao subMenuDao = new SubMenuDaoImpl();
       //this.subMenus = subMenuDao.findAll();
       
       //for (Submenu sb : this.subMenus) {
       
       //DefaultMenuItem item = new DefaultMenuItem(sb.getNombreSubmenu());
       DefaultMenuItem item = new DefaultMenuItem("External");
       item.setUrl("http://www.primefaces.org");
       item.setIcon("ui-icon-home");
       firstSubmenu.addElement(item);
       model.addElement(firstSubmenu);
       //}
       }
         }
       }
    }
    
    public MenuModel getModel() {
       return model;
   }

    public void save() {
        addMessage("Success", "Data saved");
    }
     
    public void update() {
        addMessage("Success", "Data updated");
    }
     
    public void delete() {
        addMessage("Success", "Data deleted");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}

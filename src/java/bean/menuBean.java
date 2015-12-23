/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.MenuDao;
import dao.MenuDaoImpl;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Menu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
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
    
    public menuBean() { 
        /* The following code is new to make a dynamic menu bar*/
        
         model = new DefaultMenuModel();
       
         MenuDao menuDao = new MenuDaoImpl();
         this.menus = menuDao.findAll();
         
         //recorrer un arreglo para agregar los submenu y menu item
         //Encontrar un tipo que permita recibir un objeto en lugar de un String
       
       DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");
       DefaultMenuItem item = new DefaultMenuItem("External");
       item.setUrl("http://www.primefaces.org");
       item.setIcon("ui-icon-home");
       firstSubmenu.addElement(item);
       model.addElement(firstSubmenu);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.SubMenuDao;
import dao.SubMenuDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Menu;
import model.Submenu;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Mario
 */
@ManagedBean (name = "subMenuBean")
@RequestScoped
public class subMenuBean implements Serializable{

    
    public subMenuBean() {
    }
    
     @PostConstruct
    public void init() {        
    }

    
}

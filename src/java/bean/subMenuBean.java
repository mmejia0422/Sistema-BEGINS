/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Mario
 */
@ManagedBean
@RequestScoped
public class subMenuBean {
    
    public subMenuBean() {
    }
    
    private DualListModel<String> subMenus;
    
     @PostConstruct
    public void init() {
        //Cities
        List<String> sbSource = new ArrayList<String>();
        List<String> sbTarget = new ArrayList<String>();
         
        sbSource.add("San Francisco");
        sbSource.add("London");
        sbSource.add("Paris");
        sbSource.add("Istanbul");
        sbSource.add("Berlin");
        sbSource.add("Barcelona");
        sbSource.add("Rome");
        
        this.subMenus = new DualListModel<String>(sbSource, sbTarget);
        
    }

    public DualListModel<String> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(DualListModel<String> subMenus) {
        this.subMenus = subMenus;
    }
        
    
}

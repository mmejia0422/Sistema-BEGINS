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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Submenu;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Mario
 */
@ManagedBean(name = "autoCompleteBean")
public class autoCompleteBean {

    private List<Submenu> sbMenuTarget;
    private DualListModel<String> pickSbMenu;

    public autoCompleteBean() {
    }

    public DualListModel<String> llenarPicklist(Integer id) {
        this.pickSbMenu = new DualListModel<String>();
        
        if (id != null) {
            List<String> sbSource = new ArrayList<String>();
            List<String> sbTarget = new ArrayList<String>();
            SubMenuDao sbMenu = new SubMenuDaoImpl();

            sbSource.add("San Francisco");
            sbSource.add("London");
            sbSource.add("Paris");
            sbSource.add("Istanbul");
            sbSource.add("Berlin");
            sbSource.add("Barcelona");
            sbSource.add("Rome");

            sbMenuTarget = new ArrayList<Submenu>();

            this.sbMenuTarget = sbMenu.manageSubMenuTarget(id);

            if(this.sbMenuTarget.size() > 0){
            for (int i = 0; i <= this.sbMenuTarget.size() - 1; i++) {
                sbTarget.add(this.sbMenuTarget.get(i).getNombreSubmenu());
            }
            }

            this.pickSbMenu = new DualListModel<String>(sbSource, sbTarget);

        }

        return this.pickSbMenu;
    }

    public DualListModel<String> getPickSbMenu() {
        return pickSbMenu;
    }

    public void setPickSbMenu(DualListModel<String> pickSbMenu) {
        this.pickSbMenu = pickSbMenu;
    }

}

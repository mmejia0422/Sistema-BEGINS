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
import dao.RolMenuDao;
import dao.RolMenuDaoImpl;
import dao.SubMenuDao;
import dao.SubMenuDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Menu;
import model.Rol;
import model.RolMenu;
import model.Submenu;
import model.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Mario
 */
@ManagedBean(name = "menuBean")
@RequestScoped
public class menuBean implements Serializable {

    private List<Menu> menus;
    private List<RolMenu> rolMenus;
    private List<Submenu> subMenus;
    private MenuModel model;
    private Integer RespSesion;
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private List<Menu> listaMenus;
    private Menu selectedMenu;

    public menuBean() {
        this.usuarioDao = new UsuarioDaoImpl();
        this.usuario = new Usuario();
    }

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        this.listarMenus();
        this.selectedMenu = new Menu();
    }

    public void listarMenus() {
        try {
            //The following code is used to build a dynamic menu

            RolMenuDao rolMenuDao = new RolMenuDaoImpl();
            MenuDao menuDao = new MenuDaoImpl();
            SubMenuDao submenuDao = new SubMenuDaoImpl();
            //this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

            usuarioBean userbean = new usuarioBean();
            this.usuario = this.usuarioDao.findUser(userbean.getUsuarioSesion());

            if (this.usuario != null) {
                this.RespSesion = this.usuario.getRol().getIdRol();
                if (this.RespSesion != null) {
                    this.rolMenus = rolMenuDao.findByResp(this.RespSesion);

                    for (int i = 0; i < this.rolMenus.size(); i++) {
                        this.menus = menuDao.findByRolMenu(this.rolMenus.get(i).getMenu().getIdmenu());
                        
                        DefaultSubMenu firstSubmenu = new DefaultSubMenu(this.menus.get(0).getNombre(), this.menus.get(0).getIcono().getReferencia());
                        
                        this.subMenus = submenuDao.findByMenu(this.menus.get(0).getIdmenu());
                        
                        if(this.subMenus.isEmpty()) {
                            DefaultMenuItem item = new DefaultMenuItem(this.menus.get(0).getNombre());

                            item.setUrl(this.menus.get(0).getUrl());
                            item.setIcon(this.menus.get(0).getIcono().getReferencia());
                            
                            this.model.addElement(item);
                            
                        } else{

                        for (int j = 0; j < this.subMenus.size(); j++) {
                            DefaultMenuItem item = new DefaultMenuItem(this.subMenus.get(j).getNombreSubmenu());

                            item.setUrl(this.subMenus.get(j).getUrl());
                            if(this.subMenus.get(j).getIcono() != null) {
                            item.setIcon(this.subMenus.get(j).getIcono().getReferencia());
                            }
                            firstSubmenu.addElement(item);
                        }
                        this.model.addElement(firstSubmenu);
                        }   
                        
                    }
                }
            }
            //Here ends the code for the dynamic menu
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.usuario = null;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Menu> getListaMenus() {
        MenuDao menuDao = new MenuDaoImpl();
        listaMenus = menuDao.findAll();
        return listaMenus;
    }

    public void setListaMenus(List<Menu> listaMenus) {
        this.listaMenus = listaMenus;
    }

    //the following code is to give maintenance to menus
    
    public void btnCreate(ActionEvent actionEvent) {
        MenuDao menuDao = new MenuDaoImpl();
        String msg;

        if (menuDao.create(this.selectedMenu)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    
    //Here ends the code for maintenance

    public Menu getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(Menu selectedMenu) {
        this.selectedMenu = selectedMenu;
    }
    
}

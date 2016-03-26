/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.MenuDao;
import dao.MenuDaoImpl;
import dao.RolMenuDao;
import dao.RolMenuDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.RolMenu;
import model.Submenu;
import model.Usuario;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuModel;
import util.MyUtil;

/**
 *
 * @author Mario
 */
//@Named(value="loginBean")
@ManagedBean(name = "loginBean")
@SessionScoped

public class loginBean implements Serializable {

    private Usuario usuario;
    private UsuarioDao usuarioDao;  
    private Integer progress;
    //private String previousPage = null;

    public loginBean() {
        this.usuarioDao = new UsuarioDaoImpl();
        if (this.usuario == null) {
         this.usuario = new Usuario();
         }
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usuarioDao
     */
    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    /**
     * @param usuarioDao the usuarioDao to set
     */
    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn;
        String ruta = "";
        
        this.usuario = this.usuarioDao.login(this.usuario);

        if (this.usuario != null) {

            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getUsuario());
            ruta = MyUtil.basepathlogin() + "views/inicio.xhtml";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Usuario y/o clave incorrecta");
            if (this.usuario == null) {
                this.usuario = new Usuario();
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    }
    
     public Integer getProgress() {
                  
        if(progress == null) {
            progress = 0;
        }
        else {
            progress = progress + (int)(Math.random() * 35);
             
            if(progress > 100)
                progress = 100;
          }
         
        return progress;
    }
     
     /*public void cancelProgress(){
         this.progress = null;
     }*/
     
  
 //Revisando si se refresca la pagina
  /*public void checkF5() {
    String msg = "";
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		String id = viewRoot.getViewId();
		if (previousPage == null) {
			//msg = "First page ever";
                    //cancelProgress();
		} else if (previousPage.equals(id)) {
			msg = "F5 or reload";
                    cancelProgress();
		} else if (FacesContext.getCurrentInstance().isPostback()) {
			msg = "It's a postback";
                    cancelProgress();
		} else
			msg = "It's a navigation";
                    cancelProgress();
                
		previousPage = id;
		FacesMessage fm = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage(null, fm);
  }*/
     
     public void setProgress(Integer progress) {
        this.progress = progress;
    }

    /*public void logout() {
        String ruta = MyUtil.basepathlogin() + "login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();

        context.addCallbackParam("loggedOut", true);
        context.addCallbackParam("ruta", ruta);
    }*/

}

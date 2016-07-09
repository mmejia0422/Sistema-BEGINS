package model;
// Generated 07-08-2016 05:51:52 PM by Hibernate Tools 4.3.1



/**
 * Submenu generated by hbm2java
 */
public class Submenu  implements java.io.Serializable {


     private Integer idsubmenu;
     private Icono icono;
     private Menu menu;
     private String nombreSubmenu;
     private String estado;
     private String url;

    public Submenu() {
    }

	
    public Submenu(Menu menu, String nombreSubmenu, String estado, String url) {
        this.menu = menu;
        this.nombreSubmenu = nombreSubmenu;
        this.estado = estado;
        this.url = url;
    }
    public Submenu(Icono icono, Menu menu, String nombreSubmenu, String estado, String url) {
       this.icono = icono;
       this.menu = menu;
       this.nombreSubmenu = nombreSubmenu;
       this.estado = estado;
       this.url = url;
    }
   
    public Integer getIdsubmenu() {
        return this.idsubmenu;
    }
    
    public void setIdsubmenu(Integer idsubmenu) {
        this.idsubmenu = idsubmenu;
    }
    public Icono getIcono() {
        return this.icono;
    }
    
    public void setIcono(Icono icono) {
        this.icono = icono;
    }
    public Menu getMenu() {
        return this.menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public String getNombreSubmenu() {
        return this.nombreSubmenu;
    }
    
    public void setNombreSubmenu(String nombreSubmenu) {
        this.nombreSubmenu = nombreSubmenu;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }




}



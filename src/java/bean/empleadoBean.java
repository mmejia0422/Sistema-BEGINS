/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.EmpleadoDao;
import dao.EmpleadoDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Empleado;

/**
 *
 * @author Mario
 */
@ManagedBean (name ="empleadoBean")
@RequestScoped
public class empleadoBean {

    /**
     * Creates a new instance of empleadoBean
     */
    
    private List<Empleado> empleados;
    private Empleado selectedEmpleados;
    private List<SelectItem> selectOneItemsEmp;
    
    public empleadoBean() {
        this.empleados = new ArrayList<Empleado>();
    }
    
    @PostConstruct
    public void init() {
        this.selectedEmpleados = new Empleado();
    }

    public List<Empleado> getEmpleados() {
        EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
        this.empleados = empleadoDao.findAll();
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Empleado getSelectedEmpleados() {
        return selectedEmpleados;
    }

    public void setSelectedEmpleados(Empleado selectedEmpleados) {
        this.selectedEmpleados = selectedEmpleados;
    }
    
    
     public void btnCreateEmpleado(ActionEvent actionEvent) {
        EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
        String msg;

        if (empleadoDao.create(this.selectedEmpleados)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
     
      public void btnUpdateEmpleado(ActionEvent actionEvent) {
        EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
        String msg;
        if (empleadoDao.update(this.selectedEmpleados)) {
            msg = "Se modifico correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
      
       public void btnDeleteEmpleado(ActionEvent actionEvent) {
        EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
        String msg;
        if (empleadoDao.delete(this.selectedEmpleados.getIdEmpleado())) {
            msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public List<SelectItem> getSelectOneItemsEmp() {
        this.selectOneItemsEmp = new ArrayList<SelectItem>();
        EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
        List<Empleado> empleados = empleadoDao.selectItems();
        for (Empleado emp : empleados) {
            SelectItem selectItem = new SelectItem(emp.getIdEmpleado(), emp.getNombre() + " " + emp.getApellido());
            this.selectOneItemsEmp.add(selectItem);
        }
        return selectOneItemsEmp;
    }
    
}

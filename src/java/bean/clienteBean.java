/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ClienteDao;
import dao.ClienteDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Cliente;

/**
 *
 * @author Mario
 */
@ManagedBean (name = "clienteBean")
@RequestScoped
public class clienteBean {

    private List<Cliente> clientes;
    private Cliente selectedCliente;
    
    /**
     * Creates a new instance of clienteBean
     */
    public clienteBean() {
        this.clientes = new ArrayList<Cliente>();
    }
    
    @PostConstruct
    public void init(){
        this.selectedCliente = new Cliente();
    }

    public List<Cliente> getClientes() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        this.clientes = clienteDao.findAll();
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }
    
     public void btnCreateCliente(ActionEvent actionEvent) {
        ClienteDao clienteDao = new ClienteDaoImpl();
        String msg;

        if (clienteDao.create(this.selectedCliente)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    
    public void btnUpdateCliente(ActionEvent actionEvent) {
        ClienteDao clienteDao = new ClienteDaoImpl();
        String msg;

        if (clienteDao.update(this.selectedCliente)) {
            msg = "Se guardo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    
    public void btnDeleteCliente(ActionEvent actionEvent) {
        ClienteDao clienteDao = new ClienteDaoImpl();
        String msg;
        if (clienteDao.delete(this.selectedCliente.getIdCliente())) {
            msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
}

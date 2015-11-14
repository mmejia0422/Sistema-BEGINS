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
import model.Departamento;

/**
 *
 * @author Mario
 */
@ManagedBean (name = "departamentoBean")
@RequestScoped
public class departamentoBean {
    
    private List<Departamento> departamentos;
    private Departamento selectedDptos;

    /**
     * Creates a new instance of departamentoBean
     */
    public departamentoBean() {
        this.departamentos = new ArrayList<Departamento>();
    }
    
      @PostConstruct
    public void init() {
        this.selectedDptos = new Departamento();
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public Departamento getSelectedDptos() {
        return selectedDptos;
    }

    public void setSelectedDptos(Departamento selectedDptos) {
        this.selectedDptos = selectedDptos;
    }
    
    
}

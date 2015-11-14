/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Mario
 */

@Entity
@Table(name="departamento"
    ,catalog="gasisw_bd"
)

public class Departamento implements java.io.Serializable{
    private int idDpto;
    private String nombre;
    private Pais pais;
    
    public Departamento (){
        this.idDpto = 0;
    }

    public Departamento(int idDpto, String nombre, Pais pais) {
        this.idDpto = idDpto;
        this.nombre = nombre;
        this.pais = pais;
    }

    @Id 
    @Column(name="id_depto", unique=true, nullable=false)
    public int getIdDpto() {
        return idDpto;
    }

    public void setIdDpto(int idDpto) {
        this.idDpto = idDpto;
    }

    @Column(name="nombre", length=50, nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pais_id_pais", nullable=false)

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/*import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;*7

/**
 *
 * @author Mario
 */
/*@Entity
@Table(name="pais"
    ,catalog="gasisw_bd"
)*/

public class Pais implements java.io.Serializable{
    private int idpais;
    private String nombre;
    private int codigopostal;

    public Pais() {
        this.idpais = 0;
    }
    
    public Pais(int idpais, String nombre, int codigopostal) {
        this.idpais = idpais;
        this.nombre = nombre;
        this.codigopostal = codigopostal;
    }
   // @Id 

    //@Column(name="id_pais", unique=true, nullable=false)
    public int getIdpais() {
        return idpais;
    }

    public void setIdpais(int idpais) {
        this.idpais = idpais;
    }

    //@Column(name="nombre", nullable=false, length=50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //@Column(name="codigo_postal", nullable=false)
    public int getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(int codigopostal) {
        this.codigopostal = codigopostal;
    }

}

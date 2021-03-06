package model;
// Generated 11-18-2015 05:01:00 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Municipio generated by hbm2java
 */
@Entity
@Table(name = "municipio", catalog = "gasisw_bd1"
)
public class Municipio implements java.io.Serializable {

    private Integer idMunicipio;
    private Departamento departamento;
    private String nombre;
    private Set clientes = new HashSet(0);
    private Set proveedors = new HashSet(0);

    public Municipio() {
        this.idMunicipio = 0;
        this.departamento = new Departamento();
    }

    @PostConstruct
    public void init() {
        this.idMunicipio = 0;
        this.departamento = new Departamento();
    }

    public Municipio(Departamento departamento, String nombre) {
        this.departamento = departamento;
        this.nombre = nombre;
    }

    public Municipio(Departamento departamento, String nombre, Set clientes, Set proveedors) {
        this.departamento = departamento;
        this.nombre = nombre;
        this.clientes = clientes;
        this.proveedors = proveedors;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id_municipio", unique = true, nullable = false)
    public Integer getIdMunicipio() {
        return this.idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id_depto", nullable = false)
    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Column(name = "nombre", nullable = false, length = 25)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "municipio")
    public Set getClientes() {
        return this.clientes;
    }

    public void setClientes(Set clientes) {
        this.clientes = clientes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "municipio")
    public Set getProveedors() {
        return this.proveedors;
    }

    public void setProveedors(Set proveedors) {
        this.proveedors = proveedors;
    }

}

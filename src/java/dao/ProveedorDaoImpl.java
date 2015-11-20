/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Proveedor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
/**
 *
 * @author Mario
 */
public class ProveedorDaoImpl implements ProveedorDao{

    @Override
    public List<Proveedor> findAll() {
        List<Proveedor> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Proveedor u left join fetch u.municipio";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Proveedor proveedor) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(proveedor);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Proveedor proveedor) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Proveedor proveedordb = (Proveedor) sesion.load(Proveedor.class, proveedor.getIdProveedor());
            proveedordb.setNombre(proveedor.getNombre());
            proveedordb.setDireccion(proveedor.getDireccion());
            proveedordb.setTelefono(proveedor.getTelefono());
            proveedordb.setDescripcion(proveedor.getDescripcion());
            proveedordb.setNit(proveedor.getNit());
            proveedordb.setMunicipio(proveedor.getMunicipio());
            sesion.update(proveedordb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
       boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Query q = sesion.createQuery("from Proveedor where id_proveedor = :id_proveedor");
            q.setParameter("id_proveedor", id);
            Proveedor proveedordb = (Proveedor) q.list().get(0);

            sesion.delete(proveedordb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
            e.printStackTrace();
        }
        return flag;
    }
    
}

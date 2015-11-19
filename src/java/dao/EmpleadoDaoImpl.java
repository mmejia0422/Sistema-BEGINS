/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Empleado;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class EmpleadoDaoImpl implements EmpleadoDao{

    @Override
    public List<Empleado> findAll() {
        List<Empleado> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Empleado";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Empleado empleado) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(empleado);
            tx.commit();
            flag = true;
        }catch (Exception e) {
            flag = false;
            tx.rollback();
            e.printStackTrace();
        }
        
        return flag;
    }

    @Override
    public boolean update(Empleado empleado) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Empleado empleadodb = (Empleado) sesion.load(Empleado.class, empleado.getIdEmpleado());
            empleadodb.setNombre(empleado.getNombre());
            empleadodb.setApellido(empleado.getApellido());
            empleadodb.setDui(empleado.getDui());
            sesion.update(empleadodb);
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
            Query q = sesion.createQuery("from Empleado where id_empleado = :id_empleado");
            q.setParameter("id_empleado", id);
            Empleado empleadodb = (Empleado) q.list().get(0);

            sesion.delete(empleadodb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
            e.printStackTrace();
        }
        return flag;
    }
    
    @Override
    public List<Empleado> selectItems() {
        List<Empleado> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Empleado order by nombre";
        try{
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return listado;
    }
    
}

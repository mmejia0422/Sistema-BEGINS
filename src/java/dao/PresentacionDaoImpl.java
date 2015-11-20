/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Presentacion;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class PresentacionDaoImpl implements PresentacionDao{

    @Override
    public List<Presentacion> findAll() {
        List<Presentacion> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Presentacion";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Presentacion presentacion) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(presentacion);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Presentacion presentacion) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Presentacion presBd = (Presentacion) sesion.load(Presentacion.class, presentacion.getIdPresentacion());
            presBd.setNombre(presentacion.getNombre());
            presBd.setDescripcion(presentacion.getDescripcion());
            sesion.update(presBd);
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
            Query q = sesion.createQuery("from Presentacion where id_presentacion = :id_presentacion");
            q.setParameter("id_presentacion", id);
            Presentacion presBd = (Presentacion) q.list().get(0);

            sesion.delete(presBd);
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
    public List<Presentacion> selectItems() {
       List<Presentacion> listado = null;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.beginTransaction();
        String sql = "FROM Presentacion";
        try{
            listado = s.createQuery(sql).list();
            tx.commit();
        } catch(RuntimeException e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
        return listado;
    }
    
}

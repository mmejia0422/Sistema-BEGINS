/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Rol;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class RolDaoImpl implements RolDao {

    @Override
    public List<Rol> selectItems() {
        List<Rol> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Rol";
        try{
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return listado;
    }

    @Override
    public List<Rol> findAll() {
        List<Rol> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Rol";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Rol rol) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(rol);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
            tx.rollback();
        }
        return flag;
    }
    
}

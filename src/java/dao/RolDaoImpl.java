/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Rol;
import org.hibernate.Query;
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

    @Override
    public boolean update(Rol rol) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Rol roldb = (Rol) sesion.load(Rol.class, rol.getIdRol());
            roldb.setDescripcion(rol.getDescripcion());
            roldb.setEstado(rol.getEstado());
            sesion.update(roldb);
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
            Query q = sesion.createQuery("from Rol where id_rol = :id_rol");
            q.setParameter("id_rol", id);
            Rol roldb = (Rol) q.list().get(0);

            sesion.delete(roldb);
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
    public List<Rol> findResp(String usuario) {
        List<Rol> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Usuario u left join fetch u.rol where u.usuario = '" + usuario + "'";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Menu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class MenuDaoImpl implements MenuDao{

    @Override
    public List<Menu> findByRolMenu(Integer idMenu) {
        List<Menu> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Menu m left join fetch m.icono where idMenu = '" + idMenu + "'";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public List<Menu> findAll() {
         List<Menu> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Menu m left join fetch m.icono order by orden asc";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Menu menu) {
          boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(menu);
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
    public boolean update(Menu menu) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Menu menuBd = (Menu) sesion.load(Menu.class, menu.getIdmenu());
            menuBd.setNombre(menu.getNombre());
            menuBd.setUrl(menu.getUrl());
            menuBd.setEstado(menu.getEstado());
            menuBd.setIcono(menu.getIcono());
            menuBd.setOrden(menu.getOrden());
            tx.commit();
            flag = true;
        }catch(Exception e){
            flag = false;
            tx.rollback();
            e.printStackTrace();
        }
        
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
      boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        
        try {
            Query q = sesion.createQuery("from Menu where idmenu = :idmenu");
            q.setParameter("idmenu", id);
            Menu menuDb = (Menu) q.list().get(0);

            sesion.delete(menuDb);
            tx.commit();
            flag = true;
        }catch(Exception e){
            flag = false;
            tx.rollback();
        }
       return flag;
    }
}

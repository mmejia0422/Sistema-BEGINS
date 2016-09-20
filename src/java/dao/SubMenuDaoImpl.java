/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Submenu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class SubMenuDaoImpl implements SubMenuDao{

    @Override
    public List<Submenu> findByMenu(Integer idMenu) {
         List<Submenu> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Submenu s left join fetch s.icono where s.estado = 'Y' and menu_idmenu = '" + idMenu + "'";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Submenu subMenu) {
       boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(subMenu);
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
    public boolean update(Submenu subMenu) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Submenu subMenuBd = (Submenu) sesion.load(Submenu.class, subMenu.getIdsubmenu());
            subMenuBd.setNombreSubmenu(subMenu.getNombreSubmenu());
            subMenuBd.setEstado(subMenu.getEstado());
            subMenuBd.setUrl(subMenu.getUrl());
            subMenuBd.setIcono(subMenu.getIcono());
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
            Query q = sesion.createQuery("from Submenu where idsubmenu = :idsubmenu");
            q.setParameter("idsubmenu", id);
            Submenu subMenuDb = (Submenu) q.list().get(0);

            sesion.delete(subMenuDb);
            tx.commit();
            flag = true;
        }catch(Exception e){
            flag = false;
            tx.rollback();
        }
       return flag;
    }
    
    @Override
    public List<Submenu> manageSubMenuTarget(Integer idMenu) {
         List<Submenu> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Submenu s left join fetch s.menu where s.estado = 'Y' and menu_idmenu = '" + idMenu + "'";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public List<Submenu> manageSubMenuSource(){
         List<Submenu> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Submenu s left join fetch s.menu where s.estado = 'Y' and menu_idmenu is null";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }
    
}

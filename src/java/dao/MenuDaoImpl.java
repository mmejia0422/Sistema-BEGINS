/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Menu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class MenuDaoImpl implements MenuDao{

    @Override
    public List<Menu> findAll() {
        List<Menu> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Menu";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }
    
}

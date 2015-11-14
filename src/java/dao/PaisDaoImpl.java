/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Pais;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class PaisDaoImpl implements PaisDao {

    @Override
    public List<Pais> findAll() {
         List<Pais> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Pais";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Pais pais) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(pais);
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
    public boolean update(Pais pais) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Pais paisdb = (Pais) sesion.load(Pais.class, pais.getIdpais());
            paisdb.setNombre(pais.getNombre());
            paisdb.setCodigopostal(pais.getCodigopostal());
            sesion.update(paisdb);
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
            Query q = sesion.createQuery("from Pais where id_pais = :id_pais");
            q.setParameter("id_pais", id);
            Pais paisdb = (Pais) q.list().get(0);

            sesion.delete(paisdb);
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

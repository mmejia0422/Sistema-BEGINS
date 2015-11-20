/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Marca;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class MarcaDaoImpl implements MarcaDao {

    @Override
    public List<Marca> findAll() {
       List<Marca> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Marca";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Marca marca) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(marca);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Marca marca) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Marca marcaDb = (Marca) sesion.load(Marca.class, marca.getIdMarca());
            marcaDb.setNombre(marca.getNombre());
            marcaDb.setDescripcion(marca.getDescripcion());;
            sesion.update(marcaDb);
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
            Query q = sesion.createQuery("from Marca where id_marca = :id_marca");
            q.setParameter("id_marca", id);
            Marca marcaDb = (Marca) q.list().get(0);

            sesion.delete(marcaDb);
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
    public List<Marca> selectItems() {
        List<Marca> listado = null;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.beginTransaction();
        String sql = "FROM Marca";
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

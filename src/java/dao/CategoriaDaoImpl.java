/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Categoria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class CategoriaDaoImpl implements CategoriaDao {

    @Override
    public List<Categoria> findAll() {
         List<Categoria> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Categoria";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Categoria categoria) {
       boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(categoria);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Categoria categoria) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Categoria categoriadb = (Categoria) sesion.load(Categoria.class, categoria.getIdCategoria());
            categoriadb.setNombre(categoria.getNombre());
            categoriadb.setDescripcion(categoria.getDescripcion());
            sesion.update(categoriadb);
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
            Query q = sesion.createQuery("from Categoria where id_categoria = :id_categoria");
            q.setParameter("id_categoria", id);
            Categoria categoriadb = (Categoria) q.list().get(0);

            sesion.delete(categoriadb);
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
    public List<Categoria> selectItems() {
        List<Categoria> listado = null;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.beginTransaction();
        String sql = "FROM Categoria";
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

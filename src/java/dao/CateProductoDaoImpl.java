/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.CatProducto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class CateProductoDaoImpl implements CateProductoDao{

    @Override
    public List<CatProducto> findAll() {
      List<CatProducto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM CatProducto u left join fetch u.marca left join fetch u.categoria left join fetch u.presentacion";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(CatProducto catPro) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(catPro);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(CatProducto catPro) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            CatProducto catProDb = (CatProducto) sesion.load(CatProducto.class, catPro.getIdCatProducto());
            catProDb.setNombre(catPro.getNombre());
            catProDb.setDescripcion(catPro.getDescripcion());
            catProDb.setPrecio(catPro.getPrecio());
            catProDb.setMarca(catPro.getMarca());
            catProDb.setCategoria(catPro.getCategoria());
            catProDb.setPresentacion(catPro.getPresentacion());
            sesion.update(catProDb);
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
            Query q = sesion.createQuery("from CatProducto where id_cat_producto = :id_cat_producto");
            q.setParameter("id_cat_producto", id);
            CatProducto catProDb = (CatProducto) q.list().get(0);

            sesion.delete(catProDb);
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
    public List<CatProducto> selectItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

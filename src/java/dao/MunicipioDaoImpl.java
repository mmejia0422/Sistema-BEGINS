/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Municipio;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class MunicipioDaoImpl implements MunicipioDao{

    @Override
    public List<Municipio> findAll() {
         List<Municipio> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Municipio m left join fetch m.departamento dep left join fetch dep.pais";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Municipio municipio) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(municipio);
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
    public boolean update(Municipio municipio) {
          boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Municipio municipioBd = (Municipio) sesion.load(Municipio.class, municipio.getIdMunicipio());
            municipioBd.setNombre(municipio.getNombre());
            municipioBd.setDepartamento(municipio.getDepartamento());
            sesion.update(municipioBd);
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
            Query q = sesion.createQuery("from Municipio where id_municipio = :id_municipio");
            q.setParameter("id_municipio", id);
            Municipio municipioDb = (Municipio) q.list().get(0);

            sesion.delete(municipioDb);
            tx.commit();
            flag = true;
        }catch(Exception e){
            flag = false;
        }
    return flag;
    }

    @Override
    public List<Municipio> selectItems() {
       List<Municipio> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Municipio";
        try{
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return listado;
    }
    
}

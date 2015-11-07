/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Usuario;
import model.UsuarioId;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public Usuario findByUsuario(Usuario usuario) {
        Usuario model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Usuario WHERE usuario = '" + usuario.getUsuario() + "'";
        try {
            //sesion.beginTransaction();
            model = (Usuario) sesion.createQuery(sql).uniqueResult();
            //sesion.beginTransaction().commit();
            tx.commit();
        } catch (Exception e) {
            //sesion.beginTransaction().rollback();
            tx.rollback();
        }
        return model;
    }

    @Override
    public Usuario login(Usuario usuario) {
        Usuario model = this.findByUsuario(usuario);
        if (model != null) {
            if (!usuario.getContrasena().equals(model.getContrasena())) {
                model = null;
            }
        }

        return model;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Usuario u left join fetch u.rol";
        try {
            //sesion.beginTransaction();
            listado = sesion.createQuery(sql).list();
            //sesion.beginTransaction().commit();
            tx.commit();
        } catch (Exception e) {
            //sesion.beginTransaction().rollback();
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Usuario usuario) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(usuario);
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
    public boolean update(Usuario usuario) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Usuario usuariodb = (Usuario) sesion.load(Usuario.class, usuario.getId());
            usuariodb.setUsuario(usuario.getUsuario());
            usuariodb.setContrasena(usuario.getContrasena());
            usuariodb.setRol(usuario.getRol());
            usuariodb.setEstado(usuario.getEstado());
            sesion.update(usuariodb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    //public boolean delete(Usuario usuario) {
    public boolean delete(Integer id) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            //UsuarioId usuariodb = (UsuarioId) sesion.load(UsuarioId.class, id); //posiblemente al hacer el cast a Usuario da error debe de ser al UsuarioId
            //Usuario usuariodb = (Usuario) sesion.load(Usuario.class, usuario.getId());
            Query q = sesion.createQuery("from Usuario where id_usuario = :id_usuario");
            q.setParameter("id_usuario", id);
            Usuario usuariodb = (Usuario) q.list().get(0);

            sesion.delete(usuariodb);
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

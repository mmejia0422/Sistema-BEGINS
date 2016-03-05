/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.faces.context.FacesContext;
import model.Usuario;
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
        String sql = "FROM Usuario u left join fetch u.rol WHERE u.estado = 'Y' and u.usuario = '" + usuario.getUsuario() + "'";
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
        String sql = "FROM Usuario u left join fetch u.rol left join fetch u.empleado";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
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
            Usuario usuariodb = (Usuario) sesion.load(Usuario.class, usuario.getIdUsuario());
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
    public boolean delete(Integer id) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
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

    @Override
    public List<Usuario> selectItems() {
         List<Usuario> listado = null;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.beginTransaction();
        String sql = "FROM Usuario";
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

    @Override
    public Usuario findUser(String usuarioSesion) {
        Usuario model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Usuario u left join fetch u.rol WHERE u.estado = 'Y' and u.usuario = '" + usuarioSesion + "'";
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

}

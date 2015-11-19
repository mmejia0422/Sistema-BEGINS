/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class ClienteDaoImpl implements ClienteDao{

    @Override
    public List<Cliente> findAll() {
       List<Cliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM Cliente c left join fetch c.municipio";
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Cliente cliente) {
       boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(cliente);
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
    public boolean update(Cliente cliente) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Cliente clienteBd = (Cliente) sesion.load(Cliente.class, cliente.getIdCliente());
            clienteBd.setNombre(cliente.getNombre());
            clienteBd.setApellido(cliente.getApellido());
            clienteBd.setDui(cliente.getDui());
            clienteBd.setNit(cliente.getNit());
            clienteBd.setTelefono(cliente.getTelefono());
            clienteBd.setMunicipio(cliente.getMunicipio());
            sesion.update(clienteBd);
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
            Query q = sesion.createQuery("from Cliente where id_cliente = :id_cliente");
            q.setParameter("id_cliente", id);
            Cliente clienteDb = (Cliente) q.list().get(0);

            sesion.delete(clienteDb);
            tx.commit();
            flag = true;
        }catch(Exception e){
            flag = false;
            tx.rollback();
        }
    return flag;
    }
    
}

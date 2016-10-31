/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.LecturaSensor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class LecturaSensorDaoImpl implements LecturaSensorDao {

    @Override
    public List<LecturaSensor> fillDashboard() {
        List<LecturaSensor> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String hql = "from LecturaSensor order by idlectura_sensor desc";
        try {
            Query sql = sesion.createQuery(hql);
            sql.setFirstResult(0);
            sql.setMaxResults(7);
            lista = sql.list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return lista;
    }
    
}

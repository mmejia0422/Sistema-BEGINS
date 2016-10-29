/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.GeneralParameters;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Mario
 */
public class ParamDaoImpl implements ParamDao{
    
    @Override
    public GeneralParameters findCompName(String codigo) {
        GeneralParameters model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        String sql = "FROM GeneralParameters where lookupCode = '" + codigo + "'";
        try {
            model = (GeneralParameters) sesion.createQuery(sql).uniqueResult();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return model;
    }
}

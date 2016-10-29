/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ParamDao;
import dao.ParamDaoImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.GeneralParameters;

/**
 *
 * @author Mario
 */
@ManagedBean
@RequestScoped
public class paramBean {

    public paramBean() {
    }
    
    public String companyName(){
        GeneralParameters company;
        ParamDao param = new ParamDaoImpl();
        company = param.findCompName("company_name");
        
        return company.getMeaning();
    }
}

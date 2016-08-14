/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.multimedia;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Mario
 */
@ManagedBean (name ="imagenBean")
public class imagenBean {

    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            images.add("galeria" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}

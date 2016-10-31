/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.LecturaSensorDao;
import dao.LecturaSensorDaoImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import model.LecturaSensor;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author Mario
 */
@ManagedBean(name = "dashboardBean")
@ViewScoped
public class dashboardBean implements Serializable{

    /**
     * Creates a new instance of dashboardBean
     */
    public dashboardBean() {
    }
 
    
    private LineChartModel lineModel1;
     
    @PostConstruct
    public void init() {
        createLineModels();
    }
 
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
     
    public void createLineModels() {
        
        
        lineModel1 = initCategoryModel();
        lineModel1.setTitle("Category Chart");
        lineModel1.setLegendPosition("e");
        lineModel1.setShowPointLabels(true);
        lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Fecha de lectura"));
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Lectura");
        yAxis.setMin(0);
        yAxis.setMax(900);
    }
 
    
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
        LecturaSensorDao lectura = new LecturaSensorDaoImpl();
        List<LecturaSensor> matriz = lectura.fillDashboard();
        
        
 
        ChartSeries temp = new ChartSeries();
        temp.setLabel("Temperatura");
        for(int i=0; i < matriz.size(); i++){
        temp.set(matriz.get(i).getFechaLectura().toString(),matriz.get(i).getTemperatura());
        }
        
        ChartSeries humAmbiente = new ChartSeries();
        humAmbiente.setLabel("Humedad ambiente");
        for(int i=0; i < matriz.size(); i++){
        humAmbiente.set(matriz.get(i).getFechaLectura().toString(),matriz.get(i).getHumAmbiente());
        }
        
        ChartSeries humTerreno = new ChartSeries();
        humTerreno.setLabel("Humedad terreno");
        for(int i=0; i < matriz.size(); i++){
        humTerreno.set(matriz.get(i).getFechaLectura().toString(),matriz.get(i).getHumTerreno());
        }
        
        ChartSeries lux = new ChartSeries();
        lux.setLabel("Luz");
        for(int i=0; i < matriz.size(); i++){
        lux.set(matriz.get(i).getFechaLectura().toString(),matriz.get(i).getLux());
        }   
 
        model.addSeries(temp);
        model.addSeries(humAmbiente);
        model.addSeries(humTerreno);
        model.addSeries(lux);
         
        return model;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorescontroller;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author quirozariel21
 */
@ManagedBean(name="periodoReferenciaBean")
@ViewScoped
public class PeriodoReferenciaController implements Serializable{
    
    private PeriodoReferencia periodoRefencia;
    
    private List<PeriodoReferencia>dataPeriodoReferencia;
    
    //Params
    List<String>grupoEdades;
    List<SelectItem>nivelTerritorial;
    
    private List<String> semestres;
    
    
    private PieChartModel pieModel1;
    private PeriodoReferencia periodoReferenciaAux;
     private LineChartModel lineModel1;
    private LineChartModel lineModel2;
    
    
    
    @PostConstruct
    public void ini(){
        
        createLineModels();
        
           pieModel1 = new PieChartModel();
         
        pieModel1.set("Masculino", 20);
        pieModel1.set("Femenino", 80);        
         
        pieModel1.setTitle("Hombres Mujeres");
        pieModel1.setLegendPosition("w");
        
        periodoReferenciaAux=new PeriodoReferencia();
    
        periodoRefencia=new PeriodoReferencia();
        periodoRefencia.setFemenino(0.00);
        periodoRefencia.setMasculino(0.00);
        periodoRefencia.setTotal(0.00);
        
        dataPeriodoReferencia=new VirtualFlow.ArrayLinkedList<>();
        //
        grupoEdades=new ArrayList<>();
        grupoEdades.add("<6m");
        grupoEdades.add("6m < 1a");
        grupoEdades.add("1 -4a");
        grupoEdades.add("5-9a");
        grupoEdades.add("10-14a");
        grupoEdades.add("15-19a");
        grupoEdades.add("20-39a");
        grupoEdades.add("40-49a");
        grupoEdades.add("50-59a");
        grupoEdades.add("60>");
        
        //Nacional                
        SelectItemGroup g0=new SelectItemGroup("Nacional");
        g0.setSelectItems(new SelectItem[] {new SelectItem("Nacional", " Total Nacional")}); 
        
        //BENI
        SelectItemGroup g1=new SelectItemGroup("Beni");
        g1.setSelectItems(new SelectItem[] {new SelectItem("Beni", " Total Beni"), new SelectItem("Trinidad", "Trinidad"),
            new SelectItem("San Javier", "San Javier"), new SelectItem("Riberalta", "Riberalta")});        
        
        //
        SelectItemGroup g2=new SelectItemGroup("Chiquisaca");
        g2.setSelectItems(new SelectItem[] {new SelectItem("Chiquisaca", " Total Chiquisaca"), new SelectItem("Sucre", "Sucre"),
            new SelectItem("Yotala", "Yotala"), new SelectItem("Poroma", "Poroma")});                       
        
        //
        SelectItemGroup g3=new SelectItemGroup("Cochabamba");
        g3.setSelectItems(new SelectItem[] {new SelectItem("Cochabamba", " Total Cochabamba"), new SelectItem("Alquile", "Alquile"),
            new SelectItem("Pasorapa", "Pasorapa"), new SelectItem("Omereque", "Omereque")});                       
        
        //
        SelectItemGroup g4=new SelectItemGroup("La Paz");
        g4.setSelectItems(new SelectItem[] {new SelectItem("LaPaz", " Total La Paz"), new SelectItem("Palca", "Palca"),
            new SelectItem("Mecapaca", "Mecapaca"), new SelectItem("Achocalla", "Achocalla")});                                       
        
        //
        SelectItemGroup g5=new SelectItemGroup("Oruro");
        g5.setSelectItems(new SelectItem[] {new SelectItem("Oruro", " Total Oruro"), new SelectItem("Caracollo", "Caracollo"),
            new SelectItem("ElChoro", "El Choro"), new SelectItem("Pari(Soracachi)", "Pari (Soracachi)")});   
        
        
        nivelTerritorial=new ArrayList<>();
        nivelTerritorial.add(g0);
        nivelTerritorial.add(g1);
        nivelTerritorial.add(g2);
        nivelTerritorial.add(g3);
        nivelTerritorial.add(g4);
        nivelTerritorial.add(g5);
        
        
        //populate
        dataPeriodoReferencia=new ArrayList<>();
        PeriodoReferencia pr1=new PeriodoReferencia();
        pr1.setNivelTerritorial("Bolivia"); 
        pr1.setTrimestre("ENE-MAR");
        pr1.setGrupoEdad("<6m");
        pr1.setFemenino(0.4);
        pr1.setMasculino(0.1);
        pr1.setTotal(0.5);

        PeriodoReferencia pr2=new PeriodoReferencia();
        pr2.setNivelTerritorial("Chuquisaca");     
        pr2.setTrimestre("ENE-MAR");
        pr2.setGrupoEdad("<6m");
        pr2.setFemenino(0.7);
        pr2.setMasculino(0.2);
        pr2.setTotal(0.9);      

        PeriodoReferencia pr3=new PeriodoReferencia();
        pr3.setTrimestre("ABR-JUN");
        pr3.setNivelTerritorial("Yotala");        
        pr3.setGrupoEdad("<6m");
        pr3.setFemenino(0.4);
        pr3.setMasculino(0.2);
        pr3.setTotal(0.6);        
        
        dataPeriodoReferencia.add(pr1);
        dataPeriodoReferencia.add(pr2);
        dataPeriodoReferencia.add(pr3);
        
        
        semestres=new ArrayList<>();
        semestres.add("ENE-MAR");
        semestres.add("ABR-JUN");
        semestres.add("JUL-SEP");
        semestres.add("OCT-DIC");
    }
    
    public void handleMasculino(){
        System.out.println("A F "+periodoRefencia.getFemenino());
        System.out.println("A M "+periodoRefencia.getMasculino());
        periodoRefencia.setTotal(periodoRefencia.getMasculino()+periodoRefencia.getFemenino());
    }

    public void handleFemenino(){
        System.out.println("B F "+periodoRefencia.getFemenino());
        System.out.println("B M "+periodoRefencia.getMasculino());
        periodoRefencia.setTotal(periodoRefencia.getMasculino()+periodoRefencia.getFemenino());
    }
    
    
    public void guardarPeriodoReferencia(){
        System.out.println("GUARDANDO");
        System.out.println("periodoRefencia:"+periodoRefencia);
        dataPeriodoReferencia.add(periodoRefencia); 
        periodoRefencia=new PeriodoReferencia();
        periodoRefencia.setFemenino(0.00);
        periodoRefencia.setMasculino(0.00);
        periodoRefencia.setTotal(0.00);
        
    }

    public PeriodoReferencia getPeriodoReferenciaAux() {
        System.out.println("GETTEANDO SETEANDO");
        return periodoReferenciaAux;
    }

    

    public void setPeriodoReferenciaAux(PeriodoReferencia periodoReferenciaAux) {
        
        System.out.println("SETEANDO SETEANDO");
        this.periodoReferenciaAux = periodoReferenciaAux;
        System.out.println("mas "+periodoReferenciaAux.getMasculino());
        System.out.println("fem "+periodoReferenciaAux.getFemenino());
        
           
        pieModel1.set("Masculino", periodoReferenciaAux.getMasculino());
        pieModel1.set("Femenino", periodoReferenciaAux.getFemenino());        
         
    
    }
    
       public void select(){
           System.out.println("SETEANDO SETEANDO11");
        this.periodoReferenciaAux = periodoReferenciaAux;
        System.out.println("mas "+periodoReferenciaAux.getMasculino());
        System.out.println("fem "+periodoReferenciaAux.getFemenino());
        
   
       }


        private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Category Chart");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
     
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
       
       
    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }
    
  
    
    public PeriodoReferencia getPeriodoRefencia() {
        return periodoRefencia;
    }

    public void setPeriodoRefencia(PeriodoReferencia periodoRefencia) {
        this.periodoRefencia = periodoRefencia;
    }

    public List<PeriodoReferencia> getDataPeriodoReferencia() {
        return dataPeriodoReferencia;
    }

    public void setDataPeriodoReferencia(List<PeriodoReferencia> dataPeriodoReferencia) {
        this.dataPeriodoReferencia = dataPeriodoReferencia;
    }

    public List<String> getGrupoEdades() {
        return grupoEdades;
    }

    public void setGrupoEdades(List<String> grupoEdades) {
        this.grupoEdades = grupoEdades;
    }

    public List<SelectItem> getNivelTerritorial() {
        return nivelTerritorial;
    }

    public void setNivelTerritorial(List<SelectItem> nivelTerritorial) {
        this.nivelTerritorial = nivelTerritorial;
    }

    public List<String> getSemestres() {
        return semestres;
    }

    public void setSemestres(List<String> semestres) {
        this.semestres = semestres;
    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        this.lineModel2 = lineModel2;
    }
    
    
    
    
}

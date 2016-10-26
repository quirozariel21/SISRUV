/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorescontroller;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author KRETCO
 */
@ManagedBean(name="indicadoresBean")
@ViewScoped
public class IndicadoresController implements Serializable{

    //
    private String institucion;
    private String indicador;
    private String formulaCalculo;
    private String tipoViolencia;
    private String territorio;
    private String periodoReferencia;
    
    private List<String> instituciones;
    
    private List<Indicador>indicadores;
    private List<TipoViolencia> selectedViolencia;
    
    private List<TipoViolencia>tiposViolencia;
    
    private List<Indicador>dataIndicadores;
    
    private PieChartModel pieModel1;
    
    @PostConstruct
    public void init(){
   
        instituciones=new ArrayList<>();
        // todo load list from database
        instituciones.add("Policia boliviana");
        instituciones.add("Organo Judicial");
        instituciones.add("Ministerio de Salud");
        
        dataIndicadores=new ArrayList<>();
        indicadores=new ArrayList<>();
         pieModel1 = new PieChartModel();
        tiposViolencia=new ArrayList<>();
        tiposViolencia=buildTipoViolencia();
        loadIndicadores();
    }

     private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);
         
        pieModel1.setTitle("Simple Pie");
        pieModel1.setShowDataLabels(true);
        pieModel1.setLegendPosition("w");
    }
    
    public void loadIndicadores(){             
        
        Indicador indicador=new Indicador();
        indicador.setTipoIndicador("Numero de casos recibidos con impulsion formal"
                + " por tipo de violencia.");
        indicador.setFormulaCalculo("Numero de casos remitidos por Fiscalia con impulsacion formal"
                + " por tipo de violencia.");
        indicador.setTieneTipoViolenacia(true);        
        indicador.setTiposViolencia(buildTipoViolencia());
        indicadores.add(indicador);
        
        Indicador indicador1=new Indicador();
        indicador1.setTipoIndicador("Total de personas acusadas por caso y tipo de violencia.");
        indicador1.setFormulaCalculo("Total de personas acusadas por tipo de violencia / total de casos "
                + "de violencia recibidos.");
        indicador1.setTieneTipoViolenacia(true);        
        indicador1.setTiposViolencia(buildTipoViolencia());
        indicadores.add(indicador1);
        
        Indicador indicador2=new Indicador();
        indicador2.setTipoIndicador("Total de casos de violencia en proceso judicial "
                + "en el periodo.");
        indicador2.setFormulaCalculo("Numero de casos de violencia que se "
                + "encuentran en juicio, segun tipo de violencia.");
        indicador2.setTieneTipoViolenacia(true);        
        indicador2.setTiposViolencia(buildTipoViolencia());
        indicadores.add(indicador2);

        
        Indicador indicador3=new Indicador();
        indicador3.setTipoIndicador("Proposicion de casos de viiolencia con sentencia.");
        indicador3.setFormulaCalculo("Numero de casos de violencia consentencia / total "
                + "de casos en proceso de juicio o ingresados.");
        indicador.setTieneTipoViolenacia(false);        
        indicador.setCantidad(Integer.SIZE);
        indicadores.add(indicador3);
    }
    
    
    public List<TipoViolencia> buildTipoViolencia(){
        
        List<TipoViolencia> list=new ArrayList<>();
        
        TipoViolencia tViolencia1=new TipoViolencia();
        tViolencia1.setNombre("Violencia Feminicio");
        tViolencia1.setCantidad(0);
        list.add(tViolencia1);

        TipoViolencia tViolencia2=new TipoViolencia();
        tViolencia2.setNombre("Violencia Contra los Derechos de la Libertad Sexual");
        tViolencia2.setCantidad(0);
        list.add(tViolencia2);
        
        TipoViolencia tViolencia3=new TipoViolencia();
        tViolencia3.setNombre("Violencia Contra los Derechos Reproductivos");
        tViolencia3.setCantidad(0);
        list.add(tViolencia3);
        
        TipoViolencia tViolencia4=new TipoViolencia();
        tViolencia4.setNombre("Violencia Contra la Dignidad, la Honra y el Nombre.");
        tViolencia4.setCantidad(0);
        list.add(tViolencia4);
        
        TipoViolencia tViolencia5=new TipoViolencia();
        tViolencia5.setNombre("Violencia en el Ejercicio Politico y Liderazgo");
        tViolencia5.setCantidad(0);
        list.add(tViolencia5);
        
        TipoViolencia tViolencia6=new TipoViolencia();
        tViolencia6.setNombre("Violencia Mediatica");
        tViolencia6.setCantidad(0);
        list.add(tViolencia6);
        
        TipoViolencia tViolencia7=new TipoViolencia();
        tViolencia7.setNombre("Violencia Institucional");
        tViolencia7.setCantidad(0);
        list.add(tViolencia7);
        
        TipoViolencia tViolencia8=new TipoViolencia();
        tViolencia8.setNombre("Violencia en el Sistema Educativo Plurinacional");
        tViolencia8.setCantidad(0);
        list.add(tViolencia8);
        
        TipoViolencia tViolencia9=new TipoViolencia();
        tViolencia9.setNombre("Violencia en el Servicio de Salud");
        tViolencia9.setCantidad(0);
        list.add(tViolencia9);
        
        TipoViolencia tViolencia10=new TipoViolencia();
        tViolencia10.setNombre("Violencia Laboral");
        tViolencia10.setCantidad(0);
        list.add(tViolencia10);
        
        TipoViolencia tViolencia11=new TipoViolencia();
        tViolencia11.setNombre("Violencia en la Familia");
        tViolencia11.setCantidad(0);
        list.add(tViolencia11);

        TipoViolencia tViolencia12=new TipoViolencia();
        tViolencia12.setNombre("Violencia Simbolica y/o Encubierta");
        tViolencia12.setCantidad(0);
        list.add(tViolencia12);
        
        TipoViolencia tViolencia13=new TipoViolencia();
        tViolencia13.setNombre("Violencia Patrimonial y Economica");
        tViolencia13.setCantidad(0);
        list.add(tViolencia13);
        
        TipoViolencia tViolencia14=new TipoViolencia();
        tViolencia14.setNombre("Violencia Sexual");
        tViolencia14.setCantidad(0);
        list.add(tViolencia14);
        
        TipoViolencia tViolencia15=new TipoViolencia();
        tViolencia15.setNombre("Violencia Psicologica");
        tViolencia15.setCantidad(0);
        list.add(tViolencia15);
        
        TipoViolencia tViolencia16=new TipoViolencia();
        tViolencia16.setNombre("Violencia Fisica");
        tViolencia16.setCantidad(0);
        list.add(tViolencia16);
        
        TipoViolencia tViolencia17=new TipoViolencia();
        tViolencia17.setNombre("Otros");
        tViolencia17.setCantidad(0);
        list.add(tViolencia17);      
        
        return list;
    }

    public void handleTipoIndicador(){
        System.out.println("Handle Tipo de indicador");
        System.out.println(">> Indicador "+this.indicador);
        for(Indicador ind:indicadores){
            if(ind.getTipoIndicador().equalsIgnoreCase(this.indicador)){
                this.formulaCalculo=ind.getFormulaCalculo();
            }
        }
        
    }

    public void guardarIndicador(){
        System.out.println("==>> guardar indicador <<===");
         FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Welcome " + this.indicador + " " + this.institucion));                           
         Indicador indicador=new Indicador();
         indicador.setInstitucion(this.institucion);
         indicador.setTipoIndicador(this.indicador);
         indicador.setFormulaCalculo(this.formulaCalculo);
         indicador.setTerritorializacion(this.territorio);
         indicador.setPeriodoReferencia(this.periodoReferencia);
         indicador.setTiposViolencia(this.tiposViolencia);
         dataIndicadores.add(indicador);
         //todo remove indicador ya adicionado
    }

    public List<Indicador> getDataIndicadores() {
        return dataIndicadores;
    }

    public void setDataIndicadores(List<Indicador> dataIndicadores) {
        this.dataIndicadores = dataIndicadores;
    }
    
    
    public String getTipoViolencia() {
        return tipoViolencia;
    }

    public void setTipoViolencia(String tipoViolencia) {
        this.tipoViolencia = tipoViolencia;
    }

    public List<TipoViolencia> getTiposViolencia() {
        return tiposViolencia;
    }

    public void setTiposViolencia(List<TipoViolencia> tiposViolencia) {
        this.tiposViolencia = tiposViolencia;
    }
    
    
    
    public List<TipoViolencia> getSelectedViolencia() {
        System.out.println("### GET POR QUE");
        return selectedViolencia;
    }

    public void setSelectedViolencia(List<TipoViolencia> selectedViolencia) {
        System.out.println("### SET");
        this.selectedViolencia = selectedViolencia;
        System.out.println("selected:"+selectedViolencia);
        pieModel1 = new PieChartModel();
        for(TipoViolencia tv:selectedViolencia){
            pieModel1.set(tv.getNombre(), tv.getCantidad());    
        }         
         
        pieModel1.setTitle("Grafico");
        pieModel1.setShowDataLabels(true);
        pieModel1.setLegendPosition("w");
        
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }
    
    public void openDialogViolencias(){
        System.out.println("### OPEN DIALOG");
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> map = context.getExternalContext().getRequestMap();
        List<TipoViolencia> listTiposViolencia = (List<TipoViolencia>) map.get("tiposViolencia");
        System.out.println("VIOLENCOAS"+listTiposViolencia );
    }
       
    
    public List<String> getInstituciones() {
        return instituciones;
    }

    public List<Indicador> getIndicadores() {
        return indicadores;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getFormulaCalculo() {
        return formulaCalculo;
    }

    public void setFormulaCalculo(String formulaCalculo) {
        this.formulaCalculo = formulaCalculo;
    }

    

    public String getTerritorio() {
        return territorio;
    }

    public void setTerritorio(String territorio) {
        this.territorio = territorio;
    }

    public String getPeriodoReferencia() {
        return periodoReferencia;
    }

    public void setPeriodoReferencia(String periodoReferencia) {
        this.periodoReferencia = periodoReferencia;
    }
    
    
 

}

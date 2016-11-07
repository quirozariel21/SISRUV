/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorescontroller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    
    private List<Indicador>indicadores;
    private Indicador indicador;  
    private List<String> instituciones;
    private List<Indicador>dataIndicadores;
   
    @PostConstruct
    public void init(){
        System.out.println("@@@@ POST CONSTRUCT @@@");
        instituciones=new ArrayList<>();        
        instituciones.add("Policia boliviana");
        instituciones.add("Organo Judicial");
        instituciones.add("Ministerio de Salud");
        
        indicador=new Indicador();
        
        dataIndicadores=new ArrayList<>();
        
        Indicador ind1=new Indicador();
        ind1.setTitulo("Porcentaje de casos de violecia en la familia atendidos en el sistema de salud");
        ind1.setDescripcion("Proporcion de casos de violencia en la familia atendidos por los servicios de salud en un periodo y territorio determinado del total de casos de familia");
        ind1.setFormula("PVF = total de casos en la familia atendidos en los servicios de salud / el total de casos de violencia atendidos por 100.");
        ind1.setFechaCreacion(new Date());
        ind1.setGestion(2014);

        Indicador ind2=new Indicador();
        ind2.setTitulo("Porcentaje de casos de violecia en la familia atendidos en el sistema de salud");
        ind2.setDescripcion("Proporcion de casos de violencia en la familia atendidos por los servicios de salud en un periodo y territorio determinado del total de casos de familia");
        ind2.setFormula("PVF = total de casos en la familia atendidos en los servicios de salud / el total de casos de violencia atendidos por 100.");
        ind2.setFechaCreacion(new Date());
        ind2.setGestion(2015);
        
        Indicador ind3=new Indicador();
        ind3.setTitulo("Porcetanje de casos de otras violencias por lesiones autoinflingidas y colectivas atendidos en los servicios de salud");
        ind3.setDescripcion("Proporcion de casos de  otras violencias por lesiones autoinflingidas y colectivas  atendidos por los servicios de salud en un periodo y territorio determinado del total de casos de violencia atendidos.");
        ind3.setFormula("PVOV= total de casos de  otras violencias por lesiones autoinflingidas y colectivas atendidos en los servicios de salud / el total de casos de violencia atendidos por 100.");
        ind3.setFechaCreacion(new Date());
        ind3.setGestion(2016);        
        
        dataIndicadores.add(ind1);
        dataIndicadores.add(ind2);
        dataIndicadores.add(ind3);
        
        cargarIndicadores();
    }

    public void cargarIndicadores(){
        
        indicadores=new ArrayList<>();
        Indicador indicador1=new Indicador();
        indicador1.setIdIndicador(1);
        indicador1.setTitulo("Porcentaje de casos de violecia en la familia atendidos en el sistema de salud");
        indicador1.setDescripcion("Proporcion de casos de violencia en la familia atendidos por los servicios de salud en un periodo y territorio determinado del total de casos de familia.");
        indicador1.setFormula("PVF = total de casos en la familia atendidos en los servicios de salud / el total de casos de violencia atendidos por 100.");
        indicadores.add(indicador1);
                
        Indicador indicador2=new Indicador();
        indicador2.setIdIndicador(2);
        indicador2.setTitulo("Porcetanje de casos de violecia sexual (Familiar, domestica y/o en otros ambitos) atendidos en el sistema de salud");
        indicador2.setDescripcion("Proporcion de casos de violencia sexual (Familiar, domestica y/o en otros ambitos) atendidos por los servicios de salud en un periodo y territorio determinado del total de casos de violencia");
        indicador2.setFormula("PVF= total de casos en la familia atendidos en los servicios de salud / el total de casos de violencia atendidos por 100.");
        indicadores.add(indicador2);
        
        Indicador indicador3=new Indicador();
        indicador3.setIdIndicador(3);
        indicador3.setTitulo("Porcetanje de casos de otras violencias por lesiones autoinflingidas y colectivas atendidos en los servicios de salud");
        indicador3.setDescripcion("Proporcion de casos de  otras violencias por lesiones autoinflingidas y colectivas  atendidos por los servicios de salud en un periodo y territorio determinado del total de casos de violencia atendidos.");
        indicador3.setFormula("PVOV= total de casos de  otras violencias por lesiones autoinflingidas y colectivas atendidos en los servicios de salud / el total de casos de violencia atendidos por 100.");
        indicadores.add(indicador3);
        System.out.println("Load indicadores "+indicadores.size());
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }


    public void handleTipoIndicador(){
        System.out.println("Handle Tipo de indicador");
        System.out.println("idIndicador"+this.indicador.getIdIndicador());        
        Indicador aux=indicadores.stream()
            .filter(ind -> ind.getIdIndicador() == this.indicador.getIdIndicador())
            .findAny()
            .orElse(null);
    
        if(aux!=null){            
            this.indicador.setTitulo(aux.getTitulo());
            this.indicador.setDescripcion(aux.getDescripcion());
            this.indicador.setFormula(aux.getFormula());
        }        
    }
    
    public void irPeriodoReferencia(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("/indicadores/periodoReferencia.xhtml");
        } catch (IOException ex) {
            System.out.println("Error all redireccionar "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    public void guardarIndicador(){
        System.out.println("==>> guardar indicador <<===");
        dataIndicadores.add(indicador);
         FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Se guardo exitosamente el indicador:" + this.indicador));                           
         indicador=new Indicador();         
    }
    
    public void openDialogViolencias(){
        System.out.println("### OPEN DIALOG");
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> map = context.getExternalContext().getRequestMap();
        List<TipoViolencia> listTiposViolencia = (List<TipoViolencia>) map.get("tiposViolencia");
        System.out.println("VIOLENCOAS"+listTiposViolencia );
    }

    public List<Indicador> getDataIndicadores() {
        return dataIndicadores;
    }

    public void setDataIndicadores(List<Indicador> dataIndicadores) {
        this.dataIndicadores = dataIndicadores;
    }
       
    
    
    public List<String> getInstituciones() {
        return instituciones;
    }

    public List<Indicador> getIndicadores() {
        return indicadores;
    }

}

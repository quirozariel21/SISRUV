/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorescontroller;

import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author quirozariel21
 */
public class PeriodoReferencia {
 
    private Integer periodoRefencia;
    private String trimestre;
    private Indicador indicador;
    private String grupoEdad;
    private String nivelTerritorial;
    private double masculino;
    private double femenino;
    private double total;

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }       
    
    public Integer getPeriodoRefencia() {
        return periodoRefencia;
    }

    public void setPeriodoRefencia(Integer periodoRefencia) {
        this.periodoRefencia = periodoRefencia;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public String getGrupoEdad() {
        return grupoEdad;
    }

    public void setGrupoEdad(String grupoEdad) {
        this.grupoEdad = grupoEdad;
    }   
    
    public String getNivelTerritorial() {
        return nivelTerritorial;
    }

    public void setNivelTerritorial(String nivelTerritorial) {
        this.nivelTerritorial = nivelTerritorial;
    }

    public double getMasculino() {
        return masculino;
    }

    public void setMasculino(double masculino) {
        this.masculino = masculino;
    }

    public double getFemenino() {
        return femenino;
    }

    public void setFemenino(double femenino) {
        this.femenino = femenino;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
    
    
}

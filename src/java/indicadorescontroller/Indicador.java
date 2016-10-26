/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorescontroller;

import java.util.List;

/**
 *
 * @author quirozariel21
 */
public class Indicador {

    private String institucion;
    private String tipoIndicador;
    private String formulaCalculo;
    private String desagregacion;
    // var aux
    private boolean tieneTipoViolenacia;
    
    private List<TipoViolencia>tiposViolencia;
    private Integer cantidad;
    
    private String territorializacion;
    private String periodoReferencia;

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    
    
    public String getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(String tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public String getFormulaCalculo() {
        return formulaCalculo;
    }

    public void setFormulaCalculo(String formulaCalculo) {
        this.formulaCalculo = formulaCalculo;
    }

    public String getDesagregacion() {
        return desagregacion;
    }

    public void setDesagregacion(String desagregacion) {
        this.desagregacion = desagregacion;
    }

    public boolean isTieneTipoViolenacia() {
        return tieneTipoViolenacia;
    }

    public void setTieneTipoViolenacia(boolean tieneTipoViolenacia) {
        this.tieneTipoViolenacia = tieneTipoViolenacia;
    }

    public List<TipoViolencia> getTiposViolencia() {
        return tiposViolencia;
    }

    public void setTiposViolencia(List<TipoViolencia> tiposViolencia) {
        this.tiposViolencia = tiposViolencia;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTerritorializacion() {
        return territorializacion;
    }

    public void setTerritorializacion(String territorializacion) {
        this.territorializacion = territorializacion;
    }

    public String getPeriodoReferencia() {
        return periodoReferencia;
    }

    public void setPeriodoReferencia(String periodoReferencia) {
        this.periodoReferencia = periodoReferencia;
    }

    
    
    
    
}

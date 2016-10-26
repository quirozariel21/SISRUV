/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresmodel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author KRETCO
 */
public class IIndicadores {
    private int id_indicadores;
    private int pregunta_id_pregunta;
    private int institucion_id_institucion;
    private Date fecha;
    private BigDecimal resultado;
    private int id_log;
    
    public IIndicadores() {
    }

    public IIndicadores(int id_indicadores, int pregunta_id_pregunta, int institucion_id_institucion, Date fecha, BigDecimal resultado) {
        this.id_indicadores = id_indicadores;
        this.pregunta_id_pregunta = pregunta_id_pregunta;
        this.institucion_id_institucion = institucion_id_institucion;
        this.fecha = fecha;
        this.resultado = resultado;
    }

    public int getId_indicadores() {
        return id_indicadores;
    }

    public void setId_indicadores(int id_indicadores) {
        this.id_indicadores = id_indicadores;
    }

    public int getPregunta_id_pregunta() {
        return pregunta_id_pregunta;
    }

    public void setPregunta_id_pregunta(int pregunta_id_pregunta) {
        this.pregunta_id_pregunta = pregunta_id_pregunta;
    }

    public int getInstitucion_id_institucion() {
        return institucion_id_institucion;
    }

    public void setInstitucion_id_institucion(int institucion_id_institucion) {
        this.institucion_id_institucion = institucion_id_institucion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getResultado() {
        return resultado;
    }

    public void setResultado(BigDecimal resultado) {
        this.resultado = resultado;
    }

    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }
    
    
}

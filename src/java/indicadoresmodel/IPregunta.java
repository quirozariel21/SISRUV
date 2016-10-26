/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresmodel;

import java.math.BigDecimal;

/**
 *
 * @author KRETCO
 */
public class IPregunta {
    private int id_pregunta;
    private String descripcion;
    private int institucion_id_institucion;
    private BigDecimal dato;

    public IPregunta() {
    }

    public IPregunta(int id_pregunta, String descripcion, int institucion_id_institucion) {
        this.id_pregunta = id_pregunta;
        this.descripcion = descripcion;
        this.institucion_id_institucion = institucion_id_institucion;
    }
    
    public int getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(int id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getInstitucion_id_institucion() {
        return institucion_id_institucion;
    }

    public void setInstitucion_id_institucion(int institucion_id_institucion) {
        this.institucion_id_institucion = institucion_id_institucion;
    }

    public BigDecimal getDato() {
        return dato;
    }

    public void setDato(BigDecimal dato) {
        this.dato = dato;
    }
    
}

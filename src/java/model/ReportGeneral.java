/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author e_mv
 */
public class ReportGeneral implements Serializable {

    private Integer id;
    private boolean check;
    private String descripcion;
    private Integer relacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getRelacion() {
        return relacion;
    }

    public void setRelacion(Integer relacion) {
        this.relacion = relacion;
    }

}

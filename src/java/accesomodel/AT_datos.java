/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesomodel;

import java.io.Serializable;

/**
 *
 * @author KRETCO
 */
public class AT_datos implements Serializable{
    private Integer id_tdatos;
    private String descripcion;
    private Integer id_t;
    private Integer relacion;
    private Integer estado;

    public AT_datos() {
    }

    public AT_datos(Integer id_tdatos, String descripcion, Integer id_t) {
        this.id_tdatos = id_tdatos;
        this.descripcion = descripcion;
        this.id_t = id_t;
    }

    public Integer getId_tdatos() {
        return id_tdatos;
    }

    public void setId_tdatos(Integer id_tdatos) {
        this.id_tdatos = id_tdatos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId_t() {
        return id_t;
    }

    public void setId_t(Integer id_t) {
        this.id_t = id_t;
    }

    public Integer getRelacion() {
        return relacion;
    }

    public void setRelacion(Integer relacion) {
        this.relacion = relacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    
    
}

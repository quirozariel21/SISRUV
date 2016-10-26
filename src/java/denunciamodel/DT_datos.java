/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denunciamodel;

import java.io.Serializable;

/**
 *
 * @author KRETCO
 */
public class DT_datos implements Serializable{
    private Integer id_tdatos;
    private Integer identificador;
    private String descripcion;
    private Integer id_t;

    public DT_datos() {
    }

    public DT_datos(Integer identificador, String descripcion, Integer id_t) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.id_t = id_t;
    }

    public Integer getId_tdatos() {
        return id_tdatos;
    }

    public void setId_tdatos(Integer id_tdatos) {
        this.id_tdatos = id_tdatos;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
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

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesomodel;

import java.io.Serializable;

/**
 *
 * @author e_mv
 */
public class AArbolMenu implements Serializable {

    private Integer codigo;
    private String descripcion;
    private Integer codigoPadre;
    private String tipo;

    public AArbolMenu(Integer codigo, String descripcion, Integer codigoPadre, String tipo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.codigoPadre = codigoPadre;
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCodigoPadre() {
        return codigoPadre;
    }

    public void setCodigoPadre(Integer codigoPadre) {
        this.codigoPadre = codigoPadre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}

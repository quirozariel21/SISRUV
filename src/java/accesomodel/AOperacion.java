/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesomodel;

import java.io.Serializable;

public class AOperacion implements Serializable{

    private Integer cod_opera;
    private Integer cod_opera_padre;
    private String descripcion;
    private String href;
    private boolean estado;
    private String icon;
    private Integer aplicacion_id_app;

    public AOperacion() {
    }

    public AOperacion(Integer cod_opera, Integer cod_opera_padre, String descripcion, String href, boolean estado, String icon, Integer aplicacion_id_app) {
        this.cod_opera = cod_opera;
        this.cod_opera_padre = cod_opera_padre;
        this.descripcion = descripcion;
        this.href = href;
        this.estado = estado;
        this.icon = icon;
        this.aplicacion_id_app = aplicacion_id_app;
    }

    public AOperacion(Integer cod_opera, String descripcion) {
        this.cod_opera = cod_opera;
        this.descripcion = descripcion;
    }
    
    public Integer getCod_opera() {
        return cod_opera;
    }

    public void setCod_opera(Integer cod_opera) {
        this.cod_opera = cod_opera;
    }

    public Integer getCod_opera_padre() {
        return cod_opera_padre;
    }

    public void setCod_opera_padre(Integer cod_opera_padre) {
        this.cod_opera_padre = cod_opera_padre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getAplicacion_id_app() {
        return aplicacion_id_app;
    }

    public void setAplicacion_id_app(Integer aplicacion_id_app) {
        this.aplicacion_id_app = aplicacion_id_app;
    }

    

}

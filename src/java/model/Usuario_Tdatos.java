/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author KRETCO
 */
public class Usuario_Tdatos implements Serializable{
     private Usuario usuario;
   private String detalle_depto;
   private String detalle_muni;
   private String detalle_serv;
   private String detalle_subs;
   
    public Usuario_Tdatos() {
        usuario =  new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDetalle_depto() {
        return detalle_depto;
    }

    public void setDetalle_depto(String detalle_depto) {
        this.detalle_depto = detalle_depto;
    }

    public String getDetalle_muni() {
        return detalle_muni;
    }

    public void setDetalle_muni(String detalle_muni) {
        this.detalle_muni = detalle_muni;
    }

    public String getDetalle_serv() {
        return detalle_serv;
    }

    public void setDetalle_serv(String detalle_serv) {
        this.detalle_serv = detalle_serv;
    }

    public String getDetalle_subs() {
        return detalle_subs;
    }

    public void setDetalle_subs(String detalle_subs) {
        this.detalle_subs = detalle_subs;
    }

    
}

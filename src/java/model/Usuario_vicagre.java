/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author KRETCO
 */
public class Usuario_vicagre implements Serializable{
    private int id_usuario_vicagre;
    private int usuario_cod_usuario;
    private int victima_agresor_id_vicagre;
    private Date fecha_reg;
    private int nro_caso;
    private String cod_sistema;
    private Date fecha_system;
    private int id_log;

    public Usuario_vicagre() {
    }

    public Usuario_vicagre(int id_usuario_vicagre, int usuario_cod_usuario, int victima_agresor_id_vicagre, Date fecha_reg, int nro_caso, String cod_sistema, Date fecha_system) {
        this.id_usuario_vicagre = id_usuario_vicagre;
        this.usuario_cod_usuario = usuario_cod_usuario;
        this.victima_agresor_id_vicagre = victima_agresor_id_vicagre;
        this.fecha_reg = fecha_reg;
        this.nro_caso = nro_caso;
        this.cod_sistema = cod_sistema;
        this.fecha_system = fecha_system;
    }

    public Usuario_vicagre(String cod_sistema) {
        this.cod_sistema = cod_sistema;
    }
    
    public Usuario_vicagre(int usuario_cod_usuario, int victima_agresor_id_vicagre, Date fecha_reg, int nro_caso, String cod_sistema, Date fecha_system) {
        this.usuario_cod_usuario = usuario_cod_usuario;
        this.victima_agresor_id_vicagre = victima_agresor_id_vicagre;
        this.fecha_reg = fecha_reg;
        this.nro_caso = nro_caso;
        this.cod_sistema = cod_sistema;
        this.fecha_system = fecha_system;
    }

    public int getId_usuario_vicagre() {
        return id_usuario_vicagre;
    }

    public void setId_usuario_vicagre(int id_usuario_vicagre) {
        this.id_usuario_vicagre = id_usuario_vicagre;
    }

    public int getUsuario_cod_usuario() {
        return usuario_cod_usuario;
    }

    public void setUsuario_cod_usuario(int usuario_cod_usuario) {
        this.usuario_cod_usuario = usuario_cod_usuario;
    }

    public int getVictima_agresor_id_vicagre() {
        return victima_agresor_id_vicagre;
    }

    public void setVictima_agresor_id_vicagre(int victima_agresor_id_vicagre) {
        this.victima_agresor_id_vicagre = victima_agresor_id_vicagre;
    }

    public Date getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(Date fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public int getNro_caso() {
        return nro_caso;
    }

    public void setNro_caso(int nro_caso) {
        this.nro_caso = nro_caso;
    }

    public String getCod_sistema() {
        return cod_sistema;
    }

    public void setCod_sistema(String cod_sistema) {
        this.cod_sistema = cod_sistema;
    }

    public Date getFecha_system() {
        return fecha_system;
    }

    public void setFecha_system(Date fecha_system) {
        this.fecha_system = fecha_system;
    }

    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }
    
    
}

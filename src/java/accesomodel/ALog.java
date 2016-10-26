/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesomodel;

import java.util.Date;

/**
 *
 * @author KRETCO
 */
public class ALog {
    private Integer id;
    private Integer id_user;
    private String ip;
    private String actividad;
    private Integer id_registro;
    private String nombre_tabla;

    public ALog() {
    }

    public ALog(Integer id, Integer id_user, String ip, String actividad, Integer id_registro, String nombre_tabla) {
        this.id = id;
        this.id_user = id_user;
        this.ip = ip;
        this.actividad = actividad;
        this.id_registro = id_registro;
        this.nombre_tabla = nombre_tabla;
    }

    public ALog(Integer id_user, String ip, String actividad, Integer id_registro, String nombre_tabla) {
        this.id_user = id_user;
        this.ip = ip;
        this.actividad = actividad;
        this.id_registro = id_registro;
        this.nombre_tabla = nombre_tabla;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Integer getId_registro() {
        return id_registro;
    }

    public void setId_registro(Integer id_registro) {
        this.id_registro = id_registro;
    }

    public String getNombre_tabla() {
        return nombre_tabla;
    }

    public void setNombre_tabla(String nombre_tabla) {
        this.nombre_tabla = nombre_tabla;
    }
    
}

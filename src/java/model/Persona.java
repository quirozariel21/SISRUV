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

public class Persona implements Serializable{
    private Integer id_persona;
    private String nombre;
    private String paterno;
    private String materno;
    private String ap_casada;

    private String numero;
    private Integer codigo_documento;
    private String sexo;
    private Integer lugar_nac;
    private String esp_lugar_nac;
    private Date fecha_nac;
    
    private String victima_nombre;
    private String agresor_nombre;

    public Persona() {
    }

    public Persona(Integer id_persona, String nombre) {
        this.id_persona = id_persona;
        this.nombre = nombre;
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }
    
    public Persona(String nombre, String paterno, String materno, String ap_casada, Integer doc_identidad, String numero, Integer codigo_documento, String sexo, Integer lugar_nac, String esp_lugar_nac, Date fecha_nac) {
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.ap_casada = ap_casada;
        this.numero = numero;
        this.codigo_documento = codigo_documento;
        this.sexo = sexo;
        this.lugar_nac = lugar_nac;
        this.esp_lugar_nac = esp_lugar_nac;
        this.fecha_nac = fecha_nac;
    }

    public Persona(Integer id_persona, String nombre, String paterno, String materno, String ap_casada, Integer doc_identidad, String numero, Integer codigo_documento, String sexo, Integer lugar_nac, String esp_lugar_nac) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.ap_casada = ap_casada;
        this.numero = numero;
        this.codigo_documento = codigo_documento;
        this.sexo = sexo;
        this.lugar_nac = lugar_nac;
        this.esp_lugar_nac = esp_lugar_nac;
    }
    
    public Persona(Integer id_persona, String nombre, String paterno, String materno, String ap_casada, Integer doc_identidad, String numero, Integer codigo_documento, String sexo, Integer lugar_nac, String esp_lugar_nac, Date fecha_nac) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.ap_casada = ap_casada;
        this.numero = numero;
        this.codigo_documento = codigo_documento;
        this.sexo = sexo;
        this.lugar_nac = lugar_nac;
        this.esp_lugar_nac = esp_lugar_nac;
        this.fecha_nac = fecha_nac;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getAp_casada() {
        return ap_casada;
    }

    public void setAp_casada(String ap_casada) {
        this.ap_casada = ap_casada;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCodigo_documento() {
        return codigo_documento;
    }

    public void setCodigo_documento(Integer codigo_documento) {
        this.codigo_documento = codigo_documento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getLugar_nac() {
        return lugar_nac;
    }

    public void setLugar_nac(Integer lugar_nac) {
        this.lugar_nac = lugar_nac;
    }

    public String getEsp_lugar_nac() {
        return esp_lugar_nac;
    }

    public void setEsp_lugar_nac(String esp_lugar_nac) {
        this.esp_lugar_nac = esp_lugar_nac;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getVictima_nombre() {
        victima_nombre = nombre+" " + paterno+" "+materno;
        return victima_nombre;
    }

    public void setVictima_nombre(String victima_nombre) {
        this.victima_nombre = victima_nombre;
    }

    public String getAgresor_nombre() {
        agresor_nombre = nombre+" " + paterno+" "+materno;
        return agresor_nombre;
    }

    public void setAgresor_nombre(String agresor_nombre) {
        this.agresor_nombre = agresor_nombre;
    }
}

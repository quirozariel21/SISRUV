/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author KRETCO
 */
public class Persona_variables implements Serializable{
    private int id_pervar;
    private int id_persona;
    private Integer edad;
    private int est_civil;
    private int nro_hijos;
    private int gestacion_h;
    private int num_miembros_fam;
    private boolean municipio;
    private String esp_municipio;
    private boolean area;
    private String esp_area;
    private int vivienda;
    private int nivel_inst;
    private boolean condicion_act;
    private int ocupacion;
    private String esp_ocupacion;
    private String cargo;
    private int ingre_economico;
    private BigDecimal monto_aprox_bs;
    private BigDecimal aporte_familiar_bs;
    private String idiomas;
    private String esp_idioma;
    private boolean etnia;
    private String esp_etnia;
    private boolean lgbti;
    private boolean pers_discapacidad;

    public Persona_variables() {
//        this.edad = 0;
//        this.est_civil = 0;
//        this.municipio = false;
//        this.area = false;
//        this.nivel_inst = 1;
//        this.condicion_act =  false;
//        this.ocupacion = 1;
//        this.etnia = false;
//        this.esp_area = "sin dato";
    }

//    public Persona_variables(boolean municipio){
//    this.municipio = municipio;
//    }

//    public Persona_variables(int id_pervar, int id_persona, Integer edad, int est_civil, int nro_hijos, int gestacion_h, int num_miembros_fam, boolean municipio, String esp_municipio, boolean area, String esp_area, int vivienda, int nivel_inst, boolean condicion_act, int ocupacion, String esp_ocupacion, String cargo, int ingre_economico, BigDecimal monto_aprox_bs, BigDecimal aporte_familiar_bs, String idiomas, String esp_idioma, boolean etnia, String esp_etnia, boolean lgbti, boolean pers_discapacidad) {
//        this.id_pervar = id_pervar;
//        this.id_persona = id_persona;
//        this.edad = edad;
//        this.est_civil = est_civil;
//        this.nro_hijos = nro_hijos;
//        this.gestacion_h = gestacion_h;
//        this.num_miembros_fam = num_miembros_fam;
//        this.municipio = municipio;
//        this.esp_municipio = esp_municipio;
//        this.area = area;
//        this.esp_area = esp_area;
//        this.vivienda = vivienda;
//        this.nivel_inst = nivel_inst;
//        this.condicion_act = condicion_act;
//        this.ocupacion = ocupacion;
//        this.esp_ocupacion = esp_ocupacion;
//        this.cargo = cargo;
//        this.ingre_economico = ingre_economico;
//        this.monto_aprox_bs = monto_aprox_bs;
//        this.aporte_familiar_bs = aporte_familiar_bs;
//        this.idiomas = idiomas;
//        this.esp_idioma = esp_idioma;
//        this.etnia = etnia;
//        this.esp_etnia = esp_etnia;
//        this.lgbti = lgbti;
//        this.pers_discapacidad = pers_discapacidad;
//    }
    
//        public Persona_variables(Integer edad, int est_civil, boolean municipio, boolean area, int nivel_inst, boolean condicion_act, int ocupacion, boolean etnia) {
//        this.edad = edad;
//        this.est_civil = est_civil;
//        this.municipio = municipio;
//        this.area = area;
//        this.nivel_inst = nivel_inst;
//        this.condicion_act = condicion_act;
//        this.ocupacion = ocupacion;
//        this.etnia = etnia;
//    }
   
//    public Persona_variables(int id_persona, int Integer, int est_civil, int nro_hijos, int gestacion_h, int num_miembros_fam, boolean municipio, String esp_municipio, boolean area, String esp_area, int vivienda, int nivel_inst, boolean condicion_act, int ocupacion, String esp_ocupacion, String cargo, int ingre_economico, BigDecimal monto_aprox_bs, BigDecimal aporte_familiar_bs, String idiomas, String esp_idioma, boolean etnia, String esp_etnia, boolean lgbti, boolean pers_discapacidad) {
//        this.id_persona = id_persona;
//        this.edad = edad;
//        this.est_civil = est_civil;
//        this.nro_hijos = nro_hijos;
//        this.gestacion_h = gestacion_h;
//        this.num_miembros_fam = num_miembros_fam;
//        this.municipio = municipio;
//        this.esp_municipio = esp_municipio;
//        this.area = area;
//        this.esp_area = esp_area;
//        this.vivienda = vivienda;
//        this.nivel_inst = nivel_inst;
//        this.condicion_act = condicion_act;
//        this.ocupacion = ocupacion;
//        this.esp_ocupacion = esp_ocupacion;
//        this.cargo = cargo;
//        this.ingre_economico = ingre_economico;
//        this.monto_aprox_bs = monto_aprox_bs;
//        this.aporte_familiar_bs = aporte_familiar_bs;
//        this.idiomas = idiomas;
//        this.esp_idioma = esp_idioma;
//        this.etnia = etnia;
//        this.esp_etnia = esp_etnia;
//        this.lgbti = lgbti;
//        this.pers_discapacidad = pers_discapacidad;
//    }

    
//    public Persona_variables(Integer edad, int est_civil, int nro_hijos, int gestacion_h, int num_miembros_fam, boolean municipio, String esp_municipio, boolean area, String esp_area, int vivienda, int nivel_inst, boolean condicion_act, int ocupacion, String esp_ocupacion, String cargo, int ingre_economico, BigDecimal monto_aprox_bs, BigDecimal aporte_familiar_bs, String idiomas, String esp_idioma, boolean etnia, String esp_etnia, boolean lgbti, boolean pers_discapacidad) {
//        this.edad = edad;
//        this.est_civil = est_civil;
//        this.nro_hijos = nro_hijos;
//        this.gestacion_h = gestacion_h;
//        this.num_miembros_fam = num_miembros_fam;
//        this.municipio = municipio;
//        this.esp_municipio = esp_municipio;
//        this.area = area;
//        this.esp_area = esp_area;
//        this.vivienda = vivienda;
//        this.nivel_inst = nivel_inst;
//        this.condicion_act = condicion_act;
//        this.ocupacion = ocupacion;
//        this.esp_ocupacion = esp_ocupacion;
//        this.cargo = cargo;
//        this.ingre_economico = ingre_economico;
//        this.monto_aprox_bs = monto_aprox_bs;
//        this.aporte_familiar_bs = aporte_familiar_bs;
//        this.idiomas = idiomas;
//        this.esp_idioma = esp_idioma;
//        this.etnia = etnia;
//        this.esp_etnia = esp_etnia;
//        this.lgbti = lgbti;
//        this.pers_discapacidad = pers_discapacidad;
//
//    }
           
    public int getId_pervar() {
        return id_pervar;
    }

    public void setId_pervar(int id_pervar) {
        this.id_pervar = id_pervar;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public int getEst_civil() {
        return est_civil;
    }

    public void setEst_civil(int est_civil) {
        this.est_civil = est_civil;
    }

    public int getNro_hijos() {
        return nro_hijos;
    }

    public void setNro_hijos(int nro_hijos) {
        this.nro_hijos = nro_hijos;
    }

    public int getGestacion_h() {
        return gestacion_h;
    }

    public void setGestacion_h(int gestacion_h) {
        this.gestacion_h = gestacion_h;
    }

    public int getNum_miembros_fam() {
        return num_miembros_fam;
    }

    public void setNum_miembros_fam(int num_miembros_fam) {
        this.num_miembros_fam = num_miembros_fam;
    }

    public boolean isMunicipio() {
        return municipio;
    }

    public void setMunicipio(boolean municipio) {
        this.municipio = municipio;
    }

    public String getEsp_municipio() {
        return esp_municipio;
    }

    public void setEsp_municipio(String esp_municipio) {
        this.esp_municipio = esp_municipio;
    }

    public boolean isArea() {
        return area;
    }

    public void setArea(boolean area) {
        this.area = area;
    }

    public String getEsp_area() {
        return esp_area;
    }

    public void setEsp_area(String esp_area) {
        this.esp_area = esp_area;
    }

    public int getVivienda() {
        return vivienda;
    }

    public void setVivienda(int vivienda) {
        this.vivienda = vivienda;
    }

    public int getNivel_inst() {
        return nivel_inst;
    }

    public void setNivel_inst(int nivel_inst) {
        this.nivel_inst = nivel_inst;
    }

    public boolean isCondicion_act() {
        return condicion_act;
    }

    public void setCondicion_act(boolean condicion_act) {
        this.condicion_act = condicion_act;
    }

    public int getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(int ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getEsp_ocupacion() {
        return esp_ocupacion;
    }

    public void setEsp_ocupacion(String esp_ocupacion) {
        this.esp_ocupacion = esp_ocupacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getIngre_economico() {
        return ingre_economico;
    }

    public void setIngre_economico(int ingre_economico) {
        this.ingre_economico = ingre_economico;
    }

    public BigDecimal getMonto_aprox_bs() {
        return monto_aprox_bs;
    }

    public void setMonto_aprox_bs(BigDecimal monto_aprox_bs) {
        this.monto_aprox_bs = monto_aprox_bs;
    }

    public BigDecimal getAporte_familiar_bs() {
        return aporte_familiar_bs;
    }

    public void setAporte_familiar_bs(BigDecimal aporte_familiar_bs) {
        this.aporte_familiar_bs = aporte_familiar_bs;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getEsp_idioma() {
        return esp_idioma;
    }

    public void setEsp_idioma(String esp_idioma) {
        this.esp_idioma = esp_idioma;
    }

    public boolean isEtnia() {
        return etnia;
    }

    public void setEtnia(boolean etnia) {
        this.etnia = etnia;
    }

    public String getEsp_etnia() {
        return esp_etnia;
    }

    public void setEsp_etnia(String esp_etnia) {
        this.esp_etnia = esp_etnia;
    }

    public boolean isLgbti() {
        return lgbti;
    }

    public void setLgbti(boolean lgbti) {
        this.lgbti = lgbti;
    }

    public boolean isPers_discapacidad() {
        return pers_discapacidad;
    }

    public void setPers_discapacidad(boolean pers_discapacidad) {
        this.pers_discapacidad = pers_discapacidad;
    }
            
}

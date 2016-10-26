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
public class Victima_agresor implements Serializable{
    private int id_vicagre;
    private String testimonio;
    private int lugar_agresion;
    private boolean municipio;
    private String esp_municipio;
    private boolean area;
    private String esp_area;
    private int parentesco;
    private String esp_parentesco;
    private String tviolencia;
    private boolean denuncio;
    private String donde_denuncio;
    private int frec_agresiones;
    private boolean agre_consume_alcohol;
    private Integer frec_consumo_alcohol;
    private boolean agre_consume_drogas;
    private Integer frec_consumo_drogas;
    private String esp_violencia;
    /* INICIO ALERTAS*/
    private int preg1;
    private int preg2;
    private int preg3;   
    private int preg4;
    private int preg5;
    private int preg6;
    private int preg7;
    private int preg8;
    private int preg9;
    private int preg10;
    private int preg11;
    private int preg12;
    private int preg13;
    private int preg14;
    private int preg15;
    private int preg16;
    private int preg17;
    private int preg18;
    private int preg19;
    private int preg_suma;
    /*FIN ALERTAS*/

        
    public Victima_agresor() {
    }

    public Victima_agresor(String testimonio, int lugar_agresion, boolean municipio, String esp_municipio, boolean area, String esp_area, int parentesco, String esp_parentesco, String tviolencia, boolean denuncio, String donde_denuncio, int frec_agresiones, boolean agre_consume_alcohol, int frec_consumo_alcohol, boolean agre_consume_drogas, int frec_consumo_drogas
    ,int preg1,int preg2, int preg3, int preg4, int preg5, int preg6, int preg7, int preg8, int preg9
    , int preg10, int preg11, int preg12, int preg13, int preg14, int preg15, int preg16, int preg17, int preg18, int preg19, int preg_suma) {
        this.testimonio = testimonio;
        this.lugar_agresion = lugar_agresion;
        this.municipio = municipio;
        this.esp_municipio = esp_municipio;
        this.area = area;
        this.esp_area = esp_area;
        this.parentesco = parentesco;
        this.esp_parentesco = esp_parentesco;
        this.tviolencia = tviolencia;
        this.denuncio = denuncio;
        this.donde_denuncio = donde_denuncio;
        this.frec_agresiones = frec_agresiones;
        this.agre_consume_alcohol = agre_consume_alcohol;
        this.frec_consumo_alcohol = frec_consumo_alcohol;
        this.agre_consume_drogas = agre_consume_drogas;
        this.frec_consumo_drogas = frec_consumo_drogas;
        this.preg1 = preg1;
        this.preg2 = preg2;
        this.preg3 = preg3;        
        this.preg4 = preg4;
        this.preg5 = preg5;
        this.preg6 = preg6;
        this.preg7 = preg7;
        this.preg8 = preg8;
        this.preg9 = preg9;
        this.preg10 = preg10;
        this.preg11 = preg11;
        this.preg12 = preg12;
        this.preg13 = preg13;
        this.preg14 = preg14;
        this.preg15 = preg15;
        this.preg16 = preg16;
        this.preg17 = preg17;
        this.preg18 = preg18;
        this.preg19 = preg19;
        this.preg_suma = preg_suma;
                
    }

    public Victima_agresor(int id_vicagre, String testimonio, int lugar_agresion, boolean municipio, String esp_municipio, boolean area, String esp_area, int parentesco, String esp_parentesco, String tviolencia, boolean denuncio, String donde_denuncio, int frec_agresiones, boolean agre_consume_alcohol, int frec_consumo_alcohol, boolean agre_consume_drogas, int frec_consumo_drogas
    , int preg1,int preg2, int preg3, int preg4, int preg5, int preg6, int preg7, int preg8, int preg9
    , int preg10, int preg11, int preg12, int preg13, int preg14, int preg15, int preg16, int preg17, int preg18, int preg19, int preg_suma) {
        this.id_vicagre = id_vicagre;
        this.testimonio = testimonio;
        this.lugar_agresion = lugar_agresion;
        this.municipio = municipio;
        this.esp_municipio = esp_municipio;
        this.area = area;
        this.esp_area = esp_area;
        this.parentesco = parentesco;
        this.esp_parentesco = esp_parentesco;
        this.tviolencia = tviolencia;
        this.denuncio = denuncio;
        this.donde_denuncio = donde_denuncio;
        this.frec_agresiones = frec_agresiones;
        this.agre_consume_alcohol = agre_consume_alcohol;
        this.frec_consumo_alcohol = frec_consumo_alcohol;
        this.agre_consume_drogas = agre_consume_drogas;
        this.frec_consumo_drogas = frec_consumo_drogas;
        this.preg1 = preg1;
        this.preg2 = preg2;
        this.preg3 = preg3;        
        this.preg4 = preg4;
        this.preg5 = preg5;
        this.preg6 = preg6;
        this.preg7 = preg7;
        this.preg8 = preg8;
        this.preg9 = preg9;
        this.preg10 = preg10;
        this.preg11 = preg11;
        this.preg12 = preg12;
        this.preg13 = preg13;
        this.preg14 = preg14;
        this.preg15 = preg15;
        this.preg16 = preg16;
        this.preg17 = preg17;
        this.preg18 = preg18;
        this.preg19 = preg19;
        this.preg_suma = preg_suma;
    }

    public Victima_agresor(int id_vicagre, String testimonio, int lugar_agresion, boolean municipio, String esp_municipio, boolean area, String esp_area, int parentesco, String esp_parentesco, String tviolencia, boolean denuncio, String donde_denuncio, int frec_agresiones, boolean agre_consume_alcohol, int frec_consumo_alcohol, boolean agre_consume_drogas, int frec_consumo_drogas, String esp_violencia
    , int preg1, int preg2, int preg3, int preg4, int preg5, int preg6, int preg7, int preg8, int preg9
    , int preg10, int preg11, int preg12, int preg13, int preg14, int preg15, int preg16, int preg17, int preg18, int preg19, int preg_suma) {
        this.id_vicagre = id_vicagre;
        this.testimonio = testimonio;
        this.lugar_agresion = lugar_agresion;
        this.municipio = municipio;
        this.esp_municipio = esp_municipio;
        this.area = area;
        this.esp_area = esp_area;
        this.parentesco = parentesco;
        this.esp_parentesco = esp_parentesco;
        this.tviolencia = tviolencia;
        this.denuncio = denuncio;
        this.donde_denuncio = donde_denuncio;
        this.frec_agresiones = frec_agresiones;
        this.agre_consume_alcohol = agre_consume_alcohol;
        this.frec_consumo_alcohol = frec_consumo_alcohol;
        this.agre_consume_drogas = agre_consume_drogas;
        this.frec_consumo_drogas = frec_consumo_drogas;
        this.esp_violencia = esp_violencia;
        this.preg1 = preg1;
        this.preg2 = preg2;
        this.preg3 = preg3;        
        this.preg4 = preg4;
        this.preg5 = preg5;
        this.preg6 = preg6;
        this.preg7 = preg7;
        this.preg8 = preg8;
        this.preg9 = preg9;
        this.preg10 = preg10;
        this.preg11 = preg11;
        this.preg12 = preg12;
        this.preg13 = preg13;
        this.preg14 = preg14;
        this.preg15 = preg15;
        this.preg16 = preg16;
        this.preg17 = preg17;
        this.preg18 = preg18;
        this.preg19 = preg19;
        this.preg_suma = preg_suma;
    }
    
    /*INICIO ALERTAS*/
    
    public int getPreg1() {
        return preg1;
    }

    public void setPreg1(int preg1) {
        this.preg1 = preg1;
    }

    public int getPreg2() {
        return preg2;
    }

    public void setPreg2(int preg2) {
        this.preg2 = preg2;
    }

    public int getPreg3() {
        return preg3;
    }
    
    public void setPreg3(int preg3) {
        this.preg3 = preg3;
    }
    
     public int getPreg4() {
        return preg4;
    }

    public void setPreg4(int preg4) {
        this.preg4 = preg4;
    }

    public int getPreg5() {
        return preg5;
    }

    public void setPreg5(int preg5) {
        this.preg5 = preg5;
    }

    public int getPreg6() {
        return preg6;
    }

    public void setPreg6(int preg6) {
        this.preg6 = preg6;
    }

    public int getPreg7() {
        return preg7;
    }

    public void setPreg7(int preg7) {
        this.preg7 = preg7;
    }

    public int getPreg8() {
        return preg8;
    }

    public void setPreg8(int preg8) {
        this.preg8 = preg8;
    }

    public int getPreg9() {
        return preg9;
    }

    public void setPreg9(int preg9) {
        this.preg9 = preg9;
    }

    public int getPreg10() {
        return preg10;
    }

    public void setPreg10(int preg10) {
        this.preg10 = preg10;
    }

    public int getPreg11() {
        return preg11;
    }

    public void setPreg11(int preg11) {
        this.preg11 = preg11;
    }

    public int getPreg12() {
        return preg12;
    }

    public void setPreg12(int preg12) {
        this.preg12 = preg12;
    }

    public int getPreg13() {
        return preg13;
    }

    public void setPreg13(int preg13) {
        this.preg13 = preg13;
    }

    public int getPreg14() {
        return preg14;
    }

    public void setPreg14(int preg14) {
        this.preg14 = preg14;
    }

    public int getPreg15() {
        return preg15;
    }

    public void setPreg15(int preg15) {
        this.preg15 = preg15;
    }

    public int getPreg16() {
        return preg16;
    }

    public void setPreg16(int preg16) {
        this.preg16 = preg16;
    }

    public int getPreg17() {
        return preg17;
    }

    public void setPreg17(int preg17) {
        this.preg17 = preg17;
    }

    public int getPreg18() {
        return preg18;
    }

    public void setPreg18(int preg18) {
        this.preg18 = preg18;
    }

    public int getPreg19() {
        return preg19;
    }

    public void setPreg19(int preg19) {
        this.preg19 = preg19;
    }
    
    public int getPreg_suma() {
        return preg_suma;
    }

    public void setPreg_suma(int preg_suma) {
        this.preg_suma = preg_suma;
    }
    
    /*FIN ALERTAS*/
    
    public int getId_vicagre() {
        return id_vicagre;
    }

    public void setId_vicagre(int id_vicagre) {
        this.id_vicagre = id_vicagre;
    }

    public String getTestimonio() {
        return testimonio;
    }

    public void setTestimonio(String testimonio) {
        this.testimonio = testimonio;
    }

    public int getLugar_agresion() {
        return lugar_agresion;
    }

    public void setLugar_agresion(int lugar_agresion) {
        this.lugar_agresion = lugar_agresion;
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

    public int getParentesco() {
        return parentesco;
    }

    public void setParentesco(int parentesco) {
        this.parentesco = parentesco;
    }

    public String getEsp_parentesco() {
        return esp_parentesco;
    }

    public void setEsp_parentesco(String esp_parentesco) {
        this.esp_parentesco = esp_parentesco;
    }

    public String getTviolencia() {
        return tviolencia;
    }

    public void setTviolencia(String tviolencia) {
        this.tviolencia = tviolencia;
    }

    public boolean isDenuncio() {
        return denuncio;
    }

    public void setDenuncio(boolean denuncio) {
        this.denuncio = denuncio;
    }

    public String getDonde_denuncio() {
        return donde_denuncio;
    }

    public void setDonde_denuncio(String donde_denuncio) {
        this.donde_denuncio = donde_denuncio;
    }

    public int getFrec_agresiones() {
        return frec_agresiones;
    }

    public void setFrec_agresiones(int frec_agresiones) {
        this.frec_agresiones = frec_agresiones;
    }

    public boolean isAgre_consume_alcohol() {
        return agre_consume_alcohol;
    }

    public void setAgre_consume_alcohol(boolean agre_consume_alcohol) {
        this.agre_consume_alcohol = agre_consume_alcohol;
    }

    public Integer getFrec_consumo_alcohol() {
        return frec_consumo_alcohol;
    }

    public void setFrec_consumo_alcohol(Integer frec_consumo_alcohol) {
        this.frec_consumo_alcohol = frec_consumo_alcohol;
    }

    public boolean isAgre_consume_drogas() {
        return agre_consume_drogas;
    }

    public void setAgre_consume_drogas(boolean agre_consume_drogas) {
        this.agre_consume_drogas = agre_consume_drogas;
    }

    public Integer getFrec_consumo_drogas() {
        return frec_consumo_drogas;
    }

    public void setFrec_consumo_drogas(Integer frec_consumo_drogas) {
        this.frec_consumo_drogas = frec_consumo_drogas;
    }

    public String getEsp_violencia() {
        return esp_violencia;
    }

    public void setEsp_violencia(String esp_violencia) {
        this.esp_violencia = esp_violencia;
    }

    
}

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
public class VicAgre_persona implements Serializable{
    private int id_vicAgre_persona;
    private int victima_agresor_id_vicagre;
    private int persona_id_persona_agresor;
    private int persona_id_persona_victima;
    private String referencia_caso_de;
    private String referencia_esp_caso;
    private String contra_ref_caso_de;
    private String contra_ref_esp_caso;
    private boolean caso_resuelto;
    private boolean caso_abandonado;
    private int persona_var_victima_id_pervar;
    private int persona_var_agresor_id_pervar;

    public VicAgre_persona() {
    }

    public VicAgre_persona(int id_vicAgre_persona, int victima_agresor_id_vicagre, int persona_id_persona_agresor, int persona_id_persona_victima, String referencia_caso_de, String referencia_esp_caso, String contra_ref_caso_de, String contra_ref_esp_caso, boolean caso_resuelto, boolean caso_abandonado) {
        this.id_vicAgre_persona = id_vicAgre_persona;
        this.victima_agresor_id_vicagre = victima_agresor_id_vicagre;
        this.persona_id_persona_agresor = persona_id_persona_agresor;
        this.persona_id_persona_victima = persona_id_persona_victima;
        this.referencia_caso_de = referencia_caso_de;
        this.referencia_esp_caso = referencia_esp_caso;
        this.contra_ref_caso_de = contra_ref_caso_de;
        this.contra_ref_esp_caso = contra_ref_esp_caso;
        this.caso_resuelto = caso_resuelto;
        this.caso_abandonado = caso_abandonado;
    }

    public VicAgre_persona(int victima_agresor_id_vicagre, int persona_id_persona_agresor, int persona_id_persona_victima, String referencia_caso_de, String referencia_esp_caso, String contra_ref_caso_de, String contra_ref_esp_caso, boolean caso_resuelto, boolean caso_abandonado) {
        this.victima_agresor_id_vicagre = victima_agresor_id_vicagre;
        this.persona_id_persona_agresor = persona_id_persona_agresor;
        this.persona_id_persona_victima = persona_id_persona_victima;
        this.referencia_caso_de = referencia_caso_de;
        this.referencia_esp_caso = referencia_esp_caso;
        this.contra_ref_caso_de = contra_ref_caso_de;
        this.contra_ref_esp_caso = contra_ref_esp_caso;
        this.caso_resuelto = caso_resuelto;
        this.caso_abandonado = caso_abandonado;
    }

    public VicAgre_persona(int id_vicAgre_persona, int victima_agresor_id_vicagre, int persona_id_persona_agresor, int persona_id_persona_victima, String referencia_caso_de, String referencia_esp_caso, String contra_ref_caso_de, String contra_ref_esp_caso, boolean caso_resuelto, boolean caso_abandonado, int persona_var_victima_id_pervar, int persona_var_agresor_id_pervar) {
        this.id_vicAgre_persona = id_vicAgre_persona;
        this.victima_agresor_id_vicagre = victima_agresor_id_vicagre;
        this.persona_id_persona_agresor = persona_id_persona_agresor;
        this.persona_id_persona_victima = persona_id_persona_victima;
        this.referencia_caso_de = referencia_caso_de;
        this.referencia_esp_caso = referencia_esp_caso;
        this.contra_ref_caso_de = contra_ref_caso_de;
        this.contra_ref_esp_caso = contra_ref_esp_caso;
        this.caso_resuelto = caso_resuelto;
        this.caso_abandonado = caso_abandonado;
        this.persona_var_victima_id_pervar = persona_var_victima_id_pervar;
        this.persona_var_agresor_id_pervar = persona_var_agresor_id_pervar;
    }

    public int getId_vicAgre_persona() {
        return id_vicAgre_persona;
    }

    public void setId_vicAgre_persona(int id_vicAgre_persona) {
        this.id_vicAgre_persona = id_vicAgre_persona;
    }

    public int getVictima_agresor_id_vicagre() {
        return victima_agresor_id_vicagre;
    }

    public void setVictima_agresor_id_vicagre(int victima_agresor_id_vicagre) {
        this.victima_agresor_id_vicagre = victima_agresor_id_vicagre;
    }

    public int getPersona_id_persona_agresor() {
        return persona_id_persona_agresor;
    }

    public void setPersona_id_persona_agresor(int persona_id_persona_agresor) {
        this.persona_id_persona_agresor = persona_id_persona_agresor;
    }

    public int getPersona_id_persona_victima() {
        return persona_id_persona_victima;
    }

    public void setPersona_id_persona_victima(int persona_id_persona_victima) {
        this.persona_id_persona_victima = persona_id_persona_victima;
    }

    public String getReferencia_caso_de() {
        return referencia_caso_de;
    }

    public void setReferencia_caso_de(String referencia_caso_de) {
        this.referencia_caso_de = referencia_caso_de;
    }

    public String getReferencia_esp_caso() {
        return referencia_esp_caso;
    }

    public void setReferencia_esp_caso(String referencia_esp_caso) {
        this.referencia_esp_caso = referencia_esp_caso;
    }

    public String getContra_ref_caso_de() {
        return contra_ref_caso_de;
    }

    public void setContra_ref_caso_de(String contra_ref_caso_de) {
        this.contra_ref_caso_de = contra_ref_caso_de;
    }

    public String getContra_ref_esp_caso() {
        return contra_ref_esp_caso;
    }

    public void setContra_ref_esp_caso(String contra_ref_esp_caso) {
        this.contra_ref_esp_caso = contra_ref_esp_caso;
    }

    public boolean isCaso_resuelto() {
        return caso_resuelto;
    }

    public void setCaso_resuelto(boolean caso_resuelto) {
        this.caso_resuelto = caso_resuelto;
    }

    public boolean isCaso_abandonado() {
        return caso_abandonado;
    }

    public void setCaso_abandonado(boolean caso_abandonado) {
        this.caso_abandonado = caso_abandonado;
    }

    public int getPersona_var_victima_id_pervar() {
        return persona_var_victima_id_pervar;
    }

    public void setPersona_var_victima_id_pervar(int persona_var_victima_id_pervar) {
        this.persona_var_victima_id_pervar = persona_var_victima_id_pervar;
    }

    public int getPersona_var_agresor_id_pervar() {
        return persona_var_agresor_id_pervar;
    }

    public void setPersona_var_agresor_id_pervar(int persona_var_agresor_id_pervar) {
        this.persona_var_agresor_id_pervar = persona_var_agresor_id_pervar;
    }
    
    
}

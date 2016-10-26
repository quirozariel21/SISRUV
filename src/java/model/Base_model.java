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
public class Base_model implements Serializable{
    private Persona personaVictima;
    private Persona_variables personaVariableVictima;
    private Persona personaAgresor;
    private Persona_variables personaVariableAgresor;
    private Victima_agresor victima_agresor;
    private VicAgre_persona vicAgre_persona;
    private Usuario_vicagre usuario_vicagre;
    /* INICIO Alertas */
    private Alerta alerta;

  
    /* FIN Alertas */
    public Base_model() {
        personaVictima = new Persona();
        personaVariableVictima = new Persona_variables();
        personaAgresor = new Persona();
        personaVariableAgresor = new Persona_variables();
        victima_agresor = new Victima_agresor();
        vicAgre_persona = new VicAgre_persona();
        usuario_vicagre = new Usuario_vicagre();
        /* INICIO Alertas */
        //alerta = new Alerta();
    /* FIN Alertas */
    }

    public Base_model(Persona personaVictima, Persona_variables personaVariableVictima, Persona personaAgresor, Persona_variables personaVictimaVariableAgresor, Victima_agresor victima_agresor, VicAgre_persona vicAgre_persona) {
        this.personaVictima = personaVictima;
        this.personaVariableVictima = personaVariableVictima;
        this.personaAgresor = personaAgresor;
        this.personaVariableAgresor = personaVictimaVariableAgresor;
        this.victima_agresor = victima_agresor;
        this.vicAgre_persona = vicAgre_persona;
    }

    public Base_model(Persona personaVictima, Persona_variables personaVariableVictima, Persona personaAgresor, Persona_variables personaVariableAgresor, Victima_agresor victima_agresor, VicAgre_persona vicAgre_persona, Usuario_vicagre usuario_vicagre) {
        this.personaVictima = personaVictima;
        this.personaVariableVictima = personaVariableVictima;
        this.personaAgresor = personaAgresor;
        this.personaVariableAgresor = personaVariableAgresor;
        this.victima_agresor = victima_agresor;
        this.vicAgre_persona = vicAgre_persona;
        this.usuario_vicagre = usuario_vicagre;
        //this.alerta = alerta;
    }
    /*Alerta */    
    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }
    /*Alerta */    
    public Persona getPersonaVictima() {
        return personaVictima;
    }

    public void setPersonaVictima(Persona personaVictima) {
        this.personaVictima = personaVictima;
    }

    public Persona_variables getPersonaVariableVictima() {
        return personaVariableVictima;
    }

    public void setPersonaVariableVictima(Persona_variables personaVariableVictima) {
        this.personaVariableVictima = personaVariableVictima;
    }

    public Persona getPersonaAgresor() {
        return personaAgresor;
    }

    public void setPersonaAgresor(Persona personaAgresor) {
        this.personaAgresor = personaAgresor;
    }

    public Persona_variables getPersonaVariableAgresor() {
        return personaVariableAgresor;
    }

    public void setPersonaVariableAgresor(Persona_variables personaVictimaVariableAgresor) {
        this.personaVariableAgresor = personaVictimaVariableAgresor;
    }

    public Victima_agresor getVictima_agresor() {
        return victima_agresor;
    }

    public void setVictima_agresor(Victima_agresor victima_agresor) {
        this.victima_agresor = victima_agresor;
    }

    public VicAgre_persona getVicAgre_persona() {
        return vicAgre_persona;
    }

    public void setVicAgre_persona(VicAgre_persona vicAgre_persona) {
        this.vicAgre_persona = vicAgre_persona;
    }

    public Usuario_vicagre getUsuario_vicagre() {
        return usuario_vicagre;
    }

    public void setUsuario_vicagre(Usuario_vicagre usuario_vicagre) {
        this.usuario_vicagre = usuario_vicagre;
    }

}

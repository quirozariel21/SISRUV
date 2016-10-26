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
public class Base_mod implements Serializable{
    private Persona persona = new Persona();
    private Persona_variables persona_variables = new Persona_variables();
    private VicAgre_persona vicAgre_persona = new VicAgre_persona();
    private Usuario_vicagre usuario_vicagre = new Usuario_vicagre();
    private int identifica;

    public Base_mod() {
    }

    public Base_mod(int identifica) {
        this.identifica = identifica;
    }
   
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona_variables getPersona_variables() {
        return persona_variables;
    }

    public void setPersona_variables(Persona_variables persona_variables) {
        this.persona_variables = persona_variables;
    }

    public VicAgre_persona getVicAgre_persona() {
        return vicAgre_persona;
    }

    public void setVicAgre_persona(VicAgre_persona vicAgre_persona) {
        this.vicAgre_persona = vicAgre_persona;
    }

    public int getIdentifica() {
        return identifica;
    }

    public void setIdentifica(int identifica) {
        this.identifica = identifica;
    }

    public Usuario_vicagre getUsuario_vicagre() {
        return usuario_vicagre;
    }

    public void setUsuario_vicagre(Usuario_vicagre usuario_vicagre) {
        this.usuario_vicagre = usuario_vicagre;
    }
    
}

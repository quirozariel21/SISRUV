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
public class Perfil implements Serializable{
    private int cod_perfil;
    private String nombre;

    public Perfil() {
    }

    public Perfil(String nombre) {
        this.nombre = nombre;
    }

    public int getCod_perfil() {
        return cod_perfil;
    }

    public void setCod_perfil(int cod_perfil) {
        this.cod_perfil = cod_perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}

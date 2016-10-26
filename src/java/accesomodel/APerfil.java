/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesomodel;

import java.io.Serializable;

/**
 *
 * @author KRETCO
 */
public class APerfil implements Serializable{
    private int cod_perfil;
    private String nombre;
    private int numeroRegistro;

    public APerfil() {
    }

    public APerfil(String nombre) {
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

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }
    
    
}

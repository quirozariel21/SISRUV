/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author e_mv
 */
public class prueba implements Serializable{
    private Integer uno;
    private String dos;
    private String tre;
    private String cua;
    private String cin;

    public prueba(Integer uno, String dos, String tre, String cua, String cin) {
        this.uno = uno;
        this.dos = dos;
        this.tre = tre;
        this.cua = cua;
        this.cin = cin;
    }

    
    public Integer getUno() {
        return uno;
    }

    public void setUno(Integer uno) {
        this.uno = uno;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getTre() {
        return tre;
    }

    public void setTre(String tre) {
        this.tre = tre;
    }

    public String getCua() {
        return cua;
    }

    public void setCua(String cua) {
        this.cua = cua;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
    
    
}

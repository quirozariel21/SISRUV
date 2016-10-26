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
public class View_report implements Serializable{
    private String descripcion;
    private String estado_civil;
    private int dato1;
    private int dato2;
    private int descrip;
    private int hombres;
    private int mujeres;
    private int total;

    public View_report() {
    }

    public View_report(String descripcion, String estado_civil, int descrip, int hombres, int mujeres, int total) {
        this.descripcion = descripcion;
        this.estado_civil = estado_civil;
        this.descrip = descrip;
        this.hombres = hombres;
        this.mujeres = mujeres;
        this.total = total;
    }

    public View_report(String descripcion, int hombres, int mujeres, int total) {
        this.descripcion = descripcion;
        this.hombres = hombres;
        this.mujeres = mujeres;
        this.total = total;
    }

    public View_report(String descripcion, int descrip, int hombres, int mujeres, int total) {
        this.descripcion = descripcion;
        this.descrip = descrip;
        this.hombres = hombres;
        this.mujeres = mujeres;
        this.total = total;
    }

    public View_report(String descripcion, String estado_civil, int dato1, int dato2, int descrip, int hombres, int mujeres, int total) {
        this.descripcion = descripcion;
        this.estado_civil = estado_civil;
        this.dato1 = dato1;
        this.dato2 = dato2;
        this.descrip = descrip;
        this.hombres = hombres;
        this.mujeres = mujeres;
        this.total = total;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getHombres() {
        return hombres;
    }

    public void setHombres(int hombres) {
        this.hombres = hombres;
    }

    public int getMujeres() {
        return mujeres;
    }

    public void setMujeres(int mujeres) {
        this.mujeres = mujeres;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDescrip() {
        return descrip;
    }

    public void setDescrip(int descrip) {
        this.descrip = descrip;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public int getDato1() {
        return dato1;
    }

    public void setDato1(int dato1) {
        this.dato1 = dato1;
    }

    public int getDato2() {
        return dato2;
    }

    public void setDato2(int dato2) {
        this.dato2 = dato2;
    }
    
}

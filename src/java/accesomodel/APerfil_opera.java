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
public class APerfil_opera implements Serializable{
    private int id_perfil_opera;
    private int perfil_cod_perfil;
    private int operacion_cod_opera;

    public APerfil_opera() {
    }

    public APerfil_opera(int id_perfil_opera, int perfil_cod_perfil, int operacion_cod_opera) {
        this.id_perfil_opera = id_perfil_opera;
        this.perfil_cod_perfil = perfil_cod_perfil;
        this.operacion_cod_opera = operacion_cod_opera;
    }

    public int getId_perfil_opera() {
        return id_perfil_opera;
    }

    public void setId_perfil_opera(int id_perfil_opera) {
        this.id_perfil_opera = id_perfil_opera;
    }

    public int getPerfil_cod_perfil() {
        return perfil_cod_perfil;
    }

    public void setPerfil_cod_perfil(int perfil_cod_perfil) {
        this.perfil_cod_perfil = perfil_cod_perfil;
    }

    public int getOperacion_cod_opera() {
        return operacion_cod_opera;
    }

    public void setOperacion_cod_opera(int operacion_cod_opera) {
        this.operacion_cod_opera = operacion_cod_opera;
    }

   
    
}

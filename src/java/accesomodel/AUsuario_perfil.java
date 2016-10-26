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
public class AUsuario_perfil implements Serializable{
    private int id_usuario_perfil;
    private int usuario_cod_usuario;
    private int perfil_cod_perfil;

    public AUsuario_perfil() {
    }

    public AUsuario_perfil(int usuario_cod_usuario, int perfil_cod_perfil) {
        this.usuario_cod_usuario = usuario_cod_usuario;
        this.perfil_cod_perfil = perfil_cod_perfil;
    }

    public int getId_usuario_perfil() {
        return id_usuario_perfil;
    }

    public void setId_usuario_perfil(int id_usuario_perfil) {
        this.id_usuario_perfil = id_usuario_perfil;
    }

    public int getUsuario_cod_usuario() {
        return usuario_cod_usuario;
    }

    public void setUsuario_cod_usuario(int usuario_cod_usuario) {
        this.usuario_cod_usuario = usuario_cod_usuario;
    }

    public int getPerfil_cod_perfil() {
        return perfil_cod_perfil;
    }

    public void setPerfil_cod_perfil(int perfil_cod_perfil) {
        this.perfil_cod_perfil = perfil_cod_perfil;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesomodel;

/**
 *
 * @author KRETCO
 */
public class AUsuarioInstitucion {
    private Integer id_usuario_institucion;
    private Integer usuario_cod_usuario;
    private Integer institucion_id_institucion;

    public AUsuarioInstitucion() {
    }

    public AUsuarioInstitucion(Integer usuario_cod_usuario, Integer institucion_id_institucion) {
        this.usuario_cod_usuario = usuario_cod_usuario;
        this.institucion_id_institucion = institucion_id_institucion;
    }

    public Integer getId_usuario_institucion() {
        return id_usuario_institucion;
    }

    public void setId_usuario_institucion(Integer id_usuario_institucion) {
        this.id_usuario_institucion = id_usuario_institucion;
    }

    public Integer getUsuario_cod_usuario() {
        return usuario_cod_usuario;
    }

    public void setUsuario_cod_usuario(Integer usuario_cod_usuario) {
        this.usuario_cod_usuario = usuario_cod_usuario;
    }

    public Integer getInstitucion_id_institucion() {
        return institucion_id_institucion;
    }

    public void setInstitucion_id_institucion(Integer institucion_id_institucion) {
        this.institucion_id_institucion = institucion_id_institucion;
    }
    
}

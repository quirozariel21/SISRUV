/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesomodel;

/**
 *
 * @author e_mv
 */
public class AAplicaciones {

    private Integer id_app;
    private String descripcion;
    private String ip_db;
    private String nombre_db;
    private String usuario_db;
    private String password_db;
    private boolean t_datos;

    public Integer getId_app() {
        return id_app;
    }

    public void setId_app(Integer id_app) {
        this.id_app = id_app;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIp_db() {
        return ip_db;
    }

    public void setIp_db(String ip_db) {
        this.ip_db = ip_db;
    }

    public String getNombre_db() {
        return nombre_db;
    }

    public void setNombre_db(String nombre_db) {
        this.nombre_db = nombre_db;
    }

    public String getUsuario_db() {
        return usuario_db;
    }

    public void setUsuario_db(String usuario_db) {
        this.usuario_db = usuario_db;
    }

    public String getPassword_db() {
        return password_db;
    }

    public void setPassword_db(String password_db) {
        this.password_db = password_db;
    }

    public boolean isT_datos() {
        return t_datos;
    }

    public void setT_datos(boolean t_datos) {
        this.t_datos = t_datos;
    }
}

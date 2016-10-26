/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author KRETCO
 */
public class Usuario implements Serializable{
    private int cod_usuario;
    private String username;
    private String password;
    private boolean estado;
    private String nombre;
    private String paterno;
    private String materno;
    private int ci;
    private Date fecha_reg;
    private Integer departamento;
    private Integer municipio;
    private Integer cod_servicio;
    private Integer cod_sub_servicio;
    private int codigo_exp;
    private String e_mail;
    private int telefono;
    private int celular;
    private String direccion;
    private Date fecha_estado;
    private String nombre_completo;    
    public Usuario() {
    }

    public Usuario(String username, String password, boolean estado, String nombre, String paterno, String materno, int ci, Date fecha_reg, int departamento, int municipio, int cod_servicio, int cod_sub_servicio, int codigo_exp, String e_mail, int telefono, int celular, String direccion, Date fecha_estado) {
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.ci = ci;
        this.fecha_reg = fecha_reg;
        this.departamento = departamento;
        this.municipio = municipio;
        this.cod_servicio = cod_servicio;
        this.cod_sub_servicio = cod_sub_servicio;
        this.codigo_exp = codigo_exp;
        this.e_mail = e_mail;
        this.telefono = telefono;
        this.celular = celular;
        this.direccion = direccion;
        this.fecha_estado = fecha_estado;
    }

    public Usuario(String username, String password, boolean estado, String nombre, String paterno, String materno, int ci, Date fecha_reg, int departamento, int municipio, int cod_servicio, int cod_sub_servicio, int codigo_exp, String e_mail, int telefono, int celular, String direccion, Date fecha_estado, String nombre_completo) {
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.ci = ci;
        this.fecha_reg = fecha_reg;
        this.departamento = departamento;
        this.municipio = municipio;
        this.cod_servicio = cod_servicio;
        this.cod_sub_servicio = cod_sub_servicio;
        this.codigo_exp = codigo_exp;
        this.e_mail = e_mail;
        this.telefono = telefono;
        this.celular = celular;
        this.direccion = direccion;
        this.fecha_estado = fecha_estado;
        this.nombre_completo = nombre_completo;
    }

  
    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public Date getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(Date fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public Integer getCod_servicio() {
        return cod_servicio;
    }

    public void setCod_servicio(Integer cod_servicio) {
        this.cod_servicio = cod_servicio;
    }

    public Integer getCod_sub_servicio() {
        return cod_sub_servicio;
    }

    public void setCod_sub_servicio(Integer cod_sub_servicio) {
        this.cod_sub_servicio = cod_sub_servicio;
    }


    public Date getFecha_estado() {
        return fecha_estado;
    }

    public void setFecha_estado(Date fecha_estado) {
        this.fecha_estado = fecha_estado;
    }

    public int getCodigo_exp() {
        return codigo_exp;
    }

    public void setCodigo_exp(int codigo_exp) {
        this.codigo_exp = codigo_exp;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre_completo() {
        nombre_completo = nombre +" "+ paterno +" "+ materno;
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }
    
    
}

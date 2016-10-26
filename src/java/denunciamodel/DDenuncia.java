/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denunciamodel;

import java.util.Date;

/**
 *
 * @author KRETCO
 */
public class DDenuncia {
    private int id_denuncia;
    private Date fecha;
    private String nombre_victima;
    private String paterno_victima;
    private String materno_victima;
    private int ci_victima;
    private String nombre_agresor;
    private String paterno_agresor;
    private String materno_agresor;
    private int ci_agresor;
    private String tviolencia;
    private String testimonio;
    private String resolucion;
    private int cod_usuario;
    private String sexo_victima;
    private int edad_victima;
    private String sexo_agresor;
    private int edad_agresor;
    private String cargo_victima;
    private String cargo_agresor;
    private int numero_victima;
    private int numero_agresor;
    private int id_log;

    public DDenuncia() {
    }

    public DDenuncia(int id_denuncia, Date fecha, String nombre_victima, String paterno_victima, String materno_victima, int ci_victima, String nombre_agresor, String paterno_agresor, String materno_agresor, int ci_agresor, String tviolencia, String testimonio, String resolucion, int cod_usuario, String sexo_victima, int edad_victima, String sexo_agresor, int edad_agresor, String cargo_victima, String cargo_agresor, int numero_victima, int numero_agresor) {
        this.id_denuncia = id_denuncia;
        this.fecha = fecha;
        this.nombre_victima = nombre_victima;
        this.paterno_victima = paterno_victima;
        this.materno_victima = materno_victima;
        this.ci_victima = ci_victima;
        this.nombre_agresor = nombre_agresor;
        this.paterno_agresor = paterno_agresor;
        this.materno_agresor = materno_agresor;
        this.ci_agresor = ci_agresor;
        this.tviolencia = tviolencia;
        this.testimonio = testimonio;
        this.resolucion = resolucion;
        this.cod_usuario = cod_usuario;
        this.sexo_victima = sexo_victima;
        this.edad_victima = edad_victima;
        this.sexo_agresor = sexo_agresor;
        this.edad_agresor = edad_agresor;
        this.cargo_victima = cargo_victima;
        this.cargo_agresor = cargo_agresor;
        this.numero_victima = numero_victima;
        this.numero_agresor = numero_agresor;
    }

    public int getId_denuncia() {
        return id_denuncia;
    }

    public void setId_denuncia(int id_denuncia) {
        this.id_denuncia = id_denuncia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre_victima() {
        return nombre_victima;
    }

    public void setNombre_victima(String nombre_victima) {
        this.nombre_victima = nombre_victima;
    }

    public String getPaterno_victima() {
        return paterno_victima;
    }

    public void setPaterno_victima(String paterno_victima) {
        this.paterno_victima = paterno_victima;
    }

    public String getMaterno_victima() {
        return materno_victima;
    }

    public void setMaterno_victima(String materno_victima) {
        this.materno_victima = materno_victima;
    }

    public int getCi_victima() {
        return ci_victima;
    }

    public void setCi_victima(int ci_victima) {
        this.ci_victima = ci_victima;
    }

    public String getNombre_agresor() {
        return nombre_agresor;
    }

    public void setNombre_agresor(String nombre_agresor) {
        this.nombre_agresor = nombre_agresor;
    }

    public String getPaterno_agresor() {
        return paterno_agresor;
    }

    public void setPaterno_agresor(String paterno_agresor) {
        this.paterno_agresor = paterno_agresor;
    }

    public String getMaterno_agresor() {
        return materno_agresor;
    }

    public void setMaterno_agresor(String materno_agresor) {
        this.materno_agresor = materno_agresor;
    }

    public int getCi_agresor() {
        return ci_agresor;
    }

    public void setCi_agresor(int ci_agresor) {
        this.ci_agresor = ci_agresor;
    }

    public String getTviolencia() {
        return tviolencia;
    }

    public void setTviolencia(String tviolencia) {
        this.tviolencia = tviolencia;
    }

    public String getTestimonio() {
        return testimonio;
    }

    public void setTestimonio(String testimonio) {
        this.testimonio = testimonio;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getSexo_victima() {
        return sexo_victima;
    }

    public void setSexo_victima(String sexo_victima) {
        this.sexo_victima = sexo_victima;
    }

    public int getEdad_victima() {
        return edad_victima;
    }

    public void setEdad_victima(int edad_victima) {
        this.edad_victima = edad_victima;
    }

    public String getSexo_agresor() {
        return sexo_agresor;
    }

    public void setSexo_agresor(String sexo_agresor) {
        this.sexo_agresor = sexo_agresor;
    }

    public int getEdad_agresor() {
        return edad_agresor;
    }

    public void setEdad_agresor(int edad_agresor) {
        this.edad_agresor = edad_agresor;
    }

    public String getCargo_victima() {
        return cargo_victima;
    }

    public void setCargo_victima(String cargo_victima) {
        this.cargo_victima = cargo_victima;
    }

    public String getCargo_agresor() {
        return cargo_agresor;
    }

    public void setCargo_agresor(String cargo_agresor) {
        this.cargo_agresor = cargo_agresor;
    }

    public int getNumero_victima() {
        return numero_victima;
    }

    public void setNumero_victima(int numero_victima) {
        this.numero_victima = numero_victima;
    }

    public int getNumero_agresor() {
        return numero_agresor;
    }

    public void setNumero_agresor(int numero_agresor) {
        this.numero_agresor = numero_agresor;
    }

    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresmodel;

import java.util.Date;

/**
 *
 * @author KRETCO
 */
public class IDenuncia {
    private int id_denuncia;
    private Date fecha;
    private String nombre_victima;
    private String paterno_victima;
    private String materno_victima;
    private String ci_victima;
    private String nombre_agresor;
    private String paterno_agresor;
    private String materno_agresor;
    private String ci_agresor;
    private int tv_fisica;
    private int tv_psicologica;
    private int tv_sexual;
    private int tv_economica;
    private String testimonio;
    private String resolucion;
    private int id_institucion;
    private String sexo_victima;
    private int edad_victima;
    private String sexo_agresor;
    private int edad_agresor;
    private String cargo_victima;
    private String cargo_agresor;
    private int numero_victima;
    private int numero_agresor;

    public IDenuncia() {
    }

    public IDenuncia(int id_denuncia, Date fecha, String nombre_victima, String paterno_victima, String materno_victima, String ci_victima, String nombre_agresor, String paterno_agresor, String materno_agresor, String ci_agresor, int tv_fisica, int tv_psicologica, int tv_sexual, int tv_economica, String testimonio, String resolucion, int id_institucion, String sexo_victima, int edad_victima, String sexo_agresor, int edad_agresor) {
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
        this.tv_fisica = tv_fisica;
        this.tv_psicologica = tv_psicologica;
        this.tv_sexual = tv_sexual;
        this.tv_economica = tv_economica;
        this.testimonio = testimonio;
        this.resolucion = resolucion;
        this.id_institucion = id_institucion;
        this.sexo_victima = sexo_victima;
        this.edad_victima = edad_victima;
        this.sexo_agresor = sexo_agresor;
        this.edad_agresor = edad_agresor;
    }

    public IDenuncia(int id_denuncia, Date fecha, String nombre_victima, String paterno_victima, String materno_victima, String ci_victima, String nombre_agresor, String paterno_agresor, String materno_agresor, String ci_agresor, int tv_fisica, int tv_psicologica, int tv_sexual, int tv_economica, String testimonio, String resolucion, int id_institucion, String sexo_victima, int edad_victima, String sexo_agresor, int edad_agresor, String cargo_victima, String cargo_agresor, int numero_victima, int numero_agresor) {
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
        this.tv_fisica = tv_fisica;
        this.tv_psicologica = tv_psicologica;
        this.tv_sexual = tv_sexual;
        this.tv_economica = tv_economica;
        this.testimonio = testimonio;
        this.resolucion = resolucion;
        this.id_institucion = id_institucion;
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

    public String getCi_victima() {
        return ci_victima;
    }

    public void setCi_victima(String ci_victima) {
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

    public String getCi_agresor() {
        return ci_agresor;
    }

    public void setCi_agresor(String ci_agresor) {
        this.ci_agresor = ci_agresor;
    }

    public int getTv_fisica() {
        return tv_fisica;
    }

    public void setTv_fisica(int tv_fisica) {
        this.tv_fisica = tv_fisica;
    }

    public int getTv_psicologica() {
        return tv_psicologica;
    }

    public void setTv_psicologica(int tv_psicologica) {
        this.tv_psicologica = tv_psicologica;
    }

    public int getTv_sexual() {
        return tv_sexual;
    }

    public void setTv_sexual(int tv_sexual) {
        this.tv_sexual = tv_sexual;
    }

    public int getTv_economica() {
        return tv_economica;
    }

    public void setTv_economica(int tv_economica) {
        this.tv_economica = tv_economica;
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

    public int getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(int id_institucion) {
        this.id_institucion = id_institucion;
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

    
    
}

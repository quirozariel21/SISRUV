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
public class AInstitucion {
    private Integer id_institucion;
    private String nombre;
    private String dependiente;
    private String tipo;
    private int numRegistro;
    private String usuariodesignado;

    public AInstitucion() {
    }

    public AInstitucion(Integer id_institucion, String nombre, String dependiente, String tipo) {
        this.id_institucion = id_institucion;
        this.nombre = nombre;
        this.dependiente = dependiente;
        this.tipo = tipo;
    }

    public Integer getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(Integer id_institucion) {
        this.id_institucion = id_institucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDependiente() {
        return dependiente;
    }

    public void setDependiente(String dependiente) {
        this.dependiente = dependiente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(int numRegistro) {
        this.numRegistro = numRegistro;
    }

    public String getUsuariodesignado() {
        return usuariodesignado;
    }

    public void setUsuariodesignado(String usuariodesignado) {
        this.usuariodesignado = usuariodesignado;
    }
    
    
}

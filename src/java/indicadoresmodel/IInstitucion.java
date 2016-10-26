/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresmodel;

/**
 *
 * @author KRETCO
 */
public class IInstitucion {
    private int id_institucion;
    private String nombre;
    private String dependiente;

    public IInstitucion() {
    }

    public IInstitucion(int id_institucion, String nombre, String dependiente) {
        this.id_institucion = id_institucion;
        this.nombre = nombre;
        this.dependiente = dependiente;
    }

    public int getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(int id_institucion) {
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
    
}

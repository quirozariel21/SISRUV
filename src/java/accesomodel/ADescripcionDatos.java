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
public class ADescripcionDatos {

   private Integer id_t;
    private String descripcion;
    private boolean por_niveles;
    private Integer id_padre;

    public Integer getId_t() {
        return id_t;
    }

    public void setId_t(Integer id_t) {
        this.id_t = id_t;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isPor_niveles() {
        return por_niveles;
    }

    public void setPor_niveles(boolean por_niveles) {
        this.por_niveles = por_niveles;
    }

    public Integer getId_padre() {
        return id_padre;
    }

    public void setId_padre(Integer id_padre) {
        this.id_padre = id_padre;
    }
}

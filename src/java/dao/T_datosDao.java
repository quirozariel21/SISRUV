/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.faces.model.SelectItem;
import model.T_datos;

/**
 *
 * @author KRETCO
 */
public interface T_datosDao {
    public List<T_datos> findAll();
    public List<SelectItem> findAll_idt(String id);
    public List<SelectItem> findAll_idt_recepcion(String descrip);
    public List<T_datos> findAllIdt(String id);
    public List<T_datos> findAllIdt_servlet(String id, String gestorBD, String servidor, String baseDato, String usuario, String password);
    public boolean delete(T_datos t_datos);
    public List<SelectItem> listMunicipìos(Integer dpto);
    public List<SelectItem> listServicios(Integer municipio);
    public List<SelectItem> listSubServicios(Integer servicio);
    
}

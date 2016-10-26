/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.util.List;
import javax.faces.model.SelectItem;
import accesomodel.AT_datos;

/**
 *
 * @author KRETCO
 */
public interface AT_datosDao {
    public List<AT_datos> findAll();
    public List<SelectItem> findAll_idt(String id);
    public List<SelectItem> findAll_idt_recepcion(String descrip);
    public List<AT_datos> findAllIdt(String id);
    public boolean delete(AT_datos t_datos);
    public List<SelectItem> listMunicipìos(Integer dpto);
    public List<SelectItem> listServicios(Integer municipio);
    public List<SelectItem> listSubServicios(Integer servicio);
    
}

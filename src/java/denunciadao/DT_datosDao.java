/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denunciadao;

import java.util.List;
import javax.faces.model.SelectItem;
import denunciamodel.DT_datos;

/**
 *
 * @author KRETCO
 */
public interface DT_datosDao {
    public boolean insert(DT_datos t_datos);
    public List<DT_datos> findAll();
    public List<SelectItem> findAll_idt(String id);
    public List<DT_datos> findAllIdt(String id);
    public boolean update(DT_datos t_datos);
    public boolean delete(DT_datos t_datos);
    public List<DT_datos> findAllIdt_servlet(String id, String gestorBD, String servidor, String baseDato, String usuario, String password);
}

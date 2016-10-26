/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.util.List;
import javax.faces.model.SelectItem;
import accesomodel.AAplicaciones;
import accesomodel.ADescripcionDatos;
import accesomodel.AT_datos;

/**
 *
 * @author KRETCO
 */
public interface AAplicacionesDao {
    
    public List<AAplicaciones> findAll_Aplicaciones();
    public List<SelectItem> itemAplicaciones_Tdatos();
    public AAplicaciones findAplicaciones_Tdatos(Integer id);    
    public List<ADescripcionDatos> listDescripcionDatos(AAplicaciones aplicaciones);
    public List<AT_datos> listT_datos(AAplicaciones aplicaciones, ADescripcionDatos descripcionDatos);
    public boolean guardaTdatos(AAplicaciones aplicaciones, ADescripcionDatos descripcionDatos, AT_datos t_datos);
    public boolean modificarTdatos(AAplicaciones aplicaciones, ADescripcionDatos descripcionDatos, AT_datos t_datos);
//    public List<DescripcionDatos> listDescripcionDatosComplejos(Aplicaciones aplicaciones);
    public List<SelectItem> validaT_datos(AAplicaciones aplicaciones, Integer id);  
    public List<SelectItem> findT_datos(AAplicaciones aplicaciones, Integer id);  

}

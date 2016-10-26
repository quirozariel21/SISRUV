/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.View_violenceReport;

/**
 *
 * @author KRETCO
 */
public interface View_reportDao {

    //public String find_parameters();
    public List<View_violenceReport> reportAge(Date fecha_ini, Date fecha_fin);
    public List<View_violenceReport> reportBeen(Date fecha_ini, Date fecha_fin);
    public List<View_violenceReport> reportDepartment(Date fecha_ini, Date fecha_fin);
    public List<View_violenceReport> reportviolence(Date fecha_ini, Date fecha_fin); 
    
    public List<View_violenceReport> reportViolenceBeen(Date fecha_ini, Date fecha_fin); 
    public List<View_violenceReport> reportViolenceDpto(Date fecha_ini, Date fecha_fin);
    public List<View_violenceReport> reportViolenceDepartmentMunicipalityAge(Date fecha_ini, Date fecha_fin);
    public List<View_violenceReport> reportViolenceMunicipalityService(Date fecha_ini, Date fecha_fin);
    public List<View_violenceReport> reportViolenceServiceSubService(Date fecha_ini, Date fecha_fin);

    public List<View_violenceReport> reporte_edad_victima(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname );
    public List<View_violenceReport> reporte_persona_victima(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname , String descripcion_tdato, String campo_dato);
    public List<View_violenceReport> reporte_tviolencia_victima(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname);
    public List<View_violenceReport> reporte_vicagr_victima(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname , String descripcion_tdato, String campo_dato);

    //metodos para el agresor
    public List<View_violenceReport> reporte_edad_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname );
    public List<View_violenceReport> reporte_persona_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname , String descripcion_tdato, String campo_dato);
    public List<View_violenceReport> reporte_tviolencia_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname);
    public List<View_violenceReport> reporte_vicagr_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname , String descripcion_tdato, String campo_dato);
    
    // Reporte Tenencia Vivienda
    public List<View_violenceReport> reporte_vivienda(Integer codigo_usuario, Date fecha_ini, Date fecha_fin);
    
     // Reporte estado civil
    public List<View_violenceReport> reporte_civil(Integer codigo_usuario, Date fecha_ini, Date fecha_fin);
    
    // Reporte ocupacion principal
    public List<View_violenceReport> reporte_ocupacion(Integer codigo_usuario, Date fecha_ini, Date fecha_fin);
    
    //Report tipo relacion    
    public List<View_violenceReport> reporte_simple(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato); 
    
    //Report tabla victima_agresor   
    public List<View_violenceReport> reporte_simple_victima_agresion(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato); 

    //Report tabla persona_variables BOOLEANOS
    public List<View_violenceReport> reporte_simple_booleanos(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
    
    //Report tabla persona_variables STRINGs
    public List<View_violenceReport> reporte_simple_strings(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
    
    //Report tabla persona_variables INTEGERs
    public List<View_violenceReport> reporte_simple_ints(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
    
    //Report tabla persona_variables INTEGERs
    public List<View_violenceReport> reporte_simple_ints_hijos(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
    
    //Report tabla edades SIMPLE
    public List<View_violenceReport> reporte_simple_edad(Integer codigo_usuario, Date fecha_ini, Date fecha_fin); 
    
     //Report tabla TViolencia
    public List<View_violenceReport> reporte_simple_violencia(Integer codigo_usuario, Date fecha_ini, Date fecha_fin); 
    
    //Report tabla persona_variables INTEGERs
    public List<View_violenceReport> reporte_simple_ints_idioma(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
    
    
    
    /***********************************DECLARACIONES PARA AGRESOR****************************************/
   //Report tabla persona_variables STRINGs
    public List<View_violenceReport> reporte_simple_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
   //Report tabla victima_agresor   
    public List<View_violenceReport> reporte_simple_agresor_nac(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato);  
//Report tabla persona_variables BOOLEANOS
    public List<View_violenceReport> reporte_simple_booleanos_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
    //Report tabla victima_agresor   
    public List<View_violenceReport> reporte_simple_agresor_instrucc(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato);  
    // Reporte ocupacion principal
    public List<View_violenceReport> reporte_agresor_ocupacion(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato);
    //Report tabla persona_variables BOOLEANOS
    public List<View_violenceReport> reporte_simple_booleanos_originario(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
    //Reporte Area donde viven los agresores
    public List<View_violenceReport> reporte_simple_booleanos_area(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
     //Reporte Area donde viven los agresores
    public List<View_violenceReport> reporte_simple_booleanos_agresor_actividad(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
    //Report tabla ingreso economico  
    public List<View_violenceReport> reporte_simple_agresor_ingr_economico(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato);
    //Reporte grupo LGTB
    public List<View_violenceReport> reporte_simple_booleanos_agresor_lgtb(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato); 
    //Report tabla estado civil  
    public List<View_violenceReport> reporte_simple_agresor_est_civil(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato);
    //Report tabla edades SIMPLE
    public List<View_violenceReport> reporte_simple_agresor_edad(Integer codigo_usuario, Date fecha_ini, Date fecha_fin); 

}

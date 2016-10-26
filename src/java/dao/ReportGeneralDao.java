/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;

import model.ReportGeneral;
import model.View_violenceReport;


/**
 *
 * @author KRETCO
 */
public interface ReportGeneralDao {

    public List<ReportGeneral> findDepartamento();
    public List<ReportGeneral> findMunicipio(String cadena_departamentos);
    public List<ReportGeneral> findServicio(String cadena_departamentos);
    public List<ReportGeneral> findSubServicio(String cadena_departamentos);
    public List<View_violenceReport> reporte_edad_general_victima(Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname 
    , String departamento , String municipio , String servicio , String subservicio );
    
    public List<View_violenceReport> reporte_persona_general_victima(Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname 
    ,String descripcion_tdato ,String campo_dato, String departamento , String municipio , String servicio , String subservicio );
    
    public List<View_violenceReport> reporte_vicagr_general_victima(Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname 
    ,String descripcion_tdato ,String campo_dato, String departamento , String municipio , String servicio , String subservicio );
    
    public List<View_violenceReport> reporte_tviolencia_general_victima(Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname 
    , String departamento , String municipio , String servicio , String subservicio );

}

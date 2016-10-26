/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ReportGeneral;
import model.View_violenceReport;
import util.ConnectionDB;
import util.ConnectionUDB;

/**
 *
 * @author KRETCO
 */
public class ReportGeneralDaoImpl implements ReportGeneralDao{

    @Override
    public List<ReportGeneral> findDepartamento() {
        List<ReportGeneral> lista = new ArrayList<>();
        try {
             String query = "select * from t_datos where id_t =( SELECT id_t FROM descripcion_datos where descripcion = 'departamento')";
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                ReportGeneral reg= new ReportGeneral();
                reg.setId(resultSet.getInt("id_tdatos"));
                reg.setDescripcion(resultSet.getString("descripcion"));
                reg.setRelacion(resultSet.getInt("relacion"));
                lista.add(reg);
            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<ReportGeneral> findMunicipio(String cadena_departamentos) {
        List<ReportGeneral> lista = new ArrayList<>();
        try {
            String query = "select * from t_datos where id_t = ( select id_t from descripcion_datos "
                    + "where descripcion = 'Municipio') and relacion in ("+cadena_departamentos+")";
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                ReportGeneral reg= new ReportGeneral();
                reg.setId(resultSet.getInt("id_tdatos"));
                reg.setDescripcion(resultSet.getString("descripcion"));
                reg.setRelacion(resultSet.getInt("relacion"));
                lista.add(reg);

            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<ReportGeneral> findServicio(String cadena_servicio) {
        List<ReportGeneral> lista = new ArrayList<>();
        try {
            String query = "select * from t_datos where id_t = ( select id_t from descripcion_datos "
                    + "where descripcion = 'servicios') and relacion in ("+cadena_servicio+")";
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                ReportGeneral reg= new ReportGeneral();
                reg.setId(resultSet.getInt("id_tdatos"));
                reg.setDescripcion(resultSet.getString("descripcion"));
                reg.setRelacion(resultSet.getInt("relacion"));
                lista.add(reg);

            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<ReportGeneral> findSubServicio(String cadena_subServicio) {
        List<ReportGeneral> lista = new ArrayList<>();
        try {
            String query = "select * from t_datos where id_t = ( select id_t from descripcion_datos "
                    + "where descripcion = 'sub_servicios') and relacion in ("+cadena_subServicio+")";
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                ReportGeneral reg= new ReportGeneral();
                reg.setId(resultSet.getInt("id_tdatos"));
                reg.setDescripcion(resultSet.getString("descripcion"));
                reg.setRelacion(resultSet.getInt("relacion"));
                lista.add(reg);

            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_edad_general_victima(Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname, String departamento, String municipio, String servicio, String subservicio) {
        List<View_violenceReport> lista = new ArrayList<>();
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{ 
        
        CallableStatement cs = connection.prepareCall("SELECT * from  sp_reporte_edad_victima_general(?, ?, ?, ?, ?,?, ?, ?, ?, ?)");
        cs.setDate(1, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(2, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(3, host);
        cs.setString(4, usuario);
        cs.setString(5, pasword);
        cs.setString(6, dbname);
        cs.setString(7, departamento);
        cs.setString(8, municipio);
        cs.setString(9, servicio);
        cs.setString(10, subservicio);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("label"));
            reg.setSexo(result.getString("sexo"));
            reg.setContador(new BigDecimal(result.getInt("count")));
            lista.add(reg);
        }

        } catch (SQLException e) {           
                e.getStackTrace();            
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception ex) {            
                ex.getStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_persona_general_victima(Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname, String descripcion_tdato, String campo_dato, String departamento, String municipio, String servicio, String subservicio) {
       List<View_violenceReport> lista = new ArrayList<>();
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{ 
        
        CallableStatement cs = connection.prepareCall("SELECT * from  sp_reporte_persona_victima_general(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        cs.setDate(1, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(2, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(3, host);
        cs.setString(4, usuario);
        cs.setString(5, pasword);
        cs.setString(6, dbname);
        cs.setString(7, descripcion_tdato);
        cs.setString(8, campo_dato);        
        cs.setString(9, departamento);
        cs.setString(10, municipio);
        cs.setString(11, servicio);
        cs.setString(12, subservicio);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
        }

        } catch (SQLException e) {           
                e.getStackTrace();            
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception ex) {            
                ex.getStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_vicagr_general_victima(Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname, String descripcion_tdato, String campo_dato, String departamento, String municipio, String servicio, String subservicio) {
        List<View_violenceReport> lista = new ArrayList<>();
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{ 
        
        CallableStatement cs = connection.prepareCall("SELECT * from  sp_reporte_vicagr_victima_general(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        cs.setDate(1, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(2, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(3, host);
        cs.setString(4, usuario);
        cs.setString(5, pasword);
        cs.setString(6, dbname);
        cs.setString(7, descripcion_tdato);
        cs.setString(8, campo_dato);        
        cs.setString(9, departamento);
        cs.setString(10, municipio);
        cs.setString(11, servicio);
        cs.setString(12, subservicio);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
        }

        } catch (SQLException e) {           
                e.getStackTrace();            
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception ex) {            
                ex.getStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_tviolencia_general_victima(Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname, String departamento, String municipio, String servicio, String subservicio) {
        List<View_violenceReport> lista = new ArrayList<>();
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{ 
        
        CallableStatement cs = connection.prepareCall("SELECT * from  sp_reporte_tviolencia_victima_general(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        cs.setDate(1, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(2, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(3, host);
        cs.setString(4, usuario);
        cs.setString(5, pasword);
        cs.setString(6, dbname);
        cs.setString(7, departamento);
        cs.setString(8, municipio);
        cs.setString(9, servicio);
        cs.setString(10, subservicio);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
        }

        } catch (SQLException e) {           
                e.getStackTrace();            
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception ex) {            
                ex.getStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return lista;
    }

    
  
    
}

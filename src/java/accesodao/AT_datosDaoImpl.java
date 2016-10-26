/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import accesomodel.AT_datos;
import util.ConnectionUDB;

/**
 *
 * @author KRETCO
 */
public class AT_datosDaoImpl implements AT_datosDao{

    @Override
    public List<AT_datos> findAll() {
        List<AT_datos> listTdatos=new ArrayList<>();
        try {
            String query = "select * from vicAgre_persona";
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               AT_datos t_datos=new AT_datos();
               t_datos.setId_tdatos(resultSet.getInt("id_tdatos"));
               t_datos.setDescripcion(resultSet.getString("descripcion"));
               t_datos.setId_t(resultSet.getInt("id_t"));
               
               listTdatos.add(t_datos);
            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return listTdatos;
    }
    
    @Override
    public List<SelectItem> findAll_idt(String descrip) {
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select * from sp_find_viewdescripciondatostdatos('"+descrip+"')";
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               item.add(new SelectItem(resultSet.getInt("id_tdatos"), resultSet.getString("descripcionb")));
            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return item;
    }

    @Override
    public List<SelectItem> findAll_idt_recepcion(String descrip) {
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select * from sp_find_viewdescripciondatostdatos('"+descrip+"')";
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               item.add(new SelectItem(resultSet.getString("descripcionb"), resultSet.getString("descripcionb")));
            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return item;
    }
    
    @Override
    public boolean delete(AT_datos t_datos ) {
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "DELETE FROM t_datos WHERE id_tdatos =" + t_datos.getId_tdatos();
               
            System.out.println("sql" + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return true;
    }

    @Override
    public List<AT_datos> findAllIdt(String id) {
        List<AT_datos> lista = new ArrayList<>();
        try {
            String query = "select * from sp_find_viewdescripciondatostdatos('"+id+"')";
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                AT_datos reg= new AT_datos();
                reg.setId_tdatos(resultSet.getInt("id_tdatos"));
                reg.setDescripcion(resultSet.getString("descripcionb"));
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
    public List<SelectItem> listMunicipìos(Integer dpto) {
        
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select * from sp_find_viewdescripciondatostdatos('Municipio') where relacion =" + dpto;
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               item.add(new SelectItem(resultSet.getInt("id_tdatos"), resultSet.getString("descripcionb")));
            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return item;
    }
    
    @Override
    public List<SelectItem> listServicios(Integer municipio) {
        
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select * from sp_find_viewdescripciondatostdatos('servicios') where relacion =" + municipio;
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               item.add(new SelectItem(resultSet.getInt("id_tdatos"), resultSet.getString("descripcionb")));
            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return item;
    }
    
    @Override
    public List<SelectItem> listSubServicios(Integer servicio) {
         List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select * from sp_find_viewdescripciondatostdatos('sub_servicios') where relacion =" + servicio;
            ConnectionUDB conn=new ConnectionUDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               item.add(new SelectItem(resultSet.getInt("id_tdatos"), resultSet.getString("descripcionb")));
            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return item;
    }
    
}

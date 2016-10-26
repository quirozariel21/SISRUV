/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import model.T_datos;
import util.ConnectionDB;
import util.ConnectionServlet;

/**
 *
 * @author KRETCO
 */
public class T_datosDaoImpl implements T_datosDao{

    @Override
    public List<T_datos> findAll() {
        List<T_datos> listTdatos=new ArrayList<>();
        try {
            System.out.println("findAll");
            String query = "select * from vicAgre_persona";
            ConnectionDB conn=new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               T_datos t_datos=new T_datos();
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
            System.out.println("findAllidt String");
            String query = "select * from sp_find_viewdescripciondatostdatos('"+descrip+"')";
            ConnectionDB conn=new ConnectionDB();
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
            System.out.println("findAllidt_recepcion");
            String query = "select * from sp_find_viewdescripciondatostdatos('"+descrip+"')";
            ConnectionDB conn=new ConnectionDB();
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
    public boolean delete(T_datos t_datos ) {
        try {
            ConnectionDB conn = new ConnectionDB();
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
    public List<T_datos> findAllIdt(String id) {
        List<T_datos> lista = new ArrayList<>();
        try {
            System.out.println("findAllidt: "+ id);
            String query = "select * from sp_find_viewdescripciondatostdatos('"+id+"')";
            ConnectionDB conn=new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                T_datos reg= new T_datos();
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
    public List<T_datos> findAllIdt_servlet(String id, String gestorBD, String servidor, String baseDato, String usuario, String password) {
        List<T_datos> lista = new ArrayList<>();
        try {
            System.out.println("findAllidt_Servlet");
            String query = "select * from sp_find_viewdescripciondatostdatos('"+id+"')";
            ConnectionServlet conn=new ConnectionServlet();
            Connection connection=conn.getConnectionDB(gestorBD, servidor, baseDato, usuario, password);
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                T_datos reg= new T_datos();
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
            System.out.println("listMunicipios");
            String query = "select * from sp_find_viewdescripciondatostdatos('Municipio') where relacion =" + dpto;
            ConnectionDB conn=new ConnectionDB();
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
            System.out.println("listSErvicios");
            String query = "select * from sp_find_viewdescripciondatostdatos('servicios') where relacion =" + municipio;
            ConnectionDB conn=new ConnectionDB();
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
            System.out.println("listSubServicios");
            String query = "select * from sp_find_viewdescripciondatostdatos('sub_servicios') where relacion =" + servicio;
            ConnectionDB conn=new ConnectionDB();
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

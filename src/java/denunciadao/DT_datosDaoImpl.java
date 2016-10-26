/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denunciadao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import denunciamodel.DT_datos;
import util.ConnectionDB;
import util.ConnectionServlet;


/**
 *
 * @author KRETCO
 */
public class DT_datosDaoImpl implements DT_datosDao{

    @Override
    public boolean insert(DT_datos t_datos) {
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "INSERT INTO dt_datos(id_tdatos, identificador, descripcion, id_t)"
                    + "VALUES(" + t_datos.getId_tdatos()
                    + "," + t_datos.getIdentificador()
                    + "," + t_datos.getDescripcion()
                    + "," + t_datos.getId_t() + ")";
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
    public List<DT_datos> findAll() {
        List<DT_datos> listTdatos=new ArrayList<>();
        try {
            String query = "select * from vicAgre_persona";
            ConnectionDB conn=new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               DT_datos t_datos=new DT_datos();
               t_datos.setId_tdatos(resultSet.getInt("id_tdatos"));
               t_datos.setIdentificador(resultSet.getInt("identificador"));
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
            String query = "select * from cocoden_find_viewdescripciondatostdatos('"+descrip+"')"; // del original sp_find_viewdescripciondatostdatos
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
    public boolean update(DT_datos t_datos) {
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "UPDATE dt_datos SET id_tdatos =" + t_datos.getId_tdatos()
                    + ", identificador =" + t_datos.getIdentificador()
                    + ", descripcion =" + t_datos.getDescripcion()
                    + ", id_t =" + t_datos.getId_t();
               
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
    public boolean delete(DT_datos t_datos ) {
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "DELETE FROM dt_datos WHERE id_tdatos =" + t_datos.getId_tdatos();
               
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
    public List<DT_datos> findAllIdt(String id) {
        List<DT_datos> lista = new ArrayList<>();
        try {
            String query = "select * from cocoden_find_viewdescripciondatostdatos('"+id+"')"; // sp_find_viewdescripciondatostdatos
            ConnectionDB conn=new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                DT_datos reg= new DT_datos();
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
    public List<DT_datos> findAllIdt_servlet(String id, String gestorBD, String servidor, String baseDato, String usuario, String password) {
        List<DT_datos> lista = new ArrayList<>();
        try {
            String query = "select * from cocoden_find_viewdescripciondatostdatos('"+id+"')"; //sp_find_viewdescripciondatostdatos
            ConnectionServlet conn=new ConnectionServlet();
            Connection connection=conn.getConnectionDB(gestorBD, servidor, baseDato, usuario, password);
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                DT_datos reg= new DT_datos();
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
    
}

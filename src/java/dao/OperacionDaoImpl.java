/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.*;

import model.Operacion;
import util.ConnectionUDB;

/**
 *
 * @author e_mv
 */

public class OperacionDaoImpl implements OperacionDao {
    //int cod_app = 1;
    @Override
    public List<Operacion> findAll(Integer codigo_usuario, Integer cod_app) {
        List<Operacion> listOperacion = new ArrayList<>();
        ConnectionUDB conn = new ConnectionUDB();
        Connection connection = conn.getConnectionDB();
       
        System.out.println("USUARIO ID "+codigo_usuario); /**VERIFICAR QUE id_USUARIO SE PASA*/
        try {
            String query = "SELECT * FROM  cocomenu("+codigo_usuario+","+cod_app+");";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Operacion item = new Operacion();
                item.setCod_opera(resultSet.getInt("cod_opera"));
                item.setCod_opera_padre(resultSet.getInt("cod_opera_padre"));
                item.setDescripcion(resultSet.getString("des"));
                item.setHref(resultSet.getString("href"));
                item.setEstado(resultSet.getBoolean("estado"));
                item.setIcon(resultSet.getString("icon"));
                item.setAplicacion_id_app(resultSet.getInt("app_menu"));
                listOperacion.add(item);
                System.out.println("ID APLICACION : " + item.getAplicacion_id_app());
                System.out.println("GENERA  MENU ruv : " + item.getCod_opera());
                System.out.println("GENERA  MENU PADRE ruv : " + item.getCod_opera_padre());
            }
            /* Editado por Boris Generacion Menu Denuncias*/
            /*String query2 = "SELECT * FROM  sp_generamenu_denuncias("+codigo_usuario+");";

            Statement statement2 = connection.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(query2);
            while (resultSet2.next()) {
                Operacion item = new Operacion();
                item.setCod_opera(resultSet2.getInt("cod_opera"));
                item.setCod_opera_padre(resultSet2.getInt("cod_opera_padre"));
                item.setDescripcion(resultSet2.getString("des"));
                item.setHref(resultSet2.getString("href"));
                item.setEstado(resultSet2.getBoolean("estado"));
                item.setIcon(resultSet2.getString("icon"));
                listOperacion.add(item);
                System.out.println("ID APLICACION : " + item.getAplicacion_id_app());
                System.out.println("GENERA  MENU denuncias: " + item.getCod_opera());
                System.out.println("GENERA  MENU PADRE denuncias: " + item.getCod_opera_padre());
            }*//*Fin Editado por Boris Generacion Menu Denuncias*/
            
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return listOperacion;
    }

}

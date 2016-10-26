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
import util.ConnectionDB;
/**
 *
 * @author e_mv
 */
public class Persona_VariablesDaoImpl implements Persona_VariablesDao{

    @Override
    public int max() {
        int max=0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "select max(id_pervar) maxp from persona_variables";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                max = resultSet.getInt("maxp");
            }
             connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return max;
    }
// LOG, VER SI SE NECESITA
    /*
    @Override
    public int max_log() {
        int max=0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "select max(id_pervar) maxp from persona_variables_log";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                max = resultSet.getInt("maxp");
            }
             connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return max;
    }
    */
}
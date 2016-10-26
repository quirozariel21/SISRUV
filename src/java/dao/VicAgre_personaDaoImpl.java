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
import model.VicAgre_persona;
import util.ConnectionDB;

/**
 *
 * @author KRETCO
 */
public class VicAgre_personaDaoImpl implements VicAgre_personaDao{

    @Override
    public List<VicAgre_persona> findAll() {
        List<VicAgre_persona> listVicAgr_per=new ArrayList<>();
        try {
            String query = "select * from vicAgre_persona";
            ConnectionDB conn=new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               VicAgre_persona vicAgre_persona = new VicAgre_persona();
               
               vicAgre_persona.setId_vicAgre_persona(resultSet.getInt("id_vicAgre_persona"));
               vicAgre_persona.setVictima_agresor_id_vicagre(resultSet.getInt("victima_agresor_id_vicagre"));
               vicAgre_persona.setPersona_id_persona_agresor(resultSet.getInt("persona_id_persona_agresor"));
               vicAgre_persona.setPersona_id_persona_victima(resultSet.getInt("persona_id_persona_victima"));
               listVicAgr_per.add(vicAgre_persona);
            }
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return listVicAgr_per;
    }

    @Override
    public int max() {
        int max=0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "select max(id_vicagre_persona) maxp from vicagre_persona";
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
            String sql = "select max(id_vicagre_persona) maxp from vicagre_persona_log";
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

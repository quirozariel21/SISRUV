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
import model.Alerta;
import util.ConnectionDB;

/**
 *
 * @author bsoto
 */
public class AlertaDaoImpl implements AlertaDao {

    @Override
    public List<Alerta> findAll() {
        List<Alerta> listaAlerta = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM Alerta";
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            
            while(resultset.next()){
                Alerta pregunta = new Alerta();
                pregunta.setPreg1(resultset.getInt(""));
                pregunta.setPreg2(resultset.getInt(""));
                pregunta.setPreg3(resultset.getInt(""));
                pregunta.setPreg4(resultset.getInt(""));
                pregunta.setPreg5(resultset.getInt(""));
                pregunta.setPreg6(resultset.getInt(""));
                pregunta.setPreg7(resultset.getInt(""));
                pregunta.setPreg8(resultset.getInt(""));
                pregunta.setPreg9(resultset.getInt(""));
                pregunta.setPreg10(resultset.getInt(""));
                pregunta.setPreg11(resultset.getInt(""));
                pregunta.setPreg12(resultset.getInt(""));
                pregunta.setPreg13(resultset.getInt(""));
                pregunta.setPreg14(resultset.getInt(""));
                pregunta.setPreg15(resultset.getInt(""));
                pregunta.setPreg16(resultset.getInt(""));
                pregunta.setPreg17(resultset.getInt(""));
                pregunta.setPreg18(resultset.getInt(""));
                pregunta.setPreg19(resultset.getInt(""));     
                
                
                listaAlerta.add(pregunta);
                
            }   
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
        
        return listaAlerta;
    }
    
}

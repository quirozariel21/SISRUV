/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Persona;
import java.sql.SQLException;
import util.ConnectionDB;

/**
 *
 * @author KRETCO
 */
public class PersonaDaoImpl implements PersonaDao {

    public int max() {
        int max = 0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(id_persona) maxp from persona";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                max = resultSet.getInt("maxp");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return max;
    }

    public boolean create(Persona persona) {
        boolean res = false;

        return res;
    }

    @Override
    public List<Persona> listPersona() {
        List<Persona> listpersonas = new ArrayList<>();
        List<Persona> data = new ArrayList<>();
        try {
            String query = "select * from persona order by id_persona";
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Persona reg = new Persona();
                reg.setId_persona(resultSet.getInt("id_persona"));
                reg.setNombre(resultSet.getString("nombre"));
                reg.setPaterno(resultSet.getString("paterno"));
                reg.setMaterno(resultSet.getString("materno"));
                reg.setAp_casada(resultSet.getString("ap_casada"));
                reg.setNumero(resultSet.getString("numero"));
                reg.setCodigo_documento(resultSet.getInt("codigo_documento"));
                reg.setSexo(resultSet.getString("sexo"));
                reg.setLugar_nac(resultSet.getInt("lugar_nac"));
                reg.setEsp_lugar_nac(resultSet.getString("esp_lugar_nac"));
                reg.setFecha_nac(resultSet.getDate("fecha_nac"));
                data.add(reg);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
// LOG, VER SI SE NECESITA
    /*
    @Override
    public int max_log() {
        int max = 0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(id_persona) maxp from persona_log";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                max = resultSet.getInt("maxp");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return max;
    }*/
}

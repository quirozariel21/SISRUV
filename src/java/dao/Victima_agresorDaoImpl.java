/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Victima_agresor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionDB;

/**
 *
 * @author KRETCO
 */
public class Victima_agresorDaoImpl implements Victima_agresorDao{

    @Override
    public List<Victima_agresor> findAll() {
        List<Victima_agresor> listVicAgre1=new ArrayList<>();
        try {
            String query = "select * from victima_agresor";
            ConnectionDB conn=new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               Victima_agresor victima_agresor=new Victima_agresor();
               
               victima_agresor.setId_vicagre(resultSet.getInt("id_vicagre"));
//               victima_agresor.setFecha_reg(resultSet.getDate("fecha_reg"));
//               victima_agresor.setNroCaso_inst(resultSet.getInt("nroCaso_inst"));
//               victima_agresor.setTestimonio(resultSet.getString("testimonio"));
//               victima_agresor.setLugar_agresion(resultSet.getInt("lugar_agresion"));
//               victima_agresor.setDireccion(resultSet.getString("direccion"));
//               victima_agresor.setNro_domicilio(resultSet.getString("nro_domicilio"));
//               victima_agresor.setNom_edificio(resultSet.getString("nom_edificio"));
//               victima_agresor.setNro_piso_dpto(resultSet.getString("nro_piso_dpto"));
//               victima_agresor.setZona(resultSet.getString("zona"));
//               victima_agresor.setMunicipio(resultSet.getString("municipio"));
//               victima_agresor.setEsp_municipio(resultSet.getString("esp_municipio"));
//               victima_agresor.setParentesco(resultSet.getString("parentesco"));
//               victima_agresor.setOtros_fam(resultSet.getString("otros_fam"));
//               victima_agresor.setOtros_no_fam(resultSet.getString("otros_no_fam"));
//               victima_agresor.setAmbito_violencia(resultSet.getInt("ambito_violencia"));
               victima_agresor.setTviolencia(resultSet.getString("tviolencia"));
//               victima_agresor.setDenuncio(resultSet.getInt("denuncio"));
//               victima_agresor.setDonde(resultSet.getString("donde"));
//               victima_agresor.setFrecuencia(resultSet.getInt("frecuencia"));
//               victima_agresor.setTiempo_agresion(resultSet.getInt("tiempo_agresion"));
//               victima_agresor.setDeriv_interna(resultSet.getInt("deriv_interna"));
//               victima_agresor.setDeriv_externa(resultSet.getInt("deriv_externa"));
//               victima_agresor.setRecepcion(resultSet.getInt("recepcion"));
               listVicAgre1.add(victima_agresor);
            }
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return listVicAgre1;
    }

    @Override
    public int max() {
        int max=0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "select max(id_vicagre) maxp from victima_agresor";
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
    /*****************VICTIMA AGRESOR LOG, VER SI SE NECESITA**************/
    /*
    @Override
    public int max_log() {
        int max=0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "select max(id_vicagre) maxp from victima_agresor_log";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import accesomodel.ALog;
import util.ConnectionLDB;

/**
 *
 * @author KRETCO
 */
public class ALogDaoImpl implements ALogDao{

   
    @Override
    public int insert(int num, ALog log) {
        ConnectionLDB conn = new ConnectionLDB();
        Connection connection = conn.getConnectionDB();
        int resp = 0;
        try {
            if(num == 1){
                CallableStatement l = connection.prepareCall("select sp_operaciones_acceso_log(?,?,?,?,?,?,?)");
                System.out.println("fffffffffffff : " + max_event());

                resp = max_event() + 1;
                System.out.println("jnojojhopjpoijpoijiopjiopjjio : " + resp);
                l.setInt(1, num);
                l.setInt(2, resp);
                l.setInt(3, log.getId_user());
                l.setString(4, log.getIp());
                l.setString(5, log.getActividad());
                l.setInt(6, log.getId_registro());
                l.setString(7, log.getNombre_tabla());
                
                ResultSet resultSet = l.executeQuery();
                connection.close();
            }else{
                CallableStatement l = connection.prepareCall("select sp_operaciones_acceso_log(?,?,?,?,?,?,?)");
                resp = max_error() + 1;
                l.setInt(1, num);
                l.setInt(2, resp);
                l.setInt(3, log.getId_user());
                l.setString(4, log.getIp());
                l.setString(5, log.getActividad());
                l.setInt(6, log.getId_registro());
                l.setString(7, log.getNombre_tabla());
                
                ResultSet resultSet = l.executeQuery();
                connection.close();
            }
          
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return resp;
    }

    @Override
    public int max_event() {
        int max=0;
        try {
            ConnectionLDB conn = new ConnectionLDB();
        Connection connection = conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "select max(id) maxp from log_event_acceso";
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

    @Override
    public int max_error() {
        int max=0;
        try {
            ConnectionLDB conn = new ConnectionLDB();
        Connection connection = conn.getConnectionDB();
            Statement statement=connection.createStatement();
            String sql = "select max(id) maxp from log_error_acceso";
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
}

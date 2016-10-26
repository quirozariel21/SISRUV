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
import accesomodel.APerfil_opera;
import util.ConnectionUDB;

/**
 *
 * @author e_mv
 */
public class APerfil_operaDaoImpl implements APerfil_operaDao {

    @Override
    public List<APerfil_opera> findAll_Perfil_opera(Integer cod_perfil) {
        List<APerfil_opera> lista = new ArrayList<>();
        try {
            String query = "select * from perfil_opera where perfil_cod_perfil = " + cod_perfil;
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                APerfil_opera reg = new APerfil_opera();
                reg.setId_perfil_opera(resultSet.getInt("id_perfil_opera"));
                reg.setPerfil_cod_perfil(resultSet.getInt("perfil_cod_perfil"));
                reg.setOperacion_cod_opera(resultSet.getInt("operacion_cod_opera"));
                lista.add(reg);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean deleteOpe(int codPerfil) {
        boolean res = false;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("delete from perfil_opera where perfil_cod_perfil = " + codPerfil);
            res = true;
        } catch (Exception e) {
        }
        return true;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.sql.*;
import java.util.*;
import accesomodel.AOperacion;
import util.ConnectionUDB;

/**
 *
 * @author e_mv
 */
public class AOperacionDaoImpl implements AOperacionDao {

    @Override
    public List<AOperacion> findAll(Integer aplicacion_id_app) {
        List<AOperacion> listOperacion = new ArrayList<>();
        try {
            System.out.println("SE LLAMA A LA APP = "+aplicacion_id_app);
            String query = "select * from operacion where estado= true and aplicacion_id_app = "+aplicacion_id_app+" order by aplicacion_id_app, cod_opera";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AOperacion item = new AOperacion(
                        resultSet.getInt("cod_opera"),
                        resultSet.getInt("cod_opera_padre"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("href"),
                        resultSet.getBoolean("estado"),
                        resultSet.getString("icon"),
                        resultSet.getInt("aplicacion_id_app"));
                listOperacion.add(item);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOperacion;
    }

}

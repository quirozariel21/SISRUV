/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import indicadoresmodel.IInstitucion;
import util.ConnectionDB;

/**
 *
 * @author KRETCO
 */
public class IInstitucionDaoImpl implements IInstitucionDao {

    @Override
    public List<IInstitucion> findAll() {
        List<IInstitucion> listaInstituciones = new ArrayList<>();
        try {
            String query = "select * from institucion";
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                IInstitucion institucion = new IInstitucion();
                institucion.setId_institucion(resultSet.getInt("id_institucion"));
                institucion.setNombre(resultSet.getString("nombre"));
                institucion.setDependiente(resultSet.getString("dependiente"));
                
                listaInstituciones.add(institucion);
            }
        } catch (Exception e) {
        }
        return listaInstituciones;
    }

    @Override
    public int insert(int num, IInstitucion institucion) {
        int aux = 0;
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try {
            if (num == 1) {
                connection.setAutoCommit(false);
                CallableStatement i = connection.prepareCall("select sp_operaciones_institucion(?,?,?,?)");
                i.setInt(1, num);
                i.setInt(2, max() + 1);
                i.setString(3, institucion.getNombre());
                i.setString(4, institucion.getDependiente());

                ResultSet resultSet = i.executeQuery();
                aux=1;
            }else{
                connection.setAutoCommit(false);
                CallableStatement i = connection.prepareCall("select sp_operaciones_institucion(?,?,?,?)");
                i.setInt(1, num);
                i.setInt(2, institucion.getId_institucion());
                i.setString(3, institucion.getNombre());
                i.setString(4, institucion.getDependiente());
            }
        } catch (Exception e) {
        }
        return aux;
    }

    @Override
    public int max() {
        int maxp = 0;
        int max = 0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(id_institucion) maxp from institucion";
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

}

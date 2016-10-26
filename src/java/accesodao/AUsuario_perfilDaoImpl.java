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
import java.util.ArrayList;
import java.util.List;
import accesomodel.APerfil;
import accesomodel.AUsuario;
import accesomodel.AUsuario_perfil;
import util.ConnectionUDB;

/**
 *
 * @author e_mv
 */
public class AUsuario_perfilDaoImpl implements AUsuario_perfilDao {

    @Override
    public boolean addPerfil(APerfil perfil, AUsuario usuario, int idAdmin) {
        boolean res = false;
        String sql = "select coco_operaciones_usuario_perfil(?,?,?,?,?)";  // antes era sp_operaciones_usuario_perfil(?,?,?,?);      
        System.out.println("accesodao.AUsuario_perfilDaoImpl.addPerfil(): addPERIFL - " + usuario.getCod_usuario() + "-" + perfil.getCod_perfil()+" id Admin "+idAdmin);
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            CallableStatement callstate = connection.prepareCall(sql);
            callstate.setInt(1, 1);
            callstate.setInt(2, 0);
            callstate.setInt(3, usuario.getCod_usuario());
            callstate.setInt(4, perfil.getCod_perfil());
            callstate.setInt(5, idAdmin);
            callstate.executeQuery();
            res = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean deletePerfiles(int codUsuario) {
        boolean res = false;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("delete from usuario_perfil where usuario_cod_usuario = " + codUsuario);
            res = true;
        } catch (Exception e) {
        }
        return true;
    }

    @Override
    public List<APerfil> listPerfil(Integer codigo_usuario) {
        List<APerfil> lista = new ArrayList<>();
        try {
            String query = "select * from usuario_perfil where usuario_cod_usuario = " + codigo_usuario;
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                APerfil reg = new APerfil();
                reg.setCod_perfil(resultSet.getInt("perfil_cod_perfil"));
                lista.add(reg);
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return lista;
    }
    
    @Override
    public List<AUsuario_perfil> listUsuarioPerfil() {
        List<AUsuario_perfil> lista = new ArrayList<>();
        try {
            String query = "select * from usuario_perfil";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AUsuario_perfil reg = new AUsuario_perfil();
                reg.setPerfil_cod_perfil(resultSet.getInt("perfil_cod_perfil"));
                reg.setUsuario_cod_usuario(resultSet.getInt("usuario_cod_usuario"));
                lista.add(reg);
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return lista;
    }

}

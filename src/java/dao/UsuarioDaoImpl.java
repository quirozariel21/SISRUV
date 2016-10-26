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
import model.Operacion;
import model.Usuario;
import model.Usuario_Tdatos;
import util.ConnectionServlet;

import util.ConnectionUDB;

/**
 *
 * @author KRETCO A
 */
public class UsuarioDaoImpl implements UsuarioDao {

//    //---------------------- Con la base de datos usuarios---------------------- //
    @Override
    public Usuario findUsuario(Usuario usuario) {
        Usuario registro = new Usuario();
        ConnectionUDB conn = new ConnectionUDB();
        Connection connection = conn.getConnectionDB();
        try {
            System.out.println(" Usuario "+usuario.getUsername()+" password "+usuario.getPassword());
            String query = "select * from usuario where username = '" + usuario.getUsername() + "' and password = '" + usuario.getPassword() + "' and estado = true";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                registro.setCod_usuario(resultSet.getInt("cod_usuario"));
                registro.setUsername(resultSet.getString("username"));
                registro.setPassword(resultSet.getString("password"));
                registro.setEstado(resultSet.getBoolean("estado"));
                registro.setNombre(resultSet.getString("nombre"));
                registro.setPaterno(resultSet.getString("paterno"));
                registro.setMaterno(resultSet.getString("materno"));
            }
  

        } catch (SQLException e) {
           
                e.getStackTrace();
            
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception ex) {
            
                ex.getStackTrace();
            

        } finally {
            try {
                if (connection != null) {
                    connection.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return registro;
    }
    
    @Override
    public int datoSubServicio(Integer cod) {
        int codsub = 0;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select cod_sub_servicio as cod from usuario where cod_usuario = " + cod;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                codsub = resultSet.getInt("cod");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return codsub;
    }
    
    @Override
    public List<Operacion> listMenu() {
        List<Operacion> lista = new ArrayList<>();
        try {
            String query = "select p.* from operacion p order by p asc";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Operacion reg = new Operacion();
                reg.setCod_opera(resultSet.getInt("cod_opera"));
                reg.setDescripcion(resultSet.getString("descripcion"));
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
    public Usuario_Tdatos datoUsuario(Integer cod_usuario, String gestorBD, String servidor, String baseDato, String usuario, String password) {        
        Usuario_Tdatos registro = new Usuario_Tdatos();
        try {
            String query = "select *  from coco_view_usuario_detalle_pdf where cod_usuario = "+cod_usuario; // cambiado de view_usuario_detalle_pdf porque EN LA BD si no hay SUB SERVICIO SALE null
            ConnectionServlet conn=new ConnectionServlet();
            Connection connection=conn.getConnectionDB(gestorBD, servidor, baseDato, usuario, password);
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            if (resultSet.next()) {
                registro.getUsuario().setCod_usuario(resultSet.getInt("cod_usuario"));
                registro.getUsuario().setUsername(resultSet.getString("username"));
                registro.getUsuario().setPassword(resultSet.getString("password"));
                registro.getUsuario().setEstado(resultSet.getBoolean("estado"));
                registro.getUsuario().setNombre(resultSet.getString("nombre"));
                registro.getUsuario().setPaterno(resultSet.getString("paterno"));
                registro.getUsuario().setMaterno(resultSet.getString("materno"));
                registro.getUsuario().setCi(resultSet.getInt("ci"));
                registro.getUsuario().setFecha_reg(resultSet.getDate("fecha_reg"));
                registro.getUsuario().setDepartamento(resultSet.getInt("departamento"));
                registro.getUsuario().setMunicipio(resultSet.getInt("municipio"));
                registro.getUsuario().setCod_servicio(resultSet.getInt("cod_servicio"));
                registro.getUsuario().setCod_sub_servicio(resultSet.getInt("cod_sub_servicio"));
                registro.getUsuario().setFecha_estado(resultSet.getDate("fecha_reg"));
                registro.setDetalle_depto(resultSet.getString("detail_dep"));
                registro.setDetalle_muni(resultSet.getString("detail_mun"));
                registro.setDetalle_serv(resultSet.getString("detail_ser"));
                registro.setDetalle_subs(resultSet.getString("detail_sub"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return registro;

    }

}

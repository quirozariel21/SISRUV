

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import accesomodel.AAplicaciones;
import accesomodel.ADescripcionDatos;
import accesomodel.AT_datos;
import accesoutil.AConnectionDinamic;
import util.ConnectionUDB;

/**
 *
 * @author KRETCO
 */

public class AAplicacionesDaoImpl implements AAplicacionesDao {

    @Override
    public List<AAplicaciones> findAll_Aplicaciones() {
        List<AAplicaciones> lista = new ArrayList<>();
        try {
            String query = "select * from aplicaciones order by id_app asc ";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("QUERY DE APLICACIONES DAO IMPL" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AAplicaciones reg = new AAplicaciones();
                reg.setId_app(resultSet.getInt("id_app"));
                reg.setDescripcion(resultSet.getString("descripcion"));
                reg.setIp_db(resultSet.getString("ip_db"));
                reg.setNombre_db(resultSet.getString("nombre_db"));
                reg.setUsuario_db(resultSet.getString("usuario_db"));
                reg.setPassword_db(resultSet.getString("password_db"));
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
    public List<SelectItem> itemAplicaciones_Tdatos() {
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select * from aplicaciones where t_datos = true order by id_app asc ";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                item.add(new SelectItem(resultSet.getInt("id_app"), resultSet.getString("descripcion")));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return item;
    }

    @Override
    public AAplicaciones findAplicaciones_Tdatos(Integer id) {
        AAplicaciones res = new AAplicaciones();
        try {
            String query = "select * from aplicaciones where id_app = "+ id;
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                res.setId_app(resultSet.getInt("id_app"));
                res.setDescripcion(resultSet.getString("descripcion"));
                res.setIp_db(resultSet.getString("ip_db"));
                res.setNombre_db(resultSet.getString("nombre_db"));
                res.setUsuario_db(resultSet.getString("usuario_db"));
                res.setPassword_db(resultSet.getString("password_db"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return res;
    }
    
    @Override
    public List<ADescripcionDatos> listDescripcionDatos(AAplicaciones aplicaciones) {
        List<ADescripcionDatos> listDesc = new ArrayList<>();
        try {
            String query = "select * from descripcion_datos";
            AConnectionDinamic conn = new AConnectionDinamic();
            System.out.println("datos:::"+"postgresql"+ aplicaciones.getIp_db()+"-"+ aplicaciones.getNombre_db()+"-"+ aplicaciones.getUsuario_db()+"-"+ aplicaciones.getPassword_db());
            Connection connection = conn.getConnectionDinamic("postgresql", aplicaciones.getIp_db(), aplicaciones.getNombre_db(), aplicaciones.getUsuario_db(), aplicaciones.getPassword_db());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ADescripcionDatos reg =  new ADescripcionDatos();
                reg.setId_t(resultSet.getInt("id_t"));
                reg.setDescripcion(resultSet.getString("descripcion"));
                listDesc.add(reg);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return listDesc;
    }

    @Override
    public List<AT_datos> listT_datos(AAplicaciones aplicaciones, ADescripcionDatos descripcionDatos) {
        List<AT_datos> listT_datos = new ArrayList<>();
        try {
            String query = "select * from t_datos where id_t = "+descripcionDatos.getId_t()+" order by id_tdatos ";
            AConnectionDinamic conn = new AConnectionDinamic();
            Connection connection = conn.getConnectionDinamic("postgresql", aplicaciones.getIp_db(), aplicaciones.getNombre_db(), aplicaciones.getUsuario_db(), aplicaciones.getPassword_db());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AT_datos reg =  new AT_datos();
                reg.setId_tdatos(resultSet.getInt("id_tdatos"));
                reg.setDescripcion(resultSet.getString("descripcion"));
                reg.setId_t(resultSet.getInt("id_t"));
                reg.setRelacion(resultSet.getInt("relacion"));
                reg.setEstado(resultSet.getInt("estado"));
                listT_datos.add(reg);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return listT_datos;
    }

    @Override
    public boolean guardaTdatos(AAplicaciones aplicaciones, ADescripcionDatos descripcionDatos, AT_datos t_datos) {
        boolean res = false;
        AConnectionDinamic conn = new AConnectionDinamic();
        Connection connection = conn.getConnectionDinamic("postgresql", aplicaciones.getIp_db(), aplicaciones.getNombre_db(), aplicaciones.getUsuario_db(), aplicaciones.getPassword_db());
        try {
                CallableStatement callstate = connection.prepareCall("select sp_operaciones_tdatos(?, ?, ?, ?, ?, ?)");
                callstate.setInt(1, 1);
                callstate.setInt(2, 0);
                callstate.setString(3, t_datos.getDescripcion());
                callstate.setInt(4, descripcionDatos.getId_t());
                callstate.setInt(5, t_datos.getRelacion());
                callstate.setInt(6, 1);
                callstate.executeQuery();

                res = true;
            
            connection.close();
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean modificarTdatos(AAplicaciones aplicaciones, ADescripcionDatos descripcionDatos, AT_datos t_datos) {
        boolean res = false;
        AConnectionDinamic conn = new AConnectionDinamic();
        Connection connection = conn.getConnectionDinamic("postgresql", aplicaciones.getIp_db(), aplicaciones.getNombre_db(), aplicaciones.getUsuario_db(), aplicaciones.getPassword_db());
        try {
                CallableStatement callstate = connection.prepareCall("select sp_operaciones_tdatos(?, ?, ?, ?, ?, ?)");
                callstate.setInt(1, 2);
                callstate.setInt(2, t_datos.getId_tdatos());
                callstate.setString(3, t_datos.getDescripcion());
                callstate.setInt(4, descripcionDatos.getId_t());
                callstate.setInt(5, t_datos.getRelacion());
                callstate.setInt(6, 1);
                callstate.executeQuery();

                res = true;
            
            connection.close();
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return res;
    }

//    @Override
//    public List<DescripcionDatos> listDescripcionDatosComplejos(Aplicaciones aplicaciones) {
//        List<DescripcionDatos> listDesc = new ArrayList<>();
//        try {
//            String query = "select * from descripcion_datos where por_niveles = true";
//            ConnectionDinamic conn = new ConnectionDinamic();
//            Connection connection = conn.getConnectionDinamic("postgresql", aplicaciones.getIp_db(), aplicaciones.getNombre_db(), aplicaciones.getUsuario_db(), aplicaciones.getPassword_db());
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                DescripcionDatos reg =  new DescripcionDatos();
//                reg.setId_t(resultSet.getInt("id_t"));
//                reg.setDescripcion(resultSet.getString("descripcion"));
//                listDesc.add(reg);
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error SqlExeption" + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error :" + e.getMessage());
//        }
//        return listDesc;
//    }

    @Override
    public List<SelectItem> validaT_datos(AAplicaciones aplicaciones, Integer id) {
        
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = " select * from t_datos where id_t = "+id+" and relacion != 0 order by id_tdatos";
            AConnectionDinamic conn = new AConnectionDinamic();
            Connection connection = conn.getConnectionDinamic("postgresql", aplicaciones.getIp_db(), aplicaciones.getNombre_db(), aplicaciones.getUsuario_db(), aplicaciones.getPassword_db());
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                item.add(new SelectItem(resultSet.getInt("id_tdatos"), resultSet.getString("descripcion")));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return item;
        
    }
    
    @Override
    public List<SelectItem> findT_datos(AAplicaciones aplicaciones, Integer id) {
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = " select * from t_datos where id_t in (select id_padre from descripcion_datos where id_t = "+id+") order by id_tdatos";
            AConnectionDinamic conn = new AConnectionDinamic();
            Connection connection = conn.getConnectionDinamic("postgresql", aplicaciones.getIp_db(), aplicaciones.getNombre_db(), aplicaciones.getUsuario_db(), aplicaciones.getPassword_db());
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                item.add(new SelectItem(resultSet.getInt("id_tdatos"), resultSet.getString("descripcion")));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return item;
    }
}

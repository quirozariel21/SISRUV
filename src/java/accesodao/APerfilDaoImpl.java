/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import controller.UsuarioController;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import accesomodel.ALog;
import accesomodel.APerfil;
import util.ConnectionUDB;
import accesomodel.AOperacion;

/**
 *
 * @author e_mv
 */

public class APerfilDaoImpl implements APerfilDao{

    @Override

    public List<APerfil> findAll_Perfil() {
         List<APerfil> lista = new ArrayList<>();
        try {
            String query = "select * from perfil order by cod_perfil asc ";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("APerfilDaoImpl - findAll_Perfil" + query);
            ResultSet resultSet = statement.executeQuery(query);
            int i = 1;
            while (resultSet.next()) {
                APerfil reg = new APerfil();                
                reg.setCod_perfil(resultSet.getInt("cod_perfil"));
                reg.setNombre(resultSet.getString("nombre"));
                reg.setNumeroRegistro(i++);
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
    public boolean insert(int num, APerfil perfil, int idAdmin) { //analizar la variale num si num es 1 o 2
        ALogDao logDao = new ALogDaoImpl();
        APerfil perfil1 = new APerfil();
        perfil1 = perfil;int respuesta = 0;
         boolean res = false;
        int resp = 0;
        try {
            ConnectionUDB conn = new ConnectionUDB();
        Connection connection = conn.getConnectionDB();
            if (num == 1) {
                CallableStatement p = connection.prepareCall("select coco_operaciones_perfil(?,?,?,?)");

                p.setInt(1, num);
                p.setInt(2, perfil.getCod_perfil());
                p.setString(3, perfil.getNombre());
                p.setInt(4, idAdmin);

                ResultSet resultSet = p.executeQuery();
                resp = 1;
                System.out.println("RESPPPP : " + resp);
                connection.close();
                res = true;
                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                System.out.println("ENTRA_1 APerfilDaoImpl insert : ");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "perfil");
                respuesta = logDao.insert(1, log);
                System.out.println("RESPUESTAAA : " + respuesta);
                if (respuesta != 0) {
                    int sss = insert_log(perfil1, respuesta);
                    System.out.println("INSERT LOG :" + sss);
                    System.out.println("ENTRA_1   : ");
                }
                
            } else {
                CallableStatement p = connection.prepareCall("select coco_operaciones_perfil(?,?,?,?)");

                p.setInt(1, num);
                p.setInt(2, perfil.getCod_perfil());
                p.setString(3, perfil.getNombre());
                p.setInt(4, idAdmin);

                ResultSet resultSet = p.executeQuery();
                System.out.println("RESPPPP : " + resp);

                resp = 1;
                connection.close();
                res = true;
                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                System.out.println("ENNNNTTTRRRRRA_1   : ");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Modificar : ", max() + 1, "perfil");
                respuesta = logDao.insert(1, log);
                System.out.println("RESPUESTAAA : " + respuesta);
                if (respuesta != 0) {
                    int sss = insert_log(perfil1, respuesta);
                    System.out.println("INSERT LOG :" + sss);
                    System.out.println("ENTRA_1   : ");
                }
            }
            connection.close();            
            
        } catch (SQLException e) {
            UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
            System.out.println("Error SqlExeption" + e.getMessage());
            System.out.println("ipAddress:" + ipAddress);
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar, Error en la base de datos :", max() + 1, "perfil");
                respuesta = logDao.insert(2, log);
                
        } catch (Exception e) {
            UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
            System.out.println("Error :" + e.getMessage());
            System.out.println("ipAddress:" + ipAddress);
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar, Error :", max() + 1, "perfil");
                respuesta = logDao.insert(2, log);
               
        }
        
        return res;
    }

    @Override
    public boolean insertPer_Ope(int num, List<AOperacion> ListOperacion, int idAdmin) {
        boolean res = false;
         ConnectionUDB conn = new ConnectionUDB();
        Connection connection = conn.getConnectionDB();
        System.out.println("APerfilDaoImpl.insertPer_Ope(): ENTRA");
        try {
            if (num == 1) {
                System.out.println("APerfilDaoImpl.insertPer_Ope(): num = 1");
                for (AOperacion opera1 : ListOperacion) {
                    System.out.println("APerfilDaoImpl.insertPer_Ope(): Num"+ num +"maxPerOpe "+maxPerOpe()+" max: "+max() +" getCodOpera :"+ opera1.getCod_opera());
                    CallableStatement po = connection.prepareCall("select coco_operaciones_perfil_opera(?,?,?,?,?)");

                    po.setInt(1, num);
                    po.setInt(2, maxPerOpe()+1);
                    po.setInt(3, max());
                    po.setInt(4, opera1.getCod_opera());
                    po.setInt(5, idAdmin);

                    ResultSet resultSet = po.executeQuery();
                }
                
                connection.close();
                res = true;
            } else {
                
            }
        } catch (Exception e) {
        }
        
        return res;
    }

    @Override
    public int max() {
        int maxp = 0;
        int max = 0;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(cod_perfil) maxp from perfil";
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

    @Override
    public int maxPerOpe() {
        int maxp = 0;
        int max = 0;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(id_perfil_opera) maxp from perfil_opera";
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

    @Override
    public List<AOperacion> find_Ope_Perf(int codigoPerfil) {
        List<AOperacion> operacions = new ArrayList<>();
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select * from sp_findAll_operaciones(" + codigoPerfil + ")";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                AOperacion operacion = new AOperacion();
                
                operacion.setCod_opera(resultSet.getInt("cod_opera_"));
                operacion.setCod_opera_padre(resultSet.getInt("cod_opera_padre_"));
                operacion.setDescripcion(resultSet.getString("descripcion_"));
                operacion.setAplicacion_id_app(resultSet.getInt("aplicacion_id_app"));
                
                operacions.add(operacion);
            }
            connection.close();
        } catch (Exception e) {
        }
        return operacions;
    }

    @Override
    public int max_log() {
        int max = 0;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(cod_perfil) maxp from perfil_log";
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

    @Override
    public int insert_log(APerfil perfil, int id_log) {
         boolean res = false;
        int resp = 0, aux = 0;

        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            CallableStatement p = connection.prepareCall("select sp_operaciones_perfil_log(?,?,?,?)");

                p.setInt(1, 1);
                p.setInt(2, max_log()+1);
                p.setString(3, perfil.getNombre());
                p.setInt(4, id_log);
                ResultSet resultSet = p.executeQuery();
                connection.close();
                res = true;
             aux = max_log()+1;
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }
    
}

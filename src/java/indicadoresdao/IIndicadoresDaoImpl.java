/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresdao;

import controller.UsuarioController;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import indicadoresmodel.IIndicadores;
import indicadoresmodel.IInstitucion;
import indicadoresmodel.ILog;
import util.ConnectionDB;
import util.ConnectionUDB;

/**
 *
 * @author KRETCO
 */
public class IIndicadoresDaoImpl implements IIndicadoresDao {

    ILogDao logDao = new ILogDaoImpl();

    @Override
    public String find_parameters() {
        String resp = "";
        try {
            String query = "select valor from parametros where parametro = 'dbusuario_ip' or parametro = 'dbusuario_usuario' or parametro = 'dbusuario_password'";
            System.out.println("entra : " + query);
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("ENTRAAAAAAAAA : " + resultSet.getString("valor"));
                resp += resultSet.getString("valor") + ",";
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        System.out.println("PARAMETROSSSSSS : " + resp);
        return resp;
    }

    @Override
    public int insert(int num, IIndicadores indicadores) {
        int res = 0;
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        IIndicadores indicadores1 = new IIndicadores();
        indicadores1 = indicadores;

        try {
            if (num == 1) {
                System.out.println("11111111111111111 : " + indicadores.getPregunta_id_pregunta());
                System.out.println("22222222222222222 : " + indicadores.getInstitucion_id_institucion());
                System.out.println("33333333333333333 : " + indicadores.getResultado());

                CallableStatement de = connection.prepareCall("select sp_operaciones_indicadores(?,?,?,?,?,?)");

                de.setInt(1, num);
                de.setInt(2, max() + 1);
                de.setInt(3, indicadores.getPregunta_id_pregunta());
                de.setInt(4, indicadores.getInstitucion_id_institucion());
                de.setDate(5, new java.sql.Date(indicadores.getFecha().getTime()));
                de.setBigDecimal(6, indicadores.getResultado());
                ResultSet resultSet = de.executeQuery();
                connection.close();
                res = 1;
                if (res != 0) {

                    UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                    int respuesta = 0;
                    System.out.println("ipAddress:" + ipAddress);
                    System.out.println("codigo USUARIO : " + obj.getUsuario().getCod_usuario());
                    ILog log = new ILog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "indicadores");
                    //logDao.insert(1, log);
                    respuesta = logDao.insert(1, log);
                    System.out.println("xxxxxxxxxxxxxxx : " + respuesta);
                    if (respuesta != 0) {
                        int sss = insert_log(indicadores1, respuesta);
                        System.out.println("INSERT LOG :" + sss);
                        System.out.println("ENTRA_1   : ");
                    }

                }

            } else {
                CallableStatement de = connection.prepareCall("select sp_operaciones_indicadores(?,?,?,?,?,?)");

                de.setInt(1, num);
                de.setInt(2, indicadores.getId_indicadores());
                de.setInt(3, indicadores.getPregunta_id_pregunta());
                de.setInt(4, indicadores.getInstitucion_id_institucion());
                de.setDate(5, new java.sql.Date(indicadores.getFecha().getTime()));
                de.setBigDecimal(6, indicadores.getResultado());

                ResultSet resultSet = de.executeQuery();
                connection.close();
                res = 1;

                if (res != 0) {
                    UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                    int respuesta = 0;
                    System.out.println("ipAddress:" + ipAddress);
                    System.out.println("codigo USUARIO : " + obj.getUsuario().getCod_usuario());
                    ILog log = new ILog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "indicadores");
                    //logDao.insert(1, log);
                    respuesta = logDao.insert(1, log);
                    System.out.println("xxxxxxxxxxxxxxx : " + respuesta);
                    if (respuesta != 0) {
                        int sss = insert_log(indicadores1, respuesta);
                        System.out.println("INSERT LOG :" + sss);
                        System.out.println("ENTRA_2   : ");
                    }
                }

            }

        } catch (SQLException e) {
            try {
                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                int respuesta = 0;
                System.out.println("ipAddress:" + ipAddress);
                System.out.println("codigo USUARIO : " + obj.getUsuario().getCod_usuario());
                ILog log = new ILog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "indicadores");
                respuesta = logDao.insert(2, log);
                System.out.println("xxxxxxxxxxxxxxx : " + respuesta);

            } catch (Exception ex) {
                try {
                    UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                    int respuesta = 0;
                    System.out.println("ipAddress:" + ipAddress);
                    System.out.println("codigo USUARIO : " + obj.getUsuario().getCod_usuario());
                    ILog log = new ILog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "indicadores");
                    respuesta = logDao.insert(2, log);
                    System.out.println("xxxxxxxxxxxxxxx : " + respuesta);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public int max() {
        int max = 0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(id_indicadores) maxp from indicadores";
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
    public String dev_institucion() {
        String servidorU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("servidorU");
        String baseDatoU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("baseDatoU");
        String usuarioU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("usuarioU");
        String passwordU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("passwordU");
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        String inst = "";
        try {
            UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
            String query = "	select institucion.id_institucion as id_institucion1, institucion.nombre as nombre1 from dblink('host=" + servidorU + " user= " + usuarioU + " password= " + passwordU + " dbname=" + baseDatoU + "',"
                    + " 'SELECT id_institucion, nombre, dependiente, tipo FROM institucion')\n"
                    + "	as institucion(id_institucion integer, nombre varchar, dependiente varchar, tipo varchar) where id_institucion in (select usuario_institucion.institucion_id_institucion \n"
                    + "	from dblink('host=" + servidorU + " user= " + usuarioU + " password= " + passwordU + " dbname=" + baseDatoU + "', 'SELECT id_usuario_institucion, usuario_cod_usuario, institucion_id_institucion FROM usuario_institucion')\n"
                    + "	as usuario_institucion(id_usuario_institucion integer, usuario_cod_usuario integer, institucion_id_institucion integer) where usuario_cod_usuario = " + obj.getUsuario().getCod_usuario() + ")";
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            if (resultSet.next()) {
                IInstitucion institucion = new IInstitucion();

                institucion.setId_institucion(resultSet.getInt("id_institucion1"));
                institucion.setNombre(resultSet.getString("nombre1"));
                inst = institucion.getNombre();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inst;
    }

    @Override
    public int max_log() {
        int max = 0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(id_indicadores) maxp from indicadores_log";
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
    public int insert_log(IIndicadores indicadores, int id_log) {
        int respt = 0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            CallableStatement de = connection.prepareCall("select sp_operaciones_indicadores_log(?,?,?,?,?,?,?)");
            System.out.println("ddddd : " + max_log());
            de.setInt(1, 1);
            de.setInt(2, max_log() + 1);
            de.setInt(3, indicadores.getPregunta_id_pregunta());
            de.setInt(4, indicadores.getInstitucion_id_institucion());
            de.setDate(5, new java.sql.Date(indicadores.getFecha().getTime()));
            de.setBigDecimal(6, indicadores.getResultado());
            de.setInt(7, id_log);
            ResultSet resultSet = de.executeQuery();
            respt = max_log()+1;
            connection.close();
            System.out.println("hhhhhhhhhhhhhhhhhhh : " + respt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respt;
    }

}

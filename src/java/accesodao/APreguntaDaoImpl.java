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
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import accesomodel.ALog;
import accesomodel.APregunta;
import util.ConnectionUDB;

/**
 *
 * @author KRETCO
 */
public class APreguntaDaoImpl implements APreguntaDao {
    ALogDao logDao = new ALogDaoImpl();
    @Override
    public List<APregunta> findAll_Preguntas() {
        List<APregunta> lista = new ArrayList<>();
        int i = 1;
        try {
            String query = "select p.id_pregunta as preg, p.descripcion as descrip, p.institucion_id_institucion as inst, i.nombre as nom from pregunta p join institucion i on p.institucion_id_institucion = i.id_institucion order by p.* asc";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                APregunta pregunta = new APregunta();

                pregunta.setId_pregunta(resultSet.getInt("preg"));
                pregunta.setDescripcion(resultSet.getString("descrip"));
                pregunta.setInstitucion_id_institucion(resultSet.getInt("inst"));
                pregunta.setDescripcionInst(resultSet.getString("nom"));
                pregunta.setNumRegistro(i++);
                lista.add(pregunta);
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
    public boolean insert(int num, APregunta pregunta) {
        boolean res = false;

        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            if (num == 1) {
                System.out.println("maaaaaxxxxx : " + max());
                System.out.println("sssssss : " + pregunta.getDescripcion());
                System.out.println("sssssss : " + pregunta.getInstitucion_id_institucion());
                CallableStatement p = connection.prepareCall("select sp_operaciones_pregunta(?,?,?,?)");
                p.setInt(1, num);
                p.setInt(2, max() + 1);
                p.setString(3, pregunta.getDescripcion());
                p.setInt(4, pregunta.getInstitucion_id_institucion());
                p.executeQuery();  
                res = true;
                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                int respuesta = 0;
                System.out.println("ENNNNTTTRRRRRA_1   : ");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "pregunta");
                respuesta = logDao.insert(1, log);
                System.out.println("RESPUESTAAA : " + respuesta);
                
            } else {
                CallableStatement p = connection.prepareCall("select sp_operaciones_pregunta(?,?,?,?)");
                p.setInt(1, num);
                p.setInt(2, pregunta.getId_pregunta());
                p.setString(3, pregunta.getDescripcion());
                p.setInt(4, pregunta.getInstitucion_id_institucion());
                p.executeQuery();
                res = true;
                
                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                int respuesta = 0;
                System.out.println("ENNNNTTTRRRRRA_1   : ");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Modificar : ", max() + 1, "pregunta");
                respuesta = logDao.insert(1, log);
                System.out.println("RESPUESTAAA : " + respuesta);
            }
            
            connection.close();
        } catch (SQLException ex) {
            ex.getMessage();
        } catch (Exception e) {
            e.getMessage();
        }
        return res;
    }

    @Override
    public List<SelectItem> findAll_nInst(String descrip) {
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select i.* from institucion i where i.tipo = '" + descrip + "' order by i.* asc";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                item.add(new SelectItem(resultSet.getInt("id_institucion"), resultSet.getString("nombre")));
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
    public int max() {
        int max = 0;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(id_pregunta) maxp from pregunta";
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

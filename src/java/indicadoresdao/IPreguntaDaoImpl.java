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
import javax.faces.context.FacesContext;
import indicadoresmodel.IPregunta;
import util.ConnectionDB;
import util.ConnectionUDB;

/**
 *
 * @author KRETCO
 */
public class IPreguntaDaoImpl implements IPreguntaDao{
    
    @Override
    public String find_parameters() {
        String resp = "";
         try {
            String query = "select valor from parametros where parametro = 'dbusuario_ip' or parametro = 'dbusuario_usuario' or parametro = 'dbusuario_password'";
            System.out.println("entra : " + query);
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            //System.out.println("query:" + query);
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
    public int insert(int num, IPregunta pregunta) {
        int res = 0;
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try {
            if (num == 1) {
                CallableStatement pr = connection.prepareCall("select sp_operaciones_pregunta(?,?,?,?)");

                pr.setInt(1, num);
                pr.setInt(2, max()+1);
                pr.setString(3, pregunta.getDescripcion());
                pr.setInt(4, pregunta.getInstitucion_id_institucion());
                
                ResultSet resultSet = pr.executeQuery();
                connection.close();
                res = 1;
            } else {
                CallableStatement pr = connection.prepareCall("select sp_operaciones_pregunta(?,?,?,?)");

                pr.setInt(1, num);
                pr.setInt(2, pregunta.getId_pregunta());
                pr.setString(3, pregunta.getDescripcion());
                pr.setInt(4, pregunta.getInstitucion_id_institucion());
                
                ResultSet resultSet = pr.executeQuery();
                connection.close();
                res = 1;                                                        
            }
            
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }

        return res;
    }

    @Override
    public int max() {
        int maxp = 0;
        int max = 0;
        try {
            ConnectionDB conn = new ConnectionDB();
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

    @Override
     public List<IPregunta> findAllIdt(int id) {
        List<IPregunta> lista = new ArrayList<>();
        String servidorU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("servidorU");
        String baseDatoU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("baseDatoU");
        String usuarioU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("usuarioU");
        String passwordU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("passwordU");
        try {
            String query = "	select pregunta.id_pregunta as id_pregunta1, pregunta.descripcion as descripcion1, pregunta.institucion_id_institucion as institucion_id_institucion1 from dblink('host="+servidorU+" user= "+usuarioU+" password= "+passwordU+" dbname="+baseDatoU+"', 'SELECT id_pregunta, descripcion, institucion_id_institucion FROM pregunta')\n" +
"	as pregunta(id_pregunta integer, descripcion varchar, institucion_id_institucion integer) join (select usuario_institucion.* from dblink('host="+servidorU+" user= "+usuarioU+" password= "+passwordU+" \n" +
"	dbname="+baseDatoU+"', 'SELECT id_usuario_institucion, usuario_cod_usuario, institucion_id_institucion FROM usuario_institucion')\n" +
"	as usuario_institucion(id_usuario_institucion integer, usuario_cod_usuario integer, institucion_id_institucion integer)) u on pregunta.institucion_id_institucion = u.institucion_id_institucion\n" +
"	where u.usuario_cod_usuario = " + id;
            ConnectionDB conn=new ConnectionDB();
            Connection connection=conn.getConnectionDB();
            Statement statement=connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                IPregunta reg= new IPregunta();
                reg.setId_pregunta(resultSet.getInt("id_pregunta1"));
                reg.setDescripcion(resultSet.getString("descripcion1"));
                reg.setInstitucion_id_institucion(resultSet.getInt("institucion_id_institucion1"));
                lista.add(reg);

            }
            connection.close();
        } catch(SQLException e){
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return lista;
    }
     
     
    
}

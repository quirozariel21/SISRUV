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
import accesomodel.AInstitucion;
import accesomodel.ALog;
import accesomodel.APerfil;
import accesomodel.AUsuario;
import accesomodel.AUsuarioInstitucion;
import accesomodel.AUsuario_perfil;
import util.ConnectionUDB;

/**
 *
 * @author KRETCO
 */
public class AInstitucionDaoImpl implements AInstitucionDao {
    ALogDao logDao = new ALogDaoImpl();
    AUsuarioDao usuarioDao = new AUsuarioDaoImpl();
    
    @Override
    public List<AInstitucion> findAll_Instituciones() {
        List<AInstitucion> lista = new ArrayList<>();
        List<AUsuarioInstitucion> listUsuInst = new ArrayList<>();
        
        List<AUsuario> listUsuario = new ArrayList<>();
        listUsuInst = findAll_UsuarioInstitucion();
        listUsuario = listUsuario();
        int i = 1, z = 0;
        try {
            String query = "select i.* from institucion i order by i.* asc";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String usuarioAsignado = "";
                AInstitucion institucion = new AInstitucion();

                institucion.setId_institucion(resultSet.getInt("id_institucion"));
                institucion.setNombre(resultSet.getString("nombre"));
                institucion.setDependiente(resultSet.getString("dependiente"));
                institucion.setTipo(resultSet.getString("tipo"));
                institucion.setNumRegistro(i++);
//                z = resultSet.getInt("id_institucion");
//                System.out.println("zzzzzzzzzzzz : " + z);
//                for (UsuarioInstitucion usuarioInstitucion : listUsuInst) {
//                    if(usuarioInstitucion.getInstitucion_id_institucion() == z){
//                        for (Usuario usu : listUsuario) {
//                            if(usu.getCod_usuario() == usuarioInstitucion.getUsuario_cod_usuario()){
//                                usuarioAsignado = "SI";
//                            }
//                        }
//                    }
//                }
//                System.out.println("uuuu : u" + usuarioAsignado);
//                institucion.setUsuariodesignado(usuarioAsignado);
                
                lista.add(institucion);
               // System.out.println("LISTAAAAAAAA : " + lista.size());
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
    public boolean insert(int num, AInstitucion institucion) {
        boolean res = false;
        int resp = 0;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            if (num == 1) {
                System.out.println("maaaaaxxxxx : " + max());
                CallableStatement ui = connection.prepareCall("select sp_operaciones_institucion(?,?,?,?,?)");
                ui.setInt(1, num);
                ui.setInt(2, max() + 1);
                ui.setString(3, institucion.getNombre());
                ui.setString(4, institucion.getDependiente());
                ui.setString(5, institucion.getTipo());
                resp = max() + 1;
                ui.executeQuery();
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
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "institucion");
                respuesta = logDao.insert(1, log);
                System.out.println("RESPUESTAAA : " + respuesta);
                
            } else {
                CallableStatement ui = connection.prepareCall("select sp_operaciones_institucion(?,?,?,?,?)");
                ui.setInt(1, num);
                ui.setInt(2, institucion.getId_institucion());
                ui.setString(3, institucion.getNombre());
                ui.setString(4, institucion.getDependiente());
                ui.setString(5, institucion.getTipo());
                ui.executeQuery();
                resp = institucion.getId_institucion();
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
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Modificar : ", max() + 1, "institucion");
                respuesta = logDao.insert(1, log);
                System.out.println("RESPUESTAAA : " + respuesta);
            }
            connection.close();
            

        } catch (SQLException ex) {
            ex.getMessage();
            UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                int respuesta = 0;
                System.out.println("ENNNNTTTRRRRRA_1   : ");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar, Error en la base de datos: ", max() + 1, "institucion");
                respuesta = logDao.insert(2, log);
                System.out.println("RESPUESTAAA : " + respuesta);
        } catch (Exception e) {
            UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                int respuesta = 0;
                System.out.println("ENNNNTTTRRRRRA_1   : ");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar, Error : ", max() + 1, "institucion");
                respuesta = logDao.insert(2, log);
                System.out.println("RESPUESTAAA : " + respuesta);
            e.getMessage();
        }
        return res;
    }

    @Override
    public int max() {
        int max = 0;
        try {
            ConnectionUDB conn = new ConnectionUDB();
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

    @Override
    public List<AInstitucion> listInstitucion(Integer idInstitucion) {
        List<AInstitucion> lista = new ArrayList<>();
        try {
            String query = "select * from institucion where id_institucion = " + idInstitucion;
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AInstitucion reg = new AInstitucion();
                reg.setId_institucion(resultSet.getInt("idInstitucion"));
                reg.setNombre(resultSet.getString("nombre"));
                reg.setDependiente(resultSet.getString("dependiente"));
                reg.setTipo(resultSet.getString("tipo"));
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
    public List<SelectItem> findAll_nInst() {
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select i.* from institucion i order by i.* asc";
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
    public List<SelectItem> findAll_Inst(int codInstitucion) {
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select i.* from institucion i where id_institucion = " + codInstitucion + "order by i.* asc";
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
    public String findAll_tipo(int codInstitucion) {
       String resp = "";
        try {
            String query = "select * from institucion where id_institucion = " + codInstitucion;
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                AInstitucion reg = new AInstitucion();
                reg.setId_institucion(resultSet.getInt("idInstitucion"));
                reg.setNombre(resultSet.getString("nombre"));
                reg.setDependiente(resultSet.getString("dependiente"));
                reg.setTipo(resultSet.getString("tipo"));
                resp = reg.getTipo();
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return  resp;
    }

    @Override
    public boolean modifica(int codUsuario, int idInstitucion) {
        boolean res =false;
        try {
            String query = "select sp_operaciones_UpdateInst(" + codUsuario + "," + idInstitucion +")" ;
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("queryyyyy" + query);
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            res = true;

        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return res;
    }

    @Override
    public boolean insertUsuInst(int num, int codUsuario, int idInstitucion) {
        boolean res = false;

        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            if (num == 1) {
                System.out.println("maaaaaxxxxx : " + max());
                CallableStatement ui = connection.prepareCall("select sp_operaciones_usuario_institucion(?,?,?,?)");
                ui.setInt(1, num);
                ui.setInt(2, max());
                ui.setInt(3, codUsuario);
                ui.setInt(4, idInstitucion);
                
                ui.executeQuery();
                res = true;
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
    public AUsuario findUsuInst(int codUsuInst) {
        AUsuario usuario = new AUsuario();
        System.out.println("EL COSIGO DE LA INSTITUCION ES : " + codUsuInst);
        try {
            String query = "select username, nombre, paterno, materno from usuario where cod_usuario in (select usuario_cod_usuario from usuario_institucion where institucion_id_institucion = " + codUsuInst + ")";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setPaterno(resultSet.getString("paterno"));
                usuario.setMaterno(resultSet.getString("materno"));
                usuario.setUsername(resultSet.getString("username"));
            }else{
                usuario.setNombre("La institucion ");
                usuario.setPaterno("no esta relacionada");
                usuario.setMaterno("con ningun usuario");
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return usuario;
    }

    @Override
    public List<AUsuarioInstitucion> findAll_UsuarioInstitucion() {
        List<AUsuarioInstitucion> instituciones = new ArrayList<>();
        AUsuarioInstitucion usuarioInstitucion = new AUsuarioInstitucion();
        try {
            String query = "select * from usuario_institucion";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                usuarioInstitucion.setId_usuario_institucion(resultSet.getInt("id_usuario_institucion"));
                usuarioInstitucion.setInstitucion_id_institucion(resultSet.getInt("institucion_id_institucion"));
                usuarioInstitucion.setUsuario_cod_usuario(resultSet.getInt("usuario_cod_usuario"));
                
                instituciones.add(usuarioInstitucion);
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return instituciones;
    }
    
    public List<AUsuario> listUsuario() {
        List<AUsuario> lista = new ArrayList<>();
        try {
            String query = "select u.* from usuario u order by u.* asc";
            System.out.println("query : " + query);     
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                AUsuario reg = new AUsuario();
                reg.setCod_usuario(resultSet.getInt("cod_usuario"));
                reg.setUsername(resultSet.getString("username"));
                reg.setPassword(resultSet.getString("password"));
                reg.setEstado(resultSet.getBoolean("estado"));
                reg.setNombre(resultSet.getString("nombre"));
                reg.setPaterno(resultSet.getString("paterno"));
                reg.setMaterno(resultSet.getString("materno"));
                reg.setCi(resultSet.getInt("ci"));
                reg.setFecha_reg(resultSet.getDate("fecha_reg"));
                reg.setDepartamento(resultSet.getInt("departamento"));
                reg.setMunicipio(resultSet.getInt("municipio"));
                reg.setCod_servicio(resultSet.getInt("cod_servicio"));
                reg.setCod_sub_servicio(resultSet.getInt("cod_sub_servicio"));
                reg.setCodigo_exp(resultSet.getInt("codigo_exp"));
                reg.setE_mail(resultSet.getString("e_mail"));
                reg.setTelefono(resultSet.getInt("telefono"));
                reg.setCelular(resultSet.getInt("celular"));
                reg.setDireccion(resultSet.getString("direccion"));
                reg.setFecha_estado(resultSet.getDate("fecha_estado"));
                reg.setAcceso(resultSet.getString("acceso"));
                
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

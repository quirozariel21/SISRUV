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
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import accesomodel.ALog;
import accesomodel.APerfil;
import accesomodel.APerfil_opera;
import accesomodel.AUsuario;
import accesomodel.AUsuario_perfil;
import util.ConnectionUDB;
import accesosecurity.ASeguridad;

/**
 *
 * @author KRETCO A
 */
public class AUsuarioDaoImpl implements AUsuarioDao {

    ALogDao logDao = new ALogDaoImpl();
    int isAdmin;

    public AUsuario login(AUsuario usuario) {
        AUsuario model = this.findUsuario(usuario);
        // Seguridad seguridad = new Seguridad();
        if (model != null) {
            //model.setPassword(seguridad.desencriptar(model.getPassword()));
            System.out.println("a" + usuario.getPassword() + "b" + model.getPassword());

            if (!usuario.getPassword().equals(model.getPassword())) {
                model = null;
            }
        }
        return model;
    }

    //---------------------- Con la base de datos usuarios---------------------- //
    @Override
    public AUsuario findUsuario(AUsuario usuario) {
        System.out.println("print: " + usuario.getUsername());
        System.out.println("print: " + usuario.getPassword());
        AUsuario registro = new AUsuario();
        ASeguridad seguridad = new ASeguridad();
        String ddd = "";
        try {

            String query = "SELECT * FROM coco_login_usuario( '" + usuario.getUsername() + "', '" + usuario.getPassword() + "')"; // REVISAR sp_login_usuario
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
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
                registro.setCi(resultSet.getInt("ci"));
                registro.setFecha_reg(resultSet.getDate("fecha_reg"));
                registro.setDepartamento(resultSet.getInt("departamento"));
                registro.setMunicipio(resultSet.getInt("municipio"));
                registro.setCod_servicio(resultSet.getInt("cod_servicio"));
                registro.setCod_sub_servicio(resultSet.getInt("cod_sub_servicio"));
                registro.setFecha_estado(resultSet.getDate("fecha_reg"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return registro;
    }

    @Override
    public List<AUsuario> listUsuario() {
        List<AUsuario> lista = new ArrayList<>();
        List<APerfil> listPerfiles = new ArrayList<>();
        List<AUsuario_perfil> usuario_perfiles = new ArrayList<>();
        APerfilDao perfilDao = new APerfilDaoImpl();
        APerfil perfil = new APerfil();
        AUsuario_perfilDao usuario_perfilDao = new AUsuario_perfilDaoImpl();
        AUsuario_perfil usuario_perfil = new AUsuario_perfil();
        usuario_perfiles = usuario_perfilDao.listUsuarioPerfil();
        listPerfiles = perfilDao.findAll_Perfil();
        try {
            String query = "select u.* from usuario u order by u.* asc";
            System.out.println("query : " + query);     
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int i = 1;
            
            while (resultSet.next()) {
                String resp = "";
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
                reg.setNumRegistro(i++);
                //reg.setMuestraPerfil(find_perfilXusuario(resultSet.getInt("cod_usuario")));
                for (AUsuario_perfil usu_per : usuario_perfiles) {
                    if (usu_per.getUsuario_cod_usuario() == resultSet.getInt("cod_usuario")) {
                        for (APerfil per : listPerfiles) {
                            if (usu_per.getPerfil_cod_perfil() == per.getCod_perfil()) {
                                resp = resp + per.getNombre() + " | ";
                            }
                        }
                    }
                }
                reg.setMuestraPerfil(resp);

                lista.add(reg);
                System.out.println("LISTAAAAAAAAAA : " + lista.size());
            }
            System.out.println("iiiiiiiiiiiiiiiii : " + i);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean addUsuario(int num, AUsuario usuario, int idAdmin) {
        boolean res = false;
        int resp = 0;
        this.isAdmin = idAdmin;
        AUsuario usuario1 = new AUsuario();
        usuario1 = usuario;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            if (num == 1) {
                System.out.println("dat : " + num + usuario.getCod_usuario());
                CallableStatement callstate = connection.prepareCall("select coco_operaciones_usuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); //coco_operaciones_usuario
                callstate.setInt(1, num);
                callstate.setInt(2, usuario.getCod_usuario());
                callstate.setString(3, usuario.getUsername());
                callstate.setString(4, usuario.getPassword());
                callstate.setBoolean(5, true);
                callstate.setString(6, usuario.getNombre());
                callstate.setString(7, usuario.getPaterno());
                callstate.setString(8, usuario.getMaterno());
                callstate.setInt(9, usuario.getCi());
                callstate.setInt(10, usuario.getDepartamento());
                callstate.setInt(11, usuario.getMunicipio());
                callstate.setInt(12, usuario.getCod_servicio());
                callstate.setInt(13, usuario.getCod_sub_servicio());
                callstate.setInt(14, usuario.getCodigo_exp());
                callstate.setString(15, usuario.getE_mail());
                callstate.setInt(16, usuario.getTelefono());
                callstate.setInt(17, usuario.getCelular());
                callstate.setString(18, usuario.getDireccion());
                callstate.setDate(19, new java.sql.Date(new Date().getTime()));
                callstate.setString(20, "ROLE_USER");
                callstate.setInt(21, idAdmin);
                callstate.executeQuery();
                resp = usuario.getCod_usuario();
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
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "usuario");
                respuesta = logDao.insert(1, log);
                System.out.println("RESPUESTAAA : " + respuesta);
                if (respuesta != 0) {
                    int sss = insert_log(usuario1, respuesta);
                    System.out.println("INSERT LOG :" + sss);
                    System.out.println("ENTRA_1   : ");
                }
                
            } else {
                System.out.println("INGRESAAAAAAAAAAAAAAAAAAA : ");
                CallableStatement callstate = connection.prepareCall("select coco_operaciones_usuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                callstate.setInt(1, num);
                callstate.setInt(2, usuario.getCod_usuario());
                callstate.setString(3, usuario.getUsername());
                callstate.setString(4, usuario.getPassword());
                callstate.setBoolean(5, usuario.isEstado());
                callstate.setString(6, usuario.getNombre());
                callstate.setString(7, usuario.getPaterno());
                callstate.setString(8, usuario.getMaterno());
                callstate.setInt(9, usuario.getCi());
                callstate.setInt(10, usuario.getDepartamento());
                callstate.setInt(11, usuario.getMunicipio());
                callstate.setInt(12, usuario.getCod_servicio());
                callstate.setInt(13, usuario.getCod_sub_servicio());
                callstate.setInt(14, usuario.getCodigo_exp());
                callstate.setString(15, usuario.getE_mail());
                callstate.setInt(16, usuario.getTelefono());
                callstate.setInt(17, usuario.getCelular());
                callstate.setString(18, usuario.getDireccion());
                callstate.setDate(19, new java.sql.Date(usuario.getFecha_estado().getTime()));
                callstate.setString(20, "ROLE_USER");
                callstate.setInt(21, idAdmin);
                callstate.executeQuery();
                resp = usuario.getCod_usuario();
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
                ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Modificar : ", max() + 1, "usuario");
                respuesta = logDao.insert(1, log);
                System.out.println("RESPUESTAAA : " + respuesta);
                if (respuesta != 0) {
                    int sss = insert_log(usuario1, respuesta);
                    System.out.println("INSERT LOG :" + sss);
                    System.out.println("ENTRA_1   : ");
                }
                
            }
            connection.close();
            System.out.println("deedeedeeeeeee : " + res);

        } catch (SQLException ex) {
            ex.printStackTrace();
            UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            int respuesta = 0;
            System.out.println("ENNNNTTTRRRRRA_1   : ");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            System.out.println("ipAddress:" + ipAddress);
            ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Error al Insertar en la base de datos: ", max() + 1, "usuario");
            respuesta = logDao.insert(1, log);
            System.out.println("RESPUESTAAA : " + respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            int respuesta = 0;
            System.out.println("ENNNNTTTRRRRRA_1   : ");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            System.out.println("ipAddress:" + ipAddress);
            ALog log = new ALog(obj.getUsuario().getCod_usuario(), ipAddress, "Error al Insertar : ", max() + 1, "usuario");
            respuesta = logDao.insert(1, log);
            System.out.println("RESPUESTAAA : " + respuesta);
        }
        return res;
    }

    @Override
    public boolean delUsuario(Integer cod_usuario) {
        boolean res = false;
        try {
            String detele = "DELETE FROM usuario  WHERE  cod_usuario = " + cod_usuario;
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            statement.execute(detele);
            res = true;
        } catch (SQLException e) {
            e.getMessage();
        }
        return res;
    }
//---------------------- Con la base de datos usuarios---------------------- //

    @Override
    public int devSubServicio(int cod) {
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
    //---------------------- Con la base de datos usuarios---------------------- //

    @Override
    public boolean insertUsu(int num, AUsuario usuario, List<APerfil> perfil) {
        boolean res = false;
        ConnectionUDB conn = new ConnectionUDB();
        Connection connection = conn.getConnectionDB();
        System.out.println("maximoUsuario : " + max());
        AUsuario_perfil usuario_perfil = new AUsuario_perfil();
        APerfil_opera perfil_opera = new APerfil_opera();
        try {
            if (num == 1) {
                connection.setAutoCommit(false);
                CallableStatement u = connection.prepareCall("select sp_operaciones_usuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                u.setInt(1, num);
                u.setInt(2, max() + 1);
                u.setString(3, usuario.getUsername());
                u.setString(4, usuario.getPassword());
                u.setBoolean(5, usuario.isEstado());
                u.setString(6, usuario.getNombre());
                u.setString(7, usuario.getPaterno());
                u.setString(8, usuario.getMaterno());
                u.setInt(9, usuario.getCi());
                u.setInt(10, usuario.getDepartamento());
                u.setInt(11, usuario.getMunicipio());
                u.setInt(12, usuario.getCod_servicio());
                u.setInt(13, usuario.getCod_sub_servicio());
                u.setInt(14, usuario.getCodigo_exp());
                u.setString(15, usuario.getE_mail());
                u.setInt(16, usuario.getTelefono());
                u.setInt(17, usuario.getCelular());
                u.setString(18, usuario.getDireccion());
                u.setDate(19, new java.sql.Date(new Date().getTime()));
                u.setString(20, "ROLE_USER");

                ResultSet resultSet = u.executeQuery();

                CallableStatement up = connection.prepareCall("select sp_operaciones_usuario_perfil(?,?,?,?)");

                up.setInt(1, num);
                up.setInt(2, 2);
                up.setInt(3, max() + 1);
                up.setInt(4, 2);
                ResultSet resultSet2 = up.executeQuery();

                connection.commit();
                connection.close();

                res = true;
            } else {

                connection.setAutoCommit(false);
                CallableStatement u = connection.prepareCall("select sp_operaciones_usuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                u.setInt(1, num);
                u.setInt(2, usuario.getCod_usuario());
                u.setString(3, usuario.getUsername());
                u.setString(4, usuario.getPassword());
                u.setBoolean(5, usuario.isEstado());
                u.setString(6, usuario.getNombre());
                u.setString(7, usuario.getPaterno());
                u.setString(8, usuario.getMaterno());
                u.setInt(9, usuario.getCi());
                u.setInt(10, usuario.getDepartamento());
                u.setInt(11, usuario.getMunicipio());
                u.setInt(12, usuario.getCod_servicio());
                u.setInt(13, usuario.getCod_sub_servicio());
                u.setInt(14, usuario.getCodigo_exp());
                u.setString(15, usuario.getE_mail());
                u.setInt(16, usuario.getTelefono());
                u.setInt(17, usuario.getCelular());
                u.setString(18, usuario.getDireccion());
                u.setDate(19, new java.sql.Date(usuario.getFecha_estado().getTime()));
                u.setString(20, usuario.getAcceso());

                ResultSet resultSet = u.executeQuery();

                CallableStatement up = connection.prepareCall("select sp_operaciones_usuario_perfil(?,?,?,?)");

                up.setInt(1, num);
                up.setInt(2, usuario_perfil.getId_usuario_perfil());
                up.setInt(3, usuario_perfil.getUsuario_cod_usuario());
                up.setInt(4, usuario_perfil.getPerfil_cod_perfil());

                ResultSet resultSet2 = up.executeQuery();

                connection.commit();
                connection.close();

                res = true;
            }
        } catch (SQLException e) {
            e.getMessage();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }

        return res;

    }

    //---------------------- Con la base de datos usuarios---------------------- //
    @Override
    public int max() {
        int maxp = 0;
        int max = 0;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(cod_usuario) maxp from usuario";
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

    //---------------------- Con la base de datos usuarios---------------------- //
    @Override
    public boolean insertPer(int num, APerfil perfil) {
        boolean res = false;
        ConnectionUDB conn = new ConnectionUDB();
        Connection connection = conn.getConnectionDB();
        try {
            if (num == 1) {
                CallableStatement p = connection.prepareCall("select sp_operaciones_perfil(?,?,?)");

                p.setInt(1, num);
                p.setInt(2, perfil.getCod_perfil());
                p.setString(3, perfil.getNombre());

                ResultSet resultSet = p.executeQuery();
                connection.close();
                res = true;
            } else {
                CallableStatement p = connection.prepareCall("select sp_operaciones_perfil(?,?,?)");

                p.setInt(1, num);
                p.setInt(2, perfil.getCod_perfil());
                p.setString(3, perfil.getNombre());

                ResultSet resultSet = p.executeQuery();
                connection.close();
                res = true;
            }
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }

        return res;
    }

    //---------------------- Con la base de datos usuarios---------------------- //
    @Override
    public boolean insertUsu_Per(int num, AUsuario_perfil usuario_perfil) {
        boolean res = false;
        ConnectionUDB conn = new ConnectionUDB();
        Connection connection = conn.getConnectionDB();
        try {
            if (num == 1) {
                CallableStatement up = connection.prepareCall("select sp_operaciones_usuario_perfil(?,?,?,?)");

                up.setInt(1, num);
                up.setInt(2, usuario_perfil.getId_usuario_perfil());
                up.setInt(3, usuario_perfil.getUsuario_cod_usuario());
                up.setInt(4, usuario_perfil.getPerfil_cod_perfil());

                ResultSet resultSet = up.executeQuery();
                connection.close();
                res = true;
            } else {
                CallableStatement up = connection.prepareCall("select sp_operaciones_usuario_perfil(?,?,?,?)");

                up.setInt(1, num);
                up.setInt(2, usuario_perfil.getId_usuario_perfil());
                up.setInt(3, usuario_perfil.getUsuario_cod_usuario());
                up.setInt(4, usuario_perfil.getPerfil_cod_perfil());

                ResultSet resultSet = up.executeQuery();
                connection.close();
                res = true;
            }
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }

        return res;
    }

    @Override
    public boolean insertPerOpe(int num, APerfil_opera perfil_opera) {
        boolean res = false;
        ConnectionUDB conn = new ConnectionUDB();
        Connection connection = conn.getConnectionDB();
        try {
            if (num == 1) {
                CallableStatement po = connection.prepareCall("select sp_operaciones_perfil_opera(?,?,?,?)");

                po.setInt(1, num);
                po.setInt(2, perfil_opera.getId_perfil_opera());
                po.setInt(3, perfil_opera.getPerfil_cod_perfil());
                po.setInt(4, perfil_opera.getOperacion_cod_opera());

                ResultSet resultSet = po.executeQuery();
                connection.close();
                res = true;
            } else {
                CallableStatement po = connection.prepareCall("select sp_operaciones_perfil_opera(?,?,?,?)");

                po.setInt(1, num);
                po.setInt(2, perfil_opera.getId_perfil_opera());
                po.setInt(3, perfil_opera.getPerfil_cod_perfil());
                po.setInt(4, perfil_opera.getOperacion_cod_opera());

                ResultSet resultSet = po.executeQuery();
                connection.close();
                res = true;
            }
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }

        return res;
    }

    @Override
    public List<SelectItem> findAll_Pefil() {
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select p.* from perfil p order by p asc";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                item.add(new SelectItem(resultSet.getInt("cod_perfil"), resultSet.getString("nombre")));
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
    public String tipoUsuario(Integer cod_usuario) {
        String respuesta = "";
        try {
            String query = "select u.*, i.tipo from usuario u join institucion i ON u.id_institucion = i.id_institucion where u.cod_usuario = " + cod_usuario;
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("sssssss" + query);
            if (resultSet.next()) {
                respuesta = resultSet.getString("tipo");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption en tipoUsuario()" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public List<AUsuario> listUsuInst() {
        List<AUsuario> lista = new ArrayList<>();
        try {
            String query = "select * from view_lista_usuarios_den_ind";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AUsuario usuario = new AUsuario();

                usuario.setCod_usuario(resultSet.getInt("cod_usuario"));
                usuario.setUsername(resultSet.getString("username"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setEstado(resultSet.getBoolean("estado"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setPaterno(resultSet.getString("paterno"));
                usuario.setMaterno(resultSet.getString("materno"));
                usuario.setCi(resultSet.getInt("ci"));
                usuario.setFecha_reg(resultSet.getDate("fecha_reg"));
                usuario.setDepartamento(resultSet.getInt("departamento"));
                usuario.setMunicipio(resultSet.getInt("municipio"));
                usuario.setCod_servicio(resultSet.getInt("cod_servicio"));
                usuario.setCod_sub_servicio(resultSet.getInt("cod_sub_servicio"));
                usuario.setCodigo_exp(resultSet.getInt("codigo_exp"));
                usuario.setE_mail(resultSet.getString("e_mail"));
                usuario.setTelefono(resultSet.getInt("telefono"));
                usuario.setCelular(resultSet.getInt("celular"));
                usuario.setDireccion(resultSet.getString("direccion"));
                usuario.setFecha_estado(resultSet.getDate("fecha_estado"));
                usuario.setAcceso(resultSet.getString("acceso"));

                lista.add(usuario);
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
    public List<SelectItem> findAll_UsuInst() {
        List<SelectItem> item = new ArrayList<>();
        try {
            String query = "select * from usuario u left join usuario_institucion ui on u.cod_usuario = ui.usuario_cod_usuario where ui.usuario_cod_usuario is null";
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                item.add(new SelectItem(resultSet.getInt("cod_usuario"), resultSet.getString("username")));
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
    public boolean find_username(String username, int ci) {
        AUsuario usuario = new AUsuario();
        boolean resp = false;
        try {
            String query = "select * from usuario where username = '" + username + "' or ci =" + ci;
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
                resp = true;
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return resp;
    }

    @Override
    public boolean find_usernameUpdate(String username, int ci, String selectedUsername, int selecttedCi) {
        AUsuario usuario = new AUsuario();
        boolean resp = false;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            if (!username.equals(selectedUsername) && ci != selecttedCi) {
                String query = "select * from usuario where username = '" + username + "' or ci =" + ci;

                Statement statement = connection.createStatement();
                System.out.println("query" + query);
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    usuario.setNombre(resultSet.getString("nombre"));
                    usuario.setPaterno(resultSet.getString("paterno"));
                    usuario.setMaterno(resultSet.getString("materno"));
                    usuario.setUsername(resultSet.getString("username"));
                    resp = true;
                    System.out.println("111");
                }

            }
            if (!username.equals(selectedUsername)) {
                String query = "select * from usuario where username = '" + username + "'";

                Statement statement = connection.createStatement();
                System.out.println("query" + query);
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    usuario.setNombre(resultSet.getString("nombre"));
                    usuario.setPaterno(resultSet.getString("paterno"));
                    usuario.setMaterno(resultSet.getString("materno"));
                    usuario.setUsername(resultSet.getString("username"));
                    resp = true;
                    System.out.println("222");
                }
            }
            if (ci != selecttedCi) {
                String query = "select * from usuario where ci = " + ci;

                Statement statement = connection.createStatement();
                System.out.println("query" + query);
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    usuario.setNombre(resultSet.getString("nombre"));
                    usuario.setPaterno(resultSet.getString("paterno"));
                    usuario.setMaterno(resultSet.getString("materno"));
                    usuario.setUsername(resultSet.getString("username"));
                    resp = true;
                    System.out.println("333");
                }

            }
            System.out.println("ÑÑÑÑÑÑÑÑññññ : " + resp);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return resp;
    }

    @Override
    public String find_perfilXusuario(int codigo_usuario) {
        AUsuario usuario = new AUsuario();
        String resp = "";
        try {
            String query = "select p.nombre as nomb from perfil p join usuario_perfil up on p.cod_perfil = up.perfil_cod_perfil join usuario u\n"
                    + "on up.usuario_cod_usuario = u.cod_usuario  where u.cod_usuario = " + codigo_usuario;
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                resp = resp + resultSet.getString("nomb") + ",";
            }
            resp = resp.substring(0, resp.length() - 1);
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return resp;
    }

    public int insert_log(AUsuario usuario, int id_log) {
        boolean res = false;
        int resp = 0, aux = 0;

        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            System.out.println("dat : " + max_log() + 1);
            CallableStatement callstate = connection.prepareCall("select sp_operaciones_usuario_log(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            callstate.setInt(1, 1);
            callstate.setInt(2, max_log() + 1);
            callstate.setString(3, usuario.getUsername());
            callstate.setString(4, usuario.getPassword());
            callstate.setBoolean(5, true);
            callstate.setString(6, usuario.getNombre());
            callstate.setString(7, usuario.getPaterno());
            callstate.setString(8, usuario.getMaterno());
            callstate.setInt(9, usuario.getCi());
            callstate.setInt(10, usuario.getDepartamento());
            callstate.setInt(11, usuario.getMunicipio());
            callstate.setInt(12, usuario.getCod_servicio());
            callstate.setInt(13, usuario.getCod_sub_servicio());
            callstate.setInt(14, usuario.getCodigo_exp());
            callstate.setString(15, usuario.getE_mail());
            callstate.setInt(16, usuario.getTelefono());
            callstate.setInt(17, usuario.getCelular());
            callstate.setString(18, usuario.getDireccion());
            callstate.setDate(19, new java.sql.Date(new Date().getTime()));
            callstate.setString(20, "ROLE_USER");
            callstate.setInt(21, id_log);
            resp = usuario.getCod_usuario();
            callstate.executeQuery();
            res = true; aux = max_log()+1;
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }

    @Override
    public int max_log() {
        int max = 0;
        try {
            ConnectionUDB conn = new ConnectionUDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(cod_usuario) maxp from usuario_log";
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

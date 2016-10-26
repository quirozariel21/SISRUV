/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denunciadao;

import controller.UsuarioController;import dao.LogDao;
import dao.LogDaoImpl;
/*****REvisar esta parte de modulo denuncias*****/
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import denunciamodel.DDenuncia;
import denunciamodel.DInstitucion;
import model.Log;
import model.Usuario;
import util.ConnectionDB;
import util.ConnectionUDB;

/**
 *
 * @author KRETCO
 */
public class DDenunciaDaoImpl implements DDenunciaDao {

    private Usuario usuario = new Usuario();
    private LogDao logDao = new LogDaoImpl();

    @Override
    public List<DDenuncia> findAll() {
        List<DDenuncia> listadenuncias = new ArrayList<>();
        List<DInstitucion> listaInstituciones = new ArrayList<>();
        try {
            String query = "select d.*  from denuncia d order by d.* desc";
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                DDenuncia denuncia = new DDenuncia();

                denuncia.setId_denuncia(resultSet.getInt("id_denuncia"));
                denuncia.setFecha(resultSet.getDate("fecha"));
                denuncia.setNombre_victima(resultSet.getString("nombre_victima"));
                denuncia.setPaterno_victima(resultSet.getString("paterno_victima"));
                denuncia.setMaterno_victima(resultSet.getString("materno_victima"));
                denuncia.setCi_victima(resultSet.getInt("ci_victima"));
                denuncia.setNombre_agresor(resultSet.getString("nombre_agresor"));
                denuncia.setPaterno_agresor(resultSet.getString("paterno_agresor"));
                denuncia.setMaterno_agresor(resultSet.getString("materno_agresor"));
                denuncia.setCi_agresor(resultSet.getInt("ci_agresor"));
                denuncia.setTviolencia(resultSet.getString("tviolencia"));
                denuncia.setTestimonio(resultSet.getString("testimonio"));
                denuncia.setResolucion(resultSet.getString("resolucion"));
                denuncia.setCod_usuario(resultSet.getInt("cod_usuario"));
                denuncia.setSexo_victima(resultSet.getString("sexo_victima"));
                denuncia.setEdad_victima(resultSet.getInt("edad_victima"));
                denuncia.setSexo_agresor(resultSet.getString("sexo_agresor"));
                denuncia.setEdad_agresor(resultSet.getInt("edad_agresor"));
                denuncia.setNumero_victima(resultSet.getInt("numero_victima"));
                denuncia.setNumero_agresor(resultSet.getInt("numero_agresor"));
                denuncia.setCargo_victima(resultSet.getString("cargo_victima"));
                denuncia.setCargo_agresor(resultSet.getString("cargo_agresor"));
                listadenuncias.add(denuncia);
                connection.close();
            }
        } catch (Exception e) {
        }
        return listadenuncias;
    }

    @Override
    public int insert(int num, DDenuncia denuncia) {
        int aux = 0;
        DInstitucionDao institucionDao = new DInstitucionDaoImpl();
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        DDenuncia denuncia1 = new DDenuncia();
        denuncia1 = denuncia;
        try {
            if (num == 1) {
                UsuarioController obj1 = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                CallableStatement d = connection.prepareCall("select sp_operaciones_denuncia(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("dddddddddd : " + d);
                d.setInt(1, num);
                d.setInt(2, max() + 1);
                d.setDate(3, new java.sql.Date(denuncia.getFecha().getTime()));
                d.setString(4, denuncia.getNombre_victima());
                d.setString(5, denuncia.getPaterno_victima());
                d.setString(6, denuncia.getMaterno_victima());
                d.setInt(7, denuncia.getCi_victima());
                d.setString(8, denuncia.getNombre_agresor());
                d.setString(9, denuncia.getPaterno_agresor());
                d.setString(10, denuncia.getMaterno_agresor());
                d.setInt(11, denuncia.getCi_agresor());
                d.setString(12, denuncia.getTviolencia());
                d.setString(13, denuncia.getTestimonio());
                d.setString(14, denuncia.getResolucion());
                d.setInt(15, obj1.getUsuario().getCod_usuario());
                d.setString(16, denuncia.getSexo_victima());
                d.setInt(17, denuncia.getEdad_victima());
                d.setString(18, denuncia.getSexo_agresor());
                d.setInt(19, denuncia.getEdad_agresor());
                d.setInt(20, denuncia.getNumero_victima());
                d.setInt(21, denuncia.getNumero_agresor());
                d.setString(22, denuncia.getCargo_victima());
                d.setString(23, denuncia.getCargo_agresor());

                ResultSet resultSet = d.executeQuery();
                aux = max();
                System.out.println("max1x : " + max());
                if (aux != 0) {

                    UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                    int respuesta = 0;
                    System.out.println("ipAddress:" + ipAddress);
                    System.out.println("codigo USUARIO : " + obj.getUsuario().getCod_usuario());
                    Log log = new Log(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "denuncia");
                    //logDao.insert(1, log);
                    respuesta = logDao.insert(1, log);
                    System.out.println("xxxxxxxxxxxxxxx : " + respuesta);
                    if (respuesta != 0) {
                        int sss = insert_log(denuncia1, respuesta);
                        System.out.println("INSERT LOG :" + sss);
                        System.out.println("ENTRA_4   : ");
                    }

                }

            } else {
                CallableStatement d = connection.prepareCall("select sp_operaciones_denuncia(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                d.setInt(1, num);
                d.setInt(2, denuncia.getId_denuncia());
                d.setDate(3, new java.sql.Date(denuncia.getFecha().getTime()));
                d.setString(4, denuncia.getNombre_victima());
                d.setString(5, denuncia.getPaterno_victima());
                d.setString(6, denuncia.getMaterno_victima());
                d.setInt(7, denuncia.getCi_victima());
                d.setString(8, denuncia.getNombre_agresor());
                d.setString(9, denuncia.getPaterno_agresor());
                d.setString(10, denuncia.getMaterno_agresor());
                d.setInt(11, denuncia.getCi_agresor());
                d.setString(12, denuncia.getTviolencia());
                d.setString(13, denuncia.getTestimonio());
                d.setString(14, denuncia.getResolucion());
                d.setInt(15, denuncia.getCod_usuario());
                d.setString(16, denuncia.getSexo_victima());
                d.setInt(17, denuncia.getEdad_victima());
                d.setString(18, denuncia.getSexo_agresor());
                d.setInt(19, denuncia.getEdad_agresor());
                d.setInt(20, denuncia.getNumero_victima());
                d.setInt(21, denuncia.getNumero_agresor());
                d.setString(22, denuncia.getCargo_victima());
                d.setString(23, denuncia.getCargo_agresor());
                aux = denuncia.getId_denuncia();
                ResultSet resultSet = d.executeQuery();
                if (aux != 0) {

                    UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                    int respuesta = 0;
                    System.out.println("ipAddress:" + ipAddress);
                    System.out.println("codigo USUARIO : " + obj.getUsuario().getCod_usuario());
                    Log log = new Log(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "denuncia");
                    //logDao.insert(1, log);
                    respuesta = logDao.insert(1, log);
                    System.out.println("xxxxxxxxxxxxxxx : " + respuesta);
                    if (respuesta != 0) {
                        int sss = insert_log(denuncia1, respuesta);
                        System.out.println("INSERT LOG :" + sss);
                        System.out.println("ENTRA_4   : ");
                    }
                }
            }
            denuncia = new DDenuncia();
        } catch (SQLException ex) {
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
                    Log log = new Log(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "denuncia");
                    //logDao.insert(1, log);
                    respuesta = logDao.insert(2, log);
                    
            } catch (Exception e) {
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
                    Log log = new Log(obj.getUsuario().getCod_usuario(), ipAddress, "Insertar : ", max() + 1, "denuncia");
                    //logDao.insert(1, log);
                    respuesta = logDao.insert(2, log);
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
            String sql = "select max(id_denuncia) maxp from denuncia";
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int find_inst() {
        int maxp = 0;
        int max = 0;
        UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");

        try {
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
            Statement statement = connection.createStatement();
            String sql = "	select usuario_institucion.institucion_id_institucion as institucion_id_institucion1 from dblink('host=" + servidorU + " user= " + usuarioU + " password= " + passwordU + " dbname=" + baseDatoU + "', "
                    + "'SELECT id_usuario_institucion, \n"
                    + "   usuario_cod_usuario, institucion_id_institucion FROM usuario_institucion')\n"
                    + "	as usuario_institucion(id_usuario_institucion integer, usuario_cod_usuario integer, institucion_id_institucion integer) where usuario_cod_usuario = " + obj.getUsuario().getCod_usuario();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                max = resultSet.getInt("institucion_id_institucion1");
                System.out.println("MAX : " + max);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        System.out.println("maxxxxxxxxxxxxx : " + max);
        return max;

    }

    @Override
    public int insert_log(DDenuncia denuncia, int id_log) {
        
        int aux = 0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            UsuarioController obj1 = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
            CallableStatement d = connection.prepareCall("select sp_operaciones_denuncia_log(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            System.out.println("dddddddddd : " + d);
            System.out.println("dddddddddd : " + max_log()+1);
            System.out.println("xxx : " + id_log);
            d.setInt(1, 1);
            d.setInt(2, max_log()+ 1);
            d.setDate(3, new java.sql.Date(denuncia.getFecha().getTime()));
            d.setString(4, denuncia.getNombre_victima());
            d.setString(5, denuncia.getPaterno_victima());
            d.setString(6, denuncia.getMaterno_victima());
            d.setInt(7, denuncia.getCi_victima());
            d.setString(8, denuncia.getNombre_agresor());
            d.setString(9, denuncia.getPaterno_agresor());
            d.setString(10, denuncia.getMaterno_agresor());
            d.setInt(11, denuncia.getCi_agresor());
            d.setString(12, denuncia.getTviolencia());
            d.setString(13, denuncia.getTestimonio());
            d.setString(14, denuncia.getResolucion());
            d.setInt(15, obj1.getUsuario().getCod_usuario());
            d.setString(16, denuncia.getSexo_victima());
            d.setInt(17, denuncia.getEdad_victima());
            d.setString(18, denuncia.getSexo_agresor());
            d.setInt(19, denuncia.getEdad_agresor());
            d.setInt(20, denuncia.getNumero_victima());
            d.setInt(21, denuncia.getNumero_agresor());
            d.setString(22, denuncia.getCargo_victima());
            d.setString(23, denuncia.getCargo_agresor());
            d.setInt(24, id_log);
            aux = max_log() + 1;
            ResultSet resultSet = d.executeQuery();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }

    @Override
    public int max_log() {
        int maxp = 0;
        int max = 0;
        try {
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            String sql = "select max(id_denuncia) maxp from denuncia_log";
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

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
import indicadoresmodel.IDenuncia;
import indicadoresmodel.IInstitucion;
import util.ConnectionDB;

/**
 *
 * @author KRETCO
 */
public class IDenunciaDaoImpl implements IDenunciaDao{

    @Override
    public List<IDenuncia> findAll() {
        List<IDenuncia> listadenuncias = new ArrayList<>();
        List<IInstitucion> listaInstituciones = new ArrayList<>();
        try {
            String query = "select * from denuncia";
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                IDenuncia denuncia = new IDenuncia();
                
                denuncia.setId_denuncia(resultSet.getInt("id_denuncia"));
                denuncia.setFecha(resultSet.getDate("fecha"));
                denuncia.setNombre_victima(resultSet.getString("nombre_victima"));
                denuncia.setPaterno_victima(resultSet.getString("paterno_victima"));
                denuncia.setMaterno_victima(resultSet.getString("materno_victima"));
                denuncia.setCi_victima(resultSet.getString("ci_victima"));
                denuncia.setNombre_agresor(resultSet.getString("nombre_agresor"));
                denuncia.setPaterno_agresor(resultSet.getString("paterno_agresor"));
                denuncia.setMaterno_agresor(resultSet.getString("materno_agresor"));
                denuncia.setCi_agresor(resultSet.getString("ci_agresor"));
                denuncia.setTv_fisica(resultSet.getInt("tv_fisica"));
                denuncia.setTv_psicologica(resultSet.getInt("tv_psicologica"));
                denuncia.setTv_sexual(resultSet.getInt("tv_sexual"));
                denuncia.setTv_economica(resultSet.getInt("tv_economica"));
                denuncia.setTestimonio(resultSet.getString("testimonio"));
                denuncia.setResolucion(resultSet.getString("resolucion"));
                denuncia.setId_institucion(resultSet.getInt("id_institucion"));
                denuncia.setSexo_victima(resultSet.getString("sexo_victima"));
                denuncia.setEdad_victima(resultSet.getInt("edad_victima"));
                denuncia.setSexo_agresor(resultSet.getString("sexo_agresor"));
                denuncia.setEdad_agresor(resultSet.getInt("edad_agresor"));
                denuncia.setNumero_victima(resultSet.getInt("numero_victima"));
                denuncia.setNumero_agresor(resultSet.getInt("numero_agresor"));
                denuncia.setCargo_victima(resultSet.getString("cargo_victima"));
                denuncia.setCargo_agresor(resultSet.getString("cargo_agresor"));
                listadenuncias.add(denuncia);
            }
        } catch (Exception e) {
        }
        return listadenuncias;
    }

    @Override
    public int insert(int num, IDenuncia denuncia) {
        int aux = 0;
        IInstitucionDao institucionDao = new IInstitucionDaoImpl();
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try {
            if (num == 1) {
                CallableStatement d = connection.prepareCall("select sp_operaciones_denuncia(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("dddddddddd : " + d);
                d.setInt(1, num);
                d.setInt(2, max() + 1);
                d.setDate(3, new java.sql.Date(denuncia.getFecha().getTime()));
                d.setString(4, denuncia.getNombre_victima());
                d.setString(5, denuncia.getPaterno_victima());
                d.setString(6, denuncia.getMaterno_victima());
                d.setString(7, denuncia.getCi_victima());
                d.setString(8, denuncia.getNombre_agresor());
                d.setString(9, denuncia.getPaterno_agresor());
                d.setString(10, denuncia.getMaterno_agresor());
                d.setString(11, denuncia.getCi_agresor());
                d.setInt(12, denuncia.getTv_fisica());
                d.setInt(13, denuncia.getTv_psicologica());
                d.setInt(14, denuncia.getTv_sexual());
                d.setInt(15, denuncia.getTv_economica());
                d.setString(16, denuncia.getTestimonio());
                d.setString(17, denuncia.getResolucion());
                d.setInt(18, institucionDao.max());
                d.setString(19, denuncia.getSexo_victima());
                d.setInt(20, denuncia.getEdad_victima());
                d.setString(21, denuncia.getSexo_agresor());
                d.setInt(22, denuncia.getEdad_agresor());
                d.setInt(23, denuncia.getNumero_victima());
                d.setInt(24, denuncia.getNumero_agresor());
                d.setString(25, denuncia.getCargo_victima());
                d.setString(26, denuncia.getCargo_agresor());
                
                ResultSet resultSet = d.executeQuery();
                connection.close();
                aux=1;
            }else{
                CallableStatement d = connection.prepareCall("select sp_operaciones_denuncia(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                d.setInt(1, num);
                d.setInt(2, denuncia.getId_denuncia());
                d.setDate(3, new java.sql.Date(denuncia.getFecha().getTime()));
                d.setString(4, denuncia.getNombre_victima());
                d.setString(5, denuncia.getPaterno_victima());
                d.setString(6, denuncia.getMaterno_victima());
                d.setString(7, denuncia.getCi_victima());
                d.setString(8, denuncia.getNombre_agresor());
                d.setString(9, denuncia.getPaterno_agresor());
                d.setString(10, denuncia.getMaterno_agresor());
                d.setString(11, denuncia.getCi_agresor());
                d.setInt(12, denuncia.getTv_fisica());
                d.setInt(13, denuncia.getTv_psicologica());
                d.setInt(14, denuncia.getTv_sexual());
                d.setInt(15, denuncia.getTv_economica());
                d.setString(16, denuncia.getTestimonio());
                d.setString(17, denuncia.getResolucion());
                d.setInt(18, denuncia.getId_institucion());
                d.setString(19, denuncia.getSexo_victima());
                d.setInt(20, denuncia.getEdad_victima());
                d.setString(21, denuncia.getSexo_agresor());
                d.setInt(22, denuncia.getEdad_agresor());
                d.setInt(23, denuncia.getNumero_victima());
                d.setInt(24, denuncia.getNumero_agresor());
                d.setString(25, denuncia.getCargo_victima());
                d.setString(26, denuncia.getCargo_agresor());
                
                ResultSet resultSet = d.executeQuery();
                connection.close();
                aux=1;
            }
        } catch (Exception e) {
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
    
}

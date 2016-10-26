/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import model.T_datos;
import model.View_violenceReport;
import util.ConnectionDB;
import util.ConnectionUDB;


/**
 *
 * @author KRETCO
 */
public class View_reportDaoImpl implements View_reportDao {
    
//    @Override
//    public String find_parameters() {
//        String resp = "";
//         try {
//            String query = "select valor from parametros where parametro = 'dbusuario_ip' or parametro = 'dbusuario_usuario' or parametro = 'dbusuario_password'";
//            System.out.println("entra : " + query);
//            ConnectionUDB conn = new ConnectionUDB();
//            Connection connection = conn.getConnectionDB();
//            Statement statement = connection.createStatement();
//            //System.out.println("query:" + query);
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                System.out.println("ENTRAAAAAAAAA : " + resultSet.getString("valor"));
//                resp += resultSet.getString("valor") + ",";
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error :" + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error :" + e.getMessage());
//        }
//         System.out.println("PARAMETROSSSSSS : " + resp);
//        return resp;
//    }
    
    @Override
    public List<View_violenceReport> reportAge(Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> list = new ArrayList<>();

        String servidorU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("servidorU");
        String baseDatoU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("baseDatoU");
        String usuarioU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("usuarioU");
        String passwordU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("passwordU");
        
        try {
            String query = "SELECT rang\n" +
                "	,SUM(CASE WHEN sexo = 'H' THEN 1 ELSE 0 END ) AS \"h\"\n" +
                "	,SUM(CASE WHEN sexo = 'M' THEN 1 ELSE 0 END ) AS \"m\"\n" +
                "	,COUNT(*) AS tot\n" +
                "   FROM ( SELECT edad as ed,\n" +
                "                CASE\n" +
                "		    WHEN edad >= 1 AND edad <= 15 THEN 'menor 15'\n" +
                "                    WHEN edad >= 15 AND edad <= 19 THEN '15 a 19'\n" +
                "                    WHEN edad >= 20 AND edad <= 24 THEN '20 a 24'\n" +
                "                    WHEN edad >= 25 AND edad <= 29 THEN '25 a 29'\n" +
                "                    WHEN edad >= 30 AND edad <= 34 THEN '30 a 34'\n" +
                "                    WHEN edad >= 35 AND edad <= 39 THEN '35 a 39'\n" +
                "                    WHEN edad >= 40 AND edad <= 44 THEN '40 a 44'\n" +
                "                    WHEN edad >= 45 AND edad <= 49 THEN '45 a 49'\n" +
                "                    WHEN edad >= 50 AND edad <= 54 THEN '50 a 54'\n" +
                "                    WHEN edad >= 55 AND edad <= 59 THEN '55 a 59'\n" +
                "                    WHEN edad >= 60 AND edad <= 64 THEN '60 a 64'\n" +
                "                    WHEN edad > 64 THEN 'mayor a 64'\n" +
                "                    ELSE 'other'\n" +
                "                END AS rang,\n" +
                "                fecha_reg,\n" +
                "            sexo\n" +
                "           FROM ( SELECT b.id_pervar,\n" +
                "    b.edad,\n" +
                "    b.est_civil,\n" +
                "    b.nro_hijos,\n" +
                "    b.gestacion_h,\n" +
                "    b.num_miembros_fam,\n" +
                "    b.municipio,\n" +
                "    b.esp_municipio,\n" +
                "    b.area,\n" +
                "    b.esp_area,\n" +
                "    b.vivienda,\n" +
                "    b.nivel_inst,\n" +
                "    b.condicion_act,\n" +
                "    b.ocupacion,\n" +
                "    b.esp_ocupacion,\n" +
                "    b.cargo,\n" +
                "    b.ingre_economico,\n" +
                "    b.monto_aprox_bs,\n" +
                "    b.aporte_familiar_bs,\n" +
                "    b.idiomas,\n" +
                "    b.esp_idioma,\n" +
                "    b.etnia,\n" +
                "    b.esp_etnia,\n" +
                "    b.lgbti,\n" +
                "    b.pers_discapacidad,\n" +
                "    a.id_persona,\n" +
                "    a.nombre,\n" +
                "    a.paterno,\n" +
                "    a.materno,\n" +
                "    a.ap_casada,\n" +
                "    a.numero,\n" +
                "    a.codigo_documento,\n" +
                "    a.sexo,\n" +
                "    a.lugar_nac,\n" +
                "    a.esp_lugar_nac,\n" +
                "    a.fecha_nac,\n" +
                "    f.cod_usuario,\n" +
                "    f.username,\n" +
                "    f.password,\n" +
                "    f.estado,\n" +
                "    f.ci,\n" +
                "    f.fecha_reg,\n" +
                "    f.departamento,\n" +
                "    f.municipio AS municipio_usu,\n" +
                "    f.cod_servicio,\n" +
                "    f.cod_sub_servicio,\n" +
                "    f.codigo_exp,\n" +
                "    f.e_mail,\n" +
                "    f.telefono,\n" +
                "    f.celular,\n" +
                "    f.direccion,\n" +
                "    f.fecha_estado,\n" +
                "    d.id_vicagre,\n" +
                "    d.testimonio,\n" +
                "    d.lugar_agresion,\n" +
                "    d.parentesco,\n" +
                "    d.esp_parentesco,\n" +
                "    d.tviolencia,\n" +
                "    d.denuncio,\n" +
                "    d.donde_denuncio,\n" +
                "    d.frec_agresiones,\n" +
                "    d.agre_consume_alcohol,\n" +
                "    d.frec_consumo_alcohol,\n" +
                "    d.agre_consume_drogas,\n" +
                "    d.frec_consumo_drogas\n" +
                "   FROM persona a\n" +
                "     JOIN persona_variables b ON a.id_persona = b.id_persona\n" +
                "     JOIN vicagre_persona c ON c.persona_id_persona_agresor = a.id_persona OR c.persona_id_persona_victima = a.id_persona\n" +
                "     JOIN victima_agresor d ON d.id_vicagre = c.victima_agresor_id_vicagre\n" +
                "     JOIN usuario_vicagre e ON e.victima_agresor_id_vicagre = d.id_vicagre\n" +
                "JOIN (\n" +
                "     select usuario.* from\n" +
                "    dblink('host="+servidorU+"\n" +
                "    user="+usuarioU+"\n" +
                "    password="+passwordU+"\n" +
                "    dbname="+baseDatoU+"', 'SELECT cod_usuario, username, password, estado, nombre, paterno, materno, ci, fecha_reg, departamento, municipio, cod_servicio, cod_sub_servicio,\n" +
                "    codigo_exp, e_mail, telefono, celular, direccion, fecha_estado  FROM usuario')\n" +
                "    AS usuario(cod_usuario integer, username varchar, password varchar, estado boolean, nombre varchar, paterno varchar, materno varchar, ci integer, fecha_reg date, \n" +
                "    departamento integer, municipio integer, cod_servicio integer, cod_sub_servicio integer, codigo_exp integer, e_mail varchar, telefono integer, celular integer,\n" +
                "    direccion varchar, fecha_estado date)\n" +
                "    ) f ON f.cod_usuario = e.usuario_cod_usuario) as view_ruv) resp\n" +
                "           where fecha_reg>='" + fecha_ini + "' and fecha_reg<='" + fecha_fin + "'\n" +
                "           --where fecha_reg>=fecha_ini and fecha_reg<=fecha_fin\n" +
                "           --and rang <> 'other' \n" +
                "\n" +
                "  GROUP BY rang\n" +
                "  ORDER BY rang asc;";
            System.out.println("entra : " + query);
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                view_report.setDescripcion(resultSet.getString("rang"));
                view_report.setHombre(resultSet.getBigDecimal("h"));
                view_report.setMujer(resultSet.getBigDecimal("m"));
                view_report.setTota(resultSet.getBigDecimal("tot"));
                list.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<View_violenceReport> reportBeen(Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> list = new ArrayList<>();
        String servidorU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("servidorU");
        String baseDatoU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("baseDatoU");
        String usuarioU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("usuarioU");
        String passwordU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("passwordU");
        try {
            String query = "	select descripcionb, h.* from view_descripciondatos_tdatos z\n" +
                    "	left join (\n" +
                    "	      select t.est_civil as est\n" +
                    "		,SUM(CASE WHEN sexo = 'H' THEN 1 ELSE 0 END ) AS \"h\"\n" +
                    "		,SUM(CASE WHEN sexo = 'M' THEN 1 ELSE 0 END ) AS \"m\"\n" +
                    "		,COUNT(*) AS tot\n" +
                    "		from ( SELECT b.id_pervar,\n" +
                    "    b.edad,\n" +
                    "    b.est_civil,\n" +
                    "    b.nro_hijos,\n" +
                    "    b.gestacion_h,\n" +
                    "    b.num_miembros_fam,\n" +
                    "    b.municipio,\n" +
                    "    b.esp_municipio,\n" +
                    "    b.area,\n" +
                    "    b.esp_area,\n" +
                    "    b.vivienda,\n" +
                    "    b.nivel_inst,\n" +
                    "    b.condicion_act,\n" +
                    "    b.ocupacion,\n" +
                    "    b.esp_ocupacion,\n" +
                    "    b.cargo,\n" +
                    "    b.ingre_economico,\n" +
                    "    b.monto_aprox_bs,\n" +
                    "    b.aporte_familiar_bs,\n" +
                    "    b.idiomas,\n" +
                    "    b.esp_idioma,\n" +
                    "    b.etnia,\n" +
                    "    b.esp_etnia,\n" +
                    "    b.lgbti,\n" +
                    "    b.pers_discapacidad,\n" +
                    "    a.id_persona,\n" +
                    "    a.nombre,\n" +
                    "    a.paterno,\n" +
                    "    a.materno,\n" +
                    "    a.ap_casada,\n" +
                    "    a.numero,\n" +
                    "    a.codigo_documento,\n" +
                    "    a.sexo,\n" +
                    "    a.lugar_nac,\n" +
                    "    a.esp_lugar_nac,\n" +
                    "    a.fecha_nac,\n" +
                    "    f.cod_usuario,\n" +
                    "    f.username,\n" +
                    "    f.password,\n" +
                    "    f.estado,\n" +
                    "    f.ci,\n" +
                    "    f.fecha_reg,\n" +
                    "    f.departamento,\n" +
                    "    f.municipio AS municipio_usu,\n" +
                    "    f.cod_servicio,\n" +
                    "    f.cod_sub_servicio,\n" +
                    "    f.codigo_exp,\n" +
                    "    f.e_mail,\n" +
                    "    f.telefono,\n" +
                    "    f.celular,\n" +
                    "    f.direccion,\n" +
                    "    f.fecha_estado,\n" +
                    "    d.id_vicagre,\n" +
                    "    d.testimonio,\n" +
                    "    d.lugar_agresion,\n" +
                    "    d.parentesco,\n" +
                    "    d.esp_parentesco,\n" +
                    "    d.tviolencia,\n" +
                    "    d.denuncio,\n" +
                    "    d.donde_denuncio,\n" +
                    "    d.frec_agresiones,\n" +
                    "    d.agre_consume_alcohol,\n" +
                    "    d.frec_consumo_alcohol,\n" +
                    "    d.agre_consume_drogas,\n" +
                    "    d.frec_consumo_drogas\n" +
                    "   FROM persona a\n" +
                    "     JOIN persona_variables b ON a.id_persona = b.id_persona\n" +
                    "     JOIN vicagre_persona c ON c.persona_id_persona_agresor = a.id_persona OR c.persona_id_persona_victima = a.id_persona\n" +
                    "     JOIN victima_agresor d ON d.id_vicagre = c.victima_agresor_id_vicagre\n" +
                    "     JOIN usuario_vicagre e ON e.victima_agresor_id_vicagre = d.id_vicagre\n" +
                    "JOIN (\n" +
                    "     select usuario.* from\n" +
                    "     dblink('host="+servidorU+"\n" +
                    "    user="+usuarioU+"\n" +
                    "    password="+passwordU+"\n" +
                    "    dbname="+baseDatoU+"', 'SELECT cod_usuario, username, password, estado, nombre, paterno, materno, ci, fecha_reg, departamento, municipio, cod_servicio, cod_sub_servicio,\n" +
                    "    codigo_exp, e_mail, telefono, celular, direccion, fecha_estado  FROM usuario')\n" +
                    "    AS usuario(cod_usuario integer, username varchar, password varchar, estado boolean, nombre varchar, paterno varchar, materno varchar, ci integer, fecha_reg date, \n" +
                    "    departamento integer, municipio integer, cod_servicio integer, cod_sub_servicio integer, codigo_exp integer, e_mail varchar, telefono integer, celular integer,\n" +
                    "    direccion varchar, fecha_estado date)\n" +
                    "    ) f ON f.cod_usuario = e.usuario_cod_usuario) t\n" +
                    "		where fecha_reg>='" + fecha_ini + "' and fecha_reg<='" + fecha_fin + "'\n" +
                    "		GROUP  BY est) as h on z.id_tdatos = h.est\n" +
                    "\n" +
                    "	where z.descripciona = 'estado_civil';";
            System.out.println("entra : " + query);
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            //System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                view_report.setDescripcion(resultSet.getString("descripcionb"));
                view_report.setHombre(resultSet.getBigDecimal("h"));
                view_report.setMujer(resultSet.getBigDecimal("m"));
                view_report.setTota(resultSet.getBigDecimal("tot"));
                list.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<View_violenceReport> reportDepartment(Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> list = new ArrayList<>();
        String servidorU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("servidorU");
        String baseDatoU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("baseDatoU");
        String usuarioU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("usuarioU");
        String passwordU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("passwordU");
        try {
            String query = " select descripcionb, h.* from view_descripciondatos_tdatos z\n" +
                    "   left join( \n" +
                    "        select t.departamento as est\n" +
                    "	,SUM(CASE WHEN sexo = 'H' THEN 1 ELSE 0 END ) AS \"h\"\n" +
                    "	,SUM(CASE WHEN sexo = 'M' THEN 1 ELSE 0 END ) AS \"m\"\n" +
                    "	,COUNT(*) AS tot\n" +
                    "	from ( SELECT b.id_pervar,\n" +
                    "    b.edad,\n" +
                    "    b.est_civil,\n" +
                    "    b.nro_hijos,\n" +
                    "    b.gestacion_h,\n" +
                    "    b.num_miembros_fam,\n" +
                    "    b.municipio,\n" +
                    "    b.esp_municipio,\n" +
                    "    b.area,\n" +
                    "    b.esp_area,\n" +
                    "    b.vivienda,\n" +
                    "    b.nivel_inst,\n" +
                    "    b.condicion_act,\n" +
                    "    b.ocupacion,\n" +
                    "    b.esp_ocupacion,\n" +
                    "    b.cargo,\n" +
                    "    b.ingre_economico,\n" +
                    "    b.monto_aprox_bs,\n" +
                    "    b.aporte_familiar_bs,\n" +
                    "    b.idiomas,\n" +
                    "    b.esp_idioma,\n" +
                    "    b.etnia,\n" +
                    "    b.esp_etnia,\n" +
                    "    b.lgbti,\n" +
                    "    b.pers_discapacidad,\n" +
                    "    a.id_persona,\n" +
                    "    a.nombre,\n" +
                    "    a.paterno,\n" +
                    "    a.materno,\n" +
                    "    a.ap_casada,\n" +
                    "    a.numero,\n" +
                    "    a.codigo_documento,\n" +
                    "    a.sexo,\n" +
                    "    a.lugar_nac,\n" +
                    "    a.esp_lugar_nac,\n" +
                    "    a.fecha_nac,\n" +
                    "    f.cod_usuario,\n" +
                    "    f.username,\n" +
                    "    f.password,\n" +
                    "    f.estado,\n" +
                    "    f.ci,\n" +
                    "    f.fecha_reg,\n" +
                    "    f.departamento,\n" +
                    "    f.municipio AS municipio_usu,\n" +
                    "    f.cod_servicio,\n" +
                    "    f.cod_sub_servicio,\n" +
                    "    f.codigo_exp,\n" +
                    "    f.e_mail,\n" +
                    "    f.telefono,\n" +
                    "    f.celular,\n" +
                    "    f.direccion,\n" +
                    "    f.fecha_estado,\n" +
                    "    d.id_vicagre,\n" +
                    "    d.testimonio,\n" +
                    "    d.lugar_agresion,\n" +
                    "    d.parentesco,\n" +
                    "    d.esp_parentesco,\n" +
                    "    d.tviolencia,\n" +
                    "    d.denuncio,\n" +
                    "    d.donde_denuncio,\n" +
                    "    d.frec_agresiones,\n" +
                    "    d.agre_consume_alcohol,\n" +
                    "    d.frec_consumo_alcohol,\n" +
                    "    d.agre_consume_drogas,\n" +
                    "    d.frec_consumo_drogas\n" +
                    "   FROM persona a\n" +
                    "     JOIN persona_variables b ON a.id_persona = b.id_persona\n" +
                    "     JOIN vicagre_persona c ON c.persona_id_persona_agresor = a.id_persona OR c.persona_id_persona_victima = a.id_persona\n" +
                    "     JOIN victima_agresor d ON d.id_vicagre = c.victima_agresor_id_vicagre\n" +
                    "     JOIN usuario_vicagre e ON e.victima_agresor_id_vicagre = d.id_vicagre\n" +
                    "JOIN (\n" +
                    "     select usuario.* from\n" +
                    "    dblink('host="+servidorU+"\n" +
                "    user="+usuarioU+"\n" +
                "    password="+passwordU+"\n" +
                "    dbname="+baseDatoU+"', 'SELECT cod_usuario, username, password, estado, nombre, paterno, materno, ci, fecha_reg, departamento, municipio, cod_servicio, cod_sub_servicio,\n" +
                    "    codigo_exp, e_mail, telefono, celular, direccion, fecha_estado  FROM usuario')\n" +
                    "    AS usuario(cod_usuario integer, username varchar, password varchar, estado boolean, nombre varchar, paterno varchar, materno varchar, ci integer, fecha_reg date, \n" +
                    "    departamento integer, municipio integer, cod_servicio integer, cod_sub_servicio integer, codigo_exp integer, e_mail varchar, telefono integer, celular integer,\n" +
                    "    direccion varchar, fecha_estado date)\n" +
                    "    ) f ON f.cod_usuario = e.usuario_cod_usuario) t\n" +
                    "	where fecha_reg>='" + fecha_ini + "' and fecha_reg<= '" + fecha_fin + "'\n" +
                    "	GROUP  BY est) as h on z.id_tdatos = h.est\n" +
                    "\n" +
                    "	where z.descripciona = 'departamento';";
            System.out.println("entra : " + query);
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                view_report.setDescripcion(resultSet.getString("descripcionb"));
                view_report.setHombre(resultSet.getBigDecimal("h"));
                view_report.setMujer(resultSet.getBigDecimal("m"));
                view_report.setTota(resultSet.getBigDecimal("tot"));
                list.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<View_violenceReport> reportviolence(Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> list = new ArrayList<>();
        String servidorU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("servidorU");
        String baseDatoU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("baseDatoU");
        String usuarioU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("usuarioU");
        String passwordU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("passwordU");
        try {
            String query = "select t.tviolencia as est\n" +
"		,SUM(CASE WHEN sexo = 'H' THEN 1 ELSE 0 END ) AS \"h\"\n" +
"		,SUM(CASE WHEN sexo = 'M' THEN 1 ELSE 0 END ) AS \"m\"\n" +
"		,COUNT(*) AS tot\n" +
"		from (SELECT b.id_pervar,\n" +
                    "    b.edad,\n" +
                    "    b.est_civil,\n" +
                    "    b.nro_hijos,\n" +
                    "    b.gestacion_h,\n" +
                    "    b.num_miembros_fam,\n" +
                    "    b.municipio,\n" +
                    "    b.esp_municipio,\n" +
                    "    b.area,\n" +
                    "    b.esp_area,\n" +
                    "    b.vivienda,\n" +
                    "    b.nivel_inst,\n" +
                    "    b.condicion_act,\n" +
                    "    b.ocupacion,\n" +
                    "    b.esp_ocupacion,\n" +
                    "    b.cargo,\n" +
                    "    b.ingre_economico,\n" +
                    "    b.monto_aprox_bs,\n" +
                    "    b.aporte_familiar_bs,\n" +
                    "    b.idiomas,\n" +
                    "    b.esp_idioma,\n" +
                    "    b.etnia,\n" +
                    "    b.esp_etnia,\n" +
                    "    b.lgbti,\n" +
                    "    b.pers_discapacidad,\n" +
                    "    a.id_persona,\n" +
                    "    a.nombre,\n" +
                    "    a.paterno,\n" +
                    "    a.materno,\n" +
                    "    a.ap_casada,\n" +
                    "    a.numero,\n" +
                    "    a.codigo_documento,\n" +
                    "    a.sexo,\n" +
                    "    a.lugar_nac,\n" +
                    "    a.esp_lugar_nac,\n" +
                    "    a.fecha_nac,\n" +
                    "    f.cod_usuario,\n" +
                    "    f.username,\n" +
                    "    f.password,\n" +
                    "    f.estado,\n" +
                    "    f.ci,\n" +
                    "    f.fecha_reg,\n" +
                    "    f.departamento,\n" +
                    "    f.municipio AS municipio_usu,\n" +
                    "    f.cod_servicio,\n" +
                    "    f.cod_sub_servicio,\n" +
                    "    f.codigo_exp,\n" +
                    "    f.e_mail,\n" +
                    "    f.telefono,\n" +
                    "    f.celular,\n" +
                    "    f.direccion,\n" +
                    "    f.fecha_estado,\n" +
                    "    d.id_vicagre,\n" +
                    "    d.testimonio,\n" +
                    "    d.lugar_agresion,\n" +
                    "    d.parentesco,\n" +
                    "    d.esp_parentesco,\n" +
                    "    d.tviolencia,\n" +
                    "    d.denuncio,\n" +
                    "    d.donde_denuncio,\n" +
                    "    d.frec_agresiones,\n" +
                    "    d.agre_consume_alcohol,\n" +
                    "    d.frec_consumo_alcohol,\n" +
                    "    d.agre_consume_drogas,\n" +
                    "    d.frec_consumo_drogas\n" +
                    "   FROM persona a\n" +
                    "     JOIN persona_variables b ON a.id_persona = b.id_persona\n" +
                    "     JOIN vicagre_persona c ON c.persona_id_persona_agresor = a.id_persona OR c.persona_id_persona_victima = a.id_persona\n" +
                    "     JOIN victima_agresor d ON d.id_vicagre = c.victima_agresor_id_vicagre\n" +
                    "     JOIN usuario_vicagre e ON e.victima_agresor_id_vicagre = d.id_vicagre\n" +
                    "JOIN (\n" +
                    "     select usuario.* from\n" +
                    "    dblink('host="+servidorU+"\n" +
                "    user="+usuarioU+"\n" +
                "    password="+passwordU+"\n" +
                "    dbname="+baseDatoU+"', 'SELECT cod_usuario, username, password, estado, nombre, paterno, materno, ci, fecha_reg, departamento, municipio, cod_servicio, cod_sub_servicio,\n" +
                    "    codigo_exp, e_mail, telefono, celular, direccion, fecha_estado  FROM usuario')\n" +
                    "    AS usuario(cod_usuario integer, username varchar, password varchar, estado boolean, nombre varchar, paterno varchar, materno varchar, ci integer, fecha_reg date, \n" +
                    "    departamento integer, municipio integer, cod_servicio integer, cod_sub_servicio integer, codigo_exp integer, e_mail varchar, telefono integer, celular integer,\n" +
                    "    direccion varchar, fecha_estado date)\n" +
                    "    ) f ON f.cod_usuario = e.usuario_cod_usuario) t\n"+
"		where fecha_reg>='" + fecha_ini +"' and fecha_reg<= '" + fecha_fin + "'\n" +
"		GROUP  BY est;";
            System.out.println("entra : " + query);
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                view_report.setDescripcion(resultSet.getString("est"));
                view_report.setHombre(resultSet.getBigDecimal("h"));
                view_report.setMujer(resultSet.getBigDecimal("m"));
                view_report.setTota(resultSet.getBigDecimal("tot"));
                list.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return list;
    }
    
    @Override
    public List<View_violenceReport> reportViolenceBeen(Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> list = new ArrayList<>();
        View_violenceReport view_report1 = new View_violenceReport();
        View_reportDao view_reportDao = new View_reportDaoImpl();
        List<T_datos> list_t = new ArrayList<>();
        try {
            String query = "select * from sp_view_violenciaestcivil_by_date('" + fecha_ini + "','" + fecha_fin + "')";
            System.out.println("entra : " + query);
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                // String concatenaViolencias = null;
                int id_t = 0;
                view_report.setColumna1(resultSet.getString("descripcion"));
                view_report.setColumna2(Integer.toString(resultSet.getInt("estado_civil")));
                view_report.setColumna3(Integer.toString(resultSet.getInt("hombres")));
                view_report.setColumna4(Integer.toString(resultSet.getInt("mujeres")));
                view_report.setColumna5(Integer.toString(resultSet.getInt("total")));

                list.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<View_violenceReport> reportViolenceDpto(Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> list = new ArrayList<>();
        View_violenceReport view_report1 = new View_violenceReport();
        View_reportDao view_reportDao = new View_reportDaoImpl();
        List<T_datos> list_t = new ArrayList<>();
        try {
            String query = "select * from sp_view_violenciadepartamento_by_date('" + fecha_ini + "','" + fecha_fin + "')";
            System.out.println("entra : " + query);
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                // String concatenaViolencias = null;
                int id_t = 0;
                view_report.setColumna1(resultSet.getString("descripcion"));
                view_report.setColumna2(Integer.toString(resultSet.getInt("estado_civil")));
                view_report.setColumna3(Integer.toString(resultSet.getInt("hombres")));
                view_report.setColumna4(Integer.toString(resultSet.getInt("mujeres")));
                view_report.setColumna5(Integer.toString(resultSet.getInt("total")));

                list.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<View_violenceReport> reportViolenceDepartmentMunicipalityAge(Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> list = new ArrayList<>();
        View_violenceReport view_report1 = new View_violenceReport();
        View_reportDao view_reportDao = new View_reportDaoImpl();
        List<T_datos> list_t = new ArrayList<>();
        try {
            String query = "select * from sp_view_violenciadptomunicipioedad_by_date('" + fecha_ini + "','" + fecha_fin + "')";
            System.out.println("entra : " + query);
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                // String concatenaViolencias = null;
                int id_t = 0;
                view_report.setColumna1(resultSet.getString("violencia"));
                view_report.setColumna2(Integer.toString(resultSet.getInt("dpto")));
                //view_report.setColumna3(Integer.toString(resultSet.getInt("municipio")));
                view_report.setColumna3(resultSet.getString("rango"));
                view_report.setColumna4(Integer.toString(resultSet.getInt("hombres")));
                view_report.setColumna5(Integer.toString(resultSet.getInt("mujeres")));
                view_report.setColumna6(Integer.toString(resultSet.getInt("total")));

                list.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<View_violenceReport> reportViolenceMunicipalityService(Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> list = new ArrayList<>();
        View_violenceReport view_report1 = new View_violenceReport();
        View_reportDao view_reportDao = new View_reportDaoImpl();
        try {
            String query = "select * from sp_view_violenciamunicipioservicio_by_date('" + fecha_ini + "','" + fecha_fin + "')";
            System.out.println("entra : " + query);
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                // String concatenaViolencias = null;
                int id_t = 0;
                view_report.setColumna1(resultSet.getString("descripcion"));
                view_report.setColumna2(Integer.toString(resultSet.getInt("mun")));
                view_report.setColumna3(Integer.toString(resultSet.getInt("cod")));
                view_report.setColumna4(Integer.toString(resultSet.getInt("hombres")));
                view_report.setColumna5(Integer.toString(resultSet.getInt("mujeres")));
                view_report.setColumna6(Integer.toString(resultSet.getInt("total")));

                list.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<View_violenceReport> reportViolenceServiceSubService(Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> list = new ArrayList<>();
        View_violenceReport view_report1 = new View_violenceReport();
        View_reportDao view_reportDao = new View_reportDaoImpl();
        try {
            String query = "select * from sp_view_violenciaserviciosubservicio_by_date('" + fecha_ini + "','" + fecha_fin + "')";
            System.out.println("entra : " + query);
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                // String concatenaViolencias = null;
                int id_t = 0;
                view_report.setColumna1(resultSet.getString("descripcion"));
                view_report.setColumna2(Integer.toString(resultSet.getInt("cod")));
                view_report.setColumna3(Integer.toString(resultSet.getInt("cod_sub")));
                view_report.setColumna4(Integer.toString(resultSet.getInt("hombres")));
                view_report.setColumna5(Integer.toString(resultSet.getInt("mujeres")));
                view_report.setColumna6(Integer.toString(resultSet.getInt("total")));

                list.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<View_violenceReport> reporte_edad_victima(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname) {
        List<View_violenceReport> lista = new ArrayList<>();
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{ 
        
        CallableStatement cs = connection.prepareCall("SELECT * from sp_reporte_edad_victima(?, ?, ?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(4, host);
        cs.setString(5, usuario);
        cs.setString(6, pasword);
        cs.setString(7, dbname);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("label"));
            reg.setSexo(result.getString("sexo"));
            reg.setContador(new BigDecimal(result.getInt("count")));
            lista.add(reg);
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
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_persona_victima(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from sp_reporte_persona_victima(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(4, host);
        cs.setString(5, usuario);
        cs.setString(6, pasword);
        cs.setString(7, dbname);
        cs.setString(8, descripcion_tdato);
        cs.setString(9, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_tviolencia_victima(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname) {
        List<View_violenceReport> lista = new ArrayList<>();
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{
        CallableStatement cs = connection.prepareCall("SELECT * from sp_reporte_tviolencia_victima(?, ?, ?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(4, host);
        cs.setString(5, usuario);
        cs.setString(6, pasword);
        cs.setString(7, dbname);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_vicagr_victima(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{        
        CallableStatement cs = connection.prepareCall("SELECT * from sp_reporte_vicagr_victima(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(4, host);
        cs.setString(5, usuario);
        cs.setString(6, pasword);
        cs.setString(7, dbname);
        cs.setString(8, descripcion_tdato);
        cs.setString(9, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_edad_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname) {
        List<View_violenceReport> lista = new ArrayList<>();
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        System.out.println("dao.View_reportDaoImpl.reporte_edad_Agresor(): "+codigo_usuario+new java.sql.Date(fecha_ini.getTime())+new java.sql.Date(fecha_fin.getTime())
        +host+usuario+pasword+dbname);
        try{ 
        
        CallableStatement cs = connection.prepareCall("SELECT * from sp_reporte_edad_agresor(?, ?, ?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(4, host);
        cs.setString(5, usuario);
        cs.setString(6, pasword);
        cs.setString(7, dbname);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("label"));
            reg.setSexo(result.getString("sexo"));
            reg.setContador(new BigDecimal(result.getInt("count")));
            lista.add(reg);
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
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_persona_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from sp_reporte_persona_agresor(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(4, host);
        cs.setString(5, usuario);
        cs.setString(6, pasword);
        cs.setString(7, dbname);
        cs.setString(8, descripcion_tdato);
        cs.setString(9, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_tviolencia_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname) {
        List<View_violenceReport> lista = new ArrayList<>();
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        
        try{
            
        CallableStatement cs = connection.prepareCall("SELECT * from sp_reporte_tviolencia_agresor(?, ?, ?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(4, host);
        cs.setString(5, usuario);
        cs.setString(6, pasword);
        cs.setString(7, dbname);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_vicagr_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String host, String usuario, String pasword, String dbname, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{        
        CallableStatement cs = connection.prepareCall("SELECT * from sp_reporte_vicagr_agresor(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));
        cs.setString(4, host);
        cs.setString(5, usuario);
        cs.setString(6, pasword);
        cs.setString(7, dbname);
        cs.setString(8, descripcion_tdato);
        cs.setString(9, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }
    
    // REPORTE TENENCIA VIVIENDA

    @Override
    public List<View_violenceReport> reporte_vivienda(Integer codigo_usuario, Date fecha_ini, Date fecha_fin) {
        System.out.println("dao.View_reportDaoImpl.reporte_vivienda(): Cod usuario: "+codigo_usuario+" fecha ini: "+fecha_ini+" fecha fin: "+fecha_fin);
        List<View_violenceReport> lista = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection connection  = conn.getConnectionDB();
        try {
            String query = "SELECT\n" +
                           " Count(*) AS total,\n" +
                           " Sum(case when vivienda = 37 then 1 else 0 end) AS totalpropia,\n" +
                           " Sum(case when vivienda = 38 then 1 else 0 end) AS totalanticretico,\n" +
                           " Sum(case when vivienda = 39 then 1 else 0 end) AS totalalquiler,\n" +
                           " Sum(case when vivienda = 40 then 1 else 0 end) AS totalcedida,\n" +
                           " Sum(case when vivienda = 41 then 1 else 0 end) AS totalprestada,\n" +
                           " Sum(case when vivienda = 42 then 1 else 0 end) AS totalotra\n" +
                           " FROM\n" +
                           " persona_variables\n" +
                           " INNER JOIN vicagre_persona ON vicagre_persona.persona_var_victima_id_pervar = persona_variables.id_pervar\n" +
                           " INNER JOIN victima_agresor ON vicagre_persona.victima_agresor_id_vicagre = victima_agresor.id_vicagre\n" +
                           " INNER JOIN usuario_vicagre ON usuario_vicagre.victima_agresor_id_vicagre = victima_agresor.id_vicagre\n" +
                           " WHERE\n" +
                           " usuario_vicagre.usuario_cod_usuario = "+codigo_usuario+"\n" +
                           " AND usuario_vicagre.fecha_reg BETWEEN '"+fecha_ini+"' AND '"+fecha_fin+"'";
            
            
            Statement statement = connection.createStatement();            
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                              
                view_report.setPropia(resultSet.getInt("totalpropia"));
                view_report.setAnticretico(resultSet.getInt("totalanticretico"));
                view_report.setAlquiler(resultSet.getInt("totalalquiler"));
                view_report.setCedida(resultSet.getInt("totalcedida"));
                view_report.setPrestada(resultSet.getInt("totalprestada"));
                view_report.setOtra(resultSet.getInt("totalotra"));

                lista.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        
        return lista;
    }
    
     // REPORTE estado civil

    @Override
    public List<View_violenceReport> reporte_civil(Integer codigo_usuario, Date fecha_ini, Date fecha_fin) {
        //System.out.println("dao.View_reportDaoImpl.reporte_vivienda(): Cod usuario: "+codigo_usuario+" fecha ini: "+fecha_ini+" fecha fin: "+fecha_fin);
        List<View_violenceReport> lista = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection connection  = conn.getConnectionDB();
        try {
            String query = "SELECT\n" +
                           " Count(*) AS total,\n" +
                           " Sum(case when est_civil = 10 then 1 else 0 end) AS soltero,\n" +
                           " Sum(case when est_civil = 12 then 1 else 0 end) AS casado,\n" +
                           " Sum(case when est_civil = 13 then 1 else 0 end) AS concubino,\n" +
                           " Sum(case when est_civil = 14 then 1 else 0 end) AS divorciado,\n" +
                           " Sum(case when est_civil = 15 then 1 else 0 end) AS separado,\n" +
                           " Sum(case when est_civil = 16 then 1 else 0 end) AS viuda\n" +
                           " FROM\n" +
                           " persona_variables\n" +
                           " INNER JOIN vicagre_persona ON vicagre_persona.persona_var_victima_id_pervar = persona_variables.id_pervar\n" +
                           " INNER JOIN victima_agresor ON vicagre_persona.victima_agresor_id_vicagre = victima_agresor.id_vicagre\n" +
                           " INNER JOIN usuario_vicagre ON usuario_vicagre.victima_agresor_id_vicagre = victima_agresor.id_vicagre\n" +
                           " WHERE\n" +
                           " usuario_vicagre.usuario_cod_usuario = "+codigo_usuario+"\n" +
                           " AND usuario_vicagre.fecha_reg BETWEEN '"+fecha_ini+"' AND '"+fecha_fin+"'";
            
            
            Statement statement = connection.createStatement();            
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                              
                view_report.setSoltero(resultSet.getInt("soltero"));
                view_report.setCasado(resultSet.getInt("casado"));
                view_report.setConcubino(resultSet.getInt("concubino"));
                view_report.setDivorciado(resultSet.getInt("divorciado"));
                view_report.setSeparado(resultSet.getInt("separado"));
                view_report.setViudo(resultSet.getInt("viuda"));

                lista.add(view_report);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        
        return lista;
    }
    
    
    // REPORTE ocupacion principal

    @Override
    public List<View_violenceReport> reporte_ocupacion(Integer codigo_usuario, Date fecha_ini, Date fecha_fin) {
        //System.out.println("dao.View_reportDaoImpl.reporte_vivienda(): Cod usuario: "+codigo_usuario+" fecha ini: "+fecha_ini+" fecha fin: "+fecha_fin);
        List<View_violenceReport> lista = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection connection  = conn.getConnectionDB();
        try {
            String query = "SELECT\n" +
                           " Count(*) AS total,\n" +
                           " Sum(case when ocupacion = 49 then 1 else 0 end) AS estudiante,\n" +
                           " Sum(case when ocupacion = 50 then 1 else 0 end) AS propia,\n" +
                           " Sum(case when ocupacion = 51 then 1 else 0 end) AS labores,\n" +
                           " Sum(case when ocupacion = 52 then 1 else 0 end) AS empleada,\n" +
                           " Sum(case when ocupacion = 53 then 1 else 0 end) AS otro\n" +                           
                           " FROM\n" +
                           " persona_variables\n" +
                           " INNER JOIN vicagre_persona ON vicagre_persona.persona_var_victima_id_pervar = persona_variables.id_pervar\n" +
                           " INNER JOIN victima_agresor ON vicagre_persona.victima_agresor_id_vicagre = victima_agresor.id_vicagre\n" +
                           " INNER JOIN usuario_vicagre ON usuario_vicagre.victima_agresor_id_vicagre = victima_agresor.id_vicagre\n" +
                           " WHERE\n" +
                           " usuario_vicagre.usuario_cod_usuario = "+codigo_usuario+"\n" +
                           " AND usuario_vicagre.fecha_reg BETWEEN '"+fecha_ini+"' AND '"+fecha_fin+"'";
            
            
            Statement statement = connection.createStatement();            
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                View_violenceReport view_report = new View_violenceReport();
                              
                view_report.setEstudiante(resultSet.getInt("estudiante"));
                view_report.setCuentapropia(resultSet.getInt("propia"));
                view_report.setLabores(resultSet.getInt("labores"));
                view_report.setEmpleada(resultSet.getInt("empleada"));
                view_report.setOtraocupacion(resultSet.getInt("otro"));
                
                lista.add(view_report);
                
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        
        return lista;
    }

    @Override
    public List<View_violenceReport> reporte_simple(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_reporte_persona_victima(?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, descripcion_tdato);
        cs.setString(5, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            //reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }
    
    @Override
    public List<View_violenceReport> reporte_simple_victima_agresion(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_reporte_vicagr_victima(?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, descripcion_tdato);
        cs.setString(5, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            //reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }
    
    @Override
    public List<View_violenceReport> reporte_simple_booleanos(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_bool_reporte_persona_victima(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));  
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while(result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();               
              
                
                if(result.getBoolean("colum1")== true){
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Otro Municipio");
                            auxDesc = "Mismo Municipio";
                            break;
                        case "area":
                            reg.setDescripcion("Rural");
                            auxDesc = "Urbano";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("No Remunerado");
                            auxDesc = "Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("Pertenece");
                            auxDesc = "No Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("Si Pertenece a LGTB");
                            auxDesc = "No Pertence a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Con Discapacidad");
                            auxDesc = "Sin Discapacidad";
                            break;    
                    }
                }else{
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Mismo Municipio"); 
                            auxDesc = "Otro Municipo";
                            break;
                        case "area":
                            reg.setDescripcion("Urbano");
                            auxDesc = "Rural";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("Remunerado");
                            auxDesc = "No Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("No Pertenece");
                            auxDesc = "Si Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("No Pertenece a LGTB");
                            auxDesc = "Pertenece a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Sin Discapacidad");
                            auxDesc = "Con Discapacidad";
                            break;
                    }
                }
                //reg.setColumnBool(result.getBoolean("colum1"));
                //reg.setSexo(result.getString("colum2"));

                reg.setContador(new BigDecimal(result.getInt("id")));            
                lista.add(reg);
            }
            //System.out.println("View_reportDaoImpl() : LISTA SIZE " + lista.size());
            if(lista.size()==1){
               View_violenceReport reg2 = new View_violenceReport();
               String descFaltante = lista.get(0).getDescripcion();
               //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
               reg2.setDescripcion(auxDesc);
               reg2.setContador(BigDecimal.ZERO);
               lista.add(reg2);
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
        
        return lista;
    }
    
    @Override
    public List<View_violenceReport> reporte_simple_strings(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_string_reporte_persona_victima(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while (result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee" + result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();
                if ((result.getString("colum1")).equals("H")) {
                    reg.setDescripcion("Hombre");
                    auxDesc = "Mujer";
                } else {
                    reg.setDescripcion("Mujer");
                    auxDesc = "Hombre";
                }
                //reg.setDescripcion(result.getString("colum1"));
                //reg.setSexo(result.getString("colum2"));
                reg.setContador(new BigDecimal(result.getInt("id")));
                lista.add(reg);
            }
            if (lista.size() == 1) {
                View_violenceReport reg2 = new View_violenceReport();
                String descFaltante = lista.get(0).getDescripcion();
                //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
                reg2.setDescripcion(auxDesc);
                reg2.setContador(BigDecimal.ZERO);
                lista.add(reg2);
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
        
        return lista;
    }
    
    @Override
    public List<View_violenceReport> reporte_simple_ints(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_int_reporte_persona_victima(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while (result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee" + result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();
                if (Integer.parseInt(result.getString("colum1")) == 0 ) {
                   reg.setDescripcion("No en Gestacion");
                    auxDesc = "En Gestacion";
                } else {                    
                     reg.setDescripcion("En Gestacion");
                    auxDesc = "No en Gestacion";
                }
                //reg.setDescripcion(result.getString("colum1"));
                //reg.setSexo(result.getString("colum2"));
               
                reg.setContador(new BigDecimal(result.getInt("id")));
                
                lista.add(reg);
            }
            if (lista.size() == 1) {
                View_violenceReport reg2 = new View_violenceReport();
                String descFaltante = lista.get(0).getDescripcion();
                //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
                reg2.setDescripcion(auxDesc);
                reg2.setContador(BigDecimal.ZERO);
                lista.add(reg2);
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
        
        return lista;
    }
    
    
    @Override
    public List<View_violenceReport> reporte_simple_ints_hijos(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_int_reporte_persona_victima(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while (result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee" + result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();
                switch (Integer.parseInt(result.getString("colum1"))){
                    case 0:
                        reg.setDescripcion("No tiene Hijos");
                        break;
                    case 1:
                        reg.setDescripcion("Tiene Un Hijo");
                        break;
                    case 2:
                        reg.setDescripcion("Tiene Dos Hijos");
                        break;
                    case 3:
                        reg.setDescripcion("Tiene Tres Hijos");
                        break;
                    case 4:
                        reg.setDescripcion("Tiene Cuatro Hijos");
                        break;
                    case 5:
                        reg.setDescripcion("Tiene Cinco Hijos");
                        break;    
                    case 6:
                        reg.setDescripcion("Tiene Seis Hijos");
                        break;    
                }
                /*        
                if (Integer.parseInt(result.getString("colum1")) == 0 ) {
                   reg.setDescripcion("No en Gestacion");
                    auxDesc = "En Gestacion";
                } else {                    
                     reg.setDescripcion("En Gestacion");
                    auxDesc = "No en Gestacion";
                }*/
                //reg.setDescripcion(result.getString("colum1"));
                //reg.setSexo(result.getString("colum2"));
               
                reg.setContador(new BigDecimal(result.getInt("id")));
                
                lista.add(reg);
            }
            if (lista.size() == 1) {
                View_violenceReport reg2 = new View_violenceReport();
                String descFaltante = lista.get(0).getDescripcion();
                //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
                reg2.setDescripcion(auxDesc);
                reg2.setContador(BigDecimal.ZERO);
                lista.add(reg2);
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
        
        return lista;
    }
    
     @Override
    public List<View_violenceReport> reporte_simple_edad(Integer codigo_usuario, Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_reporte_edad_victima(?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));       
        
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
           
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("label"));
            //reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("count")));
            lista.add(reg);
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
        
        return lista;
    }
    
    
     @Override
    public List<View_violenceReport> reporte_simple_violencia(Integer codigo_usuario, Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_reporte_tviolencia_victima(?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));       
        
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
           
            View_violenceReport reg = new View_violenceReport();
            //String st = ;
            //String resSubstring = st.substring(1);
            String[] array = result.getString("colum1").split(",");
            
                StringBuilder sb = new StringBuilder();
                for(String element : array){
                    //System.out.println("dao.View_reportDaoImpl.reporte_simple_ints_idioma() Idiomas ids "+ element);
                    //String builder = sb.append(element).toString();
                    switch (element) {
                        case "98":
                            sb.append("- Violencia Fisica  ");
                            break;
                        case "99":
                            sb.append("- Violencia Psicologica ");
                            break;
                        case "100":
                            sb.append("- Violencia Sexual ");
                            break;
                        case "101":
                            sb.append("- Violencia Patrimonial y Economica ");
                            break;
                        case "102":
                            sb.append("- Violencia Simbolica y/o Encubierta ");
                            break;
                        case "103":
                            sb.append("- Violencia en la Familia ");
                            break;
                        case "104":
                            sb.append("- Violencia Laboral ");
                            break;
                        case "105":
                            sb.append("- Violencia en Servicio de Salud ");
                            break;
                        case "106":
                            sb.append("- Violencia en el Sistema Educativo Plurinacional ");
                            break;
                        case "107":
                            sb.append("- Violencia Institucional ");
                            break;
                        case "108":
                            sb.append("- Violencia Mediatica ");
                            break;
                        case "109":
                            sb.append("- Violencia en el Ejercicio Politico y de Liderazgo ");
                            break;
                        case "110":
                            sb.append("- Violencia Contra la Dignidad, la Honra y el Nombre ");
                            break;
                        case "111":
                            sb.append("- Violencia Contra los Derechos Reproductivos ");
                            break; 
                        case "112":
                            sb.append("- Violencia Contra los Derechos de Libertad Sexual ");
                            break; 
                        case "113":
                            sb.append("- Violencia Feminicida ");
                            break;  
                           
                    }
                }
                //System.out.println("dao.View_reportDaoImpl.reporte_simple_ints_idioma(): APPEND string builder " +sb.toString());
                reg.setDescripcion(sb.toString());
            
            //reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }
    
    @Override
    public List<View_violenceReport> reporte_simple_ints_idioma(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_string_reporte_persona_victima(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while (result.next()) {
                System.out.println("view_reportDAOIMPL : IDIOMA" + result.getString("colum1"));
                View_violenceReport reg = new View_violenceReport();
                String[] array = result.getString("colum1").split(",");
                StringBuilder sb = new StringBuilder();
                for(String element : array){
                    System.out.println("dao.View_reportDaoImpl.reporte_simple_ints_idioma() Idiomas ids "+ element);
                    //String builder = sb.append(element).toString();
                    switch (element) {
                        case "58":
                            sb.append("- Castellano ");
                            break;
                        case "59":
                            sb.append("- Quechua ");
                            break;
                        case "60":
                            sb.append("- Aymara ");
                            break;
                        case "61":
                            sb.append("- Guarani ");
                            break;
                        case "62":
                            sb.append("- Otro Nativo ");
                            break;
                        case "63":
                            sb.append("- Extranjero ");
                            break;
                    }
                }
                System.out.println("dao.View_reportDaoImpl.reporte_simple_ints_idioma(): APPEND string builder " +sb.toString());
                reg.setDescripcion(sb.toString());
               
                
             
               
                reg.setContador(new BigDecimal(result.getInt("id")));
                
                lista.add(reg);
            }/*
            if (lista.size() == 1) {
                View_violenceReport reg2 = new View_violenceReport();
                String descFaltante = lista.get(0).getDescripcion();
                //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
                reg2.setDescripcion(auxDesc);
                reg2.setContador(BigDecimal.ZERO);
                lista.add(reg2);
            }*/
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
        
        return lista;
    }
    
    
    /**************************************/
    @Override
    public List<View_violenceReport> reporte_simple_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_string_reporte_persona_agresor(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while (result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee" + result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();
                if ((result.getString("colum1")).equals("H")) {
                    reg.setDescripcion("Hombres");
                    auxDesc = "Mujeres";
                } else {
                    reg.setDescripcion("Mujeres");
                    auxDesc = "Hombres";
                }
                //reg.setDescripcion(result.getString("colum1"));
                //reg.setSexo(result.getString("colum2"));
                reg.setContador(new BigDecimal(result.getInt("id")));
                lista.add(reg);
            }
            if (lista.size() == 1) {
                View_violenceReport reg2 = new View_violenceReport();
                String descFaltante = lista.get(0).getDescripcion();
                //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
                reg2.setDescripcion(auxDesc);
                reg2.setContador(BigDecimal.ZERO);
                lista.add(reg2);
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
        
        return lista;
    }
    
    /********************************************nivel instruccion***********************************/
    @Override
    public List<View_violenceReport> reporte_simple_agresor_instrucc(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_reporte_persona_agresor_insturcc(?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, descripcion_tdato);
        cs.setString(5, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            //reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }
    /************************************************ocupacion******************************/
    @Override
    public List<View_violenceReport> reporte_agresor_ocupacion(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_reporte_persona_agresor_ocupacion(?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, descripcion_tdato);
        cs.setString(5, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            //reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }
    /**************************************/
   /* @Override
    public List<View_violenceReport> reporte_simple_agresor_nacimiento(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_string_reporte_agresor_nacimiento(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while (result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee" + result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();
                if ((result.getString("colum1")).equals("H")) {
                    reg.setDescripcion("Hombres");
                    auxDesc = "Mujeres";
                } else {
                    reg.setDescripcion("Mujeres");
                    auxDesc = "Hombres";
                }
                //reg.setDescripcion(result.getString("colum1"));
                //reg.setSexo(result.getString("colum2"));
                reg.setContador(new BigDecimal(result.getInt("id")));
                lista.add(reg);
            }
            if (lista.size() == 1) {
                View_violenceReport reg2 = new View_violenceReport();
                String descFaltante = lista.get(0).getDescripcion();
                //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
                reg2.setDescripcion(auxDesc);
                reg2.setContador(BigDecimal.ZERO);
                lista.add(reg2);
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
        
        return lista;
    }*/
    @Override
    public List<View_violenceReport> reporte_simple_agresor_nac(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_reporte_agresor_nacimiento(?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, descripcion_tdato);
        cs.setString(5, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            //reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }
    
    /*******************************PARA MUNICIPIO DONDE VIVE EL AGRESOR**************/
    @Override
    public List<View_violenceReport> reporte_simple_booleanos_agresor(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_bool_reporte_persona_agresor(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));  
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while(result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();               
              
                
                if(result.getBoolean("colum1")== true){
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Otro Municipio");
                            auxDesc = "Mismo Municipio";
                            break;
                        case "area":
                            reg.setDescripcion("Rural");
                            auxDesc = "Urbano";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("No Remunerado");
                            auxDesc = "Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("Pertenece");
                            auxDesc = "No Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("Si Pertenece a LGTB");
                            auxDesc = "No Pertence a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Con Discapacidad");
                            auxDesc = "Sin Discapacidad";
                            break;    
                    }
                }else{
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Mismo Municipio"); 
                            auxDesc = "Otro Municipo";
                            break;
                        case "area":
                            reg.setDescripcion("Urbano");
                            auxDesc = "Rural";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("Remunerado");
                            auxDesc = "No Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("No Pertenece");
                            auxDesc = "Si Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("No Pertenece a LGTB");
                            auxDesc = "Pertenece a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Sin Discapacidad");
                            auxDesc = "Con Discapacidad";
                            break;
                    }
                }
                //reg.setColumnBool(result.getBoolean("colum1"));
                //reg.setSexo(result.getString("colum2"));

                reg.setContador(new BigDecimal(result.getInt("id")));            
                lista.add(reg);
            }
            //System.out.println("View_reportDaoImpl() : LISTA SIZE " + lista.size());
            if(lista.size()==1){
               View_violenceReport reg2 = new View_violenceReport();
               String descFaltante = lista.get(0).getDescripcion();
               //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
               reg2.setDescripcion(auxDesc);
               reg2.setContador(BigDecimal.ZERO);
               lista.add(reg2);
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
        
        return lista;
    }
    /***************************************************para pueblo originario**************************************/
    @Override
    public List<View_violenceReport> reporte_simple_booleanos_originario(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_bool_reporte_agresor_origen(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));  
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while(result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();               
              
                
                if(result.getBoolean("colum1")== true){
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Otro Municipio");
                            auxDesc = "Mismo Municipio";
                            break;
                        case "area":
                            reg.setDescripcion("Rural");
                            auxDesc = "Urbano";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("No Remunerado");
                            auxDesc = "Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("Pertenece");
                            auxDesc = "No Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("Si Pertenece a LGTB");
                            auxDesc = "No Pertence a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Con Discapacidad");
                            auxDesc = "Sin Discapacidad";
                            break;    
                    }
                }else{
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Mismo Municipio"); 
                            auxDesc = "Otro Municipo";
                            break;
                        case "area":
                            reg.setDescripcion("Urbano");
                            auxDesc = "Rural";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("Remunerado");
                            auxDesc = "No Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("No Pertenece");
                            auxDesc = "Si Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("No Pertenece a LGTB");
                            auxDesc = "Pertenece a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Sin Discapacidad");
                            auxDesc = "Con Discapacidad";
                            break;
                    }
                }
                //reg.setColumnBool(result.getBoolean("colum1"));
                //reg.setSexo(result.getString("colum2"));

                reg.setContador(new BigDecimal(result.getInt("id")));            
                lista.add(reg);
            }
            //System.out.println("View_reportDaoImpl() : LISTA SIZE " + lista.size());
            if(lista.size()==1){
               View_violenceReport reg2 = new View_violenceReport();
               String descFaltante = lista.get(0).getDescripcion();
               //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
               reg2.setDescripcion(auxDesc);
               reg2.setContador(BigDecimal.ZERO);
               lista.add(reg2);
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
        
        return lista;
    }
    
    /***************************************************para area donde vive**************************************/
    @Override
    public List<View_violenceReport> reporte_simple_booleanos_area(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_bool_reporte_agresor_area(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));  
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while(result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();               
              
                
                if(result.getBoolean("colum1")== true){
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Otro Municipio");
                            auxDesc = "Mismo Municipio";
                            break;
                        case "area":
                            reg.setDescripcion("Rural");
                            auxDesc = "Urbano";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("No Remunerado");
                            auxDesc = "Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("Pertenece");
                            auxDesc = "No Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("Si Pertenece a LGTB");
                            auxDesc = "No Pertence a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Con Discapacidad");
                            auxDesc = "Sin Discapacidad";
                            break;    
                    }
                }else{
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Mismo Municipio"); 
                            auxDesc = "Otro Municipo";
                            break;
                        case "area":
                            reg.setDescripcion("Urbano");
                            auxDesc = "Rural";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("Remunerado");
                            auxDesc = "No Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("No Pertenece");
                            auxDesc = "Si Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("No Pertenece a LGTB");
                            auxDesc = "Pertenece a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Sin Discapacidad");
                            auxDesc = "Con Discapacidad";
                            break;
                    }
                }
                //reg.setColumnBool(result.getBoolean("colum1"));
                //reg.setSexo(result.getString("colum2"));

                reg.setContador(new BigDecimal(result.getInt("id")));            
                lista.add(reg);
            }
            //System.out.println("View_reportDaoImpl() : LISTA SIZE " + lista.size());
            if(lista.size()==1){
               View_violenceReport reg2 = new View_violenceReport();
               String descFaltante = lista.get(0).getDescripcion();
               //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
               reg2.setDescripcion(auxDesc);
               reg2.setContador(BigDecimal.ZERO);
               lista.add(reg2);
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
        
        return lista;
    }
    
    /***************************************************para condicion de actividad**************************************/
    @Override
    public List<View_violenceReport> reporte_simple_booleanos_agresor_actividad(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_bool_reporte_agresor_condi_act(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));  
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while(result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();               
              
                
                if(result.getBoolean("colum1")== true){
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Otro Municipio");
                            auxDesc = "Mismo Municipio";
                            break;
                        case "area":
                            reg.setDescripcion("Rural");
                            auxDesc = "Urbano";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("No Remunerado");
                            auxDesc = "Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("Pertenece");
                            auxDesc = "No Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("Si Pertenece a LGTB");
                            auxDesc = "No Pertence a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Con Discapacidad");
                            auxDesc = "Sin Discapacidad";
                            break;    
                    }
                }else{
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Mismo Municipio"); 
                            auxDesc = "Otro Municipo";
                            break;
                        case "area":
                            reg.setDescripcion("Urbano");
                            auxDesc = "Rural";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("Remunerado");
                            auxDesc = "No Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("No Pertenece");
                            auxDesc = "Si Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("No Pertenece a LGTB");
                            auxDesc = "Pertenece a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Sin Discapacidad");
                            auxDesc = "Con Discapacidad";
                            break;
                    }
                }
                //reg.setColumnBool(result.getBoolean("colum1"));
                //reg.setSexo(result.getString("colum2"));

                reg.setContador(new BigDecimal(result.getInt("id")));            
                lista.add(reg);
            }
            //System.out.println("View_reportDaoImpl() : LISTA SIZE " + lista.size());
            if(lista.size()==1){
               View_violenceReport reg2 = new View_violenceReport();
               String descFaltante = lista.get(0).getDescripcion();
               //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
               reg2.setDescripcion(auxDesc);
               reg2.setContador(BigDecimal.ZERO);
               lista.add(reg2);
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
        
        return lista;
    }
    
    
    /********************************************nivel instruccion***********************************/
    @Override
    public List<View_violenceReport> reporte_simple_agresor_ingr_economico(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_reporte_persona_agresor_insturcc(?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, descripcion_tdato);
        cs.setString(5, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            //reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }
/***************************************************para grupo lgtb**************************************/
    @Override
    public List<View_violenceReport> reporte_simple_booleanos_agresor_lgtb(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_bool_reporte_persona_agresor(?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));  
        cs.setString(4, campo_dato);
        ResultSet result = cs.executeQuery();
        String auxDesc = "";
            while(result.next()) {
                System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
                View_violenceReport reg = new View_violenceReport();               
              
                
                if(result.getBoolean("colum1")== true){
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Otro Municipio");
                            auxDesc = "Mismo Municipio";
                            break;
                        case "area":
                            reg.setDescripcion("Rural");
                            auxDesc = "Urbano";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("No Remunerado");
                            auxDesc = "Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("Pertenece");
                            auxDesc = "No Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("Si Pertenece a LGTB");
                            auxDesc = "No Pertence a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Con Discapacidad");
                            auxDesc = "Sin Discapacidad";
                            break;    
                    }
                }else{
                    switch (campo_dato){
                        case "municipio":
                            reg.setDescripcion("Mismo Municipio"); 
                            auxDesc = "Otro Municipo";
                            break;
                        case "area":
                            reg.setDescripcion("Urbano");
                            auxDesc = "Rural";
                            break;
                        case "condicion_act":
                            reg.setDescripcion("Remunerado");
                            auxDesc = "No Remunerado";
                            break;
                        case "etnia":
                            reg.setDescripcion("No Pertenece");
                            auxDesc = "Si Pertenece";
                            break;
                        case "lgbti":
                            reg.setDescripcion("No Pertenece a LGTB");
                            auxDesc = "Pertenece a LGTB";
                            break;
                        case "pers_discapacidad":
                            reg.setDescripcion("Sin Discapacidad");
                            auxDesc = "Con Discapacidad";
                            break;
                    }
                }
                //reg.setColumnBool(result.getBoolean("colum1"));
                //reg.setSexo(result.getString("colum2"));

                reg.setContador(new BigDecimal(result.getInt("id")));            
                lista.add(reg);
            }
            //System.out.println("View_reportDaoImpl() : LISTA SIZE " + lista.size());
            if(lista.size()==1){
               View_violenceReport reg2 = new View_violenceReport();
               String descFaltante = lista.get(0).getDescripcion();
               //System.out.println("View_reportDaoImpl(): Get Descripcion despues del ADD " + descFaltante);
               reg2.setDescripcion(auxDesc);
               reg2.setContador(BigDecimal.ZERO);
               lista.add(reg2);
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
        
        return lista;
    }
    /********************************************nivel instruccion***********************************/
    @Override
    public List<View_violenceReport> reporte_simple_agresor_est_civil(Integer codigo_usuario, Date fecha_ini, Date fecha_fin, String descripcion_tdato, String campo_dato) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_reporte_persona_agresor_insturcc(?, ?, ?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));        
        cs.setString(4, descripcion_tdato);
        cs.setString(5, campo_dato);
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
            System.out.println("entra a whileeeeeeeeeeeeeeeee"+result.getInt("id"));
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("colum1"));
            //reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("id")));
            lista.add(reg);
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
        
        return lista;
    }
    /*************************************************PARA EDAD*******************************/
    @Override
    public List<View_violenceReport> reporte_simple_agresor_edad(Integer codigo_usuario, Date fecha_ini, Date fecha_fin) {
        List<View_violenceReport> lista = new ArrayList<>();        
        
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        try{         
        CallableStatement cs = connection.prepareCall("SELECT * from coco_reporte_agresor_edad(?, ?, ?)");
        cs.setInt(1, codigo_usuario);
        cs.setDate(2, new java.sql.Date(fecha_ini.getTime()));
        cs.setDate(3, new java.sql.Date(fecha_fin.getTime()));       
        
        ResultSet result = cs.executeQuery();
        
        while(result.next()) {
           
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(result.getString("label"));
            //reg.setSexo(result.getString("colum2"));
            reg.setContador(new BigDecimal(result.getInt("count")));
            lista.add(reg);
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
        
        return lista;
    }
    
    
    //////////////////////******************************************************************************/


    
}

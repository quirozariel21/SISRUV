/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.UsuarioController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.Base_model;
import model.Log;
import model.Persona;
import model.Persona_variables;
import model.Usuario;
import model.Victima_agresor;
import util.ConnectionDB;

import util.ConnectionUDB;
import util.ConnectionLDB;

/**
 *
 * @author KRETCO
 */
public class Base_modelDaoImpl implements Base_modelDao {

    int res = 0, aux = 0;
    private Log log = new Log();
    private Object calendario;
    // Editado BORIS: AL PARECER NO SE USA ESTE METODO find_model - VERIFICAR
    @Override
    public List<Base_model> find_model(String numero, String nombre, String paterno, String materno) {
        List<Base_model> list_base_models = new ArrayList<>();
        try {
            System.out.println("FIND MODEL numero "+numero+"Nombre "+nombre+paterno);
            String query = "select * from sp_find_viewfindruv_('" + numero + "','" + nombre + "','" + paterno + "','" + materno + "')";
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Base_model base_model = new Base_model();

                base_model.getPersonaVictima().setId_persona(resultSet.getInt("id_persona"));
                base_model.getPersonaVictima().setNombre(resultSet.getString("nombre"));
                base_model.getPersonaVictima().setPaterno(resultSet.getString("paterno"));
                base_model.getPersonaVictima().setMaterno(resultSet.getString("materno"));
                base_model.getPersonaVictima().setAp_casada(resultSet.getString("ap_casada"));
                base_model.getPersonaVictima().setNumero(resultSet.getString("numero"));
                base_model.getPersonaVictima().setCodigo_documento(resultSet.getInt("codigo_documento"));
                base_model.getPersonaVictima().setSexo(resultSet.getString("sexo"));
                base_model.getPersonaVictima().setLugar_nac(resultSet.getInt("lugar_nac"));
                base_model.getPersonaVictima().setEsp_lugar_nac(resultSet.getString("esp_lugar_nac"));
                base_model.getPersonaVictima().setFecha_nac(resultSet.getDate("fecha_nac"));
                base_model.getPersonaVariableVictima().setId_pervar(resultSet.getInt("id_pervar"));
                base_model.getPersonaVariableVictima().setEdad(resultSet.getInt("edad"));
                base_model.getPersonaVariableVictima().setEst_civil(resultSet.getInt("est_civil"));
                base_model.getPersonaVariableVictima().setNro_hijos(resultSet.getInt("nro_hijos"));
                base_model.getPersonaVariableVictima().setGestacion_h(resultSet.getInt("gestacion_h"));
                base_model.getPersonaVariableVictima().setNum_miembros_fam(resultSet.getInt("num_miembros_fam"));
                base_model.getPersonaVariableVictima().setMunicipio(resultSet.getBoolean("municipio"));
                base_model.getPersonaVariableVictima().setEsp_municipio(resultSet.getString("esp_municipio"));
                base_model.getPersonaVariableVictima().setArea(resultSet.getBoolean("area"));
                base_model.getPersonaVariableVictima().setEsp_area(resultSet.getString("esp_area"));
                base_model.getPersonaVariableVictima().setVivienda(resultSet.getInt("vivienda"));
                base_model.getPersonaVariableVictima().setNivel_inst(resultSet.getInt("nivel_inst"));
                base_model.getPersonaVariableVictima().setCondicion_act(resultSet.getBoolean("condicion_act"));
                base_model.getPersonaVariableVictima().setOcupacion(resultSet.getInt("ocupacion"));
                base_model.getPersonaVariableVictima().setEsp_ocupacion(resultSet.getString("esp_ocupacion"));
                base_model.getPersonaVariableVictima().setCargo(resultSet.getString("cargo"));
                base_model.getPersonaVariableVictima().setIngre_economico(resultSet.getInt("ingre_economico"));
                base_model.getPersonaVariableVictima().setMonto_aprox_bs(resultSet.getBigDecimal("monto_aprox_bs"));
                base_model.getPersonaVariableVictima().setAporte_familiar_bs(resultSet.getBigDecimal("aporte_familiar_bs"));
                base_model.getPersonaVariableVictima().setIdiomas(resultSet.getString("idiomas"));
                base_model.getPersonaVariableVictima().setEsp_idioma(resultSet.getString("esp_idioma"));
                base_model.getPersonaVariableVictima().setEtnia(resultSet.getBoolean("etnia"));
                base_model.getPersonaVariableVictima().setEsp_etnia(resultSet.getString("esp_etnia"));
                base_model.getPersonaVariableVictima().setLgbti(resultSet.getBoolean("lgbti"));
                base_model.getPersonaVariableVictima().setPers_discapacidad(resultSet.getBoolean("pers_discapacidad"));
                base_model.getPersonaAgresor().setId_persona(resultSet.getInt("id_persona_a"));
                base_model.getPersonaAgresor().setNombre(resultSet.getString("nombre_a"));
                base_model.getPersonaAgresor().setPaterno(resultSet.getString("paterno_a"));
                base_model.getPersonaAgresor().setMaterno(resultSet.getString("materno_a"));
                base_model.getPersonaAgresor().setAp_casada(resultSet.getString("ap_casada_a"));
                base_model.getPersonaAgresor().setNumero(resultSet.getString("numero_a"));
                base_model.getPersonaAgresor().setCodigo_documento(resultSet.getInt("codigo_documento"));
                base_model.getPersonaAgresor().setSexo(resultSet.getString("sexo_a"));
                base_model.getPersonaAgresor().setLugar_nac(resultSet.getInt("lugar_nac_a"));
                base_model.getPersonaAgresor().setEsp_lugar_nac(resultSet.getString("esp_lugar_nac_a"));
                base_model.getPersonaAgresor().setFecha_nac(resultSet.getDate("fecha_nac_a"));
                base_model.getPersonaVariableAgresor().setId_pervar(resultSet.getInt("id_pervar_a"));
                base_model.getPersonaVariableAgresor().setEdad(resultSet.getInt("edad_a"));
                base_model.getPersonaVariableAgresor().setEst_civil(resultSet.getInt("est_civil_a"));
                base_model.getPersonaVariableAgresor().setNro_hijos(resultSet.getInt("nro_hijos_a"));
                base_model.getPersonaVariableAgresor().setGestacion_h(resultSet.getInt("gestacion_h_a"));
                base_model.getPersonaVariableAgresor().setNum_miembros_fam(resultSet.getInt("num_miembros_fam_a"));
                base_model.getPersonaVariableAgresor().setMunicipio(resultSet.getBoolean("municipio_a"));
                base_model.getPersonaVariableAgresor().setEsp_municipio(resultSet.getString("esp_municipio_a"));
                base_model.getPersonaVariableAgresor().setArea(resultSet.getBoolean("area_a"));
                base_model.getPersonaVariableAgresor().setEsp_area(resultSet.getString("esp_area_a"));
                base_model.getPersonaVariableAgresor().setVivienda(resultSet.getInt("vivienda_a"));
                base_model.getPersonaVariableAgresor().setNivel_inst(resultSet.getInt("nivel_inst_a"));
                base_model.getPersonaVariableAgresor().setCondicion_act(resultSet.getBoolean("condicion_act_a"));
                base_model.getPersonaVariableAgresor().setOcupacion(resultSet.getInt("ocupacion_a"));
                base_model.getPersonaVariableAgresor().setEsp_ocupacion(resultSet.getString("esp_ocupacion"));
                base_model.getPersonaVariableAgresor().setCargo(resultSet.getString("cargo_a"));
                base_model.getPersonaVariableAgresor().setIngre_economico(resultSet.getInt("ingre_economico_a"));
                base_model.getPersonaVariableAgresor().setMonto_aprox_bs(resultSet.getBigDecimal("monto_aprox_bs_a"));
                base_model.getPersonaVariableAgresor().setAporte_familiar_bs(resultSet.getBigDecimal("aporte_familiar_bs_a"));
                base_model.getPersonaVariableAgresor().setIdiomas(resultSet.getString("idiomas_a"));
                base_model.getPersonaVariableAgresor().setEsp_idioma(resultSet.getString("esp_idioma_a"));
                base_model.getPersonaVariableAgresor().setEtnia(resultSet.getBoolean("etnia_a"));
                base_model.getPersonaVariableAgresor().setEsp_etnia(resultSet.getString("esp_etnia_a"));
                base_model.getPersonaVariableAgresor().setLgbti(resultSet.getBoolean("lgbti_a"));
                base_model.getPersonaVariableAgresor().setPers_discapacidad(resultSet.getBoolean("pers_discapacidad_a"));
                base_model.getVicAgre_persona().setId_vicAgre_persona(resultSet.getInt("id_vicAgre_persona"));
                base_model.getVicAgre_persona().setVictima_agresor_id_vicagre(resultSet.getInt("victima_agresor_id_vicagre"));
                base_model.getVicAgre_persona().setPersona_id_persona_agresor(resultSet.getInt("persona_id_persona_agresor"));
                base_model.getVicAgre_persona().setPersona_id_persona_victima(resultSet.getInt("persona_id_persona_victima"));
                base_model.getVicAgre_persona().setReferencia_caso_de(resultSet.getString("referencia_caso_de"));
                base_model.getVicAgre_persona().setReferencia_esp_caso(resultSet.getString("referencia_esp_caso"));
                base_model.getVicAgre_persona().setContra_ref_caso_de(resultSet.getString("contra_ref_caso_de"));
                base_model.getVicAgre_persona().setContra_ref_esp_caso(resultSet.getString("contra_ref_esp_caso"));
                base_model.getVicAgre_persona().setCaso_resuelto(resultSet.getBoolean("caso_resuelto"));
                base_model.getVicAgre_persona().setCaso_abandonado(resultSet.getBoolean("caso_abandonado"));
                base_model.getVictima_agresor().setId_vicagre(resultSet.getInt("id_vicagre"));
                base_model.getVictima_agresor().setTestimonio(resultSet.getString("testimonio"));
                base_model.getVictima_agresor().setLugar_agresion(resultSet.getInt("lugar_agresion"));
                base_model.getVictima_agresor().setMunicipio(resultSet.getBoolean("municipio_d"));
                base_model.getVictima_agresor().setEsp_municipio(resultSet.getString("esp_municipio_d"));
                base_model.getVictima_agresor().setArea(resultSet.getBoolean("area_d"));
                base_model.getVictima_agresor().setEsp_area(resultSet.getString("esp_area_d"));
                base_model.getVictima_agresor().setParentesco(resultSet.getInt("parentesco"));
                base_model.getVictima_agresor().setEsp_parentesco(resultSet.getString("esp_parentesco"));
                base_model.getVictima_agresor().setTviolencia(resultSet.getString("tviolencia"));
                base_model.getVictima_agresor().setDenuncio(resultSet.getBoolean("denuncio"));
                base_model.getVictima_agresor().setDonde_denuncio(resultSet.getString("donde_denuncio"));
                base_model.getVictima_agresor().setFrec_agresiones(resultSet.getInt("frec_agresiones"));
                base_model.getVictima_agresor().setAgre_consume_alcohol(resultSet.getBoolean("agre_consume_alcohol"));
                base_model.getVictima_agresor().setFrec_consumo_alcohol(resultSet.getInt("frec_consumo_alcohol"));
                base_model.getVictima_agresor().setAgre_consume_drogas(resultSet.getBoolean("agre_consume_drogas"));
                base_model.getVictima_agresor().setFrec_consumo_drogas(resultSet.getInt("frec_consumo_drogas"));
                base_model.getVictima_agresor().setFrec_consumo_drogas(resultSet.getInt("frec_consumo_drogas"));
                /* Alertas*/
                base_model.getVictima_agresor().setPreg1(resultSet.getInt("preg1"));
                base_model.getVictima_agresor().setPreg2(resultSet.getInt("preg2"));
                base_model.getVictima_agresor().setPreg3(resultSet.getInt("preg3"));
                base_model.getVictima_agresor().setPreg4(resultSet.getInt("preg4"));
                base_model.getVictima_agresor().setPreg5(resultSet.getInt("preg5"));
                base_model.getVictima_agresor().setPreg6(resultSet.getInt("preg6"));
                base_model.getVictima_agresor().setPreg7(resultSet.getInt("preg7"));
                base_model.getVictima_agresor().setPreg8(resultSet.getInt("preg8"));
                base_model.getVictima_agresor().setPreg9(resultSet.getInt("preg9"));
                base_model.getVictima_agresor().setPreg10(resultSet.getInt("preg10"));
                base_model.getVictima_agresor().setPreg11(resultSet.getInt("preg11"));
                base_model.getVictima_agresor().setPreg12(resultSet.getInt("preg12"));
                base_model.getVictima_agresor().setPreg13(resultSet.getInt("preg13"));
                base_model.getVictima_agresor().setPreg14(resultSet.getInt("preg14"));
                base_model.getVictima_agresor().setPreg15(resultSet.getInt("preg15"));
                base_model.getVictima_agresor().setPreg16(resultSet.getInt("preg16"));
                base_model.getVictima_agresor().setPreg17(resultSet.getInt("preg17"));
                base_model.getVictima_agresor().setPreg18(resultSet.getInt("preg18"));
                base_model.getVictima_agresor().setPreg19(resultSet.getInt("preg19"));
                base_model.getVictima_agresor().setPreg_suma(resultSet.getInt("preg_suma"));             
                
                /* Alertas*/    
                list_base_models.add(base_model);
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return list_base_models;
    }

    @Override
    public int insert(int num, Base_model base_model, int existe) {
        PersonaDao personaDao = new PersonaDaoImpl();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        Usuario_vicagreDao usuario_vicagreDao = new Usuario_vicagreDaoImpl();
        Victima_agresorDao victima_agresorDao = new Victima_agresorDaoImpl();
        VicAgre_personaDao vicAgre_personaDao = new VicAgre_personaDaoImpl();
        Persona_VariablesDao persona_VariablesDao = new Persona_VariablesDaoImpl();
        LogDao logDao = new LogDaoImpl();
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        Base_model base_modellog = new Base_model();
        base_modellog = base_model;

        try {

            if (num == 1) {
                if (existe == 1) {
                    connection.setAutoCommit(false);
                    CallableStatement cs = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)"); //Audit log

                    cs.setInt(1, 2);
                    cs.setInt(2, base_model.getPersonaVictima().getId_persona());
                    cs.setString(3, base_model.getPersonaVictima().getNombre());
                    cs.setString(4, base_model.getPersonaVictima().getPaterno());
                    cs.setString(5, base_model.getPersonaVictima().getMaterno());
                    cs.setString(6, base_model.getPersonaVictima().getAp_casada());
                    cs.setString(7, base_model.getPersonaVictima().getNumero());
                    cs.setInt(8, base_model.getPersonaVictima().getCodigo_documento());
                    cs.setString(9, base_model.getPersonaVictima().getSexo());
                    cs.setInt(10, base_model.getPersonaVictima().getLugar_nac());
                    cs.setString(11, base_model.getPersonaVictima().getEsp_lugar_nac());
                    cs.setDate(12, new java.sql.Date(base_model.getPersonaVictima().getFecha_nac().getTime()));
                    cs.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                    
                    ResultSet resultSet = cs.executeQuery();

                    CallableStatement pv = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max() + 1);
                    pv.setInt(1, num);
                    pv.setInt(2, persona_VariablesDao.max() + 1);
                    pv.setInt(3, base_model.getPersonaVictima().getId_persona());
                    pv.setInt(4, base_model.getPersonaVariableVictima().getEdad());
                    pv.setInt(5, base_model.getPersonaVariableVictima().getEst_civil());
                    pv.setInt(6, base_model.getPersonaVariableVictima().getNro_hijos());
                    pv.setInt(7, base_model.getPersonaVariableVictima().getGestacion_h());
                    pv.setInt(8, base_model.getPersonaVariableVictima().getNum_miembros_fam());
                    pv.setBoolean(9, base_model.getPersonaVariableVictima().isMunicipio());
                    pv.setString(10, base_model.getPersonaVariableVictima().getEsp_municipio());
                    pv.setBoolean(11, base_model.getPersonaVariableVictima().isArea());
                    pv.setString(12, base_model.getPersonaVariableVictima().getEsp_area());
                    pv.setInt(13, base_model.getPersonaVariableVictima().getVivienda());
                    pv.setInt(14, base_model.getPersonaVariableVictima().getNivel_inst());
                    pv.setBoolean(15, base_model.getPersonaVariableVictima().isCondicion_act());
                    pv.setInt(16, base_model.getPersonaVariableVictima().getOcupacion());
                    pv.setString(17, base_model.getPersonaVariableVictima().getEsp_ocupacion());
                    pv.setString(18, base_model.getPersonaVariableVictima().getCargo());
                    pv.setInt(19, base_model.getPersonaVariableVictima().getIngre_economico());
                    pv.setBigDecimal(20, base_model.getPersonaVariableVictima().getMonto_aprox_bs());
                    pv.setBigDecimal(21, base_model.getPersonaVariableVictima().getAporte_familiar_bs());
                    pv.setString(22, base_model.getPersonaVariableVictima().getIdiomas());
                    pv.setString(23, base_model.getPersonaVariableVictima().getEsp_idioma());
                    pv.setBoolean(24, base_model.getPersonaVariableVictima().isEtnia());
                    pv.setString(25, base_model.getPersonaVariableVictima().getEsp_etnia());
                    pv.setBoolean(26, base_model.getPersonaVariableVictima().isLgbti());
                    pv.setBoolean(27, base_model.getPersonaVariableVictima().isPers_discapacidad());
                    pv.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                    
                    ResultSet resultSet_pv = pv.executeQuery();

                    CallableStatement cs1 = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    cs1.setInt(1, num);
                    cs1.setInt(2, personaDao.max() + 1);
                    cs1.setString(3, base_model.getPersonaAgresor().getNombre());
                    cs1.setString(4, base_model.getPersonaAgresor().getPaterno());
                    cs1.setString(5, base_model.getPersonaAgresor().getMaterno());
                    cs1.setString(6, base_model.getPersonaAgresor().getAp_casada());
                    cs1.setString(7, base_model.getPersonaAgresor().getNumero());
                    cs1.setInt(8, base_model.getPersonaAgresor().getCodigo_documento());
                    cs1.setString(9, base_model.getPersonaAgresor().getSexo());
                    cs1.setInt(10, base_model.getPersonaAgresor().getLugar_nac());
                    cs1.setString(11, base_model.getPersonaAgresor().getEsp_lugar_nac());
                    cs1.setDate(12, new java.sql.Date(base_model.getPersonaAgresor().getFecha_nac().getTime()));
                    cs1.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet_cs1 = cs1.executeQuery();

                    CallableStatement pv1 = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max() + 1);
                    pv1.setInt(1, num);
                    pv1.setInt(2, persona_VariablesDao.max() + 2);
                    pv1.setInt(3, personaDao.max() + 1);
                    pv1.setInt(4, base_model.getPersonaVariableAgresor().getEdad());
                    pv1.setInt(5, base_model.getPersonaVariableAgresor().getEst_civil());
                    pv1.setInt(6, base_model.getPersonaVariableAgresor().getNro_hijos());
                    pv1.setInt(7, base_model.getPersonaVariableAgresor().getGestacion_h());
                    pv1.setInt(8, base_model.getPersonaVariableAgresor().getNum_miembros_fam());
                    pv1.setBoolean(9, base_model.getPersonaVariableAgresor().isMunicipio());
                    pv1.setString(10, base_model.getPersonaVariableAgresor().getEsp_municipio());
                    pv1.setBoolean(11, base_model.getPersonaVariableAgresor().isArea());
                    pv1.setString(12, base_model.getPersonaVariableAgresor().getEsp_area());
                    pv1.setInt(13, base_model.getPersonaVariableAgresor().getVivienda());
                    pv1.setInt(14, base_model.getPersonaVariableAgresor().getNivel_inst());
                    pv1.setBoolean(15, base_model.getPersonaVariableAgresor().isCondicion_act());
                    pv1.setInt(16, base_model.getPersonaVariableAgresor().getOcupacion());
                    pv1.setString(17, base_model.getPersonaVariableAgresor().getEsp_ocupacion());
                    pv1.setString(18, base_model.getPersonaVariableAgresor().getCargo());
                    pv1.setInt(19, base_model.getPersonaVariableAgresor().getIngre_economico());
                    pv1.setBigDecimal(20, base_model.getPersonaVariableAgresor().getMonto_aprox_bs());
                    pv1.setBigDecimal(21, base_model.getPersonaVariableAgresor().getAporte_familiar_bs());
                    pv1.setString(22, base_model.getPersonaVariableAgresor().getIdiomas());
                    pv1.setString(23, base_model.getPersonaVariableAgresor().getEsp_idioma());
                    pv1.setBoolean(24, base_model.getPersonaVariableAgresor().isEtnia());
                    pv1.setString(25, base_model.getPersonaVariableAgresor().getEsp_etnia());
                    pv1.setBoolean(26, base_model.getPersonaVariableAgresor().isLgbti());
                    pv1.setBoolean(27, base_model.getPersonaVariableAgresor().isPers_discapacidad());
                    pv1.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet_pv1 = pv1.executeQuery();

                    CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//la funcion original sp_operaciones_victima_agresor(?...18)
                    va.setInt(1, num);
                    va.setInt(2, victima_agresorDao.max() + 1);
                    va.setString(3, base_model.getVictima_agresor().getTestimonio());
                    va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                    va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                    va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                    va.setBoolean(7, base_model.getVictima_agresor().isArea());
                    va.setString(8, base_model.getVictima_agresor().getEsp_area());
                    va.setInt(9, base_model.getVictima_agresor().getParentesco());
                    va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                    va.setString(11, base_model.getVictima_agresor().getTviolencia());
                    va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                    va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                    va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                    va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                    va.setInt(16, base_model.getVictima_agresor().getFrec_consumo_alcohol());
                    va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                    va.setInt(18, base_model.getVictima_agresor().getFrec_consumo_drogas());
                    /*INICIO Preguntas Alertas*/
                    va.setInt(19, base_model.getVictima_agresor().getPreg1());
                    va.setInt(20, base_model.getVictima_agresor().getPreg2());
                    va.setInt(21, base_model.getVictima_agresor().getPreg3());
                    va.setInt(22, base_model.getVictima_agresor().getPreg4());
                    va.setInt(23, base_model.getVictima_agresor().getPreg5());
                    va.setInt(24, base_model.getVictima_agresor().getPreg6());
                    va.setInt(25, base_model.getVictima_agresor().getPreg7());
                    va.setInt(26, base_model.getVictima_agresor().getPreg8());
                    va.setInt(27, base_model.getVictima_agresor().getPreg9());
                    va.setInt(28, base_model.getVictima_agresor().getPreg10());
                    va.setInt(29, base_model.getVictima_agresor().getPreg11());
                    va.setInt(30, base_model.getVictima_agresor().getPreg12());
                    va.setInt(31, base_model.getVictima_agresor().getPreg13());
                    va.setInt(32, base_model.getVictima_agresor().getPreg14());
                    va.setInt(33, base_model.getVictima_agresor().getPreg15());
                    va.setInt(34, base_model.getVictima_agresor().getPreg16());
                    va.setInt(35, base_model.getVictima_agresor().getPreg17());
                    va.setInt(36, base_model.getVictima_agresor().getPreg18());
                    va.setInt(37, base_model.getVictima_agresor().getPreg19());
                    va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
                    va.setInt(39, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                    /*FIN Preguntas Alertas*/    
                    ResultSet resultSet_va = va.executeQuery();

                    CallableStatement vp = connection.prepareCall("select coco_operaciones_vicagre_persona(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    vp.setInt(1, num);
                    vp.setInt(2, vicAgre_personaDao.max() + 1);
                    vp.setInt(3, victima_agresorDao.max() + 1);
                    vp.setInt(4, personaDao.max() + 1);
                    //vp.setInt(4, 1);
                    vp.setInt(5, base_model.getPersonaVictima().getId_persona());
                    vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                    vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                    vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                    vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                    vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                    vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                    vp.setInt(12, persona_VariablesDao.max() + 1);
                    vp.setInt(13, persona_VariablesDao.max() + 2);
                    vp.setInt(14, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet_vp = vp.executeQuery();

                    CallableStatement us = connection.prepareCall("select sp_operaciones_usuario_vicagre(?,?,?,?,?,?,?)");
                    aux = victima_agresorDao.max() + 1;

                    us.setInt(1, num);
                    us.setInt(2, base_model.getUsuario_vicagre().getId_usuario_vicagre());
                    us.setInt(3, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                    us.setInt(4, victima_agresorDao.max() + 1);
                    us.setDate(5, new java.sql.Date(base_model.getUsuario_vicagre().getFecha_reg().getTime()));
                    us.setInt(6, usuario_vicagreDao.max() + 1);
                    us.setString(7, base_model.getUsuario_vicagre().getCod_sistema() + "-" + base_model.getUsuario_vicagre().getUsuario_cod_usuario() + "-" + usuarioDao.datoSubServicio(base_model.getUsuario_vicagre().getUsuario_cod_usuario()));

                    ResultSet resultSet_us = us.executeQuery();

                    connection.commit();
                    res = aux;
                    //Log_Event
// LOG, VER SI SE NECESITA
                    
                    if (res != 0) {
                        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                        String ipAddress = request.getHeader("X-FORWARDED-FOR");
                        int respuesta = 0;
                        System.out.println("ENNNNTTTRRRRRA_1   : ");
                        if (ipAddress == null) {
                            ipAddress = request.getRemoteAddr();
                        }
                        System.out.println("ipAddress:" + ipAddress);
                        Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Insertar : ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                        respuesta = logDao.insert(1, log);
                        if (respuesta != 0) {
                            //int sss = insert_log(base_modellog, respuesta, 1);
                            //System.out.println("INSERT LOG :" + sss);
                            System.out.println("ENTRA_1   : ");
                        }

                    }

                } else {
                    connection.setAutoCommit(false);
                    CallableStatement cs = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    cs.setInt(1, num);
                    cs.setInt(2, personaDao.max() + 1);
                    cs.setString(3, base_model.getPersonaVictima().getNombre());
                    cs.setString(4, base_model.getPersonaVictima().getPaterno());
                    cs.setString(5, base_model.getPersonaVictima().getMaterno());
                    cs.setString(6, base_model.getPersonaVictima().getAp_casada());
                    cs.setString(7, base_model.getPersonaVictima().getNumero());
                    cs.setInt(8, base_model.getPersonaVictima().getCodigo_documento());
                    cs.setString(9, base_model.getPersonaVictima().getSexo());
                    cs.setInt(10, base_model.getPersonaVictima().getLugar_nac());
                    cs.setString(11, base_model.getPersonaVictima().getEsp_lugar_nac());
                    cs.setDate(12, new java.sql.Date(base_model.getPersonaVictima().getFecha_nac().getTime()));
                    cs.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet = cs.executeQuery();

                    CallableStatement pv = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max() + 1);
                    pv.setInt(1, num);
                    pv.setInt(2, persona_VariablesDao.max() + 1);
                    pv.setInt(3, personaDao.max() + 1);
                    pv.setInt(4, base_model.getPersonaVariableVictima().getEdad());
                    pv.setInt(5, base_model.getPersonaVariableVictima().getEst_civil());
                    pv.setInt(6, base_model.getPersonaVariableVictima().getNro_hijos());
                    pv.setInt(7, base_model.getPersonaVariableVictima().getGestacion_h());
                    pv.setInt(8, base_model.getPersonaVariableVictima().getNum_miembros_fam());
                    pv.setBoolean(9, base_model.getPersonaVariableVictima().isMunicipio());
                    pv.setString(10, base_model.getPersonaVariableVictima().getEsp_municipio());
                    pv.setBoolean(11, base_model.getPersonaVariableVictima().isArea());
                    pv.setString(12, base_model.getPersonaVariableVictima().getEsp_area());
                    pv.setInt(13, base_model.getPersonaVariableVictima().getVivienda());
                    pv.setInt(14, base_model.getPersonaVariableVictima().getNivel_inst());
                    pv.setBoolean(15, base_model.getPersonaVariableVictima().isCondicion_act());
                    pv.setInt(16, base_model.getPersonaVariableVictima().getOcupacion());
                    pv.setString(17, base_model.getPersonaVariableVictima().getEsp_ocupacion());
                    pv.setString(18, base_model.getPersonaVariableVictima().getCargo());
                    pv.setInt(19, base_model.getPersonaVariableVictima().getIngre_economico());
                    pv.setBigDecimal(20, base_model.getPersonaVariableVictima().getMonto_aprox_bs());
                    pv.setBigDecimal(21, base_model.getPersonaVariableVictima().getAporte_familiar_bs());
                    pv.setString(22, base_model.getPersonaVariableVictima().getIdiomas());
                    pv.setString(23, base_model.getPersonaVariableVictima().getEsp_idioma());
                    pv.setBoolean(24, base_model.getPersonaVariableVictima().isEtnia());
                    pv.setString(25, base_model.getPersonaVariableVictima().getEsp_etnia());
                    pv.setBoolean(26, base_model.getPersonaVariableVictima().isLgbti());
                    pv.setBoolean(27, base_model.getPersonaVariableVictima().isPers_discapacidad());
                    pv.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet_pv = pv.executeQuery();

                    CallableStatement cs1 = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    cs1.setInt(1, num);
                    cs1.setInt(2, personaDao.max() + 2);
                    cs1.setString(3, base_model.getPersonaAgresor().getNombre());
                    cs1.setString(4, base_model.getPersonaAgresor().getPaterno());
                    cs1.setString(5, base_model.getPersonaAgresor().getMaterno());
                    cs1.setString(6, base_model.getPersonaAgresor().getAp_casada());
                    cs1.setString(7, base_model.getPersonaAgresor().getNumero());
                    cs1.setInt(8, base_model.getPersonaAgresor().getCodigo_documento());
                    cs1.setString(9, base_model.getPersonaAgresor().getSexo());
                    cs1.setInt(10, base_model.getPersonaAgresor().getLugar_nac());
                    cs1.setString(11, base_model.getPersonaAgresor().getEsp_lugar_nac());
                    cs1.setDate(12, new java.sql.Date(base_model.getPersonaAgresor().getFecha_nac().getTime()));
                    cs1.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet_cs1 = cs1.executeQuery();

                    CallableStatement pv1 = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max() + 1);
                    pv1.setInt(1, num);
                    pv1.setInt(2, persona_VariablesDao.max() + 2);
                    pv1.setInt(3, personaDao.max() + 2);
                    pv1.setInt(4, base_model.getPersonaVariableAgresor().getEdad());
                    pv1.setInt(5, base_model.getPersonaVariableAgresor().getEst_civil());
                    pv1.setInt(6, base_model.getPersonaVariableAgresor().getNro_hijos());
                    pv1.setInt(7, base_model.getPersonaVariableAgresor().getGestacion_h());
                    pv1.setInt(8, base_model.getPersonaVariableAgresor().getNum_miembros_fam());
                    pv1.setBoolean(9, base_model.getPersonaVariableAgresor().isMunicipio());
                    pv1.setString(10, base_model.getPersonaVariableAgresor().getEsp_municipio());
                    pv1.setBoolean(11, base_model.getPersonaVariableAgresor().isArea());
                    pv1.setString(12, base_model.getPersonaVariableAgresor().getEsp_area());
                    pv1.setInt(13, base_model.getPersonaVariableAgresor().getVivienda());
                    pv1.setInt(14, base_model.getPersonaVariableAgresor().getNivel_inst());
                    pv1.setBoolean(15, base_model.getPersonaVariableAgresor().isCondicion_act());
                    pv1.setInt(16, base_model.getPersonaVariableAgresor().getOcupacion());
                    pv1.setString(17, base_model.getPersonaVariableAgresor().getEsp_ocupacion());
                    pv1.setString(18, base_model.getPersonaVariableAgresor().getCargo());
                    pv1.setInt(19, base_model.getPersonaVariableAgresor().getIngre_economico());
                    pv1.setBigDecimal(20, base_model.getPersonaVariableAgresor().getMonto_aprox_bs());
                    pv1.setBigDecimal(21, base_model.getPersonaVariableAgresor().getAporte_familiar_bs());
                    pv1.setString(22, base_model.getPersonaVariableAgresor().getIdiomas());
                    pv1.setString(23, base_model.getPersonaVariableAgresor().getEsp_idioma());
                    pv1.setBoolean(24, base_model.getPersonaVariableAgresor().isEtnia());
                    pv1.setString(25, base_model.getPersonaVariableAgresor().getEsp_etnia());
                    pv1.setBoolean(26, base_model.getPersonaVariableAgresor().isLgbti());
                    pv1.setBoolean(27, base_model.getPersonaVariableAgresor().isPers_discapacidad());
                    pv1.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet_pv1 = pv1.executeQuery();

                    CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); //la funcion original sp_operaciones_victima_agresor(?...18)
                    va.setInt(1, num);
                    va.setInt(2, victima_agresorDao.max() + 1);
                    va.setString(3, base_model.getVictima_agresor().getTestimonio());
                    va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                    va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                    va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                    va.setBoolean(7, base_model.getVictima_agresor().isArea());
                    va.setString(8, base_model.getVictima_agresor().getEsp_area());
                    va.setInt(9, base_model.getVictima_agresor().getParentesco());
                    va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                    va.setString(11, base_model.getVictima_agresor().getTviolencia());
                    va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                    va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                    va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                    va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                    va.setInt(16, base_model.getVictima_agresor().getFrec_consumo_alcohol());
                    va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                    va.setInt(18, base_model.getVictima_agresor().getFrec_consumo_drogas());
                    /*INICIO Preguntas Alertas*/
                    va.setInt(19, base_model.getVictima_agresor().getPreg1());
                    va.setInt(20, base_model.getVictima_agresor().getPreg2());
                    va.setInt(21, base_model.getVictima_agresor().getPreg3());
                    va.setInt(22, base_model.getVictima_agresor().getPreg4());
                    va.setInt(23, base_model.getVictima_agresor().getPreg5());
                    va.setInt(24, base_model.getVictima_agresor().getPreg6());
                    va.setInt(25, base_model.getVictima_agresor().getPreg7());
                    va.setInt(26, base_model.getVictima_agresor().getPreg8());
                    va.setInt(27, base_model.getVictima_agresor().getPreg9());
                    va.setInt(28, base_model.getVictima_agresor().getPreg10());
                    va.setInt(29, base_model.getVictima_agresor().getPreg11());
                    va.setInt(30, base_model.getVictima_agresor().getPreg12());
                    va.setInt(31, base_model.getVictima_agresor().getPreg13());
                    va.setInt(32, base_model.getVictima_agresor().getPreg14());
                    va.setInt(33, base_model.getVictima_agresor().getPreg15());
                    va.setInt(34, base_model.getVictima_agresor().getPreg16());
                    va.setInt(35, base_model.getVictima_agresor().getPreg17());
                    va.setInt(36, base_model.getVictima_agresor().getPreg18());
                    va.setInt(37, base_model.getVictima_agresor().getPreg19());
                    va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
                    va.setInt(39, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                    /*FIN Preguntas Alertas*/    
                    ResultSet resultSet_va = va.executeQuery();

                    CallableStatement vp = connection.prepareCall("select coco_operaciones_vicagre_persona(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    vp.setInt(1, num);
                    vp.setInt(2, vicAgre_personaDao.max() + 1);
                    vp.setInt(3, victima_agresorDao.max() + 1);
                    vp.setInt(4, personaDao.max() + 2);
                    //vp.setInt(4, 1);
                    vp.setInt(5, personaDao.max() + 1);
                    vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                    vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                    vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                    vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                    vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                    vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                    vp.setInt(12, persona_VariablesDao.max() + 1);
                    vp.setInt(13, persona_VariablesDao.max() + 2);
                    vp.setInt(14, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet_vp = vp.executeQuery();

                    CallableStatement us = connection.prepareCall("select sp_operaciones_usuario_vicagre(?,?,?,?,?,?,?)");
                    aux = victima_agresorDao.max() + 1;

                    us.setInt(1, num);
                    us.setInt(2, base_model.getUsuario_vicagre().getId_usuario_vicagre());
                    us.setInt(3, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                    us.setInt(4, victima_agresorDao.max() + 1);
                    us.setDate(5, new java.sql.Date(base_model.getUsuario_vicagre().getFecha_reg().getTime()));
                    //us.setDate(5, (java.sql.Date) usuario_vicagre1.getFecha_reg());
                    us.setInt(6, usuario_vicagreDao.max() + 1);
                    us.setString(7, base_model.getUsuario_vicagre().getCod_sistema() + "-" + base_model.getUsuario_vicagre().getUsuario_cod_usuario() + "-" + usuarioDao.datoSubServicio(base_model.getUsuario_vicagre().getUsuario_cod_usuario()));

                    ResultSet resultSet_us = us.executeQuery();

                    connection.commit();
                    res = aux;
                    //Log_Event
// LOG, VER SI SE NECESITA
                    
                    if (res != 0) {
                        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                        String ipAddress = request.getHeader("X-FORWARDED-FOR");
                        int respuesta = 0;
                        System.out.println("ENNNNTTTRRRRRA_2   : ");
                        if (ipAddress == null) {
                            ipAddress = request.getRemoteAddr();
                        }
                        System.out.println("ipAddress:" + ipAddress);
                        Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Insertar : ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                        respuesta = logDao.insert(1, log);
                        System.out.println("zzzzzzzzzzzzzzzzz : " + respuesta);
                        if (respuesta != 0) {
                            //int sss = insert_log(base_modellog, respuesta, 1);
                            //System.out.println("INSERT LOG :" + sss);
                            System.out.println("ENTRA_2   : ");
                        }
                    }
                }

            } else {

                if (existe == 0) {
                    System.out.println("entraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa : ");
                    connection.setAutoCommit(false);
                    CallableStatement cs = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    cs.setInt(1, num);
                    cs.setInt(2, base_model.getPersonaVictima().getId_persona());
                    cs.setString(3, base_model.getPersonaVictima().getNombre());
                    cs.setString(4, base_model.getPersonaVictima().getPaterno());
                    cs.setString(5, base_model.getPersonaVictima().getMaterno());
                    cs.setString(6, base_model.getPersonaVictima().getAp_casada());
                    cs.setString(7, base_model.getPersonaVictima().getNumero());
                    cs.setInt(8, base_model.getPersonaVictima().getCodigo_documento());
                    cs.setString(9, base_model.getPersonaVictima().getSexo());
                    cs.setInt(10, base_model.getPersonaVictima().getLugar_nac());
                    cs.setString(11, base_model.getPersonaVictima().getEsp_lugar_nac());
                    cs.setDate(12, new java.sql.Date(base_model.getPersonaVictima().getFecha_nac().getTime()));
                    cs.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet = cs.executeQuery();

                    CallableStatement pv = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    pv.setInt(1, num);
                    // pv.setInt(2, persona_variablesDao.max()+1);
                    System.out.println("Id PERSONA VARIABLES : " + base_model.getPersonaVariableVictima().getId_pervar());

                    pv.setInt(2, base_model.getPersonaVariableVictima().getId_pervar());
                    pv.setInt(3, base_model.getPersonaVariableVictima().getId_persona());
                    pv.setInt(4, base_model.getPersonaVariableVictima().getEdad());
                    pv.setInt(5, base_model.getPersonaVariableVictima().getEst_civil());
                    pv.setInt(6, base_model.getPersonaVariableVictima().getNro_hijos());
                    pv.setInt(7, base_model.getPersonaVariableVictima().getGestacion_h());
                    pv.setInt(8, base_model.getPersonaVariableVictima().getNum_miembros_fam());
                    pv.setBoolean(9, base_model.getPersonaVariableVictima().isMunicipio());
                    pv.setString(10, base_model.getPersonaVariableVictima().getEsp_municipio());
                    pv.setBoolean(11, base_model.getPersonaVariableVictima().isArea());
                    pv.setString(12, base_model.getPersonaVariableVictima().getEsp_area());
                    pv.setInt(13, base_model.getPersonaVariableVictima().getVivienda());
                    pv.setInt(14, base_model.getPersonaVariableVictima().getNivel_inst());
                    pv.setBoolean(15, base_model.getPersonaVariableVictima().isCondicion_act());
                    pv.setInt(16, base_model.getPersonaVariableVictima().getOcupacion());
                    pv.setString(17, base_model.getPersonaVariableVictima().getEsp_ocupacion());
                    pv.setString(18, base_model.getPersonaVariableVictima().getCargo());
                    pv.setInt(19, base_model.getPersonaVariableVictima().getIngre_economico());
                    pv.setBigDecimal(20, base_model.getPersonaVariableVictima().getMonto_aprox_bs());
                    pv.setBigDecimal(21, base_model.getPersonaVariableVictima().getAporte_familiar_bs());
                    pv.setString(22, base_model.getPersonaVariableVictima().getIdiomas());
                    pv.setString(23, base_model.getPersonaVariableVictima().getEsp_idioma());
                    pv.setBoolean(24, base_model.getPersonaVariableVictima().isEtnia());
                    pv.setString(25, base_model.getPersonaVariableVictima().getEsp_etnia());
                    pv.setBoolean(26, base_model.getPersonaVariableVictima().isLgbti());
                    pv.setBoolean(27, base_model.getPersonaVariableVictima().isPers_discapacidad());
                    pv.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet_pv = pv.executeQuery();
                    System.out.println("PERSONAAAA VARIABLES AGRESOR1111111111 : " + base_model.getPersonaAgresor().getId_persona());
                    if (base_model.getPersonaAgresor().getId_persona() == 0) {
                        //genera codigoruv
                        UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                        Base_modelDao dao1 = new Base_modelDaoImpl();
                        System.out.println("valor : " + obj.getUsuario().getCod_usuario());

                        base_model.getUsuario_vicagre().setUsuario_cod_usuario(obj.getUsuario().getCod_usuario());
                        base_model.getUsuario_vicagre().setCod_sistema(new SimpleDateFormat("MMddyyyyHHmmss").format(new Date()));

                        System.out.println("entraAÑADe");
                        CallableStatement cs1 = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        cs1.setInt(1, 1);
                        cs1.setInt(2, personaDao.max() + 1);
                        System.out.println("ID PERSONAAAA: " + personaDao.max() + 1);
                        cs1.setString(3, base_model.getPersonaAgresor().getNombre());
                        cs1.setString(4, base_model.getPersonaAgresor().getPaterno());
                        cs1.setString(5, base_model.getPersonaAgresor().getMaterno());
                        cs1.setString(6, base_model.getPersonaAgresor().getAp_casada());
                        cs1.setString(7, base_model.getPersonaAgresor().getNumero());
                        cs1.setInt(8, base_model.getPersonaAgresor().getCodigo_documento());
                        cs1.setString(9, base_model.getPersonaAgresor().getSexo());
                        cs1.setInt(10, base_model.getPersonaAgresor().getLugar_nac());
                        cs1.setString(11, base_model.getPersonaAgresor().getEsp_lugar_nac());
                        cs1.setDate(12, new java.sql.Date(base_model.getPersonaAgresor().getFecha_nac().getTime()));
                        cs1.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                        ResultSet resultSet_cs1 = cs1.executeQuery();
                        System.out.println("dddddddddddddd 1111111111111111: ");
                        CallableStatement pv1 = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        System.out.println("PERSONA VARIABLES agresor  :" + personaDao.max() + 1);
                        pv1.setInt(1, 1);
                        pv1.setInt(2, persona_VariablesDao.max() + 1);
                        pv1.setInt(3, personaDao.max() + 1);
                        pv1.setInt(4, base_model.getPersonaVariableAgresor().getEdad());
                        pv1.setInt(5, base_model.getPersonaVariableAgresor().getEst_civil());
                        pv1.setInt(6, base_model.getPersonaVariableAgresor().getNro_hijos());
                        pv1.setInt(7, base_model.getPersonaVariableAgresor().getGestacion_h());
                        pv1.setInt(8, base_model.getPersonaVariableAgresor().getNum_miembros_fam());
                        pv1.setBoolean(9, base_model.getPersonaVariableAgresor().isMunicipio());
                        pv1.setString(10, base_model.getPersonaVariableAgresor().getEsp_municipio());
                        pv1.setBoolean(11, base_model.getPersonaVariableAgresor().isArea());
                        pv1.setString(12, base_model.getPersonaVariableAgresor().getEsp_area());
                        pv1.setInt(13, base_model.getPersonaVariableAgresor().getVivienda());
                        pv1.setInt(14, base_model.getPersonaVariableAgresor().getNivel_inst());
                        pv1.setBoolean(15, base_model.getPersonaVariableAgresor().isCondicion_act());
                        pv1.setInt(16, base_model.getPersonaVariableAgresor().getOcupacion());
                        pv1.setString(17, base_model.getPersonaVariableAgresor().getEsp_ocupacion());
                        pv1.setString(18, base_model.getPersonaVariableAgresor().getCargo());
                        pv1.setInt(19, base_model.getPersonaVariableAgresor().getIngre_economico());
                        pv1.setBigDecimal(20, base_model.getPersonaVariableAgresor().getMonto_aprox_bs());
                        pv1.setBigDecimal(21, base_model.getPersonaVariableAgresor().getAporte_familiar_bs());
                        pv1.setString(22, base_model.getPersonaVariableAgresor().getIdiomas());
                        pv1.setString(23, base_model.getPersonaVariableAgresor().getEsp_idioma());
                        pv1.setBoolean(24, base_model.getPersonaVariableAgresor().isEtnia());
                        pv1.setString(25, base_model.getPersonaVariableAgresor().getEsp_etnia());
                        pv1.setBoolean(26, base_model.getPersonaVariableAgresor().isLgbti());
                        pv1.setBoolean(27, base_model.getPersonaVariableAgresor().isPers_discapacidad());
                        pv1.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                        ResultSet resultSet_pv1 = pv1.executeQuery();

                        System.out.println("TIPOSSSSSS DE VIOLENCIAS : " + base_model.getVictima_agresor().getTviolencia());

                        aux = victima_agresorDao.max();
                        CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//la funcion original sp_operaciones_victima_agresor(?...18)
                        System.out.println("victima agresor id : " + victima_agresorDao.max() + 1);
                        va.setInt(1, 1);
                        va.setInt(2, victima_agresorDao.max() + 1);
                        va.setString(3, base_model.getVictima_agresor().getTestimonio());
                        va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                        va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                        va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                        va.setBoolean(7, base_model.getVictima_agresor().isArea());
                        va.setString(8, base_model.getVictima_agresor().getEsp_area());
                        va.setInt(9, base_model.getVictima_agresor().getParentesco());
                        va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                        va.setString(11, base_model.getVictima_agresor().getTviolencia());
                        va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                        va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                        va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                        va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                        va.setInt(16, base_model.getVictima_agresor().getFrec_consumo_alcohol());
                        va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                        va.setInt(18, base_model.getVictima_agresor().getFrec_consumo_drogas());
                        /*INICIO Preguntas Alertas*/
                        va.setInt(19, base_model.getVictima_agresor().getPreg1());
                        va.setInt(20, base_model.getVictima_agresor().getPreg2());
                        va.setInt(21, base_model.getVictima_agresor().getPreg3());
                        va.setInt(22, base_model.getVictima_agresor().getPreg4());
                        va.setInt(23, base_model.getVictima_agresor().getPreg5());
                        va.setInt(24, base_model.getVictima_agresor().getPreg6());
                        va.setInt(25, base_model.getVictima_agresor().getPreg7());
                        va.setInt(26, base_model.getVictima_agresor().getPreg8());
                        va.setInt(27, base_model.getVictima_agresor().getPreg9());
                        va.setInt(28, base_model.getVictima_agresor().getPreg10());
                        va.setInt(29, base_model.getVictima_agresor().getPreg11());
                        va.setInt(30, base_model.getVictima_agresor().getPreg12());
                        va.setInt(31, base_model.getVictima_agresor().getPreg13());
                        va.setInt(32, base_model.getVictima_agresor().getPreg14());
                        va.setInt(33, base_model.getVictima_agresor().getPreg15());
                        va.setInt(34, base_model.getVictima_agresor().getPreg16());
                        va.setInt(35, base_model.getVictima_agresor().getPreg17());
                        va.setInt(36, base_model.getVictima_agresor().getPreg18());
                        va.setInt(37, base_model.getVictima_agresor().getPreg19());
                        va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
                        va.setInt(39, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                        /*FIN Preguntas Alertas*/     
                        ResultSet resultSet_va = va.executeQuery();

                        CallableStatement vp = connection.prepareCall("select coco_operaciones_vicagre_persona(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        System.out.println("id vic agre");
                        vp.setInt(1, 1);
                        vp.setInt(2, vicAgre_personaDao.max() + 1);
                        vp.setInt(3, victima_agresorDao.max() + 1);
                        //vp.setInt(4, personaDao.max() + 2);
                        vp.setInt(4, personaDao.max() + 1);
                        vp.setInt(5, base_model.getVicAgre_persona().getPersona_id_persona_victima());
                        vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                        vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                        vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                        vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                        vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                        vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                        vp.setInt(12, base_model.getPersonaVariableVictima().getId_pervar());
                        vp.setInt(13, persona_VariablesDao.max() + 1);
                        vp.setInt(14, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                        ResultSet resultSet_vp = vp.executeQuery();

                        CallableStatement us = connection.prepareCall("select sp_operaciones_usuario_vicagre(?,?,?,?,?,?,?)");
                        aux = victima_agresorDao.max() + 1;
                        System.out.println("victimaAGRESOR : " + victima_agresorDao.max() + 1);
                        System.out.println("Usuario Victima Agresor : " + base_model.getUsuario_vicagre().getId_usuario_vicagre());
                        us.setInt(1, 1);
                        us.setInt(2, base_model.getUsuario_vicagre().getId_usuario_vicagre());
                        us.setInt(3, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                        us.setInt(4, victima_agresorDao.max() + 1);
                        us.setDate(5, new java.sql.Date(base_model.getUsuario_vicagre().getFecha_reg().getTime()));
                        //us.setDate(5, (java.sql.Date) usuario_vicagre1.getFecha_reg());
                        us.setInt(6, usuario_vicagreDao.max() + 1);
                        us.setString(7, base_model.getUsuario_vicagre().getCod_sistema() + "-" + base_model.getUsuario_vicagre().getUsuario_cod_usuario() + "-" + usuarioDao.datoSubServicio(base_model.getUsuario_vicagre().getUsuario_cod_usuario()));

                        ResultSet resultSet_us = us.executeQuery();

                        connection.commit();
// LOG, VER SI SE NECESITA
                        
                        res = aux;

                        if (res != 0) {
                            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                            String ipAddress = request.getHeader("X-FORWARDED-FOR");
                            int respuesta = 0;
                            if (ipAddress == null) {
                                ipAddress = request.getRemoteAddr();
                            }
                            System.out.println("ENNNNTTTRRRRRA_3   : ");

                            System.out.println("ipAddress:" + ipAddress);
                            Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Modificar, adiciona agresor: ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                            //logDao.insert(1, log);
                            respuesta = logDao.insert(1, log);
                            if (respuesta != 0) {
                                //int sss = insert_log(base_modellog, respuesta, 1);
                                //System.out.println("INSERT LOG :" + sss);
                                System.out.println("ENTRA_3   : ");
                            }

                        }
                    } else {
                        System.out.println("MOdificacion");
                        CallableStatement cs1 = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        cs1.setInt(1, num);
                        cs1.setInt(2, base_model.getPersonaAgresor().getId_persona());
                        cs1.setString(3, base_model.getPersonaAgresor().getNombre());
                        cs1.setString(4, base_model.getPersonaAgresor().getPaterno());
                        cs1.setString(5, base_model.getPersonaAgresor().getMaterno());
                        cs1.setString(6, base_model.getPersonaAgresor().getAp_casada());
                        cs1.setString(7, base_model.getPersonaAgresor().getNumero());
                        cs1.setInt(8, base_model.getPersonaAgresor().getCodigo_documento());
                        cs1.setString(9, base_model.getPersonaAgresor().getSexo());
                        cs1.setInt(10, base_model.getPersonaAgresor().getLugar_nac());
                        cs1.setString(11, base_model.getPersonaAgresor().getEsp_lugar_nac());
                        cs1.setDate(12, new java.sql.Date(base_model.getPersonaAgresor().getFecha_nac().getTime()));
                        cs1.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                        ResultSet resultSet_cs1 = cs1.executeQuery();

                        CallableStatement pv1 = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pv1.setInt(1, num);
                        // pv.setInt(2, persona_variablesDao.max()+1);
                        System.out.println("Id PERSONA VARIABLES_2 : " + base_model.getPersonaVariableAgresor().getId_pervar());
                        pv1.setInt(2, base_model.getPersonaVariableAgresor().getId_pervar());
                        pv1.setInt(3, base_model.getPersonaVariableAgresor().getId_persona());
                        pv1.setInt(4, base_model.getPersonaVariableAgresor().getEdad());
                        pv1.setInt(5, base_model.getPersonaVariableAgresor().getEst_civil());
                        pv1.setInt(6, base_model.getPersonaVariableAgresor().getNro_hijos());
                        pv1.setInt(7, base_model.getPersonaVariableAgresor().getGestacion_h());
                        pv1.setInt(8, base_model.getPersonaVariableAgresor().getNum_miembros_fam());
                        pv1.setBoolean(9, base_model.getPersonaVariableAgresor().isMunicipio());
                        pv1.setString(10, base_model.getPersonaVariableAgresor().getEsp_municipio());
                        pv1.setBoolean(11, base_model.getPersonaVariableAgresor().isArea());
                        pv1.setString(12, base_model.getPersonaVariableAgresor().getEsp_area());
                        pv1.setInt(13, base_model.getPersonaVariableAgresor().getVivienda());
                        pv1.setInt(14, base_model.getPersonaVariableAgresor().getNivel_inst());
                        pv1.setBoolean(15, base_model.getPersonaVariableAgresor().isCondicion_act());
                        pv1.setInt(16, base_model.getPersonaVariableAgresor().getOcupacion());
                        pv1.setString(17, base_model.getPersonaVariableAgresor().getEsp_ocupacion());
                        pv1.setString(18, base_model.getPersonaVariableAgresor().getCargo());
                        pv1.setInt(19, base_model.getPersonaVariableAgresor().getIngre_economico());
                        pv1.setBigDecimal(20, base_model.getPersonaVariableAgresor().getMonto_aprox_bs());
                        pv1.setBigDecimal(21, base_model.getPersonaVariableAgresor().getAporte_familiar_bs());
                        pv1.setString(22, base_model.getPersonaVariableAgresor().getIdiomas());
                        pv1.setString(23, base_model.getPersonaVariableAgresor().getEsp_idioma());
                        pv1.setBoolean(24, base_model.getPersonaVariableAgresor().isEtnia());
                        pv1.setString(25, base_model.getPersonaVariableAgresor().getEsp_etnia());
                        pv1.setBoolean(26, base_model.getPersonaVariableAgresor().isLgbti());
                        pv1.setBoolean(27, base_model.getPersonaVariableAgresor().isPers_discapacidad());
                        pv1.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                        ResultSet resultSet_pv1 = pv1.executeQuery();

                        System.out.println("TIPOSSSSSS DE VIOLENCIAS1111111111 : " + base_model.getVictima_agresor().getTviolencia());

                        aux = victima_agresorDao.max();
                        CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//la funcion original sp_operaciones_victima_agresor(?...18)
                        va.setInt(1, num);
                        va.setInt(2, base_model.getVictima_agresor().getId_vicagre());
                        va.setString(3, base_model.getVictima_agresor().getTestimonio());
                        va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                        va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                        va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                        va.setBoolean(7, base_model.getVictima_agresor().isArea());
                        va.setString(8, base_model.getVictima_agresor().getEsp_area());
                        va.setInt(9, base_model.getVictima_agresor().getParentesco());
                        va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                        va.setString(11, base_model.getVictima_agresor().getTviolencia());
                        va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                        va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                        va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                        va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                        va.setInt(16, base_model.getVictima_agresor().getFrec_consumo_alcohol());
                        va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                        va.setInt(18, base_model.getVictima_agresor().getFrec_consumo_drogas());
/*INICIO Preguntas Alertas*/
                        va.setInt(19, base_model.getVictima_agresor().getPreg1());
                        va.setInt(20, base_model.getVictima_agresor().getPreg2());
                        va.setInt(21, base_model.getVictima_agresor().getPreg3());
                        va.setInt(22, base_model.getVictima_agresor().getPreg4());
                        va.setInt(23, base_model.getVictima_agresor().getPreg5());
                        va.setInt(24, base_model.getVictima_agresor().getPreg6());
                        va.setInt(25, base_model.getVictima_agresor().getPreg7());
                        va.setInt(26, base_model.getVictima_agresor().getPreg8());
                        va.setInt(27, base_model.getVictima_agresor().getPreg9());
                        va.setInt(28, base_model.getVictima_agresor().getPreg10());
                        va.setInt(29, base_model.getVictima_agresor().getPreg11());
                        va.setInt(30, base_model.getVictima_agresor().getPreg12());
                        va.setInt(31, base_model.getVictima_agresor().getPreg13());
                        va.setInt(32, base_model.getVictima_agresor().getPreg14());
                        va.setInt(33, base_model.getVictima_agresor().getPreg15());
                        va.setInt(34, base_model.getVictima_agresor().getPreg16());
                        va.setInt(35, base_model.getVictima_agresor().getPreg17());
                        va.setInt(36, base_model.getVictima_agresor().getPreg18());
                        va.setInt(37, base_model.getVictima_agresor().getPreg19());
                        va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
                        va.setInt(39, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                        /*FIN Preguntas Alertas*/     
                        ResultSet resultSet_va = va.executeQuery();

                        CallableStatement vp = connection.prepareCall("select coco_operaciones_vicagre_persona(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        vp.setInt(1, num);
                        vp.setInt(2, base_model.getVicAgre_persona().getId_vicAgre_persona());
                        vp.setInt(3, base_model.getVicAgre_persona().getVictima_agresor_id_vicagre());
                        //vp.setInt(4, personaDao.max() + 2);
                        vp.setInt(4, base_model.getVicAgre_persona().getPersona_id_persona_agresor());
                        vp.setInt(5, base_model.getVicAgre_persona().getPersona_id_persona_victima());
                        vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                        vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                        vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                        vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                        vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                        vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                        vp.setInt(12, base_model.getPersonaVariableVictima().getId_pervar());
                        vp.setInt(13, base_model.getPersonaVariableAgresor().getId_pervar());
                        vp.setInt(14, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                        ResultSet resultSet_vp = vp.executeQuery();

                        aux = victima_agresorDao.max() + 1;
                        System.out.println("victimaAGRESOR : " + victima_agresorDao.max() + 1);
                        System.out.println("Usuario Victima Agresor : " + base_model.getUsuario_vicagre().getId_usuario_vicagre());

                        System.out.println("RESSSSS : " + aux);
                        connection.commit();
                        res = aux;
                        System.out.println("RESSSSS1 : " + res);
// LOG, VER SI SE NECESITA        
                        if (res != 0) {
                            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                            String ipAddress = request.getHeader("X-FORWARDED-FOR");
                            int respuesta = 0;
                            if (ipAddress == null) {
                                ipAddress = request.getRemoteAddr();
                            }
                            System.out.println("ENNNNTTTRRRRRA_4   : ");

                            System.out.println("ipAddress:" + ipAddress);
                            Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Se Modifico : ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");

                            respuesta = logDao.insert(1, log);
                            if (respuesta != 0) {
                             //   int sss = insert_log(base_modellog, respuesta, 1);
                             //  System.out.println("INSERT LOG :" + sss);
                                System.out.println("ENTRA_4   : ");
                            }
                        }
                    }
                } else {
                    //existe = 1
                    System.out.println("ENNNNNN : ");
                    connection.setAutoCommit(false);
                    CallableStatement cs = connection.prepareCall("select coc_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    cs.setInt(1, num);
                    cs.setInt(2, base_model.getPersonaVictima().getId_persona());
                    cs.setString(3, base_model.getPersonaVictima().getNombre());
                    cs.setString(4, base_model.getPersonaVictima().getPaterno());
                    cs.setString(5, base_model.getPersonaVictima().getMaterno());
                    cs.setString(6, base_model.getPersonaVictima().getAp_casada());
                    cs.setString(7, base_model.getPersonaVictima().getNumero());
                    cs.setInt(8, base_model.getPersonaVictima().getCodigo_documento());
                    cs.setString(9, base_model.getPersonaVictima().getSexo());
                    cs.setInt(10, base_model.getPersonaVictima().getLugar_nac());
                    cs.setString(11, base_model.getPersonaVictima().getEsp_lugar_nac());
                    cs.setDate(12, new java.sql.Date(base_model.getPersonaVictima().getFecha_nac().getTime()));
                    cs.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet = cs.executeQuery();

                    CallableStatement pv = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    pv.setInt(1, num);
                    // pv.setInt(2, persona_variablesDao.max()+1);
                    System.out.println("Id PERSONA VARIABLES : " + base_model.getPersonaVariableVictima().getId_pervar());

                    pv.setInt(2, base_model.getPersonaVariableVictima().getId_pervar());
                    pv.setInt(3, base_model.getPersonaVariableVictima().getId_persona());
                    pv.setInt(4, base_model.getPersonaVariableVictima().getEdad());
                    pv.setInt(5, base_model.getPersonaVariableVictima().getEst_civil());
                    pv.setInt(6, base_model.getPersonaVariableVictima().getNro_hijos());
                    pv.setInt(7, base_model.getPersonaVariableVictima().getGestacion_h());
                    pv.setInt(8, base_model.getPersonaVariableVictima().getNum_miembros_fam());
                    pv.setBoolean(9, base_model.getPersonaVariableVictima().isMunicipio());
                    pv.setString(10, base_model.getPersonaVariableVictima().getEsp_municipio());
                    pv.setBoolean(11, base_model.getPersonaVariableVictima().isArea());
                    pv.setString(12, base_model.getPersonaVariableVictima().getEsp_area());
                    pv.setInt(13, base_model.getPersonaVariableVictima().getVivienda());
                    pv.setInt(14, base_model.getPersonaVariableVictima().getNivel_inst());
                    pv.setBoolean(15, base_model.getPersonaVariableVictima().isCondicion_act());
                    pv.setInt(16, base_model.getPersonaVariableVictima().getOcupacion());
                    pv.setString(17, base_model.getPersonaVariableVictima().getEsp_ocupacion());
                    pv.setString(18, base_model.getPersonaVariableVictima().getCargo());
                    pv.setInt(19, base_model.getPersonaVariableVictima().getIngre_economico());
                    pv.setBigDecimal(20, base_model.getPersonaVariableVictima().getMonto_aprox_bs());
                    pv.setBigDecimal(21, base_model.getPersonaVariableVictima().getAporte_familiar_bs());
                    pv.setString(22, base_model.getPersonaVariableVictima().getIdiomas());
                    pv.setString(23, base_model.getPersonaVariableVictima().getEsp_idioma());
                    pv.setBoolean(24, base_model.getPersonaVariableVictima().isEtnia());
                    pv.setString(25, base_model.getPersonaVariableVictima().getEsp_etnia());
                    pv.setBoolean(26, base_model.getPersonaVariableVictima().isLgbti());
                    pv.setBoolean(27, base_model.getPersonaVariableVictima().isPers_discapacidad());
                    pv.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    CallableStatement cs1 = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    cs1.setInt(1, num);
                    cs1.setInt(2, base_model.getPersonaAgresor().getId_persona());
                    System.out.println("ID PERSONAAAA: " + personaDao.max() + 1);
                    cs1.setString(3, base_model.getPersonaAgresor().getNombre());
                    cs1.setString(4, base_model.getPersonaAgresor().getPaterno());
                    cs1.setString(5, base_model.getPersonaAgresor().getMaterno());
                    cs1.setString(6, base_model.getPersonaAgresor().getAp_casada());
                    cs1.setString(7, base_model.getPersonaAgresor().getNumero());
                    cs1.setInt(8, base_model.getPersonaAgresor().getCodigo_documento());
                    cs1.setString(9, base_model.getPersonaAgresor().getSexo());
                    cs1.setInt(10, base_model.getPersonaAgresor().getLugar_nac());
                    cs1.setString(11, base_model.getPersonaAgresor().getEsp_lugar_nac());
                    cs1.setDate(12, new java.sql.Date(base_model.getPersonaAgresor().getFecha_nac().getTime()));
                    cs1.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet_cs1 = cs1.executeQuery();
                    System.out.println("dddddddddddddd 1111111111111111: ");
                    CallableStatement pv1 = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    System.out.println("PERSONA VARIABLES agresor  :" + personaDao.max() + 1);
                    pv1.setInt(1, 1);
                    pv1.setInt(2, persona_VariablesDao.max() + 1);
                    pv1.setInt(3, base_model.getPersonaAgresor().getId_persona());
                    pv1.setInt(4, base_model.getPersonaVariableAgresor().getEdad());
                    pv1.setInt(5, base_model.getPersonaVariableAgresor().getEst_civil());
                    pv1.setInt(6, base_model.getPersonaVariableAgresor().getNro_hijos());
                    pv1.setInt(7, base_model.getPersonaVariableAgresor().getGestacion_h());
                    pv1.setInt(8, base_model.getPersonaVariableAgresor().getNum_miembros_fam());
                    pv1.setBoolean(9, base_model.getPersonaVariableAgresor().isMunicipio());
                    pv1.setString(10, base_model.getPersonaVariableAgresor().getEsp_municipio());
                    pv1.setBoolean(11, base_model.getPersonaVariableAgresor().isArea());
                    pv1.setString(12, base_model.getPersonaVariableAgresor().getEsp_area());
                    pv1.setInt(13, base_model.getPersonaVariableAgresor().getVivienda());
                    pv1.setInt(14, base_model.getPersonaVariableAgresor().getNivel_inst());
                    pv1.setBoolean(15, base_model.getPersonaVariableAgresor().isCondicion_act());
                    pv1.setInt(16, base_model.getPersonaVariableAgresor().getOcupacion());
                    pv1.setString(17, base_model.getPersonaVariableAgresor().getEsp_ocupacion());
                    pv1.setString(18, base_model.getPersonaVariableAgresor().getCargo());
                    pv1.setInt(19, base_model.getPersonaVariableAgresor().getIngre_economico());
                    pv1.setBigDecimal(20, base_model.getPersonaVariableAgresor().getMonto_aprox_bs());
                    pv1.setBigDecimal(21, base_model.getPersonaVariableAgresor().getAporte_familiar_bs());
                    pv1.setString(22, base_model.getPersonaVariableAgresor().getIdiomas());
                    pv1.setString(23, base_model.getPersonaVariableAgresor().getEsp_idioma());
                    pv1.setBoolean(24, base_model.getPersonaVariableAgresor().isEtnia());
                    pv1.setString(25, base_model.getPersonaVariableAgresor().getEsp_etnia());
                    pv1.setBoolean(26, base_model.getPersonaVariableAgresor().isLgbti());
                    pv1.setBoolean(27, base_model.getPersonaVariableAgresor().isPers_discapacidad());
                    pv1.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                    ResultSet resultSet_pv1 = pv1.executeQuery();

                    System.out.println("TIPOSSSSSS DE VIOLENCIAS : " + base_model.getVictima_agresor().getTviolencia());

                    CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//la funcion original sp_operaciones_victima_agresor(?...18)
                    System.out.println("victima agresor id : " + victima_agresorDao.max() + 1);
                    va.setInt(1, 1);
                    va.setInt(2, victima_agresorDao.max() + 1);
                    va.setString(3, base_model.getVictima_agresor().getTestimonio());
                    va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                    va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                    va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                    va.setBoolean(7, base_model.getVictima_agresor().isArea());
                    va.setString(8, base_model.getVictima_agresor().getEsp_area());
                    va.setInt(9, base_model.getVictima_agresor().getParentesco());
                    va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                    va.setString(11, base_model.getVictima_agresor().getTviolencia());
                    va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                    va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                    va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                    va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                    va.setInt(16, base_model.getVictima_agresor().getFrec_consumo_alcohol());
                    va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                    va.setInt(18, base_model.getVictima_agresor().getFrec_consumo_drogas());
/*INICIO Preguntas Alertas*/
                    va.setInt(19, base_model.getVictima_agresor().getPreg1());
                    va.setInt(20, base_model.getVictima_agresor().getPreg2());
                    va.setInt(21, base_model.getVictima_agresor().getPreg3());
                    va.setInt(22, base_model.getVictima_agresor().getPreg4());
                    va.setInt(23, base_model.getVictima_agresor().getPreg5());
                    va.setInt(24, base_model.getVictima_agresor().getPreg6());
                    va.setInt(25, base_model.getVictima_agresor().getPreg7());
                    va.setInt(26, base_model.getVictima_agresor().getPreg8());
                    va.setInt(27, base_model.getVictima_agresor().getPreg9());
                    va.setInt(28, base_model.getVictima_agresor().getPreg10());
                    va.setInt(29, base_model.getVictima_agresor().getPreg11());
                    va.setInt(30, base_model.getVictima_agresor().getPreg12());
                    va.setInt(31, base_model.getVictima_agresor().getPreg13());
                    va.setInt(32, base_model.getVictima_agresor().getPreg14());
                    va.setInt(33, base_model.getVictima_agresor().getPreg15());
                    va.setInt(34, base_model.getVictima_agresor().getPreg16());
                    va.setInt(35, base_model.getVictima_agresor().getPreg17());
                    va.setInt(36, base_model.getVictima_agresor().getPreg18());
                    va.setInt(37, base_model.getVictima_agresor().getPreg19());
                    va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
                    va.setInt(39, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                    /*FIN Preguntas Alertas*/     ////////////////////////////////////////////////////////////////ME QUEDE ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA REVISARRRRR
                            
                    ResultSet resultSet_va = va.executeQuery();

                    CallableStatement vp = connection.prepareCall("select coco_operaciones_vicagre_persona(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    System.out.println("id vic agre");
                    vp.setInt(1, 1);
                    vp.setInt(2, vicAgre_personaDao.max() + 1);
                    vp.setInt(3, victima_agresorDao.max() + 1);
                    //vp.setInt(4, personaDao.max() + 2);
                    vp.setInt(4, base_model.getPersonaAgresor().getId_persona());
                    vp.setInt(5, base_model.getVicAgre_persona().getPersona_id_persona_victima());
                    vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                    vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                    vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                    vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                    vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                    vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                    vp.setInt(12, base_model.getPersonaVariableVictima().getId_pervar());
                    vp.setInt(13, persona_VariablesDao.max() + 1);
                    vp.setInt(14, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                    ResultSet resultSet_vp = vp.executeQuery();

                    aux = victima_agresorDao.max() + 1;

                    CallableStatement us = connection.prepareCall("select sp_operaciones_usuario_vicagre(?,?,?,?,?,?,?)");

                    System.out.println("victimaAGRESOR : " + victima_agresorDao.max() + 1);
                    System.out.println("Usuario Victima Agresor : " + base_model.getUsuario_vicagre().getId_usuario_vicagre());
                    us.setInt(1, 1);
                    us.setInt(2, base_model.getUsuario_vicagre().getId_usuario_vicagre());
                    us.setInt(3, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                    us.setInt(4, victima_agresorDao.max() + 1);
                    us.setDate(5, new java.sql.Date(base_model.getUsuario_vicagre().getFecha_reg().getTime()));
                    //us.setDate(5, (java.sql.Date) usuario_vicagre1.getFecha_reg());
                    us.setInt(6, usuario_vicagreDao.max() + 1);
                    System.out.println("codigoDELsistema : " + base_model.getUsuario_vicagre().getCod_sistema());
                    us.setString(7, base_model.getUsuario_vicagre().getCod_sistema() + "-" + base_model.getUsuario_vicagre().getUsuario_cod_usuario() + "-" + usuarioDao.datoSubServicio(base_model.getUsuario_vicagre().getUsuario_cod_usuario()));

                    ResultSet resultSet_us = us.executeQuery();

                    connection.commit();
                    res = aux;
// LOG, VER SI SE NECESITA
                    if (res != 0) {
                        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                        String ipAddress = request.getHeader("X-FORWARDED-FOR");
                        int respuesta = 0;
                        if (ipAddress == null) {
                            ipAddress = request.getRemoteAddr();
                        }
                        System.out.println("ENNNNTTTRRRRRA_5   : ");

                        System.out.println("ipAddress:" + ipAddress);
                        Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Modificar, adicionar agresor: ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                        respuesta = logDao.insert(1, log);
                        if (respuesta != 0) {
                            //int sss = insert_log(base_modellog, respuesta, 1);
                            //System.out.println("INSERT LOG :" + sss);
                            System.out.println("ENTRA_2   : ");
                        }

                    }

                }

            }
        } catch (SQLException e) {

            e.getMessage();
            try {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);

                Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Error Sql , Insertar : ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                logDao.insert(2, log);
                
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } catch (Exception e) {
            try {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);

                Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Error al Insertar , Insertar : ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                logDao.insert(2, log);

            } catch (Exception e1) {
                e1.getMessage();
            }
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

    @Override
    public int insert_vic(int num, Base_model base_model, int existe, int resp) {
        PersonaDao personaDao = new PersonaDaoImpl();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        Usuario_vicagreDao usuario_vicagreDao = new Usuario_vicagreDaoImpl();
        Victima_agresorDao victima_agresorDao = new Victima_agresorDaoImpl();
        VicAgre_personaDao vicAgre_personaDao = new VicAgre_personaDaoImpl();
        Persona_VariablesDao persona_VariablesDao = new Persona_VariablesDaoImpl();
        LogDao logDao = new LogDaoImpl();
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        Base_model base_modellog = new Base_model();
        base_modellog = base_model;
        try {
            //if (num == 1 && !(devcodper().equals("")) && existe == 1) {
            System.out.println("METODO CADENA_1 : " + devcodper());
            if (num == 1 && resp == 2 && existe == 1) {
                connection.setAutoCommit(false);
                System.out.println("dddddddddddddddddddddddd : " + devcodper());

                CallableStatement cs = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                cs.setInt(1, 2);
                System.out.println("PersonaVictima : " + base_model.getPersonaVictima().getId_persona());
                cs.setInt(2, base_model.getPersonaVictima().getId_persona());
                cs.setString(3, base_model.getPersonaVictima().getNombre());
                cs.setString(4, base_model.getPersonaVictima().getPaterno());
                cs.setString(5, base_model.getPersonaVictima().getMaterno());
                cs.setString(6, base_model.getPersonaVictima().getAp_casada());
                cs.setString(7, base_model.getPersonaVictima().getNumero());
                cs.setInt(8, base_model.getPersonaVictima().getCodigo_documento());
                cs.setString(9, base_model.getPersonaVictima().getSexo());
                cs.setInt(10, base_model.getPersonaVictima().getLugar_nac());
                cs.setString(11, base_model.getPersonaVictima().getEsp_lugar_nac());
                cs.setDate(12, new java.sql.Date(base_model.getPersonaVictima().getFecha_nac().getTime()));
                cs.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                        
                ResultSet resultSet = cs.executeQuery();

                CallableStatement pv = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max() + 1);
                pv.setInt(1, num);
                pv.setInt(2, persona_VariablesDao.max() + 1);
                pv.setInt(3, base_model.getPersonaVictima().getId_persona());
                pv.setInt(4, base_model.getPersonaVariableVictima().getEdad());
                pv.setInt(5, base_model.getPersonaVariableVictima().getEst_civil());
                pv.setInt(6, base_model.getPersonaVariableVictima().getNro_hijos());
                pv.setInt(7, base_model.getPersonaVariableVictima().getGestacion_h());
                pv.setInt(8, base_model.getPersonaVariableVictima().getNum_miembros_fam());
                pv.setBoolean(9, base_model.getPersonaVariableVictima().isMunicipio());
                pv.setString(10, base_model.getPersonaVariableVictima().getEsp_municipio());
                pv.setBoolean(11, base_model.getPersonaVariableVictima().isArea());
                pv.setString(12, base_model.getPersonaVariableVictima().getEsp_area());
                pv.setInt(13, base_model.getPersonaVariableVictima().getVivienda());
                pv.setInt(14, base_model.getPersonaVariableVictima().getNivel_inst());
                pv.setBoolean(15, base_model.getPersonaVariableVictima().isCondicion_act());
                pv.setInt(16, base_model.getPersonaVariableVictima().getOcupacion());
                pv.setString(17, base_model.getPersonaVariableVictima().getEsp_ocupacion());
                pv.setString(18, base_model.getPersonaVariableVictima().getCargo());
                pv.setInt(19, base_model.getPersonaVariableVictima().getIngre_economico());
                pv.setBigDecimal(20, base_model.getPersonaVariableVictima().getMonto_aprox_bs());
                pv.setBigDecimal(21, base_model.getPersonaVariableVictima().getAporte_familiar_bs());
                pv.setString(22, base_model.getPersonaVariableVictima().getIdiomas());
                pv.setString(23, base_model.getPersonaVariableVictima().getEsp_idioma());
                pv.setBoolean(24, base_model.getPersonaVariableVictima().isEtnia());
                pv.setString(25, base_model.getPersonaVariableVictima().getEsp_etnia());
                pv.setBoolean(26, base_model.getPersonaVariableVictima().isLgbti());
                pv.setBoolean(27, base_model.getPersonaVariableVictima().isPers_discapacidad());
                pv.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario()); // Usuario AUDIT, para auditoria en todos los que se vea .. Boris
                        
                ResultSet resultSet_pv = pv.executeQuery();

                CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//la funcion original sp_operaciones_victima_agresor(?...18)
                System.out.println("VICTIMAS AGRESOR :" + victima_agresorDao.max() + 1);
                va.setInt(1, num);
                va.setInt(2, victima_agresorDao.max() + 1);
                va.setString(3, base_model.getVictima_agresor().getTestimonio());
                va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                va.setBoolean(7, base_model.getVictima_agresor().isArea());
                va.setString(8, base_model.getVictima_agresor().getEsp_area());
                va.setInt(9, base_model.getVictima_agresor().getParentesco());
                va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                va.setString(11, base_model.getVictima_agresor().getTviolencia());
                va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                va.setInt(16, 0);
                va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                va.setInt(18, 0);
/*INICIO Preguntas Alertas*/
                    va.setInt(19, base_model.getVictima_agresor().getPreg1());
                    va.setInt(20, base_model.getVictima_agresor().getPreg2());
                    va.setInt(21, base_model.getVictima_agresor().getPreg3());
                    va.setInt(22, base_model.getVictima_agresor().getPreg4());
                    va.setInt(23, base_model.getVictima_agresor().getPreg5());
                    va.setInt(24, base_model.getVictima_agresor().getPreg6());
                    va.setInt(25, base_model.getVictima_agresor().getPreg7());
                    va.setInt(26, base_model.getVictima_agresor().getPreg8());
                    va.setInt(27, base_model.getVictima_agresor().getPreg9());
                    va.setInt(28, base_model.getVictima_agresor().getPreg10());
                    va.setInt(29, base_model.getVictima_agresor().getPreg11());
                    va.setInt(30, base_model.getVictima_agresor().getPreg12());
                    va.setInt(31, base_model.getVictima_agresor().getPreg13());
                    va.setInt(32, base_model.getVictima_agresor().getPreg14());
                    va.setInt(33, base_model.getVictima_agresor().getPreg15());
                    va.setInt(34, base_model.getVictima_agresor().getPreg16());
                    va.setInt(35, base_model.getVictima_agresor().getPreg17());
                    va.setInt(36, base_model.getVictima_agresor().getPreg18());
                    va.setInt(37, base_model.getVictima_agresor().getPreg19());
                    va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
                    va.setInt(39, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                /*FIN Preguntas Alertas*/  
                   
                ResultSet resultSet_va = va.executeQuery();

                CallableStatement vp = connection.prepareCall("select coco_operaciones_vicagre_persona(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("VICAGRE PERSONA2 :" + vicAgre_personaDao.max() + 1);
                System.out.println("VICAGRE PERSONA3 :" + victima_agresorDao.max() + 1);

                vp.setInt(1, num);
                vp.setInt(2, vicAgre_personaDao.max() + 1);
                vp.setInt(3, victima_agresorDao.max() + 1);
                vp.setInt(4, 0);
                vp.setInt(5, base_model.getPersonaVictima().getId_persona());
                vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                vp.setInt(12, persona_VariablesDao.max() + 1);
                System.out.println("idPERSONAVARIABLES : " + persona_VariablesDao.max());
                vp.setInt(13, 0);
                vp.setInt(14, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                ResultSet resultSet_vp = vp.executeQuery();

                aux = victima_agresorDao.max() + 1;
                CallableStatement us = connection.prepareCall("select sp_operaciones_usuario_vicagre(?,?,?,?,?,?,?)");

                us.setInt(1, num);
                us.setInt(2, base_model.getUsuario_vicagre().getId_usuario_vicagre());
                us.setInt(3, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                us.setInt(4, victima_agresorDao.max() + 1);
                us.setDate(5, new java.sql.Date(base_model.getUsuario_vicagre().getFecha_reg().getTime()));
                us.setInt(6, usuario_vicagreDao.max() + 1);
                us.setString(7, base_model.getUsuario_vicagre().getCod_sistema() + "-" + base_model.getUsuario_vicagre().getUsuario_cod_usuario() + "-" + usuarioDao.datoSubServicio(base_model.getUsuario_vicagre().getUsuario_cod_usuario()));

                ResultSet resultSet_us = us.executeQuery();
                connection.commit();
                res = aux;
// LOG, VER SI SE NECESITA                
                if (res != 0) {

                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                    int respuesta = 0;
                    System.out.println("ipAddress:" + ipAddress);
                    Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Insertar : ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                    respuesta = logDao.insert(1, log);
                    System.out.println("sdddffffffff : " + respuesta);
                    if (respuesta != 0) {
                        //int sss = insert_log(base_modellog, respuesta, 2);
                        //System.out.println("INSERT LOG :" + sss);
                        System.out.println("ENTRA_1   : ");
                    }
                }

            }
            System.out.println("METODO CADENA_2 : " + devcodper());
            if (num == 1 && resp == 2 && existe == 0) {
                connection.setAutoCommit(false);
                CallableStatement cs = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");

                cs.setInt(1, num);
                cs.setInt(2, personaDao.max() + 1);
                cs.setString(3, base_model.getPersonaVictima().getNombre());
                cs.setString(4, base_model.getPersonaVictima().getPaterno());
                cs.setString(5, base_model.getPersonaVictima().getMaterno());
                cs.setString(6, base_model.getPersonaVictima().getAp_casada());
                cs.setString(7, base_model.getPersonaVictima().getNumero());
                cs.setInt(8, base_model.getPersonaVictima().getCodigo_documento());
                cs.setString(9, base_model.getPersonaVictima().getSexo());
                cs.setInt(10, base_model.getPersonaVictima().getLugar_nac());
                cs.setString(11, base_model.getPersonaVictima().getEsp_lugar_nac());
                cs.setDate(12, new java.sql.Date(base_model.getPersonaVictima().getFecha_nac().getTime()));
                cs.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                
                ResultSet resultSet = cs.executeQuery();

                CallableStatement pv = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max() + 1);
                pv.setInt(1, num);
                pv.setInt(2, persona_VariablesDao.max() + 1);
                pv.setInt(3, personaDao.max() + 1);
                pv.setInt(4, base_model.getPersonaVariableVictima().getEdad());
                pv.setInt(5, base_model.getPersonaVariableVictima().getEst_civil());
                pv.setInt(6, base_model.getPersonaVariableVictima().getNro_hijos());
                pv.setInt(7, base_model.getPersonaVariableVictima().getGestacion_h());
                pv.setInt(8, base_model.getPersonaVariableVictima().getNum_miembros_fam());
                pv.setBoolean(9, base_model.getPersonaVariableVictima().isMunicipio());
                pv.setString(10, base_model.getPersonaVariableVictima().getEsp_municipio());
                pv.setBoolean(11, base_model.getPersonaVariableVictima().isArea());
                pv.setString(12, base_model.getPersonaVariableVictima().getEsp_area());
                pv.setInt(13, base_model.getPersonaVariableVictima().getVivienda());
                pv.setInt(14, base_model.getPersonaVariableVictima().getNivel_inst());
                pv.setBoolean(15, base_model.getPersonaVariableVictima().isCondicion_act());
                pv.setInt(16, base_model.getPersonaVariableVictima().getOcupacion());
                pv.setString(17, base_model.getPersonaVariableVictima().getEsp_ocupacion());
                pv.setString(18, base_model.getPersonaVariableVictima().getCargo());
                pv.setInt(19, base_model.getPersonaVariableVictima().getIngre_economico());
                pv.setBigDecimal(20, base_model.getPersonaVariableVictima().getMonto_aprox_bs());
                pv.setBigDecimal(21, base_model.getPersonaVariableVictima().getAporte_familiar_bs());
                pv.setString(22, base_model.getPersonaVariableVictima().getIdiomas());
                pv.setString(23, base_model.getPersonaVariableVictima().getEsp_idioma());
                pv.setBoolean(24, base_model.getPersonaVariableVictima().isEtnia());
                pv.setString(25, base_model.getPersonaVariableVictima().getEsp_etnia());
                pv.setBoolean(26, base_model.getPersonaVariableVictima().isLgbti());
                pv.setBoolean(27, base_model.getPersonaVariableVictima().isPers_discapacidad());
                pv.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                        
                ResultSet resultSet_pv = pv.executeQuery();

                CallableStatement cs1 = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                cs1.setInt(1, num);
                cs1.setInt(2, 0);
                cs1.setString(3, "0");
                cs1.setString(4, "0");
                cs1.setString(5, "0");
                cs1.setString(6, "0");
                cs1.setString(7, "0");
                cs1.setInt(8, 0);
                cs1.setString(9, "0");
                cs1.setInt(10, 0);
                cs1.setString(11, "0");
                cs1.setDate(12, (java.sql.Date) base_model.getPersonaAgresor().getFecha_nac());
                cs1.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                ResultSet resultSet_cs1 = cs1.executeQuery();

                CallableStatement pv1 = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max() + 1);
                pv1.setInt(1, num);
                pv1.setInt(2, 0);
                pv1.setInt(3, 0);
                pv1.setInt(4, 0);
                pv1.setInt(5, 0);
                pv1.setInt(6, 0);
                pv1.setInt(7, 0);
                pv1.setInt(8, 0);
                pv1.setBoolean(9, false);
                pv1.setString(10, "0");
                pv1.setBoolean(11, false);
                pv1.setString(12, "0");
                pv1.setInt(13, 0);
                pv1.setInt(14, 0);
                pv1.setBoolean(15, false);
                pv1.setInt(16, 0);
                pv1.setString(17, "0");
                pv1.setString(18, "0");
                pv1.setInt(19, 0);
                pv1.setBigDecimal(20, new BigDecimal(BigInteger.ONE));
                pv1.setBigDecimal(21, new BigDecimal(BigInteger.ONE));
                pv1.setString(22, "0");
                pv1.setString(23, "0");
                pv1.setBoolean(24, false);
                pv1.setString(25, "0");
                pv1.setBoolean(26, false);
                pv1.setBoolean(27, false);
                pv1.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
// LOG, VER SI SE NECESITA
                //insertAgre_log();

                ResultSet resultSet_pv1 = pv1.executeQuery();

                CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//la funcion original sp_operaciones_victima_agresor(?...18)
                System.out.println("frecuencia del consumo de alcohol : " + base_model.getVictima_agresor().getFrec_consumo_alcohol());
                va.setInt(1, num);
                va.setInt(2, victima_agresorDao.max() + 1);
                va.setString(3, base_model.getVictima_agresor().getTestimonio());
                va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                va.setBoolean(7, base_model.getVictima_agresor().isArea());
                va.setString(8, base_model.getVictima_agresor().getEsp_area());
                va.setInt(9, base_model.getVictima_agresor().getParentesco());
                va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                va.setString(11, base_model.getVictima_agresor().getTviolencia());
                va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                va.setInt(16, 0);
                va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                va.setInt(18, 0);
                /*INICIO Preguntas Alertas*/
                    va.setInt(19, base_model.getVictima_agresor().getPreg1());
                    va.setInt(20, base_model.getVictima_agresor().getPreg2());
                    va.setInt(21, base_model.getVictima_agresor().getPreg3());
                    va.setInt(22, base_model.getVictima_agresor().getPreg4());
                    va.setInt(23, base_model.getVictima_agresor().getPreg5());
                    va.setInt(24, base_model.getVictima_agresor().getPreg6());
                    va.setInt(25, base_model.getVictima_agresor().getPreg7());
                    va.setInt(26, base_model.getVictima_agresor().getPreg8());
                    va.setInt(27, base_model.getVictima_agresor().getPreg9());
                    va.setInt(28, base_model.getVictima_agresor().getPreg10());
                    va.setInt(29, base_model.getVictima_agresor().getPreg11());
                    va.setInt(30, base_model.getVictima_agresor().getPreg12());
                    va.setInt(31, base_model.getVictima_agresor().getPreg13());
                    va.setInt(32, base_model.getVictima_agresor().getPreg14());
                    va.setInt(33, base_model.getVictima_agresor().getPreg15());
                    va.setInt(34, base_model.getVictima_agresor().getPreg16());
                    va.setInt(35, base_model.getVictima_agresor().getPreg17());
                    va.setInt(36, base_model.getVictima_agresor().getPreg18());
                    va.setInt(37, base_model.getVictima_agresor().getPreg19());
                    va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
                    va.setInt(39, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                /*FIN Preguntas Alertas*/  
                

                ResultSet resultSet_va = va.executeQuery();

                CallableStatement vp = connection.prepareCall("select coco_operaciones_vicagre_persona(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                vp.setInt(1, num);
                vp.setInt(2, vicAgre_personaDao.max() + 1);
                vp.setInt(3, victima_agresorDao.max() + 1);
                vp.setInt(4, 0);
                vp.setInt(5, personaDao.max() + 1);
                vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                vp.setInt(12, persona_VariablesDao.max() + 1);
                vp.setInt(13, 0);
                vp.setInt(14, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                ResultSet resultSet_vp = vp.executeQuery();

                CallableStatement us = connection.prepareCall("select sp_operaciones_usuario_vicagre(?,?,?,?,?,?,?)");

                us.setInt(1, num);
                us.setInt(2, base_model.getUsuario_vicagre().getId_usuario_vicagre());
                us.setInt(3, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                us.setInt(4, victima_agresorDao.max() + 1);
                us.setDate(5, new java.sql.Date(base_model.getUsuario_vicagre().getFecha_reg().getTime()));
                us.setInt(6, usuario_vicagreDao.max() + 1);
                us.setString(7, base_model.getUsuario_vicagre().getCod_sistema() + "-" + base_model.getUsuario_vicagre().getUsuario_cod_usuario() + "-" + usuarioDao.datoSubServicio(base_model.getUsuario_vicagre().getUsuario_cod_usuario()));

                ResultSet resultSet_us = us.executeQuery();
                aux = victima_agresorDao.max() + 1;
                connection.commit();
                res = aux;
// LOG, VER SI SE NECESITA
                if (res != 0) {

                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                    int respuesta = 0;
                    System.out.println("ipAddress:" + ipAddress);
                    Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Insertar : ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                    // logDao.insert(1, log);
                    respuesta = logDao.insert(1, log);
                    if (respuesta != 0) {
                        //int sss = insert_log(base_modellog, respuesta, 2);
                        //System.out.println("INSERT LOG :" + sss);
                        System.out.println("ENTRA_2   : ");
                    }
                }

            }
            System.out.println("METODO CADENA_3 : " + devcodper());

            if (num == 1 && resp == 1 && existe == 0) {
                System.out.println("METODO CADENAaa1 : " + devcodper());
                connection.setAutoCommit(false);
                CallableStatement cs = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("PERSONA VICTIMA :" + (personaDao.max() + 1));
                cs.setInt(1, num);
                cs.setInt(2, personaDao.max() + 1);
                cs.setString(3, base_model.getPersonaVictima().getNombre());
                cs.setString(4, base_model.getPersonaVictima().getPaterno());
                cs.setString(5, base_model.getPersonaVictima().getMaterno());
                cs.setString(6, base_model.getPersonaVictima().getAp_casada());
                cs.setString(7, base_model.getPersonaVictima().getNumero());
                cs.setInt(8, base_model.getPersonaVictima().getCodigo_documento());
                cs.setString(9, base_model.getPersonaVictima().getSexo());
                cs.setInt(10, base_model.getPersonaVictima().getLugar_nac());
                cs.setString(11, base_model.getPersonaVictima().getEsp_lugar_nac());
                cs.setDate(12, new java.sql.Date(base_model.getPersonaVictima().getFecha_nac().getTime()));
                cs.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                
                ResultSet resultSet = cs.executeQuery();
                CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//la funcion original sp_operaciones_victima_agresor(?...18)
                System.out.println("VICTIMAS AGRESOR :" + victima_agresorDao.max() + 1);
                va.setInt(1, num);

                va.setInt(2, victima_agresorDao.max() + 1);
                va.setString(3, base_model.getVictima_agresor().getTestimonio());
                va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                va.setBoolean(7, base_model.getVictima_agresor().isArea());
                va.setString(8, base_model.getVictima_agresor().getEsp_area());
                va.setInt(9, base_model.getVictima_agresor().getParentesco());
                va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                va.setString(11, base_model.getVictima_agresor().getTviolencia());
                va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                va.setInt(16, 0);
                va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                va.setInt(18, 0);
/*INICIO Preguntas Alertas*/
                    va.setInt(19, base_model.getVictima_agresor().getPreg1());
                    va.setInt(20, base_model.getVictima_agresor().getPreg2());
                    va.setInt(21, base_model.getVictima_agresor().getPreg3());
                    va.setInt(22, base_model.getVictima_agresor().getPreg4());
                    va.setInt(23, base_model.getVictima_agresor().getPreg5());
                    va.setInt(24, base_model.getVictima_agresor().getPreg6());
                    va.setInt(25, base_model.getVictima_agresor().getPreg7());
                    va.setInt(26, base_model.getVictima_agresor().getPreg8());
                    va.setInt(27, base_model.getVictima_agresor().getPreg9());
                    va.setInt(28, base_model.getVictima_agresor().getPreg10());
                    va.setInt(29, base_model.getVictima_agresor().getPreg11());
                    va.setInt(30, base_model.getVictima_agresor().getPreg12());
                    va.setInt(31, base_model.getVictima_agresor().getPreg13());
                    va.setInt(32, base_model.getVictima_agresor().getPreg14());
                    va.setInt(33, base_model.getVictima_agresor().getPreg15());
                    va.setInt(34, base_model.getVictima_agresor().getPreg16());
                    va.setInt(35, base_model.getVictima_agresor().getPreg17());
                    va.setInt(36, base_model.getVictima_agresor().getPreg18());
                    va.setInt(37, base_model.getVictima_agresor().getPreg19());
                    va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
                    va.setInt(39, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                /*FIN Preguntas Alertas*/  
                

                ResultSet resultSet_va = va.executeQuery();

                CallableStatement pv = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max() + 1);
                pv.setInt(1, num);
                pv.setInt(2, persona_VariablesDao.max() + 1);
                pv.setInt(3, personaDao.max() + 1);
                pv.setInt(4, base_model.getPersonaVariableVictima().getEdad());
                pv.setInt(5, base_model.getPersonaVariableVictima().getEst_civil());
                pv.setInt(6, base_model.getPersonaVariableVictima().getNro_hijos());
                pv.setInt(7, base_model.getPersonaVariableVictima().getGestacion_h());
                pv.setInt(8, base_model.getPersonaVariableVictima().getNum_miembros_fam());
                pv.setBoolean(9, base_model.getPersonaVariableVictima().isMunicipio());
                pv.setString(10, base_model.getPersonaVariableVictima().getEsp_municipio());
                pv.setBoolean(11, base_model.getPersonaVariableVictima().isArea());
                pv.setString(12, base_model.getPersonaVariableVictima().getEsp_area());
                pv.setInt(13, base_model.getPersonaVariableVictima().getVivienda());
                pv.setInt(14, base_model.getPersonaVariableVictima().getNivel_inst());
                pv.setBoolean(15, base_model.getPersonaVariableVictima().isCondicion_act());
                pv.setInt(16, base_model.getPersonaVariableVictima().getOcupacion());
                pv.setString(17, base_model.getPersonaVariableVictima().getEsp_ocupacion());
                pv.setString(18, base_model.getPersonaVariableVictima().getCargo());
                pv.setInt(19, base_model.getPersonaVariableVictima().getIngre_economico());
                pv.setBigDecimal(20, base_model.getPersonaVariableVictima().getMonto_aprox_bs());
                pv.setBigDecimal(21, base_model.getPersonaVariableVictima().getAporte_familiar_bs());
                pv.setString(22, base_model.getPersonaVariableVictima().getIdiomas());
                pv.setString(23, base_model.getPersonaVariableVictima().getEsp_idioma());
                pv.setBoolean(24, base_model.getPersonaVariableVictima().isEtnia());
                pv.setString(25, base_model.getPersonaVariableVictima().getEsp_etnia());
                pv.setBoolean(26, base_model.getPersonaVariableVictima().isLgbti());
                pv.setBoolean(27, base_model.getPersonaVariableVictima().isPers_discapacidad());
                pv.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                
                ResultSet resultSet_pv = pv.executeQuery();

                CallableStatement vp = connection.prepareCall("select coco_operaciones_vicagre_persona(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("VICAGRE PERSONA2 :" + vicAgre_personaDao.max() + 1);
                System.out.println("VICAGRE PERSONA3 :" + victima_agresorDao.max() + 1);

                vp.setInt(1, num);
                vp.setInt(2, vicAgre_personaDao.max() + 1);
                vp.setInt(3, victima_agresorDao.max() + 1);
                vp.setInt(4, 0);
                vp.setInt(5, personaDao.max() + 1);
                vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                vp.setInt(12, persona_VariablesDao.max() + 1);
                System.out.println("idPERSONAVARIABLES : " + persona_VariablesDao.max());
                vp.setInt(13, 0);
                vp.setInt(14, base_model.getUsuario_vicagre().getUsuario_cod_usuario());

                ResultSet resultSet_vp = vp.executeQuery();
//
                aux = victima_agresorDao.max() + 1;
                CallableStatement us = connection.prepareCall("select sp_operaciones_usuario_vicagre(?,?,?,?,?,?,?)");

                us.setInt(1, num);
                us.setInt(2, base_model.getUsuario_vicagre().getId_usuario_vicagre());
                us.setInt(3, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                us.setInt(4, victima_agresorDao.max() + 1);
                us.setDate(5, new java.sql.Date(base_model.getUsuario_vicagre().getFecha_reg().getTime()));
                //us.setDate(5, (java.sql.Date) usuario_vicagre1.getFecha_reg());
                us.setInt(6, usuario_vicagreDao.max() + 1);
                us.setString(7, base_model.getUsuario_vicagre().getCod_sistema() + "-" + base_model.getUsuario_vicagre().getUsuario_cod_usuario() + "-" + usuarioDao.datoSubServicio(base_model.getUsuario_vicagre().getUsuario_cod_usuario()));

                ResultSet resultSet_us = us.executeQuery();
                connection.commit();
                res = aux;
// LOG, VER SI SE NECESITA
                if (res != 0) {
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                    int respuesta = 0;
                    System.out.println("ipAddress:" + ipAddress);
                    Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Insertar : ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                    //logDao.insert(1, log);
                    respuesta = logDao.insert(1, log);
                    System.out.println("sdddffffffff : " + respuesta);

                    if (respuesta != 0) {
                        //int sss = insert_log(base_modellog, respuesta, 2);
                        //System.out.println("INSERT LOG :" + sss);
                        System.out.println("ENTRA_3   : ");
                    }
                }
            }

            if (num == 2) {
                connection.setAutoCommit(false);
                CallableStatement cs = connection.prepareCall("select coco_operaciones_persona(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println(" id_persona :" + base_model.getPersonaVictima().getId_persona());
                cs.setInt(1, num);
                cs.setInt(2, base_model.getPersonaVictima().getId_persona());
                cs.setString(3, base_model.getPersonaVictima().getNombre());
                cs.setString(4, base_model.getPersonaVictima().getPaterno());
                cs.setString(5, base_model.getPersonaVictima().getMaterno());
                cs.setString(6, base_model.getPersonaVictima().getAp_casada());
                cs.setString(7, base_model.getPersonaVictima().getNumero());
                cs.setInt(8, base_model.getPersonaVictima().getCodigo_documento());
                cs.setString(9, base_model.getPersonaVictima().getSexo());
                cs.setInt(10, base_model.getPersonaVictima().getLugar_nac());
                cs.setString(11, base_model.getPersonaVictima().getEsp_lugar_nac());
                cs.setDate(12, new java.sql.Date(base_model.getPersonaVictima().getFecha_nac().getTime()));
                cs.setInt(13, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                
                ResultSet resultSet = cs.executeQuery();

                CallableStatement pv = connection.prepareCall("select coco_operaciones_persona_variables_(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pv.setInt(1, num);
                // pv.setInt(2, persona_variablesDao.max()+1);
                pv.setInt(2, base_model.getPersonaVariableVictima().getId_pervar());
                pv.setInt(3, base_model.getPersonaVariableVictima().getId_persona());
                pv.setInt(4, base_model.getPersonaVariableVictima().getEdad());
                pv.setInt(5, base_model.getPersonaVariableVictima().getEst_civil());
                pv.setInt(6, base_model.getPersonaVariableVictima().getNro_hijos());
                pv.setInt(7, base_model.getPersonaVariableVictima().getGestacion_h());
                pv.setInt(8, base_model.getPersonaVariableVictima().getNum_miembros_fam());
                pv.setBoolean(9, base_model.getPersonaVariableVictima().isMunicipio());
                pv.setString(10, base_model.getPersonaVariableVictima().getEsp_municipio());
                pv.setBoolean(11, base_model.getPersonaVariableVictima().isArea());
                pv.setString(12, base_model.getPersonaVariableVictima().getEsp_area());
                pv.setInt(13, base_model.getPersonaVariableVictima().getVivienda());
                pv.setInt(14, base_model.getPersonaVariableVictima().getNivel_inst());
                pv.setBoolean(15, base_model.getPersonaVariableVictima().isCondicion_act());
                pv.setInt(16, base_model.getPersonaVariableVictima().getOcupacion());
                pv.setString(17, base_model.getPersonaVariableVictima().getEsp_ocupacion());
                pv.setString(18, base_model.getPersonaVariableVictima().getCargo());
                pv.setInt(19, base_model.getPersonaVariableVictima().getIngre_economico());
                pv.setBigDecimal(20, base_model.getPersonaVariableVictima().getMonto_aprox_bs());
                pv.setBigDecimal(21, base_model.getPersonaVariableVictima().getAporte_familiar_bs());
                pv.setString(22, base_model.getPersonaVariableVictima().getIdiomas());
                pv.setString(23, base_model.getPersonaVariableVictima().getEsp_idioma());
                pv.setBoolean(24, base_model.getPersonaVariableVictima().isEtnia());
                pv.setString(25, base_model.getPersonaVariableVictima().getEsp_etnia());
                pv.setBoolean(26, base_model.getPersonaVariableVictima().isLgbti());
                pv.setBoolean(27, base_model.getPersonaVariableVictima().isPers_discapacidad());
                pv.setInt(28, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                
                ResultSet resultSet_pv = pv.executeQuery();
                aux = victima_agresorDao.max();
                CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//la funcion original sp_operaciones_victima_agresor(?...18)
                va.setInt(1, num);
                va.setInt(2, base_model.getVictima_agresor().getId_vicagre());
                va.setString(3, base_model.getVictima_agresor().getTestimonio());
                va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                va.setBoolean(7, base_model.getVictima_agresor().isArea());
                va.setString(8, base_model.getVictima_agresor().getEsp_area());
                va.setInt(9, base_model.getVictima_agresor().getParentesco());
                va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                va.setString(11, base_model.getVictima_agresor().getTviolencia());
                va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                va.setInt(16, base_model.getVictima_agresor().getFrec_consumo_alcohol());
                va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                va.setInt(18, base_model.getVictima_agresor().getFrec_consumo_drogas());
/*INICIO Preguntas Alertas*/
                    va.setInt(19, base_model.getVictima_agresor().getPreg1());
                    va.setInt(20, base_model.getVictima_agresor().getPreg2());
                    va.setInt(21, base_model.getVictima_agresor().getPreg3());
                    va.setInt(22, base_model.getVictima_agresor().getPreg4());
                    va.setInt(23, base_model.getVictima_agresor().getPreg5());
                    va.setInt(24, base_model.getVictima_agresor().getPreg6());
                    va.setInt(25, base_model.getVictima_agresor().getPreg7());
                    va.setInt(26, base_model.getVictima_agresor().getPreg8());
                    va.setInt(27, base_model.getVictima_agresor().getPreg9());
                    va.setInt(28, base_model.getVictima_agresor().getPreg10());
                    va.setInt(29, base_model.getVictima_agresor().getPreg11());
                    va.setInt(30, base_model.getVictima_agresor().getPreg12());
                    va.setInt(31, base_model.getVictima_agresor().getPreg13());
                    va.setInt(32, base_model.getVictima_agresor().getPreg14());
                    va.setInt(33, base_model.getVictima_agresor().getPreg15());
                    va.setInt(34, base_model.getVictima_agresor().getPreg16());
                    va.setInt(35, base_model.getVictima_agresor().getPreg17());
                    va.setInt(36, base_model.getVictima_agresor().getPreg18());
                    va.setInt(37, base_model.getVictima_agresor().getPreg19());
                    va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
                    va.setInt(39, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                /*FIN Preguntas Alertas*/  
                

                ResultSet resultSet_va = va.executeQuery();

                CallableStatement vp = connection.prepareCall("select coco_operaciones_vicagre_persona(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                vp.setInt(1, num);
                vp.setInt(2, base_model.getVicAgre_persona().getId_vicAgre_persona());
                vp.setInt(3, base_model.getVicAgre_persona().getVictima_agresor_id_vicagre());
                vp.setInt(4, base_model.getVicAgre_persona().getPersona_id_persona_agresor());
                vp.setInt(5, base_model.getVicAgre_persona().getPersona_id_persona_victima());
                vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                vp.setInt(12, base_model.getPersonaVariableVictima().getId_pervar());
                vp.setInt(13, base_model.getPersonaVariableAgresor().getId_pervar());
                vp.setInt(14, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                
                ResultSet resultSet_vp = vp.executeQuery();
                connection.commit();
                res = aux;
// LOG, VER SI SE NECESITA
                if (res != 0) {

                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                    int respuesta = 0;
                    System.out.println("ipAddress:" + ipAddress);
                    Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Modificar : ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                    respuesta = logDao.insert(1, log);
                    if (respuesta != 0) {
                        //int sss = insert_log(base_modellog, respuesta, 2);
                        //System.out.println("INSERT LOG :" + sss);
                        System.out.println("ENTRA_4   : ");
                    }
                }
            }

        } catch (SQLException e) {

            try {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Error Sql al Insertar: ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                logDao.insert(2, log);
                
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } catch (Exception e) {
            try {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                Log log = new Log(base_model.getUsuario_vicagre().getUsuario_cod_usuario(), ipAddress, "Error al Insertar: ", victima_agresorDao.max(), "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                logDao.insert(2, log);
            } catch (Exception e1) {
                e1.getMessage();
            }

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

    @Override
    public Base_model data_baseModel(Victima_agresor victimaAgresor) {
        Base_model data = new Base_model();
        try {

            String query = "select * from coco_view_compuesto_victima_denuncia_agresor where id_vicagre = " + victimaAgresor.getId_vicagre(); //SE CAMBIO de view_compuesto_victima_denuncia_agresor
            System.out.println("este es mi query:::::" + query);
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                data.getUsuario_vicagre().setId_usuario_vicagre(resultSet.getInt("id_usuario_vicagre"));
                data.getUsuario_vicagre().setUsuario_cod_usuario(resultSet.getInt("usuario_cod_usuario"));
                data.getUsuario_vicagre().setVictima_agresor_id_vicagre(resultSet.getInt("victima_agresor_id_vicagre"));
                data.getUsuario_vicagre().setFecha_reg(resultSet.getDate("fecha_reg"));
                data.getUsuario_vicagre().setNro_caso(resultSet.getInt("nro_caso"));
                data.getUsuario_vicagre().setCod_sistema(resultSet.getString("cod_sistema"));
                data.getUsuario_vicagre().setFecha_system(resultSet.getDate("fecha_system"));

                data.getVictima_agresor().setId_vicagre(resultSet.getInt("id_vicagre"));
                data.getVictima_agresor().setTestimonio(resultSet.getString("testimonio"));
                data.getVictima_agresor().setLugar_agresion(resultSet.getInt("lugar_agresion"));
                data.getVictima_agresor().setMunicipio(resultSet.getBoolean("municipio"));
                data.getVictima_agresor().setEsp_municipio(resultSet.getString("esp_municipio"));
                data.getVictima_agresor().setArea(resultSet.getBoolean("area"));
                data.getVictima_agresor().setEsp_area(resultSet.getString("esp_area"));
                data.getVictima_agresor().setParentesco(resultSet.getInt("parentesco"));
                data.getVictima_agresor().setEsp_parentesco(resultSet.getString("esp_parentesco"));
                data.getVictima_agresor().setTviolencia(resultSet.getString("tviolencia"));
                data.getVictima_agresor().setDenuncio(resultSet.getBoolean("denuncio"));
                data.getVictima_agresor().setDonde_denuncio(resultSet.getString("donde_denuncio"));
                data.getVictima_agresor().setFrec_agresiones(resultSet.getInt("frec_agresiones"));
                data.getVictima_agresor().setAgre_consume_alcohol(resultSet.getBoolean("agre_consume_alcohol"));
                data.getVictima_agresor().setFrec_consumo_alcohol(resultSet.getInt("frec_consumo_alcohol"));
                data.getVictima_agresor().setAgre_consume_drogas(resultSet.getBoolean("agre_consume_drogas"));
                data.getVictima_agresor().setFrec_consumo_drogas(resultSet.getInt("frec_consumo_drogas"));
                data.getVictima_agresor().setEsp_violencia(resultSet.getString("esp_violencia"));
                /* INICIO PREGUNTAS ALERTAS*/
                data.getVictima_agresor().setPreg1(resultSet.getInt("preg1"));
                data.getVictima_agresor().setPreg2(resultSet.getInt("preg2"));
                data.getVictima_agresor().setPreg3(resultSet.getInt("preg3"));
                data.getVictima_agresor().setPreg4(resultSet.getInt("preg4"));
                data.getVictima_agresor().setPreg5(resultSet.getInt("preg5"));
                data.getVictima_agresor().setPreg6(resultSet.getInt("preg6"));
                data.getVictima_agresor().setPreg7(resultSet.getInt("preg7"));
                data.getVictima_agresor().setPreg8(resultSet.getInt("preg8"));
                data.getVictima_agresor().setPreg9(resultSet.getInt("preg9"));
                data.getVictima_agresor().setPreg10(resultSet.getInt("preg10"));
                data.getVictima_agresor().setPreg11(resultSet.getInt("preg11"));
                data.getVictima_agresor().setPreg12(resultSet.getInt("preg12"));
                data.getVictima_agresor().setPreg13(resultSet.getInt("preg13"));
                data.getVictima_agresor().setPreg14(resultSet.getInt("preg14"));
                data.getVictima_agresor().setPreg15(resultSet.getInt("preg15"));
                data.getVictima_agresor().setPreg16(resultSet.getInt("preg16"));
                data.getVictima_agresor().setPreg17(resultSet.getInt("preg17"));
                data.getVictima_agresor().setPreg18(resultSet.getInt("preg18"));
                data.getVictima_agresor().setPreg19(resultSet.getInt("preg19"));
                data.getVictima_agresor().setPreg_suma(resultSet.getInt("preg_suma"));
                
/* FIN PREGUNTAS ALERTAS*/
                data.getVicAgre_persona().setId_vicAgre_persona(resultSet.getInt("id_vicAgre_persona"));
                data.getVicAgre_persona().setVictima_agresor_id_vicagre(resultSet.getInt("victima_agresor_id_vicagre"));
                data.getVicAgre_persona().setPersona_id_persona_agresor(resultSet.getInt("persona_id_persona_agresor"));
                data.getVicAgre_persona().setPersona_id_persona_victima(resultSet.getInt("persona_id_persona_victima"));
                data.getVicAgre_persona().setReferencia_caso_de(resultSet.getString("referencia_caso_de"));
                data.getVicAgre_persona().setReferencia_esp_caso(resultSet.getString("referencia_esp_caso"));
                data.getVicAgre_persona().setContra_ref_caso_de(resultSet.getString("contra_ref_caso_de"));
                data.getVicAgre_persona().setContra_ref_esp_caso(resultSet.getString("contra_ref_esp_caso"));
                data.getVicAgre_persona().setCaso_resuelto(resultSet.getBoolean("caso_resuelto"));
                data.getVicAgre_persona().setCaso_abandonado(resultSet.getBoolean("caso_abandonado"));

                data.getPersonaVictima().setId_persona(resultSet.getInt("vic_id_persona"));
                data.getPersonaVictima().setNombre(resultSet.getString("vic_nombre"));
                data.getPersonaVictima().setPaterno(resultSet.getString("vic_paterno"));
                data.getPersonaVictima().setMaterno(resultSet.getString("vic_materno"));
                data.getPersonaVictima().setAp_casada(resultSet.getString("vic_ap_casada"));
                data.getPersonaVictima().setNumero(resultSet.getString("vic_numero"));
                data.getPersonaVictima().setCodigo_documento(resultSet.getInt("vic_codigo_documento"));
                data.getPersonaVictima().setSexo(resultSet.getString("vic_sexo"));
                data.getPersonaVictima().setLugar_nac(resultSet.getInt("vic_lugar_nac"));
                data.getPersonaVictima().setEsp_lugar_nac(resultSet.getString("vic_esp_lugar_nac"));
                data.getPersonaVictima().setFecha_nac(resultSet.getDate("vic_fecha_nac"));

                data.getPersonaAgresor().setId_persona(resultSet.getInt("agr_id_persona"));
                System.out.println("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddDDDDDDDDDDDDDDDDD : " + data.getPersonaAgresor().getId_persona());
                data.getPersonaAgresor().setNombre(resultSet.getString("agr_nombre"));
                data.getPersonaAgresor().setPaterno(resultSet.getString("agr_paterno"));
                data.getPersonaAgresor().setMaterno(resultSet.getString("agr_materno"));
                data.getPersonaAgresor().setAp_casada(resultSet.getString("agr_ap_casada"));
                data.getPersonaAgresor().setNumero(resultSet.getString("agr_numero"));
                data.getPersonaAgresor().setCodigo_documento(resultSet.getInt("agr_codigo_documento"));
                data.getPersonaAgresor().setSexo(resultSet.getString("agr_sexo"));
                data.getPersonaAgresor().setLugar_nac(resultSet.getInt("agr_lugar_nac"));
                data.getPersonaAgresor().setEsp_lugar_nac(resultSet.getString("agr_esp_lugar_nac"));
                data.getPersonaAgresor().setFecha_nac(resultSet.getDate("agr_fecha_nac"));

                data.getPersonaVariableVictima().setId_pervar(resultSet.getInt("vic_id_pervar"));
                data.getPersonaVariableVictima().setId_persona(resultSet.getInt("vic_id_persona"));
                data.getPersonaVariableVictima().setEdad(resultSet.getInt("vic_edad"));
                data.getPersonaVariableVictima().setEst_civil(resultSet.getInt("vic_est_civil"));
                data.getPersonaVariableVictima().setNro_hijos(resultSet.getInt("vic_nro_hijos"));
                data.getPersonaVariableVictima().setGestacion_h(resultSet.getInt("vic_gestacion_h"));
                data.getPersonaVariableVictima().setNum_miembros_fam(resultSet.getInt("vic_num_miembros_fam"));
                data.getPersonaVariableVictima().setMunicipio(resultSet.getBoolean("vic_municipio"));
                data.getPersonaVariableVictima().setEsp_municipio(resultSet.getString("vic_esp_municipio"));
                data.getPersonaVariableVictima().setArea(resultSet.getBoolean("vic_area"));
                data.getPersonaVariableVictima().setEsp_area(resultSet.getString("vic_esp_area"));
                data.getPersonaVariableVictima().setVivienda(resultSet.getInt("vic_vivienda"));
                data.getPersonaVariableVictima().setNivel_inst(resultSet.getInt("vic_nivel_inst"));
                data.getPersonaVariableVictima().setCondicion_act(resultSet.getBoolean("vic_condicion_act"));
                data.getPersonaVariableVictima().setOcupacion(resultSet.getInt("vic_ocupacion"));
                data.getPersonaVariableVictima().setEsp_ocupacion(resultSet.getString("vic_esp_ocupacion"));
                data.getPersonaVariableVictima().setCargo(resultSet.getString("vic_cargo"));
                data.getPersonaVariableVictima().setIngre_economico(resultSet.getInt("vic_ingre_economico"));
                data.getPersonaVariableVictima().setMonto_aprox_bs(resultSet.getBigDecimal("vic_monto_aprox_bs"));
                data.getPersonaVariableVictima().setAporte_familiar_bs(resultSet.getBigDecimal("vic_aporte_familiar_bs"));
                data.getPersonaVariableVictima().setIdiomas(resultSet.getString("vic_idiomas"));
                data.getPersonaVariableVictima().setEsp_idioma(resultSet.getString("vic_esp_idioma"));
                data.getPersonaVariableVictima().setEtnia(resultSet.getBoolean("vic_etnia"));
                data.getPersonaVariableVictima().setEsp_etnia(resultSet.getString("vic_esp_etnia"));
                data.getPersonaVariableVictima().setLgbti(resultSet.getBoolean("vic_lgbti"));
                data.getPersonaVariableVictima().setPers_discapacidad(resultSet.getBoolean("vic_pers_discapacidad"));

                data.getPersonaVariableAgresor().setId_pervar(resultSet.getInt("agr_id_pervar"));
                data.getPersonaVariableAgresor().setId_persona(resultSet.getInt("agr_id_persona"));
                data.getPersonaVariableAgresor().setEdad(resultSet.getInt("agr_edad"));
                data.getPersonaVariableAgresor().setEst_civil(resultSet.getInt("agr_est_civil"));
                data.getPersonaVariableAgresor().setNro_hijos(resultSet.getInt("agr_nro_hijos"));
                data.getPersonaVariableAgresor().setGestacion_h(resultSet.getInt("agr_gestacion_h"));
                data.getPersonaVariableAgresor().setNum_miembros_fam(resultSet.getInt("agr_num_miembros_fam"));
                data.getPersonaVariableAgresor().setMunicipio(resultSet.getBoolean("agr_municipio"));
                data.getPersonaVariableAgresor().setEsp_municipio(resultSet.getString("agr_esp_municipio"));
                data.getPersonaVariableAgresor().setArea(resultSet.getBoolean("agr_area"));
                data.getPersonaVariableAgresor().setEsp_area(resultSet.getString("agr_esp_area"));
                data.getPersonaVariableAgresor().setVivienda(resultSet.getInt("agr_vivienda"));
                data.getPersonaVariableAgresor().setNivel_inst(resultSet.getInt("agr_nivel_inst"));
                data.getPersonaVariableAgresor().setCondicion_act(resultSet.getBoolean("agr_condicion_act"));
                data.getPersonaVariableAgresor().setOcupacion(resultSet.getInt("agr_ocupacion"));
                data.getPersonaVariableAgresor().setEsp_ocupacion(resultSet.getString("agr_esp_ocupacion"));
                data.getPersonaVariableAgresor().setCargo(resultSet.getString("agr_cargo"));
                data.getPersonaVariableAgresor().setIngre_economico(resultSet.getInt("agr_ingre_economico"));
                data.getPersonaVariableAgresor().setMonto_aprox_bs(resultSet.getBigDecimal("agr_monto_aprox_bs"));
                data.getPersonaVariableAgresor().setAporte_familiar_bs(resultSet.getBigDecimal("agr_aporte_familiar_bs"));
                data.getPersonaVariableAgresor().setIdiomas(resultSet.getString("agr_idiomas"));
                data.getPersonaVariableAgresor().setEsp_idioma(resultSet.getString("agr_esp_idioma"));
                data.getPersonaVariableAgresor().setEtnia(resultSet.getBoolean("agr_etnia"));
                data.getPersonaVariableAgresor().setEsp_etnia(resultSet.getString("agr_esp_etnia"));
                data.getPersonaVariableAgresor().setLgbti(resultSet.getBoolean("agr_lgbti"));
                data.getPersonaVariableAgresor().setPers_discapacidad(resultSet.getBoolean("agr_pers_discapacidad"));

            }
        } catch (Exception e) {
            e.getMessage();
        }
        return data;
    }
//BASE MOD SE CONVIERTE EN BASE MODEL
    // Si no me equivoco este Find Year obtiene algunos resultados para la LISTA en detalle.xhtml
    @Override
    public List<Base_model> find_year() {
        /*Edit POR BORIS. obtenerel codigo de usuario para enviar a la function coco_viewfindyears*/
        UsuarioController usuarioc = new UsuarioController();
        int codigousuario = usuarioc.getUsuario().getCod_usuario();
        System.out.println(codigousuario);
        //Usuario usuario = new Usuario();
         /*Edit POR BORIS. obtenerel codigo de usuario para enviar a la function coco_viewfindyears*/
        List<Base_model> list_basemod = new ArrayList<>();
        try {
            String query = "select * from coco_viewfindyears("+codigousuario+")";
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("BASE_MODEL fIND_YEAR:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Base_model base_mod = new Base_model();

                base_mod.getVicAgre_persona().setId_vicAgre_persona(resultSet.getInt("id_vicAgre_persona"));
                base_mod.getVictima_agresor().setTviolencia(resultSet.getString("tviolencia"));
                base_mod.getPersonaVictima().setNumero(resultSet.getString("vic_numero"));
                base_mod.getPersonaVictima().setId_persona(resultSet.getInt("vic_id_persona"));
                base_mod.getPersonaVictima().setNombre(resultSet.getString("vic_nombre"));
                base_mod.getPersonaVictima().setPaterno(resultSet.getString("vic_paterno"));
                base_mod.getPersonaVictima().setMaterno(resultSet.getString("vic_materno"));
                base_mod.getPersonaAgresor().setNumero(resultSet.getString("agr_numero"));
                base_mod.getPersonaAgresor().setId_persona(resultSet.getInt("agr_id_persona"));
                base_mod.getPersonaAgresor().setNombre(resultSet.getString("agr_nombre"));
                base_mod.getPersonaAgresor().setPaterno(resultSet.getString("agr_paterno"));
                base_mod.getPersonaAgresor().setMaterno(resultSet.getString("agr_materno"));
                base_mod.getUsuario_vicagre().setId_usuario_vicagre(resultSet.getInt("id_usuario_vicagre"));
                base_mod.getUsuario_vicagre().setUsuario_cod_usuario(resultSet.getInt("usuario_cod_usuario"));
                base_mod.getUsuario_vicagre().setVictima_agresor_id_vicagre(resultSet.getInt("victima_agresor_id_vicagre"));
                base_mod.getUsuario_vicagre().setFecha_reg(resultSet.getDate("fecha_reg"));
                base_mod.getUsuario_vicagre().setNro_caso(resultSet.getInt("nro_caso"));
                base_mod.getUsuario_vicagre().setCod_sistema(resultSet.getString("cod_sistema"));
                base_mod.getUsuario_vicagre().setFecha_system(resultSet.getDate("fecha_system"));
                /* Inicio OBtener ALERTAS sumatoria*/
                base_mod.getVictima_agresor().setPreg_suma(resultSet.getInt("preg_suma"));
                /* fin OBtener ALERTAS sumatoria*/
                //Obtiene los casos despues de la atencion Boris
                base_mod.getVicAgre_persona().setContra_ref_caso_de(resultSet.getString("contra_ref_caso_de"));
                base_mod.getVicAgre_persona().setContra_ref_esp_caso(resultSet.getString("contra_ref_esp_caso"));
                list_basemod.add(base_mod);
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }

        return list_basemod;

    }

    @Override
    public List<Base_model> find_codruv(String cod_sistema) {
        List<Base_model> list_base_mod = new ArrayList<>();
//        LogDao logDao = new LogDaoImpl();
//        
//        int sw = 0;
//        ConnectionLDB connL = new ConnectionLDB();
//        Connection connectionL = connL.getConnectionDB();

        try {
            String query = "select * from coco_find_viewfindruv_codruv('" + cod_sistema + "')"; // Cambiado del original sp_find_viewfindruv_codruv

            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                System.out.println("whileeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                Base_model base_mod = new Base_model();
                base_mod.getVicAgre_persona().setId_vicAgre_persona(resultSet.getInt("id_vicAgre_persona"));
                base_mod.getVictima_agresor().setTviolencia(resultSet.getString("tviolencia"));
                base_mod.getPersonaVictima().setNumero(resultSet.getString("vic_numero"));
                base_mod.getPersonaVictima().setId_persona(resultSet.getInt("vic_id_persona"));
                base_mod.getPersonaVictima().setNombre(resultSet.getString("vic_nombre"));
                base_mod.getPersonaVictima().setPaterno(resultSet.getString("vic_paterno"));
                base_mod.getPersonaVictima().setMaterno(resultSet.getString("vic_materno"));
                base_mod.getPersonaAgresor().setNumero(resultSet.getString("agr_numero"));
                base_mod.getPersonaAgresor().setId_persona(resultSet.getInt("agr_id_persona"));
                base_mod.getPersonaAgresor().setNombre(resultSet.getString("agr_nombre"));
                base_mod.getPersonaAgresor().setPaterno(resultSet.getString("agr_paterno"));
                base_mod.getPersonaAgresor().setMaterno(resultSet.getString("agr_materno"));
                base_mod.getUsuario_vicagre().setId_usuario_vicagre(resultSet.getInt("id_usuario_vicagre"));
                base_mod.getUsuario_vicagre().setUsuario_cod_usuario(resultSet.getInt("usuario_cod_usuario"));
                base_mod.getUsuario_vicagre().setVictima_agresor_id_vicagre(resultSet.getInt("victima_agresor_id_vicagre"));
                base_mod.getUsuario_vicagre().setFecha_reg(resultSet.getDate("fecha_reg"));
                base_mod.getUsuario_vicagre().setNro_caso(resultSet.getInt("nro_caso"));
                base_mod.getUsuario_vicagre().setCod_sistema(resultSet.getString("cod_sistema"));
                base_mod.getUsuario_vicagre().setFecha_system(resultSet.getDate("fecha_system"));

                list_base_mod.add(base_mod);
//                sw = 1;
            }
//            boolean aux = false;
//            if (sw != 0 || aux == resultSet.next()) {
////                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
////                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
////                String ipAddress = request.getHeader("X-FORWARDED-FOR");
////                if (ipAddress == null) {
////                    ipAddress = request.getRemoteAddr();
////                }
////                System.out.println("ipAddress:" + ipAddress);
////                Log log = new Log(obj.getUsuario().getCod_usuario(), ipAddress, "Aplicacion Ruv", "Buscar por codigo Ruv: "+ cod_sistema, 0,"usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
////                logDao.insert(1, log);
//
//            }
            connection.close();
        } catch (SQLException e) {
//            try {
////                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
////                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
////                String ipAddress = request.getHeader("X-FORWARDED-FOR");
////                if (ipAddress == null) {
////                    ipAddress = request.getRemoteAddr();
////                }
////                System.out.println("ipAddress:" + ipAddress);
////                Log log = new Log(obj.getUsuario().getCod_usuario(), ipAddress, "Aplicacion Ruv", "Buscar por codigo Ruv: "+ cod_sistema, 0,"usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
////                logDao.insert(2, log);
////                CallableStatement l = connectionL.prepareCall("select sp_operaciones_log(?,?,?,?,?,?,?)");
//
//            } catch (Exception ex) {
            e.getMessage();
//            }
        }
//        catch (Exception e) {
//            try {
////                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
////                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
////                String ipAddress = request.getHeader("X-FORWARDED-FOR");
////                if (ipAddress == null) {
////                    ipAddress = request.getRemoteAddr();
////                }
////                System.out.println("ipAddress:" + ipAddress);
////                Log log = new Log(obj.getUsuario().getCod_usuario(), ipAddress, "Aplicacion Ruv", "Buscar por codigo Ruv: "+ cod_sistema, 0,"usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
////                logDao.insert(2, log);
//                
//            } catch (Exception e1) {
//                e1.getMessage();
//            }
//        } finally {
//            try {
////                if (connection != null) {
////                    connection.close();
////                }
//                if (connectionL != null) {
//                    connectionL.close();
//                }
//            } catch (Exception e) {
//                e.getMessage();
//            }
//        }
        return list_base_mod;
    }

    @Override
    public List<Base_model> find_mod(String vic_numero, String vic_nombre, String vic_paterno, String vic_materno, String agr_numero, String agr_nombre, String agr_paterno, String agr_materno) {
        List<Base_model> list_base_mod = new ArrayList<>();
        LogDao logDao = new LogDaoImpl();
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        int sw = 0;
        ConnectionLDB connL = new ConnectionLDB();
        Connection connectionL = connL.getConnectionDB();

        try {
            System.out.println("FIND_MOD:" + vic_numero);
            String query = "select * from sp_find_viewfindruv('" + vic_numero + "','" + vic_nombre + "','" + vic_paterno + "','" + vic_materno + "','" + agr_numero + "','" + agr_nombre + "','" + agr_paterno + "','" + agr_materno + "')";

            Statement statement = connection.createStatement();
            System.out.println("query:" + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Base_model base_mod = new Base_model();

                base_mod.getVicAgre_persona().setId_vicAgre_persona(resultSet.getInt("id_vicAgre_persona"));
                base_mod.getVictima_agresor().setTviolencia(resultSet.getString("tviolencia"));
                base_mod.getPersonaVictima().setNumero(resultSet.getString("vic_numero"));
                base_mod.getPersonaVictima().setId_persona(resultSet.getInt("vic_id_persona"));
                base_mod.getPersonaVictima().setNombre(resultSet.getString("vic_nombre"));
                base_mod.getPersonaVictima().setPaterno(resultSet.getString("vic_paterno"));
                base_mod.getPersonaVictima().setMaterno(resultSet.getString("vic_materno"));
                base_mod.getPersonaAgresor().setNumero(resultSet.getString("agr_numero"));
                base_mod.getPersonaAgresor().setId_persona(resultSet.getInt("agr_id_persona"));
                base_mod.getPersonaAgresor().setNombre(resultSet.getString("agr_nombre"));
                base_mod.getPersonaAgresor().setPaterno(resultSet.getString("agr_paterno"));
                base_mod.getPersonaAgresor().setMaterno(resultSet.getString("agr_materno"));
                base_mod.getUsuario_vicagre().setId_usuario_vicagre(resultSet.getInt("id_usuario_vicagre"));
                base_mod.getUsuario_vicagre().setUsuario_cod_usuario(resultSet.getInt("usuario_cod_usuario"));
                base_mod.getUsuario_vicagre().setVictima_agresor_id_vicagre(resultSet.getInt("victima_agresor_id_vicagre"));
                base_mod.getUsuario_vicagre().setFecha_reg(resultSet.getDate("fecha_reg"));
                base_mod.getUsuario_vicagre().setNro_caso(resultSet.getInt("nro_caso"));
                base_mod.getUsuario_vicagre().setCod_sistema(resultSet.getString("cod_sistema"));
                base_mod.getUsuario_vicagre().setFecha_system(resultSet.getDate("fecha_system"));

                list_base_mod.add(base_mod);
            }
            boolean aux = false;
            if (sw != 0 || aux == resultSet.next()) {
                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                Log log = new Log(obj.getUsuario().getCod_usuario(), ipAddress, "Buscar por Detalle: " + vic_numero + "," + vic_nombre + "," + vic_paterno + "," + vic_materno, 0, "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                logDao.insert(1, log);
            }

        } catch (SQLException e) {
            try {
                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                Log log = new Log(obj.getUsuario().getCod_usuario(), ipAddress, "Buscar por Detalle: " + vic_numero + "," + vic_nombre + "," + vic_paterno + "," + vic_materno, 0, "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                logDao.insert(2, log);

            } catch (Exception ex1) {
                ex1.getMessage();
            }
        } catch (Exception e) {
            try {
                UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                System.out.println("ipAddress:" + ipAddress);
                Log log = new Log(obj.getUsuario().getCod_usuario(), ipAddress, "Buscar por Detalle: " + vic_numero + "," + vic_nombre + "," + vic_paterno + "," + vic_materno, 0, "usuario_vicagre, victima_agresor, vicagre_persona, persona, persona_variables");
                logDao.insert(1, log);

            } catch (Exception e1) {
                e1.getMessage();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
                e.getMessage();
            }
        }
        return list_base_mod;
    }

//    @Override
//    public String devcodper() {
//        //int dev = 696;
//        String dev = "";
//        int encontro = 0;
//        try {
//            String query = "select nombre from persona where id_persona = 0";
//            ConnectionDB conn = new ConnectionDB();
//            Connection connection = conn.getConnectionDB();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            if (resultSet.next()) {
//                dev = resultSet.getString("nombre");
//                encontro = 1;
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error SqlExeption" + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error :" + e.getMessage());
//        }
//        return dev;
//    }
    @Override
    public int devcodper() {
        int dev1 = 696;
        String dev = "";
        int encontro = 0;
        try {
            String query = "select nombre from persona where id_persona = 0";
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                dev = resultSet.getString("nombre");
                dev1 = 1;
            } else {
                dev1 = 2;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        System.out.println("DEVCODPER_METODO : " + dev1);
        return dev1;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    @Override
    public Persona find_exist_ci(Persona persona) {
        Persona registro = new Persona();
        try {
            String query = "select * from persona where numero = '" + persona.getNumero() + "' and codigo_documento = " + persona.getCodigo_documento();
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                registro.setId_persona(resultSet.getInt("id_persona"));
                registro.setNombre(resultSet.getString("nombre"));
                registro.setPaterno(resultSet.getString("paterno"));
                registro.setMaterno(resultSet.getString("materno"));
                registro.setAp_casada(resultSet.getString("ap_casada"));
                registro.setNumero(resultSet.getString("numero"));
                registro.setCodigo_documento(resultSet.getInt("codigo_documento"));
                registro.setSexo(resultSet.getString("sexo"));
                registro.setLugar_nac(resultSet.getInt("lugar_nac"));
                registro.setEsp_lugar_nac(resultSet.getString("esp_lugar_nac"));
                registro.setFecha_nac(resultSet.getDate("fecha_nac"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            e.printStackTrace();
        }
        return registro;
    }

    @Override
    public Persona_variables find_exist_detalle(Persona persona) {

        Persona_variables reg = new Persona_variables();
        try {
            String query = "select * from persona_variables "
                    + "where id_pervar = ( select max(id_pervar) "
                    + "from persona_variables where id_persona = (select id_persona from persona where numero = '" + persona.getNumero() + "' and codigo_documento = " + persona.getCodigo_documento() + "))";
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("entraaaaaaaaaaaaaaaaaaaaaaaaa aquiiiiiiiiiiiiiiiii");
                reg.setId_pervar(resultSet.getInt("id_pervar"));
                reg.setEdad(resultSet.getInt("edad"));
                reg.setEst_civil(resultSet.getInt("est_civil"));
                reg.setNro_hijos(resultSet.getInt("nro_hijos"));
                reg.setGestacion_h(resultSet.getInt("gestacion_h"));
                reg.setNum_miembros_fam(resultSet.getInt("num_miembros_fam"));
                reg.setMunicipio(resultSet.getBoolean("municipio"));
                reg.setEsp_municipio(resultSet.getString("esp_municipio"));
                reg.setArea(resultSet.getBoolean("area"));
                reg.setEsp_area(resultSet.getString("esp_area"));
                reg.setVivienda(resultSet.getInt("vivienda"));
                reg.setNivel_inst(resultSet.getInt("nivel_inst"));
                reg.setCondicion_act(resultSet.getBoolean("condicion_act"));
                reg.setOcupacion(resultSet.getInt("ocupacion"));
                reg.setEsp_ocupacion(resultSet.getString("esp_ocupacion"));
                reg.setCargo(resultSet.getString("cargo"));
                reg.setIngre_economico(resultSet.getInt("ingre_economico"));
                reg.setMonto_aprox_bs(resultSet.getBigDecimal("monto_aprox_bs"));
                reg.setAporte_familiar_bs(resultSet.getBigDecimal("aporte_familiar_bs"));
                reg.setIdiomas(resultSet.getString("idiomas"));
                reg.setEsp_idioma(resultSet.getString("esp_idioma"));
                reg.setEtnia(resultSet.getBoolean("etnia"));
                reg.setEsp_etnia(resultSet.getString("esp_etnia"));
                reg.setLgbti(resultSet.getBoolean("lgbti"));
                reg.setPers_discapacidad(resultSet.getBoolean("pers_discapacidad"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("regggggggggggg" + reg.getOcupacion());
        return reg;
    }

    @Override
    public boolean find_exist_ci_save(Persona persona) {
        Persona registro = new Persona();
        boolean resp = false;
        try {
            String query = "select * from persona where numero = '" + persona.getNumero() + "' and codigo_documento = " + persona.getCodigo_documento();
            ConnectionDB conn = new ConnectionDB();
            Connection connection = conn.getConnectionDB();
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeNTRA");
                registro.setId_persona(resultSet.getInt("id_persona"));
                registro.setNombre(resultSet.getString("nombre"));
                registro.setPaterno(resultSet.getString("paterno"));
                registro.setMaterno(resultSet.getString("materno"));
                registro.setAp_casada(resultSet.getString("ap_casada"));
                registro.setNumero(resultSet.getString("numero"));
                registro.setCodigo_documento(resultSet.getInt("codigo_documento"));
                registro.setSexo(resultSet.getString("sexo"));
                registro.setLugar_nac(resultSet.getInt("lugar_nac"));
                registro.setEsp_lugar_nac(resultSet.getString("esp_lugar_nac"));
                registro.setFecha_nac(resultSet.getDate("fecha_nac"));
                resp = true;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return resp;
    }
/*
    @Override
    public int insert_log(Base_model base_model, int id_log, int exist) {
        PersonaDao personaDao = new PersonaDaoImpl();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        Usuario_vicagreDao usuario_vicagreDao = new Usuario_vicagreDaoImpl();
        Victima_agresorDao victima_agresorDao = new Victima_agresorDaoImpl();
        VicAgre_personaDao vicAgre_personaDao = new VicAgre_personaDaoImpl();
        Persona_VariablesDao persona_VariablesDao = new Persona_VariablesDaoImpl();
        LogDao logDao = new LogDaoImpl();
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();
        int aux12 = 0;
        int res12 = 0;
        
        try {
            if (exist == 1) {
                connection.setAutoCommit(false);
                CallableStatement cs = connection.prepareCall("select sp_operaciones_persona_log(?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("entro LOG PERSONA VICTIMA : " + personaDao.max_log() + 1);
                cs.setInt(1, 1);
                cs.setInt(2, personaDao.max_log() + 1);
                cs.setString(3, base_model.getPersonaVictima().getNombre());
                cs.setString(4, base_model.getPersonaVictima().getPaterno());
                cs.setString(5, base_model.getPersonaVictima().getMaterno());
                cs.setString(6, base_model.getPersonaVictima().getAp_casada());
                cs.setString(7, base_model.getPersonaVictima().getNumero());
                cs.setInt(8, base_model.getPersonaVictima().getCodigo_documento());
                cs.setString(9, base_model.getPersonaVictima().getSexo());
                cs.setInt(10, base_model.getPersonaVictima().getLugar_nac());
                cs.setString(11, base_model.getPersonaVictima().getEsp_lugar_nac());
                cs.setDate(12, new java.sql.Date(base_model.getPersonaVictima().getFecha_nac().getTime()));

                ResultSet resultSet = cs.executeQuery();

                CallableStatement pv = connection.prepareCall("select sp_operaciones_persona_variables_log(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("entro LOG PERSONA VARIABLES VICTIMA : ");

                System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max_log() + 1);
                pv.setInt(1, 1);
                pv.setInt(2, persona_VariablesDao.max_log() + 1);
                pv.setInt(3, personaDao.max_log() + 1);
                pv.setInt(4, base_model.getPersonaVariableVictima().getEdad());
                pv.setInt(5, base_model.getPersonaVariableVictima().getEst_civil());
                pv.setInt(6, base_model.getPersonaVariableVictima().getNro_hijos());
                pv.setInt(7, base_model.getPersonaVariableVictima().getGestacion_h());
                pv.setInt(8, base_model.getPersonaVariableVictima().getNum_miembros_fam());
                pv.setBoolean(9, base_model.getPersonaVariableVictima().isMunicipio());
                pv.setString(10, base_model.getPersonaVariableVictima().getEsp_municipio());
                pv.setBoolean(11, base_model.getPersonaVariableVictima().isArea());
                pv.setString(12, base_model.getPersonaVariableVictima().getEsp_area());
                pv.setInt(13, base_model.getPersonaVariableVictima().getVivienda());
                pv.setInt(14, base_model.getPersonaVariableVictima().getNivel_inst());
                pv.setBoolean(15, base_model.getPersonaVariableVictima().isCondicion_act());
                pv.setInt(16, base_model.getPersonaVariableVictima().getOcupacion());
                pv.setString(17, base_model.getPersonaVariableVictima().getEsp_ocupacion());
                pv.setString(18, base_model.getPersonaVariableVictima().getCargo());
                pv.setInt(19, base_model.getPersonaVariableVictima().getIngre_economico());
                pv.setBigDecimal(20, base_model.getPersonaVariableVictima().getMonto_aprox_bs());
                pv.setBigDecimal(21, base_model.getPersonaVariableVictima().getAporte_familiar_bs());
                pv.setString(22, base_model.getPersonaVariableVictima().getIdiomas());
                pv.setString(23, base_model.getPersonaVariableVictima().getEsp_idioma());
                pv.setBoolean(24, base_model.getPersonaVariableVictima().isEtnia());
                pv.setString(25, base_model.getPersonaVariableVictima().getEsp_etnia());
                pv.setBoolean(26, base_model.getPersonaVariableVictima().isLgbti());
                pv.setBoolean(27, base_model.getPersonaVariableVictima().isPers_discapacidad());
                System.out.println("ssssssss : ");
                ResultSet resultSet_pv = pv.executeQuery();
                System.out.println("personamax :" + personaDao.max_log());

                CallableStatement cs1 = connection.prepareCall("select sp_operaciones_persona_log(?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("entro LOG PERSONA AGRESOR : " + personaDao.max_log() + 2);
                cs1.setInt(1, 1);
                cs1.setInt(2, personaDao.max_log() + 2);
                cs1.setString(3, base_model.getPersonaAgresor().getNombre());
                cs1.setString(4, base_model.getPersonaAgresor().getPaterno());
                cs1.setString(5, base_model.getPersonaAgresor().getMaterno());
                cs1.setString(6, base_model.getPersonaAgresor().getAp_casada());
                cs1.setString(7, base_model.getPersonaAgresor().getNumero());
                cs1.setInt(8, base_model.getPersonaAgresor().getCodigo_documento());
                cs1.setString(9, base_model.getPersonaAgresor().getSexo());
                cs1.setInt(10, base_model.getPersonaAgresor().getLugar_nac());
                cs1.setString(11, base_model.getPersonaAgresor().getEsp_lugar_nac());
                cs1.setDate(12, new java.sql.Date(base_model.getPersonaAgresor().getFecha_nac().getTime()));

                ResultSet resultSet_cs1 = cs1.executeQuery();

                CallableStatement pv1 = connection.prepareCall("select sp_operaciones_persona_variables_log(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("entro LOG PERSONA VARIABLES AGRESOR : " + persona_VariablesDao.max_log() + 2);

                System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max_log()+ 2);
                pv1.setInt(1, 1);
                pv1.setInt(2, persona_VariablesDao.max_log() + 2);
                pv1.setInt(3, personaDao.max_log() + 2);
                pv1.setInt(4, base_model.getPersonaVariableAgresor().getEdad());
                pv1.setInt(5, base_model.getPersonaVariableAgresor().getEst_civil());
                pv1.setInt(6, base_model.getPersonaVariableAgresor().getNro_hijos());
                pv1.setInt(7, base_model.getPersonaVariableAgresor().getGestacion_h());
                pv1.setInt(8, base_model.getPersonaVariableAgresor().getNum_miembros_fam());
                pv1.setBoolean(9, base_model.getPersonaVariableAgresor().isMunicipio());
                pv1.setString(10, base_model.getPersonaVariableAgresor().getEsp_municipio());
                pv1.setBoolean(11, base_model.getPersonaVariableAgresor().isArea());
                pv1.setString(12, base_model.getPersonaVariableAgresor().getEsp_area());
                pv1.setInt(13, base_model.getPersonaVariableAgresor().getVivienda());
                pv1.setInt(14, base_model.getPersonaVariableAgresor().getNivel_inst());
                pv1.setBoolean(15, base_model.getPersonaVariableAgresor().isCondicion_act());
                pv1.setInt(16, base_model.getPersonaVariableAgresor().getOcupacion());
                pv1.setString(17, base_model.getPersonaVariableAgresor().getEsp_ocupacion());
                pv1.setString(18, base_model.getPersonaVariableAgresor().getCargo());
                pv1.setInt(19, base_model.getPersonaVariableAgresor().getIngre_economico());
                pv1.setBigDecimal(20, base_model.getPersonaVariableAgresor().getMonto_aprox_bs());
                pv1.setBigDecimal(21, base_model.getPersonaVariableAgresor().getAporte_familiar_bs());
                pv1.setString(22, base_model.getPersonaVariableAgresor().getIdiomas());
                pv1.setString(23, base_model.getPersonaVariableAgresor().getEsp_idioma());
                pv1.setBoolean(24, base_model.getPersonaVariableAgresor().isEtnia());
                pv1.setString(25, base_model.getPersonaVariableAgresor().getEsp_etnia());
                pv1.setBoolean(26, base_model.getPersonaVariableAgresor().isLgbti());
                pv1.setBoolean(27, base_model.getPersonaVariableAgresor().isPers_discapacidad());

                ResultSet resultSet_pv1 = pv1.executeQuery();
                //VICTIMA AGRESOR LOG, VER SI SE NECESITA    
                
                CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor_log(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//la funcion original sp_operaciones_victima_agresor(?...18)
                System.out.println("entro VICTIMA AGRESOR" + victima_agresorDao.max_log() + 1);
                va.setInt(1, 1);
                va.setInt(2, victima_agresorDao.max_log() + 1);
                va.setString(3, base_model.getVictima_agresor().getTestimonio());
                va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                va.setBoolean(7, base_model.getVictima_agresor().isArea());
                va.setString(8, base_model.getVictima_agresor().getEsp_area());
                va.setInt(9, base_model.getVictima_agresor().getParentesco());
                va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                va.setString(11, base_model.getVictima_agresor().getTviolencia());
                va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                va.setInt(16, base_model.getVictima_agresor().getFrec_consumo_alcohol());
                va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                va.setInt(18, base_model.getVictima_agresor().getFrec_consumo_drogas());
//INICIO Preguntas Alertas
                
                    va.setInt(19, base_model.getVictima_agresor().getPreg1());
                    va.setInt(20, base_model.getVictima_agresor().getPreg2());
                    va.setInt(21, base_model.getVictima_agresor().getPreg3());
                    va.setInt(22, base_model.getVictima_agresor().getPreg4());
                    va.setInt(23, base_model.getVictima_agresor().getPreg5());
                    va.setInt(24, base_model.getVictima_agresor().getPreg6());
                    va.setInt(25, base_model.getVictima_agresor().getPreg7());
                    va.setInt(26, base_model.getVictima_agresor().getPreg8());
                    va.setInt(27, base_model.getVictima_agresor().getPreg9());
                    va.setInt(28, base_model.getVictima_agresor().getPreg10());
                    va.setInt(29, base_model.getVictima_agresor().getPreg11());
                    va.setInt(30, base_model.getVictima_agresor().getPreg12());
                    va.setInt(31, base_model.getVictima_agresor().getPreg13());
                    va.setInt(32, base_model.getVictima_agresor().getPreg14());
                    va.setInt(33, base_model.getVictima_agresor().getPreg15());
                    va.setInt(34, base_model.getVictima_agresor().getPreg16());
                    va.setInt(35, base_model.getVictima_agresor().getPreg17());
                    va.setInt(36, base_model.getVictima_agresor().getPreg18());
                    va.setInt(37, base_model.getVictima_agresor().getPreg19());
                    va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
                
                //FIN Preguntas Alertas
                
                ResultSet resultSet_va = va.executeQuery();

                CallableStatement vp = connection.prepareCall("select sp_operaciones_vicagre_persona_log(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("entro VICAGRE PERSONA" + vicAgre_personaDao.max_log() + 1);
                System.out.println("entro VICTIMA AGRESOR" + victima_agresorDao.max_log() + 1);
                System.out.println("entro PERSONA VICTIMA " + personaDao.max_log()+ 1);
                System.out.println("entro PERSONA AGRESOR" + personaDao.max_log() + 2);
                aux12 = vicAgre_personaDao.max_log() + 1;
                vp.setInt(1, 1);
                vp.setInt(2, vicAgre_personaDao.max_log() + 1);
                vp.setInt(3, victima_agresorDao.max_log() + 1);
                vp.setInt(4, personaDao.max_log() + 2);
                //vp.setInt(4, 1);
                vp.setInt(5, personaDao.max_log() + 1);
                vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                vp.setInt(12, persona_VariablesDao.max_log() + 1);
                vp.setInt(13, persona_VariablesDao.max_log() + 2);

                ResultSet resultSet_vp = vp.executeQuery();
                
                
                System.out.println("2222 : " + base_model.getUsuario_vicagre().getId_usuario_vicagre());
                System.out.println("3333 : " + base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                System.out.println("4444 : " + victima_agresorDao.max_log() + 1);
                System.out.println("5555 : " + base_model.getUsuario_vicagre().getFecha_reg().getTime());
                
                System.out.println("66666 : " + base_model.getUsuario_vicagre().getCod_sistema() + "-" + base_model.getUsuario_vicagre().getUsuario_cod_usuario() + "-" + usuarioDao.datoSubServicio(base_model.getUsuario_vicagre().getUsuario_cod_usuario()));
                System.out.println("7777 : " + usuario_vicagreDao.max_log() + 1);
                System.out.println("88888 : " + id_log);
                
                
                
                CallableStatement us = connection.prepareCall("select sp_operaciones_usuario_vicagre_log(?,?,?,?,?,?,?,?)");
                System.out.println("entro USUARIO VICAGRE");

                us.setInt(1, 1);
                us.setInt(2, usuario_vicagreDao.max_log() + 1);
                us.setInt(3, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                us.setInt(4, victima_agresorDao.max_log() + 1);
                us.setDate(5, new java.sql.Date(base_model.getUsuario_vicagre().getFecha_reg().getTime()));
                //us.setDate(5, (java.sql.Date) usuario_vicagre1.getFecha_reg());
                us.setInt(6, usuario_vicagreDao.max_log() + 1);
                us.setString(7, base_model.getUsuario_vicagre().getCod_sistema() + "-" + base_model.getUsuario_vicagre().getUsuario_cod_usuario() + "-" + usuarioDao.datoSubServicio(base_model.getUsuario_vicagre().getUsuario_cod_usuario()));
                us.setInt(8, id_log);
                ResultSet resultSet_us = us.executeQuery();

                connection.commit();
                connection.close();
                res12 = aux12;
            } else {
                connection.setAutoCommit(false);
                CallableStatement cs = connection.prepareCall("select sp_operaciones_persona_log(?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("PERSONA VICTIMA :" + (personaDao.max_log()+ 1));
                cs.setInt(1, 1);
                cs.setInt(2, personaDao.max_log()+ 1);
                cs.setString(3, base_model.getPersonaVictima().getNombre());
                cs.setString(4, base_model.getPersonaVictima().getPaterno());
                cs.setString(5, base_model.getPersonaVictima().getMaterno());
                cs.setString(6, base_model.getPersonaVictima().getAp_casada());
                cs.setString(7, base_model.getPersonaVictima().getNumero());
                cs.setInt(8, base_model.getPersonaVictima().getCodigo_documento());
                cs.setString(9, base_model.getPersonaVictima().getSexo());
                cs.setInt(10, base_model.getPersonaVictima().getLugar_nac());
                cs.setString(11, base_model.getPersonaVictima().getEsp_lugar_nac());
                cs.setDate(12, new java.sql.Date(base_model.getPersonaVictima().getFecha_nac().getTime()));

                ResultSet resultSet = cs.executeQuery();
                CallableStatement va = connection.prepareCall("select coco_operaciones_victima_agresor_log(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//la funcion original sp_operaciones_victima_agresor(?...18)
                System.out.println("VICTIMAS AGRESOR :" + victima_agresorDao.max() + 1);
                va.setInt(1, 1);

                va.setInt(2, victima_agresorDao.max_log()+ 1);
                // va.setInt(2, 1);
                va.setString(3, base_model.getVictima_agresor().getTestimonio());
                va.setInt(4, base_model.getVictima_agresor().getLugar_agresion());
                va.setBoolean(5, base_model.getVictima_agresor().isMunicipio());
                va.setString(6, base_model.getVictima_agresor().getEsp_municipio());
                va.setBoolean(7, base_model.getVictima_agresor().isArea());
                va.setString(8, base_model.getVictima_agresor().getEsp_area());
                va.setInt(9, base_model.getVictima_agresor().getParentesco());
                va.setString(10, base_model.getVictima_agresor().getEsp_parentesco());
                va.setString(11, base_model.getVictima_agresor().getTviolencia());
                va.setBoolean(12, base_model.getVictima_agresor().isDenuncio());
                va.setString(13, base_model.getVictima_agresor().getDonde_denuncio());
                va.setInt(14, base_model.getVictima_agresor().getFrec_agresiones());
                va.setBoolean(15, base_model.getVictima_agresor().isAgre_consume_alcohol());
                va.setInt(16, 0);
                va.setBoolean(17, base_model.getVictima_agresor().isAgre_consume_drogas());
                va.setInt(18, 0);
//INICIO Preguntas Alertas
                
                    va.setInt(20, base_model.getVictima_agresor().getPreg2());
                    va.setInt(21, base_model.getVictima_agresor().getPreg3());
                    va.setInt(22, base_model.getVictima_agresor().getPreg4());
                    va.setInt(23, base_model.getVictima_agresor().getPreg5());
                    va.setInt(24, base_model.getVictima_agresor().getPreg6());
                    va.setInt(25, base_model.getVictima_agresor().getPreg7());
                    va.setInt(26, base_model.getVictima_agresor().getPreg8());
                    va.setInt(27, base_model.getVictima_agresor().getPreg9());
                    va.setInt(28, base_model.getVictima_agresor().getPreg10());
                    va.setInt(29, base_model.getVictima_agresor().getPreg11());
                    va.setInt(30, base_model.getVictima_agresor().getPreg12());
                    va.setInt(31, base_model.getVictima_agresor().getPreg13());
                    va.setInt(32, base_model.getVictima_agresor().getPreg14());
                    va.setInt(33, base_model.getVictima_agresor().getPreg15());
                    va.setInt(34, base_model.getVictima_agresor().getPreg16());
                    va.setInt(35, base_model.getVictima_agresor().getPreg17());
                    va.setInt(36, base_model.getVictima_agresor().getPreg18());
                    va.setInt(37, base_model.getVictima_agresor().getPreg19());
                    va.setInt(38, base_model.getVictima_agresor().getPreg_suma());
//FIN Preguntas Alertas  
                ResultSet resultSet_va = va.executeQuery();

                CallableStatement pv = connection.prepareCall("select sp_operaciones_persona_variables_log(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("PERSONA VARIABLES VICTIMA  :" + personaDao.max() + 1);
                pv.setInt(1, 1);
                pv.setInt(2, persona_VariablesDao.max_log()+ 1);
                pv.setInt(3, personaDao.max_log()+ 1);
                pv.setInt(4, base_model.getPersonaVariableVictima().getEdad());
                pv.setInt(5, base_model.getPersonaVariableVictima().getEst_civil());
                pv.setInt(6, base_model.getPersonaVariableVictima().getNro_hijos());
                pv.setInt(7, base_model.getPersonaVariableVictima().getGestacion_h());
                pv.setInt(8, base_model.getPersonaVariableVictima().getNum_miembros_fam());
                pv.setBoolean(9, base_model.getPersonaVariableVictima().isMunicipio());
                pv.setString(10, base_model.getPersonaVariableVictima().getEsp_municipio());
                pv.setBoolean(11, base_model.getPersonaVariableVictima().isArea());
                pv.setString(12, base_model.getPersonaVariableVictima().getEsp_area());
                pv.setInt(13, base_model.getPersonaVariableVictima().getVivienda());
                pv.setInt(14, base_model.getPersonaVariableVictima().getNivel_inst());
                pv.setBoolean(15, base_model.getPersonaVariableVictima().isCondicion_act());
                pv.setInt(16, base_model.getPersonaVariableVictima().getOcupacion());
                pv.setString(17, base_model.getPersonaVariableVictima().getEsp_ocupacion());
                pv.setString(18, base_model.getPersonaVariableVictima().getCargo());
                pv.setInt(19, base_model.getPersonaVariableVictima().getIngre_economico());
                pv.setBigDecimal(20, base_model.getPersonaVariableVictima().getMonto_aprox_bs());
                pv.setBigDecimal(21, base_model.getPersonaVariableVictima().getAporte_familiar_bs());
                pv.setString(22, base_model.getPersonaVariableVictima().getIdiomas());
                pv.setString(23, base_model.getPersonaVariableVictima().getEsp_idioma());
                pv.setBoolean(24, base_model.getPersonaVariableVictima().isEtnia());
                pv.setString(25, base_model.getPersonaVariableVictima().getEsp_etnia());
                pv.setBoolean(26, base_model.getPersonaVariableVictima().isLgbti());
                pv.setBoolean(27, base_model.getPersonaVariableVictima().isPers_discapacidad());

                ResultSet resultSet_pv = pv.executeQuery();

                CallableStatement vp = connection.prepareCall("select sp_operaciones_vicagre_persona_log(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("VICAGRE PERSONA2 :" + vicAgre_personaDao.max_log()+ 1);
                System.out.println("VICAGRE PERSONA3 :" + victima_agresorDao.max_log()+ 1);

                vp.setInt(1, 1);
                vp.setInt(2, vicAgre_personaDao.max_log()+ 1);
                vp.setInt(3, victima_agresorDao.max_log()+ 1);
                //vp.setInt(4, personaDao.max() + 2);
                vp.setInt(4, 0);
                vp.setInt(5, personaDao.max_log()+ 1);
                vp.setString(6, base_model.getVicAgre_persona().getReferencia_caso_de());
                vp.setString(7, base_model.getVicAgre_persona().getReferencia_esp_caso());
                vp.setString(8, base_model.getVicAgre_persona().getContra_ref_caso_de());
                vp.setString(9, base_model.getVicAgre_persona().getContra_ref_esp_caso());
                vp.setBoolean(10, base_model.getVicAgre_persona().isCaso_resuelto());
                vp.setBoolean(11, base_model.getVicAgre_persona().isCaso_abandonado());
                vp.setInt(12, persona_VariablesDao.max_log()+ 1);
                System.out.println("idPERSONAVARIABLES : " + persona_VariablesDao.max_log());
                vp.setInt(13, 0);

                ResultSet resultSet_vp = vp.executeQuery();
//
                aux12 = vicAgre_personaDao.max_log() + 1;
                CallableStatement us = connection.prepareCall("select sp_operaciones_usuario_vicagre_log(?,?,?,?,?,?,?,?)");
                System.out.println("entro USUARIO VICAGRE");

                us.setInt(1, 1);
                us.setInt(2, usuario_vicagreDao.max_log() + 1);
                us.setInt(3, base_model.getUsuario_vicagre().getUsuario_cod_usuario());
                us.setInt(4, victima_agresorDao.max_log() + 1);
                us.setDate(5, new java.sql.Date(base_model.getUsuario_vicagre().getFecha_reg().getTime()));
                //us.setDate(5, (java.sql.Date) usuario_vicagre1.getFecha_reg());
                us.setInt(6, usuario_vicagreDao.max_log() + 1);
                us.setString(7, base_model.getUsuario_vicagre().getCod_sistema() + "-" + base_model.getUsuario_vicagre().getUsuario_cod_usuario() + "-" + usuarioDao.datoSubServicio(base_model.getUsuario_vicagre().getUsuario_cod_usuario()));
                us.setInt(8, id_log);
                ResultSet resultSet_us = us.executeQuery();
                connection.commit();
                connection.close();
                res12 = aux12;

            }

        } catch (SQLException e) {
            System.out.println("dddd :" + e.getStackTrace());

        } catch (Exception e1) {
            System.out.println("dddd :" + e1.getStackTrace());
        }

        return res12;

    }
*/
/*   
    @Override
    public int insertAgre_log() {
        ConnectionDB conn = new ConnectionDB();
        Connection connection = conn.getConnectionDB();

        try {
            connection.setAutoCommit(false);

            CallableStatement cs1 = connection.prepareCall("select sp_operaciones_persona_log(?,?,?,?,?,?,?,?,?,?,?,?)");
            cs1.setInt(1, 1);
            cs1.setInt(2, 0);
            cs1.setString(3, "0");
            cs1.setString(4, "0");
            cs1.setString(5, "0");
            cs1.setString(6, "0");
            cs1.setString(7, "0");
            cs1.setInt(8, 0);
            cs1.setString(9, "0");
            cs1.setInt(10, 0);
            cs1.setString(11, "0");
            cs1.setDate(12, new java.sql.Date(new Date().getTime()));

            ResultSet resultSet_cs1 = cs1.executeQuery();

            CallableStatement pv1 = connection.prepareCall("select sp_operaciones_persona_variables_log(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pv1.setInt(1, 1);
            pv1.setInt(2, 0);
            pv1.setInt(3, 0);
            pv1.setInt(4, 0);
            pv1.setInt(5, 0);
            pv1.setInt(6, 0);
            pv1.setInt(7, 0);
            pv1.setInt(8, 0);
            pv1.setBoolean(9, false);
            pv1.setString(10, "0");
            pv1.setBoolean(11, false);
            pv1.setString(12, "0");
            pv1.setInt(13, 0);
            pv1.setInt(14, 0);
            pv1.setBoolean(15, false);
            pv1.setInt(16, 0);
            pv1.setString(17, "0");
            pv1.setString(18, "0");
            pv1.setInt(19, 0);
            pv1.setBigDecimal(20, new BigDecimal(BigInteger.ONE));
            pv1.setBigDecimal(21, new BigDecimal(BigInteger.ONE));
            pv1.setString(22, "0");
            pv1.setString(23, "0");
            pv1.setBoolean(24, false);
            pv1.setString(25, "0");
            pv1.setBoolean(26, false);
            pv1.setBoolean(27, false);

            ResultSet resultSet_pv1 = pv1.executeQuery();

            connection.commit();
            connection.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return 1;
    }
*/
}

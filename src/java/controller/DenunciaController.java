/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Base_modelDao;
import dao.Base_modelDaoImpl;
import dao.OperacionDao;
import dao.OperacionDaoImpl;
import dao.T_datosDao;
import dao.T_datosDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import model.Base_model;
import model.MenuView;
import model.Operacion;
import model.Persona;
import model.Persona_variables;
import model.T_datos;
import model.Usuario;
import model.Usuario_vicagre;
import model.Victima_agresor;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import util.ConnectionDB;
import util.ConnectionUDB;

/**
 *
 * @author e_mvz
 */
@ManagedBean
@SessionScoped
public class DenunciaController implements Serializable {

    private boolean flag_RecCaso = false;
    private boolean flag_DocIden = false;
    private boolean flag_ApCasada = false;
    private boolean flag_LNac = false;
    private boolean flag_Municipio = false;
    private boolean flag_ocupacion_principal = false;
    private boolean flag_idioma_hablado = false;
    private boolean flag_pueblo_orig = false;
    private boolean flag_tipo_violencia = false;
    private boolean flag_especi_agresor = false;
    private boolean flag_MotivoMunicipioEsp = false;
    private boolean flag_DenuncioAnterior = false;
    private boolean flag_ConsumeAlc = false;
    private boolean flag_ConsumeDro = false;
    private boolean flagOpcion = false;
    //agresor
    private boolean flag_LugarAgresión = false;
    private boolean flag_DocIdenAgresor = false;
    private boolean flag_ApCasadaAgresor = false;
    private boolean flag_LNacAgresor = false;
    private boolean flag_MunicipioAgresor = false;
    private String selectOneRadioMunicioAgresor;
    private String selectOneRadioBarrioAgresor;
    private String selectOneRadioCondicion_actAgresor;
    private boolean flag_ocupacion_principalAgresor = false;
    

    //denuncia dialog    
    private boolean flagDialogVictimaAgresor = false;
    private boolean flagDialogPerfilAgresor = false;
    private boolean flagItemPerfilAgresor = false;
    private boolean flagDialogAgresor = false;

    private String selectOneRadioMunicio;
    private String selectOneRadioBarrio;
    private String selectOneRadioPueblOrig;
    private String selectOneRadioMotivoMunicipio;
    private String selectOneRadioMotivoBarrio;
    private String selectOneRadioMotivoDenuncioAnt;
    private String selectOneRadioMotivoConsumeDro;
    private String selectOneRadioMotivoConsumeAlc;
    private String selectOneRadioCondicion_act;
    
    //Despues del caso dialog
    private boolean flag_despues_caso;    

   
    /*Inicio Preguntas*/
    private String selectPreg1;    
    private String selectPreg2;    
    private String selectPreg3;
    private String selectPreg4;
    private String selectPreg5;
    private String selectPreg6;
    private String selectPreg7;
    private String selectPreg8;
    private String selectPreg9;
    private String selectPreg10;
    private String selectPreg11;
    private String selectPreg12;
    private String selectPreg13;
    private String selectPreg14;
    private String selectPreg15;
    private String selectPreg16;
    private String selectPreg17;
    private String selectPreg18;
    private String selectPreg19;
        
    private String selectPreg_suma; 
       
    
    /*FIN Preguntas*/
    private Base_model base_model; //var eddy
//    private Persona persona_victima;  //var eddy
//    private Persona persona_agresor;  //var eddy

    private String[] selectedRelacionVicAgr;

    private List<T_datos> listaResult;//variable Idioma Hablado
    private List<T_datos> listaSelect;//variable Idioma Hablado
    private List<T_datos> listaIdimaHablado;

    private List<T_datos> listaResultTipoViolencia;
    private List<T_datos> listaSelectTipoViolencia;
    private List<T_datos> listaTipoViolencia;
    /////////
    private String url_servlet;

    //NUEVO CODIGO BASE_MODEL   
       
    public DenunciaController() {
        //busqueda
        this.flag_tblResultadoFind_codruv = false;
        Base_modelDao base_modDao = new Base_modelDaoImpl();
        list_base_models = base_modDao.find_year();
        filtro_persona = new Persona();
        filtro_persona_agre = new Persona();
        filtro_usuario_vicagre = new Usuario_vicagre();
        System.out.println("ENTRA CONSTRUCTOR");
        base_model = new Base_model();

        listaSelect = new ArrayList<>();
        listaIdimaHablado = new ArrayList<>();
        base_model.getPersonaVariableVictima().setEst_civil(0);
        this.flagOpcion = false;
        
         T_datosDao personaDao = new T_datosDaoImpl();

        listaResult = personaDao.findAllIdt("idioma_hablado");
        listaSelect = new ArrayList<>();
        listaIdimaHablado = listaResult;

        listaResultTipoViolencia = personaDao.findAllIdt("ambito_tipo_violencia");
        listaSelectTipoViolencia = new ArrayList<>();
        listaTipoViolencia = listaResultTipoViolencia;
            
        
    }  
    
    
    public void buttonSeleccionaFecha(AjaxBehaviorEvent a) {
        if (a != null) {
            org.primefaces.component.calendar.Calendar cal = (org.primefaces.component.calendar.Calendar) a.getSource();
            if (cal != null) {
                System.out.println("mi valor:" + cal.getLocalValue());
            }
        }
    }
    
    public String actionMenuDespuesCaso(){
        String res = "fi_fiv";
        return res;
    }
    
    
    public String actionMenuItemMotivo() {
        String res = null;        
        
        if (listaSelect.size() > 0) {
            String idiomas = "";
            Integer contador = 0;
            for (T_datos item : listaSelect) {
                if (contador > 0) {
                    idiomas = idiomas + item.getId_tdatos() + ",";
                } else {
                    idiomas = "," + item.getId_tdatos() + ",";
                }
                contador++;
            }

            base_model.getPersonaVariableVictima().setIdiomas(idiomas);
            res = "fi_fii";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: Seleccione al menos un idioma", "Seleccione al menos un idioma");
            FacesContext.getCurrentInstance().addMessage(null, message);            
        }
        return res;
    }

    public String action_formiii_formii() {

        this.flagItemPerfilAgresor = false;
//        base_model.setPersonaAgresor(new Persona());
//        base_model.getVictima_agresor().setFrec_consumo_alcohol(null);
//        base_model.getVictima_agresor().setFrec_consumo_drogas(null);
//        base_model.setPersonaVariableAgresor(new Persona_variables());

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formulario");
        return "fiii_fii";
    }

    public String action_formiii_formi() {

        this.flagItemPerfilAgresor = false;
//        base_model.setPersonaAgresor(new Persona());
//        base_model.getVictima_agresor().setFrec_consumo_alcohol(null);
//        base_model.getVictima_agresor().setFrec_consumo_drogas(null);
//        base_model.setPersonaVariableAgresor(new Persona_variables());

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formulario");
        return "fiii_fi";
    }

    public String action_item_formi() {

        this.flagItemPerfilAgresor = false;
        base_model.setPersonaAgresor(null);
        base_model.getVictima_agresor().setFrec_consumo_alcohol(null);
        base_model.getVictima_agresor().setFrec_consumo_drogas(null);
        base_model.setPersonaVariableAgresor(null);

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formulario");
        return "fiii_fi";
    }

    public void actionMenuItemAgresor() {
        this.flagDialogPerfilAgresor = true;
    }

    public void handleCIChange() {
        this.flag_DocIden = false;
        System.out.println("CodigoDocumento:"+ base_model.getPersonaVictima().getCodigo_documento());
        if (base_model.getPersonaVictima().getCodigo_documento() != null ) {
            this.flag_DocIden = true;
        }else{
            base_model.getPersonaVictima().setCodigo_documento(null);
        }
    }

    public void handleCIChange_agre() {
        this.flag_DocIdenAgresor = false;
        if (base_model.getPersonaAgresor().getCodigo_documento() != null ) {
            this.flag_DocIdenAgresor = true;
        }
    }

    public void onCountryChange() {
        this.flag_ApCasada = false;
        String eCivil = "";
        T_datosDao personaDao = new T_datosDaoImpl();
        List<SelectItem> arrayList = personaDao.findAll_idt("estado_civil");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getValue().equals(getBase_model().getPersonaVariableVictima().getEst_civil())) {
                eCivil = arrayList.get(i).getLabel();
            }
        }

        if (this.base_model.getPersonaVictima().getSexo().equals("M") && eCivil.equals("Casada(o)")) {
            this.flag_ApCasada = true;
        }

    }

    /*TODO ESTO DEL AGRESOR*/
    public void handleCI_agresorChange() {
        this.flag_DocIdenAgresor = false;
        if (base_model.getPersonaAgresor().getCodigo_documento() > 0) {
            this.flag_DocIdenAgresor = true;
        }
    }

    public void onCountryChange_agresor() {
        this.flag_ApCasadaAgresor = false;
        String eCivil = "";
        T_datosDao personaDao = new T_datosDaoImpl();
        List<SelectItem> arrayList = personaDao.findAll_idt("estado_civil");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getValue().equals(getBase_model().getPersonaVariableAgresor().getEst_civil())) {
                eCivil = arrayList.get(i).getLabel();
            }
        }

        if (this.base_model.getPersonaAgresor().getSexo().equals("M") && eCivil.equals("Casada(o)")) {
            this.flag_ApCasadaAgresor = true;
        }

    }

    public void onDateSelectFechaNacAgresor() {
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(this.base_model.getPersonaAgresor().getFecha_nac());
        int año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        base_model.getPersonaVariableAgresor().setEdad(año);
    }

    public void handleLNacAgresorChange() {
        this.flag_LNacAgresor = false;
        String eLugNac = "";
        T_datosDao personaDao = new T_datosDaoImpl();
        List<SelectItem> arrayList = personaDao.findAll_idt("lugar_nac");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getValue().equals(base_model.getPersonaAgresor().getLugar_nac())) {
                eLugNac = arrayList.get(i).getLabel();
            }
        }

        if (eLugNac.equals("Otro municipio") || eLugNac.equals("Otro Pais")) {
            this.flag_LNacAgresor = true;
        }
    }

    public void handleMunicipioAgresor() {
        this.flag_MunicipioAgresor = false;
        if (this.selectOneRadioMunicioAgresor.equals("1")) {
            this.flag_MunicipioAgresor = true;
        }
    }

    public void handleOcupacionPrincipalAgresor() {
        this.flag_ocupacion_principalAgresor = false;

        String eOcupacionPrin = "";
        T_datosDao personaDao = new T_datosDaoImpl();
        List<SelectItem> arrayList = personaDao.findAll_idt("ocupacion_principal");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getValue().equals(base_model.getPersonaVariableAgresor().getOcupacion())) {
                eOcupacionPrin = arrayList.get(i).getLabel();
            }
        }

        if (eOcupacionPrin.equals("Otro")) {
            this.flag_ocupacion_principalAgresor = true;
        }
    }

    public void cancela_action() {
        this.flagDialogVictimaAgresor = false;
        this.flagDialogPerfilAgresor = false;
        this.flagDialogAgresor = false;
    }

    //Estos metodos son para guardar en la base de datos
    public void reset() {

        this.flag_RecCaso = false;
        this.flag_DocIden = false;
        this.flag_ApCasada = false;
        this.flag_LNac = false;
        this.flag_Municipio = false;
        this.flag_ocupacion_principal = false;
        this.flag_idioma_hablado = false;
        this.flag_pueblo_orig = false;
        this.flag_tipo_violencia = false;
        this.flag_especi_agresor = false;
        this.flag_MotivoMunicipioEsp = false;
        this.flag_DenuncioAnterior = false;
        this.flag_ConsumeAlc = false;
        this.flag_ConsumeDro = false;
        this.flag_LugarAgresión = false;
        this.flag_DocIdenAgresor = false;
        this.flag_ApCasadaAgresor = false;
        this.flag_LNacAgresor = false;
        this.flag_MunicipioAgresor = false;
        this.flag_ocupacion_principalAgresor = false;
        this.flagDialogVictimaAgresor = false;
        this.flagDialogPerfilAgresor = false;
        this.flagItemPerfilAgresor = false;
        this.flagDialogAgresor = false;

        this.selectOneRadioMunicioAgresor = null;
        this.selectOneRadioBarrioAgresor = null;
        this.selectOneRadioCondicion_actAgresor = null;
        this.selectOneRadioMunicio = null;
        this.selectOneRadioBarrio = null;
        this.selectOneRadioPueblOrig = null;
        this.selectOneRadioMotivoMunicipio = null;
        this.selectOneRadioMotivoBarrio = null;
        this.selectOneRadioMotivoDenuncioAnt = null;
        this.selectOneRadioMotivoConsumeDro = null;
        this.selectOneRadioMotivoConsumeAlc = null;
        this.selectOneRadioCondicion_act = null;
        /*Despues del caso*/
        this.flag_despues_caso = false;
        /*PREGUNTAS*/
        this.selectPreg1 = null; 
        this.selectPreg2 = null;
        this.selectPreg3 = null;
        this.selectPreg4 = null;
        this.selectPreg5 = null;
        this.selectPreg6 = null;
        this.selectPreg7 = null;
        this.selectPreg8 = null;
        this.selectPreg9 = null;
        this.selectPreg10 = null;
        this.selectPreg11 = null;
        this.selectPreg12 = null;
        this.selectPreg13 = null;
        this.selectPreg14 = null;
        this.selectPreg15 = null;
        this.selectPreg16 = null;
        this.selectPreg17 = null;
        this.selectPreg18 = null;
        this.selectPreg19 = null;
        this.selectPreg_suma = null;
        
        /*PREGUNTAS*/
        base_model = new Base_model();

        T_datosDao personaDao = new T_datosDaoImpl();
        listaResult = personaDao.findAllIdt("idioma_hablado");
        listaSelect = new ArrayList<>();
        listaIdimaHablado = listaResult;

        listaResultTipoViolencia = personaDao.findAllIdt("ambito_tipo_violencia");
        listaSelectTipoViolencia = new ArrayList<>();
        listaTipoViolencia = listaResultTipoViolencia;

    }

    public void handleCloseDialog(CloseEvent event) {
        botonDialogVictimaAgresorCancelar();
    }

    public String botonDialogVictimaAgresorAceptar() {
        

        //setea valores datos agresor
        base_model.setPersonaAgresor(new Persona());
        base_model.getVictima_agresor().setFrec_consumo_alcohol(null);
        base_model.getVictima_agresor().setFrec_consumo_drogas(null);
        base_model.setPersonaVariableAgresor(new Persona_variables());
        //setea valores datos agresor
        int existe = 0;
        boolean resp = false;
        String res = null;
        String tipo_violencia = "";
        Integer contador = 0;
        for (T_datos item : listaSelectTipoViolencia) {
            if (contador > 0) {
                tipo_violencia = tipo_violencia + item.getId_tdatos() + ",";
            } else {
                tipo_violencia = "," + item.getId_tdatos() + ",";
            }
            contador++;
        }
//setea datos
        UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
        Base_modelDao dao1 = new Base_modelDaoImpl();
        System.out.println("valor : " + obj.getUsuario().getCod_usuario());
        base_model.getUsuario_vicagre().setUsuario_cod_usuario(obj.getUsuario().getCod_usuario());
        base_model.getUsuario_vicagre().setCod_sistema(new SimpleDateFormat("MMddyyyyHHmmss").format(new Date()));

        base_model.getVicAgre_persona().setPersona_id_persona_agresor(0);
        base_model.getVictima_agresor().setTviolencia(tipo_violencia);
        /*GUARDAR RESPUESTAS DE LA ALERTA*/
        calcularAlerta();
        base_model.getVictima_agresor().getPreg_suma();
        /*GUARDAR RESPUESTAS DE LA ALERTA*/
        Base_modelDao base_modelDao = new Base_modelDaoImpl();
        System.out.println("valor de fecha" + base_model.getUsuario_vicagre().getFecha_reg());
        resp = dao1.find_exist_ci_save(base_model.getPersonaVictima());
        System.out.println("RESPUESTAAAA : " + resp);
        if (resp) {
            System.out.println("entra :");
            existe = 1;
        }
        int res1 = 0;
        if(base_modelDao.devcodper() == 1){
            res1 = 1;
        }else{
            res1 = 2;
        }
        
        int sw = base_modelDao.insert_vic(1, base_model, existe, res1);
        System.out.println("SWWWWWWWWWWWWWWWWWWWW : " + sw);
        if (sw != 0) {
            System.out.println("entraal swich");
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String requestURL = request.getRequestURL().toString();
            String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
            this.url_servlet = url + "/Formulario_?cod=" + sw + "&tv=" + tipo_violencia + "&cu=" + obj.getUsuario().getCod_usuario();
            System.out.println("url:" + url_servlet);
            res = "fii_imp";
                                                                                
            this.flagDialogVictimaAgresor = false;
            reset();
            Base_modelDao base_modDao = new Base_modelDaoImpl();
            list_base_models = base_modDao.find_year();
        }
        return res;

    }

    public void botonDialogVictimaAgresorCancelar() {
        this.flagDialogVictimaAgresor = false;

    }

    public String boton_dialog_form3() {
        String ss = "";
        if (listaSelectTipoViolencia.size() > 0) {
            this.flagItemPerfilAgresor = true;
            this.flagDialogPerfilAgresor = false;
            ss = "fii_fiii";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: Necesita seleccionar un tipo de violencia por lo menos", "Necesita seleccionar un tipo de violencia por lo menos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return ss;
    }

    public void handleCloseDialogAgresor(CloseEvent event) {
        botonDialogAddAgresorCancelar();
    }

    public String botonDialogAddAgresorAceptar() {
        String resp = null;
        int existe = 0;
        boolean res = false;
        String tipo_violencia = "";
        Integer contador = 0;
        for (T_datos item : listaSelectTipoViolencia) {
            if (contador > 0) {
                tipo_violencia = tipo_violencia + item.getId_tdatos() + ",";
            } else {
                tipo_violencia = "," + item.getId_tdatos() + ",";
            }
            contador++;
        }
        System.out.println("estado civil a:" + base_model.getPersonaVariableAgresor().getEst_civil() + "vic: " + base_model.getPersonaVariableVictima().getEst_civil());
        UsuarioController obj = (UsuarioController) ConnectionDB.getSessionBean("usuarioController");
        Base_modelDao dao123 = new Base_modelDaoImpl();
        System.out.println("valor : " + obj.getUsuario().getCod_usuario());
        base_model.getUsuario_vicagre().setUsuario_cod_usuario(obj.getUsuario().getCod_usuario());
        base_model.getUsuario_vicagre().setCod_sistema(new SimpleDateFormat("MMddyyyyHHmmss").format(new Date()));
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH : " + base_model.getVictima_agresor().isMunicipio());
        base_model.getVicAgre_persona().setPersona_id_persona_agresor(0);
        base_model.getVictima_agresor().setTviolencia(tipo_violencia);

        Base_modelDao base_modelDao = new Base_modelDaoImpl();

        if (!base_model.getVictima_agresor().isAgre_consume_alcohol()) {
            base_model.getVictima_agresor().setFrec_consumo_alcohol(0);
        }
        if (!base_model.getVictima_agresor().isAgre_consume_drogas()) {
            base_model.getVictima_agresor().setFrec_consumo_drogas(0);
        }
        res = dao123.find_exist_ci_save(base_model.getPersonaVictima());
        System.out.println("RESPUESTAAAA : " + resp);
        if (res) {
            System.out.println("entra :");
            existe = 1;
        }
        System.out.println("SSSSSSSSSSsssss" + base_model.getPersonaAgresor().getCodigo_documento());
        if(base_model.getPersonaAgresor().getCodigo_documento() == null){
            base_model.getPersonaAgresor().setCodigo_documento(0);
            System.out.println("ssssssssssss" + base_model.getPersonaAgresor().getCodigo_documento());
        }
//        
//        if(base_model.getPersonaAgresor().getCodigo_documento()>=0){
//            
//        }
//        else{
//            base_model.getPersonaAgresor().setCodigo_documento(0);
//        }

        int sw = base_modelDao.insert(1, base_model, existe);
        
        if (sw != 0) {
            System.out.println("entra al swwww");
            //obtiene url de la app
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String requestURL = request.getRequestURL().toString();
            String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
            this.url_servlet = url + "/Formulario?cod=" + sw + "&tv=" + tipo_violencia + "&cu=" + obj.getUsuario().getCod_usuario();
            System.out.println("url:" + url_servlet);
            resp = "fiii_imp";
            reset();
            Base_modelDao base_modDao = new Base_modelDaoImpl();
            list_base_models = base_modDao.find_year();
        } else {
            this.flagDialogAgresor = false;
        }
        //base_model = new Base_model();
        return resp;
    }

    public void botonDialogAddAgresorCancelar() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "close", "close detail");
        FacesContext.getCurrentInstance().addMessage(null, message);
        cancela_action();
    }

    public void handleLNacChange() {
        this.flag_LNac = false;
        String eLugNac = "";
        T_datosDao personaDao = new T_datosDaoImpl();
        List<SelectItem> arrayList = personaDao.findAll_idt("lugar_nac");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getValue().equals(base_model.getPersonaVictima().getLugar_nac())) {
                eLugNac = arrayList.get(i).getLabel();
            }
        }

        if (eLugNac.equals("Otro municipio") || eLugNac.equals("Otro Pais")) {
            this.flag_LNac = true;
        }
    }

    public void handleLNacChange_agre() {
        flag_LNac = false;
        String eLugNac = "";
        T_datosDao personaDao = new T_datosDaoImpl();
        List<SelectItem> arrayList = personaDao.findAll_idt("lugar_nac");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getValue().equals(base_model.getPersonaAgresor().getLugar_nac())) {
                eLugNac = arrayList.get(i).getLabel();
            }
        }

        if (eLugNac.equalsIgnoreCase("Otro Pais")||eLugNac.equalsIgnoreCase("Otro municipio")) {
            this.setFlag_LNac(true);
            flag_LNac=true;
        }
    }

    public void handleMunicipio() {
        this.flag_Municipio = false;
        if (this.selectOneRadioMunicio.equals("1")) {
            this.flag_Municipio = true;
        }else{
            base_model.getPersonaVariableVictima().setEsp_municipio("");
        }
    }

    public void handleOcupacionPrincipal() {
        this.flag_ocupacion_principal = false;

        String eOcupacionPrin = "";
        T_datosDao personaDao = new T_datosDaoImpl();
        List<SelectItem> arrayList = personaDao.findAll_idt("ocupacion_principal");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getValue().equals(base_model.getPersonaVariableVictima().getOcupacion())) {
                eOcupacionPrin = arrayList.get(i).getLabel();
            }
        }

        if (eOcupacionPrin.equals("Otro")) {
            this.flag_ocupacion_principal = true;
        }
    }

    public void handleLugarAgresion() {
        this.flag_LugarAgresión = false;
        String eLugAgr = "";
        T_datosDao dao = new T_datosDaoImpl();
        List<SelectItem> arrayList = dao.findAll_idt("lugar_agresion");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getValue().equals(base_model.getVictima_agresor().getLugar_agresion())) {
                eLugAgr = arrayList.get(i).getLabel();
            }
        }

        if (eLugAgr.equals("Mismo Domicilio de la Victima") || eLugAgr.equals("")) {
            System.out.println("entraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        } else {
            this.flag_LugarAgresión = true;
        }
    }

    public void calcular_edad() {
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(this.base_model.getPersonaVictima().getFecha_nac());
        int año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        base_model.getPersonaVariableVictima().setEdad(año);
    }

    public void calcular_edad_agresor() {
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(this.base_model.getPersonaAgresor().getFecha_nac());
        int año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        base_model.getPersonaVariableAgresor().setEdad(año);
    }

    public void handlePuebloOriginario() {
        this.flag_pueblo_orig = false;
        if (this.selectOneRadioPueblOrig.equals("1")) {
            this.flag_pueblo_orig = true;
        }
    }

    public String update_registro() {
        boolean resp = false;
        String tipo_violencia = "";
        Integer contador = 0;
        for (T_datos item : listaSelectTipoViolencia) {
            if (contador > 0) {
                tipo_violencia = tipo_violencia + item.getId_tdatos() + ",";
            } else {
                tipo_violencia = "," + item.getId_tdatos() + ",";
            }
            contador++;
        }
        String res = "";
        base_model.getVictima_agresor().setTviolencia(tipo_violencia);
        Base_modelDao base_modelDao = new Base_modelDaoImpl();
//        System.out.println("s1s1s1s1s1s1s1s1 : " + base_model.getPersonaAgresor().getId_persona());
        
        int existe = 0;
        System.out.println("aaaaaaaaaa : " + resp);
        resp = base_modelDao.find_exist_ci_save(base_model.getPersonaAgresor());
        System.out.println("RESPUESTAAAA : " + resp);
        if (resp && existe_agr != 1) {
//            base_model.getUsuario_vicagre().setCod_sistema("");
//            base_model.getUsuario_vicagre().set
            UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
            Base_modelDao dao1 = new Base_modelDaoImpl();
            System.out.println("valor : " + obj.getUsuario().getCod_usuario());
            
            base_model.getUsuario_vicagre().setUsuario_cod_usuario(obj.getUsuario().getCod_usuario());
            base_model.getUsuario_vicagre().setCod_sistema(new SimpleDateFormat("MMddyyyyHHmmss").format(new Date()));
            System.out.println("entra :" + resp);
            existe = 1;
        }
        
        if (base_modelDao.insert(2, base_model, existe) == 0) {
            System.out.println("Error, no se modifico correctamente");
            existe_agr = 0;
        } else {
            System.out.println("Exito, se modifico correctamente");
            existe_agr = 0;
            res = "det_iii";
        }
        base_model = new Base_model();
        Base_modelDao base_modDao = new Base_modelDaoImpl();
        list_base_models = base_modDao.find_year();
        return res;
    }

    //modifica la victima, BORIS: CORREGIDO  PARA QUE APAREZCA MENSAJE SI NO SE SELECCION TIPO VIOLENCIA
    public String update_vic() {
        System.out.println("controller.DenunciaController.update_vic(): LISTA VIOLENCIA "+ listaSelectTipoViolencia.size());        
        String res = "";
        
        if (listaSelectTipoViolencia.size() > 0) {
            //this.flagDialogVictimaAgresor = true; 
            String tipo_violencia = "";
            Integer contador = 0;
            for (T_datos item : listaSelectTipoViolencia) {
                if (contador > 0) {
                    tipo_violencia = tipo_violencia + item.getId_tdatos() + ",";
                } else {
                    tipo_violencia = "," + item.getId_tdatos() + ",";
                }
                contador++;
            }
            base_model.getVictima_agresor().setTviolencia(tipo_violencia);
            calcularAlerta();
            System.out.println("PREG_SUMA " + base_model.getVictima_agresor().getPreg_suma());
            //System.out.println("entra :");
            Base_modelDao base_modelDao = new Base_modelDaoImpl();
            if (base_modelDao.insert_vic(2, base_model, 0, 1) == 0) {
                System.out.println("Error, no se modifico correctamente");
            } else {
                System.out.println("Exito, se modifico correctamente");
                res = "det_ii";
            }
            base_model = new Base_model();
            Base_modelDao base_modDao = new Base_modelDaoImpl();
            list_base_models = base_modDao.find_year();
            
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: Necesita seleccionar un tipo de violencia por lo menos", "Necesita seleccionar un tipo de violencia por lo menos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
        
        
        return res;
    }

    public Base_model getBase_model() {
        return base_model;
    }

    public void setBase_model(Base_model base_model) {
        this.base_model = base_model;
    }

    public boolean isFlag_RecCaso() {
        return flag_RecCaso;
    }

    public void setFlag_RecCaso(boolean flag_RecCaso) {
        this.flag_RecCaso = flag_RecCaso;
    }

    public boolean isFlag_DocIden() {
        return flag_DocIden;
    }

    public void setFlag_DocIden(boolean flag_DocIden) {
        this.flag_DocIden = flag_DocIden;
    }

    public boolean isFlag_ApCasada() {
        return flag_ApCasada;
    }

    public void setFlag_ApCasada(boolean flag_ApCasada) {
        this.flag_ApCasada = flag_ApCasada;
    }

    public boolean isFlag_LNac() {
        return flag_LNac;
    }

    public void setFlag_LNac(boolean flag_LNac) {
        this.flag_LNac = flag_LNac;
    }

    public boolean isFlag_Municipio() {
        return flag_Municipio;
    }

    public void setFlag_Municipio(boolean flag_Municipio) {
        this.flag_Municipio = flag_Municipio;
    }

    public boolean isFlag_ocupacion_principal() {
        return flag_ocupacion_principal;
    }

    public void setFlag_ocupacion_principal(boolean flag_ocupacion_principal) {
        this.flag_ocupacion_principal = flag_ocupacion_principal;
    }

    public boolean isFlag_idioma_hablado() {
        return flag_idioma_hablado;
    }

    public void setFlag_idioma_hablado(boolean flag_idioma_hablado) {
        this.flag_idioma_hablado = flag_idioma_hablado;
    }

    public boolean isFlag_pueblo_orig() {
        return flag_pueblo_orig;
    }

    public void setFlag_pueblo_orig(boolean flag_pueblo_orig) {
        this.flag_pueblo_orig = flag_pueblo_orig;
    }

    public boolean isFlag_tipo_violencia() {
        return flag_tipo_violencia;
    }

    public void setFlag_tipo_violencia(boolean flag_tipo_violencia) {
        this.flag_tipo_violencia = flag_tipo_violencia;
    }

    public boolean isFlag_especi_agresor() {
        return flag_especi_agresor;
    }

    public void setFlag_especi_agresor(boolean flag_especi_agresor) {
        this.flag_especi_agresor = flag_especi_agresor;
    }

    public boolean isFlag_MotivoMunicipioEsp() {
        return flag_MotivoMunicipioEsp;
    }

    public void setFlag_MotivoMunicipioEsp(boolean flag_MotivoMunicipioEsp) {
        this.flag_MotivoMunicipioEsp = flag_MotivoMunicipioEsp;
    }

    public boolean isFlag_DenuncioAnterior() {
        return flag_DenuncioAnterior;
    }

    public void setFlag_DenuncioAnterior(boolean flag_DenuncioAnterior) {
        this.flag_DenuncioAnterior = flag_DenuncioAnterior;
    }

    public boolean isFlag_ConsumeAlc() {
        return flag_ConsumeAlc;
    }

    public void setFlag_ConsumeAlc(boolean flag_ConsumeAlc) {
        this.flag_ConsumeAlc = flag_ConsumeAlc;
    }

    public boolean isFlag_ConsumeDro() {
        return flag_ConsumeDro;
    }

    public void setFlag_ConsumeDro(boolean flag_ConsumeDro) {
        this.flag_ConsumeDro = flag_ConsumeDro;
    }

    public boolean isFlag_LugarAgresión() {
        return flag_LugarAgresión;
    }

    public void setFlag_LugarAgresión(boolean flag_LugarAgresión) {
        this.flag_LugarAgresión = flag_LugarAgresión;
    }

    public boolean isFlag_DocIdenAgresor() {
        return flag_DocIdenAgresor;
    }

    public void setFlag_DocIdenAgresor(boolean flag_DocIdenAgresor) {
        this.flag_DocIdenAgresor = flag_DocIdenAgresor;
    }

    public boolean isFlag_ApCasadaAgresor() {
        return flag_ApCasadaAgresor;
    }

    public void setFlag_ApCasadaAgresor(boolean flag_ApCasadaAgresor) {
        this.flag_ApCasadaAgresor = flag_ApCasadaAgresor;
    }

    public boolean isFlag_LNacAgresor() {
        return flag_LNacAgresor;
    }

    public void setFlag_LNacAgresor(boolean flag_LNacAgresor) {
        this.flag_LNacAgresor = flag_LNacAgresor;
    }

    public boolean isFlag_MunicipioAgresor() {
        return flag_MunicipioAgresor;
    }

    public void setFlag_MunicipioAgresor(boolean flag_MunicipioAgresor) {
        this.flag_MunicipioAgresor = flag_MunicipioAgresor;
    }

    public String getSelectOneRadioMunicioAgresor() {
        return selectOneRadioMunicioAgresor;
    }

    public void setSelectOneRadioMunicioAgresor(String selectOneRadioMunicioAgresor) {
        this.selectOneRadioMunicioAgresor = selectOneRadioMunicioAgresor;
        if (selectOneRadioMunicioAgresor.equals("1")) {
            base_model.getPersonaVariableAgresor().setMunicipio(true);
        } else {
            base_model.getPersonaVariableAgresor().setMunicipio(false);
        }
    }

    public String getSelectOneRadioBarrioAgresor() {
        return selectOneRadioBarrioAgresor;
    }

    public void setSelectOneRadioBarrioAgresor(String selectOneRadioBarrioAgresor) {
        this.selectOneRadioBarrioAgresor = selectOneRadioBarrioAgresor;
    }

    public boolean isFlag_ocupacion_principalAgresor() {
        return flag_ocupacion_principalAgresor;
    }

    public void setFlag_ocupacion_principalAgresor(boolean flag_ocupacion_principalAgresor) {
        this.flag_ocupacion_principalAgresor = flag_ocupacion_principalAgresor;
    }

    public boolean isFlagDialogVictimaAgresor() {
        return flagDialogVictimaAgresor;
    }

    public void setFlagDialogVictimaAgresor(boolean flagDialogVictimaAgresor) {
        this.flagDialogVictimaAgresor = flagDialogVictimaAgresor;
    }

    public boolean isFlagDialogPerfilAgresor() {
        return flagDialogPerfilAgresor;
    }

    public void setFlagDialogPerfilAgresor(boolean flagDialogPerfilAgresor) {
        this.flagDialogPerfilAgresor = flagDialogPerfilAgresor;
    }

    public boolean isFlagItemPerfilAgresor() {
        return flagItemPerfilAgresor;
    }

    public void setFlagItemPerfilAgresor(boolean flagItemPerfilAgresor) {
        this.flagItemPerfilAgresor = flagItemPerfilAgresor;
    }

    public boolean isFlagDialogAgresor() {
        return flagDialogAgresor;
    }

    public void setFlagDialogAgresor(boolean flagDialogAgresor) {
        this.flagDialogAgresor = flagDialogAgresor;
    }

    public String getSelectOneRadioMunicio() {
        return selectOneRadioMunicio;
    }

    public void setSelectOneRadioMunicio(String selectOneRadioMunicio) {
        this.selectOneRadioMunicio = selectOneRadioMunicio;
        if (selectOneRadioMunicio.equals("1")) {
            base_model.getPersonaVariableVictima().setMunicipio(true);
        } else {
            base_model.getPersonaVariableVictima().setMunicipio(false);
        }
    }
    /*******************INICIO preguntaS**************************/
    public String getSelectPreg1() {
        return selectPreg1;
    }
    public void setSelectPreg1(String selectPreg1) {
        this.selectPreg1 = selectPreg1;
        switch (selectPreg1){
            case "0":
            base_model.getVictima_agresor().setPreg1(0);
            break;
            case "1":
            base_model.getVictima_agresor().setPreg1(1);
            break;
            case "2":
            base_model.getVictima_agresor().setPreg1(2);
            break;
            case "3":
            base_model.getVictima_agresor().setPreg1(3);
            break;            
        }        
    }
    //****** preg2
    public String getSelectPreg2() {
        return selectPreg2;
    }
    public void setSelectPreg2(String selectPreg2) {
        this.selectPreg2 = selectPreg2;
        if(selectPreg2.equals("2")){ 
            base_model.getVictima_agresor().setPreg2(2);
        } else{
            base_model.getVictima_agresor().setPreg2(0);
        }   
    }
    //****** preg3
    public String getSelectPreg3() {
        return selectPreg3;
    }

    public void setSelectPreg3(String selectPreg3) {
        this.selectPreg3 = selectPreg3;        
        switch (selectPreg3){
            case "0":
            base_model.getVictima_agresor().setPreg3(0);
            break;
            case "1":
            base_model.getVictima_agresor().setPreg3(1);
            break;
            case "2":
            base_model.getVictima_agresor().setPreg3(2);
            break;
            case "3":
            base_model.getVictima_agresor().setPreg3(3);
            break;            
        }  
    }
//****** preg4
    public String getSelectPreg4() {
        return selectPreg4;
    }

    public void setSelectPreg4(String selectPreg4) {
        this.selectPreg4 = selectPreg4;
            switch (selectPreg4){
            case "1":
            base_model.getVictima_agresor().setPreg4(1);
            break;
            case "2":
            base_model.getVictima_agresor().setPreg4(2);
            break;
            case "3":
            base_model.getVictima_agresor().setPreg4(3);
            break;            
        }
    }
//****** preg5
    public String getSelectPreg5() {
        return selectPreg5;
    }

    public void setSelectPreg5(String selectPreg5) {
        this.selectPreg5 = selectPreg5;
        if(selectPreg5.equals("2")){ 
            base_model.getVictima_agresor().setPreg5(2);
        } else{
            base_model.getVictima_agresor().setPreg5(0);
        }
    }
//****** preg6
    public String getSelectPreg6() {
        return selectPreg6;
    }

    public void setSelectPreg6(String selectPreg6) {
        this.selectPreg6 = selectPreg6;
        if(selectPreg6.equals("2")){ 
            base_model.getVictima_agresor().setPreg6(2);
        } else{
            base_model.getVictima_agresor().setPreg6(0);
        }
    }
//****** preg7
    public String getSelectPreg7() {
        return selectPreg7;
    }

    public void setSelectPreg7(String selectPreg7) {
        this.selectPreg7 = selectPreg7;
        if(selectPreg7.equals("3")){ 
            base_model.getVictima_agresor().setPreg7(3);
        } else{
            base_model.getVictima_agresor().setPreg7(0);
        }
    }
//****** preg8
    public String getSelectPreg8() {
        return selectPreg8;
    }

    public void setSelectPreg8(String selectPreg8) {
        this.selectPreg8 = selectPreg8;
        switch (selectPreg8){
            case "0":
            base_model.getVictima_agresor().setPreg8(0);
            break;
            case "1":
            base_model.getVictima_agresor().setPreg8(1);
            break;
            case "2":
            base_model.getVictima_agresor().setPreg8(2);
            break;
            case "3":
            base_model.getVictima_agresor().setPreg8(3);
            break;            
        }
    }
//****** preg9
    public String getSelectPreg9() {
        return selectPreg9;
    }

    public void setSelectPreg9(String selectPreg9) {
        this.selectPreg9 = selectPreg9;
        if(selectPreg9.equals("3")){ 
            base_model.getVictima_agresor().setPreg9(3);
        } else{
            base_model.getVictima_agresor().setPreg9(0);
        }
    }
//****** preg10
    public String getSelectPreg10() {
        return selectPreg10;
    }

    public void setSelectPreg10(String selectPreg10) {
        this.selectPreg10 = selectPreg10;
        switch (selectPreg10){
            case "0":
            base_model.getVictima_agresor().setPreg10(0);
            break;
            case "1":
            base_model.getVictima_agresor().setPreg10(1);
            break;
            case "2":
            base_model.getVictima_agresor().setPreg10(2);
            break;
            case "3":
            base_model.getVictima_agresor().setPreg10(3);
            break;            
        }
    }
//****** preg11
    public String getSelectPreg11() {
        return selectPreg11;
    }

    public void setSelectPreg11(String selectPreg11) {
        this.selectPreg11 = selectPreg11;
        switch (selectPreg11){
            case "0":
            base_model.getVictima_agresor().setPreg11(0);
            break;
            case "1":
            base_model.getVictima_agresor().setPreg11(1);
            break;
            case "2":
            base_model.getVictima_agresor().setPreg11(2);
            break;
            case "3":
            base_model.getVictima_agresor().setPreg11(3);
            break;            
        }
    }
//****** preg12
    public String getSelectPreg12() {
        return selectPreg12;
    }

    public void setSelectPreg12(String selectPreg12) {
        this.selectPreg12 = selectPreg12;
        if(selectPreg12.equals("2")){ 
            base_model.getVictima_agresor().setPreg12(2);
        } else{
            base_model.getVictima_agresor().setPreg12(0);
        }
    }
//****** preg13
    public String getSelectPreg13() {
        return selectPreg13;
    }

    public void setSelectPreg13(String selectPreg13) {
        this.selectPreg13 = selectPreg13;
        if(selectPreg13.equals("2")){ 
            base_model.getVictima_agresor().setPreg13(2);
        } else{
            base_model.getVictima_agresor().setPreg13(0);
        }
    }
//****** preg14
    public String getSelectPreg14() {
        return selectPreg14;
    }

    public void setSelectPreg14(String selectPreg14) {
        this.selectPreg14 = selectPreg14;
        if(selectPreg14.equals("2")){ 
            base_model.getVictima_agresor().setPreg14(2);
        } else{
            base_model.getVictima_agresor().setPreg14(0);
        }
    }
//****** preg15
    public String getSelectPreg15() {
        return selectPreg15;
    }

    public void setSelectPreg15(String selectPreg15) {
        this.selectPreg15 = selectPreg15;
        switch (selectPreg15){
            case "0":
            base_model.getVictima_agresor().setPreg15(0);
            break;
            case "1":
            base_model.getVictima_agresor().setPreg15(1);
            break;
            case "2":
            base_model.getVictima_agresor().setPreg15(2);
            break;
            case "3":
            base_model.getVictima_agresor().setPreg15(3);
            break;            
        }
    }
//****** preg16
    public String getSelectPreg16() {
        return selectPreg16;
    }

    public void setSelectPreg16(String selectPreg16) {
        this.selectPreg16 = selectPreg16;
         if(selectPreg16.equals("2")){ 
            base_model.getVictima_agresor().setPreg16(2);
        } 
         if(selectPreg16.equals("1")){
            base_model.getVictima_agresor().setPreg16(1);
        }
         
    }
    //****** preg17
    public String getSelectPreg17() {
        return selectPreg17;
    }

    public void setSelectPreg17(String selectPreg17) {
        this.selectPreg17 = selectPreg17;
         if(selectPreg17.equals("1")){ 
            base_model.getVictima_agresor().setPreg17(1);
        } else{
            base_model.getVictima_agresor().setPreg17(0);
        }
    }
    //****** preg18
    public String getSelectPreg18() {
        return selectPreg18;
    }

    public void setSelectPreg18(String selectPreg18) {
        this.selectPreg18 = selectPreg18;
         if(selectPreg18.equals("1")){ 
            base_model.getVictima_agresor().setPreg18(1);
        } else{
            base_model.getVictima_agresor().setPreg18(0);
        }
    }
    //****** preg19
    public String getSelectPreg19() {
        return selectPreg19;
    }

    public void setSelectPreg19(String selectPreg19) {
        this.selectPreg19 = selectPreg19;
         if(selectPreg19.equals("3")){ 
            base_model.getVictima_agresor().setPreg19(3);
        } else{
            base_model.getVictima_agresor().setPreg19(2);
        }
    }
    
    //****** preg_suma
    
     public String getSelectPreg_suma() {
        int alerta = base_model.getVictima_agresor().getPreg_suma();    
        if (alerta >= 0 && alerta < 13){
            selectPreg_suma = "Riesgo Leve";
        }
        if (alerta >= 13 && alerta < 22){
            selectPreg_suma = "Riesgo Moderado";
        }
        if (alerta >= 22){
            selectPreg_suma = "Riesgo Alto";
        }
        return selectPreg_suma;
        
    }    
    
    public void calcularAlerta(){
    int pregSuma = base_model.getVictima_agresor().getPreg1()+ base_model.getVictima_agresor().getPreg2() + base_model.getVictima_agresor().getPreg3()+
                            base_model.getVictima_agresor().getPreg4()+ base_model.getVictima_agresor().getPreg5() + base_model.getVictima_agresor().getPreg6()+
                            base_model.getVictima_agresor().getPreg7()+ base_model.getVictima_agresor().getPreg8() + base_model.getVictima_agresor().getPreg9()+
                            base_model.getVictima_agresor().getPreg10()+ base_model.getVictima_agresor().getPreg11() + base_model.getVictima_agresor().getPreg12()+
                            base_model.getVictima_agresor().getPreg13()+ base_model.getVictima_agresor().getPreg14() + base_model.getVictima_agresor().getPreg15()+
                            base_model.getVictima_agresor().getPreg16()+ base_model.getVictima_agresor().getPreg17() + base_model.getVictima_agresor().getPreg18()+
                            base_model.getVictima_agresor().getPreg19();
    
    base_model.getVictima_agresor().setPreg_suma(pregSuma);     
    } 
     
    public void setSelectPreg_suma(String selectPreg_suma) {
        this.selectPreg_suma = selectPreg_suma;      
              
    }
    
    /*FIN preguntaS*/
    public String getSelectOneRadioBarrio() {
        return selectOneRadioBarrio;
    }

    public void setSelectOneRadioBarrio(String selectOneRadioBarrio) {
        this.selectOneRadioBarrio = selectOneRadioBarrio;
        if (selectOneRadioBarrio.equals("1")) {
            base_model.getPersonaVariableVictima().setArea(true);
        } else {
            base_model.getPersonaVariableVictima().setArea(false);
        }
    }

    public String getSelectOneRadioPueblOrig() {
        return selectOneRadioPueblOrig;
    }

    public void setSelectOneRadioPueblOrig(String selectOneRadioPueblOrig) {
        this.selectOneRadioPueblOrig = selectOneRadioPueblOrig;
        if (selectOneRadioPueblOrig.equals("1")) {
            base_model.getPersonaVariableVictima().setEtnia(true);
        } else {
            base_model.getPersonaVariableVictima().setEtnia(false);
        }
    }

    public String getSelectOneRadioMotivoMunicipio() {
        return selectOneRadioMotivoMunicipio;
    }

    public void setSelectOneRadioMotivoMunicipio(String selectOneRadioMotivoMunicipio) {
        this.selectOneRadioMotivoMunicipio = selectOneRadioMotivoMunicipio;
        if (selectOneRadioMotivoMunicipio.equals("1")) {
            base_model.getVictima_agresor().setMunicipio(true);
        } else {
            base_model.getVictima_agresor().setMunicipio(false);
        }
    }

    public String getSelectOneRadioMotivoBarrio() {
        return selectOneRadioMotivoBarrio;
    }

    public void setSelectOneRadioMotivoBarrio(String selectOneRadioMotivoBarrio) {
        this.selectOneRadioMotivoBarrio = selectOneRadioMotivoBarrio;
        if (selectOneRadioMotivoBarrio.equals("1")) {
            base_model.getVictima_agresor().setArea(true);
        } else {
            base_model.getVictima_agresor().setArea(false);
        }
    }

    public String getSelectOneRadioMotivoDenuncioAnt() {
        return selectOneRadioMotivoDenuncioAnt;
    }

    public void setSelectOneRadioMotivoDenuncioAnt(String selectOneRadioMotivoDenuncioAnt) {
        this.selectOneRadioMotivoDenuncioAnt = selectOneRadioMotivoDenuncioAnt;
        if (selectOneRadioMotivoDenuncioAnt.equals("1")) {
            base_model.getVictima_agresor().setDenuncio(true);
        } else {
            base_model.getVictima_agresor().setDenuncio(false);
        }
    }

    public String getSelectOneRadioMotivoConsumeDro() {
        return selectOneRadioMotivoConsumeDro;
    }

    public void setSelectOneRadioMotivoConsumeDro(String selectOneRadioMotivoConsumeDro) {
        this.selectOneRadioMotivoConsumeDro = selectOneRadioMotivoConsumeDro;
        if (selectOneRadioMotivoConsumeDro.equals("1")) {
            base_model.getVictima_agresor().setAgre_consume_drogas(true);
        } else {
            base_model.getVictima_agresor().setAgre_consume_drogas(false);
        }
    }

    public String getSelectOneRadioMotivoConsumeAlc() {
        return selectOneRadioMotivoConsumeAlc;
    }

    public void setSelectOneRadioMotivoConsumeAlc(String selectOneRadioMotivoConsumeAlc) {
        this.selectOneRadioMotivoConsumeAlc = selectOneRadioMotivoConsumeAlc;
        if (selectOneRadioMotivoConsumeAlc.equals("1")) {
            base_model.getVictima_agresor().setAgre_consume_alcohol(true);
        } else {
            base_model.getVictima_agresor().setAgre_consume_alcohol(false);
        }
    }

    public String getSelectOneRadioCondicion_actAgresor() {
        return selectOneRadioCondicion_actAgresor;
    }

    public void setSelectOneRadioCondicion_actAgresor(String selectOneRadioCondicion_actAgresor) {
        this.selectOneRadioCondicion_actAgresor = selectOneRadioCondicion_actAgresor;
    }

    public String getSelectOneRadioCondicion_act() {
        return selectOneRadioCondicion_act;
    }

    public void setSelectOneRadioCondicion_act(String selectOneRadioCondicion_act) {
        this.selectOneRadioCondicion_act = selectOneRadioCondicion_act;
    }

    public String[] getSelectedRelacionVicAgr() {
        return selectedRelacionVicAgr;
    }

    public void setSelectedRelacionVicAgr(String[] selectedRelacionVicAgr) {
        this.selectedRelacionVicAgr = selectedRelacionVicAgr;
    }

    public void actionDataListA() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String val = map.get("seleccion");
        for (T_datos item : listaResult) {
            if (item.getId_tdatos() == Integer.parseInt(val)) {
                listaSelect.add(item);
            }
        }

        List<T_datos> list_aux = new ArrayList<>();
        for (T_datos item : listaResult) {
            if (item.getId_tdatos() != Integer.parseInt(val)) {
                list_aux.add(item);
            }
        }
        this.listaResult = list_aux;

        Integer contador = 0;
        for (T_datos item : listaSelect) {
            if (item.getDescripcion().equals("Otro nativo") || item.getDescripcion().equals("Extranjero")) {
                contador++;
            }
        }
        this.flag_idioma_hablado = false;
        if (contador > 0) {
            this.flag_idioma_hablado = true;
        }
    }

    public void actionDataListB() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String val = map.get("seleccion");
        for (T_datos item : listaSelect) {
            if (item.getId_tdatos() == Integer.parseInt(val)) {
                listaResult.add(item);
            }
        }

        List<T_datos> list_aux = new ArrayList<>();
        for (T_datos item : listaSelect) {
            if (item.getId_tdatos() != Integer.parseInt(val)) {
                list_aux.add(item);
            }
        }
        this.listaSelect = list_aux;

        Integer contador = 0;
        for (T_datos item : listaSelect) {
            if (item.getDescripcion().equals("Otro nativo") || item.getDescripcion().equals("Extranjero")) {
                contador++;
            }
        }
        this.flag_idioma_hablado = false;
        if (contador > 0) {
            this.flag_idioma_hablado = true;
        }
    }

    /**
     * tipo de violencia*
     */
    public void actionDataListTipoViolenciaA() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String val = map.get("seleccion");
        for (T_datos item : listaResultTipoViolencia) {
            if (item.getId_tdatos() == Integer.parseInt(val)) {
                listaSelectTipoViolencia.add(item);
            }
        }

        List<T_datos> list_aux = new ArrayList<>();
        for (T_datos item : listaResultTipoViolencia) {
            if (item.getId_tdatos() != Integer.parseInt(val)) {
                list_aux.add(item);
            }
        }
        this.listaResultTipoViolencia = list_aux;

        Integer contador = 0;
        for (T_datos item : listaSelectTipoViolencia) {
            if (item.getDescripcion().equals("Otros")) {
                contador++;
            }
        }
        this.flag_tipo_violencia = false;
        if (contador > 0) {
            this.flag_tipo_violencia = true;
        }
    }

    public void actionDataListTipoViolenciaB() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String val = map.get("seleccion");
        for (T_datos item : listaSelectTipoViolencia) {
            if (item.getId_tdatos() == Integer.parseInt(val)) {
                listaResultTipoViolencia.add(item);
            }
        }

        List<T_datos> list_aux = new ArrayList<>();
        for (T_datos item : listaSelectTipoViolencia) {
            if (item.getId_tdatos() != Integer.parseInt(val)) {
                list_aux.add(item);
            }
        }
        this.listaSelectTipoViolencia = list_aux;

        Integer contador = 0;
        for (T_datos item : listaSelectTipoViolencia) {
            if (item.getDescripcion().equals("Otros")) {
                contador++;
            }
        }
        this.flag_tipo_violencia = false;
        if (contador > 0) {
            this.flag_tipo_violencia = true;
        }
    }

    public void add_victima_relacion() {
        System.out.println("controller.DenunciaController.add_victima_relacion() TIpo Violencia Size"+listaSelectTipoViolencia.size());
        if (listaSelectTipoViolencia.size() > 0) {
            this.flagDialogVictimaAgresor = true;
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: Necesita seleccionar un tipo de violencia por lo menos", "Necesita seleccionar un tipo de violencia por lo menos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void add_victima_relacion_agresor() {
        if (listaSelectTipoViolencia.size() > 0) {
            this.flagDialogAgresor = true;
        } else {
            System.out.println("Error");
        }
    }

    public void actionSeleccionaTipoViolenciaTodoA() {
        this.flag_tipo_violencia = false;
        this.listaSelectTipoViolencia = new ArrayList<>();
        this.listaResultTipoViolencia = listaTipoViolencia;
    }

    public void actionSeleccionaTipoViolenciaTodoB() {
        this.flag_tipo_violencia = true;
        this.listaResultTipoViolencia = new ArrayList<>();
        this.listaSelectTipoViolencia = listaTipoViolencia;
    }

    public void actionSeleccionaTodoA() {
        this.flag_idioma_hablado = false;
        this.listaSelect = new ArrayList<>();
        this.listaResult = listaIdimaHablado;
    }

    public void actionSeleccionaTodoB() {
        this.flag_idioma_hablado = true;
        this.listaResult = new ArrayList<>();
        this.listaSelect = listaIdimaHablado;
    }

    public void handleRelacionVicAgr() {

        T_datosDao personaDao = new T_datosDaoImpl();
        this.flag_especi_agresor = false;
        List<T_datos> items = personaDao.findAllIdt("relacion_agresor_victima");
        String eRelVicAgr = "";

        for (T_datos item : items) {
            if (item.getId_tdatos() == base_model.getVictima_agresor().getParentesco()) {
                eRelVicAgr = item.getDescripcion();
            }
        }

        if (eRelVicAgr.equals("Otros")) {
            this.flag_especi_agresor = true;
        }

    }

    public void handleMotivoViolenciaMunicipio() {
        this.flag_MotivoMunicipioEsp = false;
        if (this.selectOneRadioMotivoMunicipio.equals("1")) {
            this.flag_MotivoMunicipioEsp = true;
        }
    }

    public void handleMotivoDenuncioAnterior() {
        this.flag_DenuncioAnterior = false;
        if (this.selectOneRadioMotivoDenuncioAnt.equals("1")) {
            this.flag_DenuncioAnterior = true;
        }
    }

    public void handleMotivoConsumeAlc() {
        this.flag_ConsumeAlc = false;
        if (this.selectOneRadioMotivoConsumeAlc.equals("1")) {
            this.flag_ConsumeAlc = true;
        }
    }

    public void handleMotivoConsumeDro() {
        this.flag_ConsumeDro = false;
        if (this.selectOneRadioMotivoConsumeDro.equals("1")) {
            this.flag_ConsumeDro = true;
        }
    }

    public List<T_datos> getListaResult() {
        return listaResult;
    }

    public void setListaResult(List<T_datos> listaResult) {
        this.listaResult = listaResult;
    }

    public List<T_datos> getListaSelect() {
        return listaSelect;
    }

    public void setListaSelect(List<T_datos> listaSelect) {
        this.listaSelect = listaSelect;
    }

    public List<T_datos> getListaResultTipoViolencia() {
        return listaResultTipoViolencia;
    }

    public void setListaResultTipoViolencia(List<T_datos> listaResultTipoViolencia) {
        this.listaResultTipoViolencia = listaResultTipoViolencia;
    }

    public List<T_datos> getListaSelectTipoViolencia() {
        return listaSelectTipoViolencia;
    }

    public void setListaSelectTipoViolencia(List<T_datos> listaSelectTipoViolencia) {
        this.listaSelectTipoViolencia = listaSelectTipoViolencia;
    }
    //--busqueda de registros
    private Persona filtro_persona;  //var eddy
    private Persona filtro_persona_agre;
    private List<Base_model> list_base_models;
    private Base_model selectedBaseModel;

    public void find() {
        //System.out.println("main");
        Base_modelDao base_modDao = new Base_modelDaoImpl();
        list_base_models = new ArrayList<>();
        list_base_models = base_modDao.find_mod(filtro_persona.getNumero(), filtro_persona.getNombre(), filtro_persona.getPaterno(), filtro_persona.getMaterno(), filtro_persona.getNumero(), filtro_persona.getNombre(), filtro_persona.getPaterno(), filtro_persona.getMaterno());
        System.out.println("FIND "+filtro_persona.getNumero() );
        selectedBaseModel = new Base_model();
    }

    public void find_year() {
        System.out.println("FIND_YEAR() ");
        Base_modelDao base_modDao = new Base_modelDaoImpl();
        list_base_models = new ArrayList<>();
        list_base_models = base_modDao.find_year();
    }
    private int existe_agr;
    public void onRowSelect(SelectEvent event) {
        
        System.out.println("mi valos seleccionado: " + selectedBaseModel.getUsuario_vicagre().getVictima_agresor_id_vicagre() + "---" + selectedBaseModel.getPersonaVictima().getNumero());
        this.flag_DocIden = true;
        this.flag_DocIdenAgresor = true;
        this.flagOpcion = true;
        existe_agr = 0;
        //this.flag_Municipio = true;
        Victima_agresor data = new Victima_agresor();
        data.setId_vicagre(selectedBaseModel.getUsuario_vicagre().getVictima_agresor_id_vicagre());
        Base_modelDao dao = new Base_modelDaoImpl();
        base_model = dao.data_baseModel(data);
        //existe persona agresor?
            if(base_model.getPersonaAgresor().getId_persona()!= 0){
                existe_agr = 1;
            }
        //OBTIENE LOS IDIOMAS
        List<T_datos> listRes = new ArrayList<>();
        List<T_datos> listSel = new ArrayList<>();
        String[] arrayIdiomas = base_model.getPersonaVariableVictima().getIdiomas().split(",");
        T_datosDao personaDao = new T_datosDaoImpl();
        listaResult = personaDao.findAllIdt("idioma_hablado");
        for (T_datos item : listaResult) {
            boolean a = false;
            for (int i = 0; i < arrayIdiomas.length; i++) {
                if (arrayIdiomas[i].equals(item.getId_tdatos().toString())) {
                    listSel.add(item);
                    a = true;
                }
            }
            if (!a) {
                listRes.add(item);
            }
        }
        listaResult = new ArrayList<>();
        this.listaResult = listRes;
        this.listaSelect = listSel;
//OBTIENE LOS IDIOMAS
//SETEA RADIOBUTON
        if (base_model.getPersonaVariableVictima().isMunicipio()) {
            this.selectOneRadioMunicio = "1";
        } else {
            this.selectOneRadioMunicio = "0";
        }
        //////***************************************** INICIO PREGUNTAS****/
        
        switch (base_model.getVictima_agresor().getPreg1()){
            case 0:
            this.selectPreg1 = "0";
            break;
            case 1:
            this.selectPreg1 = "1";
            break;
            case 2:
            this.selectPreg1 = "2";
            break;
            case 3:
            this.selectPreg1 = "3";
            break;  
            default:
            this.selectPreg1 = "0";
            break;
        }        
        // Vista preg2
        
        if(base_model.getVictima_agresor().getPreg2() == 2){
            this.selectPreg2 = "2";
        }else{
            this.selectPreg2 = "0";
        }
               
        // Vista preg3
        
        switch (base_model.getVictima_agresor().getPreg3()){
            case 0:
            this.selectPreg3 = "0";
            break;
            case 1:
            this.selectPreg3 = "1";
            break;
            case 2:
            this.selectPreg3 = "2";
            break;
            case 3:
            this.selectPreg3 = "3";
            break;  
            default:
            this.selectPreg3 = "0";
            break;
        }  
       
        // Vista preg4
        switch (base_model.getVictima_agresor().getPreg4()){
            case 1:
            this.selectPreg4 = "1";
            break;
            case 2:
            this.selectPreg4 = "2";
            break;
            case 3:
            this.selectPreg4 = "3";
            break;  
            default:
            this.selectPreg4 = "1";
            break;
        }        
        
        // Vista preg5
        if(base_model.getVictima_agresor().getPreg5() == 2){
            this.selectPreg5 = "2";
        }else{
            this.selectPreg5 = "0";
        }
        // Vista preg6
        if(base_model.getVictima_agresor().getPreg6() == 2){
            this.selectPreg6 = "2";
        }else{
            this.selectPreg6 = "0";
        }
        // Vista preg7
        if(base_model.getVictima_agresor().getPreg7() == 3){
            this.selectPreg7 = "3";
        }else{
            this.selectPreg7 = "0";
        }
        // Vista preg8
        switch (base_model.getVictima_agresor().getPreg8()){
            case 0:
            this.selectPreg8 = "0";
            break;
            case 1:
            this.selectPreg8 = "1";
            break;
            case 2:
            this.selectPreg8 = "2";
            break;
            case 3:
            this.selectPreg8 = "3";
            break;  
            default:
            this.selectPreg8 = "0";
            break;
        } 
        // Vista preg9
        if(base_model.getVictima_agresor().getPreg9() == 3){
            this.selectPreg9 = "3";
        }else{
            this.selectPreg9 = "0";
        }
        // Vista preg10
        switch (base_model.getVictima_agresor().getPreg10()){
            case 0:
            this.selectPreg10 = "0";
            break;
            case 1:
            this.selectPreg10 = "1";
            break;
            case 2:
            this.selectPreg10 = "2";
            break;
            case 3:
            this.selectPreg10 = "3";
            break;  
            default:
            this.selectPreg10 = "0";
            break;
        } 
        // Vista preg11
        switch (base_model.getVictima_agresor().getPreg11()){
            case 0:
            this.selectPreg11 = "0";
            break;
            case 1:
            this.selectPreg11 = "1";
            break;
            case 2:
            this.selectPreg11 = "2";
            break;
            case 3:
            this.selectPreg11 = "3";
            break;  
            default:
            this.selectPreg11 = "0";
            break;
        } 
        // Vista preg12
        if(base_model.getVictima_agresor().getPreg12() == 2){
            this.selectPreg12 = "2";
        }else{
            this.selectPreg12 = "0";
        }
        // Vista preg13
        if(base_model.getVictima_agresor().getPreg13() == 2){
            this.selectPreg13 = "2";
        }else{
            this.selectPreg13 = "0";
        }
        // Vista preg14
        if(base_model.getVictima_agresor().getPreg14() == 2){
            this.selectPreg14 = "2";
        }else{
            this.selectPreg14 = "0";
        }
        // Vista preg15
         switch (base_model.getVictima_agresor().getPreg15()){
            case 0:
            this.selectPreg15 = "0";
            break;
            case 1:
            this.selectPreg15 = "1";
            break;
            case 2:
            this.selectPreg15 = "2";
            break;
            case 3:
            this.selectPreg15 = "3";
            break;  
            default:
            this.selectPreg15 = "0";
            break;
        } 
        // Vista preg16
        if(base_model.getVictima_agresor().getPreg16() == 2){
            this.selectPreg16 = "2";
        }else{        
            this.selectPreg16 = "1";
        }
        // Vista preg17
        if(base_model.getVictima_agresor().getPreg17() == 2){
            this.selectPreg17 = "1";
        }else{
            this.selectPreg17 = "0";
        }
        // Vista preg18
        if(base_model.getVictima_agresor().getPreg18() == 2){
            this.selectPreg18 = "1";
        }else{
            this.selectPreg18 = "0";
        }
        // Vista preg19
        if(base_model.getVictima_agresor().getPreg19() == 3){
            this.selectPreg19 = "3";
        }else{
            this.selectPreg19 = "2";
        }
        
        
        //////***************************************** FIN PREGUNTAS****/
        
        
        if (base_model.getPersonaVariableVictima().isArea()) {
            this.selectOneRadioBarrio = "1";
        } else {
            this.selectOneRadioBarrio = "0";
        }

        if (base_model.getPersonaVariableVictima().isEtnia()) {
            this.selectOneRadioPueblOrig = "1";
        } else {
            this.selectOneRadioPueblOrig = "0";
        }
        if (base_model.getPersonaVariableVictima().isCondicion_act()) {
            this.selectOneRadioCondicion_act = "1";
        } else {
            this.selectOneRadioCondicion_act = "0";
        }

        if (base_model.getPersonaVariableVictima().isMunicipio()) {
            this.flag_Municipio = true;
        }

        if (base_model.getPersonaVariableAgresor().isMunicipio()) {
            this.flag_MunicipioAgresor = true;
        }

        if (base_model.getPersonaVictima().getLugar_nac() != 34) {
            this.flag_LNac = true;
        }
        //lugar de agresion agresor
        if (base_model.getPersonaAgresor().getLugar_nac() != 34) {
            this.flag_LNacAgresor = true;
        }

        if (base_model.getVictima_agresor().getLugar_agresion() != 93) {
            this.flag_LugarAgresión = true;
        }

        if (base_model.getVictima_agresor().isMunicipio()) {
            this.flag_MotivoMunicipioEsp = true;
        }

        if (base_model.getVictima_agresor().isAgre_consume_alcohol()) {
            this.flag_ConsumeAlc = true;
        }

        if (base_model.getVictima_agresor().isAgre_consume_drogas()) {
            this.flag_ConsumeDro = true;
        }

        if (base_model.getPersonaVariableVictima().isEtnia()) {
            this.flag_pueblo_orig = true;
        }

//SETEA RADIOBUTON        
//OBTIENE LOS TIPOS DE VIOLENCIA
        //listaResultTipoViolencia
        List<T_datos> listResTV = new ArrayList<>();
        List<T_datos> listSelTV = new ArrayList<>();
        String[] arrayTipoViolencia = base_model.getVictima_agresor().getTviolencia().split(",");

        listaResultTipoViolencia = personaDao.findAllIdt("ambito_tipo_violencia");
        for (T_datos item : listaResultTipoViolencia) {
            boolean a = false;
            for (int i = 0; i < arrayTipoViolencia.length; i++) {
                if (arrayTipoViolencia[i].equals(item.getId_tdatos().toString())) {
                    listSelTV.add(item);
                    a = true;
                }
            }
            if (!a) {
                listResTV.add(item);
            }
        }
        listaResultTipoViolencia = new ArrayList<>();
        this.listaResultTipoViolencia = listResTV;
        this.listaSelectTipoViolencia = listSelTV;
//OBTIENE LOS TIPOS DE VIOLENCIA      
//SETEA RADIOBUTON
        if (base_model.getVictima_agresor().isMunicipio()) {
            this.selectOneRadioMotivoMunicipio = "1";
        } else {
            this.selectOneRadioMotivoMunicipio = "0";
        }
        if (base_model.getVictima_agresor().isArea()) {
            this.selectOneRadioMotivoBarrio = "1";
        } else {
            this.selectOneRadioMotivoBarrio = "0";
        }

        if (base_model.getVictima_agresor().isDenuncio()) {
            this.selectOneRadioMotivoDenuncioAnt = "1";
        } else {
            this.selectOneRadioMotivoDenuncioAnt = "0";
        }

//SETEA RADIOBUTON        
//SETEA LOS DATOS DEL AGRESOR 
        if (base_model.getPersonaAgresor().getId_persona() == 0) {
            this.flagItemPerfilAgresor = false;
        } else {
            this.flagItemPerfilAgresor = true;

            if (base_model.getPersonaVariableAgresor().isMunicipio()) {
                this.selectOneRadioMunicioAgresor = "1";
            } else {
                this.selectOneRadioMunicioAgresor = "0";
            }

            if (base_model.getPersonaVariableAgresor().isArea()) {
                this.selectOneRadioBarrioAgresor = "1";
            } else {
                this.selectOneRadioBarrioAgresor = "0";
            }

            if (base_model.getPersonaVariableAgresor().isCondicion_act()) {
                this.selectOneRadioCondicion_actAgresor = "1";
            } else {
                this.selectOneRadioCondicion_actAgresor = "0";
            }

            if (base_model.getVictima_agresor().isAgre_consume_alcohol()) {
                this.selectOneRadioMotivoConsumeAlc = "1";
            } else {
                this.selectOneRadioMotivoConsumeAlc = "0";
            }
            System.out.println("cons:" + base_model.getVictima_agresor().isAgre_consume_drogas());
            if (base_model.getVictima_agresor().isAgre_consume_drogas()) {
                this.selectOneRadioMotivoConsumeDro = "1";
            } else {
                this.selectOneRadioMotivoConsumeDro = "0";
            }
        }
//SETEA LOS DATOS DEL AGRESOR
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/formularioi.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DenunciaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDD : " + base_model.getPersonaVariableVictima().getId_pervar());
        System.out.println("FFFFFFFFFFFFFFFFFFFF : " + base_model.getPersonaVariableAgresor().getId_pervar());
        System.out.println("ggggggggggggggggGGGGGGGGGGGGGG : " + base_model.getPersonaAgresor().getId_persona());
    }

    private Usuario_vicagre filtro_usuario_vicagre;
    private boolean flag_tblResultadoFind_codruv;

    public void find_codruv() {
        this.flag_tblResultadoFind_codruv = false;
        this.list_base_models = new ArrayList<>();
        Base_modelDao base_modDao = new Base_modelDaoImpl();
        //list_base_models = new ArrayList<>();
        list_base_models = base_modDao.find_codruv(filtro_usuario_vicagre.getCod_sistema());
        if (list_base_models.size()>0) {
            this.flag_tblResultadoFind_codruv = true;
        }
        System.out.println("listaaaaaaaaaaaaaaa" + list_base_models.size());
    }

    //metodo de verificacion de ci
    public void handleKeyEventVerificaCI() {
        Base_modelDao dao = new Base_modelDaoImpl();
        if (!base_model.getPersonaVictima().getNumero().equals("") && base_model.getPersonaVictima().getCodigo_documento() != null) {
            Persona obj = dao.find_exist_ci(base_model.getPersonaVictima());
            System.out.println("ideeeeeee" + obj.getId_persona());
            if (obj.getId_persona() != null) {
                this.base_model.setPersonaVictima(obj);
                calcular_edad();
                Persona_variables per_var = dao.find_exist_detalle(obj);
                System.out.println("abfsghsasjkhdjkahsdkl" + per_var.getId_pervar());
                this.base_model.setPersonaVariableVictima(per_var);

                if (base_model.getPersonaVariableVictima().isMunicipio()) {
                    this.selectOneRadioMunicio = "1";
                } else {
                    this.selectOneRadioMunicio = "0";
                }

                if (base_model.getPersonaVariableVictima().isArea()) {
                    this.selectOneRadioBarrio = "1";
                } else {
                    this.selectOneRadioBarrio = "0";
                }

                if (base_model.getPersonaVariableVictima().isEtnia()) {
                    this.selectOneRadioPueblOrig = "1";
                } else {
                    this.selectOneRadioPueblOrig = "0";
                }
                if (base_model.getPersonaVariableVictima().isCondicion_act()) {
                    this.selectOneRadioCondicion_act = "1";
                } else {
                    this.selectOneRadioCondicion_act = "0";
                }

                if (base_model.getPersonaVariableVictima().isMunicipio()) {
                    this.flag_Municipio = true;
                }

                if (base_model.getPersonaVariableAgresor().isMunicipio()) {
                    this.flag_MunicipioAgresor = true;
                }

                if (base_model.getPersonaVictima().getLugar_nac() != 34) {
                    this.flag_LNac = true;
                }

                if (base_model.getPersonaVariableVictima().isMunicipio()) {
                    this.flag_Municipio = true;
                }

                if (base_model.getPersonaVariableAgresor().isMunicipio()) {
                    this.flag_MunicipioAgresor = true;
                }

                if (base_model.getPersonaVictima().getLugar_nac() != 34) {
                    this.flag_LNac = true;
                }

                if (base_model.getPersonaVariableVictima().isEtnia()) {
                    this.flag_pueblo_orig = true;
                }

                List<T_datos> listRes = new ArrayList<>();
                List<T_datos> listSel = new ArrayList<>();
                String[] arrayIdiomas = base_model.getPersonaVariableVictima().getIdiomas().split(",");
                T_datosDao personaDao = new T_datosDaoImpl();
                listaResult = personaDao.findAllIdt("idioma_hablado");
                for (T_datos item : listaResult) {
                    boolean a = false;
                    for (int i = 0; i < arrayIdiomas.length; i++) {
                        if (arrayIdiomas[i].equals(item.getId_tdatos().toString())) {
                            listSel.add(item);
                            a = true;
                        }
                    }
                    if (!a) {
                        listRes.add(item);
                    }
                }
                listaResult = new ArrayList<>();
                this.listaResult = listRes;
                this.listaSelect = listSel;

            } else {
                base_model.getPersonaVictima().setId_persona(null);
                base_model.getPersonaVictima().setNombre("");
                base_model.getPersonaVictima().setPaterno("");
                base_model.getPersonaVictima().setMaterno("");
                base_model.getPersonaVictima().setAp_casada("");
                base_model.getPersonaVictima().setSexo("");
                base_model.getPersonaVictima().setLugar_nac(null);
                base_model.getPersonaVictima().setEsp_lugar_nac("");
                base_model.getPersonaVictima().setFecha_nac(null);
                base_model.getPersonaVariableVictima().setEdad(null);
                base_model.setPersonaVariableVictima(new Persona_variables());
            }
        }
    }

    public void handleKeyEventVerificaCIAgresor() {
        Base_modelDao dao = new Base_modelDaoImpl();
        if (!base_model.getPersonaAgresor().getNumero().equals("") && base_model.getPersonaAgresor().getCodigo_documento() != null) {
            Persona obj = dao.find_exist_ci(base_model.getPersonaAgresor());
            System.out.println("ideeeeeee" + obj.getId_persona());
            if (obj.getId_persona() != null) {
                this.base_model.setPersonaAgresor(obj);
                calcular_edad();
                Persona_variables per_var = dao.find_exist_detalle(obj);
                System.out.println("abfsghsasjkhdjkahsdkl" + per_var.getId_pervar());
                this.base_model.setPersonaVariableAgresor(per_var);
            } else {
                base_model.getPersonaAgresor().setId_persona(0);
                base_model.getPersonaAgresor().setNombre("");
                base_model.getPersonaAgresor().setPaterno("");
                base_model.getPersonaAgresor().setMaterno("");
                base_model.getPersonaAgresor().setAp_casada("");
                base_model.getPersonaAgresor().setSexo("");
                base_model.getPersonaAgresor().setLugar_nac(null);
                base_model.getPersonaAgresor().setEsp_lugar_nac("");
                base_model.getPersonaAgresor().setFecha_nac(null);
                base_model.setPersonaVariableAgresor(new Persona_variables());
            }
        }

    }

    public Persona getFiltro_persona() {
        return filtro_persona;
    }

    public void setFiltro_persona(Persona filtro_persona) {
        this.filtro_persona = filtro_persona;
    }

    public List<Base_model> getList_base_models() {
        return list_base_models;
    }

    public void setList_base_models(List<Base_model> list_base_models) {
        this.list_base_models = list_base_models;
    }

    public Base_model getSelectedBaseModel() {
        return selectedBaseModel;
    }

    public void setSelectedBaseModel(Base_model selectedBaseModel) {
        this.selectedBaseModel = selectedBaseModel;
    }

    public Usuario_vicagre getFiltro_usuario_vicagre() {
        return filtro_usuario_vicagre;
    }

    public void setFiltro_usuario_vicagre(Usuario_vicagre filtro_usuario_vicagre) {
        this.filtro_usuario_vicagre = filtro_usuario_vicagre;
    }

    public boolean isFlag_tblResultadoFind_codruv() {
        return flag_tblResultadoFind_codruv;
    }

    public void setFlag_tblResultadoFind_codruv(boolean flag_tblResultadoFind_codruv) {
        this.flag_tblResultadoFind_codruv = flag_tblResultadoFind_codruv;
    }

    public List<T_datos> getListaIdimaHablado() {
        return listaIdimaHablado;
    }

    public void setListaIdimaHablado(List<T_datos> listaIdimaHablado) {
        this.listaIdimaHablado = listaIdimaHablado;
    }

    public List<T_datos> getListaTipoViolencia() {
        return listaTipoViolencia;
    }

    public void setListaTipoViolencia(List<T_datos> listaTipoViolencia) {
        this.listaTipoViolencia = listaTipoViolencia;
    }

    public Persona getFiltro_persona_agre() {
        return filtro_persona_agre;
    }

    public void setFiltro_persona_agre(Persona filtro_persona_agre) {
        this.filtro_persona_agre = filtro_persona_agre;
    }

    public String getUrl_servlet() {
        return url_servlet;
    }

    public void setUrl_servlet(String url_servlet) {
        this.url_servlet = url_servlet;
    }

    public boolean isFlagOpcion() {
        return flagOpcion;
    }

    public void setFlagOpcion(boolean flagOpcion) {
        this.flagOpcion = flagOpcion;
    }

    public int getExiste_agr() {
        return existe_agr;
    }

    public void setExiste_agr(int existe_agr) {
        this.existe_agr = existe_agr;
    }
    
public String buttonVerPDF(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String codigo = map.get("codigo_vicagre");
        String tipo_violen = map.get("tipo_violen");
        String tiene_agresor = map.get("tiene_agresor");
        
        UsuarioController obj = (UsuarioController) ConnectionDB.getSessionBean("usuarioController");
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
        
        if (!tiene_agresor.equals("0")) {
            this.url_servlet = url + "/Formulario?cod=" + codigo + "&tv=" + tipo_violen + "&cu=" + obj.getUsuario().getCod_usuario();
            System.out.println("url:" + url_servlet);
        }else{
            this.url_servlet = url + "/Formulario_?cod=" + codigo + "&tv=" + tipo_violen + "&cu=" + obj.getUsuario().getCod_usuario();
            System.out.println("url:" + url_servlet);
        }
    return "impPDF";

    }



public void fireSelection(ValueChangeEvent event) {
    System.out.println("valor req: New: "+event.getNewValue()+", Old: "+event.getOldValue());

}


public int codigoUser;

 public void datosDialogo(){             
       
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String codigoUsuario = map.get("codigoUsuario");
        this.codigoUser = Integer.parseInt(codigoUsuario);
        System.out.println("controller.DFViewEstadoCaso.abrirDialogo() Codigo Caso: "+codigoUser);
     
        selectedBaseModel = new Base_model();
        //selectedBase_model = new Base_model();
        base_model = new Base_model();
        
        Victima_agresor data = new Victima_agresor();
        data.setId_vicagre(codigoUser);
        Base_modelDao dao = new Base_modelDaoImpl();
        base_model = dao.data_baseModel(data);
        
        /*
        int idvicagrel = base_model.getVicAgre_persona().getVictima_agresor_id_vicagre();
        String nombrel = base_model.getPersonaVictima().getNombre();
        String paternol = base_model.getPersonaVictima().getPaterno();
        String maternol = base_model.getPersonaVictima().getMaterno();
        Boolean estadol = base_model.getVicAgre_persona().isCaso_resuelto();*/
        
        System.out.println("controller.DFViewEstadoCaso.abrirDialogo(): Nombre victima"+base_model.getPersonaVictima().getNombre()+" paterno "
                +base_model.getPersonaVictima().getPaterno()+" materno "+base_model.getPersonaVictima().getMaterno()+" estado "
                +base_model.getVicAgre_persona().isCaso_resuelto()+" id "+base_model.getVicAgre_persona().getVictima_agresor_id_vicagre());
        
        
        DFViewEstadoCaso estadoCaso = new DFViewEstadoCaso();
        estadoCaso.abrirDialogo();
                
    }
 
 
 
 /************** Edicion Boris: Despues del Caso ********************************/
 
   public boolean isFlag_despues_caso() {
        return flag_despues_caso;
    }

    public void setFlag_despues_caso(boolean flag_despues_caso) {
        this.flag_despues_caso = flag_despues_caso;
    }

   

 public void handleDespuesCaso() {
        this.flag_despues_caso = false;
        
        //FacesContext context = FacesContext.getCurrentInstance();
        //Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        
        //String otro = map.get("Otro");
       // System.out.println("controller.DenunciaController.handleDespuesCaso() Despues del Caso: LLAMADNO AL LSITENER"+getFlag_despues_caso());
        String despuescaso = "";
        T_datosDao personaDao = new T_datosDaoImpl();
        List<SelectItem> arrayList = personaDao.findAll_idt_recepcion("despues_caso");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getValue().equals(base_model.getVicAgre_persona().getContra_ref_caso_de())) {
                despuescaso = arrayList.get(i).getLabel();
            }
        }
      
        if (despuescaso.equals("Otra Instancia")) {
            this.flag_despues_caso = true;
            System.out.println("controller.DenunciaController.handleDespuesCaso() Despues del Caso: TRUE");
        }
    }
 
 
  //edit boris modifica el estado del caso
 
    public void cancelar_estado_caso(){
        System.out.println("controller.DenunciaController.cancelar_estado_caso() : CANCELAR ESTADO" );
        RequestContext.getCurrentInstance().closeDialog("viewEstadoCaso");     
        refresh();
      
    }
    public void update_estado_caso() {
        String res = "";
        String tipo_violencia = "";
        Integer contador = 0;
        for (T_datos item : listaSelectTipoViolencia) {
            if (contador > 0) {
                tipo_violencia = tipo_violencia + item.getId_tdatos() + ",";
            } else {
                tipo_violencia = "," + item.getId_tdatos() + ",";
            }
            contador++;
        }
        base_model.getVictima_agresor().setTviolencia(tipo_violencia);
        calcularAlerta();
        //System.out.println("PREG_SUMA "+ base_model.getVictima_agresor().getPreg_suma());
        //System.out.println("entra :");
        Base_modelDao base_modelDao = new Base_modelDaoImpl();
        if (base_modelDao.insert_vic(2, base_model, 0, 1) == 0) {
            System.out.println("Error, no se modifico correctamente");
        } else {
            System.out.println("Exito, se modifico correctamente");
            RequestContext.getCurrentInstance().closeDialog("viewEstadoCaso");
            //res = "detalle";
        }
        base_model = new Base_model();
        Base_modelDao base_modDao = new Base_modelDaoImpl();
        list_base_models = base_modDao.find_year();
        refresh();
        //return res;
    }
    
    public String refresh(){
        System.out.println("controller.DenunciaController.refresh() REFRESH");
        String res = "/menu.xhtml?faces-redirect=true";
        return res;
    }
}

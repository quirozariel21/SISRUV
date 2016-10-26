/*
 * To change this license header, choose License Headers in Project Properties.
 * and open the template in the editor.
 */
package denunciacontroller;

import controller.UsuarioController;
import controller.UsuarioController;
import denunciadao.DDenunciaDao;
import denunciadao.DDenunciaDaoImpl;
import denunciadao.DInstitucionDao;
import denunciadao.DInstitucionDaoImpl;
import denunciadao.DT_datosDao;
import denunciadao.DT_datosDaoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import denunciamodel.DDenuncia;
import denunciamodel.DInstitucion;
import denunciamodel.DT_datos;
import org.primefaces.event.CloseEvent;
import util.ConnectionDB;
import util.ConnectionUDB;

/**
 *
 * @author KRETCOooo
 */
@ManagedBean
@SessionScoped
public class DDenunciaController {

    private DDenuncia denuncia;
    private DInstitucion institucion;
    private List<DDenuncia> listadenuncias;
    private List<DInstitucion> listaInstituciones;
    private boolean flag_tipo_violencia = false;
    private List<DT_datos> listaResultTipoViolencia;
    private List<DT_datos> listaSelectTipoViolencia;
    private List<DT_datos> listaTipoViolencia;
    private boolean flagDialogVictimaAgresor = false;

    private boolean flagDialogAgresor = false;
    private boolean flagItemPerfilAgresor = false;
    private boolean flag_DocIden = false;
    private String url_servlet;

    public DDenunciaController() {
        System.out.println("DENUNCIA CONTROLER");
        denuncia = new DDenuncia();
        institucion = new DInstitucion();
        listadenuncias = new ArrayList<>();
        listaInstituciones = new ArrayList<>();
        listaResultTipoViolencia = new ArrayList<>();
        listaSelectTipoViolencia = new ArrayList<>();
        DT_datosDao personaDao = new DT_datosDaoImpl();

        listaResultTipoViolencia = personaDao.findAllIdt("ambito_tipo_violencia");
        listaSelectTipoViolencia = new ArrayList<>();
        listaTipoViolencia = listaResultTipoViolencia;
    }

    public String botonDialogVictimaAgresorAceptar() {
        String resp = "";
        String tipo_violencia = "";
        Integer contador = 0;
        for (DT_datos item : listaSelectTipoViolencia) {
            if (contador > 0) {
                tipo_violencia = tipo_violencia + item.getId_tdatos() + ",";
            } else {
                tipo_violencia = "," + item.getId_tdatos() + ",";
            }
            contador++;
        }

        denuncia.setTviolencia(tipo_violencia);
        System.out.println("TIPOS DE VIOLENCIA : " + denuncia.getTviolencia());
//setea datossss
        DDenunciaDao denunciaDao = new DDenunciaDaoImpl();
        int sw = denunciaDao.insert(1, denuncia);
        System.out.println("CODIGO : " + sw);
        if (sw != 0) {
            UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");
            System.out.println("CODIGO SWWWWWWWW : " + sw);
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
        } else {
            this.flagDialogAgresor = false;
        }
        return resp;
    }

    public void reset() {
        DT_datosDao personaDao = new DT_datosDaoImpl();
        denuncia = new DDenuncia();
        listaSelectTipoViolencia = new ArrayList<>();
        listaResultTipoViolencia = personaDao.findAllIdt("ambito_tipo_violencia");
        this.flagDialogAgresor = false;
        this.flagDialogVictimaAgresor = false;
    }

    public void insert() {
        DT_datosDao personaDao = new DT_datosDaoImpl();
        String tipo_violencia = "";
        Integer contador = 0;

        for (DT_datos item : listaSelectTipoViolencia) {
            if (contador > 0) {
                tipo_violencia = tipo_violencia + item.getId_tdatos() + ",";
            } else {
                tipo_violencia = "," + item.getId_tdatos() + ",";
            }
            contador++;
        }

        denuncia.setTviolencia(tipo_violencia);

        DDenunciaDao denunciaDao = new DDenunciaDaoImpl();
        if (denunciaDao.insert(1, denuncia) == 0) {
            System.out.println("Error, no se inserto correctamente");
        } else {
            System.out.println("Exito, se inserto correctamente");
        }
        denuncia = new DDenuncia();
        listaSelectTipoViolencia = new ArrayList<>();
        listaResultTipoViolencia = personaDao.findAllIdt("ambito_tipo_violencia");
    }

    public void update() {

        DDenunciaDao denunciaDao = new DDenunciaDaoImpl();
        if (denunciaDao.insert(2, denuncia) == 0) {
            System.out.println("Error, no se inserto correctamente");
        } else {
            System.out.println("Exito, se inserto correctamente");
        }
        denuncia = new DDenuncia();
    }

    public void insertinst() {

        DInstitucionDao institucionDao = new DInstitucionDaoImpl();
        if (institucionDao.insert(1, institucion) == 0) {
            System.out.println("Error, no se inserto correctamente");
        } else {
            System.out.println("Exito, se inserto correctamente");
        }
        denuncia = new DDenuncia();
    }

    public void updateinst() {

        DInstitucionDao institucionDao = new DInstitucionDaoImpl();
        if (institucionDao.insert(2, institucion) == 0) {
            System.out.println("Error, no se inserto correctamente");
        } else {
            System.out.println("Exito, se inserto correctamente");
        }
        denuncia = new DDenuncia();
    }

    public DDenuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(DDenuncia denuncia) {
        this.denuncia = denuncia;
    }

    public DInstitucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(DInstitucion institucion) {
        this.institucion = institucion;
    }

    public List<DDenuncia> getListadenuncias() {
        DDenunciaDao denunciaDao = new DDenunciaDaoImpl();
        listadenuncias = denunciaDao.findAll();
        return listadenuncias;
    }

    public void setListadenuncias(List<DDenuncia> listadenuncias) {
        this.listadenuncias = listadenuncias;
    }

    public List<DInstitucion> getListaInstituciones() {
        DInstitucionDao institucionDao = new DInstitucionDaoImpl();
        listaInstituciones = institucionDao.findAll();
        return listaInstituciones;
    }

    public void setListaInstituciones(List<DInstitucion> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }

    public boolean isFlag_tipo_violencia() {
        return flag_tipo_violencia;
    }

    public void setFlag_tipo_violencia(boolean flag_tipo_violencia) {
        this.flag_tipo_violencia = flag_tipo_violencia;
    }

    public void actionDataListTipoViolenciaA() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String val = map.get("seleccion");
        for (DT_datos item : listaResultTipoViolencia) {
            if (item.getId_tdatos() == Integer.parseInt(val)) {
                listaSelectTipoViolencia.add(item);
            }
        }

        List<DT_datos> list_aux = new ArrayList<>();
        for (DT_datos item : listaResultTipoViolencia) {
            if (item.getId_tdatos() != Integer.parseInt(val)) {
                list_aux.add(item);
            }
        }
        this.listaResultTipoViolencia = list_aux;

        Integer contador = 0;
        for (DT_datos item : listaSelectTipoViolencia) {
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
        for (DT_datos item : listaSelectTipoViolencia) {
            if (item.getId_tdatos() == Integer.parseInt(val)) {
                listaResultTipoViolencia.add(item);
            }
        }

        List<DT_datos> list_aux = new ArrayList<>();
        for (DT_datos item : listaSelectTipoViolencia) {
            if (item.getId_tdatos() != Integer.parseInt(val)) {
                list_aux.add(item);
            }
        }
        this.listaSelectTipoViolencia = list_aux;

        Integer contador = 0;
        for (DT_datos item : listaSelectTipoViolencia) {
            if (item.getDescripcion().equals("Otros")) {
                contador++;
            }
        }
        this.flag_tipo_violencia = false;
        if (contador > 0) {
            this.flag_tipo_violencia = true;
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

    public List<DT_datos> getListaResultTipoViolencia() {
        return listaResultTipoViolencia;
    }

    public void setListaResultTipoViolencia(List<DT_datos> listaResultTipoViolencia) {
        this.listaResultTipoViolencia = listaResultTipoViolencia;
    }

    public List<DT_datos> getListaSelectTipoViolencia() {
        return listaSelectTipoViolencia;
    }

    public void setListaSelectTipoViolencia(List<DT_datos> listaSelectTipoViolencia) {
        this.listaSelectTipoViolencia = listaSelectTipoViolencia;
    }

    public List<DT_datos> getListaTipoViolencia() {
        return listaTipoViolencia;
    }

    public void setListaTipoViolencia(List<DT_datos> listaTipoViolencia) {
        this.listaTipoViolencia = listaTipoViolencia;
    }

    public void add_victima_relacion() {
        int sw2 = 0;
        int sw3 = 0;
        int sw4 = 0;
        if (listaSelectTipoViolencia.size() > 0) {
            sw2 = 1;
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: Necesita seleccionar una violencia por lo menos", "Necesita seleccionar una violencia por lo menos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (denuncia.getEdad_victima() != 0) {
            sw3 = 1;
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: Necesita introducir la Edad", "Necesita Necesita introducir la Edad");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        if (denuncia.getNumero_victima() != 0) {
            sw4 = 1;
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: Necesita introducir el Numero de Carnet", "Necesita introducir el Numero de Carnet");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (sw2 == 1 && sw3 == 1 && sw4 == 1) {
            this.flagDialogVictimaAgresor = true;
        }
    }

    public boolean isFlagDialogVictimaAgresor() {
        return flagDialogVictimaAgresor;
    }

    public void setFlagDialogVictimaAgresor(boolean flagDialogVictimaAgresor) {
        this.flagDialogVictimaAgresor = flagDialogVictimaAgresor;
    }

    public void handleCloseDialog(CloseEvent event) {
        botonDialogVictimaAgresorCancelar();
    }

    public void botonDialogVictimaAgresorCancelar() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "close", "close detail");
        FacesContext.getCurrentInstance().addMessage(null, message);
        cancela_action();

    }

    public void cancela_action() {
        this.flagDialogVictimaAgresor = false;

    }

    public String getUrl_servlet() {
        return url_servlet;
    }

    public void setUrl_servlet(String url_servlet) {
        this.url_servlet = url_servlet;
    }

    public boolean isFlagDialogAgresor() {
        return flagDialogAgresor;
    }

    public void setFlagDialogAgresor(boolean flagDialogAgresor) {
        this.flagDialogAgresor = flagDialogAgresor;
    }

    public void botonDialogAddAgresorCancelar() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "close", "close detail");
        FacesContext.getCurrentInstance().addMessage(null, message);
        cancela_action();
    }

    public String action_formiii_formii() {

        this.flagItemPerfilAgresor = false;

        return "fiii_fii";
    }

    public void handleCIChange() {
        this.flag_DocIden = false;
        if (denuncia.getCi_victima() > 0) {
            this.flag_DocIden = true;
        }
    }

    public boolean isFlagItemPerfilAgresor() {
        return flagItemPerfilAgresor;
    }

    public void setFlagItemPerfilAgresor(boolean flagItemPerfilAgresor) {
        this.flagItemPerfilAgresor = flagItemPerfilAgresor;
    }

    public boolean isFlag_DocIden() {
        return flag_DocIden;
    }

    public void setFlag_DocIden(boolean flag_DocIden) {
        this.flag_DocIden = flag_DocIden;
    }

    public String buttonVerPDF() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String codigo = map.get("id_denuncia");
        String tipo_violencia = map.get("tipo_violen");
       // String tiene_agresor = map.get("tiene_agresor");

        UsuarioController obj = (UsuarioController) ConnectionDB.getSessionBean("usuarioController");
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
        System.out.println("wwwwwwwwwwwwwwwwww : " + codigo);

        this.url_servlet = url + "/Formulario?cod=" + codigo + "&tv=" + tipo_violencia + "&cu=" + obj.getUsuario().getCod_usuario();
        System.out.println("url:" + url_servlet);

        return "imprime_det";

    }

}

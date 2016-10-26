/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesocontroller;

import accesodao.AInstitucionDao;
import accesodao.AInstitucionDaoImpl;
import accesodao.APreguntaDao;
import accesodao.APreguntaDaoImpl;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import accesomodel.AInstitucion;
import accesomodel.APregunta;
import accesomodel.AUsuario;
import accesomodel.AUsuarioInstitucion;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author KRETCO
 */
@ManagedBean
@SessionScoped
public class AInstitucionController {

    private AInstitucion institucion;
    private APregunta pregunta;
    private AInstitucion selectedInstitucion;
    private AUsuarioInstitucion selectedUsuInst;
    private APregunta selectedPregunta;
    private AUsuario usuario;
    private AUsuario selectUsuarioInstitucion;
    private AUsuarioInstitucion usuarioInstitucion;
    private List<AInstitucion> listInstituciones;
    private List<APregunta> listPreguntas;
    
    AInstitucionDao institucionDao = new AInstitucionDaoImpl();
    APreguntaDao preguntaDao = new APreguntaDaoImpl();

    private boolean flag_Inst = false;
    private boolean flag_Preg = false;

    private List<SelectItem> itemInstitucion;

    public AInstitucionController() {
        institucion = new AInstitucion();
        pregunta = new APregunta();
        selectedInstitucion = new AInstitucion();
        selectedUsuInst = new AUsuarioInstitucion();
        selectedPregunta = new APregunta();
        usuario = new AUsuario();
        usuarioInstitucion = new AUsuarioInstitucion();
    }

    public String insertInstitucion() {
        String resp = "";
        if (institucionDao.insert(1, institucion) == true) {
            System.out.println("Se Inserto Correctamente");
            institucion = new AInstitucion();
            resp = "nueva_inst";
        } else {
            System.out.println("No se Inserto Correctamente");
        }
        return resp;
    }

    public String insertPregunta() {
        String resp = "";
        if (this.preguntaDao.insert(1, this.pregunta) == true) {
            System.out.println("Se Inserto Correctamente");
            pregunta = new APregunta();
            resp = "nueva_preg";
        } else {
            System.out.println("No se Inserto Correctamente");
        }
        return resp;
    }

    public String modificarInstitucion() {
        String resp = "";
        if (institucionDao.insert(2, institucion) == true) {
            System.out.println("Se Inserto Correctamente");
            institucion = new AInstitucion();
            resp = "nueva_inst";
        } else {
            System.out.println("No se Inserto Correctamente");
        }
        return resp;
    }

    public String modificarPregunta() {
        String resp = "";
        if (this.preguntaDao.insert(2, this.pregunta) == true) {
            System.out.println("Se Inserto Correctamente");
            pregunta = new APregunta();
            resp = "nueva_preg";
        } else {
            System.out.println("No se Inserto Correctamente");
        }
        return resp;
    }
    
    public String buttonInsertUsuInst() {
        String resp = "";
        System.out.println("INSTITUCIONNNN : " + usuarioInstitucion.getInstitucion_id_institucion());
        System.out.println("USUASRIOOOOOOO : " + usuarioInstitucion.getUsuario_cod_usuario());
        
        
        if (institucionDao.insertUsuInst(1, usuarioInstitucion.getUsuario_cod_usuario(), usuarioInstitucion.getInstitucion_id_institucion()) == true) {
            System.out.println("Se Guardo Correctamente");
            resp = "asigusuRe";
            usuarioInstitucion = new AUsuarioInstitucion();
            
        } else {
            System.out.println("No se Inserto Correctamente");
        }
        return resp;
    }
    
    
    public void buttonAction() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String codigo = map.get("codigo");
        selectedUsuInst.setInstitucion_id_institucion(Integer.parseInt(codigo));
        this.usuarioInstitucion = getSelectedUsuInst();

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/asignacion_usuario.xhtml");
        } catch (IOException ex) {
            ex.getMessage();
        }

    }        
        
    public void buttonActionUsuario() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String codigo = map.get("codigo");
        selectedUsuInst.setInstitucion_id_institucion(Integer.parseInt(codigo));
        this.usuarioInstitucion = getSelectedUsuInst();
        System.out.println("SELECT USUAINST : " + selectedUsuInst);

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/usuario_institucion.xhtml");
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public void showInstUsu(){
        
    }

    public void onRowSelect(SelectEvent event) {
        this.institucion = getSelectedInstitucion();

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/institucion.xhtml");
            this.flag_Inst = false;
            if (institucion.getId_institucion() > 0) {
                this.flag_Inst = true;
            }
        } catch (IOException ex) {
            ex.getMessage();
        }

    }
    
    public void onRowSelectP(SelectEvent event) {
        this.pregunta = getSelectedPregunta();

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
        
        itemInstitucion = institucionDao.findAll_Inst(pregunta.getInstitucion_id_institucion());
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/pregunta.xhtml");
            this.flag_Preg = false;
            if (pregunta.getId_pregunta() > 0) {
                this.flag_Preg = true;
            }
        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    public AInstitucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(AInstitucion institucion) {
        this.institucion = institucion;
    }

    public APregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(APregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<AInstitucion> getListInstituciones() {
        listInstituciones = institucionDao.findAll_Instituciones();
        return listInstituciones;
    }

    public void setListInstituciones(List<AInstitucion> listInstituciones) {
        this.listInstituciones = listInstituciones;
    }

    public List<APregunta> getListPreguntas() {
        listPreguntas = preguntaDao.findAll_Preguntas();
        return listPreguntas;
    }

    public void setListPreguntas(List<APregunta> listPreguntas) {
        this.listPreguntas = listPreguntas;
    }

    public AInstitucion getSelectedInstitucion() {
        return selectedInstitucion;
    }

    public void setSelectedInstitucion(AInstitucion selectedInstitucion) {
        this.selectedInstitucion = selectedInstitucion;
    }

    public List<SelectItem> getItemInstitucion() {
        itemInstitucion = preguntaDao.findAll_nInst("indicador");
        return itemInstitucion;
    }

    public void setItemInstitucion(List<SelectItem> itemInstitucion) {
        this.itemInstitucion = itemInstitucion;
    }

    public APregunta getSelectedPregunta() {
        return selectedPregunta;
    }

    public void setSelectedPregunta(APregunta selectedPregunta) {
        this.selectedPregunta = selectedPregunta;
    }

    public boolean isFlag_Inst() {
        return flag_Inst;
    }

    public void setFlag_Inst(boolean flag_Inst) {
        this.flag_Inst = flag_Inst;
    }

    public boolean isFlag_Preg() {
        return flag_Preg;
    }

    public void setFlag_Preg(boolean flag_Preg) {
        this.flag_Preg = flag_Preg;
    }
    
    public String buttonNuevaInst() {
        institucion = new AInstitucion();
        selectedInstitucion = null;
        this.flag_Inst = false;
        return "instRe";
    }
    
    public String buttonNuevaPreg() {
        pregunta = new APregunta();
        selectedPregunta = null;
        this.flag_Preg = false;
        return "pregRe";
    }

    public AUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(AUsuario usuario) {
        this.usuario = usuario;
    }

    public AUsuarioInstitucion getSelectedUsuInst() {
        return selectedUsuInst;
    }

    public void setSelectedUsuInst(AUsuarioInstitucion selectedUsuInst) {
        this.selectedUsuInst = selectedUsuInst;
    }

    public AUsuarioInstitucion getUsuarioInstitucion() {
        return usuarioInstitucion;
    }

    public void setUsuarioInstitucion(AUsuarioInstitucion usuarioInstitucion) {
        this.usuarioInstitucion = usuarioInstitucion;
    }

    public AUsuario getSelectUsuarioInstitucion() {
        selectUsuarioInstitucion = institucionDao.findUsuInst(selectedUsuInst.getInstitucion_id_institucion());
        return selectUsuarioInstitucion;
    }

    public void setSelectUsuarioInstitucion(AUsuario selectUsuarioInstitucion) {
        this.selectUsuarioInstitucion = selectUsuarioInstitucion;
    }
    
    
}

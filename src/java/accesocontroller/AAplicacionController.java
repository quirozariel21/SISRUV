/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesocontroller;

import accesodao.AAplicacionesDao;
import accesodao.AAplicacionesDaoImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import accesomodel.AAplicaciones;
import accesomodel.ADescripcionDatos;
import accesomodel.AT_datos;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author e_mv
 */
@ManagedBean
@SessionScoped
public class AAplicacionController {

       AAplicacionesDao aplicacionesDao = new AAplicacionesDaoImpl();
    
    private AAplicaciones selectCodAplicaciones;
    private List<SelectItem> selectItemsAplicaciones;    
    private List<ADescripcionDatos> listaDescripcionDatos;
    private ADescripcionDatos selectedDescripcionDatos;
    private boolean renderDescripcionDatos;
    
    private List<AT_datos> listaT_datos_app;
    private AT_datos selectT_datos_app;    
    
    private String opcionT_datos;
    private AT_datos registroT_datos;
    private boolean renderActionABM;
    private boolean tdatoHabilitado;
    
    List<ADescripcionDatos> listDescripcionDatosComplejos;
    private boolean renderT_datos_padre;
    private List<SelectItem> selectItemsT_datos_padre;   
    //List<DescripcionDatos> listDescripcionDatosComplejos = aplicacionesDao.listDescripcionDatosComplejos(selectCodAplicaciones);

    /**
     * Creates a new instance of AplicacionController
     */
    public AAplicacionController() {
        this.selectCodAplicaciones = new AAplicaciones();
        this.selectItemsAplicaciones = new ArrayList<>();
        this.selectItemsAplicaciones = aplicacionesDao.itemAplicaciones_Tdatos();
        this.listaDescripcionDatos = new ArrayList<>();
        this.selectedDescripcionDatos = new ADescripcionDatos();
        this.renderDescripcionDatos = false;
        
        this.listaT_datos_app = new ArrayList<>();
        this.selectT_datos_app = new AT_datos();
        this.selectT_datos_app = new AT_datos();
        this.opcionT_datos = "";
        this.registroT_datos = new AT_datos();
        
    }
    
    public void onChangeSelectAplicacion(){
        System.out.println("entra a esta opcion"+getSelectCodAplicaciones());
        if (getSelectCodAplicaciones() != null) {
            selectCodAplicaciones = aplicacionesDao.findAplicaciones_Tdatos(selectCodAplicaciones.getId_app());        
            this.listaDescripcionDatos = aplicacionesDao.listDescripcionDatos(selectCodAplicaciones);
            this.renderDescripcionDatos = true;            
        }else{
            this.listaDescripcionDatos = new ArrayList<>();
            this.renderDescripcionDatos = false;
        }
    }
    
    public void onRowSelectDescripcionDatos(SelectEvent event) {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
        
                this.listaT_datos_app = aplicacionesDao.listT_datos(selectCodAplicaciones, selectedDescripcionDatos);
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/t_datos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AAplicacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String buttonNuevoT_datos(){
        
        this.opcionT_datos = "Adicion";
        this.registroT_datos = new AT_datos();
        this.renderActionABM = false;
        this.renderT_datos_padre = false;
        this.selectItemsT_datos_padre = new ArrayList<>();

        System.out.println("este es el valor res : " +selectedDescripcionDatos.getId_t());
        
        List<SelectItem> valida_Tdatos = new ArrayList<>();
        valida_Tdatos = aplicacionesDao.validaT_datos(selectCodAplicaciones, selectedDescripcionDatos.getId_t());
        
        System.out.println("valida datesss:"+valida_Tdatos.size());
        if (valida_Tdatos.size()>0) {
            this.renderT_datos_padre = true;
            this.selectItemsT_datos_padre = aplicacionesDao.findT_datos(selectCodAplicaciones, selectedDescripcionDatos.getId_t());
        }
        
       
        return "t_datos_t_datos_abm";
    }
    
    public void onRowSelectModificaTdatos(SelectEvent event) {
        this.opcionT_datos = "Modificacion";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
        
        this.renderActionABM = true;
        this.registroT_datos = new AT_datos();
        this.registroT_datos = selectT_datos_app;
        System.out.println("jkhasjhaskad"+selectT_datos_app.getEstado());
        if (selectT_datos_app.getEstado()==0) this.tdatoHabilitado = false;
        if (selectT_datos_app.getEstado()==1) this.tdatoHabilitado = true;
        
        System.out.println("este es el valor res : " +selectedDescripcionDatos.getId_t());
        
        List<SelectItem> valida_Tdatos = new ArrayList<>();
        valida_Tdatos = aplicacionesDao.validaT_datos(selectCodAplicaciones, selectedDescripcionDatos.getId_t());
        
        System.out.println("valida datesss:"+valida_Tdatos.size());
        if (valida_Tdatos.size()>0) {
            this.renderT_datos_padre = true;
            this.selectItemsT_datos_padre = aplicacionesDao.findT_datos(selectCodAplicaciones, selectedDescripcionDatos.getId_t());
        }
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/t_datos_abm.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AAplicacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String buttonGuardaTdato(){
        String resp="";
        
        if (!renderT_datos_padre) registroT_datos.setRelacion(0);
        
        if (aplicacionesDao.guardaTdatos(selectCodAplicaciones, selectedDescripcionDatos, registroT_datos)) {
                System.out.println("exito");
                this.listaT_datos_app = aplicacionesDao.listT_datos(selectCodAplicaciones, selectedDescripcionDatos);
                resp = "t_datos_abm_t_datos";
            }else{
                System.out.println("error");
            }
        
        return resp;
        
    }
    
    public String buttonModificaTdato(){
        String resp="";
        if (tdatoHabilitado == false) registroT_datos.setEstado(0);
        if (tdatoHabilitado == true) registroT_datos.setEstado(1);
        if (!renderT_datos_padre) registroT_datos.setRelacion(0);
        
        if (aplicacionesDao.modificarTdatos(selectCodAplicaciones, selectedDescripcionDatos, registroT_datos)) {
            System.out.println("exito");
            this.listaT_datos_app = aplicacionesDao.listT_datos(selectCodAplicaciones, selectedDescripcionDatos);
            resp = "t_datos_abm_t_datos";
        }else{
            System.out.println("error");
        }
        
        return resp;
    }

    public AAplicaciones getSelectCodAplicaciones() {
        return selectCodAplicaciones;
    }

    public void setSelectCodAplicaciones(AAplicaciones selectCodAplicaciones) {
        this.selectCodAplicaciones = selectCodAplicaciones;
    }
    
    public List<SelectItem> getSelectItemsAplicaciones() {
        return selectItemsAplicaciones;
    }

    public void setSelectItemsAplicaciones(List<SelectItem> selectItemsAplicaciones) {
        this.selectItemsAplicaciones = selectItemsAplicaciones;
    }

    public List<ADescripcionDatos> getListaDescripcionDatos() {
        return listaDescripcionDatos;
    }

    public void setListaDescripcionDatos(List<ADescripcionDatos> listaDescripcionDatos) {
        this.listaDescripcionDatos = listaDescripcionDatos;
    }

    public ADescripcionDatos getSelectedDescripcionDatos() {
        return selectedDescripcionDatos;
    }

    public void setSelectedDescripcionDatos(ADescripcionDatos selectedDescripcionDatos) {
        this.selectedDescripcionDatos = selectedDescripcionDatos;
    }

    public boolean isRenderDescripcionDatos() {
        return renderDescripcionDatos;
    }

    public void setRenderDescripcionDatos(boolean renderDescripcionDatos) {
        this.renderDescripcionDatos = renderDescripcionDatos;
    }

    public List<AT_datos> getListaT_datos_app() {
        return listaT_datos_app;
    }

    public void setListaT_datos_app(List<AT_datos> listaT_datos_app) {
        this.listaT_datos_app = listaT_datos_app;
    }

    public AT_datos getSelectT_datos_app() {
        return selectT_datos_app;
    }

    public void setSelectT_datos_app(AT_datos selectT_datos_app) {
        this.selectT_datos_app = selectT_datos_app;
    }

    public String getOpcionT_datos() {
        return opcionT_datos;
    }

    public void setOpcionT_datos(String opcionT_datos) {
        this.opcionT_datos = opcionT_datos;
    }

    public AT_datos getRegistroT_datos() {
        return registroT_datos;
    }

    public void setRegistroT_datos(AT_datos registroT_datos) {
        this.registroT_datos = registroT_datos;
    }

    public boolean isRenderActionABM() {
        return renderActionABM;
    }

    public void setRenderActionABM(boolean renderActionABM) {
        this.renderActionABM = renderActionABM;
    }

    public boolean isTdatoHabilitado() {
        return tdatoHabilitado;
    }

    public void setTdatoHabilitado(boolean tdatoHabilitado) {
        this.tdatoHabilitado = tdatoHabilitado;
    }

    public boolean isRenderT_datos_padre() {
        return renderT_datos_padre;
    }

    public void setRenderT_datos_padre(boolean renderT_datos_padre) {
        this.renderT_datos_padre = renderT_datos_padre;
    }

    public List<SelectItem> getSelectItemsT_datos_padre() {
        return selectItemsT_datos_padre;
    }

    public void setSelectItemsT_datos_padre(List<SelectItem> selectItemsT_datos_padre) {
        this.selectItemsT_datos_padre = selectItemsT_datos_padre;
    }
    
}

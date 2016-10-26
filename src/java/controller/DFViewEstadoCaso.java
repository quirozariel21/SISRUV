/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Base_modelDao;
import dao.Base_modelDaoImpl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.Base_model;
import model.VicAgre_persona;
import model.Victima_agresor;
import org.primefaces.context.RequestContext;

/**
 *
 * @author bsoto
 */

@ManagedBean
public class DFViewEstadoCaso{
    //private Victima_agresor VicAgre;
    //private VicAgre_persona VicAgre_persona;
    private Base_model selectedBase_model;
    private Base_model base_model;      

    public String nombre;
    private String paterno;
    private String materno;
    private int idvicagre;
    private boolean estado;  
    public int codigoUser;
    
    public void datosDialogo(){             
       
        
        selectedBase_model = new Base_model();
        base_model = new Base_model();
        
        Victima_agresor data = new Victima_agresor();
        data.setId_vicagre(codigoUser);
        Base_modelDao dao = new Base_modelDaoImpl();
        base_model = dao.data_baseModel(data);
        
        int idvicagrel = base_model.getVicAgre_persona().getVictima_agresor_id_vicagre();
        String nombrel = base_model.getPersonaVictima().getNombre();
        String paternol = base_model.getPersonaVictima().getPaterno();
        String maternol = base_model.getPersonaVictima().getMaterno();
        Boolean estadol = base_model.getVicAgre_persona().isCaso_resuelto();
        
        System.out.println("controller.DFViewEstadoCaso.abrirDialogo(): Nombre victima"+nombrel+" paterno "+paternol+" materno "+maternol+" estado "+estadol+" id "+idvicagrel);
        
        setIdvicagre(idvicagrel);
        setNombre(nombrel);
        setPaterno(paternol);
        setMaterno(maternol);
        setEstado(estadol);
    }
    
    public void abrirDialogo(){       
        
        
        
        //datosDialogo();
        
        
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 360);
        options.put("height", 660);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        RequestContext.getCurrentInstance().openDialog("viewEstadoCaso", options, null);
                
    }
    
     public boolean isEstado() {
        return estado;
    }

    public String getNombre() {  
        System.out.println("controller.DFViewEstadoCaso.getNombre(): Nombre "+ nombre);
        return nombre;
    }

    public String getPaterno() {
        System.out.println("controller.DFViewEstadoCaso.getNombre(): paterno "+ paterno);
        return paterno;
    }

    public String getMaterno() {
        return materno;
    }
    
    public int getIdvicagre() {
        return idvicagre;
    }
    
    public void setBase_model(Base_model base_model) {
        this.base_model = base_model;
    }

    public Base_model getBase_model() {
        return base_model;
    } 
    
    public void setNombre(String nombre) {
        System.out.println("setNombre(): "+ nombre);
        this.nombre = nombre;             
    }

    public void setPaterno(String paterno) {
        System.out.println("setPaterno(): "+ paterno);
        this.paterno = paterno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public void setIdvicagre(int idvicagre) {
        this.idvicagre = idvicagre;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.T_datosDao;
import dao.T_datosDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import model.T_datos;

/**
 *
 * @author e_mv
 */
@ManagedBean
@ViewScoped
public class T_datosController {
    T_datosDao personaDao=new T_datosDaoImpl();
    
    private List<SelectItem> itemRecepcionCaso;
    private List<SelectItem> itemDocumento;
    private List<SelectItem> itemLugar;
    private List<SelectItem> itemECiivil;
    private List<SelectItem> itemTenenciaVivienda;
    private List<SelectItem> itemNivelInst;
    private List<SelectItem> itemOcupacionPrincipal;
    private List<SelectItem> itemIngresoEconomico;

    private List<SelectItem> itemLugarAgresion;
    private List<T_datos> itemRelacionVicAgr;
    private List<SelectItem> itemFrecuenciaAgresion;
    
    private List<SelectItem> itemFrecuenciaAlcohol;
    private List<SelectItem> itemFrecuenciaDrogas;
    //boris despues caso    
    private List<SelectItem> itemDespuesCaso;
    //codigo eddy
    private List<SelectItem> itemDepartamento;
    private List<SelectItem> itemMunicipios;
    private List<SelectItem> itemServicios;
    private List<SelectItem> itemSubSevicios;
    
    //usuario
    
    
    public T_datosController() {
        itemRecepcionCaso = new ArrayList<>();
        itemDocumento = new ArrayList<>();
        itemLugar = new ArrayList<>();
        itemECiivil = new ArrayList<>();
        itemTenenciaVivienda = new ArrayList<>();
        itemNivelInst = new ArrayList<>();
        itemOcupacionPrincipal = new ArrayList<>();
        itemIngresoEconomico = new ArrayList<>();

        itemRelacionVicAgr = new ArrayList<>();
        itemFrecuenciaAgresion = new ArrayList<>();
        
        itemFrecuenciaAlcohol = new ArrayList<>();
        itemFrecuenciaDrogas = new ArrayList<>();
        //boris despues caso 
        itemDespuesCaso = new ArrayList<>();
        //codigo eddy
        itemDepartamento = new ArrayList<>();
        itemMunicipios = new ArrayList<>();
        itemServicios = new ArrayList<>();
        itemSubSevicios = new ArrayList<>();
    }

    public List<SelectItem> getItemRecepcionCaso() {
        itemRecepcionCaso = personaDao.findAll_idt_recepcion("referencia_caso");
        return itemRecepcionCaso;
    }

    public List<SelectItem> getItemDocumento() {       
        itemDocumento = personaDao.findAll_idt("documento");
        return itemDocumento;
    }

    public List<SelectItem> getItemLugar() {      
        itemLugar = personaDao.findAll_idt("lugar_nac");
        return itemLugar;
    }

    public List<SelectItem> getItemECiivil() {    
        itemECiivil = personaDao.findAll_idt("estado_civil");
        return itemECiivil;
    }

    public List<SelectItem> getItemTenenciaVivienda() {      
        itemTenenciaVivienda = personaDao.findAll_idt("tenencia_vivienda");
        return itemTenenciaVivienda;
    }

    public List<SelectItem> getItemNivelInst() {    
        itemNivelInst = personaDao.findAll_idt("nivel_instruccion");
        return itemNivelInst;
    }

    public List<SelectItem> getItemOcupacionPrincipal() {        
        itemOcupacionPrincipal = personaDao.findAll_idt("ocupacion_principal");
        return itemOcupacionPrincipal;
    }

    public List<SelectItem> getItemIngresoEconomico() {    
        itemIngresoEconomico = personaDao.findAll_idt("ingreso_economico");
        return itemIngresoEconomico;
    }

    public List<SelectItem> getItemLugarAgresion() {
        itemLugarAgresion = personaDao.findAll_idt("lugar_agresion");  
        return itemLugarAgresion;
    }

    public List<T_datos> getItemRelacionVicAgr() {
        itemRelacionVicAgr = personaDao.findAllIdt("relacion_agresor_victima");  
        return itemRelacionVicAgr;
    }

    public List<SelectItem> getItemFrecuenciaAgresion() {
        itemFrecuenciaAgresion = personaDao.findAll_idt("frecuencia_agrecion");
        return itemFrecuenciaAgresion;
    }

    public List<SelectItem> getItemFrecuenciaAlcohol() {
        itemFrecuenciaAlcohol = personaDao.findAll_idt("frecuencia_alcohol");
        return itemFrecuenciaAlcohol;
    }


    public List<SelectItem> getItemFrecuenciaDrogas() {
        itemFrecuenciaDrogas = personaDao.findAll_idt("frecuencia_drogas");
        return itemFrecuenciaDrogas;
    }
    
    
     public List<SelectItem> getItemDespuesCaso() {
        itemRecepcionCaso = personaDao.findAll_idt_recepcion("despues_caso");
        return itemRecepcionCaso;
    }
     
//CODIGO EDDY
    public List<SelectItem> getItemDepartamento() {
        itemDepartamento = personaDao.findAll_idt("departamento");
        return itemDepartamento;
    }

    public T_datosDao getPersonaDao() {
        return personaDao;
    }

    public void setPersonaDao(T_datosDao personaDao) {
        this.personaDao = personaDao;
    }

    

}

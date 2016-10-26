/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denunciacontroller;

import denunciadao.DT_datosDao;
import denunciadao.DT_datosDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import denunciamodel.DT_datos;

/**
 *
 * @author e_mv
 */
@ManagedBean
@ViewScoped
public class DT_datosController {
    
    private List<SelectItem> itemRecepcionCaso;
    private List<SelectItem> itemDocumento;
    private List<SelectItem> itemLugar;
    private List<SelectItem> itemECiivil;
    private List<SelectItem> itemTenenciaVivienda;
    private List<SelectItem> itemNivelInst;
    private List<SelectItem> itemOcupacionPrincipal;
    private List<SelectItem> itemIngresoEconomico;

    private List<SelectItem> itemLugarAgresion;
    private List<DT_datos> itemRelacionVicAgr;
    private List<SelectItem> itemFrecuenciaAgresion;
    
    private List<SelectItem> itemFrecuenciaAlcohol;
    private List<SelectItem> itemFrecuenciaDrogas;
    
    //codigo eddy
    private List<SelectItem> itemDepartamento;
    private List<SelectItem> itemMunicipios;
    private List<SelectItem> itemServicios;
    private List<SelectItem> itemSubSevicios;
    
    public DT_datosController() {
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
        
        //codigo eddy
        itemDepartamento = new ArrayList<>();
        itemMunicipios = new ArrayList<>();
        itemServicios = new ArrayList<>();
        itemSubSevicios = new ArrayList<>();
    }

    public List<SelectItem> getItemRecepcionCaso() {
        return itemRecepcionCaso;
    }

    public List<SelectItem> getItemDocumento() {        
        return itemDocumento;
    }

    public List<SelectItem> getItemLugar() {        
        return itemLugar;
    }

    public List<SelectItem> getItemECiivil() {        
        return itemECiivil;
    }

    public List<SelectItem> getItemTenenciaVivienda() {        
        return itemTenenciaVivienda;
    }

    public List<SelectItem> getItemNivelInst() {        
        return itemNivelInst;
    }

    public List<SelectItem> getItemOcupacionPrincipal() {        
        return itemOcupacionPrincipal;
    }

    public List<SelectItem> getItemIngresoEconomico() {        
        return itemIngresoEconomico;
    }

    public List<SelectItem> getItemLugarAgresion() {
        return itemLugarAgresion;
    }

    public List<DT_datos> getItemRelacionVicAgr() {
        return itemRelacionVicAgr;
    }

    public List<SelectItem> getItemFrecuenciaAgresion() {
        return itemFrecuenciaAgresion;
    }

    public List<SelectItem> getItemFrecuenciaAlcohol() {
        return itemFrecuenciaAlcohol;
    }

    public void setItemFrecuenciaAlcohol(List<SelectItem> itemFrecuenciaAlcohol){
        this.itemFrecuenciaAlcohol = itemFrecuenciaAlcohol;
    }

    public List<SelectItem> getItemFrecuenciaDrogas() {
        return itemFrecuenciaDrogas;
    }

    public void setItemFrecuenciaDrogas(List<SelectItem> itemFrecuenciaDrogas) {
        this.itemFrecuenciaDrogas = itemFrecuenciaDrogas;
    }
//CODIGO EDDY
    public List<SelectItem> getItemDepartamento() {
        return itemDepartamento;
    }

    public void setItemDepartamento(List<SelectItem> itemDepartamento) {
        this.itemDepartamento = itemDepartamento;
    }

    public List<SelectItem> getItemMunicipios() {
        return itemMunicipios;
    }

    public void setItemMunicipios(List<SelectItem> itemMunicipios) {
        this.itemMunicipios = itemMunicipios;
    }

    public List<SelectItem> getItemServicios() {
        return itemServicios;
    }

    public void setItemServicios(List<SelectItem> itemServicios) {
        this.itemServicios = itemServicios;
    }

    public List<SelectItem> getItemSubSevicios() {
        return itemSubSevicios;
    }

    public void setItemSubSevicios(List<SelectItem> itemSubSevicios) {
        this.itemSubSevicios = itemSubSevicios;
    }
    
        
    @PostConstruct
    public void init() {        
        /**Cargamos los datos**/        
        DT_datosDao personaDao=new DT_datosDaoImpl();
        itemRecepcionCaso = personaDao.findAll_idt("referencia_caso");
        itemDocumento = personaDao.findAll_idt("documento");
        itemLugar = personaDao.findAll_idt("lugar_nac");
        itemECiivil = personaDao.findAll_idt("estado_civil");
        itemTenenciaVivienda = personaDao.findAll_idt("tenencia_vivienda");
        itemNivelInst = personaDao.findAll_idt("nivel_instruccion");
        itemOcupacionPrincipal = personaDao.findAll_idt("ocupacion_principal");
        itemIngresoEconomico = personaDao.findAll_idt("ingreso_economico");
            
        itemLugarAgresion = personaDao.findAll_idt("lugar_agresion");        
        itemRelacionVicAgr = personaDao.findAllIdt("relacion_agresor_victima");        
        itemFrecuenciaAgresion = personaDao.findAll_idt("frecuencia_agrecion");
        
        itemFrecuenciaAlcohol = personaDao.findAll_idt("frecuencia_alcohol");
        itemFrecuenciaDrogas = personaDao.findAll_idt("frecuencia_drogas");
        
        //codigo eddy
        itemDepartamento = personaDao.findAll_idt("departamento");
        itemMunicipios = personaDao.findAll_idt("Municipio");
        itemServicios = personaDao.findAll_idt("servicios");
        itemSubSevicios = personaDao.findAll_idt("sub_servicios");
    }
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.T_datosDao;
import dao.T_datosDaoImpl;
import dao.View_reportDao;
import dao.View_reportDaoImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.T_datos;
import model.View_violenceReport;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import util.ConnectionUDB;

/**
 *
 * @author KRETCO
 */
@ManagedBean
@SessionScoped
public class ReportsController implements Serializable {

    View_reportDao view_reportDao = new View_reportDaoImpl();
    private Date fecha_ini;
    private Date fecha_fin;    
    private PieChartModel animatedModel;
    private boolean flag_chat = false;
    private List<View_violenceReport> list_report;
    private String seleccion_reporte;
    private BigDecimal total_hombres = BigDecimal.ZERO;
    private BigDecimal total_mujer = BigDecimal.ZERO;
    private BigDecimal total_neto = BigDecimal.ZERO;
    private BigDecimal auxPercent = BigDecimal.ZERO;
    


    public ReportsController() {
        seleccion_reporte = "";
        list_report = new ArrayList<>();
        animatedModel = new PieChartModel();
    }
    
    
    public void onChangeTipoReporte() {
        
        UsuarioController obj = (UsuarioController) ConnectionUDB.getSessionBean("usuarioController");        
        String host = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("servidorU");        
        String usuario = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("usuarioU");
        String pasword = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("passwordU");
        String dbname = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("baseDatoU");        
        List<View_violenceReport> listaView = new ArrayList<>();
        List<View_violenceReport> listaView2 = new ArrayList<>();
        
        if ( fecha_ini.before(fecha_fin) ){
            if (seleccion_reporte.equals("Edad")) {
                listaView = view_reportDao.reporte_edad_victima(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, host, usuario, pasword, dbname);
                
            }
            if (seleccion_reporte.equals("Estado Civil")) {
                listaView = view_reportDao.reporte_persona_victima(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, host, usuario, pasword, dbname,"estado_civil","est_civil");
            }
            
            if (seleccion_reporte.equals("Nivel Instruccion")) {
                listaView = view_reportDao.reporte_persona_victima(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, host, usuario, pasword, dbname,"nivel_instruccion","nivel_inst");
            }

            if (seleccion_reporte.equals("Parentesco")) {
                listaView = view_reportDao.reporte_vicagr_victima(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, host, usuario, pasword, dbname,"relacion_agresor_victima","parentesco");
            }
            
            if (seleccion_reporte.equals("Violencias")) {
                    listaView = view_reportDao.reporte_tviolencia_victima(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, host, usuario, pasword, dbname);
                }
            ////////////************************* Reportes SIMPLES *****************************////////
            if (seleccion_reporte.equals("Lugar de Nacimiento de las Victimas")) {
                listaView2 = view_reportDao.reporte_simple_victima_agresion(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin,"lugar_nac","lugar_nac");
            } 
            if (seleccion_reporte.equals("Ingreso Economico de las Victimas")) {
                listaView2 = view_reportDao.reporte_simple(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, "ingreso_economico","ingre_economico");                                   
            }
             
            if (seleccion_reporte.equals("Tenencia de Vivienda de las Victimas")) {
                listaView2 = view_reportDao.reporte_simple(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin,"tenencia_vivienda","vivienda");                
            }
            
            if (seleccion_reporte.equals("Estado Civil de las Victimas")) {
                listaView2 = view_reportDao.reporte_simple(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin,"estado_civil","est_civil");              
            }
            
             if (seleccion_reporte.equals("Ocupacion de las Victimas")) {
                listaView2 = view_reportDao.reporte_simple(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin,"ocupacion_principal","ocupacion");
            }
            
            if (seleccion_reporte.equals("Nivel de Instruccion de las Victimas")) {
                listaView2 = view_reportDao.reporte_simple(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin,"nivel_instruccion","nivel_inst");
            }
             
            if (seleccion_reporte.equals("Lugares de Agresion")) {
                listaView2 = view_reportDao.reporte_simple_victima_agresion(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin,"lugar_agresion","lugar_agresion");
            }
             
            if (seleccion_reporte.equals("Relacion Victima - Agresor")) {
                listaView2 = view_reportDao.reporte_simple_victima_agresion(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin,"relacion_agresor_victima","parentesco");
            }
            
            if (seleccion_reporte.equals("Frecuencia de las Agresiones")) {
                listaView2 = view_reportDao.reporte_simple_victima_agresion(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin,"frecuencia_agrecion","frec_agresiones");
            } 
            
            if (seleccion_reporte.equals("Lugar de Nacimiento de las Victimas")) {
                listaView2 = view_reportDao.reporte_simple_victima_agresion(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin,"lugar_nac","lugar_nac");
            } 
            //Booleanos
            if (seleccion_reporte.equals("Municipio Donde Viven las Victimas")) {
                listaView2 = view_reportDao.reporte_simple_booleanos(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, "municipio");                                   
            }
            
            if (seleccion_reporte.equals("Area Donde Viven las Victimas")) {
                listaView2 = view_reportDao.reporte_simple_booleanos(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, "area");                                   
            }
            
            if (seleccion_reporte.equals("Condicion de Actividad de las Victimas")) {
                listaView2 = view_reportDao.reporte_simple_booleanos(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, "condicion_act");                                   
            }
            
            if (seleccion_reporte.equals("Pertenencia a Pueblo Indigena Originario")) {
                listaView2 = view_reportDao.reporte_simple_booleanos(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, "etnia");                                   
            }
            
            if (seleccion_reporte.equals("Pertenencia a Grupo LGTB")) {
                listaView2 = view_reportDao.reporte_simple_booleanos(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, "lgbti");                                   
            }
            
            if (seleccion_reporte.equals("Victimas con Discapacidad")) {
                listaView2 = view_reportDao.reporte_simple_booleanos(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, "pers_discapacidad");                                   
            }
            ///
            if (seleccion_reporte.equals("Victimas por Sexo")) {
                listaView2 = view_reportDao.reporte_simple_strings(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, "sexo");                                   
            }
            
            if (seleccion_reporte.equals("Victimas en Gestacion")) {
                listaView2 = view_reportDao.reporte_simple_ints(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, "gestacion_h");                                   
            }
            
            if (seleccion_reporte.equals("Numero de Hijos")) {
                listaView2 = view_reportDao.reporte_simple_ints_hijos(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin, "nro_hijos");                                   
            }
            
            if (seleccion_reporte.equals("Edades de las Victimas")) {
                listaView2 = view_reportDao.reporte_simple_edad(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin);                                   
            }
            
            if (seleccion_reporte.equals("Tipo de Violencia Sufrida")) {
                listaView2 = view_reportDao.reporte_simple_violencia(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin);                                   
            }
            
            if (seleccion_reporte.equals("Idioma que Hablan")) {
                listaView2 = view_reportDao.reporte_simple_ints_idioma(obj.getUsuario().getCod_usuario(), fecha_ini, fecha_fin,"idiomas");                                   
            }
 
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: la facha final debe ser mayor que la fecha inicio", "Atencion: la facha final debe ser mayor que la fecha inicio");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        /*** generacion de pie chart boris ****/
        if(listaView2.size()>0){
            if (seleccion_reporte.equals("Tipo de Violencia Sufrida")) {
                T_datosDao tdatoDao = new T_datosDaoImpl();
                List<T_datos> listTV = tdatoDao.findAllIdt("ambito_tipo_violencia");
                listaView = descripcion_tipo_violencia(listTV, listaView);
                this.list_report = construye_tabla(listaView);
                
            }
            
            
            this.auxPercent = BigDecimal.ZERO;
            
            PieChartModel model = new PieChartModel();         
            
                for (View_violenceReport element : listaView2) {
                    String desc = element.getDescripcion();
                    BigDecimal con = element.getContador();
                    this.auxPercent = auxPercent.add(con);
                    System.out.println("controller.ReportsController.onChangeTipoReporte(): Descrip "+desc+" contador "+con+" auxPerce "+auxPercent);
                    model.set(desc, con);
                }            
            animatedModel = model;
            animatedModel.setTitle(seleccion_reporte);
            animatedModel.setLegendPosition("ne");
            model.setFill(true);
            model.setShowDataLabels(true);
            model.setExtender("ext");
            
            //model.setDiameter(300);
            this.flag_chat = true;      
            
            this.list_report = construye_tabla2(listaView2);
        }
         /*** generacion de pie chart boris ****/
        if (listaView.size()>0) {
            if (seleccion_reporte.equals("Violencias")) {
                T_datosDao tdatoDao = new T_datosDaoImpl();
                List<T_datos> listTV = tdatoDao.findAllIdt("ambito_tipo_violencia");
                listaView = descripcion_tipo_violencia(listTV, listaView);
                this.list_report = construye_tabla(listaView);
                
            }else{
                this.list_report = construye_tabla(listaView);
            }
            
            System.out.println("size::::" + list_report.size());
                PieChartModel model = new PieChartModel();
                //ChartSeries boys = new ChartSeries();
                //boys.setLabel("Hombres");

                for (View_violenceReport item : list_report) {
                    if (item.getHombre().compareTo(BigDecimal.ZERO) >= 0) {
                        BigDecimal res = item.getHombre().divide(item.getTota(), 2, RoundingMode.HALF_UP);
                        //boys.set(item.getDescripcion(), res.multiply(new BigDecimal(100)));
                    }
                }

                //ChartSeries girls = new ChartSeries();
                //girls.setLabel("Mujeres");

                for (View_violenceReport item : list_report) {
                    if (item.getMujer().compareTo(BigDecimal.ZERO) >= 0) {
                        BigDecimal res = item.getMujer().divide(item.getTota(), 2, RoundingMode.HALF_UP);
                        //girls.set(item.getDescripcion(), res.multiply(new BigDecimal(100)));
                    }
                }
                model.set("Hombres",total_hombres);
                model.set("Mujers",total_mujer);
                animatedModel = model;
                animatedModel.setTitle(seleccion_reporte);
                //animatedModel.setAnimate(true);
                animatedModel.setLegendPosition("ne");
                //Axis yAxis = animatedModel.getAxis(AxisType.Y);
                //yAxis.setMin(0);
                //yAxis.setMax(120);
                this.flag_chat = true;
        }
    }

    
    /*metod para eliminar elementos duplicados en la lista*/
    public List<View_violenceReport> sin_repetidos(List<View_violenceReport> lista) {
        List<View_violenceReport> resp = new ArrayList<>();
        
        ArrayList<String> array = new ArrayList<>();
        for (View_violenceReport i : lista) {
            array.add(i.getDescripcion());
        }
        
        HashSet hs = new HashSet();
        hs.addAll(array);
        array.clear();
        array.addAll(hs);
        
        for (int i = array.size() -1 ; i >= 0; i--) {
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(array.get(i));
            resp.add(reg);
        }        
        return resp;        
    }
    
    
    public List<View_violenceReport> construye_tabla(List<View_violenceReport> list){
        total_mujer = BigDecimal.ZERO;
        total_hombres = BigDecimal.ZERO;
        total_neto = BigDecimal.ZERO;
        
        List<View_violenceReport> sin = sin_repetidos(list);
        
        for (View_violenceReport i : sin) {
            for (View_violenceReport j : list) {
                if (i.getDescripcion().equals(j.getDescripcion())) {
                    if (j.getSexo().equals("H"))  i.setHombre(j.getContador());
                    if (j.getSexo().equals("M"))  i.setMujer(j.getContador());
                }
            }
        }

        for(View_violenceReport j : sin){
            if (j.getHombre() == null) j.setHombre(new BigDecimal(0));
            if (j.getMujer() == null) j.setMujer(new BigDecimal(0));
	}
        
        for(View_violenceReport k : sin){
            k.setTota(k.getHombre().add(k.getMujer()));
	}
        
        for(View_violenceReport reg : sin){
            this.total_mujer = total_mujer.add(reg.getMujer());
            this.total_hombres = total_hombres.add(reg.getHombre());
            this.total_neto = total_neto.add(reg.getTota());
	}
        
//ordena la tabla        
        List<View_violenceReport> ordena_sin = new ArrayList<>();
        for (View_violenceReport q : list) {
            for (View_violenceReport r : sin) {
                if (q.getDescripcion().equals(r.getDescripcion())) {
                    boolean existe = false;
                    for (View_violenceReport s : ordena_sin) {
                        if (r.getDescripcion().equals(s.getDescripcion())) existe = true;
                    }
                    if (!existe) {
                        ordena_sin.add(r);
                    }                    
                }
            }            
        }        
        sin = ordena_sin; 
        return sin;
    }
// obtiene la descripcion de tipos de violencia    
    public List<View_violenceReport> descripcion_tipo_violencia(List<T_datos> listT_datos, List<View_violenceReport> list){
        List<View_violenceReport> resp = new ArrayList<>();
        for (View_violenceReport item : list) {            
            String[] array = item.getDescripcion().split(",");
            String a = "";
            for (int i = 0; i < array.length; i++) {
                for (T_datos dat : listT_datos) {
                    if (!array[i].equals("")) {
                        if (Integer.parseInt(array[i]) == dat.getId_tdatos()) {
                            a = a + dat.getDescripcion() + ",";
                        }
                    }
                }
            }
            a = a.substring(0, a.length() - 1);
            item.setDescripcion(a);
        }
        resp = list;
        return resp;
    }
    
    //////////*************************** edit para reporte simple boris***********************///////////////////
     public List<View_violenceReport> construye_tabla2(List<View_violenceReport> list){
        //total_mujer = BigDecimal.ZERO;
        //total_hombres = BigDecimal.ZERO;
        System.err.println("construye TABAL Percent AUXILIAR "+ auxPercent);
        total_neto = BigDecimal.ZERO;
        //BigDecimal aux = BigDecimal.ZERO;
        BigDecimal percent = BigDecimal.ZERO;
        BigDecimal con = BigDecimal.ZERO;
        List<View_violenceReport> tabla = new ArrayList<>();        
        
        for(View_violenceReport tablalist : list){
            String desc = tablalist.getDescripcion();
            con = tablalist.getContador();
            /** si es null devuelve 0 ***/
            if(con == null){ con = BigDecimal.ZERO; percent = BigDecimal.ZERO; tablalist.setPercent(percent);}            
            
            if(con.compareTo(BigDecimal.ZERO)>0){ 
                BigDecimal dividendo = con.multiply(BigDecimal.valueOf(100));
                percent = dividendo.divide(auxPercent, 2 ,BigDecimal.ROUND_HALF_UP);  
                System.out.println("Divide "+dividendo +" / "+auxPercent+" = "+percent);
                tablalist.setPercent(percent);
            }
            System.out.println("Get Percent "+ tablalist.getPercent());
            //aux = aux.add(con);
            tablalist.setDescripcion(desc);
            tablalist.setTota(con);
            
        }        
        total_neto = auxPercent;//aux;
        //System.out.println("controller.ReportsController.construye_tabla2(): TOTAL LISTA "+auxPercent);
        tabla = list;
        return tabla;
    }
    
//    public void actionEjecutaReporte() {
//        total_mujer = BigDecimal.ZERO;
//        total_hombres = BigDecimal.ZERO;
//        total_neto = BigDecimal.ZERO;
//        this.flag_chat = false;
//        if (!seleccion_reporte.equals("")) {
//            if (seleccion_reporte.equals("Edad")) {
//                list_report = view_reportDao.reportAge(fecha_ini, fecha_fin);
//            }
//            if (seleccion_reporte.equals("Estado Civil")) {
//                list_report = view_reportDao.reportBeen(fecha_ini, fecha_fin);
//            }
//            if (seleccion_reporte.equals("Departamento")) {
//                list_report = view_reportDao.reportDepartment(fecha_ini, fecha_fin);
//            }
//            if (seleccion_reporte.equals("Violencias")) {
//                list_report = reportViolence();
//            }
//
//            for (View_violenceReport reg : list_report) {
//                if (reg.getHombre() == null) {
//                    reg.setHombre(new BigDecimal(0));
//                }
//                if (reg.getMujer() == null) {
//                    reg.setMujer(new BigDecimal(0));
//                }
//                if (reg.getTota() == null) {
//                    reg.setTota(new BigDecimal(0));
//                }
//            }
//
//            for (View_violenceReport reg : list_report) {
//                this.total_mujer = total_mujer.add(reg.getMujer());
//                this.total_hombres = total_hombres.add(reg.getHombre());
//                this.total_neto = total_neto.add(reg.getTota());
//            }
//            HorizontalBarChartModel model = new HorizontalBarChartModel();
//            ChartSeries boys = new ChartSeries();
//            boys.setLabel("Hombres");
//
//            for (View_violenceReport item : list_report) {
//                if (item.getHombre().compareTo(BigDecimal.ZERO) > 0) {
//                    System.out.println("enasdl" + item.getColumna1() + "---" + item.getColumna4());
//                    BigDecimal res = item.getHombre().divide(item.getTota(), 2, RoundingMode.HALF_UP);
//                    boys.set(item.getDescripcion(), res.multiply(new BigDecimal(100)));
//                }
//            }
//
//            ChartSeries girls = new ChartSeries();
//            girls.setLabel("Mujeres");
//
//            for (View_violenceReport item : list_report) {
//                if (item.getMujer().compareTo(BigDecimal.ZERO) > 0) {
//                    System.out.println("enasdl" + item.getMujer() + "---" + item.getTota());
//                    BigDecimal res = item.getMujer().divide(item.getTota(), 2, RoundingMode.HALF_UP);
//                    girls.set(item.getDescripcion(), res.multiply(new BigDecimal(100)));
//                }
//            }
//            model.addSeries(boys);
//            model.addSeries(girls);
//            animatedModel = model;
//            animatedModel.setTitle(seleccion_reporte);
//            animatedModel.setAnimate(true);
//            animatedModel.setLegendPosition("ne");
//            Axis yAxis = animatedModel.getAxis(AxisType.Y);
//            yAxis.setMin(0);
//            yAxis.setMax(120);
//            this.flag_chat = true;
//        }
//
//    }    
//    public List<View_violenceReport> reportViolence() {
//        List<View_violenceReport> list_view_reports = view_reportDao.reportviolence(fecha_ini, fecha_fin);
//        T_datosDao tdatoDao = new T_datosDaoImpl();
//        List<T_datos> list_td = tdatoDao.findAllIdt("ambito_tipo_violencia");
//        List<View_violenceReport> result_view_list = new ArrayList<>();
//        for (View_violenceReport item : list_view_reports) {
//            View_violenceReport result_view = new View_violenceReport();
//            String[] array = item.getDescripcion().split(",");
//            String a = "";
//            for (int i = 0; i < array.length; i++) {
//                for (T_datos dat : list_td) {
//                    if (!array[i].equals("")) {
//                        if (Integer.parseInt(array[i]) == dat.getId_tdatos()) {
//                            a = a + dat.getDescripcion() + ",";
//                        }
//                    }
//                }
//            }
//            a = a.substring(0, a.length() - 1);
//            result_view.setDescripcion(a);
//            result_view.setHombre(item.getHombre());
//            result_view.setMujer(item.getMujer());
//            result_view.setTota(item.getTota());
//            result_view_list.add(result_view);
//        }
//
//        return result_view_list;
//    }    
//    public String per_var_Descripcion(String descrip1, String descrip2) {
//        String[] array = descrip1.split(",");
//        T_datosDao tdatoDao = new T_datosDaoImpl();
//        List<T_datos> list_td = tdatoDao.findAllIdt(descrip2);
//        //String[] array = item.getDescripcion().split(",");
//        String a = "";
//        for (int i = 0; i < array.length; i++) {
//            for (T_datos dat : list_td) {
//                if (!array[i].equals("")) {
//                    if (Integer.parseInt(array[i]) == dat.getId_tdatos()) {
//                        a = a + dat.getDescripcion() + ",";
//                    }
//                }
//            }
//        }
//        a = a.substring(0, a.length() - 1);
//
//        return a;
//    }
//    public String per_var_Descrip(int descrip1, String descrip2) {
//        //String[] array = descrip1.split(",");
//        T_datosDao tdatoDao = new T_datosDaoImpl();
//        List<T_datos> list_td = tdatoDao.findAllIdt(descrip2);
//        int sw = 0;
//        //String[] array = item.getDescripcion().split(",");
//        String a = "";
//        for (T_datos dat : list_td) {
//            if (descrip1 == dat.getId_tdatos()) {
//                a = a + dat.getDescripcion() + ",";
//                sw = 1;
//            }
//        }
//        if (sw == 1) {
//            a = a.substring(0, a.length() - 1);
//        }
//        return a;
//    }
//    public List<View_violenceReport> reportViolenceBeen() {
//
//        List<View_violenceReport> list_view_reports = view_reportDao.reportViolenceBeen(fecha_ini, fecha_fin);
//        List<View_violenceReport> list_wiew1 = new ArrayList<>();
//        //list_wiew1 = list_view_reports;
//        View_violenceReport view_report1 = new View_violenceReport();
//        int w = 0, h = 0, m = 0, t = 0, sw = 0;
//        T_datosDao tdatoDao = new T_datosDaoImpl();
//        Victima_agresorDao victima_agresorDao = new Victima_agresorDaoImpl();
//        List<Victima_agresor> list_victimaAgresor = victima_agresorDao.findAll();
//        List<View_violenceReport> list_result_view = new ArrayList<>();
//        for (View_violenceReport item : list_view_reports) {
//            View_violenceReport view_report = new View_violenceReport();
//            String a = "", b = "";
//            w++;
//            a = per_var_Descripcion(item.getColumna1(), "ambito_tipo_violencia");
//            b = per_var_Descrip(Integer.parseInt(item.getColumna2()), "estado_civil");
//            view_report.setColumna1(a);
//            view_report.setColumna2(b);
//            view_report.setColumna3(item.getColumna3());
//            view_report.setColumna4(item.getColumna4());
//            view_report.setColumna5(item.getColumna5());
//
//            list_wiew1.add(view_report);
//            h = h + Integer.parseInt(view_report.getColumna3());
//            m = m + Integer.parseInt(view_report.getColumna4());
//            t = t + Integer.parseInt(view_report.getColumna5());
//
//            sw = 1;
//        }
//        if (sw == 1) {
//
//            view_report1.setColumna2("Total");
//            view_report1.setColumna3(Integer.toString(h));
//            view_report1.setColumna4(Integer.toString(m));
//            view_report1.setColumna5(Integer.toString(t));
//
//            list_wiew1.add(view_report1);
//        }
//        return list_wiew1;
//    }
//    public List<View_violenceReport> reportViolenceDpto() {
//
//        List<View_violenceReport> list_view_reports = view_reportDao.reportViolenceDpto(fecha_ini, fecha_fin);
//        List<View_violenceReport> list_wiew1 = new ArrayList<>();
//        //list_wiew1 = list_view_reports;
//        View_violenceReport view_report1 = new View_violenceReport();
//        int w = 0, h = 0, m = 0, t = 0, sw = 0;
//        T_datosDao tdatoDao = new T_datosDaoImpl();
//        Victima_agresorDao victima_agresorDao = new Victima_agresorDaoImpl();
//        List<Victima_agresor> list_victimaAgresor = victima_agresorDao.findAll();
//        List<View_violenceReport> list_result_view = new ArrayList<>();
//        for (View_violenceReport item : list_view_reports) {
//            View_violenceReport view_report = new View_violenceReport();
//            String a = "", b = "";
//            w++;
//            a = per_var_Descripcion(item.getColumna1(), "ambito_tipo_violencia");
//            b = per_var_Descrip(Integer.parseInt(item.getColumna2()), "departamento");
//            view_report.setColumna1(a);
//            view_report.setColumna2(b);
//            view_report.setColumna3(item.getColumna3());
//            view_report.setColumna4(item.getColumna4());
//            view_report.setColumna5(item.getColumna5());
//
//            list_wiew1.add(view_report);
//
//            h = h + Integer.parseInt(view_report.getColumna3());
//            m = m + Integer.parseInt(view_report.getColumna4());
//            t = t + Integer.parseInt(view_report.getColumna5());
//
//            sw = 1;
//        }
//        if (sw == 1) {
//
//            view_report1.setColumna1("Total");
//            view_report1.setColumna3(Integer.toString(h));
//            view_report1.setColumna4(Integer.toString(m));
//            view_report1.setColumna5(Integer.toString(t));
//
//            list_wiew1.add(view_report1);
//        }
//        return list_wiew1;
//    }
//    public List<View_violenceReport> reportViolenceDptoMunAge() {
//
//        List<View_violenceReport> list_view_reports = view_reportDao.reportViolenceDepartmentMunicipalityAge(fecha_ini, fecha_fin);
//        List<View_violenceReport> list_wiew1 = new ArrayList<>();
//        //list_wiew1 = list_view_reports;
//        View_violenceReport view_report1 = new View_violenceReport();
//        int w = 0, h = 0, m = 0, t = 0, sw = 0;
//        T_datosDao tdatoDao = new T_datosDaoImpl();
//        Victima_agresorDao victima_agresorDao = new Victima_agresorDaoImpl();
//        List<Victima_agresor> list_victimaAgresor = victima_agresorDao.findAll();
//        List<View_violenceReport> list_result_view = new ArrayList<>();
//        for (View_violenceReport item : list_view_reports) {
//            View_violenceReport view_report = new View_violenceReport();
//            String a = "", b = "";
//            w++;
//            a = per_var_Descripcion(item.getColumna1(), "ambito_tipo_violencia");
//            b = per_var_Descrip(Integer.parseInt(item.getColumna2()), "departamento");
//            view_report.setColumna1(a);
//            view_report.setColumna2(b);
//            view_report.setColumna3(item.getColumna3());
//            view_report.setColumna4(item.getColumna4());
//            view_report.setColumna5(item.getColumna5());
//            view_report.setColumna6(item.getColumna6());
//            view_report.setColumna7(item.getColumna7());
//            view_report.setColumna8(item.getColumna8());
//            view_report.setColumna9(item.getColumna9());
//
//            list_wiew1.add(view_report);
//
//            h = h + Integer.parseInt(view_report.getColumna4());
//            m = m + Integer.parseInt(view_report.getColumna5());
//            t = t + Integer.parseInt(view_report.getColumna6());
//            sw = 1;
//        }
//        if (sw == 1) {
//
//            view_report1.setColumna1("Total");
//            view_report1.setColumna4(Integer.toString(h));
//            view_report1.setColumna5(Integer.toString(m));
//            view_report1.setColumna6(Integer.toString(t));
//
//            list_wiew1.add(view_report1);
//        }
//        return list_wiew1;
//    }
    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getSeleccion_reporte() {
        return seleccion_reporte;
    }

    public void setSeleccion_reporte(String seleccion_reporte) {
        this.seleccion_reporte = seleccion_reporte;
    }

    public View_reportDao getView_reportDao() {
        return view_reportDao;
    }

    public void setView_reportDao(View_reportDao view_reportDao) {
        this.view_reportDao = view_reportDao;
    }

    public List<View_violenceReport> getList_report() {
        return list_report;
    }

    public void setList_report(List<View_violenceReport> list_report) {
        this.list_report = list_report;
    }

    public PieChartModel getAnimatedModel() {
        return animatedModel;
    }

    public void setAnimatedModel(PieChartModel animatedModel) {
        this.animatedModel = animatedModel;
    }

    public BigDecimal getTotal_mujer() {
        return total_mujer;
    }

    public void setTotal_mujer(BigDecimal total_mujer) {
        this.total_mujer = total_mujer;
    }

    public BigDecimal getTotal_hombres() {
        return total_hombres;
    }

    public void setTotal_hombres(BigDecimal total_hombres) {
        this.total_hombres = total_hombres;
    }

    public BigDecimal getTotal_neto() {
        return total_neto;
    }

    public void setTotal_neto(BigDecimal total_neto) {
        this.total_neto = total_neto;
    }

    public boolean isFlag_chat() {
        return flag_chat;
    }

    public void setFlag_chat(boolean flag_chat) {
        this.flag_chat = flag_chat;
    }

}

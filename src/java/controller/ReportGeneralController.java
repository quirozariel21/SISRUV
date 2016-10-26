/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ReportGeneralDao;
import dao.ReportGeneralDaoImpl;
import dao.T_datosDao;
import dao.T_datosDaoImpl;
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
import model.ReportGeneral;
import model.T_datos;
import model.View_violenceReport;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author KRETCO
 */
@ManagedBean
@SessionScoped
public class ReportGeneralController implements Serializable {

    ReportGeneralDao reportGeneralDao = new ReportGeneralDaoImpl();
    private Date fecha_ini;
    private Date fecha_fin;
    private HorizontalBarChartModel animatedModel;
    private boolean flag_chat = false;
    //variables para el filtro
    private List<ReportGeneral> listDepartamento;
    private List<ReportGeneral> listMunicipio;
    private List<ReportGeneral> listServicio;
    private List<ReportGeneral> listSubservicio;

    private boolean renderMunicipio;
    private boolean renderServicio;
    private boolean renderSubServicio;
    private boolean renderedFiltro;

    private List<View_violenceReport> list_report;
    private String seleccion_reporte;
    private BigDecimal total_hombres = BigDecimal.ZERO;
    private BigDecimal total_mujer = BigDecimal.ZERO;
    private BigDecimal total_neto = BigDecimal.ZERO;

    public ReportGeneralController() {
        seleccion_reporte = "";
        list_report = new ArrayList<>();
        animatedModel = new HorizontalBarChartModel();
        //listas
        limpiarTodo();

    }

    public void eventSelectAllDepartamento() {

        for (ReportGeneral item : listDepartamento) {
            item.setCheck(true);
        }
        listMunicipio = new ArrayList<>();
        renderMunicipio = false;
        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listDepartamento) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }

        if (nro_selec > 0) {
            renderMunicipio = true;
            renderedFiltro = true;
            listMunicipio = reportGeneralDao.findMunicipio(codigos);
        }
    }

    public void eventDeselectAllDepartamento() {

        for (ReportGeneral item : listDepartamento) {
            item.setCheck(false);
        }

        listMunicipio = new ArrayList<>();
        renderMunicipio = false;
        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listDepartamento) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }

        if (nro_selec > 0) {
            renderMunicipio = true;
            renderedFiltro = true;
            listMunicipio = reportGeneralDao.findMunicipio(codigos);
        }
    }

    public void eventSelectDepartamento() {
        System.out.println("entra a uno");
        listMunicipio = new ArrayList<>();
        renderMunicipio = false;
        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listDepartamento) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }
        if (nro_selec > 0) {
            renderMunicipio = true;
            renderedFiltro = true;
            listMunicipio = reportGeneralDao.findMunicipio(codigos);
        }

    }

    public void eventSelectAllMunicipio() {
        for (ReportGeneral item : listMunicipio) {
            item.setCheck(true);
        }
        listServicio = new ArrayList<>();
        renderServicio = false;
        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listMunicipio) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }
        if (nro_selec > 0) {
            renderServicio = true;
            renderedFiltro = false;
            listServicio = reportGeneralDao.findServicio(codigos);
        }
    }

    public void eventDeselectAllMunicipio() {
        for (ReportGeneral item : listMunicipio) {
            item.setCheck(false);
        }
        listServicio = new ArrayList<>();
        renderServicio = false;
        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listMunicipio) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }
        if (nro_selec > 0) {
            renderedFiltro = true;
            renderServicio = true;
            listServicio = reportGeneralDao.findServicio(codigos);
        }
    }

    public void eventSelectMunicipio() {
        listServicio = new ArrayList<>();
        renderServicio = false;
        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listMunicipio) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }
        if (nro_selec > 0) {
            renderServicio = true;
            renderedFiltro = true;
            listServicio = reportGeneralDao.findServicio(codigos);
        }
    }

    public void eventSelectAllServicios() {
        for (ReportGeneral item : listServicio) {
            item.setCheck(true);
        }
        listSubservicio = new ArrayList<>();
        renderSubServicio = false;
        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listServicio) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }
        if (nro_selec > 0) {
            renderSubServicio = true;
            renderedFiltro = true;
            listSubservicio = reportGeneralDao.findSubServicio(codigos);
        }
    }

    public void eventDeselectAllServicios() {
        for (ReportGeneral item : listServicio) {
            item.setCheck(false);
        }
        listSubservicio = new ArrayList<>();
        renderSubServicio = false;
        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listServicio) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }
        if (nro_selec > 0) {
            renderSubServicio = true;
            renderedFiltro = true;
            listSubservicio = reportGeneralDao.findSubServicio(codigos);
        }
    }

    public void eventSelectServicios() {
        listSubservicio = new ArrayList<>();
        renderSubServicio = false;
        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listServicio) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }
        if (nro_selec > 0) {
            renderSubServicio = true;
            renderedFiltro = true;
            listSubservicio = reportGeneralDao.findSubServicio(codigos);
        }

    }

    public void eventSelectAllSubServicios() {
        for (ReportGeneral item : listSubservicio) {
            item.setCheck(true);
        }

        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listSubservicio) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }
        if (nro_selec > 0) {
            renderedFiltro = true;
        }
    }

    public void eventDeselectAllSubServicios() {
        for (ReportGeneral item : listSubservicio) {
            item.setCheck(false);
        }
        renderedFiltro = false;
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listSubservicio) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }
        if (nro_selec > 0) {
            renderedFiltro = true;
        }

    }
    
    public void eventSelectSubServicios(){
        
        Integer nro_selec = 0;
        String codigos = "0";
        for (ReportGeneral item : listSubservicio) {
            if (item.isCheck()) {
                if (nro_selec == 0) {
                    codigos = item.getId().toString();
                } else {
                    codigos = codigos + ", " + item.getId().toString();
                }
                nro_selec++;
            }
        }

    }

    public void onChangeTipoReporte() {
        
        if (!seleccion_reporte.equals("")) {
            List<View_violenceReport> listaView = new ArrayList<>();
        // CADENA PARA QUERY DE DEPARTAMENTO
        String cadena_departamento = " ";
        for (ReportGeneral item : listDepartamento) {
            if (item.isCheck()) {
                if (cadena_departamento.equals(" ")) {
                    cadena_departamento = " departamento in (" + item.getId();
                } else {
                    cadena_departamento = cadena_departamento + ", " + item.getId();
                }
            }
        }
        if (!cadena_departamento.equals(" ")) {
            cadena_departamento = cadena_departamento + " )";
        }
        String cadena_municipio = " ";
        for (ReportGeneral item : listMunicipio) {
            if (item.isCheck()) {
                if (cadena_municipio.equals(" ")) {
                    cadena_municipio = " and municipio in (" + item.getId();
                } else {
                    cadena_municipio = cadena_municipio + ", " + item.getId();
                }
            }

        }
        if (!cadena_municipio.equals(" ")) {
            cadena_municipio = cadena_municipio + " )";
        }
        String cadena_servicio = " ";
        for (ReportGeneral item : listServicio) {
            if (item.isCheck()) {
                if (cadena_servicio.equals(" ")) {
                    cadena_servicio = " and cod_servicio in (" + item.getId();
                } else {
                    cadena_servicio = cadena_servicio + ", " + item.getId();
                }
            }

        }
        if (!cadena_servicio.equals(" ")) {
            cadena_servicio = cadena_servicio + " )";
        }

        String cadena_subservicio = " ";
        for (ReportGeneral item : listSubservicio) {
            if (item.isCheck()) {
                if (cadena_subservicio.equals(" ")) {
                    cadena_subservicio = " and cod_servicio in (" + item.getId();
                } else {
                    cadena_subservicio = cadena_subservicio + ", " + item.getId();
                }
            }

        }
        if (!cadena_subservicio.equals(" ")) {
            cadena_subservicio = cadena_subservicio + " )";
        }

        if (fecha_ini.before(fecha_fin)) {
            String host = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("servidorU");
            String usuario = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("usuarioU");
            String pasword = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("passwordU");
            String dbname = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("baseDatoU");

            if (seleccion_reporte.equals("Edad")) {
                listaView = reportGeneralDao.reporte_edad_general_victima(fecha_ini, fecha_fin, host, usuario, pasword, dbname,
                        cadena_departamento, cadena_municipio, cadena_servicio, cadena_subservicio);
            }
            if (seleccion_reporte.equals("Estado Civil")) {
                listaView = reportGeneralDao.reporte_persona_general_victima(fecha_ini, fecha_fin, host, usuario, pasword, dbname,"estado_civil","est_civil",
                        cadena_departamento, cadena_municipio, cadena_servicio, cadena_subservicio);
            }
            if (seleccion_reporte.equals("Nivel Instruccion")) {
                listaView = reportGeneralDao.reporte_persona_general_victima(fecha_ini, fecha_fin, host, usuario, pasword, dbname,"nivel_instruccion","nivel_inst",
                        cadena_departamento, cadena_municipio, cadena_servicio, cadena_subservicio);
            }
            if (seleccion_reporte.equals("Parentesco")) {
                listaView = reportGeneralDao.reporte_vicagr_general_victima(fecha_ini, fecha_fin, host, usuario, pasword, dbname,"relacion_agresor_victima","parentesco",
                        cadena_departamento, cadena_municipio, cadena_servicio, cadena_subservicio);
            }
            if (seleccion_reporte.equals("Violencias")) {
                    listaView = reportGeneralDao.reporte_tviolencia_general_victima(fecha_ini, fecha_fin, host, usuario, pasword, dbname,
                        cadena_departamento, cadena_municipio, cadena_servicio, cadena_subservicio);
                }
            
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: la facha final debe ser mayor que la fecha inicio", "Atencion: la facha final debe ser mayor que la fecha inicio");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (listaView.size() > 0) {
            if (seleccion_reporte.equals("Violencias")) {
                T_datosDao tdatoDao = new T_datosDaoImpl();
                List<T_datos> listTV = tdatoDao.findAllIdt("ambito_tipo_violencia");
                listaView = descripcion_tipo_violencia(listTV, listaView);
                this.list_report = construye_tabla(listaView);

            } else {
                this.list_report = construye_tabla(listaView);
            }

            System.out.println("size::::" + list_report.size());
            HorizontalBarChartModel model = new HorizontalBarChartModel();
            ChartSeries boys = new ChartSeries();
            boys.setLabel("Hombres");

            for (View_violenceReport item : list_report) {
                if (item.getHombre().compareTo(BigDecimal.ZERO) >= 0) {
                    BigDecimal res = item.getHombre().divide(item.getTota(), 2, RoundingMode.HALF_UP);
                    boys.set(item.getDescripcion(), res.multiply(new BigDecimal(100)));
                }
            }

            ChartSeries girls = new ChartSeries();
            girls.setLabel("Mujeres");

            for (View_violenceReport item : list_report) {
                if (item.getMujer().compareTo(BigDecimal.ZERO) >= 0) {
                    BigDecimal res = item.getMujer().divide(item.getTota(), 2, RoundingMode.HALF_UP);
                    girls.set(item.getDescripcion(), res.multiply(new BigDecimal(100)));
                }
            }
            model.addSeries(boys);
            model.addSeries(girls);
            animatedModel = model;
            animatedModel.setTitle(seleccion_reporte);
            animatedModel.setAnimate(true);
            animatedModel.setLegendPosition("ne");
            Axis yAxis = animatedModel.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setMax(120);
            this.flag_chat = true;
        }
        }else{
            this.list_report = new ArrayList<>();
            this.flag_chat = false;
            this.animatedModel = new HorizontalBarChartModel();
        }
   
    }
    
    public void limpiarTodo(){
        listDepartamento = new ArrayList<>();
        listDepartamento = reportGeneralDao.findDepartamento();
        listMunicipio = new ArrayList<>();
        listServicio = new ArrayList<>();
        listSubservicio = new ArrayList<>();
        
        this.renderMunicipio = false;
        this.renderServicio = false;
        this.renderSubServicio = false;
        this.renderedFiltro = false;
        
        this.list_report = new ArrayList<>();
        this.flag_chat = false;
        this.animatedModel = new HorizontalBarChartModel();
        this.fecha_ini = null;
        this.fecha_fin = null;
        this.seleccion_reporte = "";
        
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

        for (int i = array.size() - 1; i >= 0; i--) {
            View_violenceReport reg = new View_violenceReport();
            reg.setDescripcion(array.get(i));
            resp.add(reg);
        }
        return resp;
    }

    public List<View_violenceReport> construye_tabla(List<View_violenceReport> list) {
        total_mujer = BigDecimal.ZERO;
        total_hombres = BigDecimal.ZERO;
        total_neto = BigDecimal.ZERO;

        List<View_violenceReport> sin = sin_repetidos(list);

        for (View_violenceReport i : sin) {
            for (View_violenceReport j : list) {
                if (i.getDescripcion().equals(j.getDescripcion())) {
                    if (j.getSexo().equals("H")) {
                        i.setHombre(j.getContador());
                    }
                    if (j.getSexo().equals("M")) {
                        i.setMujer(j.getContador());
                    }
                }
            }
        }

        for (View_violenceReport j : sin) {
            if (j.getHombre() == null) {
                j.setHombre(new BigDecimal(0));
            }
            if (j.getMujer() == null) {
                j.setMujer(new BigDecimal(0));
            }
        }

        for (View_violenceReport k : sin) {
            k.setTota(k.getHombre().add(k.getMujer()));
        }

        for (View_violenceReport reg : sin) {
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
                        if (r.getDescripcion().equals(s.getDescripcion())) {
                            existe = true;
                        }
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

    public List<View_violenceReport> descripcion_tipo_violencia(List<T_datos> listT_datos, List<View_violenceReport> list) {
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

    public List<View_violenceReport> getList_report() {
        return list_report;
    }

    public void setList_report(List<View_violenceReport> list_report) {
        this.list_report = list_report;
    }

    public HorizontalBarChartModel getAnimatedModel() {
        return animatedModel;
    }

    public void setAnimatedModel(HorizontalBarChartModel animatedModel) {
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

    public boolean isRenderServicio() {
        return renderServicio;
    }

    public void setRenderServicio(boolean renderServicio) {
        this.renderServicio = renderServicio;
    }

    public boolean isRenderSubServicio() {
        return renderSubServicio;
    }

    public void setRenderSubServicio(boolean renderSubServicio) {
        this.renderSubServicio = renderSubServicio;
    }

    public boolean isRenderedFiltro() {
        return renderedFiltro;
    }

    public void setRenderedFiltro(boolean renderedFiltro) {
        this.renderedFiltro = renderedFiltro;
    }

    public boolean isRenderMunicipio() {
        return renderMunicipio;
    }

    public void setRenderMunicipio(boolean renderMunicipio) {
        this.renderMunicipio = renderMunicipio;
    }

    public List<ReportGeneral> getListDepartamento() {
        return listDepartamento;
    }

    public void setListDepartamento(List<ReportGeneral> listDepartamento) {
        this.listDepartamento = listDepartamento;
    }

    public List<ReportGeneral> getListMunicipio() {
        return listMunicipio;
    }

    public void setListMunicipio(List<ReportGeneral> listMunicipio) {
        this.listMunicipio = listMunicipio;
    }

    public List<ReportGeneral> getListServicio() {
        return listServicio;
    }

    public void setListServicio(List<ReportGeneral> listServicio) {
        this.listServicio = listServicio;
    }

    public List<ReportGeneral> getListSubservicio() {
        return listSubservicio;
    }

    public void setListSubservicio(List<ReportGeneral> listSubservicio) {
        this.listSubservicio = listSubservicio;
    }

}

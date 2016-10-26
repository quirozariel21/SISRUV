/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorescontroller;

import controller.UsuarioController;
import indicadoresdao.IDenunciaDao;
import indicadoresdao.IDenunciaDaoImpl;
import indicadoresdao.IIndicadoresDao;
import indicadoresdao.IIndicadoresDaoImpl;
import indicadoresdao.IInstitucionDao;
import indicadoresdao.IInstitucionDaoImpl;
import indicadoresdao.IPreguntaDao;
import indicadoresdao.IPreguntaDaoImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import indicadoresmodel.IDenuncia;
import indicadoresmodel.IIndicadores;
import indicadoresmodel.IInstitucion;
import indicadoresmodel.IPregunta;
import indicadoresmodel.IT_datos;
import org.primefaces.event.CloseEvent;
import util.ConnectionDB;

/**
 *
 * @author KRETCO
 */
@ManagedBean
@ViewScoped
public class IDenunciaControllerBkp {

    private IDenuncia denuncia;
    private IInstitucion institucion;
    private List<IDenuncia> listadenuncias;
    private List<IInstitucion> listaInstituciones;

    private List<IT_datos> listaResultTipoViolencia;
    private List<IT_datos> listaSelectTipoViolencia;
    private List<IT_datos> listaTipoViolencia;
    //indicadores
    private List<IInstitucion> listainstituciones;
    private List<IPregunta> listapreguntas;
    private boolean flagDialogVictimaAgresor = false;
    private String inst;
    private Date fecha;

    public IDenunciaControllerBkp() {
        denuncia = new IDenuncia();
        institucion = new IInstitucion();
        listadenuncias = new ArrayList<>();
        listaInstituciones = new ArrayList<>();
        listainstituciones = new ArrayList<>();
        //listapreguntas = new ArrayList<>();
        inst = "";
    }

    public void insert_den() {

        IDenunciaDao denunciaDao = new IDenunciaDaoImpl();
        if (denunciaDao.insert(1, denuncia) == 0) {
            System.out.println("Error, no se inserto correctamente");
        } else {
            System.out.println("Exito, se inserto correctamente");
        }
        denuncia = new IDenuncia();
    }

    public void update_den() {

        IDenunciaDao denunciaDao = new IDenunciaDaoImpl();
        if (denunciaDao.insert(2, denuncia) == 0) {
            System.out.println("Error, no se inserto correctamente");
        } else {
            System.out.println("Exito, se inserto correctamente");
        }
        denuncia = new IDenuncia();
    }

    public void insertinst() {

        IInstitucionDao institucionDao = new IInstitucionDaoImpl();
        if (institucionDao.insert(1, institucion) == 0) {
            System.out.println("Error, no se inserto correctamente");
        } else {
            System.out.println("Exito, se inserto correctamente");
        }
        denuncia = new IDenuncia();
    }

    public void updateinst() {

        IInstitucionDao institucionDao = new IInstitucionDaoImpl();
        if (institucionDao.insert(2, institucion) == 0) {
            System.out.println("Error, no se inserto correctamente");
        } else {
            System.out.println("Exito, se inserto correctamente");
        }
        denuncia = new IDenuncia();
    }

    public void insert() {
        IIndicadoresDao indicadoresDao = new IIndicadoresDaoImpl();
        int resp = 0;
        for (IPregunta pregunta : listapreguntas) {
            System.out.println("ID PREGUNTA : " + pregunta.getId_pregunta());
            IIndicadores indicadores = new IIndicadores();
            indicadores.setPregunta_id_pregunta(pregunta.getId_pregunta());
            indicadores.setResultado(pregunta.getDato());
            indicadores.setInstitucion_id_institucion(pregunta.getInstitucion_id_institucion());
            indicadores.setFecha(fecha);
            if (indicadoresDao.insert(1, indicadores) == 1) {
                resp = 1;
            } else {
                resp = 2;
                break;
            }
        }
        this.flagDialogVictimaAgresor = false;
        if (resp == 1) {
            System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPP : SE GUARDO");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Guardo Correctamente!", "Correctamente"));
        }
        // listapreguntas = new ArrayList<>();
    }

    public void update() {

        IInstitucionDao institucionDao = new IInstitucionDaoImpl();
        if (institucionDao.insert(2, institucion) == 0) {
            System.out.println("Error, no se inserto correctamente");
        } else {
            System.out.println("Exito, se inserto correctamente");
        }
        institucion = new IInstitucion();
    }

    public IDenuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(IDenuncia denuncia) {
        this.denuncia = denuncia;
    }

    public IInstitucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(IInstitucion institucion) {
        this.institucion = institucion;
    }

    public List<IDenuncia> getListadenuncias() {
        IDenunciaDao denunciaDao = new IDenunciaDaoImpl();
        listadenuncias = denunciaDao.findAll();
        return listadenuncias;
    }

    public void setListadenuncias(List<IDenuncia> listadenuncias) {
        this.listadenuncias = listadenuncias;
    }

    public List<IInstitucion> getListaInstituciones() {
        IInstitucionDao institucionDao = new IInstitucionDaoImpl();
        listaInstituciones = institucionDao.findAll();
        return listaInstituciones;
    }

    public void setListaInstituciones(List<IInstitucion> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }

    public List<IT_datos> getListaResultTipoViolencia() {
        return listaResultTipoViolencia;
    }

    public void setListaResultTipoViolencia(List<IT_datos> listaResultTipoViolencia) {
        this.listaResultTipoViolencia = listaResultTipoViolencia;
    }

    public List<IT_datos> getListaSelectTipoViolencia() {
        return listaSelectTipoViolencia;
    }

    public void setListaSelectTipoViolencia(List<IT_datos> listaSelectTipoViolencia) {
        this.listaSelectTipoViolencia = listaSelectTipoViolencia;
    }

    public List<IT_datos> getListaTipoViolencia() {
        return listaTipoViolencia;
    }

    public void setListaTipoViolencia(List<IT_datos> listaTipoViolencia) {
        this.listaTipoViolencia = listaTipoViolencia;
    }

    public List<IInstitucion> getListainstituciones() {
        return listainstituciones;
    }

    public void setListainstituciones(List<IInstitucion> listainstituciones) {
        this.listainstituciones = listainstituciones;
    }

    public List<IPregunta> getListapreguntas() {
        IPreguntaDao preguntaDao = new IPreguntaDaoImpl();
        UsuarioController obj = (UsuarioController) ConnectionDB.getSessionBean("usuarioController");
        listapreguntas = preguntaDao.findAllIdt(obj.getUsuario().getCod_usuario());
        //       listapreguntas = preguntaDao.findAllIdt(2);
        return listapreguntas;
    }

    public void setListapreguntas(List<IPregunta> listapreguntas) {
        this.listapreguntas = listapreguntas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void handleCloseDialog(CloseEvent event) {
        botonDialogVictimaAgresorCancelar();
    }

    public void botonDialogVictimaAgresorCancelar() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "close", "close detail");
        FacesContext.getCurrentInstance().addMessage(null, message);
        cancela_action();

    }

    public void add_victima_relacion() {
        List<IPregunta> list = new ArrayList<>();
        System.out.println("UUUUUUUUUUUUUUUUUU : " + listapreguntas.size());
        System.out.println("WWWWWWWWWWWWWWWWWW : " + BigDecimal.ZERO);
        int sw = 0;
        list = listapreguntas;
        for (IPregunta den : list) {
            System.out.println("preguntas________DATO : " + den.getDato());
            String a = null;
            if (den.getDato() != null) {
                sw++;
                System.out.println("preguntas________DATO : " + den.getDato());
            }
        }
        System.out.println("SWWW : " + sw);
        if (sw > 0) {
            this.flagDialogVictimaAgresor = true;
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: Necesita responder una pregunta por lo menos", "Necesita responder una pregunta por lo menos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public boolean isFlagDialogVictimaAgresor() {
        return flagDialogVictimaAgresor;
    }

    public void setFlagDialogVictimaAgresor(boolean flagDialogVictimaAgresor) {
        this.flagDialogVictimaAgresor = flagDialogVictimaAgresor;
    }

    public void cancela_action() {
        this.flagDialogVictimaAgresor = false;

    }

    public String getInst() {
        IIndicadoresDao indicadoresDao = new IIndicadoresDaoImpl();
        inst = indicadoresDao.dev_institucion();
        System.out.println("nombre DE LA INSTITUCION : " + inst);
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }

}

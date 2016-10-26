/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesocontroller;

import accesodao.AInstitucionDao;
import accesodao.AInstitucionDaoImpl;
import accesodao.APerfilDao;
import accesodao.APerfilDaoImpl;
import accesodao.AT_datosDao;
import accesodao.AT_datosDaoImpl;
import accesodao.AUsuarioDao;
import accesodao.AUsuarioDaoImpl;
import accesodao.AUsuario_perfilDao;
import accesodao.AUsuario_perfilDaoImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import accesomodel.AInstitucion;
import accesomodel.APerfil;
import accesomodel.AUsuario;
import org.primefaces.event.SelectEvent;

//import org.primefaces.showcase.service.DocumentService;
/**
 *
 * @author KRETCO
 */
@ManagedBean
@SessionScoped
public class AUsuariosController implements Serializable {

    AUsuarioDao usuarioDao = new AUsuarioDaoImpl();
    AUsuario_perfilDao usuario_perfilDao = new AUsuario_perfilDaoImpl();
    APerfilDao perfilDao = new APerfilDaoImpl();
    AT_datosDao t_datosDao = new AT_datosDaoImpl();
    private String selectOneRadioEstado;

    private List<AUsuario> listUsuario;
    //lista para instituciones
    private List<AUsuario> listUsuInst;

    private AUsuario selectedUsuario;
    private AInstitucion institucion;
    private AInstitucionDao institucionDao = new AInstitucionDaoImpl();

    private AUsuario usuario;
    private List<APerfil> listSelectPerfil;

    private List<SelectItem> itemDocumento;
    private List<SelectItem> itemDepartamento;

    private List<SelectItem> itemMuni;
    private List<SelectItem> itemServ;
    private List<SelectItem> itemSubServ;

    private List<SelectItem> itemUsuInst;

    private List<APerfil> itemPerfil;
    private String[] selectedPerfil;

    private List<SelectItem> itemInstituciones;
    private boolean flag_Apli = false;
    private boolean flag_Usua = false;
    private boolean flag_Estado = false;
    private String select;
    private String selectedUsername;
    private int selecttedCi;

    public void pass() {
        System.out.println("valores" + usuario.getUsername() + usuario.getPassword());
    }

    public AUsuariosController() {
        this.usuario = new AUsuario();
        System.out.println("accesocontroller.AUsuariosController.<init>(): USUARIO COD "+usuario.getCod_usuario());
        this.selectedUsuario = new AUsuario();
        listSelectPerfil = new ArrayList<>();
        itemDocumento = new ArrayList<>();
        itemDepartamento = new ArrayList<>();
        itemMuni = new ArrayList<>();
        itemServ = new ArrayList<>();
        itemSubServ = new ArrayList<>();

    }

    public void onRowSelect(SelectEvent event) {
        System.out.println("accesocontroller.AUsuariosController.onROWSELECT(): USUARIO COD "+usuario.getCod_usuario());
        this.usuario = getSelectedUsuario();
        List<APerfil> lista = usuario_perfilDao.listPerfil(usuario.getCod_usuario());
        System.out.println("listaaaaaaaaaaa" + lista.size());
        String[] array = new String[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("array:" + array[i] + "-" + lista.get(i).getCod_perfil());
            array[i] = String.valueOf(lista.get(i).getCod_perfil());
        }

        selectedPerfil = array;
        itemMuni = t_datosDao.listMunicipìos(usuario.getDepartamento());
        itemServ = t_datosDao.listServicios(usuario.getMunicipio());
        itemSubServ = t_datosDao.listSubServicios(usuario.getCod_servicio());
        System.out.println("KKKKKKKKKKKKKKKKK : " + usuarioDao.tipoUsuario(usuario.getCod_usuario()));

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
        selectedUsername = usuario.getUsername();
        selecttedCi = usuario.getCi();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/usuarios.xhtml");
            this.flag_Usua = false;
            if (usuario.getCod_usuario() > 0) {
                this.flag_Usua = true;
                this.flag_Estado = true;
            }

        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    public String buttonNuevoUsuario() {
        usuario = new AUsuario();
        selectedPerfil = null;
        this.flag_Usua = false;
        this.flag_Estado = false;
        return "nuevo_usuario";
    }

    public void handleDptoChange() {
        if (usuario.getDepartamento() > 0) {
            itemMuni = t_datosDao.listMunicipìos(usuario.getDepartamento());
        }
    }

    public void handleMunChange() {
        if (usuario.getMunicipio() > 0) {
            itemServ = t_datosDao.listServicios(usuario.getMunicipio());
        }
    }

    public void handleServChange() {
        if (usuario.getCod_servicio() > 0) {
            itemSubServ = t_datosDao.listSubServicios(usuario.getCod_servicio());
        }
    }

    public String buttonGuardaUsuario(int idAdmin) {
       
        String res = "";
        for (int i = 0; i < selectedPerfil.length; i++) {
            APerfil a = new APerfil();
            a.setCod_perfil(Integer.parseInt(selectedPerfil[i].toString()));
            listSelectPerfil.add(a);
        }
        System.out.println("buttonGuardarUsuario() - user estado : " + usuario.isEstado());
        System.out.println("buttonGuardarUsuario() - lista:" + listSelectPerfil.size());
        Integer cod_usuario = usuarioDao.max() + 1;
        usuario.setCod_usuario(cod_usuario);
        List<APerfil> falla = new ArrayList<>();

        System.out.println("buttonGuardarUsuario() - id:::" + cod_usuario);
        System.out.println("buttonGuardarUsuario() - username : " + usuario.getUsername());
        System.out.println("buttonGuardarUsuario() - password : " + usuario.getPassword() + "-,-" + usuario.getPassword2());

        if (usuarioDao.find_username(usuario.getUsername(), usuario.getCi())) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: El Nombre de Usuario o el Numero de Carnet existen!", "El Nombre de Usuario o el Numero de Carnet existen!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println("error");

        } else {
            System.out.println("PASSWORD11 : " + usuario.getPassword());

            if (usuarioDao.addUsuario(1, usuario, idAdmin)) {
                for (APerfil item : listSelectPerfil) {
                    boolean ins = usuario_perfilDao.addPerfil(item, usuario, idAdmin);
                    if (ins = false) {
                        falla.add(item);
                    }
                }
                if (falla.size() > 0) {
                    usuarioDao.delUsuario(cod_usuario);
                } else {
                    res = "ok_usuario";
                    System.out.println("buttonGuardarUsuario() - todos los datos se insertaron correctos");
                    listSelectPerfil = new ArrayList<>();
                }
            }
        }

        return res;
    }

    public String buttonModificarUsuario(int idAdmin) {
        String res = "";

        for (int i = 0; i < selectedPerfil.length; i++) {
            APerfil a = new APerfil();
            a.setCod_perfil(Integer.parseInt(selectedPerfil[i].toString()));
            listSelectPerfil.add(a);
        }
        
        System.out.println("buttonModificarUsuario() USUARIO ID:"+usuario.getCod_usuario()+ "estado" + usuario.isEstado());
        
        System.out.println("buttonModificarUsuario() - Tamaño Lista:" + listSelectPerfil.size());
            System.out.println("buttonModificarUsuario() - User name : " + selectedUsername);
            System.out.println("buttonModificarUsuario() - Ci : " + selecttedCi);
        if (usuarioDao.find_usernameUpdate(usuario.getUsername(), usuario.getCi(), selectedUsername, selecttedCi)) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: El Nombre de Usuario o el Numero de Carnet existen!", "El Nombre de Usuario o el Numero de Carnet existen!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println("error");

        } else {
            if (usuarioDao.addUsuario(2, usuario, idAdmin)) {
                System.out.println("MODIFICA111111111");
                if (usuario_perfilDao.deletePerfiles(usuario.getCod_usuario())) {
                    System.out.println("MODIFICA22222");
                    List<APerfil> falla = new ArrayList<>();
                    for (APerfil item : listSelectPerfil) {
                        System.out.println("MODIFICA333333");
                        boolean ins = usuario_perfilDao.addPerfil(item, usuario, idAdmin);
                        if (ins = false) {
                            falla.add(item);
                        }
                    }
                    if (falla.size() > 0) {
                        //usuarioDao.delUsuario(cod_usuario);
                    } else {
                        res = "ok_usuario";
                        System.out.println("buttonModificarUsuario() - todos los datos se insertaron correctos");
                        usuario = new AUsuario();
                        listSelectPerfil = new ArrayList<>();
                    }
                }
            } else {
                System.out.println("No se guardo correctamente");
            }
        }

        return res;
    }
    /****************************EDICION BORIS**********************************
     ********Boton Editar Usuarios**********************************************
     ***************************************************************************/
    
    public void onEdit() {
       
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String codigo = map.get("idUsuario");
        String idAdmin = map.get("id_admin");
    System.out.println("ID USUARIO "+codigo +" Admin Id: "+idAdmin);
   
        selectedUsuario.setCod_usuario(Integer.parseInt(codigo));
        this.usuario = getSelectedUsuario();

        List<APerfil> lista = usuario_perfilDao.listPerfil(usuario.getCod_usuario());
        System.out.println("ID USUARIO despues de lista "+usuario.getCod_usuario());
        System.out.println("listaaaaaaaaaaa de anterior" + lista.size());
        String[] array = new String[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("array:" + array[i] + "-" + lista.get(i).getCod_perfil());
            array[i] = String.valueOf(lista.get(i).getCod_perfil());
        }

        selectedPerfil = array;
        itemMuni = t_datosDao.listMunicipìos(usuario.getDepartamento());
        itemServ = t_datosDao.listServicios(usuario.getMunicipio());
        itemSubServ = t_datosDao.listSubServicios(usuario.getCod_servicio());
        System.out.println("KKKKKKKKKKKKKKKKK anterior: " + usuarioDao.tipoUsuario(usuario.getCod_usuario()));

        
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
        selectedUsername = usuario.getUsername();
        selecttedCi = usuario.getCi();
        System.out.println("NOMBRE "+usuario.getUsername()+" "+usuario.getCi());
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/usuarios.xhtml");
            this.flag_Usua = false;
            if (usuario.getCod_usuario() > 0) {
                this.flag_Usua = true;
                this.flag_Estado = true;
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
    } 
    
    
    /***************************************************************************/
    public List<AUsuario> getListUsuario() {
        listUsuario = usuarioDao.listUsuario();
        return listUsuario;
    }

    public void setListUsuario(List<AUsuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public AUsuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(AUsuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public AUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(AUsuario usuario) {
        this.usuario = usuario;
    }

    public List<APerfil> getListSelectPerfil() {
        return listSelectPerfil;
    }

    public void setListSelectPerfil(List<APerfil> listSelectPerfil) {
        this.listSelectPerfil = listSelectPerfil;
    }

    public AUsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(AUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public List<SelectItem> getItemDocumento() {
        itemDocumento = t_datosDao.findAll_idt("documento");
        return itemDocumento;
    }

    public List<SelectItem> getItemDepartamento() {
        itemDepartamento = new ArrayList<>();
        itemDepartamento = t_datosDao.findAll_idt("departamento");
        return itemDepartamento;
    }

    public List<SelectItem> getItemMuni() {
        return itemMuni;
    }

    public void setItemMuni(List<SelectItem> itemMuni) {
        this.itemMuni = itemMuni;
    }

    public List<SelectItem> getItemServ() {
        return itemServ;
    }

    public void setItemServ(List<SelectItem> itemServ) {
        this.itemServ = itemServ;
    }

    public List<SelectItem> getItemSubServ() {
        return itemSubServ;
    }

    public void setItemSubServ(List<SelectItem> itemSubServ) {
        this.itemSubServ = itemSubServ;
    }

    public List<APerfil> getItemPerfil() {
        itemPerfil = perfilDao.findAll_Perfil();
        return itemPerfil;
    }

    public String[] getSelectedPerfil() {
        return selectedPerfil;
    }

    public void setSelectedPerfil(String[] selectedPerfil) {
        this.selectedPerfil = selectedPerfil;
    }

    public AInstitucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(AInstitucion institucion) {
        this.institucion = institucion;
    }

    public List<SelectItem> getItemInstituciones() {
        itemInstituciones = institucionDao.findAll_nInst();
        return itemInstituciones;
    }

    public void setItemInstituciones(List<SelectItem> itemInstituciones) {
        this.itemInstituciones = itemInstituciones;
    }

    public boolean isFlag_Apli() {
        return flag_Apli;
    }

    public void setFlag_Apli(boolean flag_Apli) {
        this.flag_Apli = flag_Apli;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public void handleApp() {
        this.flag_Apli = false;
        if (("denuncia").equals(select) || ("indicador").equals(select)) {
            this.flag_Apli = true;
            System.out.println("aaaaaaaaaaaaaaa : " + select);
            System.out.println("ENTRAAAAAAAAAAA");
        }
    }

    public boolean isFlag_Usua() {
        return flag_Usua;
    }

    public void setFlag_Usua(boolean flag_Usua) {
        this.flag_Usua = flag_Usua;
    }

    public List<AUsuario> getListUsuInst() {
        listUsuInst = usuarioDao.listUsuInst();
        return listUsuInst;
    }

    public void setListUsuInst(List<AUsuario> listUsuInst) {
        this.listUsuInst = listUsuInst;
    }

    public List<SelectItem> getItemUsuInst() {
        itemUsuInst = usuarioDao.findAll_UsuInst();
        return itemUsuInst;
    }

    public void setItemUsuInst(List<SelectItem> itemUsuInst) {
        this.itemUsuInst = itemUsuInst;
    }

    public String getSelectOneRadioEstado() {
        if (usuario.isEstado()) {
            this.selectOneRadioEstado = "1";
        } else {
            this.selectOneRadioEstado = "0";
        }
        return selectOneRadioEstado;
    }

    public void setSelectOneRadioEstado(String selectOneRadioEstado) {
        this.selectOneRadioEstado = selectOneRadioEstado;
        if (selectOneRadioEstado.equals("1")) {
            usuario.setEstado(true);
        } else {
            usuario.setEstado(false);
        }
    }

    public boolean isFlag_Estado() {
        return flag_Estado;
    }

    public void setFlag_Estado(boolean flag_Estado) {
        this.flag_Estado = flag_Estado;
    }

    public String getSelectedUsername() {
        return selectedUsername;
    }

    public void setSelectedUsername(String selectedUsername) {
        this.selectedUsername = selectedUsername;
    }

    public int getSelecttedCi() {
        return selecttedCi;
    }

    public void setSelecttedCi(int selecttedCi) {
        this.selecttedCi = selecttedCi;
    }

}

package controller;

import dao.OperacionDao;
import dao.OperacionDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.MenuView;
import model.Operacion;
import model.Usuario;
import org.primefaces.event.CloseEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import dao.LogDao;
import dao.LogDaoImpl;
import dao.MenuGenDao;
import dao.MenuGenDaoImpl;
import model.Log;
import model.MenuGen;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable {

    UsuarioDao dao_usuario = new UsuarioDaoImpl();
    OperacionDao dao_operacion = new OperacionDaoImpl();
    LogDao logDao = new LogDaoImpl();
    /*Inicio EDIT BORIS, CREAMOS NUEVO OBJETO PARA DETERMINAR 
    LOS IDs DE LAS APLICACIONES QUE ESTARA EN  EL MENU*/
    
    MenuGenDao dao_menu = new MenuGenDaoImpl();
    /*fIN EDIT BORIS, CREAMOS NUEVO OBJETO PARA DETERMINAR 
    LOS IDs DE LAS APLICACIONES QUE ESTARA EN  EL MENU*/
    
    private Usuario usuario;
    private Date fecha_actual;
    private List<MenuView> menuView = new ArrayList<>();
    private String item_url;
    private List<MenuView> menuViewResponsive = new ArrayList<>();
    
    private String ruv = "false";
    private String denuncias = "false";
    private String indicadores = "false";
    private String administrador = "false";
    private String sea = "false";
    private String viomap = "false";
    private String rejap = "false";

    public UsuarioController() {

        usuario = new Usuario();
        menuView = new ArrayList<>();
        menuViewResponsive = new ArrayList<>();

        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                usuario.setUsername(((User) authentication.getPrincipal()).getUsername());
                usuario.setPassword(((User) authentication.getPrincipal()).getPassword());
                usuario = dao_usuario.findUsuario(usuario);
                int sw = 0;
                
                List<MenuGen> menuList = new ArrayList<>();
                menuList = dao_menu.findMenuUser(usuario.getCod_usuario());
                System.out.println("usuarioController --- codigo usuario: "+usuario.getCod_usuario());
                for(MenuGen item : menuList){
                    if(item.getAplicacionId() == 1){
                        this.ruv = "true";
                    }
                    if(item.getAplicacionId() == 2){
                        this.denuncias = "true";
                    }
                    if(item.getAplicacionId() == 3){
                        this.indicadores = "true";
                    }
                    if(item.getAplicacionId() == 4){
                        this.administrador = "true";
                    }
                    if(item.getAplicacionId() == 5){
                        this.sea = "true";
                    }
                    if(item.getAplicacionId() == 6){
                        this.viomap = "true";
                    }
                    if(item.getAplicacionId() == 7){
                        this.rejap = "true";
                    }
                    //System.out.println("Codigo Aplicacion para MENU: "+ item.getAplicacionId());
                }
                //arma el menu
                /* inicio EDIT BORIS: NO ARMAR EL MENU TODAVIA SE COMENTARA CODIGO*//*
                List<Operacion> operacion = new ArrayList<>();
                operacion = dao_operacion.findAll(usuario.getCod_usuario());

                menu_dinamic(operacion);
                
                for (Operacion item : operacion) {
                    if (item.getCod_opera_padre() == 0) {
                        System.out.println("codigo padre:" + item.getCod_opera() + "-" + operacion.size()+" ,CODIGO APP: "+item.getAplicacion_id_app());
                        List<Operacion> sub = submenu(item.getCod_opera(), operacion);
                        menuView.add(new MenuView(item.getCod_opera(), item.getCod_opera_padre(), item.getDescripcion(), item.getHref(), item.getIcon(), sub));
                        for (Operacion subitem : sub) {
                            menuViewResponsive.add(new MenuView(subitem.getCod_opera(), subitem.getCod_opera_padre(), subitem.getDescripcion(), subitem.getHref(), subitem.getIcon(), null));
                        }
                    }
                }
                
                /* fin EDIT BORIS: NO ARMAR EL MENU TODAVIA SE COMENTARA CODIGO*/    
            }
        }

    }
    /* inicio EDIT BORIS: DEVUELVE EL ESTADO DE LOS MENUS*/
    public String get_menuRuv(){
        return ruv;
    }
    
    public String get_menuDenuncias(){
        return denuncias;
    }
    
    public String get_menuIndicadores(){
        return indicadores;
    }
    
    public String get_menuAdministracion(){
        return administrador;
    }
    
    public String get_menuSea(){
        return sea;
    }
    
    public String get_menuViomap(){
        return viomap;
    }
    
    public String get_menuRejap(){
        return rejap;
    }
    /* FIN EDIT BORIS: DEVUELVE EL ESTADO DE LOS MENUS*/
    public void menu_dinamic(List<Operacion> lista) {
        menuModel = new DefaultMenuModel();

        //DefaultSubMenu submenu = new DefaultSubMenu();
        for (Operacion item : lista) {
            if (item.getCod_opera_padre() == 0) {
                //submenu = new DefaultSubMenu(item.getDescripcion());
                /*List<Operacion> subLista = submenu(item.getCod_opera(), lista);
                for (Operacion subitem : subLista) {
                    DefaultMenuItem menuItem = new DefaultMenuItem(subitem.getDescripcion());
                    menuItem.setIcon(subitem.getIcon());
                    menuItem.setCommand("#{usuarioController.actionLink}");
                    menuItem.setParam("href", subitem.getHref());
                    submenu.addElement(menuItem);
                }*/
                //menuModel.addElement(submenu);
            }

        }
    }

    private MenuModel menuModel;

    public MenuModel getMenuModel() {
        return menuModel;
    }
    /*
    public List<Operacion> submenu(Integer id, List<Operacion> operacion) {
        List<Operacion> submenu = new ArrayList<>();
        for (Operacion item : operacion) {
            if (Objects.equals(id, item.getCod_opera_padre())) {
                submenu.add(item);
            }
        }
        return submenu;
    }
    */
    public void actionLink() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String href = map.get("href");
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/" + href);
        } catch (IOException ex) {
            Logger.getLogger(DenunciaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.getExternalContext().getSessionMap().remove("denunciaController");
    }

    private boolean flagconfimacion = false;

    public void actionCerrarSession() {
        System.out.println("action cerrar");
        this.flagconfimacion = true;
    }

    public void handleCloseDialog(CloseEvent event) {
        actionDialogCancelar();
    }

    public void actionDialogCancelar() {
        System.out.println("Cancelar Dialogo");
        this.flagconfimacion = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha_actual() {
        fecha_actual = new Date();
        return fecha_actual;
    }

    public List<MenuView> getMenuView() {
        return menuView;
    }

    public void setMenuView(List<MenuView> menuView) {
        this.menuView = menuView;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public List<MenuView> getMenuViewResponsive() {
        return menuViewResponsive;
    }

    public void setMenuViewResponsive(List<MenuView> menuViewResponsive) {
        this.menuViewResponsive = menuViewResponsive;
    }

    public boolean isFlagconfimacion() {
        return flagconfimacion;
    }

    public void setFlagconfimacion(boolean flagconfimacion) {
        this.flagconfimacion = flagconfimacion;
    }

}

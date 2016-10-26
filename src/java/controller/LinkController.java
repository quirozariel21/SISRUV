/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OperacionDao;
import dao.OperacionDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.MenuView;
import model.Operacion;
import model.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author KRETCO
 */
@ManagedBean
@SessionScoped
public class LinkController implements Serializable{
    /*edit INICIOBoris VAriables*/
    OperacionDao dao_operacion = new OperacionDaoImpl();
    private Usuario usuario;
    UsuarioDao dao_usuario = new UsuarioDaoImpl();
    
    private List<MenuView> menuView = new ArrayList<>();
    private List<MenuView> menuViewResponsive = new ArrayList<>();
    /*edit FIN Boris VAriables*/
    
     /*INICIO Edit BORIS prUEBA*/
    public String menuPrincipal(){
        return "/menu.xhtml?faces-redirect=true";  
    }
    
    public String obtenerMenu(String aplicacion){
    
          
        int nroApp;        
        nroApp = Integer.parseInt(aplicacion);
        String retorno = "";
        
        usuario = new Usuario();
        menuView = new ArrayList<>();
        menuViewResponsive = new ArrayList<>();
        System.out.print("EL NUMERO DE LA APLICACION: "+nroApp+" Usuario:"+ usuario.getCod_usuario());
        
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                usuario.setUsername(((User) authentication.getPrincipal()).getUsername());
                usuario.setPassword(((User) authentication.getPrincipal()).getPassword());
                usuario = dao_usuario.findUsuario(usuario);
                int sw = 0;
        
        List<Operacion> operacion = new ArrayList<>();
                operacion = dao_operacion.findAll(usuario.getCod_usuario(),nroApp); //EL Parametro nroApp es el ID DE LA tabla APLICACION

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
            }  
        }    
            
        FacesContext contextMenu = FacesContext.getCurrentInstance();
        Map<String, String> map = contextMenu.getExternalContext().getRequestParameterMap();
        String dato = map.get("aplicacion");
        System.out.println("href" + dato);
        String link = "";

        if (dato.equals("denuncias")) {
            //link = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("denuncias");            
            System.out.println("Aplicacion "+ dato);
            retorno = "/denuncia/denuncia.xhtml?faces-redirect=true";           
        }        
        if (dato.equals("ruv")) {
            //link = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("denuncias");            
            System.out.println("Aplicacion "+ dato);
            retorno = "detalle?faces-redirect=true";           
        }
        if (dato.equals("indicadores")) {
            //link = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("denuncias");            
            System.out.println("Aplicacion "+ dato);
            retorno = "/indicadores/indicadores.xhtml?faces-redirect=true";           
        }
        if (dato.equals("acceso")) {
            //link = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("denuncias");            
            System.out.println("Aplicacion "+ dato);
            retorno = "/acceso/usuariosListado.xhtml?faces-redirect=true";           
        } 
        
        return (retorno);
    }  
    
    /*FIN Edit BORIS prUEBA*/
    
    /**edit INICIO BORIS SIDE MEN**/
    
    public void menu_dinamic(List<Operacion> lista) {
        menuModel = new DefaultMenuModel();

        DefaultSubMenu submenu = new DefaultSubMenu();
        for (Operacion item : lista) {
            if (item.getCod_opera_padre() == 0) {
                submenu = new DefaultSubMenu(item.getDescripcion());
                List<Operacion> subLista = submenu(item.getCod_opera(), lista);
                for (Operacion subitem : subLista) {
                    DefaultMenuItem menuItem = new DefaultMenuItem(subitem.getDescripcion());
                    menuItem.setIcon(subitem.getIcon());
                    menuItem.setCommand("#{usuarioController.actionLink}");
                    menuItem.setParam("href", subitem.getHref());
                    submenu.addElement(menuItem);
                }
                menuModel.addElement(submenu);
            }

        }
    }

    private MenuModel menuModel;

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public List<Operacion> submenu(Integer id, List<Operacion> operacion) {
        List<Operacion> submenu = new ArrayList<>();
        for (Operacion item : operacion) {
            if (Objects.equals(id, item.getCod_opera_padre())) {
                submenu.add(item);
            }
        }
        return submenu;
    }

    /**edit FIN BORIS SIDE MEN**/
    
    
    public void actionLink() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String dato = map.get("href");
        System.out.println("href" + dato);
        String link = "";

        if (dato.equals("ruv")) {
            link = FacesContext.getCurrentInstance().getExternalContext()
                    .getInitParameter("ruv");
        }

        if (dato.equals("indicadores")) {
            link = FacesContext.getCurrentInstance().getExternalContext()
                    .getInitParameter("indicadores");
        }

        if (dato.equals("denuncias")) {
            link = FacesContext.getCurrentInstance().getExternalContext()
                    .getInitParameter("denuncias");
        }
        
        if (dato.equals("rejap")) {
            link = FacesContext.getCurrentInstance().getExternalContext()
                    .getInitParameter("rejap");
        }

        if (dato.equals("costeo")) {
            link = FacesContext.getCurrentInstance().getExternalContext()
                    .getInitParameter("costeo");
        }

        if (dato.equals("viomap")) {
            link = FacesContext.getCurrentInstance().getExternalContext()
                    .getInitParameter("viomap");
        }

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(link);
        } catch (IOException ex) {
            Logger.getLogger(LinkController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // context.getExternalContext().getSessionMap().remove("linkcontroller");

        // context.getExternalContext().getSessionMap().remove("linkcontroller");
    }

}


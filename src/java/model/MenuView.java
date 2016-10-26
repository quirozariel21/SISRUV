/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author e_mv
 */
public class MenuView implements Serializable {

    private Integer cod_menu_opera;
    private Integer cod_menu_opera_padre;
    private String menu_descripcion;
    private String menu_href;
    private String menu_icon;
    private List<Operacion> submenu = new ArrayList<>();

    public MenuView(Integer cod_menu_opera, Integer cod_menu_opera_padre, String menu_descripcion, String menu_href, String menu_icon, List<Operacion> submenu) {
        this.cod_menu_opera = cod_menu_opera;
        this.cod_menu_opera_padre = cod_menu_opera_padre;
        this.menu_descripcion = menu_descripcion;
        this.menu_href = menu_href;
        this.menu_icon = menu_icon;
        this.submenu = submenu;
    }

    
    public Integer getCod_menu_opera() {
        return cod_menu_opera;
    }

    public void setCod_menu_opera(Integer cod_menu_opera) {
        this.cod_menu_opera = cod_menu_opera;
    }

    public Integer getCod_menu_opera_padre() {
        return cod_menu_opera_padre;
    }

    public void setCod_menu_opera_padre(Integer cod_menu_opera_padre) {
        this.cod_menu_opera_padre = cod_menu_opera_padre;
    }

    public String getMenu_descripcion() {
        return menu_descripcion;
    }

    public void setMenu_descripcion(String menu_descripcion) {
        this.menu_descripcion = menu_descripcion;
    }

    public String getMenu_href() {
        return menu_href;
    }

    public void setMenu_href(String menu_href) {
        this.menu_href = menu_href;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public List<Operacion> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<Operacion> submenu) {
        this.submenu = submenu;
    }

}

package dao;

import java.util.List;
import model.MenuGen;

/**
 * * @author servidor CREADO POR BORIS : MENU PARA GENERAR 
 * LA VISTA PRINCIPAL SEGUN EL CODIGO DE USUARIO
 */

public interface MenuGenDao {
    public List<MenuGen> findMenuUser(Integer cod_perfil);
}

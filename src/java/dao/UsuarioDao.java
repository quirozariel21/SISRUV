/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Operacion;
import model.Usuario;
import model.Usuario_Tdatos;



/**
 *
 * @author KRETCOA
 */
public interface UsuarioDao {    
    public Usuario findUsuario(Usuario usuario);
    public int datoSubServicio(Integer cod);
    public List<Operacion> listMenu();
    public Usuario_Tdatos datoUsuario(Integer cod_usuario, String gestorBD, String servidor, String baseDato, String usuario, String password);

}

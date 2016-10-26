/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.util.List;
import javax.faces.model.SelectItem;
import accesomodel.APerfil;
import accesomodel.APerfil_opera;
import accesomodel.AUsuario;
import accesomodel.AUsuario_perfil;

/**
 *
 * @author KRETCOA
 */
public interface AUsuarioDao {    
    public AUsuario login(AUsuario usuario);
    public AUsuario findUsuario(AUsuario usuario);
    public List<AUsuario> listUsuario();
    public boolean addUsuario(int num, AUsuario usuario, int idAdmin);
    public boolean delUsuario(Integer cod_usuario);
    public String tipoUsuario(Integer cod_usuario);
    
    public int devSubServicio(int cod);
    public boolean insertUsu(int num, AUsuario usuario, List<APerfil> perfil);
    public int max();
    //inserciones
    public boolean insertUsu_Per(int num, AUsuario_perfil usuario_perfil);
    public boolean insertPer(int num, APerfil perfil);
    public boolean insertPerOpe(int num, APerfil_opera perfil_opera);
    //Inserta Institucion
    public List<AUsuario> listUsuInst();
    public List<SelectItem> findAll_UsuInst();
   
    public List<SelectItem> findAll_Pefil();
    public boolean find_username(String username, int ci);
    public boolean find_usernameUpdate(String username, int ci, String selectedUsername, int selecttedCi);
    public String find_perfilXusuario(int codigo_usuario);
    public int max_log();
  
}

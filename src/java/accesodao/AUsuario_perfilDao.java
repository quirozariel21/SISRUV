/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.util.List;
import accesomodel.APerfil;
import accesomodel.AUsuario;
import accesomodel.AUsuario_perfil;

/**
 *
 * @author e_mv
 */
public interface AUsuario_perfilDao {
    public boolean addPerfil(APerfil perfil, AUsuario usuario, int idAdmin);
    public boolean deletePerfiles(int codUsuario);
    public List<APerfil> listPerfil(Integer codigo_usuario);
    public List<AUsuario_perfil> listUsuarioPerfil();
}

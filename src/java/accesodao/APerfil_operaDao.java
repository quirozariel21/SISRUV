/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.util.List;
import accesomodel.APerfil_opera;

/**
 *
 * @author e_mv
 */
public interface APerfil_operaDao {
    public List<APerfil_opera> findAll_Perfil_opera(Integer cod_perfil);
    public boolean deleteOpe(int codPerfil);
}

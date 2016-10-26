/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.util.List;
import accesomodel.APerfil;
import accesomodel.AOperacion;

/**
 *
 * @author e_mv
 */
public interface APerfilDao {
    public List<APerfil> findAll_Perfil();
    public boolean insert(int num, APerfil perfil, int idAdmin);
    public boolean insertPer_Ope(int num, List<AOperacion> ListOperacion, int idAdmin);
    public int max();
    public int maxPerOpe();
    public List<AOperacion> find_Ope_Perf(int codigoPerfil);
    public int insert_log(APerfil perfil, int id_log);
    public int max_log();
}

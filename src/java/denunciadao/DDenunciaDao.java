/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denunciadao;

import java.util.List;
import denunciamodel.DDenuncia;

/**
 *
 * @author KRETCO
 */
public interface DDenunciaDao {
    public List<DDenuncia> findAll();
    public int insert(int num, DDenuncia denuncia);
    public int max();
    public int max_log();
    public int find_inst();
    public int insert_log(DDenuncia denuncia, int id_log); 
}

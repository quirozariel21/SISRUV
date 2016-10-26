/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresdao;

import java.util.List;
import indicadoresmodel.IDenuncia;

/**
 *
 * @author KRETCO
 */
public interface IDenunciaDao {
    public List<IDenuncia> findAll();
    public int insert(int num, IDenuncia denuncia);
    public int max();
    
}

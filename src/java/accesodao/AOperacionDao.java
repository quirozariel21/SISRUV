/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.util.List;
import accesomodel.AOperacion;

/**
 *
 * @author e_mv
 */
public interface AOperacionDao {    
    public List<AOperacion> findAll(Integer aplicacion_id_app);    
}

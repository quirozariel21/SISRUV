/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denunciadao;

import java.util.List;
import denunciamodel.DInstitucion;

/**
 *
 * @author KRETCO
 */
public interface DInstitucionDao {
        public List<DInstitucion> findAll();
        public int insert(int num, DInstitucion institucion);
        public int max();
}

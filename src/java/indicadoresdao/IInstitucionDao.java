/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresdao;

import java.util.List;
import indicadoresmodel.IInstitucion;

/**
 *
 * @author KRETCO
 */
public interface IInstitucionDao {
        public List<IInstitucion> findAll();
        public int insert(int num, IInstitucion institucion);
        public int max();
}

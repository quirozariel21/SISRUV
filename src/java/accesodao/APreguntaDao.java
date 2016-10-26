/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.util.List;
import javax.faces.model.SelectItem;
import accesomodel.APregunta;

/**
 *
 * @author KRETCO
 */
public interface APreguntaDao {
    public List<APregunta> findAll_Preguntas();
    public boolean insert(int num, APregunta pregunta);
    public List<SelectItem> findAll_nInst(String descrip);
    public int max();
}

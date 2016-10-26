/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresdao;

import java.util.List;
import indicadoresmodel.IPregunta;

/**
 *
 * @author KRETCO
 */
public interface IPreguntaDao {
    public String find_parameters();
    public int insert(int num, IPregunta pregunta);
    public int max();
    public List<IPregunta>findAllIdt(int id);
}

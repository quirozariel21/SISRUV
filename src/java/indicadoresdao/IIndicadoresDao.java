/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresdao;

import indicadoresmodel.IIndicadores;

/**
 *
 * @author KRETCO
 */
public interface IIndicadoresDao {
    public String find_parameters();
    public int insert(int num, IIndicadores indicadores);
    public int max();
    public String dev_institucion();
    public int max_log();
    public int insert_log(IIndicadores indicadores, int id_log); 
    
}

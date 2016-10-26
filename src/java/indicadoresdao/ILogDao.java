/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadoresdao;

import indicadoresmodel.ILog;

/**
 *
 * @author KRETCO
 */
public interface ILogDao {
    public int insert(int num, ILog log);
    public int max_event();
    public int max_error();
}

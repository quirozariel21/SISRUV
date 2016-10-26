/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import accesomodel.ALog;

/**
 *
 * @author KRETCO
 */
public interface ALogDao {
     public int max_event();
    public int max_error();
    public int insert(int num, ALog log);
}

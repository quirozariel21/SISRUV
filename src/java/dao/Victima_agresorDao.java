/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Victima_agresor;

/**
 *
 * @author KRETCO
 */
public interface Victima_agresorDao {
    public List<Victima_agresor> findAll();
    public int max();
    
    // LOG, VER SI SE NECESITA
   /* public int max_log();*/
}

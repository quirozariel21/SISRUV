/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.VicAgre_persona;

/**
 *
 * @author KRETCO
 */
public interface VicAgre_personaDao {
    public List<VicAgre_persona> findAll();
    public int max();
    // LOG, VER SI SE NECESITA
    /*
    public int max_log();
    */
}

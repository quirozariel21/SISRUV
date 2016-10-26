/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Log;

/**
 *
 * @author KRETCO
 */
public interface LogDao {
    public int max_event();
    public int max_error();
    public int insert(int num, Log log);
}

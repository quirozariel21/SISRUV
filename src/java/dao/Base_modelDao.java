	
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Base_model;
import model.Persona;
import model.Persona_variables;
import model.Victima_agresor;

/**
 *
 * @author KRETCO
 */
public interface Base_modelDao {
    public List<Base_model> find_model(String numero, String nombre, String paterno, String materno);
    public int insert(int num, Base_model base_model, int existe);
    public int insert_vic(int num, Base_model base_model, int existe, int resp);
    public Base_model data_baseModel(Victima_agresor victimaAgresor);
    
    public List<Base_model> find_year();
    public List<Base_model> find_codruv(String cod_sistema);
    public List<Base_model> find_mod(String vic_numero, String vic_nombre, String vic_paterno, String vic_materno, String agr_numero, String agr_nombre, String agr_paterno, String agr_materno);
   // public String devcodper();
    public int devcodper();
    public Persona find_exist_ci(Persona persona);
    public Persona_variables find_exist_detalle(Persona persona);
    public boolean find_exist_ci_save(Persona persona);
// LOG, VER SI SE NECESITA    
    //public int insert_log(Base_model base_model, int id_log, int exist);
    //public int insertAgre_log();

    
}
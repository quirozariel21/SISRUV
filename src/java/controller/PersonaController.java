/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PersonaDao;
import dao.PersonaDaoImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Persona;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author KRETCO
 */
@ManagedBean
@ViewScoped
public class PersonaController implements Serializable{
    
    private List<Persona> listPersona;
    private Persona selectedPersona;
    
    @PostConstruct
    public void init() {
        PersonaDao dao = new PersonaDaoImpl();
        listPersona = dao.listPersona();
        selectedPersona = new Persona();
    }

    public void onRowSelect(SelectEvent event) {
        System.out.println("entra onselect");
    }
    
    public List<Persona> getListPersona() {
        
        return listPersona;
    }

    public void setListPersona(List<Persona> listPersona) {
        this.listPersona = listPersona;
    }

    public Persona getSelectedPersona() {
        return selectedPersona;
    }

    public void setSelectedPersona(Persona selectedPersona) {
        this.selectedPersona = selectedPersona;
    }
    
}

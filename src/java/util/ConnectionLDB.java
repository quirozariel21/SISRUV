/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.faces.context.FacesContext;

/**
 *
 * @author KRETCO
 */
public class ConnectionLDB {
    private String gestorBDL;
    private String servidorL;
    private String baseDatoL;
    private String usuarioL;
    private String passwordL;

    public Connection getConnectionDB() {

       Connection connection = null;
        gestorBDL = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("gestorBDL");
        servidorL = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("servidorL");
        baseDatoL = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("baseDatoL");
        usuarioL = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("usuarioL");
        passwordL = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("passwordL");
        
 
//        gestorBD = "postgresql";
//        servidor = "localhost";
//        baseDato = "ruv";
//        usuario = "postgres";
//        password = "postgres";


        if (gestorBDL.equals("postgresql")) {
			try {
                            
				Class.forName("org.postgresql.Driver");
                                System.out.println("var:" +servidorL + baseDatoL + usuarioL +passwordL);
                                
//                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+servidor+"", ""+usuario+"", ""+password+"");
                                connection = DriverManager.getConnection("jdbc:postgresql://"+servidorL+":5432/"+baseDatoL+"", ""+usuarioL+"", ""+passwordL+"");
                                
			} catch (ClassNotFoundException e) {
				System.out.println("EL driver de la BD no se encuentra fisicamente en la aplicacion");
				e.printStackTrace();
			} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}
		}
return connection;

    }

    public static Object getSessionBean(String namebean) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(namebean);
    }

    public String getGestorBDL() {
        return gestorBDL;
    }

    public void setGestorBDL(String gestorBDL) {
        this.gestorBDL = gestorBDL;
    }

    public String getServidorL() {
        return servidorL;
    }

    public void setServidorL(String servidorL) {
        this.servidorL = servidorL;
    }

    public String getBaseDatoL() {
        return baseDatoL;
    }

    public void setBaseDatoL(String baseDatoL) {
        this.baseDatoL = baseDatoL;
    }

    public String getUsuarioL() {
        return usuarioL;
    }

    public void setUsuarioL(String usuarioL) {
        this.usuarioL = usuarioL;
    }

    public String getPasswordL() {
        return passwordL;
    }

    public void setPasswordL(String passwordL) {
        this.passwordL = passwordL;
    }

    
}

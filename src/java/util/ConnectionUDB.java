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
public class ConnectionUDB {
    private String gestorBDU;
    private String servidorU;
    private String baseDatoU;
    private String usuarioU;
    private String passwordU;

    public Connection getConnectionDB() {

       Connection connection = null;
        gestorBDU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("gestorBDU");
        servidorU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("servidorU");
        baseDatoU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("baseDatoU");
        usuarioU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("usuarioU");
        passwordU = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("passwordU");
        
 
//        gestorBD = "postgresql";
//        servidor = "localhost";
//        baseDato = "ruv";
//        usuario = "postgres";
//        password = "postgres";


        if (gestorBDU.equals("postgresql")) {
			try {
                            
				Class.forName("org.postgresql.Driver");
                                System.out.println("var:" +servidorU + baseDatoU + usuarioU +passwordU);
                                
//                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+servidor+"", ""+usuario+"", ""+password+"");
                                connection = DriverManager.getConnection("jdbc:postgresql://"+servidorU+":5432/"+baseDatoU+"", ""+usuarioU+"", ""+passwordU+"");
                                
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

    public String getGestorBDU() {
        return gestorBDU;
    }

    public void setGestorBDU(String gestorBDU) {
        this.gestorBDU = gestorBDU;
    }

    public String getServidorU() {
        return servidorU;
    }

    public void setServidorU(String servidorU) {
        this.servidorU = servidorU;
    }

    public String getBaseDatoU() {
        return baseDatoU;
    }

    public void setBaseDatoU(String baseDatoU) {
        this.baseDatoU = baseDatoU;
    }

    public String getUsuarioU() {
        return usuarioU;
    }

    public void setUsuarioU(String usuarioU) {
        this.usuarioU = usuarioU;
    }

    public String getPasswordU() {
        return passwordU;
    }

    public void setPasswordU(String passwordU) {
        this.passwordU = passwordU;
    }

    
}

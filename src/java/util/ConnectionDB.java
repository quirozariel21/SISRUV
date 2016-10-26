/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.faces.context.FacesContext;




/**
 *
 * @author mi_mv
 */
public class ConnectionDB {

    private String gestorBD;
    private String servidor;
    private String baseDato;
    private String usuario;
    private String password;

    public Connection getConnectionDB() {

       Connection connection = null;
        gestorBD = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("gestorBD");
        servidor = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("servidor");
        baseDato = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("baseDato");
        usuario = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("usuario");
        password = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("password");
        
 
//        gestorBD = "postgresql";
//        servidor = "localhost";
//        baseDato = "ruv";
//        usuario = "postgres";
//        password = "postgres";


        if (gestorBD.equals("postgresql")) {
			try {
                            
				Class.forName("org.postgresql.Driver");
                                System.out.println("vargghughughughugu:" +servidor + baseDato + usuario +password);
                                
//                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+servidor+"", ""+usuario+"", ""+password+"");
                                connection = DriverManager.getConnection("jdbc:postgresql://"+servidor+":5432/"+baseDato+"", ""+usuario+"", ""+password+"");
                                
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

    public String getGestorBD() {
        return gestorBD;
    }

    public void setGestorBD(String gestorBD) {
        this.gestorBD = gestorBD;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getBaseDato() {
        return baseDato;
    }

    public void setBaseDato(String baseDato) {
        this.baseDato = baseDato;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

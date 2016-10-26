/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoutil;

//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.faces.context.FacesContext;

/**
 *
 * @author marcelo velez
 */
public class AConnectionDinamic {

    public Connection getConnectionDinamic(String gestorBD, String servidor, String baseDato, String usuario, String password) {
        Connection connection = null;
        if (gestorBD.equals("postgresql")) {
            try {
                Class.forName("org.postgresql.Driver");
                System.out.println("var:" + servidor + baseDato + usuario + password);
                connection = DriverManager.getConnection("jdbc:postgresql://" + servidor + ":5432/" + baseDato + "", "" + usuario + "", "" + password + "");
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

    private Object getServletConfig() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

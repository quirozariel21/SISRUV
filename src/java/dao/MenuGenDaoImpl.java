package dao;

/**
 ** @author servidor
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.MenuGen;
import util.ConnectionUDB;
        

public class MenuGenDaoImpl implements MenuGenDao{

    @Override
    public List<MenuGen> findMenuUser(Integer cod_perfil) {
        List <MenuGen> listMenu = new ArrayList<>();
        
        ConnectionUDB conn = new ConnectionUDB();
        Connection connection = conn.getConnectionDB();
        /**VERIFICAR QUE id_USUARIO SE PASA*/
        System.out.println("USUARIO ID "+cod_perfil);
        
        try {
            String query = "SELECT\n"
                    + " operacion.aplicacion_id_app AS id_app\n"
                    + " FROM\n"
                    + " usuario\n"
                    + " INNER JOIN usuario_perfil ON usuario_perfil.usuario_cod_usuario = usuario.cod_usuario\n"
                    + " INNER JOIN perfil ON usuario_perfil.perfil_cod_perfil = perfil.cod_perfil\n"
                    + " INNER JOIN perfil_opera ON perfil_opera.perfil_cod_perfil = perfil.cod_perfil\n"
                    + " INNER JOIN operacion ON perfil_opera.operacion_cod_opera = operacion.cod_opera\n"
                    + " WHERE\n"
                    + " usuario.cod_usuario = "+cod_perfil+"\n"
                    + " GROUP BY\n"
                    + " operacion.aplicacion_id_app";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                MenuGen menuItem = new MenuGen();
                menuItem.setAplicacionId(resultSet.getInt("id_app"));
                listMenu.add(menuItem);
                System.out.println("ID aplicaciones PARA MENU: " + menuItem.getAplicacionId());
                
            }
        } catch (SQLException e) {
            System.out.println("Error SqlExeption" + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return listMenu;
    }
  
    
}

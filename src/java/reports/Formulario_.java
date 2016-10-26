/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import dao.T_datosDao;
import dao.T_datosDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.T_datos;
import model.Usuario_Tdatos;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import util.ConnectionServlet;

/**
 *
 * @author e_mvvvv
 */
@WebServlet(name = "Formulario_", urlPatterns = {"/faces/Formulario_"})
public class Formulario_ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String gestorBD = getServletContext().getInitParameter("gestorBD");
        String servidor = getServletContext().getInitParameter("servidor");
        String baseDato = getServletContext().getInitParameter("baseDato");
        String usuario = getServletContext().getInitParameter("usuario");
        String password = getServletContext().getInitParameter("password");

        String logo_ruv = getServletContext().getRealPath("/WEB-INF/reports/logoRUV.png");
        String logo_sip = getServletContext().getRealPath("/WEB-INF/reports/logoSIP.png");
        String cod = request.getParameter("cod");
        String t_v = request.getParameter("tv");
        String cod_u = request.getParameter("cu");
        //tipo de violencia
        String param_tipo_violencia = "";
        Integer contador = 0;
        String[] arrayViolencia = t_v.split(",");

        T_datosDao tdatoDao = new T_datosDaoImpl();
        List<T_datos> list = tdatoDao.findAllIdt_servlet("ambito_tipo_violencia", gestorBD, servidor, baseDato, usuario, password);

        for (int i = 0; i < arrayViolencia.length; i++) {
            for (T_datos item : list) {
                if (arrayViolencia[i].toString().equals(item.getId_tdatos().toString())) {
                    if (contador > 0) {
                        param_tipo_violencia = param_tipo_violencia + ", " + item.getDescripcion();
                    } else {
                        param_tipo_violencia = item.getDescripcion();
                        contador++;
                    }
                }
            }
        }

        String gestorBDU = getServletContext().getInitParameter("gestorBDU");
        String servidorU = getServletContext().getInitParameter("servidorU");
        String baseDatoU = getServletContext().getInitParameter("baseDatoU");
        String usuarioU = getServletContext().getInitParameter("usuarioU");
        String passwordU = getServletContext().getInitParameter("passwordU");
        UsuarioDao usuarioDaoDao = new UsuarioDaoImpl();
        Usuario_Tdatos usuario_Tdatos = usuarioDaoDao.datoUsuario(Integer.parseInt(cod_u), gestorBDU, servidorU, baseDatoU, usuarioU, passwordU);   
        
        ServletContext sc = null;
        Map parametros = new HashMap();
        parametros.put("logo_ruv", logo_ruv);
        parametros.put("logo_sip", logo_sip);
        parametros.put("cod", Integer.parseInt(cod));
        parametros.put("tipo_violencia", param_tipo_violencia);
        
        parametros.put("detail_dep", usuario_Tdatos.getDetalle_depto());
        parametros.put("detail_mun", usuario_Tdatos.getDetalle_muni());
        parametros.put("detail_serv", usuario_Tdatos.getDetalle_serv());
        //parametros.put("detail_sub", usuario_Tdatos.getDetalle_subs()); //DETALLE SUB DESACTIVADO
        parametros.put("cod_usuario", usuario_Tdatos.getUsuario().getCod_usuario());

        sc = this.getServletContext();
        Connection conexion = null;
        ConnectionServlet C = null;
        try {
            C = new ConnectionServlet();
            C.getConnectionDB(gestorBD, servidor, baseDato, usuario, password);
            conexion = C.getConnectionDB(gestorBD, servidor, baseDato, usuario, password);

            File reportFile = new File(sc.getRealPath("/WEB-INF/reports/formulario_v.jasper"));
            JasperReport jasperReport;
            jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getAbsoluteFile());
            byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parametros, conexion);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        } catch (JRException e) {
            throw new ServletException(e);
        } catch (IOException e) {
            throw new ServletException(e);
        } finally {
            conexion.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Formulario_.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Formulario_.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

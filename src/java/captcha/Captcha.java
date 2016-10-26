/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha;

import static com.lowagie.text.pdf.PdfFileSpecification.url;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.image.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author e_mv
 */
@WebServlet(name = "Captcha", urlPatterns = {"/Captcha"})
public class Captcha extends HttpServlet {
    private Object bufimg;

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
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
    
    private int height = 0;
    private int width = 0;
    public static final String CAPTCHA_KEY = "captcha_key_name";
   @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        height = Integer.parseInt(getServletConfig().getInitParameter("height"));
        width = Integer.parseInt(getServletConfig().getInitParameter("width"));
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //Expire response
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //File archivo = new File( "d:/abc.jpg" );
        String url = getServletContext().getRealPath("/resources/img/captcha.jpg");
        File archivo = new File(url);
        image = (ImageIO.read(archivo));
        
        
        Graphics2D graphics2D = image.createGraphics();
        Hashtable<TextAttribute, Object> map = new Hashtable<TextAttribute, Object>();
        Random r = new Random();
        String token = Long.toString(Math.abs(r.nextLong()), 36);
        String ch = token.substring(0, 6);
        Color c = new Color (4, 95, 180);
        Color d = new Color (10, 62, 112);
       GradientPaint gp = new GradientPaint(30, 30, d, 15, 25, c, false);
        graphics2D.setPaint(gp);
        Font font = new Font("Verdana", Font.CENTER_BASELINE, 26);
        graphics2D.setFont(font);        
        graphics2D.drawString(ch, 12, 40);
        graphics2D.dispose();
        graphics2D.drawLine(407, 355, 371, 349);

        HttpSession session = request.getSession(true);
        session.setAttribute(CAPTCHA_KEY, ch);
        
        
        //otro
       
        //otro

        OutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpeg", outputStream);
        outputStream.close();
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
        processRequest(request, response);
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
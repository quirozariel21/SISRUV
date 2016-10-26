/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import captcha.Captcha;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements PhaseListener {

    protected final Log logger = LogFactory.getLog(getClass());
    private String inputCaptcha;

    /**
     *
     * Redirects the login request directly to spring security check. Leave this
     * method as it is to properly support spring security.
     *
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public void doLogin() throws ServletException, IOException {

        boolean res = false;
        this.session = false;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Boolean isResponseCorrect = Boolean.FALSE;
        javax.servlet.http.HttpSession session = request.getSession();
        String c = (String) session.getAttribute(Captcha.CAPTCHA_KEY);
        if (inputCaptcha.equals(c)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println("Metodo de spring security");
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
            FacesContext.getCurrentInstance().responseComplete();

            Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);
            res = e instanceof BadCredentialsException;
            System.out.println("Respuesta de BadCredentialsException:: " + res);
            if (res) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o Contraseña invalidos...!", "Usuario o Contraseña invalidos...!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                System.out.println("Se ingreso con Exito");
                this.session = true;
            }

        } else {
            this.inputCaptcha = "";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El key no coincide", "El key no coincide");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void afterPhase(PhaseEvent event) {
    }

    /* (non-Javadoc)
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     *
     * Do something before rendering phase.
     */
    public void beforePhase(PhaseEvent event) {
        /*
        Exception e = (Exception) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (e instanceof BadCredentialsException) {
            logger.debug("Found exception in session map: " + e);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Username or password not valid.", "Username or password not valid"));
        }*/
// SE USA ESTE CODIGO PARA QUE LOS NAVEGADORES NO GUARDEN CACHE Y NO SIRVA EL BOTON BACK
// RELACIONADO CON FACE-CONFIG.XML -> LIFECYCLE
        FacesContext facesContext = event.getFacesContext();
        HttpServletResponse response = (HttpServletResponse) facesContext
                .getExternalContext().getResponse();
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        // Stronger according to blog comment below that references HTTP spec
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "must-revalidate");
    }

    /* (non-Javadoc)
     * @see javax.faces.event.PhaseListener#getPhaseId()
     *
     * In which phase you want to interfere?
     */
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    public String getInputCaptcha() {
        return inputCaptcha;
    }

    public void setInputCaptcha(String inputCaptcha) {
        this.inputCaptcha = inputCaptcha;
    }

    private boolean session;
    /* Metodo que aparece cuando se ha excedido el tiempo de sesion*/
    public void buttonIndex() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        //String url = requestURL.substring(0, requestURL.lastIndexOf("/")); // es para que vaya directo a la raiz LE AGREGAMOS /SISRUV
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(/*url +*/ "/SISRUV/faces/login.xhtml");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }
    /* Conectado con web.xml. para redirigir al menu*/
    public void buttonMenuIndex() {
        String url = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("menuIndex");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

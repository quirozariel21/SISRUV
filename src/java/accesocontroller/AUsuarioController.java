package accesocontroller;

import accesodao.AOperacionDao;
import accesodao.AOperacionDaoImpl;
import accesodao.AUsuarioDao;
import accesodao.AUsuarioDaoImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.MenuView;
import accesomodel.AOperacion;
import accesomodel.AUsuario;
import org.primefaces.event.CloseEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ManagedBean
@SessionScoped
public class AUsuarioController implements Serializable {

    AUsuarioDao dao_usuario = new AUsuarioDaoImpl();
    AOperacionDao dao_operacion = new AOperacionDaoImpl();

    private AUsuario usuario;
    private Date fecha_actual;
    private String item_url;
    private List<MenuView> menuViewResponsive = new ArrayList<>();

    public AUsuarioController() {

        usuario = new AUsuario();
        menuViewResponsive = new ArrayList<>();

        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                usuario.setUsername(((User) authentication.getPrincipal()).getUsername());
                usuario.setPassword(((User) authentication.getPrincipal()).getPassword());
                usuario = dao_usuario.findUsuario(usuario);
                System.out.println("val:" + usuario.getNombre());
                
            }
        }

    }

    public void actionLink() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String href = map.get("href");
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/" + href);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        context.getExternalContext().getSessionMap().remove("denunciaController");
        context.getExternalContext().getSessionMap().remove("aplicacionController");
    }

    private boolean flagconfimacion = false;

    public void actionCerrarSession() {
        this.flagconfimacion = true;
    }

    public void handleCloseDialog(CloseEvent event) {
        actionDialogCancelar();
    }

    public void actionDialogCancelar() {
        System.out.println("entra aquiiisisisisisissi");
        this.flagconfimacion = false;
    }

    public AUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(AUsuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha_actual() {
        fecha_actual = new Date();
        return fecha_actual;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public List<MenuView> getMenuViewResponsive() {
        return menuViewResponsive;
    }

    public void setMenuViewResponsive(List<MenuView> menuViewResponsive) {
        this.menuViewResponsive = menuViewResponsive;
    }

    public boolean isFlagconfimacion() {
        return flagconfimacion;
    }

    public void setFlagconfimacion(boolean flagconfimacion) {
        this.flagconfimacion = flagconfimacion;
    }

}

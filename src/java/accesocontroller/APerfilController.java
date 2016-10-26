/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesocontroller;

import accesodao.AAplicacionesDao;
import accesodao.AAplicacionesDaoImpl;
import accesodao.AOperacionDao;
import accesodao.AOperacionDaoImpl;
import accesodao.APerfilDao;
import accesodao.APerfilDaoImpl;
import accesodao.APerfil_operaDao;
import accesodao.APerfil_operaDaoImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import accesomodel.AAplicaciones;
import accesomodel.AArbolMenu;
import accesomodel.AOperacion;
import accesomodel.APerfil;
import accesomodel.APerfil_opera;
import controller.UsuarioController;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author KRETCO
 */
@ManagedBean
@SessionScoped
//@ViewScoped
public class APerfilController implements Serializable {

    APerfilDao perfilDao = new APerfilDaoImpl();
    AAplicacionesDao aplicacionesDao = new AAplicacionesDaoImpl();
    AOperacionDao operacionDao = new AOperacionDaoImpl();
    APerfil_operaDao perfil_operaDao = new APerfil_operaDaoImpl();
    
     private boolean flag_Perf = false;

    private List<APerfil> listPerfil;
    private APerfil selectedPerfil;

    private APerfil perfil;
    private List<AArbolMenu> listArbolMenu;
    private TreeNode rootArbolMenu;
    private TreeNode[] selectedNodes;

    public APerfilController() {
        listPerfil = new ArrayList<>();
        listArbolMenu = new ArrayList<>();
        perfil = new APerfil();

    }

    public void onRowSelect(SelectEvent event) {
  System.out.println("perfil "+this.selectedPerfil.getCod_perfil());
        perfil = this.selectedPerfil;
        List<APerfil_opera> result_list = perfil_operaDao.findAll_Perfil_opera(selectedPerfil.getCod_perfil());
        
        System.out.println("resultado de lista"+result_list.size());
        
        List<AAplicaciones> listAplicaciones = aplicacionesDao.findAll_Aplicaciones();
        TreeNode root = new CheckboxTreeNode(new AArbolMenu(0, "base", 0, "base"), null);
        root.setExpanded(true);
        TreeNode documents = new CheckboxTreeNode(new AArbolMenu(1, "Aplicaciones", 1, "app 1"), root);
        documents.setExpanded(true);
        for (AAplicaciones itemApp : listAplicaciones) {
            System.out.println("entra a for");
            TreeNode nodeP = new CheckboxTreeNode(new AArbolMenu(itemApp.getId_app(), itemApp.getDescripcion(), 0, "App"), documents);
            nodeP.setExpanded(true);
            List<AOperacion> listOperacion = operacionDao.findAll(itemApp.getId_app());

            if (listOperacion.size() > 0) {
                for (AOperacion itemOpe : listOperacion) {
                    if (itemOpe.getCod_opera_padre() == 0) {
                        TreeNode nodePrim = new CheckboxTreeNode("document", new AArbolMenu(itemOpe.getCod_opera(), itemOpe.getDescripcion(), itemOpe.getCod_opera_padre(), "Ope"), nodeP);
                        nodePrim.setExpanded(true);                        
                        if (esPadre(itemOpe.getCod_opera(), listOperacion)) {
                            for (AOperacion itemOpeSub : listOperacion) {
                                if (itemOpe.getCod_opera() == itemOpeSub.getCod_opera_padre()) {
                                    TreeNode nodeSeg = new CheckboxTreeNode("document", new AArbolMenu(itemOpeSub.getCod_opera(), itemOpeSub.getDescripcion(), itemOpeSub.getCod_opera_padre(), "Ope"), nodePrim);
                                    nodeSeg.setExpanded(true);                                    
                                    if(verificaCheckTree(itemOpeSub.getCod_opera(), result_list)) nodeSeg.setSelected(true);
                                }
                            }
                        }
                    }
                }
            }
        }
        rootArbolMenu = root;
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/perfilAdiciona.xhtml");
            this.flag_Perf = true;
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public String buttonNuevoPerfil() {
        String res = "";
        List<AAplicaciones> listAplicaciones = aplicacionesDao.findAll_Aplicaciones();
        TreeNode root = new CheckboxTreeNode(new AArbolMenu(0, "base", 0, "base"), null);
        root.setExpanded(true);
        TreeNode documents = new CheckboxTreeNode(new AArbolMenu(1, "Aplicaciones", 1, "app 1"), root);
        documents.setExpanded(true);
        for (AAplicaciones itemApp : listAplicaciones) {
            System.out.println("entra a for");
            TreeNode nodeP = new CheckboxTreeNode(new AArbolMenu(itemApp.getId_app(), itemApp.getDescripcion(), 0, "App"), documents);
            nodeP.setExpanded(true);
            List<AOperacion> listOperacion = operacionDao.findAll(itemApp.getId_app());

            if (listOperacion.size() > 0) {
                for (AOperacion itemOpe : listOperacion) {
                    if (itemOpe.getCod_opera_padre() == 0) {
                        TreeNode nodePrim = new CheckboxTreeNode("document", new AArbolMenu(itemOpe.getCod_opera(), itemOpe.getDescripcion(), itemOpe.getCod_opera_padre(), "Ope"), nodeP);
                        nodePrim.setExpanded(true);
                        if (esPadre(itemOpe.getCod_opera(), listOperacion)) {
                            for (AOperacion itemOpeSub : listOperacion) {
                                if (itemOpe.getCod_opera() == itemOpeSub.getCod_opera_padre()) {
                                    TreeNode nodeSeg = new CheckboxTreeNode("document", new AArbolMenu(itemOpeSub.getCod_opera(), itemOpeSub.getDescripcion(), itemOpeSub.getCod_opera_padre(), "Ope"), nodePrim);
                                    nodeSeg.setExpanded(true);
                                    
                                }
                            }
                        }
                    }
                }
                res = "nuevo_perfil";
                
            }
        }
        perfil = new APerfil();
        rootArbolMenu = root;
        this.flag_Perf = false;
        return res;
    }
    
    public boolean esPadre(Integer id, List<AOperacion> listOperacion) {
        boolean res = false;
        for (AOperacion item : listOperacion) {
            if (id == item.getCod_opera_padre()) {
                res = true;
            }
        }
        return res;
    }
    
    public  boolean verificaCheckTree (Integer id_opera, List<APerfil_opera> list){
        boolean res = false;
        for (APerfil_opera item : list) {
            if (id_opera == item.getOperacion_cod_opera()) {
                res = true;
            }
            
        }
        return res;
    }

    public String displaySelectedMultiple(TreeNode[] nodes, int idAdmin) {
        String res = "";
        List<AOperacion> lOperacion = new ArrayList<>();
        System.out.println("AperfilController.displaySelectedMultiple() : NODES = " + nodes );
        if (nodes != null && nodes.length > 0) {
            System.out.println("AperfilController.displaySelectedMultiple() : NODES = " + nodes.length);
            StringBuilder builder = new StringBuilder();

            for (TreeNode node : nodes) {
                AArbolMenu a = (AArbolMenu) node.getData();

                System.out.println("AperfilController.displaySelectedMultiple() : TIPO = " + a.getTipo());
                if (a.getTipo().equals("Ope")) {
                    AOperacion data = new AOperacion(a.getCodigo(), a.getDescripcion());
                    lOperacion.add(data);
                }
            }

        }
        //aqui viene la insercion
        if( perfilDao.insert(1, perfil, idAdmin) == true){
            System.out.println("APerfilController.displaySelectedMultiple(): perfilDao.insert == true " );
            perfilDao.insertPer_Ope(1, lOperacion, idAdmin);
            res = "cancela_nuevo_perfil";
        }
        System.out.println("APerfilController.displaySelectedMultiple(): Perfil " + perfil.getNombre());
        //System.out.println("APerfilController.displaySelectedMultiple()");
        for (AOperacion item : lOperacion) {
        System.out.println("APerfilController.displaySelectedMultiple(): Operacion = " + item.getCod_opera());    
        }
        return res;
    }
    
    public String modificarPerfil(TreeNode[] nodes, int idAdmin){
         String res = "";
        List<AOperacion> lOperacion = new ArrayList<>();
        if (nodes != null && nodes.length > 0) {
            StringBuilder builder = new StringBuilder();

            for (TreeNode node : nodes) {
                AArbolMenu a = (AArbolMenu) node.getData();

                System.out.println("APerfilController.modificarPerfil(): Perfil TIPO = " + a.getTipo());
                if (a.getTipo().equals("Ope")) {
                    AOperacion data = new AOperacion(a.getCodigo(), a.getDescripcion());
                    lOperacion.add(data);
                }
            }

        }
        //aqui viene la insercion
        
        System.out.println("APerfilController.modificarPerfil(): Perfil Codigo =  " + perfil.getCod_perfil());
        if( perfilDao.insert(2, perfil,idAdmin) == true){
            System.out.println("ENTRA 2 . APerfilController.modificarPerfil(): perfilDao.insert(2,perfil)");
            if(perfil_operaDao.deleteOpe(perfil.getCod_perfil())){
                System.out.println("entra2222222222222");
                 perfilDao.insertPer_Ope(1, lOperacion, idAdmin);
                 res = "cancela_nuevo_perfil";
                 this.flag_Perf = true;
            }
            
           
        }
        System.out.println("APerfilController.modificarPerfil(): Perfil = " + perfil.getNombre());
        //System.out.println("APerfilController.modificarPerfil()");
        for (AOperacion item : lOperacion) {
            System.out.println("APerfilController.modificarPerfil(): operacion = " + item.getCod_opera());    
        }
        return res;
    }

    public List<APerfil> getListPerfil() {
        listPerfil = new ArrayList<>();
        listPerfil = perfilDao.findAll_Perfil();
        return listPerfil;
    }

    public void setListPerfil(List<APerfil> listPerfil) {
        this.listPerfil = listPerfil;
    }

    public APerfil getSelectedPerfil() {
        return selectedPerfil;
    }

    public void setSelectedPerfil(APerfil selectedPerfil) {
        this.selectedPerfil = selectedPerfil;
    }

    public APerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(APerfil perfil) {
        this.perfil = perfil;
    }

    public List<AArbolMenu> getListArbolMenu() {
        return listArbolMenu;
    }

    public void setListArbolMenu(List<AArbolMenu> listArbolMenu) {
        this.listArbolMenu = listArbolMenu;
    }

    public TreeNode getRootArbolMenu() {
        return rootArbolMenu;
    }

    public void setRootArbolMenu(TreeNode rootArbolMenu) {
        this.rootArbolMenu = rootArbolMenu;
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public boolean isFlag_Perf() {
        return flag_Perf;
    }

    public void setFlag_Perf(boolean flag_Perf) {
        this.flag_Perf = flag_Perf;
    }
    

}

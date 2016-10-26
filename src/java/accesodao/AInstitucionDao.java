/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodao;

import java.util.List;
import javax.faces.model.SelectItem;
import accesomodel.AInstitucion;
import accesomodel.AUsuario;
import accesomodel.AUsuarioInstitucion;

/**
 *
 * @author KRETCO
 */
public interface AInstitucionDao {
    public List<AInstitucion> findAll_Instituciones();
    public boolean insert(int num, AInstitucion institucion);
    public int max();
    public List<AInstitucion> listInstitucion(Integer idInstitucion);
    public List<SelectItem> findAll_nInst();
    public List<SelectItem> findAll_Inst(int codInstitucion);
    public String findAll_tipo(int codInstitucion);
    
    public boolean modifica(int codUsuario, int idInstitucion);
    //inserta Usuario institucion
    public boolean insertUsuInst(int num, int codUsuario, int idInstitucion);
    public AUsuario findUsuInst(int codUsuInst);
    public List<AUsuarioInstitucion> findAll_UsuarioInstitucion();
    public List<AUsuario> listUsuario();
    
}

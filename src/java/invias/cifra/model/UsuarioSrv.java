/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.model;

import invias.cifra.dao.UsuarioDAO;
import invias.cifra.entity.Usuario;
import invias.cifra.entity.UsuarioContrato;
import invias.cifra.exception.CifraException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jaflorez
 */
public class UsuarioSrv implements Serializable {

    private static final long serialVersionUID = 1L;


    public List<UsuarioContrato> usuarioContratoDireccionLst(Usuario usuario, Integer direccion) throws CifraException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.usuarioContratoDireccionLst(usuario, direccion);

    }

    public List<Usuario> getUsuarioLst() throws CifraException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscar();
    }

    public List<Usuario> getUsuarioTipoLst(int tipo) throws CifraException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscarTipo(tipo);
    }

    public void insertarUsuario(Usuario usuario) throws CifraException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.insertar(usuario);
    }

    public void modificarUsuario(Usuario usuario) throws CifraException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.updateUsuario(usuario);
    }

    public Usuario loadUsuarioByDocumento(String documento) throws CifraException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.loadUsuarioByDocumento(documento);
    }

    public Usuario loadUsuario(Integer identificador) throws CifraException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.loadUsuario(identificador);
    }

    public Usuario loadUsuarioByCadenaVerificacion(String cadena) throws CifraException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.loadUsuarioByCadenaVerificacion(cadena);

    }


    public void updateUsuario(Usuario usuario) throws CifraException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.updateUsuario(usuario);
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.util.CifraUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;

import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@SessionScoped
public class MenuBean extends BaseBean {

    /**
     * Creates a new instance of menuBean
     */
    private MenuModel menuModel;

    public MenuBean() {
        this.makeMenu();
    }

    private void makeMenu() {
        this.menuModel = new DefaultMenuModel();
        DefaultMenuItem itemHome = new DefaultMenuItem("Inicio");
        itemHome.setUrl("../inicio/index.xhtml");
        itemHome.setIcon("ui-icon-home");
        this.menuModel.addElement(itemHome);
        //First submenu  
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        if (sgbSes == null) {
            CifraUtil.redirectRootOut();
        }
        if (sgbSes.getCurUsuario() != null) {
        } else {
            CifraUtil.redirectRootOut();
        }

        int tipoUsr = sgbSes.getCurUsuario().getTipo();
        DefaultMenuItem item;
        if (tipoUsr == 2 || tipoUsr == 3 || tipoUsr == 4 || tipoUsr == 5 || tipoUsr == 1 || tipoUsr == 6) {
            DefaultSubMenu tramiteSubmenu = new DefaultSubMenu("Contratos");
            tramiteSubmenu.setIcon("ui-icon-gear");
            if (tipoUsr == 3) {
                item = new DefaultMenuItem("Nuevo contrato");
                item.setIcon("ui-icon-document");
                item.setUrl("../contratos/contrato.xhtml?newContrato=S");
                tramiteSubmenu.addElement(item);
            }
            if (tipoUsr == 5) {
                item = new DefaultMenuItem("Registro SIIF");
                item.setIcon("ui-icon-document");
                item.setUrl("../contratos/reportesiiflst.xhtml");
                tramiteSubmenu.addElement(item);
            } else {
                item = new DefaultMenuItem("Contratos registrados");
                item.setIcon("ui-icon-document");
                item.setUrl("../contratos/contratolst.xhtml");
                tramiteSubmenu.addElement(item);
            }
            this.menuModel.addElement(tramiteSubmenu);
        }
        if (tipoUsr == 1 || tipoUsr == 3) {
            DefaultSubMenu adminSubmenu = new DefaultSubMenu("Administracion");
            if (tipoUsr == 1) {
                item = new DefaultMenuItem("Usuarios");
                item.setUrl("../admin/usuariolst.xhtml");
                item.setIcon("ui-icon-person");
                adminSubmenu.addElement(item);
                item = new DefaultMenuItem("Proyectos");
                item.setUrl("../admin/proyectolst.xhtml");
                item.setIcon("ui-icon-gear");
                adminSubmenu.addElement(item);

            }
            item = new DefaultMenuItem("Tercero");
            item.setUrl("../admin/tercerolst.xhtml");
            item.setIcon("ui-icon-person");
            adminSubmenu.addElement(item);
            this.menuModel.addElement(adminSubmenu);
        }
        DefaultSubMenu adminSubmenu = new DefaultSubMenu("Resportes/Busquedas");
        item = new DefaultMenuItem("Reporte consolidado");
        item.setIcon("ui-icon-suitcase");
        item.setUrl("../reportes/consolidado.xhtml");
        adminSubmenu.addElement(item);

        item = new DefaultMenuItem("Reporte por proyecto");
        item.setIcon("ui-icon-suitcase");
        item.setUrl("../reportes/porproyecto.xhtml");
        adminSubmenu.addElement(item);
        this.menuModel.addElement(adminSubmenu);
        DefaultSubMenu datosPersonalesSubmenu = new DefaultSubMenu("Datos personales");
        item = new DefaultMenuItem("Mis datos");
        item.setUrl("../admin/usuarioedit.xhtml");
        item.setIcon("");
        datosPersonalesSubmenu.addElement(item);
        item = new DefaultMenuItem("Cambiar contraseña");
        item.setUrl("../admin/usuarioclave.xhtml");
        datosPersonalesSubmenu.addElement(item);
        this.menuModel.addElement(datosPersonalesSubmenu);
    }

    public void cerrarSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            ExternalContext oContext = FacesContext.getCurrentInstance().getExternalContext();
            oContext.redirect(oContext.getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(MenuBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MenuRedirec(String ruta) {
        try {
            ExternalContext oContext = FacesContext.getCurrentInstance().getExternalContext();
            oContext.redirect(oContext.getRequestContextPath() + ruta + "?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(MenuBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

}

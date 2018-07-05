/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.db;
import invias.cifra.bean.ReporteBean;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

/**
 *
 * @author jaflorez
 */
public class CifraConn {
    /**
     * Almacena el valor de la propiedad initCtx
     */
    private Context initCtx;
    
    /**
     * Almacena el valor de la propiedad ds
     */
    private DataSource ds;
    
    /**
     * Almacena el valor de la propiedad con
     */
    private Connection con;
    
    /**
     * Almacena el valor de la propiedad envCtx
     */
    private Context envCtx;

    public CifraConn()throws SQLException,Exception {
        initCtx=null;
        ds=null;
        con=null;
        try{
            initCtx=new InitialContext();
            envCtx=(Context)initCtx.lookup("java:comp/env");
            ds=(DataSource)envCtx.lookup("jdbc/cifra");
            con=ds.getConnection();
        } catch(SQLException e){
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, e);
            con=null;
            throw e;
        } catch(Exception e){
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, e);
            con=null;
            throw e;
        }
    }
    
    /**
     * Crea una nueva instancia de ConnectionTramites
     */
    
    
}

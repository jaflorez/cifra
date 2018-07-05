/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dao;

import java.io.Serializable;
import javax.persistence.EntityManagerFactory;


/**
 *
 * @author jaflorez
 */
public class BaseDAO  implements Serializable{
    private static final long serialVersionUID = 1L;
    protected String PERSISTENCE_UNIT_NAME = "cifraPU";
    protected EntityManagerFactory factory;
    
    
}

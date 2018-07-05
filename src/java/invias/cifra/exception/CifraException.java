/**
 *    TramitesException.java
 *
 *    Creado el 18 de septiembre de 2008, 18:14
 *    Tramites, Invias
 *    @author GRUPO INVIAS
 *    PLATAFORMA - UNIVERSIDAD DE PAMPLONA
 *    Pamplona (Norte de Santander). Colombia.
 */

package invias.cifra.exception;

/**
 * @author GRUPO DE DESARROLLO PLATAFORMA - UNIVERSIDAD DE PAMPLONA
 */

public class CifraException extends Exception {
    
    /**
     * Holds value of property tipoPersona.
     */
    private String tipo;
    
    /**
     * Holds value of property clase.
     */
    private String clase;
    
    /**
     * Holds value of property proceso.
     */
    private String proceso;
    
    /**
     * Constructor con el mensaje de error.
     * @param msg el mensaje de error asociado con la excepción
     */
    public CifraException(String msg) {
        super(msg);
    }

    /**
     * Constructor con el mensaje de error y la raiz de la causa.
     * @param msg el mensaje de error asociado con la excepción
     * @param cause causa de la raiz del error de la excepción
     */
    public CifraException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public CifraException(String tipo, String clase, String proceso, String mensaje) {
        super(mensaje);
        this.tipo=tipo;
        this.clase=clase;
        this.proceso=proceso;
    }    
    
    /**
     * Getter for property tipoPersona.
     * @return Value of property tipoPersona.
     */
    public String getTipo() {
        return this.tipo;
    }
    
    /**
     * Setter for property tipoPersona.
     * @param tipo New value of property tipoPersona.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Getter for property clase.
     * @return Value of property clase.
     */
    public String getClase() {
        return this.clase;
    }
    
    /**
     * Setter for property clase.
     * @param clase New value of property clase.
     */
    public void setClase(String clase) {
        this.clase = clase;
    }
    
    /**
     * Getter for property proceso.
     * @return Value of property proceso.
     */
    public String getProceso() {
        return this.proceso;
    }
    
    /**
     * Setter for property proceso.
     * @param proceso New value of property proceso.
     */
    public void setProceso(String proceso) {
        this.proceso = proceso;
    }
    
}

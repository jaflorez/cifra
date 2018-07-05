/*
 * DatosCorreoVO.java
 *  Plataforma - Universidad de Pamplona
 * Created on 5 de agosto de 2004, 03:16 PM
 */

//package co.gov.icfesinteractivo.common.modelo;
package invias.cifra.email;

import java.io.Serializable;
import java.util.*;



/**
 * @author  Grupo de Desarrollo Plataforma
 * Universidad de Pamplona
 * @version 3.0
 */
 
public class DatosCorreo implements Serializable {
    
    
    /** Holds value of property mensaje. */
    
    private String mensaje = null;
    
    /** Holds value of property asunto. */
    private String asunto = null;
    
    /** Holds value of property remitente. */
    private String remitente = null;
    
    /** Holds value of property destinatario. */
    private ArrayList destinatario;
    
    /** Holds value of property destinatarioCopia. */
    private ArrayList destinatarioCopia;
    
    /** Holds value of property destinatarioOculto. */
    private ArrayList destinatarioOculto;
    
    /** Holds value of property adjunto. */
    private ArrayList adjunto;
    
    /**
     * Holds value of property error.
     */
    private String error;
    
    /** Creates a new instance of DatosCorreoVO */
    public DatosCorreo() {
    }
    
    /**
     * Getter for property mensaje.
     * @return Value of property mensaje.
     */
    public String getMensaje() {
        return this.mensaje;
    }
    
    /**
     * Setter for property mensaje.
     * @param mensaje New value of property mensaje.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    /**
     * Getter for property asunto.
     * @return Value of property asunto.
     */
    public String getAsunto() {
        return this.asunto;
    }
    
    /**
     * Setter for property asunto.
     * @param asunto New value of property asunto.
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    /**
     * Getter for property remitente.
     * @return Value of property remitente.
     */
    public String getRemitente() {
        return this.remitente;
    }
    
    /**
     * Setter for property remitente.
     * @param remitente New value of property remitente.
     */
    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }
    
    /**
     * Getter for property destinatario.
     * @return Value of property destinatario.
     */
    public ArrayList getDestinatario() {
        return this.destinatario;
    }
    
    /**
     * Setter for property destinatario.
     * @param destinatario New value of property destinatario.
     */
    public void setDestinatario(ArrayList destinatario) {
        this.destinatario = destinatario;
    }
    
    /**
     * Getter for property destinatarioCopia.
     * @return Value of property destinatarioCopia.
     */
    public ArrayList getDestinatarioCopia() {
        return this.destinatarioCopia;
    }
    
    /**
     * Setter for property destinatarioCopia.
     * @param destinatarioCopia New value of property destinatarioCopia.
     */
    public void setDestinatarioCopia(ArrayList destinatarioCopia) {
        this.destinatarioCopia = destinatarioCopia;
    }
    
    /**
     * Getter for property destinatarioCopiaOculta.
     * @return Value of property destinatarioCopiaOculta.
     */
    public ArrayList getDestinatarioOculto() {
        return this.destinatarioOculto;
    }
    
    /**
     * Setter for property destinatarioCopiaOculta.
     * @param destinatarioCopiaOculta New value of property destinatarioCopiaOculta.
     */
    public void setDestinatarioOculto(ArrayList destinatarioOculto) {
        this.destinatarioOculto = destinatarioOculto;
    }
    
    /**
     * Getter for property adjunto.
     * @return Value of property adjunto.
     */
    public ArrayList getAdjunto() {
        return this.adjunto;
    }
    
    /**
     * Setter for property adjunto.
     * @param adjunto New value of property adjunto.
     */
    public void setAdjunto(ArrayList adjunto) {
        this.adjunto = adjunto;
    }
    
    /**
     * Getter for property error.
     * @return Value of property error.
     */
    public String getError() {
        return this.error;
    }
    
    /**
     * Setter for property error.
     * @param error New value of property error.
     */
    public void setError(String error) {
        this.error = error;
    }

    
    
}
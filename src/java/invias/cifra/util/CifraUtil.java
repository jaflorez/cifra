/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.util;

import invias.cifra.bean.CifraSessionBean;
import invias.cifra.entity.ReporteArchivo;
import invias.cifra.entity.Usuario;
import invias.cifra.exception.CifraException;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import javax.faces.model.SelectItem;

/**
 *
 * @author jaflorez
 */
public final class CifraUtil {

    public static ArrayList<SelectItem> listaAnos() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        ArrayList<SelectItem> listaAno = new ArrayList<SelectItem>();

        for (int contador = 0; contador < 20; contador++) {
            SelectItem si;
            si = new SelectItem();
            si.setValue(year - 10 + contador);
            si.setLabel(Integer.toString(year - 10 + contador));
            listaAno.add(si);
        }
        return listaAno;
    }

    public static Usuario curUser() {
        return new Usuario(1);
    }

    public static Date fechaMasDias(Date fecha, int dias) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DATE, dias);  // number of days to add
        return c.getTime();
    }

    public static CifraSessionBean getSessionBean() {
        ExternalContext tmpEC;
        Map sMap;
        tmpEC = FacesContext.getCurrentInstance().getExternalContext();
        sMap = tmpEC.getSessionMap();
        CifraSessionBean cifraSes = (CifraSessionBean) sMap.get("cifraSessionBean");
        return cifraSes;
    }

    public static void redirectPage(String page) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/faces/" + page);
        } catch (IOException ex) {
            Logger.getLogger(CifraUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void redirectRootIn() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/faces/inicio/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CifraUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void redirectRootOut() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/faces/out/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CifraUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String GetMD5(String passwordToHash) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;

    }

    public static String getRamdomString(int numero) {
        String code = RandomStringUtils.randomAlphanumeric(numero);
        return code;
    }

    public static String getRequestURL() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
        return baseURL;
    }

    public static String pathToWriteTmpFile() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String s = context.getRealPath("/resources/tmp");
        return s;
    }

    public static String getNomFileFromDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return dateFormat.format(date);
    }

    public static String pathToResources() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String s = context.getRealPath("/resources");
        return s;
    }

    public static String getExtension(String fileName) {
        int dot = fileName.lastIndexOf(".");
        return fileName.substring(dot + 1);
    }

    public static BigDecimal getNumberFromText(String text) {
        String str = "";
        try {
            str = text.substring(text.indexOf("$") + 1);
            str = str.replace(",", "").trim();
            BigDecimal bd = new BigDecimal(str);
            return bd;
        } catch (NumberFormatException e) {
            Logger.getLogger(CifraUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static void redirectPage(String page, String title, String msg) throws CifraException {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Flash flash = facesContext.getExternalContext().getFlash();
            flash.setKeepMessages(true);
            flash.setRedirect(true);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/" + page);
        } catch (IOException ex) {
            Logger.getLogger(CifraUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new CifraException(ex.getMessage());
        }
    }

    public static String getNombreUsuario(int idTipo) {
        switch (idTipo) {
            case 1:
                return "Administrador";
            case 2:
                return "Gestor tecnico";
            case 3:
                return "Interventor";
            case 4:
                return "Supervisor";
            case 5:
                return "Financiero";
            case 6:
                return "Consulta";

            default:
                break;
        }
        return "";
    }

    public static String getNombreMes(int numMes) {
        String[] str = {"Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octobre",
            "Noviembre",
            "Diciembre"};
        String monthString;
        if(numMes < 1){
            monthString = "Invalid month";
        } else if (numMes <= str.length) {
            monthString = str[numMes - 1];
        } else {
            monthString = "Invalid month";
        }
        return monthString;

    }

    public static String getRealPathResource() {
        ExternalContext tmpEC;
        tmpEC = FacesContext.getCurrentInstance().getExternalContext();
        String realPath = tmpEC.getRealPath("/resources");
        return realPath;
    }

    public static Date parseDate(String date, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.parse(date);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(CifraUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String formatDate(Date fecha) {
        if (fecha != null) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            return df.format(fecha);

        }
        return "";
    }

    public static String formatDateMM(Date fecha) {
        if (fecha != null) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            return df.format(fecha);

        }
        return "";
    }

    public static String formatPesos(BigDecimal valor) {
        if (valor == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("#,###");
        return "$" + df.format(valor);
    }

    public static String formatNumero(BigDecimal valor) {
        if (valor == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(valor);
    }

    public static String formatNumeroTn(BigDecimal valor) {
        if (valor == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(valor);
    }

    public static String formatNumeroF(float valor) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(valor);
    }

    public static String formatBooleanSiNO(Boolean valor) {
        if (valor) {
            return "Si X No ";
        } else {
            return "Si  No X";
        }

    }

    public static String formatString(String valor) {
        if (valor == null) {
            return "";
        }
        return valor;

    }

    public static String formatInteger(Integer valor) {
        if (valor == null) {
            return "";
        }
        return valor.toString();

    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String imageToFile(ReporteArchivo ra) {
        //Copiar el archivo en file sistem
        String rutaFile = CifraUtil.pathToWriteTmpFile() + "/img" + ra.getIdentificador() + "." + ra.getExtension();
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(rutaFile))) {
            out.write(ra.getArchivo());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CifraUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CifraUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rutaFile;

    }

}

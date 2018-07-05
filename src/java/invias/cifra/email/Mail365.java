/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.email;

import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jaflorez
 */
public class Mail365 {

    private boolean busy;
    private String emailSend;
    private String emailPassword;
    private String mailSmtpHost;
    private String mailSmtpPort;
    private String mailSmtpSslTrust;
    public Mail365() throws Exception {
        busy = false;
        FacesContext ctx = FacesContext.getCurrentInstance();
        this.emailSend = ctx.getExternalContext().getInitParameter("emailSend");
        this.emailPassword = ctx.getExternalContext().getInitParameter("emailPassword");
        this.mailSmtpHost = ctx.getExternalContext().getInitParameter("mailSmtpHost");
        this.mailSmtpPort = ctx.getExternalContext().getInitParameter("mailSmtpPort");
        this.mailSmtpSslTrust = ctx.getExternalContext().getInitParameter("mailSmtpSslTrust");
        if (this.emailSend == null || this.emailPassword == null || this.mailSmtpHost == null
                || this.mailSmtpPort == null || this.mailSmtpSslTrust == null) {
            this.emailSend = "tramiteenlinea@invias.gov.co";
            this.emailPassword = "xgL8h00E1wk9";
            this.mailSmtpHost = "smtp.office365.com";
            this.mailSmtpPort = "587";
            this.mailSmtpSslTrust = "smtp.office365.com";
        }

    }

    public void sendMail(DatosCorreo correo) throws Exception {
        if (!busy) {
            busy = true;
        } else {
            return;
        }
        final String smtpAuthUserName = this.emailSend;
        final String smtpAuthPassword = this.emailPassword;

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpAuthUserName, smtpAuthPassword);
            }
        };
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", this.mailSmtpHost);
        properties.setProperty("mail.smtp.port", this.mailSmtpPort);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.trust", this.mailSmtpSslTrust);
        Session session = Session.getInstance(properties, authenticator);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.emailSend));
            //Asignar los destinatarios
            if (correo.getDestinatario() != null) {
                for (int i = 0; i < correo.getDestinatario().size(); i++) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress((String) correo.getDestinatario().get(i)));
                }
            }
            message.setSubject(correo.getAsunto());

            //message.setText(correo.getMensaje());
            message.setContent(correo.getMensaje(), "text/html");
            Transport.send(message);
        } catch (MessagingException exception) {
            exception.printStackTrace();
            throw new Exception(Mail365.class.getName() + ":" + exception.getMessage());
        }
        busy = false;
    }

}

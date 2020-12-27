package it.unical.demacs.inf.asd.ProgettoAgile8.utility;

import javax.mail.*;
import javax.mail.internet.*;


import java.util.*;

public class SendEmail {
    private static SendEmail i = null;
    final String username = "veterinary_clinic@libero.it";
    final String password = "Veterinary_clinic88";
    private SendEmail() {}

    public static SendEmail getInstance()
    {
        if(i==null)
            i=new SendEmail();
        return i;
    }

    public void sendMail(String oggetto, String testo, String destinatario) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.libero.it");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("veterinary_clinic@libero.it"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            );
            message.setSubject(oggetto + " - Veterinary Clinic");
            message.setText("Gentile " + "utente" + "," +
                    "\n\n "+testo+
                    "\n\n Cordiali saluti," +
                    "\n Il team di Veterinary Clinic ");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String randomString(int length){
        Random rand = new Random();
        StringBuffer tempStr = new StringBuffer();
        tempStr.append("");
        for (int i = 0; i < length; i++) {
            int c = rand.nextInt(122 - 48) + 48;
            if((c >= 58 && c <= 64) || (c >= 91 && c <= 96)){
                i--;
                continue;
            }
            tempStr.append((char)c);
        }
        return tempStr.toString();
    }

    public void sendMailDelete(String destinatario) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.libero.it");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("veterinary_clinic@libero.it"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            );
            message.setSubject("Annullamento prenotazione Veterinary Clinic");
            message.setText("Gentile " + "utente" + "," +
                    "\n\n La sua pronatazione è stata annullata." +
                    "\n\n Cordiali saluti," +
                    "\n Il team di Veterinary Clinic ");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

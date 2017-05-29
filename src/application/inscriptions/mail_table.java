/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application.inscriptions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Armand
 */
import static java.lang.reflect.Array.set;
import java.util.ArrayList;
import javax.mail.*;
import javax.activation.*;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashSet;
import java.util.Set;

public class mail_table {

    private ArrayList<String> destinataires = new ArrayList();
    private String sujet;
    private String contenu;
   
    static String email_hote = "m2lfloarmandgeorge@gmail.com";
    static String email_pass = "M2Lisworking";
    static Properties properties = System.getProperties();
    static String hote = "smtp.gmail.com";

    static {

        // set up des proprietees, invariable.
      
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
       

    }

    
   
    /**
     *cree un nouveau tableau de mail
     * @param destinataires liste des adresses email des destinataires
     * @param sujet le sujet du mail
     * @param contenu le contenu du message
     * 
     */
    
    public  mail_table(ArrayList<String> destinataires, String sujet, String contenu) {
        this.destinataires = destinataires;
        this.sujet = sujet;
        this.contenu = contenu;
    }
   
    /**
     *crée un tableau de mail vide
     */
    public mail_table(){
        this.sujet = "";
        this.contenu = "";
    }
    
    /**
     *crée un tableau de mail a partir des inscrit a une competition;
     * @param compet un object competition
     * @param sujet le sujetdu mail 
     * @param contenu le contenu du mail 
     */
    public mail_table(Competition compet, String sujet, String contenu){
        Set<Candidat>  aaa = compet.getCandidats();
        for(Candidat candidat: aaa){
            System.out.println(candidat.getNom());
        }
    }

    /**
     * envoie du /des mail(s)                                           
     */
    public void envoie() {
            // tentative d'envoi du message;
         try {

             //creation de la session et du mimemessage
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email_hote, email_pass);

                }
            };
            Session session = Session.getDefaultInstance(properties, authenticator);
            MimeMessage message = new MimeMessage(session);
            
            // set sujet et contenu, puis envoie le mail a chaque destinataire le mail
            message.setSubject(this.sujet);
            message.setText(this.contenu);
            for(String destinataire: this.destinataires){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinataire));
                 Transport.send(message);
                  System.out.println("message sent successfully to " + destinataire);
            }
            
            // Send message
            Transport.send(message);

        } catch (Exception mex) {
            System.out.println(mex.getMessage());
        }

    }

    // getter and setters
    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getHote() {
        return hote;
    }

    public void setHote(String hote) {
        this.hote = hote;
    }
    
  

}


package bt.user.model;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator{
   
   PasswordAuthentication pa;
   
   public MailAuth() {
       String mail_id = "fivingo@gmail.com";
       String mail_pw = "qmffn%RE1ngg";
       
       pa = new PasswordAuthentication(mail_id, mail_pw);
   }
   
   public PasswordAuthentication getPasswordAuthentication() {
       return pa;
   }
}


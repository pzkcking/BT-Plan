package bt.user.model;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator{
   
   PasswordAuthentication pa;
   
   public MailAuth() {
       String mail_id = "구글메일주소";
       String mail_pw = "비밀번호";
       
       pa = new PasswordAuthentication(mail_id, mail_pw);
   }
   
   public PasswordAuthentication getPasswordAuthentication() {
       return pa;
   }
}


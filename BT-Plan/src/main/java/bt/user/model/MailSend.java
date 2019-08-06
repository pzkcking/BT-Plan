package bt.user.model;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class MailSend {
    
    public void MailSend(String subject,String msgText,String Uuserid,String Uemail) {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
        
        Authenticator auth = new MailAuth();
        
        Session session = Session.getDefaultInstance(prop, auth);
        
        MimeMessage msg = new MimeMessage(session);
    
        try {
            msg.setSentDate(new Date());
            
            msg.setFrom(new InternetAddress("fivingo@gmail.com", "BT_Plan"));
            InternetAddress too = new InternetAddress("fivingo@gmail.com");         
            msg.setRecipient(Message.RecipientType.TO, too);            
            msg.setSubject("[BT-PLan 문의] "+subject, "UTF-8");
           // msg.setText("[문의한 아이디]"+Uuserid+"\n"+msgText, "UTF-8");
            //msg.setText("[문의한 아이디]"+Uuserid+"\n[Email]"+Uemail, "UTF-8");
            msg.setText("[문의한 아이디] "+Uuserid+"\n[Email] "+Uemail+"\n [본문] "+msgText, "UTF-8");            
            
            Transport.send(msg);
            
        } catch(AddressException ae) {            
            System.out.println("AddressException : " + ae.getMessage());           
        } catch(MessagingException me) {            
            System.out.println("MessagingException : " + me.getMessage());
        } catch(UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException : " + e.getMessage());			
        }
                
    }
}


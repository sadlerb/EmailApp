import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
* Sends a email to the specified recepients
* @author Brendon Sadler
* @version 0.8
*/

public class Email{

    private String userEmail;
    private String userPassword;
    private String receipent;
    private Properties props = new Properties();
    private Session session;
    private Message message;
    private String subject;
    private String body;
    private boolean isAttachment = false;
    private String filename;

    /**
     * Creates a email object and checks if the email and password are valid
     * @param userEmail Email the message is sent from
     * @param userPassword Password used to verify the given email
     */
    public Email(String userEmail, String userPassword){

        this.userEmail = userEmail;
        this.userPassword = userPassword;
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        session = Session.getInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(userEmail, userPassword);
          }
        });
        
    }
    /**
     * Sets the message header
     * @param header
     */
    public void setHeader(String header){
        this.subject = header;
    }

    /**
     * Sets the message body
     * @param body
     */
    public void setBody(String body){  
        this.body = body;
    }
    /**
     * Sets the recepients of the message
     * @param recipient
     */
    public void setRecipient(String recipient){

        this.receipent = recipient;
    }





    public void sendMessage(){
        if (isAttachment == false){
            try {

                message = new MimeMessage(session);
                message.setFrom(new InternetAddress(userEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipent));
                message.setSubject(subject);
                message.setText(body);
            
           
                System.out.println("Sending Message");
                Transport.send(message);

                 System.out.println("Message Sent");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }else{

            try {
                
                message = new MimeMessage(session);
                message.setFrom(new InternetAddress(userEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipent));
                message.setSubject(subject);
                BodyPart messageBodyPart = new MimeBodyPart();
                Multipart multipart = new MimeMultipart();
                messageBodyPart.setText(body);
                multipart.addBodyPart(messageBodyPart);
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                message.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
               
                System.out.println("Sending Message");
                Transport.send(message);
    
                System.out.println("Message Sent");
    
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addAttachment(String filename){

        isAttachment = true;
        this.filename = filename;
    }

}

    



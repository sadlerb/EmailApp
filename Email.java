import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email{

    private String userEmail;
    private String userPassword;
    private String receipent;
    private Properties props = new Properties();
    private Session session;
    private Message message;
    private String subject;
    private String body;


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

    public void setHeader(String header){
        this.subject = header;
    }

    public void setBody(String body){
        String string = " ";
        String[] array = body.split("\\|");
        for (int i = 0; i < array.length; i ++){
            string  += array[i] + "\n"; 
        }
        
        this.body = string;
    }

    
    public void setRecipient(String recipient){

        this.receipent = recipient;
    }





    public void sendMessage(){

        try {

            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipent));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Message Sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
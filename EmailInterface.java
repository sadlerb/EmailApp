
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
* Creates a GUI that accepts user input inorder to send a email with or without a attachment
* @author Brendon Sadler
* @version 0.8
*/

public class EmailInterface extends JPanel implements ActionListener{

    Email email;
    String s1[] = FileManager.getFileNames();
    Button b1 = new Button();
    Button b2 = new Button();
    JComboBox box;

    JTextField t1,t2,t3,t4;
    JTextArea ta;
    JLabel l1,l2,l3,l4,l5,l6;
    JPasswordField password = new JPasswordField();
    
    /** 
     * Creates a GUI that the user can interact with
    */
    public EmailInterface(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Email label and texfild
        l1 = new JLabel("Email:   ");
        t1 = new JTextField("");
        t1.setColumns(60);
        this.add(l1);
        this.add(t1);
        // Password label and textfield
        l2 = new JLabel("Password:  ");
        password.setColumns(60);
        this.add(l2);
        this.add(password);
        // TO label and textfield
        l3 = new JLabel(" TO:   ");
        t2 = new JTextField("");
        t2.setColumns(60);
        this.add(l3);
        
        //Dropdown Menu
        box = new JComboBox<>(s1);
        box.setEditable(false);
        box.setSelectedItem(null);
        box.addActionListener(this);
        this.add(box);
        this.add(t2);
        // Header label and textfield
        l4 = new JLabel("Header:  ");
        t3 = new JTextField(60);
        this.add(l4);
        this.add(t3);
        // Body label and textbox
        l5 = new JLabel(" Body: ");
        ta = new JTextArea();
        ta.setRows(15);
        ta.setColumns(60);
        this.add(l5);
        this.add(ta);
        // Attachment label and textfield
        l6 = new JLabel("Attachment");
        t4 = new JTextField(60);
        t4.setText("");
        this.add(l6);
        this.add(t4);
        // Send Button
        b1.setLabel("SEND");
        b1.setActionCommand("send");
        b1.addActionListener(this);
        this.add(b1);
        // Back button
        b2.setLabel("BACK");
        b2.setActionCommand("back");
        b2.addActionListener(this);
        JLabel pading = new JLabel("                                                                                                                                 ");
        this.add(pading);
        this.add(b2);
        
    }
    
    
    /** 
     * Manages the combo box 
     * Sends a email with the infomation collected in the GUI or sends the user back to the main screen if the back button is pressed
    */
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == box ){
            String fileName = (String) box.getSelectedItem();
            if (t2.getText().equals("")){
            t2.setText(t2.getText() + FileManager.getEmail(FileManager.getFile(fileName)));
            }else{
                t2.setText(t2.getText() + "," +FileManager.getEmail(FileManager.getFile(fileName)));
            }
            box.setSelectedItem(null);
        }


        //Creates a email object and sets recepients, header, body and attachemt if required
        if ("send".equals(e.getActionCommand())){
            String user = t1.getText();
            char[] pass = password.getPassword();
            String password = String.valueOf(pass);
            String recipient = t2.getText();
            String header = t3.getText();
            String body = ta.getText();
            String attachment = t4.getText();
            
            // Adds a attachment if one is specified
            email = new Email(user,password);
            if (!attachment.equals("")){
                attachment = attachment.replace("\\", "\\\\");
                System.out.println(attachment);
                email.addAttachment(attachment);
            }

            // Sets and sends the message
            email.setRecipient(recipient);
            email.setHeader(header);
            email.setBody(body);
            email.sendMessage();

            // Sets all text values to a empty string after the message is sent
            t1.setText("");
            t2.setText("");
            t3.setText("");
            ta.setText("");
            t4.setText("");
            this.password.setText("");
            



        }
        // Sends the user back to the main screen
        if ("back".equals(e.getActionCommand())){
            EmailApp.setMainFrame();
        }

    

    }
    
}
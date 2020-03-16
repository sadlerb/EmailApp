
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Interface extends JPanel implements ActionListener{

    Email email;
    Button b1 = new Button();

    JTextField t1,t2,t3,t4;
    JLabel l1,l2,l3,l4,l5;
    JPasswordField password = new JPasswordField();
    
    
    public Interface(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        l1 = new JLabel("Email:   ");
        t1 = new JTextField(" ");
        t1.setColumns(60);
        this.add(l1);
        this.add(t1);
        l2 = new JLabel("Password:  ");
        password.setColumns(60);
        this.add(l2);
        this.add(password);
        l3 = new JLabel(" TO:   ");
        t2 = new JTextField(" ");
        t2.setColumns(60);
        this.add(l3);
        this.add(t2);

        l4 = new JLabel("Header:  ");
        t3 = new JTextField(60);
        this.add(l4);
        this.add(t3);

        l5 = new JLabel(" Body: ");
        t4 = new JTextField(60);
        t4.setSize(50,300);
        this.add(l5);
        this.add(t4);

        b1.setLabel("SEND");
        b1.setActionCommand("send");
        b1.addActionListener(this);
        this.add(b1);


        
    }
    
    
    
    public void actionPerformed(ActionEvent e) {
        if ("send".equals(e.getActionCommand())){
            String user = t1.getText();
            char[] pass = password.getPassword();
            String password = String.valueOf(pass);
            String recipient = t2.getText();
            String header = t3.getText();
            String body = t4.getText();

            email = new Email(user,password);

            email.setRecipient(recipient);
            email.setHeader(header);
            email.setBody(body);
            email.sendMessage();



        }

    }
    
}
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FileInterface extends JPanel implements ActionListener{

    JTextField field1,field2;
    Button b1,b2;
    JLabel l1,l2;

    public FileInterface(){

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        l1 = new JLabel("Name ");
        field1 = new JTextField("");
        l2 = new JLabel("Email: ");
        field2 = new JTextField("");
        field1.setColumns(60);
        field2.setColumns(60);
        b1 = new Button("Submit");
        b1.addActionListener(this);
        b1.setActionCommand("submit");
        b2 = new Button("Back");
        b2.addActionListener(this);
        b2.setActionCommand("back");


        
        this.add(l1);
        this.add(field1);
        this.add(l2);
        this.add(field2);
        this.add(b1);
        this.add(b2);
        
    }


    
















    public void actionPerformed(ActionEvent e) {
        if("submit".equals(e.getActionCommand())){

           String name = field1.getText();
           String email = field2.getText();
           new FileManager(name, email);

           field1.setText("");
           field2.setText("");
        }

        if("back".equals(e.getActionCommand())){

            EmailApp.setMainFrame();
        }

    }


    
}
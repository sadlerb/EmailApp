import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.GridLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
*Creates a window of Specified height and width that serves as the main hub for the app.
* @author Brendon Sadler
* @version 0.8
*/
public class EmailApp implements ActionListener {

    public static int WIDTH = 600;
    public static int HEIGHT = 600;
    public static JPanel panel = new JPanel();
    Button b1,b2;
    public static JFrame frame = new JFrame("Email App");
    
    /** Creates a window near the center of the screen and gives the
     * user a choice as to which part of the program to use
     */
    public EmailApp() {
    
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Email App");
        frame.setResizable(false);
        panel.setLayout(new GridLayout(2,2));
        b1 = new Button("Email");
        b2 = new Button("Files");
        b1.setActionCommand("email");
        b2.setActionCommand("file");
        b1.addActionListener(this);
        b2.addActionListener(this);
        panel.add(b1);
        panel.add(b2);
        frame.add(panel);
        
        frame.setVisible(true);
    }

    /**
     * Creates a instance of the app
     * @param args unused  
     * @return nothig  
     */
    public static void main(String[] args) {

        new EmailApp();

    }
    /** Sends the user to the appropriate screen based on their input
     * @param e Action Event
     * @return nothing
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Sends the user to the Email Inteface
        if("email".equals(e.getActionCommand())){
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            frame.add(new EmailInterface());
            frame.getContentPane().revalidate();
            
            
        }
        // Sends the user to the File Interface
        if("file".equals(e.getActionCommand())){
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            frame.add(new FileInterface());
            frame.getContentPane().revalidate();
            
            
        }




        

    }
    /** Sets the current screen of the app to the main screen
     * @return nothing
     */
    public static void  setMainFrame(){
        frame.getContentPane().removeAll();
        frame.getContentPane().invalidate();
        frame.add(panel);
        frame.getContentPane().revalidate();

    }
    

    
}
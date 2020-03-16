import javax.swing.JFrame;


public class EmailApp extends JFrame{

    public static int WIDTH = 600;
    public static int HEIGHT = 600;


    public EmailApp (){
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Email App");
        this.setResizable(false);
        this.add(new Interface());
        this.setVisible(true);
    }



    public static void main(String[] args) {

        new EmailApp();

    } 
    
}
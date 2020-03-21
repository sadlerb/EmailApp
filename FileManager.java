import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;




public class FileManager{

    public FileManager(String filename, String email){
    try{
        FileWriter fw = new FileWriter("C:\\Users\\xmort\\Projects\\Mail\\email\\" + filename + ".txt");
        fw.write(email);
        fw.close();

    }catch(IOException e){
        System.out.println("Error");
    }

        

    }


    public static File getFile(String fileName){

        File[] files = getFiles();
        File file = null;

        for (int i = 0; i < files.length;i++){

            if(files[i].getName().equals(fileName)){

                file = files[i];
                

            }
        }
        return file;
        


    }

    public static File[] getFiles(){
        File folder = new File("C:\\Users\\xmort\\Projects\\Mail\\email");
        File[] files = folder.listFiles();
        
        
        return files;


    }

    public static String[] getFileNames(){
        File[] files = getFiles();
        String string ="";
        String[] arr;

        for (int i = 0; i < files.length; i++){

            string += files[i].getName() + "|";

        }

        arr = string.split("\\|");

        return arr;



    }


    public static String getEmail(File file){
        String email = "";
        Path path = file.toPath();
        try {
            email = Files.readString(path, StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
        }



        return email;

    }


    






}
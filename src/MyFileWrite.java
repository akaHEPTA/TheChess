import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Extra challenge feature: File writer class
 */
public class MyFileWrite {
    // Field
    private PrintWriter myPW;
    private int counter = 1;
    private File newFile;
    private static final String newFileName = "./log/temp.txt";
    private SimpleDateFormat time;

    public MyFileWrite() throws IOException {
        setFile();
        initializeObjects();
    }

    private void initializeObjects() throws IOException {
        myPW = new PrintWriter(new BufferedWriter(new FileWriter(newFileName)));
        time = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

        myPW.println(time.format(new Date(System.currentTimeMillis())));
    }

    private void setFile() {
        newFile = new File(newFileName);
        try {
            if (newFile.exists()) {
                newFile.delete();
                System.out.println("[!] File exist");
            } else {
                System.out.println("[!] File not exist");
            }
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("[!] Log module is offline");
        }
    }

    public void recordMove(String move) {
        myPW.println((counter++) + ". " + move);
    }

    public void endRecord(){
        newFile.renameTo(new File("./log/" + time.format(new Date())));
        myPW.close();
    }


}

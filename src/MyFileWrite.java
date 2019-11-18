import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        myPW.println(time.format(new Date(System.currentTimeMillis())));
    }

    private void setFile() {
        newFile = new File(newFileName);
        try {
            if (newFile.exists()) newFile.delete();
            if (newFile.createNewFile()) System.out.println("[!] Log file created");
        } catch (IOException e) {
            System.out.println("[!] Log module is offline");
        }
    }

    private void initializeObjects() throws IOException {
        myPW = new PrintWriter(new BufferedWriter(new FileWriter(newFileName)));
        time = new SimpleDateFormat("MM/dd/yyyy_hh:mm:ss");
    }

    public void recordMove(String move) {
        myPW.println((counter++) + ". " + move);
    }

    public void endRecord() {
        myPW.close();
        String tempString = time.format(new Date()) + ".txt";

        File newFile = new File(tempString);
        try {
            Files.move(newFile.toPath(), newFile.toPath());
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}

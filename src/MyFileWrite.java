import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Extra challenge feature: File writer class
 */
public class MyFileWrite {
    // Field
    private PrintWriter myPW;
    private int counter = 0;
    private File newFile;
    private SimpleDateFormat date;
    private SimpleDateFormat time;

    public MyFileWrite() throws IOException {
        setFile();
        initializeObjects();
        myPW.println(date.format(date) + " " + date.format(time));
    }

    private void initializeObjects() {
        date = new SimpleDateFormat("MM/dd/yyyy");
        time = new SimpleDateFormat("hh:mm:ss a");
    }

    private void setFile() throws IOException {
        newFile = new File("/log/temp.txt");
        myPW = new PrintWriter(new BufferedWriter(new FileWriter("/log/temp.txt")));
    }

    private void recordMove(String move) {
        myPW.println((counter++) + ". " + move);
    }

    private void endRecord() throws IOException {
        Date today = new Date();
        File temp = new File("/log/" + date.format(today) + "_" + time.format(today));
        newFile.renameTo(temp);
        myPW.close();
    }


}

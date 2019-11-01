import java.util.Scanner;

public class InputCollector {
    // Fields
    private Scanner myScan;

    // Constructor
    public InputCollector() {
        setObjects();
        System.out.println("[!] Input collector module online");
    }

    // Methods
    private void setObjects() {
        myScan = new Scanner(System.in);
    }

    public String getLine() {
        String temp;
        while (true) {
            try {
                temp = myScan.nextLine().replaceAll("\\s", "");
                if (temp.equals('\n')) throw new Exception();
                else break;
            } catch (Exception e) {
                System.out.println("[!] Invalid value");
            }
        }
        return temp;
    }

}

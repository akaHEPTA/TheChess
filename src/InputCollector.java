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
    String result;
    while (true) {
      try {
        String temp = myScan.nextLine().replaceAll("\\s", "");
        if (temp.isEmpty()) throw new Exception("Empty input data");
        else {
          result = temp;
          break;
        }
      } catch (Exception e) {
        System.out.println("[!] Invalid value : " + e.getMessage());
        myScan = new Scanner(System.in);
      }
    }
    return result;
  }

  //  public int getInt() {
  //    int result;
  //    while (true) {
  //      try {
  //        result = myScan.nextInt();
  //        System.out.println(myScan.hasNext());
  //        break;
  //      } catch (Exception e) {
  //        System.out.println("[!] Invalid value : " + e.getMessage());
  //        myScan = new Scanner(System.in);
  //      }
  //    }
  //    return result;
  //  }
}

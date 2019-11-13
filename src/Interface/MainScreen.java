package Interface;

import javax.swing.*;
import java.awt.*;

public class MainScreen {
    public MainScreen(){
        JFrame windowGame = new JFrame("Chess Game");
        windowGame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        windowGame.setResizable(false);
        windowGame.setSize(500,500);
        windowGame.setVisible(true);

        JTextPane p = new JTextPane();

        Container topConteiner = Box.createVerticalBox();
        topConteiner.add(p);


        p.setText("texto");

        windowGame.add(topConteiner);

    }

    public static void main( String[] args ) {
        new MainScreen();

    }
}

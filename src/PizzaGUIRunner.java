import javax.swing.*;
import java.awt.*;

public class PizzaGUIRunner {
    public static void main(String[] args) {
        PizzaGUIFrame frame = new PizzaGUIFrame();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int height = screenSize.height;
        int width = screenSize.width;
        frame.setSize(width/4, height/2);
        frame.setLocation(width / 4, height / 20);

        frame.setTitle("Pizza Order Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
import java.awt.*;
import javax.swing.*;
public class QuacksFrame extends JFrame{
    private final int WIDTH = 1600;
    private final int HEIGHT = 960;
    public QuacksFrame(String framename) {
        super(framename);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        add(new QuacksExtraPanel());
        add(new QuacksBrewPanel());
        setVisible(true);
    }
}

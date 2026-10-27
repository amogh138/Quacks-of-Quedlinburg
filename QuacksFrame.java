import java.awt.*;
import javax.swing.*;
public class QuacksFrame {
    private final int WIDTH;
    private final int HEIGHT;
    public QuacksFrame(String framename) {
        super(framename);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        add(new QuacksExtraPanel());
        add(new QuacksBrewPanel());
        setVisible(true);
    }
}

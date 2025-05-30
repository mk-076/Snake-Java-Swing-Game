import java.awt.Dimension;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame {
    // Customizable
    final static String TITLE = "Snake";
    final Dimension SIZE = new Dimension(800, 800);
    final int LINES = 24;

    public SnakeFrame() {
        super(TITLE);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setPreferredSize(SIZE);
        add(new SnakePanel(LINES));
        pack();
        setVisible(true);
    }
}

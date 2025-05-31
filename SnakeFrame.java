import javax.swing.JFrame;

public class SnakeFrame extends JFrame {
    // Customizable
    final static String TITLE = "Snake";
    final int LINES = 20;
    final int TILE_SIZE = 20;
    final int PANEL_SIZE = LINES * TILE_SIZE;

    public SnakeFrame() {
        super(TITLE);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        add(new SnakePanel(LINES, TILE_SIZE));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

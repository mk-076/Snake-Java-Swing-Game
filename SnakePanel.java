import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener {
    int lines;

    // Snake Head
    Tile snakeHead;

    // Movement
    int yVelocity = 0;
    int xVelocity = 0;

    Timer timer;

    public SnakePanel(int lines) {
        super();

        setBackground(Color.BLACK);
        this.lines = lines;

        snakeHead = new Tile(0, 0);
        Timer timer = new Timer(200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                move();
                repaint();
            }

        });

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow(true);

        timer.start();
    }

    public void move() {
        int x = snakeHead.getX();
        int y = snakeHead.getY();

        snakeHead.setPosition(x + xVelocity, y + yVelocity);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tileHeight = (int) (getHeight() / lines);
        int tileWidth = (int) (getWidth() / lines);

        // Draw Snake Head
        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileWidth, snakeHead.y * tileHeight, tileWidth, tileHeight);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                if (xVelocity == 0) {
                    xVelocity = -1;
                    yVelocity = 0;
                }
                break;

            case KeyEvent.VK_D:
                if (xVelocity == 0) {
                    xVelocity = 1;
                    yVelocity = 0;
                }
                break;

            case KeyEvent.VK_W:
                if (yVelocity == 0) {
                    xVelocity = 0;
                    yVelocity = -1;
                }
                break;

            case KeyEvent.VK_S:
                if (yVelocity == 0) {
                    xVelocity = 0;
                    yVelocity = 1;
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

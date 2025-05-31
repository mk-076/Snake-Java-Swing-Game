import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Dimension;

public class SnakePanel extends JPanel implements KeyListener {
    int lines;
    int tileSize;

    // Snake Head
    Tile snakeHead;

    // Snake Body
    ArrayList<Tile> snakeBody;

    // Apple
    Tile apple;

    // Movement
    int bufferXVelocity = 0;
    int bufferYVelocity = 0;
    int yVelocity = 0;
    int xVelocity = 0;

    boolean isGameOver = false;

    Timer timer;

    public SnakePanel(int lines, int tileSize) {
        super();

        setPreferredSize(new Dimension(lines * tileSize, lines * tileSize));
        setBackground(Color.BLACK);
        
        this.lines = lines;
        this.tileSize = tileSize;

        SwingUtilities.invokeLater(() -> {
            apple = new Tile(5, 5);
            placeApple();
        });

        snakeHead = new Tile(0, 0);
        snakeBody = new ArrayList<Tile>();

        timer = new Timer(200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isColliding(snakeHead, apple)) {
                    snakeBody.add(new Tile(apple.x, apple.y));
                    placeApple();
                }

                if (isOutOfBounds(snakeHead)) {
                    isGameOver = true;
                }

                

                if (!isGameOver) {
                    move();
                    repaint();
                }

                for (Tile bodyPart : snakeBody) {
                    if (isColliding(snakeHead, bodyPart)) {
                        isGameOver = true;
                    }
                }
            }

        });

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow(true);

        timer.start();
    }

    public void move() {
        bufferXVelocity = xVelocity;
        bufferYVelocity = yVelocity;

        int x = snakeHead.getX();
        int y = snakeHead.getY();

        snakeHead.setPosition(x + xVelocity, y + yVelocity);
    }

    public void placeApple() {
        int x = (int) (Math.random() * lines);
        int y = (int) (Math.random() * lines);

        apple.setPosition(x, y);
    }

    public boolean isColliding(Tile t1, Tile t2) {
        return (t1.x == t2.x && t1.y == t2.y);
    }

    public boolean isOutOfBounds(Tile t) {
        return (t.x < 0 || t.x > lines || t.y < 0 || t.y > lines);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw Apple
        g.setColor(Color.RED);
        g.fillRect(apple.x * tileSize, apple.y * tileSize, tileSize, tileSize);

        // Draw Snake Head
        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);

        // Draw Snake Body
        for (int i = snakeBody.size() - 1; i > -1; i--) {
            Tile bodyPart = snakeBody.get(i);

            g.fillRect(bodyPart.x * tileSize, bodyPart.y * tileSize, tileSize, tileSize);

            if (i != 0) {
                Tile previousBodyPart = snakeBody.get(i - 1);

                bodyPart.x = previousBodyPart.x;
                bodyPart.y = previousBodyPart.y;
            } else {
                bodyPart.x = snakeHead.x;
                bodyPart.y = snakeHead.y;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                if (bufferXVelocity == 0) {
                    xVelocity = -1;
                    yVelocity = 0;
                }
                break;

            case KeyEvent.VK_D:
                if (bufferXVelocity == 0) {
                    xVelocity = 1;
                    yVelocity = 0;
                }
                break;

            case KeyEvent.VK_W:
                if (bufferYVelocity == 0) {
                    xVelocity = 0;
                    yVelocity = -1;
                }
                break;

            case KeyEvent.VK_S:
                if (bufferYVelocity == 0) {
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

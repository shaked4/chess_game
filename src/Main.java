import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    static Board board = new Board();

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 800, 830);
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (white) {
                            g.setColor(new Color(240, 217, 181));
                        } else {
                            g.setColor(new Color(181, 136, 98));
                        }
                        g.fillRect(x * 100, y * 100, 100, 100);
                        white = !white;
                    }
                    white = !white;
                }
                try {
                    for (Piece piece : board.pieces) {
                        if (piece == null) {
                            continue;
                        }
                        g.drawImage(piece.getImage(), piece.square.getX() * 100, piece.square.getY() * 100, null);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                for (Square square : board.circles) {
                    g.setColor(new Color(130, 150, 105));
                    g.fillOval(square.getX() * 100 + 33, square.getY() * 100 + 33, 34, 34);
                }
            }
        };

        frame.add(pn);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // When mouse clicked, remove all previous circles from the board
                board.removeAllCircles();
                int x = e.getX()-7;
                int y = e.getY() - 30;
                x = x / 100;
                y = y / 100;
                ArrayList<Square> possibleSquares = new ArrayList<>();
                for (Piece piece :
                        board.pieces) {
                    if (piece.square.getX() == x && piece.square.getY() == y) {
                        possibleSquares = piece.getPossibleSquares(board.pieces);
                        break;
                    }
                }

                if (!possibleSquares.isEmpty()) {
                    for (Square square : possibleSquares) {
                        board.addCircle(square);
                    }
                }
                pn.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
}


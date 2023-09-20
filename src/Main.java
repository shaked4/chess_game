import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

                for (Square square : board.greenCircles) {
                    g.setColor(new Color(130, 150, 105));
                    g.fillOval(square.getX() * 100 + 33, square.getY() * 100 + 33, 34, 34);
                }
            }
        };

        frame.add(pn);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        pn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // When mouse clicked, remove all previous circles from the board
                ArrayList<Square> possibleSquares = new ArrayList<>();
                boolean t=true;
                for (Piece piece :
                        board.pieces) {
                    if (piece.square.getX() == (e.getX() / 100) && piece.square.getY() == (e.getY() / 100)) {
                        for (Square square :
                                board.greenCircles) {
                            if (square.getX() == (e.getX() / 100) && square.getY() == (e.getY() / 100)) {
                                board.lastPieceClicked.setSquare(square.getX(), square.getY());
                                board.pieces.remove(piece);
                                t=false;
                                break;
                            }
                        }
                        if (t==true) {
                            board.lastPieceClicked = piece;
                            possibleSquares = piece.getPossibleSquares(board.pieces);
                        }
                        board.greenCircles.clear();
                        break;

                    }
                }

//                    System.out.println(e.getX() + e.getY());

                    if (!possibleSquares.isEmpty()) {
                        board.greenCircles.addAll(possibleSquares);

                    }


                    else {
                        for (Square square :
                                board.greenCircles) {
                            if (square.getX() == (e.getX() / 100) && square.getY() == (e.getY() / 100)) {
                                board.lastPieceClicked.setSquare(square.getX(), square.getY());
                                board.greenCircles.clear();
                                break;
                            }
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


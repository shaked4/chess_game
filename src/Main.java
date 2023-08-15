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

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 800, 830);
        Piece[] pieces = new Piece[32];
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
                BufferedImage bufferedImage;
                try {

                    pieces[0] = new Pawn(0, 6, Piece.COLOR.WHITE);
                    bufferedImage = ImageIO.read(new File(pieces[0].getImagePath()));
                    g.drawImage(bufferedImage, 0, 600, null);

                    pieces[1] = new Knight(1, 0, Piece.COLOR.BLACK);
                    bufferedImage = ImageIO.read(new File(pieces[1].getImagePath()));
                    g.drawImage(bufferedImage, 100, 0, null);

                    pieces[2] = new Pawn(0, 4, Piece.COLOR.WHITE);
                    bufferedImage = ImageIO.read(new File(pieces[2].getImagePath()));
                    g.drawImage(bufferedImage, 0, 400, null);

                    pieces[3] = new Knight(1, 5, Piece.COLOR.BLACK);
                    bufferedImage = ImageIO.read(new File(pieces[3].getImagePath()));
                    g.drawImage(bufferedImage, 100, 500, null);

                    pieces[4] = new Pawn(5, 6, Piece.COLOR.WHITE);
                    bufferedImage = ImageIO.read(new File(pieces[4].getImagePath()));
                    g.drawImage(bufferedImage, 500, 600, null);

                    pieces[5] = new Knight(6, 5, Piece.COLOR.BLACK);
                    bufferedImage = ImageIO.read(new File(pieces[5].getImagePath()));
                    g.drawImage(bufferedImage, 600, 500, null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        frame.add(pn);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int x = e.getX();
                int y = e.getY() - 40;
                System.out.printf("x: %d, y: %d%n", x, y);
                System.out.println("cell is: " + "x:" + x / 100 + " y:" + y / 100);
                x = x / 100;
                y = y / 100;
                ArrayList<Square> possibleSquares = new ArrayList<>();
                for (int i = 0; i < 32; i++) {
                    if (pieces[i] != null && pieces[i].square.getX() == x && pieces[i].square.getY() == y) {
                        possibleSquares = pieces[i].getPossibleSquares(pieces);
                        System.out.println("in on white pawn");
                        break;
                    }
                }
                if (!possibleSquares.isEmpty()) {

                    System.out.println("in on possible squares");
                    for (Square square : possibleSquares) {
                        System.out.println("in possible loop");
                        BufferedImage bufferedImage;
                        try {
                            bufferedImage = ImageIO.read(new File("img/green_circle.png"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                        System.out.println(y * 100);
                        pn.getGraphics().drawImage(image, square.getX() * 100 + 20, square.getY() * 100, null);
                    }

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
}


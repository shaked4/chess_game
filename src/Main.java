import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


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
                    bufferedImage = ImageIO.read(new File("white_pawn2.png"));
                    pieces[0] = new Pawn(0, 6, Piece.COLOR.WHITE);
                    g.drawImage(bufferedImage, 10, 610, null);

                    bufferedImage = ImageIO.read(new File("black_knight05.png"));
                    pieces[1] = new Knight(1, 0, Piece.COLOR.BLACK);
                    g.drawImage(bufferedImage, 100, 0, null);

                    bufferedImage = ImageIO.read(new File("white_pawn2.png"));
                    pieces[2] = new Knight(0, 4, Piece.COLOR.WHITE);
                    g.drawImage(bufferedImage, 0, 410, null);

                    bufferedImage = ImageIO.read(new File("black_knight05.png"));
                    pieces[3] = new Knight(1, 5, Piece.COLOR.BLACK);
                    g.drawImage(bufferedImage, 100, 510, null);

                    bufferedImage = ImageIO.read(new File("white_pawn2.png"));
                    pieces[4] = new Pawn(5, 6, Piece.COLOR.WHITE);
                    g.drawImage(bufferedImage, 510, 610, null);

                    bufferedImage = ImageIO.read(new File("black_knight05.png"));
                    pieces[5] = new Knight(6, 5, Piece.COLOR.BLACK);
                    g.drawImage(bufferedImage, 610, 510, null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        frame.add(pn);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("hello");
                if ((e.getX() > 0 && e.getX() < 100) && (e.getY() > 600 && e.getY() < 700)) {
                    System.out.println("working");
                }
                System.out.println("cell is: " + "x:" + e.getX() / 100 + " y:" + e.getY() / 100);
                int x = e.getX() / 100;
                int y = e.getY() / 100;
                ArrayList<Square> PossibleSquares = new ArrayList<>();
                for (int i = 0; i < 32; i++) {
                    if (pieces[i] != null && pieces[i].square.getX() == x && pieces[i].square.getY() == y) {
                        PossibleSquares = pieces[i].getPossibleSquares(pieces);
                        System.out.println("in on white pawn");
                        break;
                    }
                }
                if (!PossibleSquares.isEmpty()) {

                    System.out.println("in on possible squares");
                    for (int i = 0; i < PossibleSquares.size(); i++) {
                        System.out.println("in possible loop");
                        BufferedImage bufferedImage = null;
                        try {
                            bufferedImage = ImageIO.read(new File("green_circle.png"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                        System.out.println(y * 100);
                        pn.getGraphics().drawImage(image, PossibleSquares.get(i).getX() * 100 + 20, PossibleSquares.get(i).getY() * 100, null);
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


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
                //the first case is that the player ate a piece which means the square he clicked contains both a piece and green circle
                ArrayList<Square> possibleSquares = new ArrayList<>();
                boolean t = true;
                for (Piece piece :
                        board.pieces) {
                    if (( (board.lastPieceClicked==null) || (piece.color!=board.lastPieceClickedColor ) )  &&  piece.square.getX() == (e.getX() / 100) && piece.square.getY() == (e.getY() / 100)) {
                        for (Square square :
                                board.greenCircles) {
                            if (square.getX() == (e.getX() / 100) && square.getY() == (e.getY() / 100)) {
                                board.lastPieceClicked.setSquare(square.getX(), square.getY());
                                board.pieces.remove(piece);
                                t = false;
                                if (board.lastPieceClicked.getColor()== Piece.COLOR.BLACK){
                                    if (board.isInCheck(board.whiteKing))
                                        System.out.println("the king is in risk!!!!!");
                                    if(board.isItCheckmate(board.whiteKing))
                                        System.out.println("the king is dead give up");
                                }

                                else {
                                    if (board.isInCheck(board.blackKing))
                                        System.out.println("the king is in risk!!!!!");
                                    if(board.isItCheckmate(board.blackKing))
                                        System.out.println("the king is dead give up");
                                }
                                break;

                            }
                        }
                        if (t) {
                            board.lastPieceClicked = piece;
                            possibleSquares = piece.getPossibleSquares(board);

                        }
                        board.greenCircles.clear();
                        break;

                    }
                }

//                    System.out.println(e.getX() + e.getY());

                if (!possibleSquares.isEmpty()) {
                    board.greenCircles.addAll(possibleSquares);
                }
                ///this one is for the case that the player clicked green circle of the last piece  clicked
                else {
                    for (Square square :
                            board.greenCircles) {
                        if (square.getX() == (e.getX() / 100) && square.getY() == (e.getY() / 100)) {
                            if (board.isThereAnOppositePiece(square, board.lastPieceClicked))
                                board.pieces.remove(board.getThisPiece(square));

                            board.lastPieceClicked.setSquare(square.getX(), square.getY());
                            board.greenCircles.clear();
                            board.setLastPieceClickedColor(board.lastPieceClicked.color);
                            //check if its pawn and if so, check if he moved and if he didn't so mark 'moved' so he cant do 2 steps anymore
                            if(board.lastPieceClicked.type== Piece.TYPE.PAWN || board.lastPieceClicked.type==Piece.TYPE.KING)
                                board.lastPieceClicked.onMoved();

                            //check for check after a piece moved
                            if (board.lastPieceClicked.getColor()== Piece.COLOR.BLACK){
                                if (board.isInCheck(board.whiteKing))
                                    System.out.println("the king is in risk!!!!!");
                                if(board.isItCheckmate(board.whiteKing))
                                    System.out.println("the king is dead give up");
                            }

                            else {
                                  if (board.isInCheck(board.blackKing))
                                    System.out.println("the king is in risk!!!!!");
                                if(board.isItCheckmate(board.blackKing))
                                    System.out.println("the king is dead give up");
                            }

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


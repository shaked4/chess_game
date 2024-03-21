import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(int x, int y, COLOR color) {
        super(TYPE.QUEEN, x, y, color);
    }


    @Override
    public ArrayList<Square> getPossibleSquaresForPiece(Board board) {


        ArrayList<Square> options = new ArrayList<>();


        int x = square.x;
        int y = square.y;
        int[][] nextMove = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, -1}, {1, 1}, {-1, 1}, {-1, -1}};
        for (int[] ints : nextMove) {
            square.x = x;
            square.y = y;
            Square nextSquare = new Square(square.x + ints[0], square.y + ints[1]);
            while (!isSquareTaken(nextSquare, board.pieces) && isInBoundaries(square, ints[0], ints[1])) {
                options.add(nextSquare);
                square.x = square.x + ints[0];
                square.y = square.y + ints[1];
                nextSquare = new Square(square.x + ints[0], square.y + ints[1]);
            }
            if (getPiece(nextSquare, board.pieces) != null && getPiece(nextSquare, board.pieces).color != this.color)
                options.add(nextSquare);

        }
        square.x = x;
        square.y = y;
                                                     
        return options;
    }

    @Override
    BufferedImage getImage() throws IOException {
        String path = color == COLOR.WHITE ? "img/white_queen.png" : "img/blackQueen.png";
        return ImageIO.read(new File(path));
    }
}

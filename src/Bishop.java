import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(int x, int y, COLOR color) {
        super(TYPE.BISHOP, x, y, color);
    }

    @Override
    public ArrayList<Square> getPossibleSquaresForPiece(Board board) {
        ArrayList<Square> options = new ArrayList<>();
        int x = square.x;
        int y = square.y;
        int[][] nextMove = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};
        for (int[] ints : nextMove) {
            square.x = x;
            square.y = y;
            Square nextSquare = new Square(square.x + ints[0], square.y + ints[1]);
            //!board.isInCheckDiffPosition(this,ints[0],ints[1] )
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
    public ArrayList<Square> getStandingPossibleSquares(Board board) {
        ArrayList<Square> options = new ArrayList<>();
        int x = square.x;
        int y = square.y;
        int[][] nextMove = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};
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
        String path = color == COLOR.WHITE ? "img/white_bishop.png" : "";
        return ImageIO.read(new File(path));
    }
}

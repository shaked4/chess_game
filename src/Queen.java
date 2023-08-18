import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(int x, int y, COLOR color) {
        super(TYPE.QUEEN, x, y, color);
    }


    @Override
    public ArrayList<Square> getPossibleSquares(List<Piece> t) {


        ArrayList<Square> options = new ArrayList<>();


        int x = square.x;
        int y = square.y;
        int[][] nextMove = {{1, 0}, {0, 1}, {-1, 0}, {0, -1},{1,-1},{1,1},{-1,1},{-1,-1}};
        for (int[] ints : nextMove) {
            square.x = x;
            square.y = y;
            Square nextSquare = new Square(square.x + ints[0], square.y + ints[1]);
            while (!isSquareTaken(nextSquare, t) && isInBoundaries(square, ints[0], ints[1])) {
                options.add(nextSquare);
                square.x = square.x + ints[0];
                square.y = square.y + ints[1];
                nextSquare = new Square(square.x + ints[0], square.y + ints[1]);
            }
            if (getPiece(nextSquare, t) != null && getPiece(nextSquare, t).color != this.color)
                options.add(nextSquare);

        }
        square.x = x;
        square.y = y;

        return options;
    }

    @Override
    BufferedImage getImage() throws IOException {
        String path = color == COLOR.WHITE ? "img/white_queen.png" : "";
        return ImageIO.read(new File(path));
    }
}

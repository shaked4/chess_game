import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    boolean moved = false;

    public King(int x, int y, COLOR color) {
        super(TYPE.KING, x, y, color);
    }

    public void onMoved() {
        super.onMoved();
        moved = true;
    }

    @Override
    public ArrayList<Square> getPossibleSquares(Board board) {

        ArrayList<Square> options = new ArrayList<>();


        int x = square.x;
        int y = square.y;
        int[][] nextMove = {{1, 0}, {0, 1}, {-1, 0}, {0, -1},{1,-1},{1,1},{-1,1},{-1,-1}};
        for (int[] ints : nextMove) {
            square.x = x;
            square.y = y;
            Square nextSquare = new Square(square.x + ints[0], square.y + ints[1]);
            if (!isSquareTaken(nextSquare, board.pieces) && isInBoundaries(square, ints[0], ints[1])) {
                options.add(nextSquare);
            }
            else if (getPiece(nextSquare, board.pieces) != null && getPiece(nextSquare, board.pieces).color != this.color)
                options.add(nextSquare);

        }
        square.x = x;
        square.y = y;

        return options;



    }

    @Override
    BufferedImage getImage() throws IOException {

            String path = color == COLOR.WHITE ? "img/white_king.png" : "img/black_king.png";
            return ImageIO.read(new File(path));

    }
}

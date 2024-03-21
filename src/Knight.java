import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Knight extends Piece {


    public Knight(int x, int y, COLOR color) {
        super(TYPE.KNIGHT, x, y, color);
    }

    @Override
    public ArrayList<Square> getPossibleSquaresForPiece(Board board) {
        ArrayList<Square> options = new ArrayList<>();

        int[][] temp = {{-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}};

        for (int i = 0; i < temp.length; i++) {
            Square tempMove = new Square(square.getX() + temp[i][0], square.getY() + temp[i][1]);
            if (!isSquareTakenSameColor(tempMove, board.pieces, color) && isInBoundaries(square, temp[i][0], temp[i][1]))
                options.add(tempMove);

        }


        return options;
    }

    @Override
    BufferedImage getImage() throws IOException {
        String path = color == COLOR.WHITE ? "img/whiteknight.png" : "img/black_knight.png";
        return ImageIO.read(new File(path));
    }
}

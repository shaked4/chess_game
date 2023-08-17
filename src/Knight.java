import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {


    public Knight(int x, int y, COLOR color) {
        super(TYPE.KNIGHT, x, y, color);
    }

    @Override
    public ArrayList<Square> getPossibleSquares(List<Piece> pieces) {
        ArrayList<Square> options = new ArrayList<>();

        Square tempMove=new Square(square.getX()-1, square.getY()-2);
        if (!isSquareTakenSameColor(tempMove,pieces,color))

        return new ArrayList<Square>();
    }

    @Override
    BufferedImage getImage() throws IOException {
        String path = color == COLOR.WHITE ? "" : "img/black_knight.png";
        return ImageIO.read(new File(path));
    }
}

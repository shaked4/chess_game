import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(int x, int y, COLOR color) {
        super(TYPE.BISHOP, x, y, color);
    }


    @Override
    public ArrayList<Square> getPossibleSquares(Piece[] t) {
        return null;
    }

    @Override
    BufferedImage getImage() throws IOException {
        throw new UnsupportedOperationException();
    }
}

import java.awt.image.BufferedImage;
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
    public ArrayList<Square> getPossibleSquares(List<Piece> t) {
        return null;
    }

    @Override
    BufferedImage getImage() throws IOException {
        throw new UnsupportedOperationException();
    }
}

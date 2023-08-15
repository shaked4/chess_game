import java.util.ArrayList;

public class Knight extends Piece {


    public Knight( int x, int y, COLOR color) {
        super(TYPE.KNIGHT, x, y, color);
    }

    @Override
    public ArrayList<Square> getPossibleSquares(Piece[] t) {
        return new ArrayList<Square>();
    }

    @Override
    String getImagePath() {
        return color == COLOR.WHITE ? "" : "black_knight05.png";
    }
}

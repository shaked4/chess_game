import java.util.ArrayList;

public class Knight extends Piece {


    public Knight( int x, int y, COLOR color) {
        super(TYPE.KNIGHT, x, y, color);
    }

    @Override
    public ArrayList<Square> getPossibleSquares(Piece[] t) {
        return new ArrayList<Square>();
    }



}

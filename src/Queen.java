import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(int x, int y, COLOR color) {
        super(TYPE.QUEEN, x, y, color);
    }



    @Override
    public ArrayList<Square> getPossibleSquares(Piece[] t) {
        return null;
    }
}
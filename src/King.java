import java.util.ArrayList;

public class King extends Piece{

    boolean moved=false;
    public King( int x, int y, COLOR color) {
        super(TYPE.KING, x, y, color);
    }

    public void onMoved() {
        super.onMoved();
        moved=true;
    }

    @Override
    public ArrayList<Square> getPossibleSquares(Piece[] t) {
        return null;
    }


}

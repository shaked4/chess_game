import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    public enum COLOR {
        BLACK,
        WHITE
    }

    public enum TYPE {
        PAWN,
        KNIGHT,
        QUEEN,
        KING,
        BISHOP,
        ROOK
    }


    TYPE type;
    Square square;
    boolean alive = true;
    COLOR color;


    public Piece(TYPE type, int x, int y, COLOR color) {
        this.type = type;
        this.square = new Square(x, y);
        this.color = color;
    }

    public void onMoved() {

    }
    protected boolean isInBoundaries(Square square,int x,int y)
    {
        return square.getX()-x>-1 && square.getY()-y>-1;

    }
    protected boolean isSquareTaken(Square square, List<Piece> pieces) {
        return getPiece(square, pieces) != null;
    }

    protected boolean isSquareTakenSameColor(Square square, List<Piece> pieces,COLOR color) {
        return getPiece(square, pieces) != null && getPiece(square,pieces).color==color;
    }
    protected Piece getPiece(Square square, List<Piece> pieces) {
        if (square == null) {
            return null;
        }

        for (Piece piece : pieces) {
            if (piece != null && piece.alive && piece.square.isEqual(square)) {
                return piece;
            }
        }
        return null;
    }

    public abstract ArrayList<Square> getPossibleSquares(List<Piece> t);

    abstract BufferedImage getImage() throws IOException;
}

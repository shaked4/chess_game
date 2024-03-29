import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    TYPE type;
    Square square;
    boolean alive = true;
    COLOR color;

    boolean moved=false;
    public Piece(TYPE type, int x, int y, COLOR color) {
        this.type = type;
        this.square = new Square(x, y);
        this.color = color;

    }

    public void onMoved()
    {
        this.moved=true;

    }


    public boolean isInBoundaries(Square square, int x, int y) {
        return square.getX() + x > -1 && square.getY() + y > -1 && square.getX() + x < 8 && square.getY() + y < 8;

    }

    protected boolean isSquareTaken(Square square, List<Piece> pieces) {
        return getPiece(square, pieces) != null;
    }

    protected boolean isSquareTakenSameColor(Square square, List<Piece> pieces, COLOR color) {
        return getPiece(square, pieces) != null && getPiece(square, pieces).color == color;
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

    public void setSquare(int x, int y) {
        this.square.SetXandY(x, y);
    }

    public abstract ArrayList<Square> getPossibleSquaresForPiece(Board board);

    public ArrayList<Square> getPossibleSquares(Board board) {
        ArrayList<Square> squares = getPossibleSquaresForPiece(board);


        squares.removeIf(square -> board.isInCheckDiffPosition(this, square.x, square.y));
        return squares;
    }

    abstract BufferedImage getImage() throws IOException;

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
    public COLOR getColor()
    {
        return this.color == COLOR.WHITE? COLOR.WHITE :  COLOR.BLACK;

    }
    public void setAlive(boolean alive)
    {
        this.alive=alive;
    }
}

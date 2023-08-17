import java.util.ArrayList;

public class Board {
    ArrayList<Piece> pieces = new ArrayList<>();
    ArrayList<Square> circles = new ArrayList<>();

    public Board() {
        init();
    }

    private void init() {
        pieces.add(new Pawn(0, 6, Piece.COLOR.WHITE));
        pieces.add(new Knight(1, 0, Piece.COLOR.BLACK));
        pieces.add(new Pawn(0, 4, Piece.COLOR.WHITE));
        pieces.add(new Knight(1, 5, Piece.COLOR.BLACK));
        pieces.add(new Pawn(5, 6, Piece.COLOR.WHITE));
        pieces.add(new Knight(6, 5, Piece.COLOR.BLACK));
        pieces.add(new Pawn(3, 1, Piece.COLOR.BLACK));
        pieces.add(new Bishop(3,4, Piece.COLOR.WHITE));
    }

    void addCircle(Square sq) {
        circles.add(sq);
    }

    void removeAllCircles() {
        circles.clear();
    }
}

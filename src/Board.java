import java.util.ArrayList;

public class Board {
    ArrayList<Piece> pieces = new ArrayList<>();
    ArrayList<Square> greenCircles = new ArrayList<>();
    Piece whiteKing, blackKing;
    Piece lastPieceClicked;

    public Board() {
        init();
    }

    private void init() {
        pieces.add(new Pawn(0, 6, Piece.COLOR.WHITE));
        pieces.add(new Knight(1, 0, Piece.COLOR.BLACK));
        pieces.add(new Pawn(0, 4, Piece.COLOR.WHITE));
        pieces.add(new Knight(1, 5, Piece.COLOR.BLACK));
        pieces.add(new Pawn(4, 4, Piece.COLOR.WHITE));
        pieces.add(new Knight(6, 5, Piece.COLOR.BLACK));
        pieces.add(new Pawn(3, 1, Piece.COLOR.BLACK));
        pieces.add(new Bishop(3, 4, Piece.COLOR.WHITE));
        pieces.add(new Rook(6, 3, Piece.COLOR.WHITE));
        pieces.add(new Pawn(5, 2, Piece.COLOR.BLACK));
        pieces.add(new Queen(4, 3, Piece.COLOR.WHITE));
        blackKing = new King(5, 0, Piece.COLOR.BLACK);
        pieces.add(blackKing);
        whiteKing = new King(2, 6, Piece.COLOR.WHITE);
        pieces.add(whiteKing);
        pieces.add(new Bishop(6,6,Piece.COLOR.BLACK));
    }

    void addCircle(Square sq) {
        greenCircles.add(sq);
    }

    void removeAllCircles() {
        greenCircles.clear();
    }

    boolean isInCheck(Piece king) {
        ArrayList<Square> possibleSquares;
        for (Piece piece : pieces) {
            if (piece != king) {
                possibleSquares = piece.getPossibleSquaresForPiece(this);
                if (possibleSquares != null && isTheKingThere(possibleSquares, king)) {
                    return true;
                }
            }
        }
        return false;
    }

    ///function if we want to check if there is check if the king is in diffrent square so we get the king and the square and we change the square of the king
    ///we have to make sure before we use the function that the square is empty
    boolean isInCheckDiffPosition(Piece piece, int x, int y) {
        int prevX = piece.square.x, prevY = piece.square.y;
        piece.setSquare(x, y);

        boolean result = isInCheck(piece.color == Piece.COLOR.BLACK ? blackKing : whiteKing);
        piece.setSquare(prevX, prevY);
        return result;
    }

    boolean isTheKingThere(ArrayList<Square> possibleSquares, Piece king) {
        for (Square square : possibleSquares) {
            if (square.getX() == king.square.getX() && square.getY() == king.square.getY()) {
                return true;
            }
        }
        return false;
    }


}

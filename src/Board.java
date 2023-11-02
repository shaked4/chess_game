import java.util.ArrayList;

public class Board {
    ArrayList<Piece> pieces = new ArrayList<>();
    ArrayList<Square> greenCircles = new ArrayList<>();

    Piece whiteKing,blackKing;

    Piece lastPieceClicked;

    public Board() {
        init();
    }

    private void init() {
        pieces.add(new Pawn(0, 6, Piece.COLOR.WHITE));
        pieces.add(new Knight(4, 1, Piece.COLOR.BLACK));
        pieces.add(new Pawn(0, 4, Piece.COLOR.WHITE));
        pieces.add(new Knight(1, 5, Piece.COLOR.BLACK));
        pieces.add(new Pawn(5, 6, Piece.COLOR.WHITE));
        pieces.add(new Knight(6, 5, Piece.COLOR.BLACK));
        pieces.add(new Pawn(3, 1, Piece.COLOR.BLACK));
        pieces.add(new Bishop(3,4, Piece.COLOR.WHITE));
        pieces.add(new Rook(6,3, Piece.COLOR.WHITE));
        pieces.add(new Pawn(5, 2, Piece.COLOR.BLACK));
        pieces.add(new Queen(3,2, Piece.COLOR.WHITE));
        blackKing=new King(5,0, Piece.COLOR.BLACK);
        pieces.add(blackKing);

    }

    void addCircle(Square sq) {
        greenCircles.add(sq);
    }

//    void setX(int index, int newX)
//    {
//        pieces.set(index,)
//    }

    void removeAllCircles() {
        greenCircles.clear();
    }

    boolean isInCheck(Piece king)
    {
        ArrayList<Square> possibleSquares;
        for (Piece piece: pieces)
        {
                        possibleSquares=piece.getPossibleSquares(pieces);
            if (possibleSquares!=null && piece.type!= Piece.TYPE.KING && isTheKingThere(possibleSquares, king)) {
                return true;
            }
        }
        return false;
    }
    boolean isTheKingThere(ArrayList<Square> t, Piece king)
    {
        for (Square square: t)
        {
            if(square.getX()==king.square.getX() && square.getY()==king.square.getY())
            {
                return true;
            }
        }
        return false;
    }
    public Piece getKingDiffrentColor(Piece piece)
    {
        if (piece.color== Piece.COLOR.WHITE)
            return blackKing;
        else
            return whiteKing;
    }
    public Piece getKingSameColor(Piece piece)
    {
        if (piece.color== Piece.COLOR.WHITE)
            return whiteKing;
        else
            return blackKing;
    }

}

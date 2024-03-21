import java.util.ArrayList;

public class Board {
    ArrayList<Piece> pieces = new ArrayList<>();
    ArrayList<Square> greenCircles = new ArrayList<>();
    Piece whiteKing, blackKing;
    Piece lastPieceClicked=null;

    Piece.COLOR lastPieceClickedColor;

    public Board() {
        init();
    }

    private void init() {
        pieces.add(new Pawn(0, 6, Piece.COLOR.WHITE));
        pieces.add(new Pawn(1, 6, Piece.COLOR.WHITE));
        pieces.add(new Pawn(2, 6, Piece.COLOR.WHITE));
        pieces.add(new Pawn(3, 6, Piece.COLOR.WHITE));
        pieces.add(new Pawn(4, 6, Piece.COLOR.WHITE));
        pieces.add(new Pawn(5, 6, Piece.COLOR.WHITE));
        pieces.add(new Pawn(6, 6, Piece.COLOR.WHITE));
        pieces.add(new Pawn(7, 6, Piece.COLOR.WHITE));
        pieces.add(new Bishop(2, 7, Piece.COLOR.WHITE));
        pieces.add(new Bishop(5, 7, Piece.COLOR.WHITE));
        pieces.add(new Rook(0, 7, Piece.COLOR.WHITE));
        pieces.add(new Rook(7, 7, Piece.COLOR.WHITE));
        pieces.add(new Queen(3, 7, Piece.COLOR.WHITE));
        pieces.add(new Knight(1, 7, Piece.COLOR.WHITE));
        pieces.add(new Knight(6, 7, Piece.COLOR.WHITE));
        whiteKing = new King(4, 7, Piece.COLOR.WHITE);
        pieces.add(whiteKing);

//BLACK
        pieces.add(new Pawn(0, 1, Piece.COLOR.BLACK));
        pieces.add(new Pawn(1, 1, Piece.COLOR.BLACK));
        pieces.add(new Pawn(2, 1, Piece.COLOR.BLACK));
        pieces.add(new Pawn(3, 1, Piece.COLOR.BLACK));
        pieces.add(new Pawn(4, 1, Piece.COLOR.BLACK));
        pieces.add(new Pawn(5, 1, Piece.COLOR.BLACK));
        pieces.add(new Pawn(6, 1, Piece.COLOR.BLACK));
        pieces.add(new Pawn(7, 1, Piece.COLOR.BLACK));
        pieces.add(new Bishop(2, 0, Piece.COLOR.BLACK));
        pieces.add(new Bishop(5, 0, Piece.COLOR.BLACK));
        pieces.add(new Rook(0, 0, Piece.COLOR.BLACK));
        pieces.add(new Rook(7, 0, Piece.COLOR.BLACK));
        pieces.add(new Queen(3, 0, Piece.COLOR.BLACK));
        pieces.add(new Knight(1, 0, Piece.COLOR.BLACK));
        pieces.add(new Knight(6, 0, Piece.COLOR.BLACK));

        blackKing = new King(4, 0, Piece.COLOR.BLACK);

        pieces.add(blackKing);

//        pieces.add(new Queen(1,1,Piece.COLOR.BLACK));
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
            if (piece != king && piece.alive) {
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
        Square square = new Square(x, y);
        if (isThereAPiece(square)) {
            getThisPiece(square).setAlive(false);
            piece.setSquare(x, y);
            boolean result = isCheckIfEaten(getThisPiece(square));
            piece.setSquare(prevX, prevY);
            getThisPiece(square).setAlive(true);
            return result;

        }

        piece.setSquare(x, y);
        boolean result = isInCheck(piece.color == Piece.COLOR.BLACK ? blackKing : whiteKing);
        piece.setSquare(prevX, prevY);
        return result;
    }

    boolean isItCheckmate(Piece king) {
        ArrayList<Square> piecePossibleSquares = new ArrayList<>();
        int sum=0;
        for (Piece piece : pieces) {
            if (piece.color == king.color) {
                piecePossibleSquares = piece.getPossibleSquares(this);
                sum=sum+piecePossibleSquares.size();
            }


        }
        return sum <= 0;
    }


    boolean isCheckIfEaten(Piece piece){

        boolean result=isInCheck(piece.color == Piece.COLOR.BLACK ? whiteKing : blackKing);
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
    boolean isThereAPiece(Square square)
    {
        for (Piece piece:pieces)
        {
            if(square.x==piece.square.x && square.y==piece.square.y)
                return true;
        }
        return false;
    }
    public boolean isThereAnOppositePiece(Square square, Piece piece)
    {

        for (Piece Squarepiece:pieces)
        {
            if(square.x==Squarepiece.square.x && square.y==Squarepiece.square.y)
                if (Squarepiece.color!=piece.color)
                    return true;
        }
        return false;
    }
    Piece getThisPiece(Square square){

        for (Piece piece: pieces)
        {
            if(square.x==piece.square.x && square.y==piece.square.y)
                return piece;
        }
        return null;
    }

    public void setLastPieceClickedColor(Piece.COLOR lastPieceClickedColor) {
        this.lastPieceClickedColor = lastPieceClickedColor;
    }
    public boolean isThereGreenCircle(int x,int y)
    {
//        Square nowSquare=new Square(x,y);
        for (Square greenSquare:greenCircles)
        {
            if (greenSquare.x== x && greenSquare.y== y)
                return true;
        }
        return false;
    }
}

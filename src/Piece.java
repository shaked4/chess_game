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
     boolean alive=true;

     COLOR color;


     public Piece(TYPE type, int x, int y, COLOR color) {
          this.type=type;
          this.square = new Square(x, y);
          this.color=color;
     }
     public void onMoved()
     {

     }

     public abstract ArrayList<Square> getPossibleSquares(Piece[] t);



     public Square getSquare() {
          return square;
     }
     public COLOR getColor(){
          return this.color;
     }
}

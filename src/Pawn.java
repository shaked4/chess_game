import java.util.ArrayList;
import java.util.function.Predicate;

public class Pawn extends Piece {
boolean moved=false;
    public Pawn( int x, int y, COLOR color) {
        super(TYPE.PAWN, x, y, color);
    }

    @Override
    public void onMoved() {
        super.onMoved();
        moved=true;
    }

    @Override
    public ArrayList<Square> getPossibleSquares(Piece[] t) {
        ArrayList<Square> options=new ArrayList<>();


        ///to see which color can eat who
        switch (this.color){
            case WHITE:
                Square temp=new Square(0,0);
                if (moved==false)
                    options.add(new Square(this.square.getX(),this.square.getY()-2));
                options.add(new Square(this.square.getX(),this.square.getY()-1));

                for (int i=0 ;i<32; i++) {
                    if (moved == false)
                        if (t[i] != null && t[i].getSquare().getX() == this.square.getX() && t[i].getSquare().getY() == this.square.getY() - 2) {
                            temp.setX(this.square.getX());
                            temp.setY(this.square.getY()-2);
                            options.remove(temp);
                            Predicate<Square> condition = square.x-> square.x=this.square.getX() && square.y-> square.y=this.square.getY();
                            options.removeIf(condition);



                        }   ///options.remove(new Square(this.square.getX(),this.square.getY()-2));

                    if (t[i]!=null && t[i].getSquare().getX() == this.square.getX() && t[i].getSquare().getY() == this.square.getY()-1 )
                        options.remove(new Square(this.square.getX(),this.square.getY()-1));

                    if ( t[i]!=null && this.square.getX()>0 && t[i].getColor()==COLOR.BLACK && t[i].getSquare().getX() - 1 == this.square.getX() && t[i].getSquare().getY()== this.square.getY() -1 )
                        options.add(new Square(this.square.getX()-1,this.square.getY()-1));

                    if ( t[i]!=null && this.square.getX()<7 && t[i].getColor()==COLOR.BLACK && t[i].getSquare().getX() -1 == this.square.getX() && t[i].getSquare().getY() == this.square.getY()-1)
                        options.add(new Square(this.square.getX() + 1,this.square.getY()-1));



                }
                break;
            case BLACK:


                if (moved==false)
                    options.add(new Square(this.square.getX(),this.square.getY()+2));
                options.add(new Square(this.square.getX(),this.square.getY()+1));


                for (int i=0 ;i<32; i++)
                {
                    if (moved==false)
                        if (t[i]!=null && t[i].getSquare().getX() == this.square.getX() && t[i].getSquare().getY() == this.square.getY()+2)
                            options.remove(new Square(this.square.getX(),this.square.getY()+2));

                    if (t[i]!=null && t[i].getSquare().getX() == this.square.getX() && t[i].getSquare().getY() == this.square.getY()+1 )
                        options.remove(new Square(this.square.getX(),this.square.getY()+1));
                    ///check for right first
                    if ( t[i]!=null && this.square.getX()>0 && t[i].getColor()==COLOR.WHITE && t[i].getSquare().getX() +1 == this.square.getX() && t[i].getSquare().getY()+1 == this.square.getY()  )
                        options.add(new Square(this.square.getX()+1,this.square.getY()-1));

                    if ( t[i]!=null && this.square.getX()<7 && t[i].getColor()==COLOR.WHITE && t[i].getSquare().getX() - 1 == this.square.getX() && t[i].getSquare().getY()+1 == this.square.getY())
                        options.add(new Square(this.square.getX()-1,this.square.getY()-1));



                }
                break;

        }





        return options;
    }


}

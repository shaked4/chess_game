import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(int x, int y, COLOR color) {
        super(TYPE.BISHOP, x, y, color);
    }


    @Override
    public ArrayList<Square> getPossibleSquares(List<Piece> t) {
        ArrayList<Square> options = new ArrayList<>();
int x= square.x;
int y= square.y;
        int[][] nextMove={{1,-1},{1,1},{-1,1},{-1,-1}};
        for (int i=0; i<nextMove.length;i++)
        {
            square.x=x;
            square.y=y;
            Square nextSquare=new Square(square.x+nextMove[i][0], square.y+nextMove[i][1] );
            while (!isSquareTakenSameColor(nextSquare,t,color) && isInBoundaries(square, nextMove[i][0], nextMove[i][1]))
            {
                options.add(nextSquare);
                if (getPiece(nextSquare,t)!=null && getPiece(nextSquare,t).color!=this.color)
                    break;
                square.x= square.x+nextMove[i][0];
                square.y=square.y+nextMove[i][1];
                nextSquare=new Square(square.x+nextMove[i][0], square.y+nextMove[i][1] );
            }

        }
        square.x=x;
        square.y=y;
        return options;
    }

    @Override
    BufferedImage getImage() throws IOException {
        String path = color == COLOR.WHITE ? "img/white_bishop.png" : "";
        return ImageIO.read(new File(path));
    }
}

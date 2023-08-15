import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    boolean moved = false;

    public Pawn(int x, int y, COLOR color) {
        super(TYPE.PAWN, x, y, color);
    }

    @Override
    public void onMoved() {
        super.onMoved();
        moved = true;
    }

    private Square forwardSquare() {
        return switch (color) {
            case WHITE -> new Square(square.getX(), square.getY() - 1);
            case BLACK -> new Square(square.getX(), square.getY() + 1);
        };
    }

    private Square forward2Squares() {
        return switch (color) {
            case WHITE -> new Square(square.getX(), square.getY() - 2);
            case BLACK -> new Square(square.getX(), square.getY() + 2);
        };
    }

    private Square getDiagonal(boolean right) {
        if (square.x == 7 && right) {
            return null;
        }

        if (square.x == 0 && !right) {
            return null;
        }

        return switch (color) {
            case WHITE -> new Square(square.getX() + (right ? 1 : -1), square.getY() - 1);
            case BLACK -> new Square(square.getX() + (right ? 1 : -1), square.getY() + 1);
        };
    }

    @Override
    public ArrayList<Square> getPossibleSquares(List<Piece> pieces) {
        ArrayList<Square> options = new ArrayList<>();

        if (!isSquareTaken(forwardSquare(), pieces)) {
            options.add(forwardSquare());
        }

        if (!moved && !isSquareTaken(forwardSquare(), pieces) && !isSquareTaken(forward2Squares(), pieces)) {
            options.add(forward2Squares());
        }

        Square diagonal = getDiagonal(true);
        Piece diagonalPiece = getPiece(diagonal, pieces);
        if (diagonal != null && diagonalPiece != null && diagonalPiece.color != this.color) {
            options.add(diagonal);
        }

        diagonal = getDiagonal(false);
        diagonalPiece = getPiece(diagonal, pieces);
        if (diagonal != null && diagonalPiece != null && diagonalPiece.color != this.color) {
            options.add(diagonal);
        }

        return options;
    }

    @Override
    BufferedImage getImage() throws IOException {
        String path = color == COLOR.WHITE ? "img/white_pawn.png" : "img/black_pawn.png";
        return ImageIO.read(new File(path));
    }
}

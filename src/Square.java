public class Square {
    int x, y;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void SetXandY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEqual(Square square) {
        return this.x == square.x && this.y == square.y;
    }
}

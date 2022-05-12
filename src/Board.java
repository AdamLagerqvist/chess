import java.util.ArrayList;

public class Board {

    private Square[] grid = new Square[64];
    public ArrayList WPieces = new ArrayList();
    public ArrayList BPieces = new ArrayList();

    public static void main(String[] args) {
        new Board();
    }

    public Board() {
        for (int i = 0; i < 64; i++) {
            if (i % 2 == 0){
                grid[i] = new Square(i, 0);
            }
            if (i % 2 == 1) {
                grid[i] = new Square(i, 5294200);
            }
        }

        WPieces = WPieces;
        BPieces = BPieces;
    }
}

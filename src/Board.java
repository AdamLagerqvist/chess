import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board {

    public static final int NORTH = -8;
    public static final int SOUTH = 8;
    public static final int WEST = -1;
    public static final int EAST = 1;

    public static final int NORTHWEST = -9;
    public static final int SOUTHWEST = 7;
    public static final int NORTHEAST = -7;
    public static final int SOUTHEAST = 9;

    private Square[] grid = new Square[64];
    public List<Piece> WPieces = new ArrayList<>();

    public List<Piece> BPieces = new ArrayList<>();

    public Board(int seed) {
        for (int i = 0; i < 64; i++) {
            if (i % 2 == 0){
                grid[i] = new Square(i, false);
            }
            if (i % 2 == 1) {
                grid[i] = new Square(i, true);
            }
        }
        Piece Bpiece;
        Piece Wpiece;
        Random random = new Random(seed);
        for (int i = 0; i < 16; i++) {
            if(i == 4) {
                Bpiece = new King(false, grid[i], this);
                Wpiece = new King(true, grid[63-i], this);
            }else {
                double rand = random.nextDouble();
                if (rand <= 0.5) {
                    Wpiece = new Pawn(true, grid[63-i], this);
                    Bpiece = new Pawn(false, grid[i], this);
                } else if (rand <= 0.625) {
                    Wpiece = new Knight(true, grid[63-i], this);
                    Bpiece = new Knight(false, grid[i], this);
                } else if (rand <= 0.75) {
                    Wpiece = new Rook(true, grid[63-i], this);
                    Bpiece = new Rook(false, grid[i], this);
                } else if (rand <= 0.875) {
                    Wpiece = new Queen(true, grid[63-i], this);
                    Bpiece = new Queen(false, grid[i], this);
                } else {
                    Wpiece = new Bishop(true, grid[63-i], this);
                    Bpiece = new Bishop(false, grid[i], this);
                }
            }
            BPieces.add(Bpiece);
            WPieces.add(Wpiece);
        }
    }

    public Square[] getGrid() {
        return grid;
    }
}

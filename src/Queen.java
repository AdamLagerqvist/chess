import java.util.LinkedList;
import java.util.List;

public class Queen extends Piece{
    public static final String blackQueen = "bqueen.png";
    public static final String whiteQueen = "wqueen.png";

    protected Queen(boolean color, Square initSquare, Board board) {
        super(color, initSquare, board);
        if (color){
            super.image = whiteQueen;
        }else {
            super.image = blackQueen;
        }
    }

    @Override
    public List<Square> getValidMoves(){
        LinkedList<Square> validMoves = new LinkedList<Square>();
        Square[] grid = super.board.getGrid();
        int position = super.currentSquare.getPos();

        // get all valid moves Northwest from initpos
        validMoves.addAll(Piece.getValidSlidingMoves(Board.NORTHWEST,grid,position,super.color));
        validMoves.addAll(Piece.getValidSlidingMoves(Board.SOUTHWEST,grid,position,super.color));
        validMoves.addAll(Piece.getValidSlidingMoves(Board.NORTHEAST,grid,position,super.color));
        validMoves.addAll(Piece.getValidSlidingMoves(Board.SOUTHEAST,grid,position,super.color));
        validMoves.addAll(Piece.getValidSlidingMoves(Board.NORTH,grid,position,super.color));
        validMoves.addAll(Piece.getValidSlidingMoves(Board.SOUTH,grid,position,super.color));
        validMoves.addAll(Piece.getValidSlidingMoves(Board.WEST,grid,position,super.color));
        validMoves.addAll(Piece.getValidSlidingMoves(Board.EAST,grid,position,super.color));
        return validMoves;
    }
}

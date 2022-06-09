import java.util.LinkedList;
import java.util.List;

public class Rook extends Piece{
    public static final String blackRook = "brook.png";
    public static final String whiteRook = "wrook.png";
    protected Rook(boolean color, Square initSquare, Board board) {
        super(color, initSquare, board);
        if (color){
            super.image = whiteRook;
        }else {
            super.image = blackRook;
        }
    }

    @Override
    public List<Square> getValidMoves(){
        LinkedList<Square> validMoves = new LinkedList<Square>();
        Square[] grid = super.board.getGrid();
        int position = super.currentSquare.getPos();

        // get all valid moves Northwest from initpos
        validMoves.addAll(Piece.getValidSlidingMoves(Board.NORTH,grid,position,super.color));
        validMoves.addAll(Piece.getValidSlidingMoves(Board.SOUTH,grid,position,super.color));
        validMoves.addAll(Piece.getValidSlidingMoves(Board.WEST,grid,position,super.color));
        validMoves.addAll(Piece.getValidSlidingMoves(Board.EAST,grid,position,super.color));
        return validMoves;
    }
}

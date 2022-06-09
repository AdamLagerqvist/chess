import java.net.NoRouteToHostException;
import java.util.LinkedList;
import java.util.List;

public class Bishop extends Piece{
    public static final String blackBishop = "src/imgs/bbishop.png";
    public static final String whiteBishop = "src/imgs/wbishop.png";

    protected Bishop(boolean color, Square initSquare, Board board) {
        super(color, initSquare, board);
        if (color){
            super.image = whiteBishop;
        }else {
            super.image = blackBishop;
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
        return validMoves;
    }
}

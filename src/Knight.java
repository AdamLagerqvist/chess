import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece{
    public static final String blackBishop = "src/imgs/bknight.png";
    public static final String whiteBishop = "src/imgs/wknight.png";

    protected Knight(boolean color, Square initSquare, Board board) {
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
        int[] moves = {15,17,10,-6,6,-10,-17,-15};
        int deltaX;
        for (int move:moves) {
            if(position+move >= 0 && position+move < 64){
                if((grid[position+move].isOccupied() && grid[position+move].getOccupyingPiece().color != color) || !grid[position+move].isOccupied()){
                    if(move%8 < 0){
                        deltaX = 8+move%8;
                    }else {
                        deltaX = move%8;
                    }
                    if((deltaX == 7 || deltaX == 6) && (position+deltaX)%8 < position%8){
                        validMoves.add(grid[position + move]);
                    } else if ((deltaX == 1 || deltaX == 2) && (position+deltaX)%8 > position%8) {
                        validMoves.add(grid[position + move]);
                    }
                }
            }
        }
        return validMoves;
    }
}

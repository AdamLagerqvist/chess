import java.util.LinkedList;
import java.util.List;

public class King extends Piece{
    public static final String blackPawn = "src/imgs/bking.png";
    public static final String whitePawn = "src/imgs/wking.png";

    private boolean hasMoved = false;

    protected King(boolean color, Square initSquare, Board board) {
        super(color, initSquare, board);
        if (color){
            super.image = whitePawn;
        }else {
            super.image = blackPawn;
        }
    }

    @Override
    public List<Square> getValidMoves(){
        LinkedList<Square> validMoves = new LinkedList<Square>();
        Square[] grid = super.board.getGrid();
        int position = super.currentSquare.getPos();
        int[] moves = {Board.NORTH,Board.NORTHEAST,Board.EAST,Board.SOUTHEAST,Board.SOUTH,Board.SOUTHWEST,Board.WEST,Board.NORTHWEST};
        for (int move:moves) {
            if(move % 8 == -1 || move % 8 == 7){
                if(position % 8 > (position + move) % 8 && position + move >= 0 && position + move < 64){
                    if(grid[position + move].isOccupied()){
                        if (grid[position + move].getOccupyingPiece().color != color){
                            validMoves.add(grid[position + move]);
                        }
                    }else {
                        validMoves.add(grid[position + move]);
                    }
                }
            }else if(move % 8 != 0){
                if(position % 8 < (position + move) % 8 && position + move >= 0 && position + move < 64){
                    if(grid[position + move].isOccupied()){
                        if (grid[position + move].getOccupyingPiece().color != color){
                            validMoves.add(grid[position + move]);
                        }
                    }else {
                        validMoves.add(grid[position + move]);
                    }
                }
            }else {
                if(position + move >= 0 && position + move < 64){
                    if(grid[position + move].isOccupied()){
                        if (grid[position + move].getOccupyingPiece().color != color){
                            validMoves.add(grid[position + move]);
                        }
                    }else {
                        validMoves.add(grid[position + move]);
                    }
                }
            }
        }

        return validMoves;
    }

    public boolean move(Square destination){
        Piece occupyingPiece = destination.getOccupyingPiece();

        if(!getValidMoves().contains(destination)){
            return false;
        }

        if (occupyingPiece != null) {
            if (occupyingPiece.getColor() != this.color) {
                if (this.color) board.BPieces.remove(occupyingPiece);
                if (!this.color) board.WPieces.remove(occupyingPiece);
                this.currentSquare.setOccupyingPiece(null);
                this.currentSquare = destination;
                destination.setOccupyingPiece(this);
                hasMoved = true;
                return true;
            } else {
                return false;
            }
        }else {
            this.currentSquare.setOccupyingPiece(null);
            this.currentSquare = destination;
            destination.setOccupyingPiece(this);
            hasMoved = true;
            return true;
        }
    }
}

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece{
    public static final String blackPawn = "src/imgs/bpawn.png";
    public static final String whitePawn = "src/imgs/wpawn.png";

    private boolean hasMoved = false;

    protected Pawn(boolean color, Square initSquare, Board board) {
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

        if(color){
            if(!hasMoved){
                if(position-16 >= 0) {
                    if(!grid[position-16].isOccupied() && !grid[position-8].isOccupied()) {
                        validMoves.add(grid[position-16]);
                    }
                }
            }
            if(position-8 >= 0) {
                if(!grid[position-8].isOccupied()) {
                    validMoves.add(grid[position - 8]);
                }
            }
            if(position-7 >= 0 && position%8 < (position-7)%8) {
                if(grid[position-7].isOccupied() && grid[position-7].getOccupyingPiece().color != color) {
                    validMoves.add(grid[position - 7]);
                }
            }
            if(position-9 >= 0 && position%8 > (position-9)%8) {
                if(grid[position-9].isOccupied() && grid[position-9].getOccupyingPiece().color != color) {
                    validMoves.add(grid[position - 9]);
                }
            }
        }else {
            if(!hasMoved){
                if(position+16 < 64) {
                    if(!grid[position+16].isOccupied() && !grid[position+8].isOccupied()) {
                        validMoves.add(grid[position+16]);
                    }
                }
            }
            if(position+8 < 64) {
                if(!grid[position+8].isOccupied()) {
                    validMoves.add(grid[position + 8]);
                }
            }
            if(position+9 < 64 && position%8 < (position+9)%8) {
                if(grid[position+9].isOccupied() && grid[position+9].getOccupyingPiece().color != color) {
                    validMoves.add(grid[position+9]);
                }
            }
            if(position+7 < 64 && position%8 > (position+7)%8) {
                if(grid[position+7].isOccupied() && grid[position+7].getOccupyingPiece().color != color) {
                    validMoves.add(grid[position+7]);
                }
            }
        }
        return validMoves;
    }
    @Override
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
                if((destination.getPos() < 8 && this.color) || (destination.getPos() > 55 && !this.color)){
                    destination.setOccupyingPiece(new Queen(this.color,destination, board));
                    if (color){
                        board.WPieces.remove(this);
                    }else {
                        board.BPieces.remove(this);
                    }
                }
                return true;
            } else {
                return false;
            }
        }else {
            this.currentSquare.setOccupyingPiece(null);
            this.currentSquare = destination;
            destination.setOccupyingPiece(this);
            hasMoved = true;
            if((destination.getPos() < 8 && this.color) || (destination.getPos() > 55 && !this.color)){
                Piece queen = new Queen(this.color,destination, board);
                destination.setOccupyingPiece(queen);
                if (color){
                    board.WPieces.remove(this);
                    board.WPieces.add(queen);
                }else {
                    board.BPieces.remove(this);
                    board.BPieces.add(queen);
                }
            }
            return true;
        }
    }
}

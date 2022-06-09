import java.util.LinkedList;
import java.util.List;

public abstract class Piece {
    protected Board board;
    protected final boolean color;
    protected Square currentSquare;
    protected String image;

    protected Piece(boolean color, Square initSquare, Board board) {
        this.board = board;
        this.color = color;
        this.currentSquare = initSquare;
        initSquare.setOccupyingPiece(this);
    }
    public String getImage() {
        return image;
    }
    public boolean getColor(){
    return color;
    }

    public static LinkedList<Square> getValidSlidingMoves(int increment, Square[] grid, int initpos, boolean color){
        LinkedList<Square> validMoves = new LinkedList<Square>();
        int varIncrement = increment;
        if (increment % 8 == -1 || increment % 8 == 7){
            while (initpos % 8 > (initpos + varIncrement) % 8 && initpos + varIncrement >= 0 && initpos + varIncrement < 64){
                if(grid[initpos + varIncrement].isOccupied()){
                    if (grid[initpos + varIncrement].getOccupyingPiece().color == color){
                        break;
                    }else{
                        validMoves.add(grid[initpos + varIncrement]);
                        break;
                    }
                }else {
                    validMoves.add(grid[initpos + varIncrement]);
                    varIncrement += increment;
                }
            }
        }else if (increment % 8 != 0){
            while (initpos % 8 < (initpos + varIncrement) % 8 && initpos + varIncrement >= 0 && initpos + varIncrement < 64){
                if(grid[initpos + varIncrement].isOccupied()){
                    if (grid[initpos + varIncrement].getOccupyingPiece().color == color){
                        break;
                    }else{
                        validMoves.add(grid[initpos + varIncrement]);
                        break;
                    }
                }else {
                    validMoves.add(grid[initpos + varIncrement]);
                    varIncrement += increment;
                }
            }
        } else {
            while (initpos + varIncrement >= 0 && initpos + varIncrement < 64){
                if(grid[initpos + varIncrement].isOccupied()){
                    if (grid[initpos + varIncrement].getOccupyingPiece().color == color){
                        break;
                    }else{
                        validMoves.add(grid[initpos + varIncrement]);
                        break;
                    }
                }else {
                    validMoves.add(grid[initpos + varIncrement]);
                    varIncrement += increment;
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
                return true;
            } else {
                return false;
            }
        }else {
            this.currentSquare.setOccupyingPiece(null);
            this.currentSquare = destination;
            destination.setOccupyingPiece(this);
            return true;
        }
    }

    public List<Square> getValidMoves(){
        return new LinkedList<Square>();
    };
}
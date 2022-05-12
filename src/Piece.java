public abstract class Piece {
    private Board board;
    private final boolean color;
    private Square currentSquare;

    protected Piece(boolean color, Square initSquare, Board board) {
        this.board = board;
        this.color = color;
        this.currentSquare = initSquare;
    }

    public boolean getColor(){
    return color;
    }

    public boolean move(Square destination){
        Piece occupyingPiece = destination.getOccupyingPiece();

        if (occupyingPiece != null){
            if(occupyingPiece.getColor() != this.color){
                if (this.color) board.BPieces.remove(occupyingPiece);
                if (!this.color) board.WPieces.remove(occupyingPiece);
                this.currentSquare.setOccupyingPiece(null);
                this.currentSquare = destination;
                destination.setOccupyingPiece(this);
                return true;
            }else{
                return false;
            }
        }

        this.currentSquare.setOccupyingPiece(null);
        destination.setOccupyingPiece(this);
        this.currentSquare = destination;
        return true;
    }
}
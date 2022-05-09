public abstract class Piece {
    private final boolean color;
    private Square currentSquare;

    protected Piece(boolean color, Square initSquare) {
        this.color = color;
        this.currentSquare = initSquare;
    }

    public boolean getColor(){
    return color;
    }

    public boolean move(Square destination){
        Piece occupyingPiece = destination.getOccupyingPiece;

        if (occupyingPiece != null){
            if(occupyingPiece.getColor() != this.color){
                if (this.color) board.BPiseces.remove(occupyingPiece);
                if (!this.color) board.WPiseces.remove(occupyingPiece);
                this.currentSquare = destination;
                destination.setOccupyingPiece = this;
                return true;
            }else{
                return false;
            }
        }

        destination.setOccupyingPiece = this;
        this.currentSquare = destination;
        return true;
    }
}
public class Square {

    private final int pos;
    private final int xpos;
    private final int ypos;

    private Piece occupyingPiece;

    private final boolean color;


    public int getPos() {
        return pos;
    }

    public int getXpos() {
        return xpos;
    }
    public int getYpos() {
        return ypos;
    }
    public boolean isOccupied(){
        return (occupyingPiece != null);
    }

    public Square(int pos, boolean color) {
        this.pos = pos;
        this.xpos = (((pos) % 8)+1);
        this.ypos = ((pos+1-xpos) / 8 + 1);
        this.color = color;
    }

    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }

    public void setOccupyingPiece(Piece newPiece) {
        occupyingPiece = newPiece;
    }
}

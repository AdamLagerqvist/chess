public class Square {

    private final int pos;
    private final int xpos;
    private final int ypos;

    private Piece occupyingPiece;

    private final int color;


    public Square(int pos, int color) {
        this.pos = pos;
        this.xpos = (((pos) % 8)+1);
        this.ypos = ((pos+1-xpos) / 8 + 1);
        this.color = color;
    }

    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }

    public void setOccupyingPiece(Piece occupyingPiece) {
        this.occupyingPiece = occupyingPiece;
    }
}

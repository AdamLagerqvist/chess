import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Board board;
    private boolean colorMove = true;
    private View view;
    public Piece selectedPiece;
    public SocketHandler socketHandler;

    public boolean isServer;
    public boolean yourColor;

    public int seed = (int)(Math.random()*1000);

    public static void main(String[] args){
        new Controller();
    }
    public List<Piece> getPieces() {
        List<Piece> pieces = new ArrayList();
        pieces.addAll(board.WPieces);
        pieces.addAll(board.BPieces);
        return pieces;
    }

    public Controller() {
        int isServer = JOptionPane.showConfirmDialog(null,"Do you want to be a server");
        if(isServer == 0){
            this.isServer = true;
            socketHandler = new SocketHandler();
            socketHandler.sendMessage("" + seed);
            yourColor = false;
            board = new Board(seed);
            view = new View(this);
            new Thread(new moveGetter(this)).start();
        } else {
            this.isServer = false;
            socketHandler = new SocketHandler("localhost");
            String msg = null;
            while (msg == null){
                msg = socketHandler.readMessage();
            }
            seed = Integer.parseInt(msg);
            yourColor = true;
            board = new Board(seed);
            view = new View(this);
        }
    }

    public Square[] getGrid(){
        return board.getGrid();
    }

    public void constructMove(int x, int y) {
        if (colorMove == yourColor) {
            Square[] grid = board.getGrid();
            Square mouseSquare = grid[(int) ((x - 1) / 100 + 8 * Math.floor(y / 100))];
            if (selectedPiece == null && mouseSquare.getOccupyingPiece().color == colorMove) {
                selectedPiece = mouseSquare.getOccupyingPiece();
            } else if (selectedPiece != null) {
                int intiPos = selectedPiece.currentSquare.getPos();
                if (selectedPiece.move(mouseSquare)) {
                    colorMove = !colorMove;
                    socketHandler.sendMessage(intiPos + "," + mouseSquare.getPos());
                    new Thread(new moveGetter(this)).start();
                }
                selectedPiece = null;
            }
        }
    }
    public void move(String move){
        int from = Integer.parseInt(move.substring(0, move.indexOf(",")));
        int to = Integer.parseInt(move.substring(move.indexOf(",")+1));
        Square[] grid = getGrid();

        grid[from].getOccupyingPiece().move(grid[to]);
        colorMove = !colorMove;
    }
    public Piece getselectedPiece(){
        return selectedPiece;
    }
}

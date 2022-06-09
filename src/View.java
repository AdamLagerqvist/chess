import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class View extends Canvas implements Runnable, MouseListener {

    private Controller controller;
    private int fps = 60;
    private BufferStrategy bs;
    private Thread thread;
    public double deltaT = 1.0/fps;

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
    }

    public View(Controller controller) {
        this.controller = controller;
        JFrame frame = new JFrame("Chess");
        this.setSize(800,800);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        this.start();
        addMouseListener(this);
    }

    private void draw(){
        bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);

        g.fillRect(0,0,800,800);

        g.setColor(new Color(30, 150, 100));
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 8; y++) {
                if (y % 2 == 0) {
                    g.fillRect(x * 200, y * 100, 100, 100);
                } else {
                    g.fillRect(x * 200 + 100, y * 100, 100, 100);
                }
            }
        }
        g.setColor(new Color(245, 97, 132, 129));
        if (controller.getselectedPiece() != null){
            g.fillRect(controller.getselectedPiece().currentSquare.getXpos()*100-100,controller.getselectedPiece().currentSquare.getYpos()*100-100,100,100);
            controller.getselectedPiece().getValidMoves().forEach(square -> {
                g.fillRect(square.getXpos()*100-100,square.getYpos()*100-100,100,100);
            });
        }
        controller.getPieces().forEach(piece -> {
            try {
                BufferedImage image = ImageIO.read(getClass().getResource(piece.getImage()));
                g.drawImage(image,5 + (piece.currentSquare.getXpos()-1) * 100,(piece.currentSquare.getYpos()-1) * 100 + 5,90,90,null);
            } catch (IOException e) {
                System.out.println("Something went wrong when loading Images");
                System.out.println(e);
            }
        });
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while(true){
            long now = System.currentTimeMillis();
            if (now - lastTime >= deltaT) {
                try{
                    draw();
                } catch (NullPointerException e){}
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        controller.constructMove(e.getX(),e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
import java.io.IOException;

public class moveGetter implements Runnable{
    private Controller controller;

    public moveGetter(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        String msg = null;
        boolean hasGottenMove = false;
        while (!hasGottenMove) {
                msg = controller.socketHandler.readMessage();
            if (msg != null) {
                controller.move(msg);
                hasGottenMove = true;
            }
        }
    }
}
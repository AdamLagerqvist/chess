import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String ip = "localhost"; //JOptionPane.showInputDialog(null,"IP?","Connect to..",JOptionPane.QUESTION_MESSAGE);
        int port = 8000; //Integer.parseInt(JOptionPane.showInputDialog(null,"Port?","Connect to..",JOptionPane.QUESTION_MESSAGE));
        Socket socket;

        try {
            socket = new Socket(ip,port);
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Client failed to connect");
            System.exit(0);
            return;
        }

        // GO
        try {
            Scanner tgb = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // ListenerThread in = new ListenerThread(br);
            // Thread listener = new Thread(in);
            // listener.start();
            out.println("asdfghjkl");
            boolean run = true;
            while (run) {
                String msg = tgb.nextLine();
                if (msg.equals("quit")) {
                    run = false;
                    out.println(msg);
                } else {
                    out.println("Client: " + msg);
                }
            }

            out.close();
            socket.close();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Client failed to communicate");
        }
    }
}
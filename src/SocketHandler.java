import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHandler {
    private PrintWriter out;
    private boolean run = true;
    private BufferedReader in;
    private Socket socket;
    public SocketHandler(){
        int port = 8000;
        ServerSocket serverSocket;
        System.out.println("Server started.");

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for connections!");
            socket = serverSocket.accept();
            // Go
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // ListenerThread in = new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            // Thread listener = new Thread(in);
            // listener.start();
            System.out.println("Client connected!");
            // thread = new Thread(this);
            // thread.start();
        } catch (Exception e) {
            System.out.println("Server fail");
        }
    }
    public SocketHandler(String serverIp){
        int port = 8000;
        System.out.println("Server started.");

        try {
            socket = new Socket(serverIp,port);
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Client failed to connect");
            System.exit(0);
            return;
        }

        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client failed to connect");
            System.exit(0);
            return;
        }
    }

    public String readMessage(){
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void sendMessage(String message){
        out.println(message);
        System.out.println("Sending: " + message);
    }
}
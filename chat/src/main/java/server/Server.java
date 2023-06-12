package server;

import messages.MessagesBroker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends AbstractServer {

    private static Server instance = null;

    private Server() {}

    public static Server getInstance() {
        if (instance == null) {
            synchronized(Server.class) {
                if (instance == null) {
                    instance = new Server();
                }
            }
        }
        return instance;
    }

    public ServerSocket connect() throws IOException {
        return super.connect();
    }

    public void onOpen(ServerSocket serverSocket) throws IOException {

        System.out.println("ServerSocket Pipeline connected on " + this.getHost() + ":" + this.getPort());

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("User login");

            ServerThread serverThread = new ServerThread(socket);
            serverThread.start();
            MessagesBroker.subscribe(serverThread);
        }
    }

    public void onMessage(Socket socket) throws IOException {

        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String message;
        do {
            message = reader.readLine();
            MessagesBroker.broadcast(message);
        } while (!message.equals("exit"));

    }

    public void onClose(Socket socket) throws IOException {
        System.out.println("close");
    }

    public void onError(IOException ex) {
        System.out.println("Server exception: " + ex.getMessage());
        ex.printStackTrace();
    }
}

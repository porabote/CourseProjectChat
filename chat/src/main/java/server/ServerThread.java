package server;

import messages.MessagesBroker;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void sendMessage(String message) {
        try {
            this.writer.write(message + "\n");
            this.writer.flush();
        } catch (IOException e) {
            // TODO: Here you HAVE to check if the connection was closed
            // And if it was closed, call a method in the server class to
            // remove this client.
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            Server.getInstance().onMessage(socket);
            socket.close();
            Server.getInstance().onClose(socket);
        } catch (IOException ex) {
            Server.getInstance().onError(ex);
        }
    }
}

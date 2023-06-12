package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        runServer();
    }

    public static void runServer() throws IOException {
        ServerSocket serverSocket = Server.getInstance().connect();
    }

}

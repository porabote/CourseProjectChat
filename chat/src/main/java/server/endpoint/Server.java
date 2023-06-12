package server.endpoint;

import client.ClientThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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

    public void connect() throws IOException {
        super.connect();
    }

    public void onOpen(ServerSocket serverSocket) throws IOException {
        System.out.println("Server connected on " + this.getHost() + ":" + this.getPort());
    }
}

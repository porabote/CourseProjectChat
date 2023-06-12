package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

interface IServerEndpoint {
    public int getPort();
    public String getHost();

    public void onOpen(ServerSocket serverSocket) throws IOException;
    public void onClose(Socket socket) throws IOException;
    public void onMessage(Socket socket) throws IOException;
    public void onError(IOException ex);
}

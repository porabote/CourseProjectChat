package server.endpoint;

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
    public void onClose() throws IOException;
    public void onMessage() throws IOException;
    public void onError(Throwable throwable);
}

package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

interface IClientEndpoint {
    public int getPort();
    public String getHost();

    public void onOpen(Socket clientSocket, BufferedReader in, BufferedWriter out, BufferedReader systemIn) throws IOException;
    public void onClose() throws IOException;
    public void onMessage() throws IOException;
    public void onError(Throwable throwable);
}

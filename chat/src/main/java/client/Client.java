package client;

import users.ChatUser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class Client extends AbstractClient {

    public Client(ChatUser user) throws IOException {
        this.user = user;
    }

    public void connect() throws IOException {
        super.connect();
    }

    @Override
    public void onOpen(Socket clientSocket, BufferedReader in, BufferedWriter out, BufferedReader systemIn) throws IOException {

    }

}

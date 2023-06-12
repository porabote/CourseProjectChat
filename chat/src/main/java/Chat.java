import client.Client;
import server.endpoint.Server;
import users.ChatUser;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Chat {

    private static Server server;
    //private static ArrayList<Socket> clientSocketList;

    public static void connectServer() throws IOException {
        Server.getInstance().connect();
    }

    public static void connectClient(ChatUser user) throws IOException {
        Client client = new Client(user);
        client.connect();
    }

}

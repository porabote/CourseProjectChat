import client.Client;
import server.Server;
import users.ChatUser;

import java.io.IOException;

public class Chat {

    public static void connectServer() throws IOException {
        Server.getInstance().connect();
    }

    public static void connectClient(ChatUser user) throws IOException {
        Client client = new Client(user);
        client.connect();
    }

}

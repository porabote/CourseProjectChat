import java.io.IOException;

import server.endpoint.Server;
import user.User;
import users.ChatUser;

public class Main {

    public static void main(String[] args) throws IOException {

        User user = User.login();
        ChatUser chatUser = new ChatUser(user.getId(), user.getName());

        Chat.connectClient(chatUser);
    }


}

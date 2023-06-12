package messages;

import files.Files;
import server.ServerThread;

import java.io.IOException;
import java.util.ArrayList;

public class MessagesBroker {

    private static String LOG_FILE_PATH = System.getProperty("user.dir") + "/app/storage/chat.messages.json";

    private static ArrayList<ServerThread> clients = new ArrayList<>();

    public static void subscribe(ServerThread client) {
        clients.add(client);
    }

    public static void unsubscribe(ServerThread client) {
        clients.remove(client);
    }

    public static ArrayList<ServerThread> getClients() {
        return new ArrayList<>(clients);
    }

    public static void broadcast(String msgIn) throws IOException {

        log(msgIn);

        for (ServerThread socketThread : MessagesBroker.getClients()) {
            socketThread.sendMessage(msgIn);
        }
    }

    private static void log(String msgIn) throws IOException {
        System.out.println(msgIn);
        Files.write(LOG_FILE_PATH, msgIn + "\n");
    }

    public void push(String unparsedMsg) {
        //Message msg = new Message();
    }

}

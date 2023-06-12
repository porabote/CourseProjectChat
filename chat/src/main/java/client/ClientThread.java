package client;

import com.google.gson.Gson;
import messages.Message;
import users.ChatUser;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ClientThread extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private ChatUser user;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public ClientThread(Socket socket, ChatUser user) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.user = user;
    }

    public void run() {

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        new Thread(() -> {
            String serverMsg;
            try {
                while (true) {
                    serverMsg = this.reader.readLine();

                    Gson gson = new Gson(); // Or use new GsonBuilder().create();
                    Message message = gson.fromJson(serverMsg, Message.class); // deserializes json into target2

                    System.out.println(message.getSenderName() + " : " + message.getContent() + "  " + message.getTimestamp() + "\n");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

        try {
            String message = "{\"content\": \"Hello i'm hear \", \"senderName\": " + this.user.getName() + ", \"timestamp\": \"" + LocalDateTime.now() + "\"}";
            this.writer.write(message + "\n");
            this.writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String userInput;
        do {
            try {
                while (true) {
                    userInput = stdIn.readLine();

                    String message = "{\"content\": \"" + userInput + "\", \"senderName\": " + this.user.getName() + ", \"timestamp\": \"" + LocalDateTime.now() + "\"}";

                    this.writer.write(message + "\n");
                    this.writer.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!userInput.equals("exit" + "\n"));
    }

}
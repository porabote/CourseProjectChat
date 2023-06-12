package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import messages.Message;
import messages.MessageBuilder;
import users.ChatUser;

import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;

public class ClientThread extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private ChatUser user;

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

                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MMM-dd HH:mm:ss").create();
                    Message message = gson.fromJson(serverMsg, Message.class); // deserializes json into target2

                    System.out.println(message.getSenderName() + " : " + message.getContent() + "  " + message.getFormatedTimestamp() + "\n");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

        try {

            Message message = new MessageBuilder()
                    .setSenderName(this.user.getName())
                    .setContent("Hello i'm hear")
                    .setTimestamp(new Timestamp(System.currentTimeMillis()).getTime())
                    .create();
            this.writer.write(message.toJson() + "\n");
            this.writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String userInput;
        do {
            try {
                while (true) {
                    userInput = stdIn.readLine();

                    Message message = new MessageBuilder()
                            .setSenderName(this.user.getName())
                            .setContent(userInput)
                            .setTimestamp(new Timestamp(System.currentTimeMillis()).getTime())
                            .create();

                    this.writer.write(message.toJson() + "\n");
                    this.writer.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!userInput.equals("exit" + "\n"));
    }

}